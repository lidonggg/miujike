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
 * @date 2019/5/30 10:27 PM
 * 后台video控制器
 */
@RestController
@RequestMapping("api/v1/works/admin/video")
public class TheVideoController {

    @Autowired
    private IVideoService videoService;

    @RequestMapping("getList")
    public PageResults<Video> getList(JqgridParam jqgridParam){
        PageResults<Video> pageResults = new PageResults<>();
        Page<Video> page = new Page<>(jqgridParam.getPage(), jqgridParam.getRows());
        page.setSearchCount(true);
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("video_id");
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
     * 下线/上线视频
     * @param videoId
     * @return
     */
    @RequestMapping("changeStatus")
    public ResponseData changeStatus(long videoId){
        Video video = videoService.getById(videoId);
        if(video.getStatus() == 0){
            video.setStatus(1);
            video.setReleaseTime(new Date());
        }else{
            video.setStatus(0);
        }

        videoService.updateById(video);
        return new ResponseData("success");
    }

}
