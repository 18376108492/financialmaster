package com.itdan.seller.service;

import com.itdan.api.ProductRpc;
import com.itdan.api.domain.ProductRpcRequest;
import com.itdan.entity.enums.OrderStatus;
import com.itdan.entity.enums.ProductStatus;
import com.itdan.entity.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jms.annotation.JmsListener;

import java.util.ArrayList;
import java.util.List;

/**
 * productRpc业务层
 */
public class ProductRpcService {

    private Logger logger=LoggerFactory.getLogger(ProductRpcService.class);

    static  final  String MQ_DESTINATIOM ="Consumer.cache.VirtualTopic.productStatus";
    @Autowired
    private ProductRpc productRpc;


    /**
     * prc请求查询所有商品
     * @return
     */
    public List<Product> findAllProduct(){

        ProductRpcRequest productRpcRequest=new ProductRpcRequest();
        List<String> status=new ArrayList<>();
        status.add(ProductStatus.IN_SELL.name());
        //添加分页操作.
        Pageable pageable=new PageRequest(0,1000,Sort.Direction.DESC,"rewardRate");
        productRpcRequest.setStatus(status);
        productRpcRequest.setPageable(pageable);
        logger.info("rpc查询全部商品请求。",productRpcRequest);
        Page<Product>page= productRpc.query(productRpcRequest);
        logger.info("rpc查询全部商品结果。",page);
        return page.getContent();
    }

    /**
     * 使用消息插件更新缓存
     */
    @JmsListener(destination ="MQ_DESTINATIOM")
    void updateCache(){
        //先清空缓存
        //再更新缓存
    }


}
