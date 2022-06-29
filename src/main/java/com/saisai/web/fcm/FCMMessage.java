package com.saisai.web.fcm;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@ToString
@Builder
public class FCMMessage {
    private String channelId;   // notification 종류
    private String title;       // 사용자이름
    private String subtitle;    // roomName(상대방 이름?)
    private String body;        // 대화 내용
    private String tag;         // roomId
    private String timeStamp;   // 시간

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("channelId",channelId);
        map.put("title",title);
        map.put("subtitle",subtitle);
        map.put("body",body);
        map.put("tag",tag);
        map.put("timeStamp",timeStamp);
        return map;
    }
}
