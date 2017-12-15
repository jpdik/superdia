
package com.cdyne.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CheckCCResult" type="{http://ws.cdyne.com/}ReturnIndicator" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "checkCCResult"
})
@XmlRootElement(name = "CheckCCResponse")
public class CheckCCResponse {

    @XmlElement(name = "CheckCCResult")
    protected ReturnIndicator checkCCResult;

    /**
     * Obtém o valor da propriedade checkCCResult.
     * 
     * @return
     *     possible object is
     *     {@link ReturnIndicator }
     *     
     */
    public ReturnIndicator getCheckCCResult() {
        return checkCCResult;
    }

    /**
     * Define o valor da propriedade checkCCResult.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnIndicator }
     *     
     */
    public void setCheckCCResult(ReturnIndicator value) {
        this.checkCCResult = value;
    }

}
