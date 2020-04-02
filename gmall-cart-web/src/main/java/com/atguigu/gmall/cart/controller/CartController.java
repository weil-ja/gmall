package com.atguigu.gmall.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.authInterceptors.LoginRequired;
import com.atguigu.gmall.bean.OmsCartItem;
import com.atguigu.gmall.bean.PmsSkuInfo;
import com.atguigu.gmall.service.CartService;
import com.atguigu.gmall.service.SkuService;
import com.atguigu.gmall.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class CartController {
    
    @Reference
    SkuService skuService;
    
    @Reference
    CartService cartService;
    
    @RequestMapping("saveState")
    @ResponseBody
    @LoginRequired(loginSuccess = false)
    public String saveState(int quantity) {
        System.out.println(quantity);
        return "saveState";
    }
    
    @RequestMapping("toTrade")
    @LoginRequired(loginSuccess = false)
    public String toTrade(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        return "toTrade";
    }
    
    @RequestMapping("countCart")
    @ResponseBody
    @LoginRequired(loginSuccess = false)
    public String countCart(String skuId, String operate, HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {
        
        String memberId = "1";
        OmsCartItem omsCartItem = cartService.findCartListBySkuId(memberId,skuId);
        BigDecimal count=new BigDecimal(1);
        if (operate.equals("add")) {
            omsCartItem.setQuantity(omsCartItem.getQuantity().add(count));
        }else {
            omsCartItem.setQuantity(omsCartItem.getQuantity().subtract(count));
        }
    
        Boolean aBoolean = cartService.checkCart(omsCartItem);
        return JSON.toJSONString(aBoolean);
    }
    
    @RequestMapping("checkCart")
    @ResponseBody
    @LoginRequired(loginSuccess = false)
    public String checkCart(String skuId, String isChecked, HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {
        
        String memberId = "1";
        OmsCartItem omsCartItem = new OmsCartItem()
                .setMemberId(memberId)
                .setProductSkuId(skuId)
                .setIsChecked(isChecked);
        Boolean aBoolean = cartService.checkCart(omsCartItem);
        return JSON.toJSONString(aBoolean);
    }
    @RequestMapping("allCheck")
    @ResponseBody
    @LoginRequired(loginSuccess = false)
    public String allCheck(int isAllChecked, HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap modelMap) {
        if (isAllChecked == 1) {
            cartService.isAllChecked(true);
        } else {
            cartService.isAllChecked(false);
        } 
        return "success";
    }
    
    @RequestMapping("cartList")
    @LoginRequired(loginSuccess = false)
    public String cartList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        String memberId = "1";
        List<OmsCartItem> omsCartItems = new ArrayList<OmsCartItem>();
//        不为空返回true
        if (StringUtils.isNotBlank(memberId)) {
            omsCartItems = cartService.cartList(memberId);
        } else {
            String cartListCookie = CookieUtil.getCookieValue(request, "cartListCookie", true);
            if (StringUtils.isNotBlank(cartListCookie)) {
                omsCartItems = JSON.parseArray(cartListCookie, OmsCartItem.class);
            }
            
        }
        BigDecimal totalAmount = totalCartPrice(omsCartItems);
        modelMap.put("totalAmount", totalAmount);
        listSortByCreateDate(omsCartItems);
        modelMap.put("cartList", omsCartItems);
        return "cartLists";
    }
    
    private BigDecimal totalCartPrice(List<OmsCartItem> omsCartItems) {
        BigDecimal bigDecimal = new BigDecimal("0");
        for (OmsCartItem omsCartItem : omsCartItems) {
            if (omsCartItem.getIsChecked().equals("1")) {
                bigDecimal = bigDecimal.add(omsCartItem.getTotalPrice());
            }
        }
        return bigDecimal;
    }
    
    @RequestMapping("addToCart")
    @LoginRequired(loginSuccess = false)
    public ModelAndView addToCart(String skuId, int quantity, HttpServletRequest request, HttpServletResponse response) {
        
        //调用商品服务
        PmsSkuInfo skuInfo = skuService.getSkuById(skuId);
        
        //将当前商品封装成购物车数据
        
        List<OmsCartItem> omsCartItems = new ArrayList<>();
        OmsCartItem omsCartItem = new OmsCartItem();
        
        omsCartItem.setCreateDate(new Date());
        omsCartItem.setDeleteStatus(0);
        omsCartItem.setModifyDate(new Date());
        omsCartItem.setPrice(skuInfo.getPrice());
        omsCartItem.setProductAttr("");
        omsCartItem.setProductBrand("");
        omsCartItem.setProductCategoryId(skuInfo.getCatalog3Id());
        omsCartItem.setProductId(skuInfo.getProductId());
        omsCartItem.setProductName(skuInfo.getSkuName());
        omsCartItem.setProductPic(skuInfo.getSkuDefaultImg());
        omsCartItem.setProductSkuCode("11111111111");
        omsCartItem.setProductSkuId(skuId);
        omsCartItem.setQuantity(new BigDecimal(quantity));
        
        //判断用户是否登陆
        String memberId = "1";
        
        
        if (StringUtils.isBlank(memberId)) {
            String cookieName = "cartListCookie";
            Integer cookieMaxage = 60 * 60 * 3;

//            用户没有登陆

//            获取原cookie
            String cartListCookie = CookieUtil.getCookieValue(request, cookieName, true);

//            查看cookie是否有值
            if (StringUtils.isBlank(cartListCookie)) {
//                cookie为空
                omsCartItems.add(omsCartItem);
            } else {
//                cookie不为空
                omsCartItems = JSON.parseArray(cartListCookie, OmsCartItem.class);
                Boolean exist = ifCartExist(omsCartItems, omsCartItem);
//                查看是否为当前商品
                if (exist) {
//                    已添加,更新数量
                    for (OmsCartItem cartItem : omsCartItems) {
                        String productId = cartItem.getProductId();
                        if (productId.equals(omsCartItem.getProductId())) {
                            cartItem.setQuantity(cartItem.getQuantity().add(omsCartItem.getQuantity()));
                        }
                    }
                } else {
//                  未添加,新增
                    omsCartItems.add(omsCartItem);
                }
            }

//            更新cookie
            CookieUtil.setCookie(request, response, cookieName, JSON.toJSONString(omsCartItems), cookieMaxage, true);
            
        } else {
            //用户已登陆

//            查询DB数据
            OmsCartItem omsCartItemFromDb = cartService.ifCartExistByUser(memberId, skuId);
            if (omsCartItemFromDb == null) {
//                该用户没有添加过该商品
                cartService.addCart(omsCartItem
                        .setMemberId(memberId)
                        .setMemberNickname("阿山")
                        .setQuantity(new BigDecimal(quantity)));
            } else {
//                该用户添加过该商品
//                设置数量
                omsCartItemFromDb.setQuantity(omsCartItemFromDb.getQuantity().add(omsCartItem.getQuantity()));
                cartService.updateCart(omsCartItemFromDb);
            }
//            同步缓存
            cartService.flushCartCache(memberId);
        }
//        对于用户的修改操作,使用重定义向
        ModelAndView mv = new ModelAndView();
        mv.addObject("skuInfo", skuInfo);
        mv.addObject("skuNum", quantity);
        mv.setViewName("success");
        return mv;
    }
    
    private void listSortByCreateDate(List<OmsCartItem> omsCartItems) {
//        去重
//        Iterator<OmsCartItem> iterator = omsCartItems.iterator();
//        if(iterator.hasNext()){
//            if (iterator.next() == null) {
//                iterator.remove();
//            }
//        }
        Collections.sort(omsCartItems, new Comparator<OmsCartItem>() {
            @Override
            public int compare(OmsCartItem o1, OmsCartItem o2) {
                try {
                    if (o1.getCreateDate() == null || o2.getCreateDate() == null) {
                        return 1;
                    }
                    Date dt1 = o1.getCreateDate();
                    Date dt2 = o2.getCreateDate();
                    
                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
    
    private Boolean ifCartExist(List<OmsCartItem> omsCartItems, OmsCartItem omsCartItem) {
        boolean b = false;
        for (OmsCartItem cartItem : omsCartItems) {
            String productId = cartItem.getProductId();
            if (productId.equals(omsCartItem.getProductId())) {
                b = true;
            } else {
                b = false;
            }
        }
        
        return b;
    }
    
    @RequestMapping("success")
    @LoginRequired(loginSuccess = false)
    public String success(PmsSkuInfo skuInfo, String skuNum, ModelMap modelMap) {
        modelMap.put("skuinfo", skuInfo);
        modelMap.put("skuNum", skuNum);
        return "success";
    }
}
