package com.saisai.web.chat;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MassChatMessageDto {
    private String id;
    private String chatRoom;
    private String userId;
    private List<String> message;
    private Map<String, ObjectId> participant;
    private String status;

    @Builder
    public MassChatMessageDto(String chatRoom, String id, String userId, List<String> message, Map<String, ObjectId> participant, String status) {
        this.chatRoom = chatRoom;
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.participant = participant;
        this.status = status;
    }

    public void copyChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom.getCode();
    }
}
