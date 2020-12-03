package database;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

@Entity
public class UsuariosDB implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique=true, nullable=false)
	private String nickname;
	@Column(unique=true, nullable=false)
	private String email;
	private String nombre;
	private String apellido;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_nacimiento;
	private String tipo_usuario;
	private static final long serialVersionUID = 1L;

	public UsuariosDB() {
		super();
	}
	
	public UsuariosDB(String nickName, String email, String nombre, String apellido, Date fecha, String tipoUsuario) {
		this.nickname = nickName;
		this.email = email;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fecha_nacimiento = fecha;
		this.tipo_usuario = tipoUsuario;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}   
	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}   
	public Date getFecha_nacimiento() {
		return this.fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}   
	public String getTipo_usuario() {
		return this.tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
   
}
