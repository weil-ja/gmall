package com.atguigu.gmall.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @return
 */
public class PmsBaseCatalog1 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;

    @Transient
    private List<PmsBaseCatalog2> catalog2s;

    public String getId() {
        return id;
    }

    public PmsBaseCatalog1 setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PmsBaseCatalog1 setName(String name) {
        this.name = name;
        return this;
    }

    public List<PmsBaseCatalog2> getCatalog2s() {
        return catalog2s;
    }

    public PmsBaseCatalog1 setCatalog2s(List<PmsBaseCatalog2> catalog2s) {
        this.catalog2s = catalog2s;
        return this;
    }
}

