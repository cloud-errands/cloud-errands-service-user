package xyz.tostring.cloud.errands.service.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.tostring.cloud.errands.service.user.entity.UserDO;
import xyz.tostring.cloud.errands.service.user.repository.UserRepository;
import xyz.tostring.cloud.errands.service.user.service.UserService;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(UserDO userDO) {
        Date date = new Date();
        userDO.setCreateTime(date);
        userDO.setUpdateTime(date);
        userRepository.save(userDO);
    }

    @Override
    public UserDO updateUser(UserDO userDO) {
        userDO.setUpdateTime(new Date());
        userRepository.save(userDO);
        return userDO;
    }

    @Override
    public void deleteUser(UserDO userDO) {
        userRepository.delete(userDO);
    }

    @Override
    public String allCount() {
        return String.valueOf(userRepository.count());
    }
}
