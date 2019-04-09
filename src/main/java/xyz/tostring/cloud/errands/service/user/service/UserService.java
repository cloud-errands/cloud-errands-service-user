package xyz.tostring.cloud.errands.service.user.service;

import xyz.tostring.cloud.errands.service.user.entity.UserDO;

public interface UserService {
    void createUser(UserDO userDO);
    UserDO updateUser(UserDO userDO);
    void deleteUser(UserDO userDO);
    String allCount();
}
