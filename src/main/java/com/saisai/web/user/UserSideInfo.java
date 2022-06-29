package com.saisai.web.user;

import com.saisai.web.auth.oauth2.ProviderType;
import com.saisai.web.auth.oauth2.RoleType;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "UserSideInfo")
@ToString
public class UserSideInfo {

    @Id
    private ObjectId id;
    private String userId;
    private String fcmToken;

    private UserSideInfo(ObjectId id, String userId, String fcmToken) {
        this.id = id;
        this.userId = userId;
        this.fcmToken = fcmToken;
    }
}
