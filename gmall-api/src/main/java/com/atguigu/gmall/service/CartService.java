package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.OmsCartItem;

import java.util.List;

public interface CartService {
    
    OmsCartItem ifCartExistByUser(String memberId, String skuId);
    
    void addCart(OmsCartItem omsCartItem);
    
    void updateCart(OmsCartItem omsCartItemFromDb);
    
    /**
     * 同步缓存
     * 
     * @param memberId
     */
    Boolean flushCartCache(String memberId);
    
    /**
     * 获取购物车列表
     * 
     * @param userId
     * @return
     */
    List<OmsCartItem> cartList(String userId);
    
    /**
     * 当用户修改选中状态时进行异步修改
     * 
     * @param omsCartItem
     */
    Boolean checkCart(OmsCartItem omsCartItem);
    
    void isAllChecked(boolean b);
    
    OmsCartItem findCartListBySkuId(String memberId, String skuId);
}
