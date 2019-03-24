package xyz.tostring.cloud.errands.service.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.tostring.cloud.errands.common.dto.BaseResult;
import xyz.tostring.cloud.errands.service.user.entity.UserReceiveInfoDO;
import xyz.tostring.cloud.errands.service.user.service.UserReceiveInfoService;

import java.util.List;

@RestController
@RequestMapping("/user/receive-info")
public class UserReceiveInfoController {
    @Autowired
    private UserReceiveInfoService userReceiveInfoService;

    @PostMapping
    public BaseResult create(@RequestBody UserReceiveInfoDO userReceiveInfoDO) {
        BaseResult baseResult = new BaseResult();
        userReceiveInfoDO = userReceiveInfoService.createReceiveInfo(userReceiveInfoDO);
        return baseResult.ok(userReceiveInfoDO);
    }

    @PutMapping
    public BaseResult update(@RequestBody UserReceiveInfoDO userReceiveInfoDO) {
        BaseResult baseResult = new BaseResult();
        userReceiveInfoDO = userReceiveInfoService.updateReceiveInfo(userReceiveInfoDO);
        return baseResult.ok(userReceiveInfoDO);
    }

    @DeleteMapping
    public BaseResult delete(@RequestBody UserReceiveInfoDO userReceiveInfoDO) {
        BaseResult baseResult = new BaseResult();
        userReceiveInfoDO = userReceiveInfoService.deleteReceiveInfo(userReceiveInfoDO);
        return baseResult.ok(userReceiveInfoDO);
    }

    @GetMapping("list/{userOpenId}")
    public BaseResult listByUserOpenId(@PathVariable String userOpenId) {
        BaseResult baseResult = new BaseResult();
        List<UserReceiveInfoDO> userReceiveInfoDOList = userReceiveInfoService.listAllByUserOpenId(userOpenId);
        return baseResult.ok(userReceiveInfoDOList);
    }

    @GetMapping("{id}")
    public BaseResult getOne(@PathVariable Long id) {
        BaseResult baseResult = new BaseResult();
        UserReceiveInfoDO userReceiveInfoDO = userReceiveInfoService.getById(id);
        return baseResult.ok(userReceiveInfoDO);
    }

    @PutMapping("as-default")
    public BaseResult setAsDefault(@RequestBody UserReceiveInfoDO userReceiveInfoDO) {
        BaseResult baseResult = new BaseResult();
        userReceiveInfoDO = userReceiveInfoService.setAsDefaultReceiveInfo(userReceiveInfoDO);
        return baseResult.ok(userReceiveInfoDO);
    }

}
