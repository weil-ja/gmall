package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsProductInfo;

import java.util.List;

public interface SpuService {
    /**
     * 查询spu列表
     * @param catalog3Id
     * @return
     */
    List<PmsProductInfo> spuList(String catalog3Id);
}
