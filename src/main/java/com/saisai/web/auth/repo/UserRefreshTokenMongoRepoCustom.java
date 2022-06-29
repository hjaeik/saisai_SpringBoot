package com.saisai.web.auth.repo;


import com.saisai.web.auth.api.UserRefreshToken;

public interface UserRefreshTokenMongoRepoCustom {
    UserRefreshToken findByUserId(String userId);
    UserRefreshToken findByUserIdAndRefreshToken(String userId, String refreshToken);
}
