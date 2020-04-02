package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsSkuInfo;

import java.util.List;

public interface SkuService {
    void saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    /**
     * 限定一条线程的测试用
     * 
     * @param skuId
     * @param ip
     * @return
     */
    PmsSkuInfo getSkuById(String skuId,String ip);
    
    PmsSkuInfo getSkuById(String skuId);

    List<PmsSkuInfo> spuSaleAttrListCheckBySku(String productId);

    List<PmsSkuInfo> getAllSku();
}
