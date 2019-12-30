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
import java.util.*;

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

    /**
     * 分页显示订单信息 admin/order/list?page=1&limit=20&sort=add_time&order=des
     */
    @RequestMapping("order/list")
    public BaseRespVo OrderList(Integer page, Integer limit, String[] orderStatusArray, Integer userId, Integer orderSn) {
        List<CskaoyanMallOrder> cskaoyanMallOrderList = new ArrayList<>();
        BaseRespVo baseRespVo = new BaseRespVo();
        PageHelper.startPage(page,limit);
        List<CskaoyanMallOrder> cskaoyanMallOrders = mallService.selectByUserIdOrderIdOrderStatus(userId, orderSn, orderStatusArray);
        cskaoyanMallOrderList.addAll(cskaoyanMallOrders);
        PageInfo<CskaoyanMallOrder> pageInfo = new PageInfo<>(cskaoyanMallOrderList);
        Long total = pageInfo.getTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", cskaoyanMallOrderList);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 显示订单详情 admin/order/detail?id=8
     */
    @RequestMapping("order/detail")
    public BaseRespVo detail(Integer id) {
        Map<String, Object> map = mallService.queryOrderMsg(id);
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 处理用户退款 admin/order/refund
     */
    @RequestMapping("order/refund")
    public BaseRespVo refund(Integer orderId, Integer refundMoney) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrno(0);
        boolean flag = mallService.refund(orderId, refundMoney);
        if (flag) {
            baseRespVo.setErrno(0);
            baseRespVo.setErrmsg("退款成功");
            return baseRespVo;
        }
        else {
            baseRespVo.setErrno(621);
            baseRespVo.setErrmsg("订单退款失败");
            return baseRespVo;
        }
    }

    /**
     * 常用的问题内容及回复 admin/issue/list?page=1&limit=20&sort=add_time&order=desc
     */
    @RequestMapping("issue/list")
    public BaseRespVo issue(Integer page, Integer limit, String question) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(page,limit);
        List<CskaoyanMallIssue> issueList = mallService.queryIssues(question);
        PageInfo<CskaoyanMallIssue> pageInfo = new PageInfo<>(issueList);
        Long total = pageInfo.getTotal();
        map.put("total", total);
        map.put("items", issueList);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 新建问题 admin/issue/create
     */
    @RequestMapping("issue/create")
    public BaseRespVo createIssue(@RequestBody Map map) {
        BaseRespVo baseRespVo = new BaseRespVo();
        String question = (String) map.get("question");
        String answer = (String) map.get("answer");
        CskaoyanMallIssue cskaoyanMallIssue = mallService.create(question, answer);
        baseRespVo.setErrno(0);
        Map map1 = new LinkedHashMap();
        map1.put("id", cskaoyanMallIssue.getId());
        map1.put("question", cskaoyanMallIssue.getQuestion());
        map1.put("answer", cskaoyanMallIssue.getAnswer());
        map1.put("addTime", cskaoyanMallIssue.getAddTime());
        map1.put("update", cskaoyanMallIssue.getUpdateTime());
        baseRespVo.setData(map1);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 删除问题  admin/issue/delete
     */
    @RequestMapping("issue/delete")
    public BaseRespVo deleteIssue(CskaoyanMallIssue cskaoyanMallIssue) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Integer id = cskaoyanMallIssue.getId();
        mallService.deleteIssueById(id);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 更新问题 admin/issue/update
     */
    @RequestMapping("issue/update")
    public BaseRespVo updateIssue(CskaoyanMallIssue cskaoyanMallIssue) {
        BaseRespVo baseRespVo = new BaseRespVo();
        cskaoyanMallIssue.setUpdateTime(new Date(System.currentTimeMillis()));
        mallService.updateIssue(cskaoyanMallIssue);
        baseRespVo.setErrno(0);
        baseRespVo.setData(cskaoyanMallIssue);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 显示常见关键词及跳转链接  admin/keyword/list?page=1&limit=20&keyword=123&url=123123&sort=add_time&order=desc
     */
    @RequestMapping("keyword/list")
    public BaseRespVo keywordList(Integer page, Integer limit, String keyword, String url) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Map<String, Object> map = new HashMap<>();
        System.out.println(keyword);
        PageHelper.startPage(page,limit);
        List<CskaoyanMallKeyword> keywordList = mallService.queryKeywords(keyword, url);
        PageInfo<CskaoyanMallKeyword> pageInfo = new PageInfo<>(keywordList);
        Long total = pageInfo.getTotal();
        map.put("total", total);
        map.put("items", keywordList);
        baseRespVo.setErrno(0);
        baseRespVo.setData(map);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 添加新的关键词 admin/keyword/create
     */
    @RequestMapping("keyword/create")
    public BaseRespVo createKeyword(@RequestBody Map map) {
        BaseRespVo baseRespVo = new BaseRespVo();
        String keyword = (String)map.get("keyword");
        String url = (String) map.get("url");
        Boolean isHot = (Boolean) map.get("isHot");
        Boolean isDefault = (Boolean) map.get("isDefault");
        Integer is_hot = null;
        Integer is_default = null;
        if (isHot) {
            is_hot = 1;
        }
        else {
            is_hot = 0;
        }
        if (isDefault) {
            is_default = 1;
        }
        else {
            is_default = 0;
        }
        CskaoyanMallKeyword cskaoyanMallKeyword = mallService.createKeyword(keyword, url, is_hot, is_default);
        Map map1 = new LinkedHashMap();
        baseRespVo.setErrno(0);
        map1.put("id", cskaoyanMallKeyword.getId());
        map1.put("keyword", cskaoyanMallKeyword.getKeyword());
        map1.put("url", cskaoyanMallKeyword.getUrl());
        map1.put("isHot", cskaoyanMallKeyword.getIsHot());
        map1.put("isDefault", cskaoyanMallKeyword.getIsDefault());
        map1.put("addTime", cskaoyanMallKeyword.getAddTime());
        map1.put("updateTime", cskaoyanMallKeyword.getUpdateTime());
        baseRespVo.setData(map1);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

    /**
     * 更新关键字信息 admin/keyword/update
     */
    @RequestMapping("keyword/update")
    public BaseRespVo updateKeyword(CskaoyanMallKeyword cskaoyanMallKeyword) {
        BaseRespVo baseRespVo = new BaseRespVo();
        cskaoyanMallKeyword.setUpdateTime(new Date(System.currentTimeMillis()));
        mallService.updateKeyword(cskaoyanMallKeyword);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("更新成功");
        return baseRespVo;
    }

    /**
     * 删除关键字信息 admin/keyword/delete
     */
    @RequestMapping("keyword/delete")
    public BaseRespVo deleteKeyword(CskaoyanMallKeyword cskaoyanMallKeyword) {
        BaseRespVo baseRespVo = new BaseRespVo();
        Integer id = cskaoyanMallKeyword.getId();
        mallService.deleteKeyword(id);
        baseRespVo.setErrno(0);
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }

}
