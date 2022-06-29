package com.saisai.web.chat;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter
@Setter // 임시
@NoArgsConstructor
@Document(value = "ChatMessage")
@ToString
public class ChatMessage {
    private String chatRoom;
    private String id;        // 순번
    private String sender;
    private String message;

    @Builder
    public ChatMessage(String chatRoom, String sender, String message, String id) {
         this.chatRoom = chatRoom;
         this.id = id;
         this.sender = sender;
         this.message = message;
    }
}
