//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2016.04.27 � 10:22:09 AM CEST 
//


package com.sharing.test;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="supplier" type="{http://webServiceExample.sharing.com/xsd}Supplier" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "supplier"
})
@XmlRootElement(name = "showSupplier")
public class ShowSupplier {

    @XmlElementRef(name = "supplier", namespace = "http://webServiceExample.sharing.com", type = JAXBElement.class, required = false)
    protected JAXBElement<Supplier> supplier;

    /**
     * Obtient la valeur de la propri�t� supplier.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Supplier }{@code >}
     *     
     */
    public JAXBElement<Supplier> getSupplier() {
        return supplier;
    }

    /**
     * D�finit la valeur de la propri�t� supplier.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Supplier }{@code >}
     *     
     */
    public void setSupplier(JAXBElement<Supplier> value) {
        this.supplier = value;
    }

}
