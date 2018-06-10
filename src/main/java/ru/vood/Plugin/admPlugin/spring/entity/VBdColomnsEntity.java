package ru.vood.Plugin.admPlugin.spring.entity;

import javax.persistence.*;

import static ru.vood.Plugin.admPlugin.spring.entity.ParentForAll.SCHEMA;

@Entity
@Table(name = "V_BD_COLOMNS", schema = SCHEMA, catalog = "")
public class VBdColomnsEntity extends VBdObjectEntity {

    @Basic
    @Column(name = "NOT_NULL", nullable = true, length = 1)
    private String notNull;

    @ManyToOne
    @JoinColumn(name = "TYPE_COLOMN", referencedColumnName = "ID", nullable = true)
    private VBdObjectTypeEntity typeColomn;

    @ManyToOne
    @JoinColumn(name = "TYPE_VALUE", referencedColumnName = "ID", nullable = true)
    private VBdObjectEntity typeValue;

    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    public VBdObjectTypeEntity getTypeColomn() {
        return typeColomn;
    }

    public void setTypeColomn(VBdObjectTypeEntity typeColomn) {
        this.typeColomn = typeColomn;
    }

    public VBdObjectEntity getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(VBdObjectEntity typeValue) {
        this.typeValue = typeValue;
    }


}
