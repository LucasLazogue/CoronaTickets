
package servidor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servidor package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ErrorCantidadCanjeExcepcion_QNAME = new QName("http://servidor/", "ErrorCantidadCanjeExcepcion");
    private final static QName _YaComproPaqueteException_QNAME = new QName("http://servidor/", "YaComproPaqueteException");
    private final static QName _EspectadorRegistradoEnFuncionExepcion_QNAME = new QName("http://servidor/", "EspectadorRegistradoEnFuncionExepcion");
    private final static QName _FechaPasadaException_QNAME = new QName("http://servidor/", "FechaPasadaException");
    private final static QName _NoEsEspectadorExepcion_QNAME = new QName("http://servidor/", "NoEsEspectadorExepcion");
    private final static QName _UsuarioNoExisteException_QNAME = new QName("http://servidor/", "UsuarioNoExisteException");
    private final static QName _UsuarioRepetidoExepcion_QNAME = new QName("http://servidor/", "UsuarioRepetidoExepcion");
    private final static QName _EspectaculoRepetidoException_QNAME = new QName("http://servidor/", "EspectaculoRepetidoException");
    private final static QName _FinVigAntesIniVigException_QNAME = new QName("http://servidor/", "FinVigAntesIniVigException");
    private final static QName _MaxEspMenorMinEspException_QNAME = new QName("http://servidor/", "MaxEspMenorMinEspException");
    private final static QName _PasswordErrorException_QNAME = new QName("http://servidor/", "PasswordErrorException");
    private final static QName _EspectaculoNoExisteException_QNAME = new QName("http://servidor/", "EspectaculoNoExisteException");
    private final static QName _EstadoEspectaculoException_QNAME = new QName("http://servidor/", "EstadoEspectaculoException");
    private final static QName _FuncionLlenaExepcion_QNAME = new QName("http://servidor/", "FuncionLlenaExepcion");
    private final static QName _RegistroNoSePuedeCanjearException_QNAME = new QName("http://servidor/", "RegistroNoSePuedeCanjearException");
    private final static QName _PaqueteRepetidoException_QNAME = new QName("http://servidor/", "PaqueteRepetidoException");
    private final static QName _FuncionNoExisteException_QNAME = new QName("http://servidor/", "FuncionNoExisteException");
    private final static QName _FuncionRepetidaException_QNAME = new QName("http://servidor/", "FuncionRepetidaException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servidor
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EspectaculoRepetidoException }
     * 
     */
    public EspectaculoRepetidoException createEspectaculoRepetidoException() {
        return new EspectaculoRepetidoException();
    }

    /**
     * Create an instance of {@link UsuarioNoExisteException }
     * 
     */
    public UsuarioNoExisteException createUsuarioNoExisteException() {
        return new UsuarioNoExisteException();
    }

    /**
     * Create an instance of {@link UsuarioRepetidoExepcion }
     * 
     */
    public UsuarioRepetidoExepcion createUsuarioRepetidoExepcion() {
        return new UsuarioRepetidoExepcion();
    }

    /**
     * Create an instance of {@link EspectadorRegistradoEnFuncionExepcion }
     * 
     */
    public EspectadorRegistradoEnFuncionExepcion createEspectadorRegistradoEnFuncionExepcion() {
        return new EspectadorRegistradoEnFuncionExepcion();
    }

    /**
     * Create an instance of {@link FechaPasadaException }
     * 
     */
    public FechaPasadaException createFechaPasadaException() {
        return new FechaPasadaException();
    }

    /**
     * Create an instance of {@link NoEsEspectadorExepcion }
     * 
     */
    public NoEsEspectadorExepcion createNoEsEspectadorExepcion() {
        return new NoEsEspectadorExepcion();
    }

    /**
     * Create an instance of {@link YaComproPaqueteException }
     * 
     */
    public YaComproPaqueteException createYaComproPaqueteException() {
        return new YaComproPaqueteException();
    }

    /**
     * Create an instance of {@link ErrorCantidadCanjeExcepcion }
     * 
     */
    public ErrorCantidadCanjeExcepcion createErrorCantidadCanjeExcepcion() {
        return new ErrorCantidadCanjeExcepcion();
    }

    /**
     * Create an instance of {@link FuncionRepetidaException }
     * 
     */
    public FuncionRepetidaException createFuncionRepetidaException() {
        return new FuncionRepetidaException();
    }

    /**
     * Create an instance of {@link FuncionNoExisteException }
     * 
     */
    public FuncionNoExisteException createFuncionNoExisteException() {
        return new FuncionNoExisteException();
    }

    /**
     * Create an instance of {@link PaqueteRepetidoException }
     * 
     */
    public PaqueteRepetidoException createPaqueteRepetidoException() {
        return new PaqueteRepetidoException();
    }

    /**
     * Create an instance of {@link EstadoEspectaculoException }
     * 
     */
    public EstadoEspectaculoException createEstadoEspectaculoException() {
        return new EstadoEspectaculoException();
    }

    /**
     * Create an instance of {@link FuncionLlenaExepcion }
     * 
     */
    public FuncionLlenaExepcion createFuncionLlenaExepcion() {
        return new FuncionLlenaExepcion();
    }

    /**
     * Create an instance of {@link RegistroNoSePuedeCanjearException }
     * 
     */
    public RegistroNoSePuedeCanjearException createRegistroNoSePuedeCanjearException() {
        return new RegistroNoSePuedeCanjearException();
    }

    /**
     * Create an instance of {@link EspectaculoNoExisteException }
     * 
     */
    public EspectaculoNoExisteException createEspectaculoNoExisteException() {
        return new EspectaculoNoExisteException();
    }

    /**
     * Create an instance of {@link PasswordErrorException }
     * 
     */
    public PasswordErrorException createPasswordErrorException() {
        return new PasswordErrorException();
    }

    /**
     * Create an instance of {@link MaxEspMenorMinEspException }
     * 
     */
    public MaxEspMenorMinEspException createMaxEspMenorMinEspException() {
        return new MaxEspMenorMinEspException();
    }

    /**
     * Create an instance of {@link FinVigAntesIniVigException }
     * 
     */
    public FinVigAntesIniVigException createFinVigAntesIniVigException() {
        return new FinVigAntesIniVigException();
    }

    /**
     * Create an instance of {@link LocalDateTime }
     * 
     */
    public LocalDateTime createLocalDateTime() {
        return new LocalDateTime();
    }

    /**
     * Create an instance of {@link DataPremioArray }
     * 
     */
    public DataPremioArray createDataPremioArray() {
        return new DataPremioArray();
    }

    /**
     * Create an instance of {@link DataRegistro }
     * 
     */
    public DataRegistro createDataRegistro() {
        return new DataRegistro();
    }

    /**
     * Create an instance of {@link DataPremio }
     * 
     */
    public DataPremio createDataPremio() {
        return new DataPremio();
    }

    /**
     * Create an instance of {@link DataRegistroArray }
     * 
     */
    public DataRegistroArray createDataRegistroArray() {
        return new DataRegistroArray();
    }

    /**
     * Create an instance of {@link DataPlataforma }
     * 
     */
    public DataPlataforma createDataPlataforma() {
        return new DataPlataforma();
    }

    /**
     * Create an instance of {@link DataPaqueteArray }
     * 
     */
    public DataPaqueteArray createDataPaqueteArray() {
        return new DataPaqueteArray();
    }

    /**
     * Create an instance of {@link DataUsuario }
     * 
     */
    public DataUsuario createDataUsuario() {
        return new DataUsuario();
    }

    /**
     * Create an instance of {@link DataFuncion }
     * 
     */
    public DataFuncion createDataFuncion() {
        return new DataFuncion();
    }

    /**
     * Create an instance of {@link DataEspectaculo }
     * 
     */
    public DataEspectaculo createDataEspectaculo() {
        return new DataEspectaculo();
    }

    /**
     * Create an instance of {@link DataPaquete }
     * 
     */
    public DataPaquete createDataPaquete() {
        return new DataPaquete();
    }

    /**
     * Create an instance of {@link DataEspectador }
     * 
     */
    public DataEspectador createDataEspectador() {
        return new DataEspectador();
    }

    /**
     * Create an instance of {@link DataEspectaculoArray }
     * 
     */
    public DataEspectaculoArray createDataEspectaculoArray() {
        return new DataEspectaculoArray();
    }

    /**
     * Create an instance of {@link DataPuntaje }
     * 
     */
    public DataPuntaje createDataPuntaje() {
        return new DataPuntaje();
    }

    /**
     * Create an instance of {@link DataArtista }
     * 
     */
    public DataArtista createDataArtista() {
        return new DataArtista();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ErrorCantidadCanjeExcepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "ErrorCantidadCanjeExcepcion")
    public JAXBElement<ErrorCantidadCanjeExcepcion> createErrorCantidadCanjeExcepcion(ErrorCantidadCanjeExcepcion value) {
        return new JAXBElement<ErrorCantidadCanjeExcepcion>(_ErrorCantidadCanjeExcepcion_QNAME, ErrorCantidadCanjeExcepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YaComproPaqueteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "YaComproPaqueteException")
    public JAXBElement<YaComproPaqueteException> createYaComproPaqueteException(YaComproPaqueteException value) {
        return new JAXBElement<YaComproPaqueteException>(_YaComproPaqueteException_QNAME, YaComproPaqueteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectadorRegistradoEnFuncionExepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "EspectadorRegistradoEnFuncionExepcion")
    public JAXBElement<EspectadorRegistradoEnFuncionExepcion> createEspectadorRegistradoEnFuncionExepcion(EspectadorRegistradoEnFuncionExepcion value) {
        return new JAXBElement<EspectadorRegistradoEnFuncionExepcion>(_EspectadorRegistradoEnFuncionExepcion_QNAME, EspectadorRegistradoEnFuncionExepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FechaPasadaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "FechaPasadaException")
    public JAXBElement<FechaPasadaException> createFechaPasadaException(FechaPasadaException value) {
        return new JAXBElement<FechaPasadaException>(_FechaPasadaException_QNAME, FechaPasadaException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoEsEspectadorExepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "NoEsEspectadorExepcion")
    public JAXBElement<NoEsEspectadorExepcion> createNoEsEspectadorExepcion(NoEsEspectadorExepcion value) {
        return new JAXBElement<NoEsEspectadorExepcion>(_NoEsEspectadorExepcion_QNAME, NoEsEspectadorExepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "UsuarioNoExisteException")
    public JAXBElement<UsuarioNoExisteException> createUsuarioNoExisteException(UsuarioNoExisteException value) {
        return new JAXBElement<UsuarioNoExisteException>(_UsuarioNoExisteException_QNAME, UsuarioNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoExepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "UsuarioRepetidoExepcion")
    public JAXBElement<UsuarioRepetidoExepcion> createUsuarioRepetidoExepcion(UsuarioRepetidoExepcion value) {
        return new JAXBElement<UsuarioRepetidoExepcion>(_UsuarioRepetidoExepcion_QNAME, UsuarioRepetidoExepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectaculoRepetidoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "EspectaculoRepetidoException")
    public JAXBElement<EspectaculoRepetidoException> createEspectaculoRepetidoException(EspectaculoRepetidoException value) {
        return new JAXBElement<EspectaculoRepetidoException>(_EspectaculoRepetidoException_QNAME, EspectaculoRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinVigAntesIniVigException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "FinVigAntesIniVigException")
    public JAXBElement<FinVigAntesIniVigException> createFinVigAntesIniVigException(FinVigAntesIniVigException value) {
        return new JAXBElement<FinVigAntesIniVigException>(_FinVigAntesIniVigException_QNAME, FinVigAntesIniVigException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MaxEspMenorMinEspException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "MaxEspMenorMinEspException")
    public JAXBElement<MaxEspMenorMinEspException> createMaxEspMenorMinEspException(MaxEspMenorMinEspException value) {
        return new JAXBElement<MaxEspMenorMinEspException>(_MaxEspMenorMinEspException_QNAME, MaxEspMenorMinEspException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PasswordErrorException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "PasswordErrorException")
    public JAXBElement<PasswordErrorException> createPasswordErrorException(PasswordErrorException value) {
        return new JAXBElement<PasswordErrorException>(_PasswordErrorException_QNAME, PasswordErrorException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EspectaculoNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "EspectaculoNoExisteException")
    public JAXBElement<EspectaculoNoExisteException> createEspectaculoNoExisteException(EspectaculoNoExisteException value) {
        return new JAXBElement<EspectaculoNoExisteException>(_EspectaculoNoExisteException_QNAME, EspectaculoNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EstadoEspectaculoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "EstadoEspectaculoException")
    public JAXBElement<EstadoEspectaculoException> createEstadoEspectaculoException(EstadoEspectaculoException value) {
        return new JAXBElement<EstadoEspectaculoException>(_EstadoEspectaculoException_QNAME, EstadoEspectaculoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuncionLlenaExepcion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "FuncionLlenaExepcion")
    public JAXBElement<FuncionLlenaExepcion> createFuncionLlenaExepcion(FuncionLlenaExepcion value) {
        return new JAXBElement<FuncionLlenaExepcion>(_FuncionLlenaExepcion_QNAME, FuncionLlenaExepcion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistroNoSePuedeCanjearException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "RegistroNoSePuedeCanjearException")
    public JAXBElement<RegistroNoSePuedeCanjearException> createRegistroNoSePuedeCanjearException(RegistroNoSePuedeCanjearException value) {
        return new JAXBElement<RegistroNoSePuedeCanjearException>(_RegistroNoSePuedeCanjearException_QNAME, RegistroNoSePuedeCanjearException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaqueteRepetidoException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "PaqueteRepetidoException")
    public JAXBElement<PaqueteRepetidoException> createPaqueteRepetidoException(PaqueteRepetidoException value) {
        return new JAXBElement<PaqueteRepetidoException>(_PaqueteRepetidoException_QNAME, PaqueteRepetidoException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuncionNoExisteException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "FuncionNoExisteException")
    public JAXBElement<FuncionNoExisteException> createFuncionNoExisteException(FuncionNoExisteException value) {
        return new JAXBElement<FuncionNoExisteException>(_FuncionNoExisteException_QNAME, FuncionNoExisteException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuncionRepetidaException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servidor/", name = "FuncionRepetidaException")
    public JAXBElement<FuncionRepetidaException> createFuncionRepetidaException(FuncionRepetidaException value) {
        return new JAXBElement<FuncionRepetidaException>(_FuncionRepetidaException_QNAME, FuncionRepetidaException.class, null, value);
    }

}
