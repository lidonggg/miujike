package com.miujike.common.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Ls J
 * @date 2019/4/17 3:37 PM
 */
public class QiNiuUtil {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(QiNiuUtil.class);

    private static Auth auth = null;
    private static Configuration cfg = null;
    private static UploadManager uploadManager = null;

    private static final String BUCKET = "miujike";
    /**
     * Access Key
     */
    private static final String AK = "q-bcEfr88aziG3OOQ1UN2k7-zkAp-CBE5mQBBJi3";
    /**
     * Secret Key
     */
    private static final String SK = "PYRiFlVpJ_-F-Dv71tR__8zlbgG0GynmKUUpGnkW";
    /**
     * 默认token有效期30天
     */
    private static long tokenExpireTime = 3600 * 24 * 30;
    /**
     * 上次获取token的时间
     */
    private static long getTokenTime = 0;

    /**
     * 视频文件的封面图
     */
    public static String coverParam = "?vframe/png/offset/1/w/640/h/360";

    private static String mUploadToken = "";
    static {
        try {
            auth = Auth.create(AK, SK);
            //构造一个带指定Zone对象的配置类
            cfg = new Configuration(Zone.zone0());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据文件后缀名，生成唯一key
     * @param suffix
     * @return
     */
    public static String generatKey(String suffix) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-");
        //获取要保存的根路径
        String subPath = sdf.format(new Date());
        //拼接路径
        String relationPath = subPath + UUID.randomUUID().toString();
        if (StringUtils.isBlank(suffix)) {
            relationPath = relationPath + ".jpg";
        } else {
            relationPath = relationPath + "." + suffix;
        }
        return relationPath;
    }

    /**
     *  简单上传，使用默认策略
     *  生成上传token
     * @return
     */
    public static String getUpToken() {
        //当前时间
        long now = System.currentTimeMillis();
        if (com.qiniu.util.StringUtils.isNullOrEmpty(mUploadToken) || (now - getTokenTime > tokenExpireTime * 1000)) {
            getTokenTime = System.currentTimeMillis();
            mUploadToken = auth.uploadToken(BUCKET, null, 3600 * 24 * 31, null, true);
            log.debug("生成新的七牛上传token： " + mUploadToken);
        }
        return mUploadToken;
    }

    public static void main(String[] args) {
        File file = new File("/Users/ykq/elephant/题库/每日一题190224/");
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getName());
            System.out.println(uploadFile("daily_subject_" + file1.getName(), file1));
        }
    }

    /**
     * 上传文件
     * @param file
     */
    public static boolean uploadFile(String key, String file) {
        if (uploadManager == null) {
            uploadManager = new UploadManager(cfg);
        }
        try {
            Response res = uploadManager.put(file, key, getUpToken());
            if (res.isOK()) {
                return true;
            } else {
                return false;
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean uploadFile(byte[] bytes, String key) {
        if (uploadManager == null) {
            uploadManager = new UploadManager(cfg);
        }
        try {
            Response res = uploadManager.put(bytes, key, getUpToken());
            if (res.isOK()) {
                return true;
            } else {
                return false;
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key和File上传到七牛
     * @param key
     * @param file
     * @return
     */
    public static boolean uploadFile(String key, File file) {
        log.debug("key======" + key);
        if (uploadManager == null) {
            uploadManager = new UploadManager(cfg);
        }
        try {
            Response res = uploadManager.put(file, key, getUpToken());
            log.debug("上传七牛" + res.isOK());
            if (res.isOK()) {
                return true;
            } else {
                return false;
            }
        } catch (QiniuException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key和网络资源路径上传文件到七牛
     * @param key
     * @param networkPath
     * @return
     * @throws IOException
     */
    public static boolean uploadComeNetwork(String key, String networkPath) throws IOException {
        //new一个URL对象
        URL url = new URL(networkPath);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();

        boolean flag = uploadFileQiNiu(key, inStream);

        return flag;

    }

    /**
     * 上传图片到七牛
     * @param key
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static boolean uploadFileQiNiu(String key, InputStream inputStream) throws IOException {
        if (uploadManager == null) {
            uploadManager = new UploadManager(cfg);
        }
        try {

            byte[] bytes = toByteArray(inputStream);
            Response res = uploadManager.put(bytes, key, getUpToken());
            return true;
        } catch (QiniuException e) {
            log.debug("上传七牛失败！====>" + e.toString());
            // 请求失败时简单状态信息
            return false;
        }
    }

    /**
     * InputStream转byte[]
     * @param input
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

}
