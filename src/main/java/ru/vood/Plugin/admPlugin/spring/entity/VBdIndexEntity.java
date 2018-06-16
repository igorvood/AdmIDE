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
    private String uniqueI = "0";

    @Basic
    @Column(name = "GLOBAL_I", nullable = true, length = 1)
    private String globalI = "0";

    //    @JoinTable(name = "contact_hobby_detail",
//            joinColumns = @JoinColumn(name = "CONTACT_ID"),
//            inverseJoinColumns = @JoinColumn(name = "HOBBY_ID"))
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "collectionId")//(mappedBy = "columns")//(fetch = FetchType.LAZY)
//    @JoinColumn(table = "VBdIndexedColomnsEntity", name = "VBdIndexedColomnsEntity", referencedColumnName = "columns")
/*    @OneToMany//(mappedBy = "collectionId", fetch = FetchType.EAGER)
    @JoinColumn(name = "COLUMNS", referencedColumnName = COLLECTION)*/
    @Transient
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

    public void addColomn(VBdColomnsEntity entity) {
        VBdIndexedColomnsEntity indexedColomnsEntity = new VBdIndexedColomnsEntity();
        indexedColomnsEntity.setColomnRef(entity);
        addColomn(indexedColomnsEntity);
    }

}
