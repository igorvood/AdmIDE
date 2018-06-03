package ru.vood.Plugin.admPlugin.spring.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

import static ru.vood.Plugin.admPlugin.spring.entity.ParentForAll.SCHEMA;

@Entity
@Table(name = "V_BD_OBJECT", schema = SCHEMA, catalog = "")
@Inheritance(strategy = InheritanceType.JOINED)
public class VBdObjectEntity extends ParentForAll {

    //private BigDecimal id;
    private String code;
    private String name;
    private String javaClass;

    private VBdObjectEntity parent;
    private VBdObjectTypeEntity typeObject;
    private Date dateCreate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT", referencedColumnName = "ID")
    public VBdObjectEntity getParent() {
        return parent;
    }

    public void setParent(VBdObjectEntity parent) {
        this.parent = parent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_OBJECT", referencedColumnName = "ID", nullable = false)
    public VBdObjectTypeEntity getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(VBdObjectTypeEntity typeObject) {
        this.typeObject = typeObject;
    }

    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CREATE", nullable = true, insertable = true, updatable = true)
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Basic
    @Column(name = "JAVA_CLASS", nullable = false, length = 512)
    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }

    public String toString() {
        return name;
        /*return "VBdObjectEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", javaClass='" + javaClass + '\'' +
                ", vBdObjectByParent=" + parent +
                ", typeObject=" + typeObject +
                '}';*/
    }
}
