package com.atguigu.gmall.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.service.AttrService;
import com.atguigu.gmall.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class SearchController {

    @Reference
    SearchService searchService;

    @Reference
    AttrService attrService;

    @RequestMapping("list.html")
    public String list(PmsSearchParam pmsSearchParam, ModelMap modelMap) {

//        返回检索结果
        List<PmsSearchSkuInfo> pmsSearchSkuInfos = searchService.list(pmsSearchParam);
//        返回商品列表
        modelMap.put("skuLsInfoList", pmsSearchSkuInfos);

//        抽取平台属性集合(数组去重)
        Set<String> valueIdSet = new HashSet<>();

        for (PmsSearchSkuInfo pmsSearchSkuInfo : pmsSearchSkuInfos) {
            for (PmsSkuAttrValue pmsSkuAttrValue : pmsSearchSkuInfo.getSkuAttrValueList()) {
                String valueId = pmsSkuAttrValue.getValueId();
                valueIdSet.add(valueId);
            }
        }

//        根据查询结果的所有属性Id将属性列表查出
        List<PmsBaseAttrInfo> pmsBaseAttrInfos = attrService.getAttrValueListByValueId(valueIdSet);

//        进一步优化,去掉当前valueId所在的属性组(去掉某一数组元素)
        String[] delValueIds = pmsSearchParam.getValueId();
        if (delValueIds != null) {

//        面包屑
            List<PmsSearchCrumb> attrValueSelectedList = new ArrayList<>();

            for (String delValueId : delValueIds) {
//            迭代器使用一次后失效
                Iterator<PmsBaseAttrInfo> iterator = pmsBaseAttrInfos.iterator();
//                面包屑参数
                PmsSearchCrumb pmsSearchCrumb = new PmsSearchCrumb()
                        .setValueId(delValueId)
                        .setUrlParam(getUrlParam(pmsSearchParam, delValueId));
                while (iterator.hasNext()) {
                    PmsBaseAttrInfo pmsBaseAttrInfo = iterator.next();
                    for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrInfo.getAttrValueList()) {
                        String attrValueId = pmsBaseAttrValue.getId();

                        if (delValueId.equals(attrValueId)) {
                            // 将选择的属性放至面包屑
                            pmsSearchCrumb.setValueName(pmsBaseAttrValue.getValueName());
                            // 然后删除该属性所在属性组(attrinfo)
                            iterator.remove();
                        }
                    }
                }
                attrValueSelectedList.add(pmsSearchCrumb);
            }
            modelMap.put("attrValueSelectedList", attrValueSelectedList);
        }
        modelMap.put("attrList", pmsBaseAttrInfos);

        String urlParam = getUrlParam(pmsSearchParam);
//        返回组合路径
        modelMap.put("urlParam", urlParam);
        String keyword = pmsSearchParam.getKeyword();
//        关键字
        if (StringUtils.isNotBlank(keyword)) {
            modelMap.put("keyword", keyword);
        }
        return "list";
    }

    private String getUrlParam(PmsSearchParam pmsSearchParam, String... delValueId) {
        String keyword = pmsSearchParam.getKeyword();
        String catalog3Id = pmsSearchParam.getCatalog3Id();
        String[] skuAttrValueList = pmsSearchParam.getValueId();

        String urlParam = "";

        if (StringUtils.isNotBlank(keyword)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "keyword=" + keyword;
        }

        if (StringUtils.isNotBlank(catalog3Id)) {
            if (StringUtils.isNotBlank(urlParam)) {
                urlParam = urlParam + "&";
            }
            urlParam = urlParam + "catalog3Id=" + catalog3Id;
        }

        if (skuAttrValueList != null) {

            for (String pmsSkuAttrValue : skuAttrValueList) {
                if (delValueId.length > 0 && delValueId != null) {
                    if (!pmsSkuAttrValue.equals(delValueId[0])) {
                        urlParam = urlParam + "&valueId=" + pmsSkuAttrValue;
                    }
                } else {
                    urlParam = urlParam + "&valueId=" + pmsSkuAttrValue;
                }
            }
        }
        return urlParam;
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }
}
