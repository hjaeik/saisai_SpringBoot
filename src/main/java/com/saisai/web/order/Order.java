package com.saisai.web.order;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@NoArgsConstructor
@Document(collection = "Order")
@ToString // 임시
public class Order {
    @Id
    private String code;
    private String chatCode;
    private String srcAddress;
    private Coordinate srcCoordinate;
    private String dstAddress;
    private Coordinate dstCoordinate;
    private String written;
    private Long amount;
    private List<String> reqDate;
    private String reqTime;
    private String status;
    private Integer routeTime;
    private Integer routeDistance;
    private long viewCount;
    private List<String> notAllowed;
    private Date createdAt;
    private Date updatedAt;

    @Builder
    public Order(String code, String srcAddress, Coordinate srcCoordinate, String dstAddress, Coordinate dstCoordinate, String written, String chatCode, Long amount, List<String> reqDate, String reqTime, String status, Integer routeTime, Integer routeDistance, long viewCount,
                 List<String> notAllowed, Date createdAt, Date updatedAt) {
        this.code = code;
        this.chatCode = chatCode;
        this.srcAddress = srcAddress;
        this.srcCoordinate = srcCoordinate;
        this.dstAddress = dstAddress;
        this.dstCoordinate = dstCoordinate;
        this.written = written;
        this.amount = amount;
        this.reqDate = reqDate;
        this.reqTime = reqTime;
        this.status = status;
        this.routeTime = routeTime;
        this.routeDistance = routeDistance;
        this.viewCount = viewCount;
        this.notAllowed = notAllowed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

//    public void update(Order updateOrder) {
//        Optional.ofNullable(updateOrder.getCode()).ifPresent(none -> this.code = updateOrder.getCode());
//        Optional.ofNullable(updateOrder.getChatCode()).ifPresent(none -> this.chatCode = updateOrder.getChatCode());
//        Optional.ofNullable(updateOrder.getSrcAddress()).ifPresent(none -> this.srcAddress = updateOrder.getSrcAddress());
//        Optional.ofNullable(updateOrder.getSrcCoordinate()).ifPresent(none -> this.srcCoordinate = updateOrder.getSrcCoordinate());
//        Optional.ofNullable(updateOrder.getDstAddress()).ifPresent(none -> this.dstAddress = updateOrder.getDstAddress());
//        Optional.ofNullable(updateOrder.getDstCoordinate()).ifPresent(none -> this.dstCoordinate = updateOrder.getDstCoordinate());
//        Optional.ofNullable(updateOrder.getWritten()).ifPresent(none -> this.written = updateOrder.getWritten());
//        Optional.ofNullable(updateOrder.getAmount()).ifPresent(none -> this.amount = updateOrder.getAmount());
//        Optional.ofNullable(updateOrder.getReqDate()).ifPresent(none -> this.reqDate = updateOrder.getReqDate());
//        Optional.ofNullable(updateOrder.getReqTime()).ifPresent(none -> this.reqTime = updateOrder.getReqTime());
//        Optional.ofNullable(updateOrder.getStatus()).ifPresent(none -> this.status = updateOrder.getStatus());
//        Optional.ofNullable(updateOrder.getRouteTime()).ifPresent(none -> this.routeTime = updateOrder.getRouteTime());
//        Optional.ofNullable(updateOrder.getRouteDistance()).ifPresent(none -> this.routeDistance = updateOrder.getRouteDistance());
//        Optional.ofNullable(updateOrder.getNotAllowed()).ifPresent(none -> this.notAllowed = updateOrder.getNotAllowed());
//        Optional.ofNullable(updateOrder.getViewCount()).ifPresent(none -> this.viewCount = updateOrder.getViewCount());
//        Optional.ofNullable(updateOrder.getCreatedAt()).ifPresent(none -> this.createdAt = updateOrder.getCreatedAt());
//        Optional.ofNullable(updateOrder.getUpdatedAt()).ifPresent(none -> this.updatedAt = updateOrder.getUpdatedAt());
//    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
//        map.put("code", code);
//        map.put("chatCode", chatCode);
        map.put("srcAddress", srcAddress);
        map.put("srcCoordinate", srcCoordinate);
        map.put("dstAddress", dstAddress);
        map.put("dstCoordinate", dstCoordinate);
//        map.put("written", written);
        map.put("amount", amount);
        map.put("reqDate", reqDate);
        map.put("reqTime", reqTime);
        map.put("status", status);
        map.put("routeTime", routeTime);
        map.put("routeDistance", routeDistance);
//        map.put("viewCount", viewCount);
//        map.put("notAllowed", notAllowed);
//        map.put("createdAt", createdAt);
//        map.put("updatedAt", updatedAt);
        return map;
    }
}
