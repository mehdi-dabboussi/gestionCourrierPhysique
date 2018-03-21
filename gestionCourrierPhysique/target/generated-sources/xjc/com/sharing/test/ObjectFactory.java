//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.04.27 à 10:22:09 AM CEST 
//


package com.sharing.test;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.sharing.test package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AnyTypeMethodsObject_QNAME = new QName("http://webServiceExample.sharing.com", "object");
    private final static QName _AnyTypeMethodsResponseReturn_QNAME = new QName("http://webServiceExample.sharing.com", "return");
    private final static QName _ShowSupplierSupplier_QNAME = new QName("http://webServiceExample.sharing.com", "supplier");
    private final static QName _SupplierAge_QNAME = new QName("http://webServiceExample.sharing.com/xsd", "age");
    private final static QName _SupplierName_QNAME = new QName("http://webServiceExample.sharing.com/xsd", "name");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.sharing.test
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Supplier }
     * 
     */
    public Supplier createSupplier() {
        return new Supplier();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link GetAsObject }
     * 
     */
    public GetAsObject createGetAsObject() {
        return new GetAsObject();
    }

    /**
     * Create an instance of {@link GetAsObjectResponse }
     * 
     */
    public GetAsObjectResponse createGetAsObjectResponse() {
        return new GetAsObjectResponse();
    }

    /**
     * Create an instance of {@link ListParameters }
     * 
     */
    public ListParameters createListParameters() {
        return new ListParameters();
    }

    /**
     * Create an instance of {@link ListParametersResponse }
     * 
     */
    public ListParametersResponse createListParametersResponse() {
        return new ListParametersResponse();
    }

    /**
     * Create an instance of {@link AnyTypeHashMap }
     * 
     */
    public AnyTypeHashMap createAnyTypeHashMap() {
        return new AnyTypeHashMap();
    }

    /**
     * Create an instance of {@link AnyTypeHashMapResponse }
     * 
     */
    public AnyTypeHashMapResponse createAnyTypeHashMapResponse() {
        return new AnyTypeHashMapResponse();
    }

    /**
     * Create an instance of {@link AnyTypeMethods }
     * 
     */
    public AnyTypeMethods createAnyTypeMethods() {
        return new AnyTypeMethods();
    }

    /**
     * Create an instance of {@link AnyTypeMethodsResponse }
     * 
     */
    public AnyTypeMethodsResponse createAnyTypeMethodsResponse() {
        return new AnyTypeMethodsResponse();
    }

    /**
     * Create an instance of {@link ShowSupplier }
     * 
     */
    public ShowSupplier createShowSupplier() {
        return new ShowSupplier();
    }

    /**
     * Create an instance of {@link ShowSupplierResponse }
     * 
     */
    public ShowSupplierResponse createShowSupplierResponse() {
        return new ShowSupplierResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServiceExample.sharing.com", name = "object", scope = AnyTypeMethods.class)
    public JAXBElement<Object> createAnyTypeMethodsObject(Object value) {
        return new JAXBElement<Object>(_AnyTypeMethodsObject_QNAME, Object.class, AnyTypeMethods.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServiceExample.sharing.com", name = "return", scope = AnyTypeMethodsResponse.class)
    public JAXBElement<Object> createAnyTypeMethodsResponseReturn(Object value) {
        return new JAXBElement<Object>(_AnyTypeMethodsResponseReturn_QNAME, Object.class, AnyTypeMethodsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Supplier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServiceExample.sharing.com", name = "supplier", scope = ShowSupplier.class)
    public JAXBElement<Supplier> createShowSupplierSupplier(Supplier value) {
        return new JAXBElement<Supplier>(_ShowSupplierSupplier_QNAME, Supplier.class, ShowSupplier.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Supplier }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServiceExample.sharing.com", name = "return", scope = ShowSupplierResponse.class)
    public JAXBElement<Supplier> createShowSupplierResponseReturn(Supplier value) {
        return new JAXBElement<Supplier>(_AnyTypeMethodsResponseReturn_QNAME, Supplier.class, ShowSupplierResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServiceExample.sharing.com/xsd", name = "age", scope = Supplier.class)
    public JAXBElement<String> createSupplierAge(String value) {
        return new JAXBElement<String>(_SupplierAge_QNAME, String.class, Supplier.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webServiceExample.sharing.com/xsd", name = "name", scope = Supplier.class)
    public JAXBElement<String> createSupplierName(String value) {
        return new JAXBElement<String>(_SupplierName_QNAME, String.class, Supplier.class, value);
    }

}
