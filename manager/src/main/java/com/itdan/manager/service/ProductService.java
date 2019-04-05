package com.itdan.manager.service;

import com.itdan.entity.enums.ProductStatus;
import com.itdan.entity.pojo.Product;
import com.itdan.manager.error.ErrorEnum;
import com.itdan.manager.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 产品业务逻辑层
 */
@Service
@Transactional
public class ProductService {

    private static Logger logger=LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    /**
     * 添加产品
     * @param product 产品对象
     * @return
     */
    public Product addProduct(Product product) {
        logger.debug("添加产品的参数为:{}",product);
        //校验数据
        checkProduck(product);
        //设置默认数据
        setDefault(product);
        //保存数据
       Product reslut=  productRepository.save(product);
       return  reslut;
    }

    /**
     * 根据传入的ID查询相应的产品
     * @param id 产品编号
     * @return
     */
    public Product getById(String id){
        Assert.notNull(id,ErrorEnum.ID_IN_NULL.toString());
        //检验ID是否为空
        Product product=productRepository.findById(id).get();
        return product;
    }

    /**
     * 分页查询
     * @param idList id列表
     * @param minRewardRate 收益率下限
     * @param maxRewardRate 收益率上限
     * @param statusList 状态列表
     * @param pageable 分页条件
     * @return
     */
    public Page<Product> query(List<String> idList,
                               BigDecimal minRewardRate,
                               BigDecimal maxRewardRate,
                               List<String>statusList,
                               Pageable pageable){
        Specification<Product> specification=new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取编号的列
                Expression<String> idCol=root.get("id");
                //获取收益率列
                Expression<BigDecimal> rewardRate=root.get("rewardRate");
                //获取状态列
                Expression<String> status=root.get("status");
                List<Predicate> predicateList=new ArrayList<>();

                //添加条件
                if (idList!=null&&idList.size()>0){
                     predicateList.add(idCol.in(idList));
                }
                if(minRewardRate!= null && BigDecimal.ZERO.compareTo(minRewardRate) < 0){
                    predicateList.add(criteriaBuilder.ge(rewardRate,minRewardRate));//ge大于等于
                }
                if(maxRewardRate !=null&&BigDecimal.ZERO.compareTo(maxRewardRate)<0){
                    predicateList.add(criteriaBuilder.le(rewardRate,maxRewardRate));//le小于等于
                }
                if (statusList!=null&&statusList.size()>0){
                    predicateList.add(status.in(statusList));
                }

                //执行查询
                criteriaQuery.where(predicateList.toArray(new Predicate[0]));
                return null;
            }

        };
        Page<Product> productPage=  productRepository.findAll(specification,pageable);
        return productPage;

    }



    /**
     * 设置默认数据
     * 例如：创建时间，更新时间,投资步长，锁定期，状态
     *
     * @param product 产品对象
     */
    private void setDefault(Product product) {
        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        }
        if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        }
        if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        }
        if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        }
        if (product.getStatus() == null) {
            product.setStatus(ProductStatus.AUDITING.name());
        }
    }


    /**
     * 检验数据
     * 1.非空校验
     * 2.收益率在0~30%以内
     * 3.投资步长为整数
     *
     * @param product 产品对象
     */
    private void checkProduck(Product product) {
        Assert.notNull(product.getId(), ErrorEnum.ID_IN_NULL.toString());
        //其他非空校验
        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) >= 0, "收益率范围错误");
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0, "投资步长需为整数");
    }

}
