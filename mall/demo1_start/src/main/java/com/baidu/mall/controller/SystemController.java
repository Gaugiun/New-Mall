package com.baidu.mall.controller;

import com.baidu.mall.bean.BaseRespVo;
import com.baidu.mall.bean.CskaoyanMallStorage;
import com.baidu.mall.service.SystemStorageServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("admin")
@RestController
public class SystemController {
    /**
     * 对象存储  admin/storage/list
     * page: 1
     * limit: 20
     * key: key    对象KEY
     * name: 010   对象名称
     * sort: add_time
     * order: desc
     */
    @Autowired
    SystemStorageServiceImpl systemStorageServiceImpl;

    @RequestMapping("storage/list")
    public BaseRespVo UserList(Integer page,Integer limit,Integer key,String name){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallStorage> storages = systemStorageServiceImpl.selectStorage(key,name);
        PageInfo<CskaoyanMallStorage> storagePageInfo = new PageInfo<>(storages);

        Map dataMap = new HashMap();
        dataMap.put("total",storagePageInfo.getTotal());
        dataMap.put("items",storages);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("storage/create")
    public BaseRespVo StorageCreate(HttpServletRequest request, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();  // name  happy.jpg
        String filename = UUID.randomUUID().toString().replace("-","")+".jpg";  // url  7d6f9888-05b3-4e1c-ad5f-ad469d62d4a1

        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);

        File file1 = new File(ResourceUtils.getURL("classpath:static/img/").getPath()+filename);

        file.transferTo(file1);

        CskaoyanMallStorage storage = new CskaoyanMallStorage();
        storage.setId(null);
        storage.setKey(filename);
        storage.setName(originalFilename);
        storage.setType(file.getContentType());
        storage.setSize((int) file.getSize());
        storage.setUrl("http://localhost:8081/img/"+filename);
        storage.setAddTime(date);
        storage.setUpdateTime(null);

        systemStorageServiceImpl.insertStorage(storage);

        CskaoyanMallStorage storage1 = systemStorageServiceImpl.selectStorageByKey(filename);
        BaseRespVo resp = new BaseRespVo();
        //System.out.println(storage1.toString());
        resp.setData(storage1);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }


}
