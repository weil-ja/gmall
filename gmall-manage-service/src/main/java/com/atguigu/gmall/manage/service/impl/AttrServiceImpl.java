package com.atguigu.gmall.manage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.PmsBaseAttrInfo;
import com.atguigu.gmall.bean.PmsBaseAttrValue;
import com.atguigu.gmall.bean.PmsBaseSaleAttr;
import com.atguigu.gmall.manage.mapper.PmsBaseAttrInfoMapper;
import com.atguigu.gmall.manage.mapper.PmsBaseAttrValueMapper;
import com.atguigu.gmall.manage.mapper.PmsBaseSaleAttrMapper;
import com.atguigu.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    PmsBaseAttrInfoMapper pmsBaseAttrInfoMapper;

    @Autowired
    PmsBaseAttrValueMapper pmsBaseAttrValueMapper;

    @Autowired
    PmsBaseSaleAttrMapper pmsBaseSaleAttrMapper;

//    根据三级类目id查询PmsBaseAttrInfo集合
    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        Example example=new Example(PmsBaseAttrInfo.class);
        example.createCriteria().andEqualTo("catalog3Id",catalog3Id);

        List<PmsBaseAttrInfo> pmsBaseAttrInfos = this.pmsBaseAttrInfoMapper.selectByExample(example);
        for (PmsBaseAttrInfo baseAttrInfo: pmsBaseAttrInfos) {

            PmsBaseAttrValue pmsBaseAttrValue=new PmsBaseAttrValue();
            pmsBaseAttrValue.setAttrId(baseAttrInfo.getId());
            List<PmsBaseAttrValue> pmsBaseAttrValues=this.pmsBaseAttrValueMapper.select(pmsBaseAttrValue);

            baseAttrInfo.setAttrValueList(pmsBaseAttrValues);
            
        }

        return pmsBaseAttrInfos;
    }

//    根据id的有无，执行增加或修改操作
    @Override
    public String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {

        if (StringUtils.isEmpty(pmsBaseAttrInfo.getId())){
//            id为空，保存
//        添加属性
            this.pmsBaseAttrInfoMapper.insertSelective(pmsBaseAttrInfo);

//        添加属性值
            insertAttr(pmsBaseAttrInfo);
        }else {
//            不为空，修改
            Example example=new Example(PmsBaseAttrInfo.class);
            example.createCriteria().andEqualTo("id",pmsBaseAttrInfo.getId());
//            属性
            this.pmsBaseAttrInfoMapper.updateByExampleSelective(pmsBaseAttrInfo,example);

//            属性值

//            删除旧属性值
            PmsBaseAttrValue record=new PmsBaseAttrValue();
            record.setAttrId(pmsBaseAttrInfo.getId());
            this.pmsBaseAttrValueMapper.delete(record);

//          插入
            insertAttr(pmsBaseAttrInfo);
        }
        return "success";
    }

    public void insertAttr(PmsBaseAttrInfo pmsBaseAttrInfo){
        List<PmsBaseAttrValue> attrValueList = pmsBaseAttrInfo.getAttrValueList();
        for (PmsBaseAttrValue attrValue : attrValueList) {
            attrValue.setAttrId(pmsBaseAttrInfo.getId());
            this.pmsBaseAttrValueMapper.insertSelective(attrValue);
        }
    }

    /**
     * 查询修改需要的参数
     * @param attrId
     * @return
     */
    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        Example example=new Example(PmsBaseAttrValue.class);
        example.createCriteria().andEqualTo("attrId",attrId);
        return this.pmsBaseAttrValueMapper.selectByExample(example);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return this.pmsBaseSaleAttrMapper.selectAll();
    }
}
