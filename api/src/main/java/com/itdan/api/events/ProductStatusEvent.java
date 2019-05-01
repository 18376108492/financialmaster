package com.itdan.api.events;

import com.itdan.entity.enums.ProductStatus;

import java.io.Serializable;

/**
 * 产品转态实体类
 */
public class ProductStatusEvent  implements Serializable {

    private Integer ID;
    private ProductStatus status;

    public ProductStatusEvent(Integer ID, ProductStatus status) {
        this.ID = ID;
        this.status = status;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
