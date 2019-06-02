package com.miujike.worksservice.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miujike.worksservice.domain.Video;
import com.miujike.worksservice.service.IVideoService;
import com.miujike.worksservice.vo.JqgridParam;
import com.miujike.worksservice.vo.PageResults;
import com.miujike.worksservice.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/5/31 12:41 AM
 */
@RestController
@RequestMapping("api/v1/works/admin/official")
public class OfficialVideoController {

    @Autowired
    private IVideoService videoService;

    @RequestMapping("getList")
    public PageResults<Video> getList(JqgridParam jqgridParam){
        PageResults<Video> pageResults = new PageResults<>();
        Page<Video> page = new Page<>(jqgridParam.getPage(), jqgridParam.getRows());
        page.setSearchCount(true);
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("video_id").eq("user_id",10000000000L);
        int records = videoService.count();
        IPage<Video> selectMapsPage = videoService.page(page, wrapper);
        pageResults.setPage(jqgridParam.getPage());
        pageResults.setRows(jqgridParam.getRows());
        pageResults.setRecords(records);
        pageResults.resetNextPage();
        pageResults.setResults(selectMapsPage.getRecords());
        return pageResults;
    }

    /**
     * 删除
     * @param videoId
     * @return
     */
    @RequestMapping("toDelete")
    public ResponseData toDelete(long videoId){
        videoService.removeById(videoId);
        return new ResponseData("success");
    }

    /**
     * 上传新视频
     * @param video
     * @return
     */
    @RequestMapping("addNewVideo")
    public ResponseData addNewVideo(Video video){
        System.out.println(video.toString());
        Date now = new Date();
        video.setReleaseTime(now);
        video.setCreateTime(now);
        video.setUserId(10000000000L);
        video.setDuration(video.getDuration() * 1000);
        videoService.save(video);
        return new ResponseData("success");
    }

}
