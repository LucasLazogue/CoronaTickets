
package servidor;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "PaqueteRepetidoException", targetNamespace = "http://servidor/")
public class PaqueteRepetidoException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PaqueteRepetidoException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public PaqueteRepetidoException_Exception(String message, PaqueteRepetidoException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public PaqueteRepetidoException_Exception(String message, PaqueteRepetidoException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.PaqueteRepetidoException
     */
    public PaqueteRepetidoException getFaultInfo() {
        return faultInfo;
    }

}
