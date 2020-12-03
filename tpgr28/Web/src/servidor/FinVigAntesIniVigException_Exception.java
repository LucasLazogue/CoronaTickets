
package servidor;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "FinVigAntesIniVigException", targetNamespace = "http://servidor/")
public class FinVigAntesIniVigException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private FinVigAntesIniVigException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public FinVigAntesIniVigException_Exception(String message, FinVigAntesIniVigException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public FinVigAntesIniVigException_Exception(String message, FinVigAntesIniVigException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.FinVigAntesIniVigException
     */
    public FinVigAntesIniVigException getFaultInfo() {
        return faultInfo;
    }

}
