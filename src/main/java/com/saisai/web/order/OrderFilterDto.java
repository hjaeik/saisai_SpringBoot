package com.saisai.web.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class OrderFilterDto {
    private List<String> srcAddress;
    private List<String> dstAddress;
    private List<String> reqDate;
    private List<String> reqTime;
    private String userId;
    private String status;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("srcAddress", srcAddress);
        map.put("dstAddress", dstAddress);
        map.put("reqDate", reqDate);
        map.put("reqTime", reqTime);
        map.put("userId", userId);
        map.put("status", status);
        return map;
    }
}
