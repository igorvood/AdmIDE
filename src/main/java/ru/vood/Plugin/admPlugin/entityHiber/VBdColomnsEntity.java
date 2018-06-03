package ru.vood.Plugin.admPlugin.entityHiber;

import javax.persistence.*;

import static ru.vood.Plugin.admPlugin.entityHiber.ParentForAll.SCHEMA;

@Entity
@Table(name = "V_BD_COLOMNS", schema = SCHEMA, catalog = "")
public class VBdColomnsEntity extends VBdObjectEntity {
    private String notNull;
    private VBdObjectTypeEntity typeColomn;
    private VBdObjectEntity typeValue;

    @Basic
    @Column(name = "NOT_NULL", nullable = true, length = 1)
    public String getNotNull() {
        return notNull;
    }

    public void setNotNull(String notNull) {
        this.notNull = notNull;
    }

    @ManyToOne
    @JoinColumn(name = "TYPE_COLOMN", referencedColumnName = "ID", nullable = true)
    public VBdObjectTypeEntity getTypeColomn() {
        return typeColomn;
    }

    public void setTypeColomn(VBdObjectTypeEntity typeColomn) {
        this.typeColomn = typeColomn;
    }

    @ManyToOne
    @JoinColumn(name = "TYPE_VALUE", referencedColumnName = "ID", nullable = true)
    public VBdObjectEntity getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(VBdObjectEntity typeValue) {
        this.typeValue = typeValue;
    }


}
