package ru.vood.Plugin.admPlugin.spring.entity;

import javax.persistence.*;
import java.math.BigDecimal;

import static ru.vood.Plugin.admPlugin.spring.entity.ParentForAll.SCHEMA;

@Entity
@Table(name = "V_BD_INDEX", schema = SCHEMA, catalog = "")
public class VBdIndexEntity extends VBdObjectEntity {

    @Basic
    @Column(name = "UNIQUE_I", nullable = true, length = 1)
    private String uniqueI;

    @Basic
    @Column(name = "GLOBAL_I", nullable = true, length = 1)
    private String globalI;

    @Basic
    @SequenceGenerator(name = "seqId", sequenceName = "SEQ_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqId")
    @Column(name = "COLUMNS", nullable = true, length = 1)
    private BigDecimal columns;

//    @Basic
//    @Column(name = "LIST_COLUMNS", nullable = false, length = 250)
//    private String listColumns;

    public String getUniqueI() {
        return uniqueI;
    }

    public void setUniqueI(String uniqueI) {
        this.uniqueI = uniqueI;
    }

    public String getGlobalI() {
        return globalI;
    }

    public void setGlobalI(String globalI) {
        this.globalI = globalI;
    }

    public BigDecimal getColumns() {
        return columns;
    }

    public void setColumns(BigDecimal columns) {
        this.columns = columns;
    }

    public String toString() {
        return "VBdIndexEntity{" +
                "uniqueI='" + uniqueI + '\'' +
                ", globalI='" + globalI + '\'' +
                ", listColumns='" + columns + '\'' +
                '}';
    }

}
