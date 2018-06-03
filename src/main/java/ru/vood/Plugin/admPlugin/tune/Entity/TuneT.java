package ru.vood.Plugin.admPlugin.tune.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TuneT complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="TuneT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NameTune" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TuneT", propOrder = {"nameTune", "value"})
@Deprecated
public class TuneT {

    @XmlElement(name = "NameTune", required = true)
    protected String nameTune;
    @XmlElement(name = "Value", required = true)
    protected String value;

    /**
     * Gets the value of the nameTune property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNameTune() {
        return nameTune;
    }

    /**
     * Sets the value of the nameTune property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNameTune(String value) {
        this.nameTune = value;
    }

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

}
