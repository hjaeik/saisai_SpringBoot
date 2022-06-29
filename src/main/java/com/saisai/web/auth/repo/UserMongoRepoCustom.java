package com.saisai.web.auth.repo;

import com.saisai.web.user.User;

public interface UserMongoRepoCustom {
    User findByUserId(String userId);
}
