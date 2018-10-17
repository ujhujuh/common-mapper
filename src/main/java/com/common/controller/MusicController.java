package com.common.controller;

import com.common.entity.Music;
import com.common.mapper.MusicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

/**
 * @author fantasy
 * @date 2018/10/17
 * @time 11:49
 */

@RestController
@RequestMapping("music")
public class MusicController {

    @Autowired
    private MusicMapper musicMapper;

    @RequestMapping("findById")
    public Music findById(@RequestParam("id") Integer id) {
        return musicMapper.selectByPrimaryKey(id);
    }

    @RequestMapping("updateById")
    public int updateById(@RequestParam("id") Integer id) {
        Music music = musicMapper.selectByPrimaryKey(id);
        music.setCreator("XXX");
        music.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return musicMapper.updateByPrimaryKey(music);
    }


}
