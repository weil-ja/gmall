package com.atguigu.gmall.cart.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.bean.OmsCartItem;
import com.atguigu.gmall.cart.mapper.OmsCartItemMapper;
import com.atguigu.gmall.service.CartService;
import com.atguigu.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    RedisUtil redisUtil;
    
    @Autowired
    OmsCartItemMapper omsCartItemMapper;
    
    @Override
    public OmsCartItem ifCartExistByUser(String memberId, String skuId) {
        OmsCartItem omsCartItem = omsCartItemMapper.selectOne(new OmsCartItem()
                .setMemberId(memberId)
                .setProductSkuId(skuId));
        return omsCartItem;
    }
    
    @Override
    public void addCart(OmsCartItem omsCartItem) {
        if (StringUtils.isNotBlank(omsCartItem.getMemberId())) {
            omsCartItemMapper.insertSelective(omsCartItem);
        }
    }
    
    @Override
    public void updateCart(OmsCartItem omsCartItemFromDb) {
        Example e = new Example(OmsCartItem.class);
        e.createCriteria().andEqualTo("id", omsCartItemFromDb.getId());
        
        omsCartItemMapper.updateByExampleSelective(omsCartItemFromDb.setModifyDate(new Date()), e);
        
    }
    
    @Override
    public Boolean flushCartCache(String memberId) {
        List<OmsCartItem> omsCartItems = omsCartItemMapper.select(new OmsCartItem().setMemberId(memberId));
        for (OmsCartItem omsCartItem : omsCartItems) {
            omsCartItem.setTotalPrice(omsCartItem.getQuantity().multiply(omsCartItem.getPrice()));
        }
        Jedis jedis = redisUtil.getJedis();
        
        Map<String, String> map = new LinkedHashMap<>();
        for (OmsCartItem omsCartItem : omsCartItems) {
            map.put(omsCartItem.getProductSkuId(), JSON.toJSONString(omsCartItem));
        }
        
        String key = "user:" + memberId + ":cart";
        jedis.del(key);
        String hmset = jedis.hmset(key, map);
    
        jedis.close();
        return StringUtils.isNotBlank(hmset);
    }
    
    @Override
    public List<OmsCartItem> cartList(String userId) {
        Jedis jedis = null;
        List<OmsCartItem> omsCartItems = new ArrayList<>();
        try {
            jedis = redisUtil.getJedis();
            List<String> hvals = jedis.hvals("user:" + userId + ":cart");
            for (String hval : hvals) {
                OmsCartItem omsCartItem = JSON.parseObject(hval, OmsCartItem.class);
                omsCartItems.add(omsCartItem);
            }
            
//        计算合计
            for (OmsCartItem omsCartItem : omsCartItems) {
//            乘法
                omsCartItem.setTotalPrice(omsCartItem.getQuantity().multiply(omsCartItem.getPrice()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            jedis.close();
        }
        return omsCartItems;
    }
    
    @Override
    public Boolean checkCart(OmsCartItem omsCartItem) {
        
        Example e = new Example(OmsCartItem.class);
        
        e.createCriteria().andEqualTo("memberId", omsCartItem.getMemberId())
                .andEqualTo("productSkuId", omsCartItem.getProductSkuId());
        ;
        int i = omsCartItemMapper.updateByExampleSelective(omsCartItem, e);
    
        Boolean b = flushCartCache(omsCartItem.getMemberId());
        return i!=0;
    }
    
    @Override
    public void isAllChecked(boolean b) {
        List<OmsCartItem> omsCartItems = omsCartItemMapper.selectAll();
        for (OmsCartItem omsCartItem : omsCartItems) {
            if (b) {
                omsCartItem.setIsChecked("1");
                checkCart(omsCartItem);
            } else {
                omsCartItem.setIsChecked("0");
                checkCart(omsCartItem);
            } 
        }
    }
    
    @Override
    public OmsCartItem findCartListBySkuId(String memberId, String skuId) {
        OmsCartItem omsCartItem = omsCartItemMapper.selectOne(new OmsCartItem()
                .setMemberId(memberId)
                .setProductSkuId(skuId));
        return omsCartItem;
    }
}
