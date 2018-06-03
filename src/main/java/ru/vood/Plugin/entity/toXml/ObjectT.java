package ru.vood.Plugin.entity.toXml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ObjectT complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ObjectT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TableName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Fields" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence maxOccurs="unbounded">
 *                   &lt;element name="Field" type="{http://ru.vood.DBEntity}FieldT"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectT", propOrder = {"tableName", "javaClassName", "fields"})
public class ObjectT implements ru.vood.core.runtime.type.i.Clonable<ObjectT> {

    @XmlElement(name = "TableName", required = true)
    protected String tableName;
    @XmlElement(name = "JavaClassName", required = false)
    protected String javaClassName;

    @XmlElement(name = "Fields")
    protected ObjectT.Fields fields;

    /**
     * Gets the value of the tableName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets the value of the tableName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTableName(String value) {
        this.tableName = value;
    }

    /**
     * Gets the value of the fields property.
     *
     * @return possible object is
     * {@link ObjectT.Fields }
     */
    public ObjectT.Fields getFields() {
        return fields;
    }

    /**
     * Sets the value of the fields property.
     *
     * @param value allowed object is
     *              {@link ObjectT.Fields }
     */
    public void setFields(ObjectT.Fields value) {
        this.fields = value;
    }

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }

    public ObjectT copy() {
        ObjectT objectT = new ObjectT();
        objectT.setFields(this.getFields());
        objectT.setJavaClassName(this.getJavaClassName());
        objectT.setTableName(this.getTableName());
        return objectT;
    }

    /**
     * <p>Java class for anonymous complex type.
     * <p/>
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p/>
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence maxOccurs="unbounded">
     *         &lt;element name="Field" type="{http://ru.vood.DBEntity}FieldT"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {"field"})
    public static class Fields {

        @XmlElement(name = "Field", required = true)
        protected List<FieldT> field;

        /**
         * Gets the value of the field property.
         * <p/>
         * <p/>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the field property.
         * <p/>
         * <p/>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getField().add(newItem);
         * </pre>
         * <p/>
         * <p/>
         * <p/>
         * Objects of the following type(s) are allowed in the list
         * {@link FieldT }
         */
        public List<FieldT> getField() {
            if (field == null) {
                field = new ArrayList<FieldT>();
            }
            return this.field;
        }

    }

}
