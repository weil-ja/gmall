package com.atguigu.gmall.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @param
 * @return
 */
public class PmsBaseSaleAttr implements Serializable {

    @Id
    @Column
    String id ;

    @Column
    String name;

    public String getId() {
        return id;
    }

    public PmsBaseSaleAttr setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PmsBaseSaleAttr setName(String name) {
        this.name = name;
        return this;
    }
}