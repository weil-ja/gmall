package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.PmsBaseCatalog1;
import com.atguigu.gmall.bean.PmsBaseCatalog2;
import com.atguigu.gmall.bean.PmsBaseCatalog3;
import com.atguigu.gmall.manage.mapper.PmsBaseCatalog1Mapper;
import com.atguigu.gmall.manage.mapper.PmsBaseCatalog2Mapper;
import com.atguigu.gmall.manage.mapper.PmsBaseCatalog3Mapper;
import com.atguigu.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    PmsBaseCatalog1Mapper pmsBaseCatalog1Mapper;
    @Autowired
    PmsBaseCatalog2Mapper pmsBaseCatalog2Mapper;
    @Autowired
    PmsBaseCatalog3Mapper pmsBaseCatalog3Mapper;

    /**
     * 查询一级类目
     * @return
     */
    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return this.pmsBaseCatalog1Mapper.selectAll();
    }

    /**
     * 通过一级类目id查询二级类目
     * @param catalog1Id
     * @return
     */
    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        Example example=new Example(PmsBaseCatalog2.class);
        example.createCriteria().andEqualTo("catalog1Id",catalog1Id);
        return this.pmsBaseCatalog2Mapper.selectByExample(example);
    }

    /**
     * 通过二级类目id查询三级类目
     * @param catalog2Id
     * @return
     */
    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        Example example=new Example(PmsBaseCatalog3.class);
        example.createCriteria().andEqualTo("catalog2Id",catalog2Id);
        return this.pmsBaseCatalog3Mapper.selectByExample(example);
    }
}
