package com.saisai.web.chat;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChatMessageDto {
    // 채팅방 ID
    private String chatRoomId;
    // 주문 ID
    private String orderId;
    // 채팅방 Image
    private Object avatarImage;
    // 채팅방 명
    private String name;
    // 채팅방 상태
    private String status;
    // 채팅방 참여자 개별 사용자 read정보?
    // read 정보
    // String ObjectId
    private Map<String, String> participant;
    // 최근 전달받은 메시지ID & 메시지
    // String
    private String messageId;
    private String message;
    // 전달한 User
    private String userId;
    // 추가 unread count ?


    @Builder
    public ChatMessageDto(String chatRoomId,
                           Object avatarImage,
                           String name,
                           String orderId,
                           String status,
                           Map<String, String> participant,
                           String messageId,
                           String userId,
                           String message) {
        this.chatRoomId = chatRoomId;
        this.avatarImage = avatarImage;
        this.orderId = orderId;
        this.name = name;
        this.status = status;
        this.participant = participant;
        this.messageId = messageId;
        this.userId = userId;
        this.message = message;
    }

    public ChatMessage toChatMessage() {
        return ChatMessage.builder()
//                .chatRoom(new ObjectId(chatRoomId))
                .chatRoom(chatRoomId)
                .sender(userId)
                .message(message)
                .build();
    }

    public void copyChatRoom(ChatRoom chatRoom) {
        this.chatRoomId = chatRoom.getCode();
        this.participant = chatRoom.getParticipant();
        this.avatarImage = chatRoom.getAvatarImage();
        this.orderId = chatRoom.getOrderCode();
        this.name = chatRoom.getName();
    }

    public void copyChatMessage(ChatMessage chatMessage) {
        if (this.participant == null) ;  //error
        this.participant.put(chatMessage.getSender(), chatMessage.getId());
        this.messageId = chatMessage.getId();
        this.chatRoomId = chatMessage.getChatRoom();
        this.userId = chatMessage.getSender();
        this.message = chatMessage.getMessage();
    }

    public void copyLastMessage(ChatMessage chatMessage) {
        this.messageId = chatMessage.getId();
        this.chatRoomId = chatMessage.getChatRoom();
        this.userId = chatMessage.getSender();
        this.message = chatMessage.getMessage();
    }

    private Map<String, String> converMap(Map<String, ObjectId> participant) {
        Iterator<String> iterator = participant.keySet().iterator();
        Map<String, String> map = new HashMap<>();
        while(iterator.hasNext()) {
            String key = iterator.next();
            map.put(key, participant.get(key).toString());
        }
        return map;
    }
}
