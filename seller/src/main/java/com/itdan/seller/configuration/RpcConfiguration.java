package com.itdan.seller.configuration;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcClientProxyCreator;
import com.itdan.api.ProductRpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URL;


/**
 * rpc相应配置
 */
@Configuration
public class RpcConfiguration {

    //获取日志对象
    private Logger logger=LoggerFactory.getLogger(RpcConfiguration.class);

    @Bean
    public AutoJsonRpcClientProxyCreator serviceImplExporter(@Value("${rpc.manager.url}") String url){

        AutoJsonRpcClientProxyCreator creator=new AutoJsonRpcClientProxyCreator();
        try {
            creator.setBaseUrl(new URL(url));
        }catch (Exception e){
         e.printStackTrace();
         logger.error("创建RPC地址出错");
        }
        //扫描rpc相应的包
        creator.setScanPackage(ProductRpc.class.getPackage().getName());
        return creator;
    }

}
