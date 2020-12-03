
package servidor;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "EspectadorRegistradoEnFuncionExepcion", targetNamespace = "http://servidor/")
public class EspectadorRegistradoEnFuncionExepcion_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private EspectadorRegistradoEnFuncionExepcion faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public EspectadorRegistradoEnFuncionExepcion_Exception(String message, EspectadorRegistradoEnFuncionExepcion faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public EspectadorRegistradoEnFuncionExepcion_Exception(String message, EspectadorRegistradoEnFuncionExepcion faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.EspectadorRegistradoEnFuncionExepcion
     */
    public EspectadorRegistradoEnFuncionExepcion getFaultInfo() {
        return faultInfo;
    }

}
