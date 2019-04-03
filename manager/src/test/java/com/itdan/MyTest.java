package com.itdan;

import com.itdan.entity.pojo.Product;
import com.itdan.manager.ManagerApplication;
import com.itdan.manager.repositories.ProductRepository;
import com.itdan.manager.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ManagerApplication.class)
public class MyTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testPruductById () throws Exception{
     Product product= productService.getById("80");
        System.out.println(product.toString());
    }

}