package com.miujike.userservice.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ls J
 * @date 2019/1/9 12:41 PM
 */
public class WxUtil {
    /**
     * 微信服务号appid
     */
    public final static String WE_CHAT_APP_ID = "wxaac0f6f90640d353";

    public final static String WE_CHAT_APP_SECRET = "54937a2bc6fbc1a28ce1e34d93a30d5f";

    private final static String GRANT_TYPE = "authorization_code";

    private final static String GET_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";

    public static Map<String, String> APP_ID_MAP;

    public static Map<String, String> APP_SECRET_MAP;

    static {
        APP_ID_MAP = new HashMap<>();
        APP_ID_MAP.put("miujike-app", "wxa71eb2257a0ef099");

        APP_SECRET_MAP = new HashMap<>();
        APP_SECRET_MAP.put("miujike-app", "29109ca5d1400d92db856fe0ea975e4b");
    }

    public static String getSessionKey(String jsCode, String tapp) {

        String surl = GET_SESSION_URL
                + "?appid=" + APP_ID_MAP.get(tapp)
                + "&secret=" + APP_SECRET_MAP.get(tapp)
                + "&js_code=" + jsCode
                + "&grant_type=" + GRANT_TYPE;
        try {
            return HttpUtil.sendHttpRequestGet(surl);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static JSONObject decodeUserInfo(String encryptedData, String iv, String code, String tapp) {
        JSONObject jsonObject = JSONObject.parseObject(getSessionKey(code, tapp));
        System.out.println(jsonObject);
        try {
            if (jsonObject.containsKey("session_key")) {
                String sessionKey = jsonObject.get("session_key").toString();
                String result = AesUtil.decrypt(encryptedData, sessionKey, iv, "UTF-8");
                if (null != result && result.length() > 0) {
                    return JSONObject.parseObject(result);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//orkqp5Un782K-2nPrT-rvXJIG6V0
        return null;
    }

    /**
     * 网页授权获取access_token
     *
     * @return
     */
    public static Map<String,String> getAccessTokenOfH5(String code) {
        Map<String,String> map = new HashMap<>();
        String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WE_CHAT_APP_ID
                + "&secret=" + WE_CHAT_APP_SECRET
                + "&code=" + code
                + "&grant_type=authorization_code";

        String res = HttpUtil.sendHttpRequestGet(urlStr);
        JSONObject jsonObject = JSONObject.parseObject(res);
        if (jsonObject.containsKey("access_token")) {
            map.put("accessToken",jsonObject.getString("access_token"));
            map.put("openId",jsonObject.getString("openId"));
            return map;
        } else {
            return null;
        }
    }

    /**
     * 刷新网页授权的access_token
     *
     * @return
     */
    public static String refreshAccessTokenOfH5() {
        return "";
    }

    /**
     * 通过网页授权获取用户信息
     * 需scope为 snsapi_userinfo
     *
     * @return
     */
    public static JSONObject getUserInfoOfH5(String accessToken, String openId) {
        String urlStr = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + accessToken
                + "&openid=" + openId + "&lang=zh_CN";
        String res = HttpUtil.sendHttpRequestGet(urlStr);

        return JSONObject.parseObject(res);
    }

    public static void main(String[] args) {

        String ls = "['a']";
        List<String> l = Arrays.asList(ls.substring(1, ls.length() - 1).split(","));
        System.out.println(l);
        System.out.println(l.size());
    }
}
