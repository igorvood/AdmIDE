package ru.vood.Plugin.admPlugin.entityHiber;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static ru.vood.Plugin.admPlugin.entityHiber.ParentForAll.SCHEMA;

@Entity
@Table(name = "V_BD_INDEX", schema = SCHEMA, catalog = "")
public class VBdIndexEntity extends VBdObjectEntity {
    private String uniqueI;
    private String globalI;
    private String listColumns;

    @Basic
    @Column(name = "UNIQUE_I", nullable = true, length = 1)
    public String getUniqueI() {
        return uniqueI;
    }

    public void setUniqueI(String uniqueI) {
        this.uniqueI = uniqueI;
    }

    @Basic
    @Column(name = "GLOBAL_I", nullable = true, length = 1)
    public String getGlobalI() {
        return globalI;
    }

    public void setGlobalI(String globalI) {
        this.globalI = globalI;
    }

    @Basic
    @Column(name = "LIST_COLUMNS", nullable = false, length = 250)
    public String getListColumns() {
        return listColumns;
    }

    public void setListColumns(String listColumns) {
        this.listColumns = listColumns;
    }

    public String toString() {
        return "VBdIndexEntity{" +
                "uniqueI='" + uniqueI + '\'' +
                ", globalI='" + globalI + '\'' +
                ", listColumns='" + listColumns + '\'' +
                '}';
    }

}
