package xyz.tostring.cloud.errands.service.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.tostring.cloud.errands.common.service.util.SnowflakeIdWorker;
import xyz.tostring.cloud.errands.service.user.repository.UserReceiveInfoRepository;
import xyz.tostring.cloud.errands.service.user.entity.UserReceiveInfoDO;
import xyz.tostring.cloud.errands.service.user.service.UserReceiveInfoService;

import java.util.Date;
import java.util.List;

@Service
public class UserReceiveInfoServiceImpl implements UserReceiveInfoService {

    public static final int DEFAULT_WEIGHT = 0;
    public static final int WEIGHT_STEP = 5;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private UserReceiveInfoRepository userReceiveInfoRepository;

    @Override
    public UserReceiveInfoDO createReceiveInfo(UserReceiveInfoDO userReceiveInfoDO) {
        Date date = new Date();
        userReceiveInfoDO.setId(snowflakeIdWorker.nextId());
        userReceiveInfoDO.setCreateTime(date);
        userReceiveInfoDO.setUpdateTime(date);
        userReceiveInfoDO.setSortWeight(DEFAULT_WEIGHT);
        userReceiveInfoRepository.save(userReceiveInfoDO);
        return userReceiveInfoDO;
    }

    @Override
    public UserReceiveInfoDO updateReceiveInfo(UserReceiveInfoDO userReceiveInfoDO) {
        Date date = new Date();
        userReceiveInfoDO.setUpdateTime(date);
        userReceiveInfoRepository.save(userReceiveInfoDO);
        return userReceiveInfoDO;
    }

    @Override
    public UserReceiveInfoDO setAsDefaultReceiveInfo(UserReceiveInfoDO userReceiveInfoDO) {
        List<UserReceiveInfoDO> userReceiveInfoDOList = listAllByUserOpenId(userReceiveInfoDO.getUserOpenId());
        userReceiveInfoDO = userReceiveInfoRepository.getOne(userReceiveInfoDO.getId());
        if (userReceiveInfoDOList != null) {
            int currentMaxWeight = userReceiveInfoDOList.get(0).getSortWeight();
            userReceiveInfoDO.setSortWeight(currentMaxWeight + WEIGHT_STEP);
            updateReceiveInfo(userReceiveInfoDO);
            return userReceiveInfoDO;
        } else {
            userReceiveInfoDO.setSortWeight(userReceiveInfoDO.getSortWeight() + WEIGHT_STEP);
            updateReceiveInfo(userReceiveInfoDO);
            return userReceiveInfoDO;
        }
    }

    @Override
    public UserReceiveInfoDO deleteReceiveInfo(UserReceiveInfoDO userReceiveInfoDO) {
        userReceiveInfoRepository.delete(userReceiveInfoDO);
        return userReceiveInfoDO;
    }

    @Override
    public List<UserReceiveInfoDO> listAll() {
        return null;
    }

    @Override
    public List<UserReceiveInfoDO> listAllByUserOpenId(String userOpenId) {
        return userReceiveInfoRepository.findAllByUserOpenIdOrderBySortWeightDescUpdateTimeDesc(userOpenId);
    }

    @Override
    public UserReceiveInfoDO getById(Long id) {
        return userReceiveInfoRepository.findById(id).orElse(null);
    }
}
