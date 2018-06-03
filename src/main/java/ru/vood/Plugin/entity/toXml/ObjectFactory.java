package ru.vood.Plugin.entity.toXml;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the ru.vood.Plugin.entity.toXml package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.vood.Plugin.entity.toXml
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ObjectT }
     */
    public ObjectT createObjectT() {
        return new ObjectT();
    }

    /**
     * Create an instance of {@link Objects }
     */
    public Objects createObjects() {
        return new Objects();
    }

    /**
     * Create an instance of {@link FieldT }
     */
    public FieldT createFieldT() {
        return new FieldT();
    }

    /**
     * Create an instance of {@link ObjectT.Fields }
     */
    public ObjectT.Fields createObjectTFields() {
        return new ObjectT.Fields();
    }

}
