package xyz.tostring.cloud.errands.service.user.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xyz.tostring.cloud.errands.common.dto.BaseResult;
import xyz.tostring.cloud.errands.service.user.config.WXCosnt;
import xyz.tostring.cloud.errands.service.user.entity.UserDO;
import xyz.tostring.cloud.errands.service.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @CrossOrigin
    @PostMapping
    public BaseResult createUser(@RequestBody UserDO userDO) {
        BaseResult baseResult = new BaseResult();
        userService.createUser(userDO);
        return baseResult.ok();
    }

    @CrossOrigin
    @PutMapping
    public BaseResult updateUser(@RequestBody UserDO userDO) {
        BaseResult baseResult = new BaseResult();
        userDO = userService.updateUser(userDO);
        return baseResult.ok(userDO);
    }

    @CrossOrigin
    @DeleteMapping
    public BaseResult deleteUser(@RequestBody UserDO userDO) {
        BaseResult baseResult = new BaseResult();
        userService.deleteUser(userDO);
        return baseResult.ok();
    }

    @CrossOrigin
    @GetMapping("/count")
    public BaseResult allCount() {
        BaseResult baseResult = new BaseResult();
        String count = userService.allCount();
        return baseResult.ok(count);
    }

    @CrossOrigin
    @GetMapping("/getOpenId")
    public BaseResult getUserOpenId(String jsCode) {
        Map<String, String> uriVariables = new HashMap<>();
        BaseResult baseResult = new BaseResult();
        uriVariables.put("appid", WXCosnt.APP_ID);
        uriVariables.put("secret", WXCosnt.APP_SECRET);
        uriVariables.put("js_code", jsCode);
        uriVariables.put("grant_type", WXCosnt.GRANT_TYPE);
        Map result = JSON.parseObject(restTemplate.getForObject(WXCosnt.WX_GET_OPENID_URL, String.class, uriVariables));
        return baseResult.ok(result);
    }
}
