package com.saisai.web.order;

import com.saisai.web.util.tmap.TmapRoute;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.QuadCurve2D;
import java.lang.reflect.Field;
import java.util.*;

public class OrderMongoRepoImpl implements OrderMongoRepoCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Order findOneOrderByStatus(String status, String writtenUserId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").is(status).and("written").is(writtenUserId));
        return mongoTemplate.findOne(query, Order.class);
    }

    // 오류처리
    @Override
    public String insertRoute(TmapRoute tmapRoute, OrderRouteDto orderRouteDto, String userId) {
        Order order = Order.builder()
                .createdAt(new Date())
                .status("N")
                .routeTime(tmapRoute.getTotalTime())
                .routeDistance(tmapRoute.getTotalDistance())
                .srcCoordinate(Coordinate.builder().longitude(orderRouteDto.getStartX()).latitude(orderRouteDto.getStartY()).build())
                .dstCoordinate(Coordinate.builder().longitude(orderRouteDto.getEndX()).latitude(orderRouteDto.getEndY()).build())
                .written(userId)
                .build();
        String code = mongoTemplate.insert(order).getCode();
        OrderDetail orderDetail = OrderDetail.builder()
                .code(code)
                .route(tmapRoute.getRoute())
                .build();
        mongoTemplate.insert(orderDetail);
        return code;
    }

    @Override
    public String updateRoute(TmapRoute tmapRoute, OrderRouteDto orderRouteDto, String orderCode, String written) {
        Update update = new Update();
        update.set("routeTime", tmapRoute.getTotalTime());
        update.set("routeDistance", tmapRoute.getTotalTime());
        update.set("srcCoordinate", Coordinate.builder().longitude(orderRouteDto.getStartX()).latitude(orderRouteDto.getStartY()).build());
        update.set("dstCoordinate", Coordinate.builder().longitude(orderRouteDto.getEndX()).latitude(orderRouteDto.getEndY()).build());
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderCode));
        update = new Update();
        update.set("route", tmapRoute.getRoute());
        mongoTemplate.updateFirst(query, update, OrderDetail.class);
        return orderCode;
    }

    @Override
    public List<Order> getOrderByFilter(OrderFilterDto orderFilterDto) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        Map<String, Object> orderFileterMap = orderFilterDto.toMap();
        Iterator<String> iterator = orderFileterMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = orderFileterMap.get(key);
            if(value != null) criteriaList.add(Criteria.where(key).is(value));
        }

        List<String> reqTimeList = orderFilterDto.getReqTime();
        criteriaList.add(Criteria.where("srcAddress").in(orderFilterDto.getSrcAddress()));
        criteriaList.add(Criteria.where("dstAddress").in(orderFilterDto.getDstAddress()));
        criteriaList.add(Criteria.where("reqDate").in(orderFilterDto.getReqDate()));
        criteriaList.add(Criteria.where("status").is("C"));
        if(reqTimeList.size() > 0) criteriaList.add(Criteria.where("reqTime").gte(reqTimeList.get(0)).lte(reqTimeList.get(1)));
        return mongoTemplate.find(query.addCriteria(Criteria.where("").andOperator(criteriaList)), Order.class);
    }

    @Override
    public Order insertOrder(Order order) {
        return mongoTemplate.insert(order);
    }

    @Override
    public boolean updateOrder(Order order) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(order.getCode()));
        Update update = new Update();
        update.set("_id", order.getCode());
        Map<String, Object> orderMap = order.toMap();
        Iterator<String> iterator = orderMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = orderMap.get(key);
            if(value != null) update.set(key, value);
        }
        return mongoTemplate.updateFirst(query, update, Order.class).wasAcknowledged();
    }

    // 변겨 가능한 부분 Check
    @Override
    public boolean updateOrderDetail(OrderDetail orderDetail) {
        Query query = new Query();
        query.addCriteria(Criteria.where("orderCode").is(orderDetail.getOrderCode()));
        Update update = new Update();
        update.set("orderCode", orderDetail.getOrderCode());
        Map<String, Object> orderDetailMap = orderDetail.toMap();
        Iterator<String> iterator = orderDetailMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = orderDetailMap.get(key);
            if(value != null) update.set(key, value);
        }
        return mongoTemplate.updateFirst(query, update, OrderDetail.class).wasAcknowledged();
    }

    @Override
    public Order getOrder(String orderCode) {
        Query query = new Query();
        return mongoTemplate.findOne(query.addCriteria(Criteria.where("_id").is(orderCode)), Order.class);
    }

    @Override
    public OrderDetail getOrderDetail(String orderCode){
        Query query = new Query();
        return mongoTemplate.findOne(query.addCriteria(Criteria.where("_id").is(orderCode)), OrderDetail.class);
    }

    @Override
    public void updateOrderViewCount(String code) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(code));
        Update update = new Update();
        update.inc("viewCount");
        mongoTemplate.updateFirst(query,update,Order.class);
    }
}
