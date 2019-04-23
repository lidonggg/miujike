package com.miujike.worksservice.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.miujike.common.util.QiNiuUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Ls J
 * @date 2019/4/22 9:47 AM
 */
@Controller
@RequestMapping("/api/v1/upload")
public class UploadController {
    private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

    /**
     * 七牛域名
     */
    private final String QINIU_BASE_PATH = "http://pq3gqpelo.bkt.clouddn.com/";

    /**
     * 上传文件
     *
     * @return
     */
    @ResponseBody
    @PostMapping("/uploadInMini")
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile uploadFile) {
        Map<String, Object> map = new HashMap<>();
        // 上传是否成功的判断标识，0-成功，1-失败
        String url = saveUploadFile(uploadFile, uploadFile.getOriginalFilename());
        log.info("url=======>" + QINIU_BASE_PATH + url);
        map.put("url", QINIU_BASE_PATH + url);
        map.put("error", 0);
        return map;
    }

    /**
     * layui方式上传文件返回
     *
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadLayuiFile")
    public String uploadLayuiFile(@RequestParam("file") MultipartFile file) {
        String url = saveUploadFile(file, file.getOriginalFilename());
        log.debug("url=======>" + QINIU_BASE_PATH + url);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        //提示消息
        map.put("msg", "");
        //0表示成功，1失败
        map.put("code", 0);
        //图片url
        map2.put("src", QINIU_BASE_PATH + url);
        //图片名称，这个会显示在输入框里
        map2.put("title", url);
        map.put("data", map2);
        String result = new JSONObject(map).toString();
        System.out.println(result);
        return result;
    }

    /**
     * 获取上传token
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getToken")
    public Map<String, Object> getToken() {
        Map<String, Object> map = new HashMap<>();

        map.put("code", 0);
        map.put("uptoken", QiNiuUtil.getUpToken());

        return map;
    }

    /**
     * 保存上传的文件到服务器，并返回文件在服务器端的真实存储路径
     *
     * @param upload              上传的文件
     * @param attachmentsFileName 原始文件名
     * @return 保存的地址
     * @throws IOException
     * @throws IllegalStateException
     */
    private String saveUploadFile(MultipartFile upload, String attachmentsFileName) throws IllegalStateException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-");
        // 获取要保存的根路径
        // System.out.println("bathPath====="+bathPath);
        String subPath = sdf.format(new Date());
        // 如果文件不存在就创建
        // 截取原始文件名，获取后缀名
        String fileType = StringUtils.substringAfterLast(attachmentsFileName, ".");
        // 拼接路径
        String relationPath = subPath + UUID.randomUUID().toString() + "." + fileType;
        String returnUrl = "";
        try {
            QiNiuUtil.uploadFileQiNiu(relationPath, upload.getInputStream());
            returnUrl = relationPath;
        } catch (IOException e) {
            log.error("七牛上传失败====》", e.toString());
            e.printStackTrace();
        }
        return returnUrl;
    }

    protected String saveUploadFileNotChangeName(MultipartFile upload, String attachmentsFileName) throws IllegalStateException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-");
        String subPath = sdf.format(new Date());
        // 如果文件不存在就创建
        // 截取原始文件名，获取后缀名
        String fileType = StringUtils.substringAfterLast(attachmentsFileName, ".");
        // 拼接路径
        String relationPath = subPath + attachmentsFileName;
        String returnUrl = "";
        try {
            QiNiuUtil.uploadFileQiNiu(relationPath, upload.getInputStream());
            returnUrl = relationPath;
        } catch (IOException e) {
            log.error("七牛上传失败====》", e.toString());
            e.printStackTrace();
        }
        return returnUrl;
    }
}
