package com.baidu.mall.service;

import com.baidu.mall.bean.CskaoyanMallBrand;
import com.baidu.mall.bean.CskaoyanMallCategory;
import com.baidu.mall.bean.CskaoyanMallCategoryByLevel;
import com.baidu.mall.bean.CskaoyanMallRegion;
import com.baidu.mall.mapper.CskaoyanMallBrandMapper;
import com.baidu.mall.mapper.CskaoyanMallCategoryMapper;
import com.baidu.mall.mapper.CskaoyanMallRegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MallServiceImpl implements MallService{
    @Autowired
    CskaoyanMallRegionMapper cskaoyanMallRegionMapper;

    @Transactional
    @Override
    public List<CskaoyanMallRegion> selectRegion() {
        List<CskaoyanMallRegion> regions = selectRegionByPId(0);
        return regions;
    }

    @Autowired
    CskaoyanMallBrandMapper cskaoyanMallBrandMapper;

    @Override
    public List<CskaoyanMallBrand> selectBrand(String name, Integer id) {
        return cskaoyanMallBrandMapper.selectBrand(name,id);
    }

    @Autowired
    CskaoyanMallCategoryMapper cskaoyanMallCategoryMapper;

    @Override
    public List<CskaoyanMallCategory> selectCategory() {
        List<CskaoyanMallCategory> cskaoyanMallCategories = cskaoyanMallCategoryMapper.selectCategoryByPid(0);
        for (CskaoyanMallCategory category : cskaoyanMallCategories){
            Integer pid = category.getId();
            category.setChildren(cskaoyanMallCategoryMapper.selectCategoryByPid(pid));
        }
        return cskaoyanMallCategories;
    }

    @Override
    public List<CskaoyanMallCategoryByLevel> selectCategoryByLevel(int i) {
        return cskaoyanMallCategoryMapper.selectCategoryByLevel(i);
    }

    @Override
    public boolean deleteCategory(Integer id) {
        return cskaoyanMallCategoryMapper.deleteCategoryById(id);
    }

    @Override
    public void insertBrand(CskaoyanMallBrand brand) {
        cskaoyanMallBrandMapper.insertBrand(brand);
    }

    @Override
    public CskaoyanMallBrand selectBrandByName(String name) {
        return cskaoyanMallBrandMapper.selectBrandByName(name);
    }

    @Override
    public boolean deleteBrand(Integer id) {
        return cskaoyanMallBrandMapper.deleteBrandById(id);
    }

    @Override
    public boolean updateBrand(CskaoyanMallBrand cskaoyanMallBrand) {
        return cskaoyanMallBrandMapper.updateBrand(cskaoyanMallBrand);
    }

    @Override
    public List<CskaoyanMallBrand> selectAllBrand() {
        return cskaoyanMallBrandMapper.selectAllBrand();
    }

    @Override
    public void insertCatagory(CskaoyanMallCategory cskaoyanMallCategory) {
        cskaoyanMallCategoryMapper.insertCategory(cskaoyanMallCategory);
    }

    @Override
    public CskaoyanMallCategory selectCategoryByName(String name) {
        return cskaoyanMallCategoryMapper.selectCategoryByName(name);
    }

    @Override
    public void updateCategory(CskaoyanMallCategory cskaoyanMallCategory) {
        cskaoyanMallCategoryMapper.updateCategory(cskaoyanMallCategory);
    }

    public List<CskaoyanMallRegion> selectRegionByPId(Integer Pid) {
        List<CskaoyanMallRegion> regions = cskaoyanMallRegionMapper.selectRegionByPId(Pid);
        for(CskaoyanMallRegion region : regions){
            if (region.getType()==3){
                return regions;
            }
            Integer id = region.getId();
            region.setChildren(selectRegionByPId(id));
        }
        return regions;
    }
}
