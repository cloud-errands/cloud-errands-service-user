package xyz.tostring.cloud.errands.service.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.tostring.cloud.errands.common.dto.BaseResult;
import xyz.tostring.cloud.errands.service.user.entity.AdminDO;
import xyz.tostring.cloud.errands.service.user.service.AdminService;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("login")
    public BaseResult login(@RequestBody AdminDO admin) {
        BaseResult baseResult = new BaseResult();
        BaseResult.Error error1 = new BaseResult.Error();
        BaseResult.Error error2 = new BaseResult.Error();
        error1.setMessage("账号或密码错误");
        error2.setMessage("账号冻结，请联系管理员");
        AdminDO adminDO = adminService.login(admin.getUserName(), admin.getPassword());
        if (adminDO != null) {
            if (1 == adminDO.getFrozen()) {
                List<BaseResult.Error> errors = new ArrayList<>();
                errors.add(error2);
                return baseResult.notOk(errors);
            } else {
                return baseResult.ok(adminDO);
            }
        } else {
            List<BaseResult.Error> errors = new ArrayList<>();
            errors.add(error1);
            return baseResult.notOk(errors);
        }
    }

    @PostMapping
    public BaseResult addAdmin(@RequestBody AdminDO adminDO) {
        BaseResult baseResult = new BaseResult();
        BaseResult.Error error = new BaseResult.Error();
        error.setMessage("用户名重复");
        if (null == adminService.getByUserName(adminDO.getUserName())) {
            adminService.createAdmin(adminDO);
            return baseResult.ok();
        } else {
            List<BaseResult.Error> errors = new ArrayList<>();
            errors.add(error);
            return baseResult.notOk(errors);
        }
    }

    @PutMapping
    public BaseResult updateAdmin(@RequestBody AdminDO adminDO) {
        adminService.updateAdmin(adminDO);
        return new BaseResult().ok();
    }

    @DeleteMapping("{id}")
    public BaseResult deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return new BaseResult().ok();
    }

    @PostMapping("frozen/{id}")
    public BaseResult frozenAdmin(@PathVariable Long id) {
        adminService.frozenAdmin(id);
        return new BaseResult().ok();
    }

    @PostMapping("unfreezing/{id}")
    public BaseResult unfreezingAdmin(@PathVariable Long id) {
        adminService.unfreezingAdmin(id);
        return new BaseResult().ok();
    }

    @GetMapping("list/{page}/{size}")
    public BaseResult listAdmin(@PathVariable Integer page, @PathVariable Integer size) {
        return new BaseResult().ok(adminService.findAllNoCriteria(page, size));
    }
}
