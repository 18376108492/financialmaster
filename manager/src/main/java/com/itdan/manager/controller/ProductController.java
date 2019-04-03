package com.itdan.manager.controller;

import com.itdan.entity.pojo.Product;
import com.itdan.manager.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 产品控制层
 */
@Controller
@RequestMapping("/manager/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 测试
     * @return
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(){
        return "test";
    }

    /**
     * 添加产品
     * @param product 产品对象
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Product addProduct(@RequestBody Product product){
          Product reslut= productService.addProduct(product);
          return  reslut;
    }

    /**
     * 根据传入的ID查询相应的产品
     * @param id 产品编号
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Product getById(@PathVariable String id){
         Product product= productService.getById(id);
         return  product;
    }

    /**
     * 分页查询
     * @param ids id列表
     * @param minRewardRate 收益率下限
     * @param maxRewardRate 收益率上限
     * @param status 状态
     * @param pageNum  页码
     * @param pagesize 每页显示条数
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Page<Product> query(String ids,
                         BigDecimal minRewardRate,
                         BigDecimal maxRewardRate,
                         String status,
                         @RequestParam(defaultValue = "0")int pageNum,
                         @RequestParam(defaultValue = "30" )int pagesize){

        System.out.println("传入的ID："+ids);
        //包装参数
        List<String> idList=null, statusList=null;
        if(StringUtils.isNotBlank(ids)){
            idList=Arrays.asList(ids.split(","));
        }
        if(StringUtils.isNotBlank(status)){
            statusList=Arrays.asList(status.split(","));
        }
        Pageable pageable=new PageRequest(pageNum,pagesize);
        Page<Product> page= productService.query(idList,minRewardRate,maxRewardRate,statusList,pageable);
        return page;
    }

}
