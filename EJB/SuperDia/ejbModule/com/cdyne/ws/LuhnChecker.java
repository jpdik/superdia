
package com.cdyne.ws;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * Validates Credit Cards to ensure proper input. This is a FREE CDYNE service ran off of our secure servers. You may use it as much as you wish. If you would like the code please visit
 * <a href="http://wiki.cdyne.com/wiki/index.php?title=Credit_Card_Verification">our wiki</a>
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "LuhnChecker", targetNamespace = "http://ws.cdyne.com/", wsdlLocation = "https://ws.cdyne.com/creditcardverify/luhnchecker.asmx?WSDL")
public class LuhnChecker
    extends Service
{

    private final static URL LUHNCHECKER_WSDL_LOCATION;
    private final static WebServiceException LUHNCHECKER_EXCEPTION;
    private final static QName LUHNCHECKER_QNAME = new QName("http://ws.cdyne.com/", "LuhnChecker");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://ws.cdyne.com/creditcardverify/luhnchecker.asmx?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        LUHNCHECKER_WSDL_LOCATION = url;
        LUHNCHECKER_EXCEPTION = e;
    }

    public LuhnChecker() {
        super(__getWsdlLocation(), LUHNCHECKER_QNAME);
    }

    public LuhnChecker(WebServiceFeature... features) {
        super(__getWsdlLocation(), LUHNCHECKER_QNAME, features);
    }

    public LuhnChecker(URL wsdlLocation) {
        super(wsdlLocation, LUHNCHECKER_QNAME);
    }

    public LuhnChecker(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, LUHNCHECKER_QNAME, features);
    }

    public LuhnChecker(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public LuhnChecker(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns LuhnCheckerSoap
     */
    @WebEndpoint(name = "LuhnCheckerSoap")
    public LuhnCheckerSoap getLuhnCheckerSoap() {
        return super.getPort(new QName("http://ws.cdyne.com/", "LuhnCheckerSoap"), LuhnCheckerSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns LuhnCheckerSoap
     */
    @WebEndpoint(name = "LuhnCheckerSoap")
    public LuhnCheckerSoap getLuhnCheckerSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.cdyne.com/", "LuhnCheckerSoap"), LuhnCheckerSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (LUHNCHECKER_EXCEPTION!= null) {
            throw LUHNCHECKER_EXCEPTION;
        }
        return LUHNCHECKER_WSDL_LOCATION;
    }

}
