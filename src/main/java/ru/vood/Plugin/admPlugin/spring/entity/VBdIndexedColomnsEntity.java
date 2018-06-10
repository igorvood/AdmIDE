package ru.vood.Plugin.admPlugin.spring.entity;

import ru.vood.Plugin.sql.additionalSteps.oracle.stepFirstLoad.LIndexedColumns;

import javax.persistence.*;
import java.math.BigDecimal;

import static ru.vood.Plugin.admPlugin.spring.entity.ParentForAll.SCHEMA;

@Entity
@Table(name = LIndexedColumns.tableName, schema = SCHEMA, catalog = "")
public class VBdIndexedColomnsEntity {

    @Id
    @SequenceGenerator(name = "seqId", sequenceName = "SEQ_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqId")
    @Column(name = "ID", nullable = false, precision = 0)
    private BigDecimal id;

    @Basic
    @Column(name = "COLLECTION_ID")
    private BigDecimal collectionId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COLUMN_REF", referencedColumnName = "ID", nullable = false)
    private VBdColomnsEntity colomnRef;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(BigDecimal collectionId) {
        this.collectionId = collectionId;
    }

    public VBdColomnsEntity getColomnRef() {
        return colomnRef;
    }

    public void setColomnRef(VBdColomnsEntity colomnRef) {
        this.colomnRef = colomnRef;
    }
}
