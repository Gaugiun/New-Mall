package com.baidu.mall.controller;

import com.baidu.mall.bean.*;
import com.baidu.mall.mapper.CskaoyanMallLogMapper;
import com.baidu.mall.service.CskaoyanMallLogService;
import com.baidu.mall.service.SystemAdminService;
import com.baidu.mall.service.SystemCharacterServiceImp;
import com.baidu.mall.service.SystemStorageServiceImpl;
import com.baidu.mall.utils.COSFileUploadUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
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
    public BaseRespVo UserList(Integer page,Integer limit,String key,String name){
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

        try {
            COSFileUploadUtil.picCOS(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        CskaoyanMallStorage storage = new CskaoyanMallStorage();
        storage.setId(null);
        storage.setKey(filename);
        storage.setName(originalFilename);
        storage.setType(file.getContentType());
        storage.setSize((int) file.getSize());
//        storage.setUrl("http://localhost:8081/img/"+filename);
        storage.setUrl("https://qwddfty-1256376956.cos.ap-beijing.myqcloud.com/"+filename);
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


    @Autowired
    SystemCharacterServiceImp systemCharacterServiceImp;

    /**
     * @return baseRespVo
     * 查找所有的管理员数据，并统计其总数，返回给response
     */
    @RequestMapping("role/list")
    public BaseRespVo<Object> roleStatics(int page, int limit, String name, String sort, String order){
        PageHelper.startPage(page,limit);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        List<CskaoyanMallRole> cskaoyanMallRoleList = systemCharacterServiceImp.queryCharacter(name);
        PageInfo<CskaoyanMallRole> rolesPageInfo = new PageInfo<>(cskaoyanMallRoleList);
        Map<String, Object> map = new HashMap<>();
        map.put("items", cskaoyanMallRoleList);
        map.put("total", rolesPageInfo.getTotal());
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("role/create")
    public BaseRespVo<Object> addRole(@RequestBody Map<String, String> json) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        String name = json.get("name");
        String desc = (json.get("desc"));
        CskaoyanMallRole cskaoyanMallRole = systemCharacterServiceImp.addRole(name, desc);
        baseRespVo.setErrno(0);
        baseRespVo.setData(cskaoyanMallRole);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("role/update")
    public BaseRespVo<Object> updateRole(@RequestBody CskaoyanMallRole cskaoyanMallRole) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        systemCharacterServiceImp.updateRole(cskaoyanMallRole);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    @RequestMapping("role/delete")
    public BaseRespVo<Object> deleteRole(@RequestBody CskaoyanMallRole cskaoyanMallRole) {
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        systemCharacterServiceImp.deleteRole(cskaoyanMallRole.getId());
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return  baseRespVo;
    }

    @Autowired
    SystemAdminService systemAdminService;

    @RequestMapping("admin/list")
    public BaseRespVo<Object> listAdmins(int page, int limit, String username, String Sort, String order){
        PageHelper.startPage(page, limit);
        List<CskaoyanMallAdmin> cskaoyanMallAdminList = systemAdminService.listAllAdmin(username);
        PageInfo<CskaoyanMallAdmin> adminPageInfo = new PageInfo<>(cskaoyanMallAdminList);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", adminPageInfo.getTotal());
        map.put("items", cskaoyanMallAdminList);
        baseRespVo.setData(map);
        return baseRespVo;
    }

    @RequestMapping("role/options")
    public BaseRespVo<Object> listRoles() {
        List<SystemCharacters4Options> cskaoyanMallRoleList = systemCharacterServiceImp.optionCharacter();
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setData(cskaoyanMallRoleList);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    @RequestMapping("role/permissions")
    public BaseRespVo<Object> ListAuths(Integer roleId) {
        HashMap map = systemCharacterServiceImp.queryAuthorityById(roleId);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        return baseRespVo;
    }

    //没有给返回类型中data数据
    @RequestMapping("admin/create")
    public BaseRespVo<Object> addAdmin(@RequestBody CskaoyanMallAdmin cskaoyanMallAdmin) {
        CskaoyanMallAdmin cskaoyanMallAdmin1 = systemAdminService.addAdmin(cskaoyanMallAdmin);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        baseRespVo.setData(cskaoyanMallAdmin1);
        return baseRespVo;
    }

    @RequestMapping("admin/delete")
    public BaseRespVo<Object> deleteAdmin(@RequestBody CskaoyanMallAdmin cskaoyanMallAdmin) {
        Integer id = cskaoyanMallAdmin.getId();
        systemAdminService.deleteAdmin(id);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    @Autowired
    CskaoyanMallLogService cskaoyanMallLogService;

    @RequestMapping("log/list")
    public BaseRespVo<Object> listAllLog(Integer page, Integer limit, String name, String sort, String order) {
        PageHelper.startPage(page, limit);
        List<CskaoyanMallLog> cskaoyanMallLogList = cskaoyanMallLogService.listLog(page, limit, name, sort, order);
        PageInfo<CskaoyanMallLog> cskaoyanMallLogPageInfo = new PageInfo<>(cskaoyanMallLogList);
        BaseRespVo<Object> baseRespVo = new BaseRespVo<>();
        Map map = new HashMap<String, Object>();
        map.put("items", cskaoyanMallLogList);
        map.put("total", cskaoyanMallLogPageInfo.getTotal());
        baseRespVo.setData(map);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}
