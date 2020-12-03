
package servidor;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import net.java.dev.jaxb.array.AnyTypeArray;
import net.java.dev.jaxb.array.IntArray;
import net.java.dev.jaxb.array.StringArray;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Publicador", targetNamespace = "http://servidor/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    net.java.dev.jaxb.array.ObjectFactory.class,
    servidor.ObjectFactory.class
})
public interface Publicador {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @throws EspectaculoNoExisteException_Exception
     * @throws FuncionRepetidaException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/ingresarDatosFuncionRequest", output = "http://servidor/Publicador/ingresarDatosFuncionResponse", fault = {
        @FaultAction(className = FuncionRepetidaException_Exception.class, value = "http://servidor/Publicador/ingresarDatosFuncion/Fault/FuncionRepetidaException"),
        @FaultAction(className = EspectaculoNoExisteException_Exception.class, value = "http://servidor/Publicador/ingresarDatosFuncion/Fault/EspectaculoNoExisteException")
    })
    public void ingresarDatosFuncion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        XMLGregorianCalendar arg1,
        @WebParam(name = "arg2", partName = "arg2")
        StringArray arg2,
        @WebParam(name = "arg3", partName = "arg3")
        XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5)
        throws EspectaculoNoExisteException_Exception, FuncionRepetidaException_Exception
    ;

    /**
     * 
     * @param arg7
     * @param arg6
     * @param arg9
     * @param arg8
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg13
     * @param arg14
     * @param arg1
     * @param arg0
     * @param arg10
     * @param arg11
     * @param arg12
     * @throws UsuarioNoExisteException_Exception
     * @throws MaxEspMenorMinEspException_Exception
     * @throws EspectaculoRepetidoException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/crearEspectaculoRequest", output = "http://servidor/Publicador/crearEspectaculoResponse", fault = {
        @FaultAction(className = EspectaculoRepetidoException_Exception.class, value = "http://servidor/Publicador/crearEspectaculo/Fault/EspectaculoRepetidoException"),
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://servidor/Publicador/crearEspectaculo/Fault/UsuarioNoExisteException"),
        @FaultAction(className = MaxEspMenorMinEspException_Exception.class, value = "http://servidor/Publicador/crearEspectaculo/Fault/MaxEspMenorMinEspException")
    })
    public void crearEspectaculo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        int arg4,
        @WebParam(name = "arg5", partName = "arg5")
        int arg5,
        @WebParam(name = "arg6", partName = "arg6")
        int arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        int arg8,
        @WebParam(name = "arg9", partName = "arg9")
        XMLGregorianCalendar arg9,
        @WebParam(name = "arg10", partName = "arg10")
        StringArray arg10,
        @WebParam(name = "arg11", partName = "arg11")
        String arg11,
        @WebParam(name = "arg12", partName = "arg12")
        String arg12,
        @WebParam(name = "arg13", partName = "arg13")
        int arg13,
        @WebParam(name = "arg14", partName = "arg14")
        String arg14)
        throws EspectaculoRepetidoException_Exception, MaxEspMenorMinEspException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarEspectaculosRequest", output = "http://servidor/Publicador/listarEspectaculosResponse")
    public StringArray listarEspectaculos(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg6
     * @throws NoEsEspectadorExepcion_Exception
     * @throws EspectadorRegistradoEnFuncionExepcion_Exception
     * @throws RegistroNoSePuedeCanjearException_Exception
     * @throws FechaPasadaException_Exception
     * @throws ErrorCantidadCanjeExcepcion_Exception
     * @throws FuncionLlenaExepcion_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/confirmarRegistroAFuncionRequest", output = "http://servidor/Publicador/confirmarRegistroAFuncionResponse", fault = {
        @FaultAction(className = FuncionLlenaExepcion_Exception.class, value = "http://servidor/Publicador/confirmarRegistroAFuncion/Fault/FuncionLlenaExepcion"),
        @FaultAction(className = NoEsEspectadorExepcion_Exception.class, value = "http://servidor/Publicador/confirmarRegistroAFuncion/Fault/NoEsEspectadorExepcion"),
        @FaultAction(className = EspectadorRegistradoEnFuncionExepcion_Exception.class, value = "http://servidor/Publicador/confirmarRegistroAFuncion/Fault/EspectadorRegistradoEnFuncionExepcion"),
        @FaultAction(className = FechaPasadaException_Exception.class, value = "http://servidor/Publicador/confirmarRegistroAFuncion/Fault/FechaPasadaException"),
        @FaultAction(className = RegistroNoSePuedeCanjearException_Exception.class, value = "http://servidor/Publicador/confirmarRegistroAFuncion/Fault/RegistroNoSePuedeCanjearException"),
        @FaultAction(className = ErrorCantidadCanjeExcepcion_Exception.class, value = "http://servidor/Publicador/confirmarRegistroAFuncion/Fault/ErrorCantidadCanjeExcepcion")
    })
    public void confirmarRegistroAFuncion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        StringArray arg5,
        @WebParam(name = "arg6", partName = "arg6")
        XMLGregorianCalendar arg6)
        throws ErrorCantidadCanjeExcepcion_Exception, EspectadorRegistradoEnFuncionExepcion_Exception, FechaPasadaException_Exception, FuncionLlenaExepcion_Exception, NoEsEspectadorExepcion_Exception, RegistroNoSePuedeCanjearException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataEspectaculo
     * @throws EspectaculoNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/seleccionarEspectaculoRequest", output = "http://servidor/Publicador/seleccionarEspectaculoResponse", fault = {
        @FaultAction(className = EspectaculoNoExisteException_Exception.class, value = "http://servidor/Publicador/seleccionarEspectaculo/Fault/EspectaculoNoExisteException")
    })
    public DataEspectaculo seleccionarEspectaculo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws EspectaculoNoExisteException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarFuncionesEspectaculoRequest", output = "http://servidor/Publicador/listarFuncionesEspectaculoResponse")
    public StringArray listarFuncionesEspectaculo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns servidor.DataFuncion
     * @throws EspectaculoNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/consultaFuncionRequest", output = "http://servidor/Publicador/consultaFuncionResponse", fault = {
        @FaultAction(className = EspectaculoNoExisteException_Exception.class, value = "http://servidor/Publicador/consultaFuncion/Fault/EspectaculoNoExisteException")
    })
    public DataFuncion consultaFuncion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws EspectaculoNoExisteException_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg6
     * @throws FinVigAntesIniVigException_Exception
     * @throws PaqueteRepetidoException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/crearPaqueteRequest", output = "http://servidor/Publicador/crearPaqueteResponse", fault = {
        @FaultAction(className = PaqueteRepetidoException_Exception.class, value = "http://servidor/Publicador/crearPaquete/Fault/PaqueteRepetidoException"),
        @FaultAction(className = FinVigAntesIniVigException_Exception.class, value = "http://servidor/Publicador/crearPaquete/Fault/FinVigAntesIniVigException")
    })
    public void crearPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        XMLGregorianCalendar arg2,
        @WebParam(name = "arg3", partName = "arg3")
        XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", partName = "arg4")
        int arg4,
        @WebParam(name = "arg5", partName = "arg5")
        XMLGregorianCalendar arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6)
        throws FinVigAntesIniVigException_Exception, PaqueteRepetidoException_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarPaquetesRequest", output = "http://servidor/Publicador/listarPaquetesResponse")
    public StringArray listarPaquetes();

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarEspectaculosEnPaqueteRequest", output = "http://servidor/Publicador/listarEspectaculosEnPaqueteResponse")
    public StringArray listarEspectaculosEnPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarEspectaculosNoEnPaqueteRequest", output = "http://servidor/Publicador/listarEspectaculosNoEnPaqueteResponse")
    public StringArray listarEspectaculosNoEnPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/agregarEspectaculoAPaqueteRequest", output = "http://servidor/Publicador/agregarEspectaculoAPaqueteResponse")
    public void agregarEspectaculoAPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2);

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getPlataformasRequest", output = "http://servidor/Publicador/getPlataformasResponse")
    public StringArray getPlataformas();

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataPaquete
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/seleccionarPaqueteRequest", output = "http://servidor/Publicador/seleccionarPaqueteResponse")
    public DataPaquete seleccionarPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns servidor.DataEspectaculo
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/seleccionarEspectaculoDePaqueteRequest", output = "http://servidor/Publicador/seleccionarEspectaculoDePaqueteResponse")
    public DataEspectaculo seleccionarEspectaculoDePaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataPaqueteArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarPaqueteValidosRequest", output = "http://servidor/Publicador/listarPaqueteValidosResponse")
    public DataPaqueteArray listarPaqueteValidos(
        @WebParam(name = "arg0", partName = "arg0")
        XMLGregorianCalendar arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarEspectaculosCategoriaRequest", output = "http://servidor/Publicador/listarEspectaculosCategoriaResponse")
    public StringArray listarEspectaculosCategoria(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarEspectaculosEstadoIngresadoRequest", output = "http://servidor/Publicador/listarEspectaculosEstadoIngresadoResponse")
    public StringArray listarEspectaculosEstadoIngresado();

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarCategoriasRequest", output = "http://servidor/Publicador/listarCategoriasResponse")
    public StringArray listarCategorias();

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataPlataforma
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/seleccionarPlataformaRequest", output = "http://servidor/Publicador/seleccionarPlataformaResponse")
    public DataPlataforma seleccionarPlataforma(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.AnyTypeArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/busquedaRequest", output = "http://servidor/Publicador/busquedaResponse")
    public AnyTypeArray busqueda(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.IntArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/valoracionEspectaculoRequest", output = "http://servidor/Publicador/valoracionEspectaculoResponse")
    public IntArray valoracionEspectaculo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getEspectadoresRegistradosRequest", output = "http://servidor/Publicador/getEspectadoresRegistradosResponse")
    public StringArray getEspectadoresRegistrados(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/getEspectadoresGanadoresRequest", output = "http://servidor/Publicador/getEspectadoresGanadoresResponse")
    public StringArray getEspectadoresGanadores(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @throws FechaPasadaException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/sortearPremiosRequest", output = "http://servidor/Publicador/sortearPremiosResponse", fault = {
        @FaultAction(className = FechaPasadaException_Exception.class, value = "http://servidor/Publicador/sortearPremios/Fault/FechaPasadaException")
    })
    public void sortearPremios(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        XMLGregorianCalendar arg3)
        throws FechaPasadaException_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg10
     * @param arg7
     * @param arg6
     * @param arg9
     * @param arg8
     * @throws PasswordErrorException_Exception
     * @throws UsuarioRepetidoExepcion_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/nuevoArtistaRequest", output = "http://servidor/Publicador/nuevoArtistaResponse", fault = {
        @FaultAction(className = UsuarioRepetidoExepcion_Exception.class, value = "http://servidor/Publicador/nuevoArtista/Fault/UsuarioRepetidoExepcion"),
        @FaultAction(className = PasswordErrorException_Exception.class, value = "http://servidor/Publicador/nuevoArtista/Fault/PasswordErrorException")
    })
    public void nuevoArtista(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        XMLGregorianCalendar arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7,
        @WebParam(name = "arg8", partName = "arg8")
        String arg8,
        @WebParam(name = "arg9", partName = "arg9")
        String arg9,
        @WebParam(name = "arg10", partName = "arg10")
        String arg10)
        throws PasswordErrorException_Exception, UsuarioRepetidoExepcion_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg7
     * @param arg6
     * @throws PasswordErrorException_Exception
     * @throws UsuarioRepetidoExepcion_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/nuevoEspectadorRequest", output = "http://servidor/Publicador/nuevoEspectadorResponse", fault = {
        @FaultAction(className = UsuarioRepetidoExepcion_Exception.class, value = "http://servidor/Publicador/nuevoEspectador/Fault/UsuarioRepetidoExepcion"),
        @FaultAction(className = PasswordErrorException_Exception.class, value = "http://servidor/Publicador/nuevoEspectador/Fault/PasswordErrorException")
    })
    public void nuevoEspectador(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3,
        @WebParam(name = "arg4", partName = "arg4")
        XMLGregorianCalendar arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7)
        throws PasswordErrorException_Exception, UsuarioRepetidoExepcion_Exception
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarUsuariosRequest", output = "http://servidor/Publicador/listarUsuariosResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://servidor/Publicador/listarUsuarios/Fault/UsuarioNoExisteException")
    })
    public StringArray listarUsuarios()
        throws UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataUsuario
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/mostrarDatosUsuarioRequest", output = "http://servidor/Publicador/mostrarDatosUsuarioResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://servidor/Publicador/mostrarDatosUsuario/Fault/UsuarioNoExisteException")
    })
    public DataUsuario mostrarDatosUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataUsuario
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/mostrarDatosUsuarioGeneralRequest", output = "http://servidor/Publicador/mostrarDatosUsuarioGeneralResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://servidor/Publicador/mostrarDatosUsuarioGeneral/Fault/UsuarioNoExisteException")
    })
    public DataUsuario mostrarDatosUsuarioGeneral(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0)
        throws UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns servidor.DataEspectaculo
     * @throws EspectaculoNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/seleccionarEspectaculoUsuarioRequest", output = "http://servidor/Publicador/seleccionarEspectaculoUsuarioResponse", fault = {
        @FaultAction(className = EspectaculoNoExisteException_Exception.class, value = "http://servidor/Publicador/seleccionarEspectaculoUsuario/Fault/EspectaculoNoExisteException")
    })
    public DataEspectaculo seleccionarEspectaculoUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws EspectaculoNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataRegistroArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarFuncionesEspectadorRequest", output = "http://servidor/Publicador/listarFuncionesEspectadorResponse")
    public DataRegistroArray listarFuncionesEspectador(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns servidor.DataFuncion
     * @throws FuncionNoExisteException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/seleccionarFuncionRequest", output = "http://servidor/Publicador/seleccionarFuncionResponse", fault = {
        @FaultAction(className = FuncionNoExisteException_Exception.class, value = "http://servidor/Publicador/seleccionarFuncion/Fault/FuncionNoExisteException")
    })
    public DataFuncion seleccionarFuncion(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws FuncionNoExisteException_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg5
     * @param arg4
     * @param arg1
     * @param arg0
     * @param arg7
     * @param arg6
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/modificarDatosUsuarioRequest", output = "http://servidor/Publicador/modificarDatosUsuarioResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://servidor/Publicador/modificarDatosUsuario/Fault/UsuarioNoExisteException")
    })
    public void modificarDatosUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        XMLGregorianCalendar arg3,
        @WebParam(name = "arg4", partName = "arg4")
        String arg4,
        @WebParam(name = "arg5", partName = "arg5")
        String arg5,
        @WebParam(name = "arg6", partName = "arg6")
        String arg6,
        @WebParam(name = "arg7", partName = "arg7")
        String arg7)
        throws UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     * @throws UsuarioNoExisteException_Exception
     * @throws PasswordErrorException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/validarUsuarioRequest", output = "http://servidor/Publicador/validarUsuarioResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://servidor/Publicador/validarUsuario/Fault/UsuarioNoExisteException"),
        @FaultAction(className = PasswordErrorException_Exception.class, value = "http://servidor/Publicador/validarUsuario/Fault/PasswordErrorException")
    })
    public boolean validarUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws PasswordErrorException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @throws YaComproPaqueteException_Exception
     */
    @WebMethod(operationName = "CompraPaquete")
    @Action(input = "http://servidor/Publicador/CompraPaqueteRequest", output = "http://servidor/Publicador/CompraPaqueteResponse", fault = {
        @FaultAction(className = YaComproPaqueteException_Exception.class, value = "http://servidor/Publicador/CompraPaquete/Fault/YaComproPaqueteException")
    })
    public void compraPaquete(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        XMLGregorianCalendar arg2)
        throws YaComproPaqueteException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/seguirUsuarioRequest", output = "http://servidor/Publicador/seguirUsuarioResponse")
    public void seguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/dejarDeSeguirUsuarioRequest", output = "http://servidor/Publicador/dejarDeSeguirUsuarioResponse")
    public void dejarDeSeguirUsuario(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/cargarDatosPruebaRequest", output = "http://servidor/Publicador/cargarDatosPruebaResponse")
    public void cargarDatosPrueba();

    /**
     * 
     * @return
     *     returns servidor.DataArtista
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/publicarDataArtistaRequest", output = "http://servidor/Publicador/publicarDataArtistaResponse")
    public DataArtista publicarDataArtista();

    /**
     * 
     * @return
     *     returns servidor.DataEspectador
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/publicarDataEspectadorRequest", output = "http://servidor/Publicador/publicarDataEspectadorResponse")
    public DataEspectador publicarDataEspectador();

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws EstadoEspectaculoException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/finalizarEspectaculoRequest", output = "http://servidor/Publicador/finalizarEspectaculoResponse", fault = {
        @FaultAction(className = EstadoEspectaculoException_Exception.class, value = "http://servidor/Publicador/finalizarEspectaculo/Fault/EstadoEspectaculoException")
    })
    public void finalizarEspectaculo(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws EstadoEspectaculoException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws EspectaculoNoExisteException_Exception
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/marcarEspectaculoFavoritoRequest", output = "http://servidor/Publicador/marcarEspectaculoFavoritoResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://servidor/Publicador/marcarEspectaculoFavorito/Fault/UsuarioNoExisteException"),
        @FaultAction(className = EspectaculoNoExisteException_Exception.class, value = "http://servidor/Publicador/marcarEspectaculoFavorito/Fault/EspectaculoNoExisteException")
    })
    public void marcarEspectaculoFavorito(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws EspectaculoNoExisteException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg1
     * @param arg0
     * @throws EspectaculoNoExisteException_Exception
     * @throws UsuarioNoExisteException_Exception
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/desmarcarEspectaculoFavoritoRequest", output = "http://servidor/Publicador/desmarcarEspectaculoFavoritoResponse", fault = {
        @FaultAction(className = UsuarioNoExisteException_Exception.class, value = "http://servidor/Publicador/desmarcarEspectaculoFavorito/Fault/UsuarioNoExisteException"),
        @FaultAction(className = EspectaculoNoExisteException_Exception.class, value = "http://servidor/Publicador/desmarcarEspectaculoFavorito/Fault/EspectaculoNoExisteException")
    })
    public void desmarcarEspectaculoFavorito(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1)
        throws EspectaculoNoExisteException_Exception, UsuarioNoExisteException_Exception
    ;

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataEspectaculoArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/listarEspectaculosFinalizadoRequest", output = "http://servidor/Publicador/listarEspectaculosFinalizadoResponse")
    public DataEspectaculoArray listarEspectaculosFinalizado(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns servidor.DataPremioArray
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/mostrarPremiosRequest", output = "http://servidor/Publicador/mostrarPremiosResponse")
    public DataPremioArray mostrarPremios(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns servidor.DataPuntaje
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/existePuntajeRequest", output = "http://servidor/Publicador/existePuntajeResponse")
    public DataPuntaje existePuntaje(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @Action(input = "http://servidor/Publicador/agregarPuntajeRequest", output = "http://servidor/Publicador/agregarPuntajeResponse")
    public void agregarPuntaje(
        @WebParam(name = "arg0", partName = "arg0")
        int arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://servidor/Publicador/habilitadoValRequest", output = "http://servidor/Publicador/habilitadoValResponse")
    public boolean habilitadoVal(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

}
