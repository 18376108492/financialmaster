package com.itdan.manager.controller;

import com.itdan.entity.pojo.Product;
import com.itdan.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 产品控制层
 */
@Controller
@RequestMapping("/products")
public class ProductController {



    @Autowired
    private ProductService productService;

    /**
     * 添加产品
     * @param product 产品对象
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product){
          Product result= productService.addProduct(product);
          return  result;
    }

    /**
     * 根据传入的ID查询相应的产品
     * @param id 产品编号
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
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
    public Product query(String ids,
                         BigDecimal minRewardRate,
                         BigDecimal maxRewardRate,
                         String status,
                         @RequestParam(defaultValue = "0")int pageNum,
                         @RequestParam(defaultValue = "30" )int pagesize){

        productService.query();

    }

}
