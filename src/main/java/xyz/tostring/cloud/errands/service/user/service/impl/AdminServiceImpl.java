package xyz.tostring.cloud.errands.service.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.tostring.cloud.errands.common.service.util.SnowflakeIdWorker;
import xyz.tostring.cloud.errands.service.user.entity.AdminDO;
import xyz.tostring.cloud.errands.service.user.repository.AdminRepository;
import xyz.tostring.cloud.errands.service.user.service.AdminService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminRepository adminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Override
    public AdminDO login(String userName, String password) {
        AdminDO adminDO = adminRepository.findAdminDOByUserName(userName);
        if (null != adminDO && bCryptPasswordEncoder.matches(password, adminDO.getPassword())) {
            return adminDO;
        }
        return null;
    }

    @Override
    public AdminDO getByUserName(String userName) {
        return adminRepository.findAdminDOByUserName(userName);
    }

    @Override
    public void createAdmin(AdminDO adminDO) {
        Date date = new Date();
        adminDO.setId(snowflakeIdWorker.nextId());
        adminDO.setPassword(bCryptPasswordEncoder.encode(adminDO.getPassword()));
        adminDO.setCreatedTime(date);
        adminDO.setUpdatedTime(date);
        adminDO.setFrozen(0);
        adminRepository.save(adminDO);
    }

    @Override
    public void updateAdmin(AdminDO adminDO) {
        Date date = new Date();
        adminDO.setUpdatedTime(date);
        adminRepository.save(adminDO);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Override
    public void frozenAdmin(Long id) {
        Optional<AdminDO> byId = adminRepository.findById(id);
        if (byId.isPresent()) {
            AdminDO adminDO = byId.get();
            adminDO.setFrozen(1);
            adminRepository.save(adminDO);
        }
    }

    @Override
    public void unfreezingAdmin(Long id) {
        Optional<AdminDO> byId = adminRepository.findById(id);
        if (byId.isPresent()) {
            AdminDO adminDO = byId.get();
            adminDO.setFrozen(0);
            adminRepository.save(adminDO);
        }
    }
}
