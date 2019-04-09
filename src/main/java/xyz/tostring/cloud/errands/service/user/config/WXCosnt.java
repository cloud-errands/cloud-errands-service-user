package xyz.tostring.cloud.errands.service.user.config;

public class WXCosnt {
    public static final String GRANT_TYPE = "authorization_code";
    public static final String APP_ID = "wxd546444ad8aaaf05";
    public static final String APP_SECRET = "e2f6eaecb76a6a8604d447b8ebfe5128";
    public static final String WX_GET_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}";
}
