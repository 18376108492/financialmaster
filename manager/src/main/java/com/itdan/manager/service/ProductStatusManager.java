package com.itdan.manager.service;

import com.itdan.api.events.ProductStatusEvent;
import com.itdan.entity.enums.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

/**
 * 产品状态管理
 */
public class ProductStatusManager {

    //定义消息收件地址
    private  final  String MQ_DESTINATIOM="VirtualTopic.productStatus";

    @Autowired
    private JmsTemplate jmsTemplate;



    /**
     * 修改产品的状态
     * @param id 产品id
     * @param status 更新后的状态
     */
    public void changeStatuc(Integer id, ProductStatus status){

        ProductStatusEvent productStatusEvent=new ProductStatusEvent(id,status);
        jmsTemplate.convertAndSend("MQ_DESTINATIOM",productStatusEvent);
    }
}
