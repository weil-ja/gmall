package com.atguigu.gmall.manage.service.impl;

import com.atguigu.gmall.bean.PmsProductInfo;
import com.atguigu.gmall.manage.mapper.PmsProductInfoMapper;
import com.atguigu.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SpuServiceImpl implements SpuService {

    @Autowired
    PmsProductInfoMapper pmsProductInfoMapper;

    @Override
    public List<PmsProductInfo> spuList(String catalog3Id) {
        PmsProductInfo pmsProductInfo =new PmsProductInfo();
        pmsProductInfo.setCatalog3Id(catalog3Id);
        return this.pmsProductInfoMapper.select(pmsProductInfo);
    }
}
