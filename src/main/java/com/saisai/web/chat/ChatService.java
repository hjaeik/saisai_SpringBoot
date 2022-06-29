package com.saisai.web.chat;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ChatService {
    final private ChatMongoRepo chatMongoRepo;

    // 초기 정보 수령
    // findChatRoomsByUser - status, user 정보 포함된 값 조회
    // findMessageByInfo -
    // @Request : UserID, List<RoomId, lastRead>
    // @Response : List<RoomInfo, List<Message>>
    // @exception    [Not Updated]


    // 초기 정보 수령(채팅방 정보)
    // 해당 기기에서 가지고 있는 lastindex 전달
    public List<ChatMessageDto> initConnection(String userId) {
        // 해당 id가 속해 있는 모든 채팅방
        List<ChatRoom> chatRoomList = chatMongoRepo.findAllChatRoom(userId);
        Map<String, ChatMessageDto> chatMessageDtoMap = new HashMap<>();
        List<ChatMessageDto> chatMessageDtoList = new ArrayList<>();
        List<String> lastMessageIdList = new ArrayList<>();

        // 채팅방별 마지막 메시지
        for(ChatRoom chatRoom: chatRoomList) {
            Map<String, String> participants = chatRoom.getParticipant();
            ChatMessageDto chatMessageDto = new ChatMessageDto();
            chatMessageDto.copyChatRoom(chatRoom);
            String participant = participants.get(userId);
            Iterator<String> iterator = participants.keySet().iterator();
            while(iterator.hasNext()) {
                String key = iterator.next();
                String value = participants.get(key);
                if(participant.compareTo(value) < 0) participant = value;
            }
            lastMessageIdList.add(participant);
            chatMessageDtoMap.put(chatRoom.getCode(), chatMessageDto);
        }
        List<ChatMessage> listChatMessage = chatMongoRepo.findAllRecentMessage(lastMessageIdList);
        for(ChatMessage chatMessage: listChatMessage) {
            ChatMessageDto chatMessageDto = chatMessageDtoMap.get(chatMessage.getChatRoom());
            chatMessageDto.copyLastMessage(chatMessage);
            chatMessageDtoList.add(chatMessageDto);
        }
        return chatMessageDtoList;
    }

    // 1.1 채팅방 정보
    public List<ChatMessage> initChatRoom(ChatMessageDto chatMessageDto, String userId) {
        // 채팅룸 확인
        ChatRoom chatRoom = chatMongoRepo.findRoom(chatMessageDto);
        chatMessageDto.copyChatRoom(chatRoom);
        return chatMongoRepo.findMessages(chatMessageDto, chatMessageDto.getParticipant().get(userId));
    }

    // 2. 채팅 보내기
    @Transactional
    public ChatMessageDto sendMessage(ChatMessageDto chatMessageDto) {
        // 채팅방 존재여부 확인
        ChatRoom chatRoom = chatMongoRepo.findRoom(chatMessageDto);
        chatMessageDto.copyChatRoom(chatRoom);
        // 채팅 입력
        ChatMessage chatMessage = chatMongoRepo.insertMessage(chatMessageDto.toChatMessage());
        // 채팅방 read 정보 업데이트
        chatMongoRepo.updateRead(chatMessage);
        chatMessageDto.copyChatMessage(chatMessage);

        return chatMessageDto;
    }

    // 3. 채팅 읽기
    // ing
    public ChatRoom readMessage(ChatMessageDto chatMessageDto) {
        // 채팅방에 있는지 확인
        ChatRoom chatRoom = chatMongoRepo.findRoom(chatMessageDto);
        chatMessageDto.copyChatRoom(chatRoom);
        ChatMessage chatMessage = chatMessageDto.toChatMessage();
        chatMongoRepo.updateRead(chatMessage);
        return null;
    }

    // 4. 채팅방 order 제안
    public ChatRoom sendOrder() {return null;}

    // 5. 채팅방 order 수정 제안
    public ChatRoom updateSendOrder() {return null;}

    // 6. 채팅방 order 선택
    public ChatRoom selectOrder() {return null;}

    // 6. 채팅방 order 완료
    public ChatRoom completeOrder() {return null;}


}
