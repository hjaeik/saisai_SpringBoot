package com.saisai.web.auth.api;

import com.saisai.web.auth.repo.UserMongoRepo;
import com.saisai.web.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
//    private final UserRepository userRepository;
    private final UserMongoRepo userMongoRepo;

    public User getUser(String userId) {
        return userMongoRepo.findByUserId(userId);
    }
}
