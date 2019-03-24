package xyz.tostring.cloud.errands.service.user.service;

import xyz.tostring.cloud.errands.service.user.entity.UserReceiveInfoDO;

import java.util.List;

public interface UserReceiveInfoService {
    UserReceiveInfoDO createReceiveInfo(UserReceiveInfoDO userReceiveInfoDO);
    UserReceiveInfoDO updateReceiveInfo(UserReceiveInfoDO userReceiveInfoDO);
    UserReceiveInfoDO setAsDefaultReceiveInfo(UserReceiveInfoDO userReceiveInfoDO);
    UserReceiveInfoDO deleteReceiveInfo(UserReceiveInfoDO userReceiveInfoDO);
    List<UserReceiveInfoDO> listAll();
    List<UserReceiveInfoDO> listAllByUserOpenId(String userOpenId);
    UserReceiveInfoDO getById(Long id);
}
