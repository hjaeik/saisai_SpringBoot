package com.saisai.web.chat;

import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.*;

@RequiredArgsConstructor
public class ChatMongoRepoImpl implements ChatMongoRepoCustom {
    private final MongoTemplate mongoTemplate;
    private final ObjectId minimum = new ObjectId(new Date(2020,01,01));

    //    해당 사용자(UserId)가 존재하는 모든 채팅방의 정보를 조회한다.
    //    @Request    ChatMessageDto.class
    //                  userId, chatRoomId
    //    @Response   ChatRoom.class
    //    @exception  [Not Updated]
    @Override
    public List<ChatRoom> findAllChatRoom(String userId) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(Criteria.where("participant." + userId).exists(true));
        query.addCriteria(Criteria.where("").andOperator(criteriaList));
        return mongoTemplate.find(query, ChatRoom.class);
    }

    //    해당 사용자(UserId)가 해당 채팅방(ChatRoomId)에 있는지 확인합니다.
    //    @Request    ChatMessageDto.class
    //                  userId, chatRoomId
    //    @Response   ChatRoom.class
    //    @exception  [Not Updated]
    @Override
    public ChatRoom findRoom(ChatMessageDto chatMessageDto) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(Criteria.where("_id").is(chatMessageDto.getChatRoomId()));
        //  criteriaList.add(Criteria.where("status").is(chatMessageDto.getStatus()));
        criteriaList.add(Criteria.where("participant." + chatMessageDto.getUserId()).gte(minimum));
        query.addCriteria(Criteria.where("").andOperator(criteriaList));
        return mongoTemplate.findOne(query, ChatRoom.class);
    }

    // 채팅 방들의 가장 최근 메시지를 가져옴
    // 방법1 채팅방id를 비굑하여 가장 최근 메시지 가져옴 [선호됨]
    // 방법2 participantID들의 objectID를 비교하여 가져올 메시지ID의 최근 메시지를 가져온다.
    @Override
    public List<ChatMessage> findAllRecentMessage(List<String> listLastMessageId) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        for(String objectId : listLastMessageId) {
            criteriaList.add(Criteria.where("_id").is(objectId));
        }
        return mongoTemplate.find(query.addCriteria(Criteria.where("").andOperator(criteriaList)), ChatMessage.class);
    }

    @Override
    public List<ChatMessage> findMessages(ChatMessageDto chatRoomDto, String lastRead) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(Criteria.where("chatRoom").is(chatRoomDto.getChatRoomId()));
        criteriaList.add(Criteria.where("_id").gt(lastRead));
        return mongoTemplate.find(query.addCriteria(Criteria.where("").andOperator(criteriaList)), ChatMessage.class);
    }

    @Override
    public ChatMessage insertMessage(ChatMessage message) { return mongoTemplate.insert(message);}

    //    해당 채팅방(ChatRoomId)의 사용자가 읽은 메시지 정보를 업데이트 합니다.()
    //    채팅 사용자
    //    @Request  ChatMessage.class
    //              userId, chatRoomId
    //    @Response ChatRoom.class
    //    @exception    [Not Updated]
    @Override
    public boolean updateRead(ChatMessage chatMessage) {
        Query query = new Query();
        StringBuilder sb = new StringBuilder("");
        Update update = new Update();
        List<Criteria> criteriaList = new ArrayList<>();
        update.set(sb.append("participant.").append(chatMessage.getSender()).toString(), chatMessage.getId());
        criteriaList.add(Criteria.where("_id").is(chatMessage.getChatRoom()));
        criteriaList.add(Criteria.where("participant."+chatMessage.getSender()).gte(minimum));
        query.addCriteria(Criteria.where("").andOperator(criteriaList));
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, ChatRoom.class);
        return updateResult.wasAcknowledged();
    }

}
