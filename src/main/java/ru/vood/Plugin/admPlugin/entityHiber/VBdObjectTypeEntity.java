package ru.vood.Plugin.admPlugin.entityHiber;

import javax.persistence.*;
import java.math.BigDecimal;

import static ru.vood.Plugin.admPlugin.entityHiber.ParentForAll.SCHEMA;

@Entity
@Table(name = "V_BD_OBJECT_TYPE", schema = SCHEMA, catalog = "")
public class VBdObjectTypeEntity extends ParentForAll {
    //private BigDecimal id;
    private String code;
    private String name;
    private VBdObjectTypeEntity parent;


    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }


    @Basic
    @Column(name = "CODE", nullable = false, length = 50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT", referencedColumnName = "ID")
    public VBdObjectTypeEntity getParent() {
        return parent;
    }

    public void setParent(VBdObjectTypeEntity parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "VBdObjectTypeEntity{" +
                "id=" + this.getId() +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", vBdObjectTypeByParent=" + parent +
                '}';
    }

}
