
package com.howtodoinjava.serializer.customerservice;

import com.legstar.coxb.CobolElement;
import com.legstar.coxb.CobolType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for Dfhcommarea complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Dfhcommarea">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="importStructure" type="{http://coxb.test.legstar.com/customerservice}ImportStructure"/>
 *         &lt;element name="exportStructure" type="{http://coxb.test.legstar.com/customerservice}ExportStructure"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dfhcommarea", propOrder = {
    "importStructure",
    "exportStructure"
})
public class Dfhcommarea
    implements Serializable
{

    private final static long serialVersionUID = 1L;
    @XmlElement(required = true)
    @CobolElement(cobolName = "IMPORT-STRUCTURE", type = CobolType.GROUP_ITEM, levelNumber = 5, srceLine = 2)
    protected ImportStructure importStructure;
    @XmlElement(required = true)
    @CobolElement(cobolName = "EXPORT-STRUCTURE", type = CobolType.GROUP_ITEM, levelNumber = 5, srceLine = 4)
    protected ExportStructure exportStructure;

    /**
     * Gets the value of the importStructure property.
     * 
     * @return
     *     possible object is
     *     {@link ImportStructure }
     *     
     */
    public ImportStructure getImportStructure() {
        return importStructure;
    }

    /**
     * Sets the value of the importStructure property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImportStructure }
     *     
     */
    public void setImportStructure(ImportStructure value) {
        this.importStructure = value;
    }

    public boolean isSetImportStructure() {
        return (this.importStructure!= null);
    }

    /**
     * Gets the value of the exportStructure property.
     * 
     * @return
     *     possible object is
     *     {@link ExportStructure }
     *     
     */
    public ExportStructure getExportStructure() {
        return exportStructure;
    }

    /**
     * Sets the value of the exportStructure property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExportStructure }
     *     
     */
    public void setExportStructure(ExportStructure value) {
        this.exportStructure = value;
    }

    public boolean isSetExportStructure() {
        return (this.exportStructure!= null);
    }

    @Override
    public String toString() {
        return "Dfhcommarea{" +
                "importStructure=" + importStructure +
                ", exportStructure=" + exportStructure +
                '}';
    }
}
