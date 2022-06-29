package com.saisai.web.util.tmap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.saisai.web.order.Order;
import com.saisai.web.order.OrderDetail;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class TmapRoute {
    List<Map> route = new ArrayList<>();
    Integer totalTime;
    Integer totalDistance;

    public TmapRoute(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);
        ArrayNode features = (ArrayNode) jsonNode.path("features");
        JsonNode total = features.get(0).path("properties");
        this.totalTime = total.path("totalTime").asInt();
        this.totalDistance = total.path("totalDistance").asInt();
        for (JsonNode feature : features) {
            if (feature.path("geometry").path("type").asText().equals("LineString")) {
                ArrayNode coordinates = (ArrayNode) feature.path("geometry").path("coordinates");
                for (JsonNode coordinate : coordinates) {
                    Map<String, String> route = new HashMap<>();
                    route.put("longitude", coordinate.get(0).asText());
                    route.put("latitude", coordinate.get(1).asText());
                    this.route.add(route);
                }
            }
        }
    }

    public Order toOrder() {
        return Order.builder()
                .routeTime(totalTime)
                .routeDistance(totalDistance)
                .status("C")
                .build();
    }

    public OrderDetail toOrderDetail() {
        return OrderDetail.builder()
                .route(route)
                .build();
    }

}
