package ru.vood.Plugin.admPlugin.spring.entity;

import javax.persistence.*;
import java.math.BigDecimal;

import static ru.vood.Plugin.admPlugin.spring.entity.ParentForAll.SCHEMA;

@Entity
@Table(name = "V_BD_OBJECT_TYPE", schema = SCHEMA, catalog = "")
public class VBdObjectTypeEntity extends ParentForAll {
    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "seqId", sequenceName = "SEQ_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqId")
    private BigDecimal id;

    @Basic
    @Column(name = "CODE", nullable = false, length = 50)
    private String code;

    @Basic
    @Column(name = "NAME", nullable = false, length = 250)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT", referencedColumnName = "ID")
    private VBdObjectTypeEntity parent;


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VBdObjectTypeEntity)) return false;

        VBdObjectTypeEntity entity = (VBdObjectTypeEntity) o;

        return getCode().equals(entity.getCode());
    }

    @Override
    public int hashCode() {
        return getCode().hashCode();
    }
}
