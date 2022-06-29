package com.saisai.web.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@Document(collection = "OrderDetail")
@ToString // 임시
public class OrderDetail {
    @Id
    private String code;
    private String orderCode;
    private String srcAddressDetail;
    private String dstAddressDetail;
    private List<Map> route;
    private List<String> baggages;
    private String reqMsg;

    @Builder
    public OrderDetail(String code, String orderCode, String srcAddressDetail, String dstAddressDetail, List<Map> route, List<String> baggages, String reqMsg) {
        this.code = code;
        this.orderCode = orderCode;
        this.srcAddressDetail = srcAddressDetail;
        this.dstAddressDetail = dstAddressDetail;
        this.route = route;
        this.baggages = baggages;
        this.reqMsg  = reqMsg;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("srcAddressDetail", srcAddressDetail);
        map.put("dstAddressDetail", dstAddressDetail);
        map.put("route", route);
        map.put("baggages", baggages);
        map.put("reqMsg", reqMsg);
        return map;
    }
}
