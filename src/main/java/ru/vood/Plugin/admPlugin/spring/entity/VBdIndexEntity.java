package ru.vood.Plugin.admPlugin.spring.entity;

import ru.vood.Plugin.admPlugin.spring.context.LoadedCTX;
import ru.vood.Plugin.admPlugin.spring.intf.CommonFunctionService;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "collectionId")//(fetch = FetchType.LAZY)
    //@JoinColumn(name = "COLUMNS", referencedColumnName = "COLLECTION_ID")
    private List<VBdIndexedColomnsEntity> colomnsEntities;

    @Basic
    @Column(name = "COLUMNS", nullable = true, length = 1)
    private BigDecimal columns;

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

    public List<VBdIndexedColomnsEntity> getColomnsEntities() {
        return colomnsEntities;
    }

    public void setColomnsEntities(List<VBdIndexedColomnsEntity> colomnsEntities) {
        this.colomnsEntities = colomnsEntities;
    }

    public BigDecimal getColumns() {
        return columns;
    }

    public void setColumns(BigDecimal columns) {
        this.columns = columns;
    }

    public void addColomn(VBdIndexedColomnsEntity entity) {
        if (columns == null) {
            columns = LoadedCTX.getService(CommonFunctionService.class).nextId();
        }
        if (colomnsEntities == null) {
            colomnsEntities = new ArrayList<VBdIndexedColomnsEntity>();
        }
        entity.setCollectionId(columns);
        colomnsEntities.add(entity);
    }
}
