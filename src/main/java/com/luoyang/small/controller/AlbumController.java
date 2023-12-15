package com.luoyang.small.controller;

import com.luoyang.small.ex.CustomServiceException;
import com.luoyang.small.pojo.dto.AlbumAddNewDTO;
import com.luoyang.small.pojo.entity.Album;
import com.luoyang.small.service.IAlbumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 相册web控制器
 *
 * @author luoyang
 * @Date 2023/12/13
 */
@Slf4j
@RequestMapping("/album")
@RestController
public class AlbumController {

    /**
     * 不建议声明为具体的实现类，那样不利于代码“解耦”！
     */
    @Autowired
    private IAlbumService albumService;

    //直接网络请求添加
    //http://localhost:8080/album/add?name=TestAlbum001&description=TestDescription001&sort=88
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addNewAlbum(AlbumAddNewDTO albumAddNewDTO) {
        try {
            albumService.addNew(albumAddNewDTO);
            return "添加相册成功Ya!";
        } catch (CustomServiceException e) {
            String message = e.getMessage();
            log.error("addNewAlbum Exception {}", message);
            return message;
        }
    }

    //直接网络请求删除
    //http://localhost:8080/album/delete?name=TestAlbum001&description=TestDescription001&sort=88
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteAlbum(AlbumAddNewDTO albumAddNewDTO) {
        if (albumAddNewDTO == null) {
            return "删除对象为空";
        }
        String name = albumAddNewDTO.getName();
        if (name == null || name.isEmpty()) {
            return "删除相册的名称为空";
        }
        try {
            albumService.deleteAlbum(name);
            return name + "相册，删除成功Ya!";
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("deleteAlbum Exception {}", message);
            return message;
        }
    }

    //直接网络请求更新
    //http://localhost:8080/album/update?name=TestAlbum001&description=TestDescription001&sort=88
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String updateAlbum(AlbumAddNewDTO albumAddNewDTO) {
        if (albumAddNewDTO == null) {
            return "更新对象为空";
        }
        String name = albumAddNewDTO.getName();
        if (name == null || name.isEmpty()) {
            return "更新相册的名称为空";
        }
        try {
            albumService.updateAlbum(albumAddNewDTO);
            return name + "相册，更新成功Ya!";
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("updateAlbum Exception {}", message);
            return message;
        }
    }

    //直接网络请求更新
    //http://localhost:8080/album/selectAll
    @RequestMapping(value = {"selectAll","fd"}, method = RequestMethod.GET)
    public List<Album> selectAllAlbum() {
        List<Album> albumList = null;
        try {
            albumList = albumService.selectAllAlbum();
//            return "查询全部成功Ya! 所有相册："+albumList.toString();
        } catch (Exception e) {
            String message = e.getMessage();
            log.error("selectAllAlbum Exception {}", message);
//            return message;
        }
        return albumList;
    }

}
