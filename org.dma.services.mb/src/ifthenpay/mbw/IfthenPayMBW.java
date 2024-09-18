
package ifthenpay.mbw;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * <font  size="4"><strong>WebService de Suporte ao Serviço <br /><font color="#008C8C">MBWay da Ifthenpay</font>.</strong></font> <br /><br />
 *
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 *
 */
@WebServiceClient(name = "IfthenPayMBW", targetNamespace = "https://www.ifthenpay.com/", wsdlLocation = "file:wsdl/ifthenpaymbw.xml")
public class IfthenPayMBW
    extends Service
{

    private final static URL IFTHENPAYMBW_WSDL_LOCATION;
    private final static WebServiceException IFTHENPAYMBW_EXCEPTION;
    private final static QName IFTHENPAYMBW_QNAME = new QName("https://www.ifthenpay.com/", "IfthenPayMBW");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = IfthenPayMBW.class.getClassLoader().getResource("wsdl/ifthenpaymbw.xml");
        } catch (Exception ex) {
            e = new WebServiceException(ex);
        }
        IFTHENPAYMBW_WSDL_LOCATION = url;
        IFTHENPAYMBW_EXCEPTION = e;
    }

    public IfthenPayMBW() {
        super(__getWsdlLocation(), IFTHENPAYMBW_QNAME);
    }

    public IfthenPayMBW(WebServiceFeature... features) {
        super(__getWsdlLocation(), IFTHENPAYMBW_QNAME, features);
    }

    public IfthenPayMBW(URL wsdlLocation) {
        super(wsdlLocation, IFTHENPAYMBW_QNAME);
    }

    public IfthenPayMBW(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, IFTHENPAYMBW_QNAME, features);
    }

    public IfthenPayMBW(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public IfthenPayMBW(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns IfthenPayMBWSoap
     */
    @WebEndpoint(name = "IfthenPayMBWSoap")
    public IfthenPayMBWSoap getIfthenPayMBWSoap() {
        return super.getPort(new QName("https://www.ifthenpay.com/", "IfthenPayMBWSoap"), IfthenPayMBWSoap.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IfthenPayMBWSoap
     */
    @WebEndpoint(name = "IfthenPayMBWSoap")
    public IfthenPayMBWSoap getIfthenPayMBWSoap(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.ifthenpay.com/", "IfthenPayMBWSoap"), IfthenPayMBWSoap.class, features);
    }

    /**
     *
     * @return
     *     returns IfthenPayMBWSoap
     */
    @WebEndpoint(name = "IfthenPayMBWSoap12")
    public IfthenPayMBWSoap getIfthenPayMBWSoap12() {
        return super.getPort(new QName("https://www.ifthenpay.com/", "IfthenPayMBWSoap12"), IfthenPayMBWSoap.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IfthenPayMBWSoap
     */
    @WebEndpoint(name = "IfthenPayMBWSoap12")
    public IfthenPayMBWSoap getIfthenPayMBWSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.ifthenpay.com/", "IfthenPayMBWSoap12"), IfthenPayMBWSoap.class, features);
    }

    /**
     *
     * @return
     *     returns IfthenPayMBWHttpGet
     */
    @WebEndpoint(name = "IfthenPayMBWHttpGet")
    public IfthenPayMBWHttpGet getIfthenPayMBWHttpGet() {
        return super.getPort(new QName("https://www.ifthenpay.com/", "IfthenPayMBWHttpGet"), IfthenPayMBWHttpGet.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IfthenPayMBWHttpGet
     */
    @WebEndpoint(name = "IfthenPayMBWHttpGet")
    public IfthenPayMBWHttpGet getIfthenPayMBWHttpGet(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.ifthenpay.com/", "IfthenPayMBWHttpGet"), IfthenPayMBWHttpGet.class, features);
    }

    /**
     *
     * @return
     *     returns IfthenPayMBWHttpPost
     */
    @WebEndpoint(name = "IfthenPayMBWHttpPost")
    public IfthenPayMBWHttpPost getIfthenPayMBWHttpPost() {
        return super.getPort(new QName("https://www.ifthenpay.com/", "IfthenPayMBWHttpPost"), IfthenPayMBWHttpPost.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IfthenPayMBWHttpPost
     */
    @WebEndpoint(name = "IfthenPayMBWHttpPost")
    public IfthenPayMBWHttpPost getIfthenPayMBWHttpPost(WebServiceFeature... features) {
        return super.getPort(new QName("https://www.ifthenpay.com/", "IfthenPayMBWHttpPost"), IfthenPayMBWHttpPost.class, features);
    }

    private static URL __getWsdlLocation() {
        if (IFTHENPAYMBW_EXCEPTION!= null) {
            throw IFTHENPAYMBW_EXCEPTION;
        }
        return IFTHENPAYMBW_WSDL_LOCATION;
    }

}