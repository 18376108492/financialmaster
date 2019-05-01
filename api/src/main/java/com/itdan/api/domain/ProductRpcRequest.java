package com.itdan.api.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 产品查询相应实体类
 */

public class ProductRpcRequest implements Serializable {

    private List<String> ids;
    private BigDecimal minRewardRate;
    private BigDecimal maxRewardRate;
    private List<String> status;
    private Pageable pageable;

    @Override
    public String toString() {
        return "ProductRpcRequest{" +
                "ids=" + ids +
                ", minRewardRate=" + minRewardRate +
                ", maxRewardRate=" + maxRewardRate +
                ", status=" + status +
                ", pageable=" + pageable +
                '}';
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public BigDecimal getMinRewardRate() {
        return minRewardRate;
    }

    public void setMinRewardRate(BigDecimal minRewardRate) {
        this.minRewardRate = minRewardRate;
    }

    public BigDecimal getMaxRewardRate() {
        return maxRewardRate;
    }

    public void setMaxRewardRate(BigDecimal maxRewardRate) {
        this.maxRewardRate = maxRewardRate;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
