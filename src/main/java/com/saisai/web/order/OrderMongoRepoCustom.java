package com.saisai.web.order;

import com.saisai.web.util.tmap.TmapRoute;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public interface OrderMongoRepoCustom {

    String insertRoute(TmapRoute route, OrderRouteDto orderRouteDto, String written);

    String updateRoute(TmapRoute route, OrderRouteDto orderRouteDto, String orderCode, String written);

    Order findOneOrderByStatus(String status, String writtenUserId);

    Order insertOrder(Order order);

    boolean updateOrder(Order order);

    boolean updateOrderDetail(OrderDetail orderDetail);

    Order getOrder(String orderCode);

    OrderDetail getOrderDetail(String orderCode);

    // 등록된 Order 에서 필터를 적용하여 조회
    List<Order> getOrderByFilter(OrderFilterDto orderFilterDto);

    // 조회수 증가
    // 미비사항 : 조회수 증가 이전에 제한되어져야할 사항 필요
    void updateOrderViewCount(String code);

}
