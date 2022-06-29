package com.saisai.web.user;

import java.util.List;
import java.util.Set;

public interface UserSideInfoMongoRepoCustom {
    // fcm 정보조회
    List<UserSideInfo> getUserSideInfo(User user);

    List<UserSideInfo> getUserSideInfoList(Set<String> users);
}
