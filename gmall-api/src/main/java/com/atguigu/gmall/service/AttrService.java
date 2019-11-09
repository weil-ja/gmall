package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;

import java.util.List;

/**
 * api接口
 */
public interface AttrService {
    /**
     * 根据三级类目id查询PmsBaseAttrInfo集合
     *
     * @param catalog3Id
     * @return
     */
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    /**
     * 根据id的有无，执行增加或修改操作
     *
     * @param pmsBaseAttrInfo
     * @return
     */
    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    /**
     * 查询修改需要的参数
     *
     * @param attrId
     * @return
     */
    List<PmsBaseAttrValue> getAttrValueList(String attrId);
}
