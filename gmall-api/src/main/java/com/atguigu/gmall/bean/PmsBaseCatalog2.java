package com.atguigu.gmall.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @param
 * @return
 */
public class PmsBaseCatalog2 implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column
    private String name;
    @Column
    private String catalog1Id;

    @Transient
    private List<PmsBaseCatalog3> catalog3List;

    public String getId() {
        return id;
    }

    public PmsBaseCatalog2 setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PmsBaseCatalog2 setName(String name) {
        this.name = name;
        return this;
    }

    public String getCatalog1Id() {
        return catalog1Id;
    }

    public PmsBaseCatalog2 setCatalog1Id(String catalog1Id) {
        this.catalog1Id = catalog1Id;
        return this;
    }

    public List<PmsBaseCatalog3> getCatalog3List() {
        return catalog3List;
    }

    public PmsBaseCatalog2 setCatalog3List(List<PmsBaseCatalog3> catalog3List) {
        this.catalog3List = catalog3List;
        return this;
    }
}
