package com.saisai.web.chat;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

public interface ChatMongoRepoCustom {

    // 채팅방 전체 조회
    List<ChatRoom> findAllChatRoom(String userId);

    // 채팅방 1개 조회
    ChatRoom findRoom(ChatMessageDto chatMessageDto);

    List<ChatMessage> findAllRecentMessage(List<String> listLastMessageId);

    // 채팅방 메시지 조회 (역순, 사용자 번호 기반)
    List<ChatMessage> findMessages(ChatMessageDto chatRoomDto, String lastRead);

    // 채팅방 메시지 저장 ()
    ChatMessage insertMessage(ChatMessage message);

    // 채팅 메시지 조회
    boolean updateRead(ChatMessage chatMessage);

    // 임시
    // 채팅 메시지 조회

}
