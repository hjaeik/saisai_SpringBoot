package com.saisai.web.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class OrderDto {
    // 주문번호
    private String code;
    // 채팅코드
    private String chatCode;
    // 출발지 주소
    private String srcAddress;
    // 출발지 상세 주소
    private String srcAddressDetail;
    // 출발지 coordinate
    private Coordinate srcCoordinate;
    // 목적지 주소
    private String dstAddress;
    // 목적지 상세 주소
    private String dstAddressDetail;
    // 출발지 coordinate
    private Coordinate dstCoordinate;
    // 작성자
    private String written;
    // 가격
    private Long amount;
    // 요청 일자
    private List<String> reqDate;
    // 요청 시각
    private String reqTime;
    // 주문 상태
    private String status;
    // 수하물 목록
    private List<String> baggages;
    // 요청 메시지
    private String reqMsg;
    //

    public Order toOrder() {
        return Order.builder()
                .code(code)
                .chatCode(chatCode)
                .srcAddress(srcAddress)
                .srcCoordinate(srcCoordinate)
                .dstAddress(dstAddress)
                .dstCoordinate(dstCoordinate)
                .written(written)
                .amount(amount)
                .reqDate(reqDate)
                .reqTime(reqTime)
                .status(status)
                .build();
    }

    public OrderDetail toOrderDetail() {
        return OrderDetail.builder()
                .orderCode(code)
                .srcAddressDetail(srcAddressDetail)
                .dstAddressDetail(dstAddressDetail)
                .baggages(baggages)
                .reqMsg(reqMsg)
                .build();
    }
}
