
package servidor;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "YaComproPaqueteException", targetNamespace = "http://servidor/")
public class YaComproPaqueteException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private YaComproPaqueteException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public YaComproPaqueteException_Exception(String message, YaComproPaqueteException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public YaComproPaqueteException_Exception(String message, YaComproPaqueteException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.YaComproPaqueteException
     */
    public YaComproPaqueteException getFaultInfo() {
        return faultInfo;
    }

}