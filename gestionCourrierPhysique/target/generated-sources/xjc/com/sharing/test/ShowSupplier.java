//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2016.04.27 à 10:22:09 AM CEST 
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
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propriété supplier.
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
     * Définit la valeur de la propriété supplier.
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
