package com.saisai.web.order;

import com.saisai.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    final private OrderService orderService;

    // 경로검색을 진행 하며 경로 DATA 전달시 사용자에게 orderCode & 경로Data 전달
    //    @Request    srcCoordinate[x, y], destCoordinate[x, y]
    //    @Response
    //    @exception  [Not Updated]
    @GetMapping("/route")
    public Mono<Message> orderRoute(@RequestParam(value = "startX") String startX,
                                    @RequestParam(value = "startY") String startY,
                                    @RequestParam(value = "endX") String endX,
                                    @RequestParam(value = "endY") String endY) {
        // latitude = y
        // longitude = x
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        OrderRouteDto orderRouteDto = OrderRouteDto.builder()
                .startX(startX)
                .startY(startY)
                .endX(endX)
                .endY(endY)
                .reqCoordType("WGS84GEO")
                .build();
        Mono<Message> response = orderService.getTmapRoute(orderRouteDto, principal.getUsername());
        return response;
    }

    @GetMapping("/")
    public ResponseEntity<Message> getOrderList(
            @RequestParam(value = "srcAddress") List<String> srcAddress,
            @RequestParam(value = "dstAddress") List<String> dstAddress,
            @RequestParam(value = "reqDate") List<String> reqDate,
            @RequestParam(value = "reqTime") List<String> reqTime
    ) {
        OrderFilterDto orderFilterDto = OrderFilterDto.builder()
                .srcAddress(srcAddress)
                .dstAddress(dstAddress)
                .reqDate(reqDate)
                .reqTime(reqTime)
                .build();

//        orderService.getOrderList(orderFilterDto);
        return null;
    }

    @GetMapping("/test")
    public ResponseEntity<Message> testUpdate() {
        orderService.testUpdate();
        return new ResponseEntity<>(new Message(true, "TEST"), HttpStatus.ACCEPTED);
    }

}
