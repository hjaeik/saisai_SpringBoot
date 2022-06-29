package com.saisai.web.chat;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.saisai.web.fcm.FCMService;
import com.saisai.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
// 트랜잭션 처리
@RestController
@RequiredArgsConstructor
public class ChatSubController {
    private final SimpMessagingTemplate template;
    private final ChatService chatService;
    private final FCMService fcmService;

    //  사용자 소켓 연결, 초기 정보 받음
    @MessageMapping(value = "/chat/init")
    public void init(StompHeaderAccessor headerAccessor){
        String userId = headerAccessor.getUser().getName();
        if(userId != null); // throw Exception("없다");
        chatService.initConnection(headerAccessor.getUser().getName());
        template.convertAndSend("/sub/chat/init/"+userId, chatService.initConnection(headerAccessor.getUser().getName()));
    }

    //  채팅방 입장
    @MessageMapping(value = "/chat/{chatRoom}")
    public void initChatRoom(
            StompHeaderAccessor headerAccessor,
            @DestinationVariable("chatRoom") String chatRoom) {
        String userId = headerAccessor.getUser().getName();
        if(userId != null); // throw Exception
        ChatMessageDto chatMessageDto = ChatMessageDto.builder()
                .chatRoomId(chatRoom)
                .userId(headerAccessor.getUser().getName())
                .build();
        List<ChatMessage> chatRoomList = chatService.initChatRoom(chatMessageDto, userId);
        template.convertAndSend("/sub/chat/"+chatRoom, chatRoomList);
    }

    // 채팅 Send
    @MessageMapping(value = "/chat/send/{chatRoom}")
    public void sendMessage(
            StompHeaderAccessor headerAccessor,
            @DestinationVariable("chatRoom") String chatRoom,
            String message) throws FirebaseMessagingException {
        if(headerAccessor.getUser().getName() != null) {
            ChatMessageDto chatMessageDto = ChatMessageDto.builder()
                    .chatRoomId(chatRoom)
                    .userId(headerAccessor.getUser().getName())
                    .message(message)
                    .build();
            chatService.sendMessage(chatMessageDto);
            fcmService.sendWithoutSender(chatMessageDto, headerAccessor.getUser().getName());
            template.convertAndSend("/sub/chat/" + chatRoom, message);
        }
    }

    // 채팅 read
    @MessageMapping(value ="/chat/read/{chatRoom}/{messageId}")
    public void readMessage(
            StompHeaderAccessor headerAccessor,
            @DestinationVariable("chatRoom") String chatRoom,
            @DestinationVariable("messageId") String messageId) {
        if(headerAccessor.getUser().getName() != null) {
            ChatMessageDto chatMessageDto = ChatMessageDto.builder()
                    .chatRoomId(chatRoom)
                    .userId(headerAccessor.getUser().getName())
                    .build();
        }
//        ChatRoom chatRoom = chatService.readMessage(chat);
        Message message = new Message(true, chatRoom);
//  template.convertAndSend("/sub/chat/room/"+chatRoom.getCode(), message);
    }
}
