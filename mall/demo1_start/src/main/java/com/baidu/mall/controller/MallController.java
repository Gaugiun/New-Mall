package com.baidu.mall.controller;

import com.baidu.mall.bean.*;
import com.baidu.mall.service.MallService;
import com.baidu.mall.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("admin")
@RestController
public class MallController {

    @Autowired
    MallService mallService;

    /**
     * 行政管理  admin/region/list
     */
    @RequestMapping("region/list")
    public BaseRespVo UserList(){
        List<CskaoyanMallRegion> regions = mallService.selectRegion();

        BaseRespVo resp = new BaseRespVo();
        resp.setData(regions);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 品牌制造商  admin/brand/list
     * page: 1
     * limit: 20
     * id: 009     品牌商ID
     * name:       品牌商名称
     * sort: add_time
     * order: desc
     */
    @RequestMapping("brand/list")
    public BaseRespVo BrandList(int page,int limit,String name,Integer id){
        PageHelper.startPage(page,limit);
        List<CskaoyanMallBrand> brands = mallService.selectBrand(name,id);
        PageInfo<CskaoyanMallBrand> brandPageInfo = new PageInfo<>(brands);

        Map dataMap = new HashMap();
        dataMap.put("total",brandPageInfo.getTotal());
        dataMap.put("items",brands);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(dataMap);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }
    /**
     * 添加品牌制造商  admin/brand/create
     * 数据库的name改为unique  不能重复
     */
    @RequestMapping("brand/create")
    public BaseRespVo CreateBrand(@RequestBody CskaoyanMallBrand brand){
        List<CskaoyanMallBrand> brands = mallService.selectAllBrand();

        brand.setId(0);
        brand.setSortOrder((byte) (brands.size()+1));
        brand.setAddTime(new Date(System.currentTimeMillis()));
        brand.setUpdateTime(new Date(System.currentTimeMillis()));
        brand.setDeleted(false);
        mallService.insertBrand(brand);

        CskaoyanMallBrand cskaoyanMallBrand = mallService.selectBrandByName(brand.getName());

        BaseRespVo resp = new BaseRespVo();
        resp.setData(cskaoyanMallBrand);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("brand/delete")
    public BaseRespVo DeleteBrand(@RequestBody CskaoyanMallBrand cskaoyanMallBrand){
        Integer id = cskaoyanMallBrand.getId();
        boolean b = mallService.deleteBrand(id);

        BaseRespVo resp = new BaseRespVo();
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("brand/update")
    public BaseRespVo UpdateBrand(@RequestBody CskaoyanMallBrand cskaoyanMallBrand){
        Integer id = cskaoyanMallBrand.getId();
        cskaoyanMallBrand.setUpdateTime(new Date(System.currentTimeMillis()));
        boolean b = mallService.updateBrand(cskaoyanMallBrand);

        CskaoyanMallBrand after = mallService.selectBrandByName(cskaoyanMallBrand.getName());

        BaseRespVo resp = new BaseRespVo();
        resp.setData(after);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }


    /**
     * 商品类目  admin/category/list
     */
    @RequestMapping("category/list")
    public BaseRespVo CategoryList(){
        List<CskaoyanMallCategory> categories = mallService.selectCategory();

        BaseRespVo resp = new BaseRespVo();
        resp.setData(categories);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("category/l1")
    public BaseRespVo CategoryL1(){
        List<CskaoyanMallCategoryByLevel> categoriesL1 = mallService.selectCategoryByLevel(1);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(categoriesL1);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    @RequestMapping("category/delete")
    public BaseRespVo DeleteCategory(@RequestBody CskaoyanMallCategory cskaoyanMallCategory){
        Integer id = cskaoyanMallCategory.getId();
        boolean b = mallService.deleteCategory(id);

        BaseRespVo resp = new BaseRespVo();
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 添加商品类目
     * */
    @RequestMapping("category/create")
    public BaseRespVo CreateCategory(@RequestBody CskaoyanMallCategory cskaoyanMallCategory){
        List<CskaoyanMallCategory> categories = mallService.selectCategory();
        cskaoyanMallCategory.setAddTime(new Date(System.currentTimeMillis()));
        cskaoyanMallCategory.setSortOrder((byte) (categories.size()+1));
        mallService.insertCatagory(cskaoyanMallCategory);

        String name = cskaoyanMallCategory.getName();
        CskaoyanMallCategory category = mallService.selectCategoryByName(name);

        BaseRespVo resp = new BaseRespVo();
        resp.setData(category);
        resp.setErrno(0);
        resp.setErrmsg("成功");

        return resp;
    }

    /**
     * 编辑商品类目
     * */
    @RequestMapping("category/update")
    public BaseRespVo UpdateCategory(@RequestBody CskaoyanMallCategory cskaoyanMallCategory){
        BaseRespVo resp = new BaseRespVo();
        if (cskaoyanMallCategory.getId().equals(cskaoyanMallCategory.getPid())){
            resp.setData(null);
            resp.setErrno(10000);
            resp.setErrmsg("不能将自己作为父类目录");
        }else {
            cskaoyanMallCategory.setUpdateTime(new Date(System.currentTimeMillis()));
            mallService.updateCategory(cskaoyanMallCategory);
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }
        return resp;
    }


}
