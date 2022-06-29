package com.saisai.web.order;

import com.saisai.web.exception.ErrorCode;
import com.saisai.web.exception.ExternalException;
import com.saisai.web.exception.SaisaiException;
import com.saisai.web.util.Message;
import com.saisai.web.util.tmap.TmapRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class OrderService {
    // TmapAppKey env처리 할 것
    // final String TMAPURL = "https://apis.openapi.sk.com/tmap/routes";
    final String TMAPURL = "http://192.168.219.110:3000/order/post";
    final String TmapAppKey = "테스트하시려면 appkey 발급받아주세요.";

    @Autowired
    private OrderMongoRepo orderMongoRepo;

    private WebClient tmapWebClient = WebClient.builder()
            .baseUrl(TMAPURL)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .defaultHeader("appKey", TmapAppKey)
            .exchangeStrategies(
                ExchangeStrategies.builder()
                    .codecs(configurer -> configurer
                    .defaultCodecs()
                    .maxInMemorySize(50 * 1024 * 1024)) // response Size 50MB 제한
                    .build())
            .build();

    //  1 tmap에 API 요청
    //  1.1 (요청 정상) 기존에 미등록된 order 목록 검색
    //  1.2 order 목록이 없는 경우 orderDetail에 Route목록 추가 & order 수정 필요한가?
    //  1.3 order 목록이 있는 경우 orderDetail에 Route목록 수정 & order 수정 필요한가?
    // ----- 미비사항 아래사항 추가 되어져야함
    //  2.1 (요청 불가) 기존에 미등록된 order 목록 검색
    //  2.2 order 목록이 없는 경우 orderDetail에 Route Null로 추가
    //  2.3 order 목록이 있는 경우 orderDetail에 Route Null로 수정
    public Mono<Message> getTmapRoute(OrderRouteDto orderRouteDto, String userId) {
        Mono<Message> mono = tmapWebClient.post()
                .bodyValue(orderRouteDto)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(String.class).map(json -> {
                            try {
                                TmapRoute tmapRoute = new TmapRoute(json);
                                Order order = orderMongoRepo.findOneOrderByStatus("N", userId);
                                if(order == null) {
                                    orderMongoRepo.insertRoute(tmapRoute, orderRouteDto, userId);
                                } else {
                                    orderMongoRepo.updateRoute(tmapRoute, orderRouteDto, order.getCode(), userId);
                                }
                                return new Message(true, tmapRoute);
                            } catch (Exception e) {
                                // Logger.warn(e);
                                System.out.println(e);
                                throw new SaisaiException(ErrorCode.MISS_ROUTE_DATA);
                            }
                        });
                    } else if (response.statusCode().is5xxServerError()) {
                        // Logger.warn(respons)
                        return response.createException().flatMap(error -> Mono.error(new ExternalException(ErrorCode.TMAP_API_SERVERERROR, error.getMessage())));
                    } else {
                        // Logger.warn(respons)
                        return response.createException().flatMap(error -> Mono.error(new ExternalException(ErrorCode.TMAP_API_BADREQUEST, error.getMessage())));
                    }
                })
                .timeout(Duration.ofSeconds(10));
        return mono;
    }

    // [사용자] Order 등록
    public void insertOrder(OrderDto orderDto, String userId) {
        Order order = orderMongoRepo.findOneOrderByStatus("N", userId);
        orderDto.setCode(order.getCode());
        OrderDetail orderDetail = orderMongoRepo.getOrderDetail(order.getCode());

        orderMongoRepo.insertOrder(orderDto.toOrder());
        orderMongoRepo.updateOrderDetail(orderDto.toOrderDetail());
    }

    // 변경하기 전에(status 값 확인해야됌)
    // [사용자] Order 업데이트
    public void updateOrder(OrderDto orderDto, String userId) {
        Order order = orderMongoRepo.getOrder(orderDto.getCode());
        OrderDetail orderDetail = orderMongoRepo.getOrderDetail(orderDto.getCode());
        if(order.getWritten() == userId) {
        //  변경
        //  orderMongoRepo.updateOrder();
            orderMongoRepo.updateOrder(orderDto.toOrder());
        //  detail 변경
        //  orderMongoRepo.updateOrderDetail();
            orderMongoRepo.updateOrderDetail(orderDto.toOrderDetail());
        }
    }

    // [사용자] 자기가 등록한 Order 조회
    public void getOrderByUserInfo(OrderFilterDto orderFilterDto) {
        orderMongoRepo.getOrderByFilter(orderFilterDto);
    }

    // [기사] Order 조회
    public List<Order> getOrder(OrderFilterDto orderFilterDto) {
        return orderMongoRepo.getOrderByFilter(orderFilterDto);
    }

    public OrderDetail getOrderDetail(String orderCode) {
        return orderMongoRepo.getOrderDetail(orderCode);
    }

    // [기사] 수정요청 Order

    // [기사] 수정요청 수락


    public void testUpdate() {
        orderMongoRepo.insertOrder(new Order());
    }
}
