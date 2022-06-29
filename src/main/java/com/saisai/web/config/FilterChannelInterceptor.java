package com.saisai.web.config;

import com.saisai.web.auth.oauth2.AuthToken;
import com.saisai.web.auth.oauth2.AuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilterChannelInterceptor implements ChannelInterceptor {
    final private AuthTokenProvider tokenProvider;

    @Override
    public boolean preReceive(MessageChannel channel) {
        return ChannelInterceptor.super.preReceive(channel);
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);

//        ((Authentication)message.getHeaders().get("simpUser")).getPrincipal();
        if(StompCommand.CONNECT.equals(headerAccessor.getCommand())) {
        }
        return ChannelInterceptor.super.preSend(message, channel);
    }

    public String getAccessToken(String str) {
        if (str == null) return null;
        if (str.startsWith("Bearer")) return str.substring("Bearer ".length());
        return null;
    }
}
