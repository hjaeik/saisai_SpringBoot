package com.saisai.web.fcm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import com.saisai.web.chat.ChatMessageDto;
import com.saisai.web.chat.ChatMongoRepo;
import com.saisai.web.user.UserSideInfo;
import com.saisai.web.user.UserSideInfoMongoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FCMService {
    @Value("${app.firebase.firebase-create-scoped}")
    String fireBaseCreateScoped;

    private FirebaseMessaging instance;
    final private UserSideInfoMongoRepo userSideInfoMongoRepo;

    // Exception 수정할 것
    // chat send시
    public void sendWithoutSender(ChatMessageDto chatMessageDto, String sender) throws FirebaseMessagingException {
        Set<String> participant = chatMessageDto.getParticipant().keySet();
        participant.remove(sender);
        List<UserSideInfo> userSideInfoList = userSideInfoMongoRepo.getUserSideInfoList(participant);

        Map<String, String> fcmMessage = FCMMessage.builder()
                .channelId("chat-notification")
                .subtitle(chatMessageDto.getName())
                .title(chatMessageDto.getUserId())
                .body(chatMessageDto.getMessage())
                .tag(chatMessageDto.getChatRoomId().toString())
                .timeStamp(Long.toString(LocalDateTime.now().toInstant(ZoneOffset.ofTotalSeconds(0)).toEpochMilli()))
                .build()
                .toMap();
        for(UserSideInfo userSideInfo: userSideInfoList) {
            Message msg = Message.builder().setToken(userSideInfo.getFcmToken()).putAllData(fcmMessage).build();
            sendMessage(msg);
        }
    }

    public void sendTargetMessage(String targetToken, String title, String body) throws FirebaseMessagingException {
        this.sendTargetMessage(targetToken, title, body, null);
    }
    public void sendTargetMessage(String targetToken, String title, String body, String image) throws FirebaseMessagingException {
        Notification notification = Notification.builder().setTitle(title).setBody(body).setImage(image).build();
        Message msg = Message.builder().setToken(targetToken).setNotification(notification).build();
        sendMessage(msg);
    }

    public void sendTopicMessage(String topic, String title, String body) throws FirebaseMessagingException {
        this.sendTopicMessage(topic, title, body, null);
    }
    public void sendTopicMessage(String topic, String title, String body, String image) throws FirebaseMessagingException {
        Notification notification = Notification.builder().setTitle(title).setBody(body).setImage(image).build();
        Message msg = Message.builder().setTopic(topic).setNotification(notification).build();
        sendMessage(msg);
    }

    public String sendMessage(Message message) throws FirebaseMessagingException {
        return this.instance.send(message);
    }

    @PostConstruct
    public void firebaseSetting() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("firebase-admin-sdk.json").getInputStream())
                .createScoped((Arrays.asList(fireBaseCreateScoped)));

        FirebaseOptions secondaryAppConfig = FirebaseOptions.builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(secondaryAppConfig);
        this.instance = FirebaseMessaging.getInstance(app);
    }

    public BatchResponse sendMessage(MulticastMessage message) throws FirebaseMessagingException {
        return this.instance.sendMulticast(message);
    }
}
