
package servidor;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "EstadoEspectaculoException", targetNamespace = "http://servidor/")
public class EstadoEspectaculoException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private EstadoEspectaculoException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public EstadoEspectaculoException_Exception(String message, EstadoEspectaculoException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public EstadoEspectaculoException_Exception(String message, EstadoEspectaculoException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.EstadoEspectaculoException
     */
    public EstadoEspectaculoException getFaultInfo() {
        return faultInfo;
    }

}
