package xyz.tostring.cloud.errands.service.user.service;

import xyz.tostring.cloud.errands.service.user.entity.AdminDO;

public interface AdminService {
    AdminDO login(String userName, String password);
    AdminDO getByUserName(String userName);
    void createAdmin(AdminDO adminDO);
    void updateAdmin(AdminDO adminDO);
    void deleteAdmin(Long id);
    void frozenAdmin(Long id);
    void unfreezingAdmin(Long id);
}
