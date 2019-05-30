package com.miujike.worksservice.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.miujike.worksservice.domain.Music;
import com.miujike.worksservice.service.IMusicService;
import com.miujike.worksservice.vo.JqgridParam;
import com.miujike.worksservice.vo.PageResults;
import com.miujike.worksservice.vo.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Ls J
 * @date 2019/5/31 12:27 AM
 */
@RestController
@RequestMapping("api/v1/works/admin/music")
public class TheMusicController {
    @Autowired
    private IMusicService musicService;

    @RequestMapping("getList")
    public PageResults<Music> getList(JqgridParam jqgridParam){
        PageResults<Music> pageResults = new PageResults<>();
        Page<Music> page = new Page<>(jqgridParam.getPage(), jqgridParam.getRows());
        page.setSearchCount(true);
        QueryWrapper<Music> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("music_id");
        int records = musicService.count();
        IPage<Music> selectMapsPage = musicService.page(page, wrapper);
        pageResults.setPage(jqgridParam.getPage());
        pageResults.setRows(jqgridParam.getRows());
        pageResults.setRecords(records);
        pageResults.resetNextPage();
        pageResults.setResults(selectMapsPage.getRecords());
        return pageResults;
    }

    /**
     * 下线/上线
     * @param videoId
     * @return
     */
    @RequestMapping("changeStatus")
    public ResponseData changeStatus(long videoId){
        Music music = musicService.getById(videoId);
        if(music.getStatus() == 0){
            music.setStatus(1);
            music.setReleaseTime(new Date());
        }else{
            music.setStatus(0);
        }

        musicService.updateById(music);
        return new ResponseData("success");
    }
}
