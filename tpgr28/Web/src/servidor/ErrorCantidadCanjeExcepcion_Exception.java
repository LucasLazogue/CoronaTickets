
package servidor;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ErrorCantidadCanjeExcepcion", targetNamespace = "http://servidor/")
public class ErrorCantidadCanjeExcepcion_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ErrorCantidadCanjeExcepcion faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ErrorCantidadCanjeExcepcion_Exception(String message, ErrorCantidadCanjeExcepcion faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ErrorCantidadCanjeExcepcion_Exception(String message, ErrorCantidadCanjeExcepcion faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: servidor.ErrorCantidadCanjeExcepcion
     */
    public ErrorCantidadCanjeExcepcion getFaultInfo() {
        return faultInfo;
    }

}
