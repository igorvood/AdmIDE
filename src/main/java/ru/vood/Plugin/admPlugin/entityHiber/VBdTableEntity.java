package ru.vood.Plugin.admPlugin.entityHiber;

import javax.persistence.*;

import static ru.vood.Plugin.admPlugin.entityHiber.ParentForAll.SCHEMA;

@Entity
@Table(name = "V_BD_TABLE", schema = SCHEMA, catalog = "")
public class VBdTableEntity extends VBdObjectEntity {

    private String tableSpace;
    private String storage;
    private Long length;
    private Long precision;
    private VBdTableEntity toType;

    @Basic
    @Column(name = "TABLE_SPACE", nullable = true, length = 50)
    public String getTableSpace() {
        return tableSpace;
    }

    public void setTableSpace(String tableSpace) {
        this.tableSpace = tableSpace;
    }

    @Basic
    @Column(name = "STORAGE", nullable = true, length = 500)
    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    @Basic
    @Column(name = "LENGTH", nullable = true, precision = 0)
    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Basic
    @Column(name = "PRECISION", nullable = true, precision = 0)
    public Long getPrecision() {
        return precision;
    }

    public void setPrecision(Long precision) {
        this.precision = precision;
    }

    @ManyToOne
    @JoinColumn(name = "TO_TYPE", referencedColumnName = "ID", nullable = true)
    public VBdTableEntity getToType() {
        return toType;
    }

    public void setToType(VBdTableEntity toType) {
        this.toType = toType;
    }
}
