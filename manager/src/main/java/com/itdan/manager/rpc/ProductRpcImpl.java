package com.itdan.manager.rpc;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import com.itdan.api.ProductRpc;
import com.itdan.api.domain.ProductRpcRequest;
import com.itdan.entity.pojo.Product;
import com.itdan.manager.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 产品rpc服务业务逻辑实现层
 */
@AutoJsonRpcServiceImpl
@Service
public class ProductRpcImpl implements ProductRpc {

    @Autowired
    private ProductService productService;

    @Override
    public Page<Product> query(ProductRpcRequest rpcRequest) {

        Page<Product> page= productService.query(rpcRequest.getIds(),
                rpcRequest.getMinRewardRate(),
                rpcRequest.getMaxRewardRate(),
                rpcRequest.getStatus(),
                rpcRequest.getPageable());
        return page;
    }

    @Override
    public Product findOne(String id) {
        return productService.getById(id);
    }
}
