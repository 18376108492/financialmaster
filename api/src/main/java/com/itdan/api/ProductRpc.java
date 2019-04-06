package com.itdan.api;

import com.googlecode.jsonrpc4j.JsonRpcService;
import com.itdan.api.domain.ProductRpcRequest;
import com.itdan.entity.pojo.Product;
import org.springframework.data.domain.Page;

/**
 * 产品相关的rpc服务
 */
@JsonRpcService("/products")
public interface ProductRpc {

    /**
     * 获取产品信息并且分页操作
     * @param rpcRequest
     * @return
     */
    Page<Product> query(ProductRpcRequest rpcRequest);

    /**
     * 添加查询对象
     * @param id
     * @return
     */
    Product findOne(String id);
}
