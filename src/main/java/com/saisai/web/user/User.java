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
@Document(collection = "User")
@ToString
public class User {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private ObjectId id;
    private String userId;
    private String username;
    private String password;
    private String email;
    private String emailVerifiedYn;
    private String profileImageUrl;
    private ProviderType providerType;
    private RoleType roleType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder(builderClassName= "social", builderMethodName = "socialBuilder")
    private User(String userId, String username, String password, String email, String emailVerifiedYn, String profileImageUrl, ProviderType providerType, RoleType roleType, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.emailVerifiedYn = emailVerifiedYn;
        this.profileImageUrl = profileImageUrl;
        this.providerType = providerType;
        this.roleType = roleType;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
