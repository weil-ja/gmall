package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.bean.PmsSkuAttrValue;
import com.atguigu.gmall.bean.PmsSkuImage;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.bean.PmsSkuSaleAttrValue;
import com.atguigu.gmall.manage.mapper.PmsSkuAttrValueMapper;
import com.atguigu.gmall.manage.mapper.PmsSkuImageMapper;
import com.atguigu.gmall.manage.mapper.PmsSkuInfoMapper;
import com.atguigu.gmall.manage.mapper.PmsSkuSaleAttrValueMapper;
import com.atguigu.gmall.service.SearchService;
import com.atguigu.gmall.service.SkuService;
import com.atguigu.gmall.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class SkuServiceImpl implements SkuService {

    @Autowired
    PmsSkuInfoMapper pmsSkuInfoMapper;

    @Autowired
    PmsSkuAttrValueMapper pmsSkuAttrValueMapper;

    @Autowired
    PmsSkuSaleAttrValueMapper pmsSkuSaleAttrValueMapper;

    @Autowired
    PmsSkuImageMapper pmsSkuImageMapper;

    @Autowired
    RedisUtil redisUtil;

    @Reference
    SearchService searchService;

    @Override
    public void saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
    
        try {
            // 插入skuInfo
            int i = pmsSkuInfoMapper.insertSelective(pmsSkuInfo);
            String skuId = pmsSkuInfo.getId();
        
            // 插入平台属性关联
            List<PmsSkuAttrValue> skuAttrValueList = pmsSkuInfo.getSkuAttrValueList();
            for (PmsSkuAttrValue pmsSkuAttrValue : skuAttrValueList) {
                pmsSkuAttrValue.setSkuId(skuId);
                pmsSkuAttrValueMapper.insertSelective(pmsSkuAttrValue);
            }
        
            // 插入销售属性关联
            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = pmsSkuInfo.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                pmsSkuSaleAttrValue.setSkuId(skuId);
                pmsSkuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
            }
        
            // 插入图片信息
            List<PmsSkuImage> skuImageList = pmsSkuInfo.getSkuImageList();
            for (PmsSkuImage pmsSkuImage : skuImageList) {
                pmsSkuImage.setSkuId(skuId);
                pmsSkuImageMapper.insertSelective(pmsSkuImage);
            }
//      向kibana传入数据
            searchService.put(pmsSkuInfo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public PmsSkuInfo getSkuByIdFromDb(String skuId) {
//      skuImage数据库中不包括图片,手动查询
        List<PmsSkuImage> pmsSkuImages = pmsSkuImageMapper.select(new PmsSkuImage().setSkuId(skuId));

        //返回给前端的数据
        PmsSkuInfo pmsSkuInfo = pmsSkuInfoMapper.selectOne(new PmsSkuInfo().setId(skuId))
                .setSkuImageList(pmsSkuImages);
        return pmsSkuInfo;
    }

    @Override
    public PmsSkuInfo getSkuById(String skuId, String ip) {
        System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "进入的商品详情的请求");
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        // 链接缓存
        Jedis jedis = redisUtil.getJedis();
        // 查询缓存
        String skuKey = "sku:" + skuId + ":info";
        String skuJson = jedis.get(skuKey);

        if (StringUtils.isNotBlank(skuJson)) {//if(skuJson!=null&&!skuJson.equals(""))
            System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "从缓存中获取商品详情");

            pmsSkuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
        } else {
            // 如果缓存中没有，查询mysql
            System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "发现缓存中没有，申请缓存的分布式锁：" + "sku:" + skuId + ":lock");


            // 设置分布式锁
            String token = UUID.randomUUID().toString();
            String OK = jedis.set("sku:" + skuId + ":lock", token, "nx", "px", 10 * 1000);// 拿到锁的线程有10秒的过期时间
            if (StringUtils.isNotBlank(OK) && OK.equals("OK")) {
                // 设置成功，有权在10秒的过期时间内访问数据库
                System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "有权在10秒的过期时间内访问数据库：" + "sku:" + skuId + ":lock");

                pmsSkuInfo = getSkuByIdFromDb(skuId);

                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (pmsSkuInfo != null) {
                    // mysql查询结果存入redis
                    jedis.set("sku:" + skuId + ":info", JSON.toJSONString(pmsSkuInfo));
                } else {
                    // 数据库中不存在该sku
                    // 为了防止缓存穿透将，null或者空字符串值设置给redis
                    jedis.setex("sku:" + skuId + ":info", 60 * 3, JSON.toJSONString(""));
                }
                // 在访问mysql后，将mysql的分布锁释放
                System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "使用完毕，将锁归还：" + "sku:" + skuId + ":lock");
//                String lockToken = jedis.get("sku:" + skuId + ":lock");
//                if(StringUtils.isNotBlank(lockToken)&&lockToken.equals(token)){
//                    jedis.del("sku:" + skuId + ":lock");
//                }
//                lua脚本查询到的一瞬间删除锁
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                jedis.eval(script, Collections.singletonList("lock"), Collections.singletonList(token));

            } else {
                System.out.println("ip为" + ip + "的同学:" + Thread.currentThread().getName() + "没有拿到锁，开始自旋");
                // 设置失败，自旋（该线程在睡眠几秒后，重新尝试访问本方法）
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return getSkuById(skuId, ip);
            }
        }
        jedis.close();
        return pmsSkuInfo;
    }

    @Override
    public PmsSkuInfo getSkuById(String skuId) {
        PmsSkuInfo pmsSkuInfo = new PmsSkuInfo();
        // 链接缓存
        Jedis jedis = redisUtil.getJedis();
        // 查询缓存
        String skuKey = "sku:" + skuId + ":info";
        String skuJson = jedis.get(skuKey);

        if (StringUtils.isNotBlank(skuJson)) {//if(skuJson!=null&&!skuJson.equals(""))

            pmsSkuInfo = JSON.parseObject(skuJson, PmsSkuInfo.class);
        } else {
            // 如果缓存中没有，查询mysql
            pmsSkuInfo = getSkuByIdFromDb(skuId);

            if (pmsSkuInfo != null) {
                // mysql查询结果存入redis
                jedis.set("sku:" + skuId + ":info", JSON.toJSONString(pmsSkuInfo));
            } else {
                // 数据库中不存在该sku
                // 为了防止缓存穿透将，null或者空字符串值设置给redis
                jedis.setex("sku:" + skuId + ":info", 60 * 3, JSON.toJSONString(""));
            }
        }
        jedis.close();
        return pmsSkuInfo;
    }

    @Override
    public List<PmsSkuInfo> spuSaleAttrListCheckBySku(String productId) {
        return pmsSkuInfoMapper.selectSkuSaleAttrValueListBySpu(productId);
    }

    @Override
    public List<PmsSkuInfo> getAllSku() {
        List<PmsSkuInfo> pmsSkuInfos = pmsSkuInfoMapper.selectAll();
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfos) {
            String skuId = pmsSkuInfo.getId();

            List<PmsSkuAttrValue> pmsSkuAttrValueList =
                    pmsSkuAttrValueMapper.select(new PmsSkuAttrValue().setSkuId(skuId));
            pmsSkuInfo.setSkuAttrValueList(pmsSkuAttrValueList);

        }
        return pmsSkuInfos;
    }
}
