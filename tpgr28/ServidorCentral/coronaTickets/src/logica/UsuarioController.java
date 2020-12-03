package logica;

import exepciones.ErrorCantidadCanjeExcepcion;
import exepciones.EspectaculoNoExisteException;
import exepciones.EspectaculoRepetidoException;
import exepciones.EspectadorRegistradoEnFuncionExepcion;
import exepciones.EstadoEspectaculoException;
import exepciones.FechaPasadaException;
import exepciones.FinVigAntesIniVigException;
import exepciones.FuncionLlenaExepcion;
import exepciones.FuncionNoExisteException;
import exepciones.FuncionNoFinalizadaException;
import exepciones.FuncionRepetidaException;
import exepciones.MaxEspMenorMinEspException;
import exepciones.NoEsEspectadorExepcion;
import exepciones.PaqueteRepetidoException;
import exepciones.PasswordErrorException;
import exepciones.PlataformaRepetidaException;
import exepciones.RegistroNoSePuedeCanjearException;
import exepciones.UsuarioNoExisteException;
import exepciones.UsuarioRepetidoExepcion;
import exepciones.YaComproPaqueteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UsuarioController implements IUsuarioController {
	private static boolean cargar_datos = true;


	//Alta de Usuario
	@Override
	public void nuevoArtista(String nickName, String email, String nombre, String apellido, 
			LocalDateTime fecha, String descripcion, String biografia, String web, 
			String password, String veripassword, String urlImg)
					throws UsuarioRepetidoExepcion, PasswordErrorException {

		if (!password.equals(veripassword)) {
			throw new PasswordErrorException("Error en la verificacion del password");
		}
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Artista[] art = mus.getArtistas();
		Espectador[] espec = mus.getEspectadores();
		boolean existe = false;
		for (int i = 0; espec != null && i < espec.length && !existe; i++) {
			if (espec[i].getEmail().equals(email)) {
				existe = true;
				throw new UsuarioRepetidoExepcion("El email " + email + "ya está registrado");
			}
			if (espec[i].getNickname().equals(nickName)) {
				existe = true;
				throw new UsuarioRepetidoExepcion("El nickname " + nickName + "ya está registrado");
			}
		}
		for (int i = 0; art != null && i < art.length && !existe; i++) {
			if (art[i].getEmail().equals(email)) {
				existe = true;
				throw new UsuarioRepetidoExepcion("El email " + email + "ya está registrado");
			}

			if (art[i].getNickname().equals(nickName)) {
				existe = true;
				throw new UsuarioRepetidoExepcion("El nickname " + nickName + "ya está registrado");
			}
		}
		if (!existe) {
			Artista artista = new Artista(nickName, email, nombre, apellido, fecha, descripcion, 
					biografia, web, password, urlImg);
			mus.agregarArtista(artista);
		}
	}

	@Override
	public void nuevoEspectador(String nickName, String email, String nombre, String apellido, 
			LocalDateTime fecha, String password, String veripassword, String urlImg)
					throws UsuarioRepetidoExepcion, PasswordErrorException {
		if (!password.equals(veripassword)) {
			throw new PasswordErrorException("Error en la verificacion del password");
		}
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Espectador[] espec = mus.getEspectadores();
		Artista[] art = mus.getArtistas();
		boolean existe = false;
		for (int i = 0; art != null && i < art.length && !existe; i++) {
			if (art[i].getEmail().equals(email)) {
				existe = true;
				throw new UsuarioRepetidoExepcion("El email " + email + "ya está registrado");
			}

			if (art[i].getNickname().equals(nickName)) {
				existe = true;
				throw new UsuarioRepetidoExepcion("El nickname " + nickName + "ya está registrado");
			}
		}
		for (int i = 0; espec != null && i < espec.length && !existe; i++) {
			if (espec[i].getEmail().equals(email)) {
				existe = true;
				throw new UsuarioRepetidoExepcion("El email " + email + "ya está registrado");
			}
			if (espec[i].getNickname().equals(nickName)) {
				existe = true;
				throw new UsuarioRepetidoExepcion("El nickname " + nickName + "ya está registrado");
			}
		}
		if (!existe) {
			Espectador espectador = new Espectador(nickName, email, nombre, apellido, fecha, password,
					urlImg);
			mus.agregarEspectador(espectador);
		}
	}


	//Consulta de Usuario
	@Override
	public String[] listarUsuarios() throws UsuarioNoExisteException {
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Artista[] art = mus.getArtistas();
		Espectador[] espec = mus.getEspectadores();

		Map<String, DataUsuario> mdu = new HashMap<String, DataUsuario>();
		if (art == null && espec == null) {
			throw new UsuarioNoExisteException("No hay usuarios registrados");
		} else {
			String[] usu = new String[art.length + espec.length];
			for (int i = 0; i < espec.length; i++) {
				String nick = espec[i].getNickname();
				DataEspectador desp = new DataEspectador(espec[i].getNickname(), espec[i].getNombre(), 
						espec[i].getApellido(), espec[i].getEmail(), espec[i].getFecha(), null, 
						espec[i].getUrlImg(), espec[i].getUsuariosSiguiendo(), 
						espec[i].getUsuariosSeguidores(), espec[i].getEspectaculosFavoritos());
				mdu.put(nick, desp); 
			}
			for (int i = 0; i < art.length; i++) {
				String nick = art[i].getNickname();
				DataArtista dar = new DataArtista(art[i].getNickname(), art[i].getNombre(), 
						art[i].getApellido(), art[i].getEmail(), art[i].getFecha(), art[i].getDescripcion(), 
						art[i].getBiografia(), art[i].getWeb(), null, art[i].getUrlImg(), 
						art[i].getUsuariosSiguiendo(), art[i].getUsuariosSeguidores());
				mdu.put(nick, dar); 
			}
			Set<String> nombres = mdu.keySet();
			int ind = 0;
			for (String n: nombres) {
				usu[ind] = n;
				ind++;
			}
			return usu;
		}
	}

	/*Este metodo unicamente se llama cuando se muestran datos del perfil del usuario*/
	@Override
	public DataUsuario mostrarDatosUsuario(String nickname) throws UsuarioNoExisteException {
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Artista art = mus.getArtista(nickname);
		Espectador espec = mus.getEspectador(nickname);
		if (espec != null) {
			Set<Registro> regs = espec.getRegistro();
			String[] funs = null;
			String[] paqs = null;
			if (regs != null) {
				funs = new String[regs.size()];
				int ind = 0;
				for (Registro r : regs) {
					funs[ind] = r.getFuncion().getNombre();
					ind++;
				}
			}
			Map<String, Compra> comp = espec.getCompras();
			if (comp != null) {
				paqs = new String[comp.size()];
				int ind = 0;
				for (Map.Entry<String, Compra> c : comp.entrySet()) {
					paqs[ind] = c.getKey();
					ind++;
				}
			}
			DataUsuario usu = new DataEspectador(espec.getNickname(), espec.getNombre(), 
					espec.getApellido(), espec.getEmail(), espec.getFecha(), funs, paqs, 
					espec.getUrlImg(), espec.getUsuariosSiguiendo(), espec.getUsuariosSeguidores(),
					espec.getEspectaculosFavoritos());
			return usu;
		} else if (art != null) {
			Espectaculo[] especs = art.getEspectaculos();
			String[] esp = null;
			if (especs != null) {
				esp = new String[especs.length];
				for (int i = 0; i < especs.length; i++) {
					esp[i] = especs[i].getNombre();
				}

			}
			DataUsuario usu = new DataArtista(art.getNickname(), art.getNombre(), art.getApellido(), 
					art.getEmail(), art.getFecha(), art.getDescripcion(), art.getBiografia(), art.getWeb(), 
					esp, art.getUrlImg(), art.getUsuariosSiguiendo(), art.getUsuariosSeguidores());
			return usu;
		} else {
			throw new UsuarioNoExisteException("El usuario correspondiente a este " 
					+ nickname + " no se encuentra registrado");
		}
	}

	/*Metodo para enviar los datos generales de un usuario, no es en el perfil del mismo*/
	@Override
	public DataUsuario mostrarDatosUsuarioGeneral(String nickname) throws UsuarioNoExisteException {
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Artista art = mus.getArtista(nickname);
		Espectador espec = mus.getEspectador(nickname);
		if (art != null) {
			Espectaculo[] espectaculo = art.getEspectaculos();
			String[] especs = null;
			if (espectaculo != null) {
				especs = new String[espectaculo.length];
				for (int i = 0; i < espectaculo.length; i++) {
					if (espectaculo[i].getEstado() == EstadoEspectaculo.Aceptado) {
						especs[i] = espectaculo[i].getNombre();
					}
				}
			}

			DataUsuario usu = new DataArtista(art.getNickname(), art.getNombre(), art.getApellido(), 
					art.getEmail(), art.getFecha(), art.getDescripcion(), art.getBiografia(),
					art.getWeb(), especs, art.getUrlImg(), art.getUsuariosSiguiendo(), 
					art.getUsuariosSeguidores());
			return usu;
		} else if (espec != null) {
			Set<Registro> regs = espec.getRegistro();
			String[] func = null;
			if (regs != null) {
				func = new String[regs.size()];
				int ind = 0;
				for (Registro r : regs) {
					func[ind] = r.getFuncion().getNombre();
					ind++;
				}
			}
			DataUsuario usu = new DataEspectador(espec.getNickname(), espec.getNombre(), 
					espec.getApellido(), espec.getEmail(), espec.getFecha(), func, null,
					espec.getUrlImg(), espec.getUsuariosSiguiendo(), espec.getUsuariosSeguidores(),
					espec.getEspectaculosFavoritos());
			return usu;
		} else {
			throw new UsuarioNoExisteException("El usuario correspondiente a este " 
					+ nickname + " no se encuentra registrado");
		}

	}

	@Override
	public DataEspectaculo seleccionarEspectaculo(String nickname, String nombre)
			throws EspectaculoNoExisteException {
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Espectaculo espect = mus.getArtista(nickname).getEspectaculo(nombre);
		if (espect == null) {
			throw new EspectaculoNoExisteException("No existe un espectaculo con este nombre");
		} else {
			DataEspectaculo des = new DataEspectaculo(espect.getNombre(), espect.getDescripcion(),
					espect.getDuracion(), espect.getMinEspectadores(), espect.getMaxEspectadores(),
					espect.getCosto(), espect.getUrl(), espect.getFechaRegistro(),
					espect.getEstado().toString(),
					espect.getUrlImg(), espect.listarFunciones(), espect.listarPaquetesAsociados(),
					espect.listarCategorias(), espect.getPremio(), espect.getUrlVideo());
			return des;
		}
	}

	@Override
	public DataFuncion seleccionarFuncion(String nickname, String nombre)
			throws FuncionNoExisteException {
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Espectador espec = mus.getEspectador(nickname);
		Set<Registro> regs = espec.getRegistro();
		Funcion fun = null;
		if (regs != null) {
			for (Registro r : regs) {
				if (nombre.equals(r.getFuncion().getNombre())) {
					fun = r.getFuncion();
				}
			}
		}
		if (fun != null) {
			DataFuncion dfu = new DataFuncion(fun.getNombre(), fun.getFechaHoraInicio(),
					null, fun.getFechaRegistro(), fun.getEspectaculo().getNombre(), fun.getUrlimg(), 
					fun.getFechaSorteo() != null);
			return dfu;
		} else {
			throw new FuncionNoExisteException("No existe una funcion con este nombre");
		}
	}

	//Modificar Datos de Usuario
	@Override
	public void modificarDatosUsuario(String nickname, String nombre, String apellido, 
			LocalDateTime fecha, String descripcion, String biografia, String web, String urlImg)
					throws UsuarioNoExisteException {
		/*en caso de que fuera un espectador los datos que no sean de espectador(web,
		 * descripcion y biografia) se pasan en null. los valores que se pasan son todos
		 * los del usuario, cambiados o no asi se setean bien todos los valores*/
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Artista art = mus.getArtista(nickname);
		Espectador espec = mus.getEspectador(nickname);
		if (art != null) {
			art.setApellido(apellido);
			art.setBiografia(biografia);
			art.setDescripcion(descripcion);
			art.setNombre(nombre);
			art.setFecha(fecha);
			art.setWeb(web);
			art.setUrlImg(urlImg);

		} else if (espec != null) {
			espec.setApellido(apellido);
			espec.setNombre(nombre);
			espec.setFecha(fecha);
			espec.setUrlImg(urlImg);
		} else {
			throw new UsuarioNoExisteException("El usuario " + nickname
					+ "no se encuentra registrado");
		}
	}

	//Validar Usuario
	@Override
	public boolean validarUsuario(String nickname, String password)
			throws UsuarioNoExisteException, PasswordErrorException {
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Artista art = mus.getArtista(nickname);
		Espectador espec = mus.getEspectador(nickname);
		if (espec != null) {
			if (!password.equals(espec.getPassword())) {
				throw new PasswordErrorException("Error en la verificacion del password");
			} else {
				return true;
			}
		} else if (art != null) {
			if (!password.equals(art.getPassword())) {
				throw new PasswordErrorException("Error en la verificacion del password");
			} else {
				return true;
			}
		} else {
			throw new UsuarioNoExisteException("El usuario correspondiente a este "
					+ nickname + " no se encuentra registrado");
		}
	}

	@Override
	public void CompraPaquete(String nickName, String nombrePaquete, Date fecha)
			throws YaComproPaqueteException {
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios(); 
		Espectador esp = mus.getEspectador(nickName);
		if (esp.getCompra(nombrePaquete) != null) {
			throw new YaComproPaqueteException("Ya Tiene comprado este paquete");
		} else {
			ManejadorPaquetes mps = ManejadorPaquetes.getManejadorPaquetes();
			Paquete pacs = mps.getPaquete(nombrePaquete);
			esp.compraPaquete(pacs, fecha);
		}
	}


	//Estas funciones de abajo puede ser que no las usemos, los seleccionar van en el controlador de
	//Espectáculo y los listar en realidad no son necesarios si vamos a solo usar una lista de strings
	//con nombres
	@Override
	public Map<String, DataEspectaculo> listarEspectaculosDeArtista(String nickname)
			throws EspectaculoNoExisteException {
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Artista art = mus.getArtista(nickname);
		Espectaculo[] esp = art.getEspectaculos();
		Map<String, DataEspectaculo> mde = new HashMap<String, DataEspectaculo>();
		//En este lugar va la except de si existe o no los espectaculos
		if (esp != null) {
			for (int i = 0; i < esp.length; i++) {
				String nombre = esp[i].getNombre();
				DataEspectaculo des = new DataEspectaculo(esp[i].getNombre(), esp[i].getDescripcion(), 
						esp[i].getDuracion(), esp[i].getMinEspectadores(), esp[i].getMaxEspectadores(), 
						esp[i].getCosto(), esp[i].getUrl(), esp[i].getFechaRegistro(), 
						esp[i].getEstado().toString(), esp[i].getUrlImg(), esp[i].listarFunciones(), 
						esp[i].listarPaquetesAsociados(), esp[i].listarCategorias(), esp[i].getPremio(),
						esp[i].getUrlVideo());
				mde.put(nombre, des);
			}
			return mde;
		} else {
			throw new EspectaculoNoExisteException("No existe un espectaculo con este nombre");
		}
	}

	/*@Override
  public Map<String, DataRegistro> listarFuncionesEspectador(String nickname) {
    ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
    Espectador espec = mus.getEspectador(nickname);
    Map<String, DataRegistro> mdf = new HashMap<String, DataRegistro>();
    Set<Registro> regs = espec.getRegistro();
    if (regs != null) {
      for (Registro r: regs) {
        String nombre = r.getFuncion().getNombre();
        String nombrePaquete;
        Object[] setReg = r.getCanjeados().toArray();
        String[] lstCanj = new String[setReg.length];
        for (int i = 0; i < setReg.length; i++) {
          lstCanj[i] = ((Registro) setReg[i]).getFuncion().getNombre();
        }
        if (r.getPaquete() == null) {
          nombrePaquete = "";
        } else {
          nombrePaquete = r.getPaquete().getNombre();
        }
        DataRegistro dre = new DataRegistro(r.getFecha(), r.getCosto(), 
            r.getFuncion().getNombre(), lstCanj, nombrePaquete);
        mdf.put(nombre, dre);
      }
    }
    return mdf;
  }*/


	@Override
	public DataRegistro[] listarFuncionesEspectador(String nickname) {
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Espectador espec = mus.getEspectador(nickname);
		Map<String, DataRegistro> mdf = new HashMap<String, DataRegistro>();
		Set<Registro> regs = espec.getRegistro();
		if (regs != null) {
			for (Registro r: regs) {
				String nombre = r.getFuncion().getNombre();
				String nombrePaquete;
				Object[] setReg = r.getCanjeados().toArray();
				String[] lstCanj = new String[setReg.length];
				for (int i = 0; i < setReg.length; i++) {
					lstCanj[i] = ((Registro) setReg[i]).getFuncion().getNombre();
				}
				if (r.getPaquete() == null) {
					nombrePaquete = "";
				} else {
					nombrePaquete = r.getPaquete().getNombre();
				}
				DataRegistro dre = new DataRegistro(r.getFecha(), r.getCosto(), 
						r.getFuncion().getNombre(), lstCanj, nombrePaquete);
				mdf.put(nombre, dre);
			}
		}
		return mdf.values().toArray(new DataRegistro[0]);
	}

	//Seguir a un usuario
	@Override
	public void seguirUsuario(String nickname1, String nickname2) {
		//Obtener el usuario que va a seguir y el que será seguido
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Usuario us1 = mus.getArtista(nickname1);
		if (us1 == null) {
			us1 = mus.getEspectador(nickname1);
		}
		Usuario us2 = mus.getArtista(nickname2);
		if (us2 == null) {
			us2 = mus.getEspectador(nickname2);
		}
		//Hacer que u1 siga a u2
		us1.seguirUsuario(us2);
		us2.agregarSeguidor(us1);
	}

	//Dejar de seguir a un usuario
	@Override
	public void dejarDeSeguirUsuario(String nickname1, String nickname2) {
		//Obtener el usuario que va a dejar de seguir y el que perderá al seguidor
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Usuario us1 = mus.getArtista(nickname1);
		if (us1 == null) {
			us1 = mus.getEspectador(nickname1);
		}
		Usuario us2 = mus.getArtista(nickname2);
		if (us2 == null) {
			us2 = mus.getEspectador(nickname2);
		}
		//Hacer que u1 deje de seguir a u2
		us1.dejarDeSeguirUsuario(us2);
		us2.quitarSeguidor(us1);
	}

	/**
	 * Puntuar espectaculo.
	 */
	public void puntuar(int puntaje, Espectaculo esp, Usuario usuario)
			throws FuncionNoFinalizadaException, NoEsEspectadorExepcion {
		if (usuario instanceof Espectador) {
			Espectador espectador = (Espectador) usuario;
			boolean habilitadoFuncion = false;
			Set<String> funciones = esp.getNombreFunciones();
			String[] funcionesArr = (String[]) funciones.toArray();
			Date fechaActual = new Date();
			for (int i = 0; i < funcionesArr.length && !habilitadoFuncion; i++) {
				DataFuncion dtFun = null;
				try {
					dtFun = seleccionarFuncion(espectador.getNickname(), funcionesArr[i]);
				} catch (FuncionNoExisteException e) {}
				if (dtFun != null) {
					Date horaFun = dtFun.getFechaHoraInicio();
					if (horaFun.compareTo(fechaActual) < 0) {
						habilitadoFuncion = true;
					}
				}
			}
			if (habilitadoFuncion) {
				espectador.agregarPuntaje(puntaje, esp);
			} else {
				throw new FuncionNoFinalizadaException("No es posible asignar un puntaje a este "
						+ "espectáculo");
			}
		} else {
			throw new NoEsEspectadorExepcion("Solo los espectadores pueden realizar esta operacion");
		}
	}

	//Marcar un espectáculo como favorito
	@Override
	public void marcarEspectaculoFavorito(String nickname, String espectaculo)
			throws UsuarioNoExisteException, EspectaculoNoExisteException {
		//Obtener el usuario que va a marcar como favorito un espectáculo
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Espectador usu = mus.getEspectador(nickname);
		if (usu == null) {
			throw new UsuarioNoExisteException("No existe un espectador con nickname " + nickname);
		}
		//Obtener el espectáculo que será favorito
		Plataforma[] listaPlat = ManejadorPlataforma.getInstance().getPlataformas();
		Espectaculo esp = null;
		for (int i = 0; esp == null && i < listaPlat.length; i++) {
			esp = listaPlat[i].obtenerEspectaculo(espectaculo);
		}
		if (esp == null) {
			throw new EspectaculoNoExisteException("El espectáculo " + espectaculo + " no existe");
		}
		//Marcar espectáculo como favorito
		usu.marcarEspectaculoFavorito(esp);
	}

	//Dejar de seguir a un usuario
	@Override
	public void desmarcarEspectaculoFavorito(String nickname, String espectaculo)
			throws UsuarioNoExisteException, EspectaculoNoExisteException {
		//Obtener el usuario que va a desmarcar como favorito un espectáculo
		ManejadorUsuarios mus = ManejadorUsuarios.getManejadorUsuarios();
		Espectador usu = mus.getEspectador(nickname);
		if (usu == null) {
			throw new UsuarioNoExisteException("No existe un espectador con nickname " + nickname);
		}
		//Obtener el espectáculo que será favorito
		Plataforma[] listaPlat = ManejadorPlataforma.getInstance().getPlataformas();
		Espectaculo esp = null;
		for (int i = 0; esp == null && i < listaPlat.length; i++) {
			esp = listaPlat[i].obtenerEspectaculo(espectaculo);
		}
		if (esp == null) {
			throw new EspectaculoNoExisteException("El espectáculo " + espectaculo + " no existe");
		}
		//Marcar espectáculo como favorito
		usu.desmarcarEspectaculoFavorito(esp);
	}
	@Override
	public DataPremio[] mostrarPremios(String nickname) {
		ManejadorUsuarios man = ManejadorUsuarios.getManejadorUsuarios();
		Espectador esp = man.getEspectador(nickname);
		Registro[] reg = esp.getRegistro().toArray(new Registro[0]);
		for (Registro r : reg) {
			System.out.print(r.getFuncion().getNombre() + " ");
			System.out.print(r.getUsuario() + " ");
			System.out.println(r.getPremio() + " ");
		}
		Set<DataPremio> res = new HashSet<DataPremio>();
		for (Registro r: reg) {
			if (r.getPremio()){
				Funcion fun = r.getFuncion();
				Espectaculo espec = fun.getEspectaculo();
				Date fech = fun.getFechaSorteo();
				DataPremio premE = espec.getPremio();
				DataPremio prem = new DataPremio(premE.getDescripcion(),premE.getCantPremiosPorFuncion());								
				prem.setEspectaculo(espec.getNombre());
				prem.setFuncion(fun.getNombre());
				prem.setFecha(fech);
				res.add(prem);
			} 
		}
		if(!res.isEmpty()) {
			Object[] obj = res.toArray();
			DataPremio[] premios = new DataPremio[obj.length];
			for (int i = 0; i < obj.length; i++) {
				premios[i] = (DataPremio) obj[i];
			}
			return premios;
		}
		else {return null;}    
	}

	public DataPuntaje existePuntaje(String espectador, String espectaculo) {
		Espectador esp = ManejadorUsuarios.getManejadorUsuarios().getEspectador(espectador);
		Plataforma[] plataformas = ManejadorPlataforma.getInstance().getPlataformas();
		Espectaculo espectaculoClase = null;
		Boolean terminado = false;
		for (int i = 0; i < plataformas.length && !terminado; i++) {
			if (plataformas[i].obtenerEspectaculo(espectaculo) != null) {
				espectaculoClase = plataformas[i].obtenerEspectaculo(espectaculo);
				terminado = true;
			}
		}
		Puntaje puntaje = esp.existePuntaje(espectaculoClase);
		if (puntaje != null)
			return new DataPuntaje(puntaje.getPuntaje(), puntaje.getEspectaculo().getNombre());
		return null;
	}

	public void agregarPuntaje(int puntaje, String espectaculo, String espectador) {
		Espectador esp = ManejadorUsuarios.getManejadorUsuarios().getEspectador(espectador);
		Plataforma[] plataformas = ManejadorPlataforma.getInstance().getPlataformas();
		Espectaculo espectaculoClase = null;
		Boolean terminado = false;
		for (int i = 0; i < plataformas.length && !terminado; i++) {
			if (plataformas[i].obtenerEspectaculo(espectaculo) != null) {
				espectaculoClase = plataformas[i].obtenerEspectaculo(espectaculo);
				terminado = true;
			}
		}
		esp.agregarPuntaje(puntaje, espectaculoClase);
	}

	public void agregarDatoHistorial(String ip, String url, String browser, String os)  {
		Historial historial = Historial.getInstance();
		historial.agregarDato(ip, url, browser, os);
	}

	public DataDatos[] listarDatosHistorial() {
		Historial historial = Historial.getInstance();
		List<DataDatos> lstDatos = historial.getDatos();
		if (!lstDatos.isEmpty()) {
			DataDatos[] res = new DataDatos[lstDatos.size()];
			lstDatos.toArray(res);
			return res;
		} else {
			return null;
		}
	}
	
	public Boolean validarCodigo(String codigo) {
		Historial historial = Historial.getInstance();
		if (historial.getPass() != null && historial.getPass().equals(codigo)) {
			return true;
		}
		return false;
	}

	/**
	 * Cargar datos de prueba.
	 */
	@SuppressWarnings("deprecation")
	public void cargarDatosPrueba() {
		if (UsuarioController.cargar_datos) {
			Fabrica fab = Fabrica.getInstancia();
			IEspectaculoController iec = fab.getIEspectaculoController();
			IUsuarioController icu = fab.getIUsuarioController();

			try {
				icu.nuevoEspectador("eleven11", "eleven11@gmail.com", "Eleven", "Ten",
						LocalDateTime.of(1971, 12, 31, 1, 1), "lkj34df", "lkj34df", null);
				icu.nuevoEspectador("costas", "gcostas@gmail.com", "Gerardo", "Costas", 
						LocalDateTime.of(1983, 11, 15, 1, 1), "poke579", "poke579", null);
				icu.nuevoEspectador("waston", "e.watson@gmail.com", "Emma", "Watson", LocalDateTime.of(1990, 4, 15, 1, 1), "mkji648", "mkji648", "https://bit.ly/3jrashA");
				icu.nuevoEspectador("house", "greghouse@gmail.com", "Gregory", "House", LocalDateTime.of(1959, 5, 15, 1, 1), "fcku0123", "fcku0123", "https://bit.ly/3ng8YZE");
				icu.nuevoEspectador("sergiop", "puglia@alpanpan.com.uy", "Sergio", "Puglia", LocalDateTime.of(1950, 1, 28, 1, 1), "vbmn4r", "vbmn4r", "https://bit.ly/2EViUGV");
				icu.nuevoEspectador("chino", "chino@trico.org.uy", "Alvaro", "Recoba", LocalDateTime.of(1976, 3, 17, 1, 1), "ncnl123", "ncnl123", "https://bit.ly/3cTJWuX");
				icu.nuevoEspectador("tonyp", "eltony@manya.org.uy", "Antonio", "Pacheco", LocalDateTime.of(1955, 2, 14, 1, 1), "mny101", "mny101", "https://bit.ly/3cS2bkh");
				icu.nuevoEspectador("lachiqui", "lachiqui@hotmail.com", "Mirtha", "Legrand", 
						LocalDateTime.of(1927, 2, 23, 1, 1), "1o1vbm", "1o1vbm", null);
				icu.nuevoEspectador("cbochinche", "cbochinche@vera.com.uy", "Cacho", "Bochinche", 
						LocalDateTime.of(1937, 5, 8, 1, 1), "ultraton01", "ultraton01", null);
			} catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
				e.printStackTrace();
			}

			try {
				icu.nuevoArtista("vpeople", "vpeople@tuta.io", "Village", "People", LocalDateTime.of(1977, 1, 1, 1, 1), "Village People es una innovadora formación musical de estilo disco de finales de los años 70. Fue famosa tanto por sus peculiares disfraces, como por sus canciones pegadizas, con letras sugerentes y llenas de dobles sentidos.", "Grupo americano del disco creado por Jacques Morali y Henry Belolo en 1977. Según Marjorie Burgess, todo comenzó cuando Morali fue a un bar gay de Nueva York una noche y notó al bailarín Felipe Rose vestido como un nativo americano.", "www.officialvillagepeople.com", "asdfg456", "asdfg456", "https://bit.ly/36uctpI");
				icu.nuevoArtista("dmode", "dmode@tuta.io", "Depeche", "Mode", LocalDateTime.of(1980, 6, 14, 1, 1), "Depeche Mode es un grupo inglés de música electrónica formado en Basildon, Essex, en 1980 por Vicent Clarke y Andrew John Fletcher, a los que se unieron Martin Lee Gore y poco después David Gahan. Actualmente se le considera como grupo de música alternativa.", " ", "www.depechemode.com", "123rtgfdv", "123rtgfdv", "https://bit.ly/2GB7vME");
				icu.nuevoArtista("clauper", "clauper@hotmail.com", "Cyndi", "Lauper", LocalDateTime.of(1953, 6, 22, 1, 1), "Cynthia Ann Stephanie Lauper, conocida simplemente como Cyndi Lauper, es una cantautora, actriz y empresaria estadounidense. Después de participar en el grupo musical, Blue Angel, en 1983 firmó con Portrait Records (filial de Epic Records) y lanzó su exitoso álbum debut She's So Unusual a finales de ese mismo año. Siguió lanzando una serie de álbumes en los que encontró una inmensa popularidad, superando los límites de contenido de las letras de sus canciones.", "Cynthia Ann Stephanie Lauper (Brooklyn, Nueva York; 22 de junio de 1953).", "cyndilauper.com", "poiuy086", "poiuy086", "https://bit.ly/34zFWvV");
				icu.nuevoArtista("bruceTheBoss", "bruceTheBoss@gmail.com", "Bruce", "Springsteen", LocalDateTime.of(1949, 9, 23, 1, 1), "Bruce Frederick Joseph Springsteen (Long Branch, Nueva Jersey, 23 de septiembre de 1949), más conocido como Bruce Springsteen, es un cantante, músico y compositor estadounidense.", " ", "brucespringsteen.net", "GTO468", "GTO468", "https://bit.ly/34hFwde");
				icu.nuevoArtista("tripleNelson", "tripleNelson@tuta.io", "La Triple", "Nelson", LocalDateTime.of(1998, 1, 1, 1, 1), "La Triple Nelson es un grupo de rock uruguayo formado en enero de 1998 e integrado inicialmente por Christian Cary (guitarra y voz), Fernando \"Paco\" Pintos (bajo y coros) y Rubén Otonello (actualmente su nuevo baterista es Rafael Ugo).", " ", "www.latriplenelson.uy", "HGF135", "HGF135", "https://bit.ly/2Geps4a");
				icu.nuevoArtista("la_ley", "la_ley@tuta.io", "La", "Ley", LocalDateTime.of(1987, 2, 14, 1, 1), "La Ley fue una banda chilena de rock formada en 1987 por iniciativa del tecladista y guitarrista. En un principio, La Ley tenía la aspiración de ser un grupo de música tecno. Este disco resulta ser un éxito de ventas y reciben una invitación al Festival Internacional de Viña del Mar de febrero de 1994.", " ", "www.lasleyesdenewton.com", "lkj65D", "lkj65D", "https://bit.ly/33oXxqQ");
				icu.nuevoArtista("lospimpi", "lospimpi@gmail.com", "Pimpinela", "Pimpinela", LocalDateTime.of(1981, 8, 13, 1, 1), "Pimpinela es un dúo musical argentino compuesto por los hermanos Lucía Galán y Joaquín Galán. Pimpinela ha editado veinticuatro discos", " ", "www.pimpinela.net", "jhvf395", "jhvf395", "https://bit.ly/30t4tRI");
				icu.nuevoArtista("dyangounchained", "dyangounchained@gmail.com", "Dyango", "Ango", LocalDateTime.of(1940, 5, 3, 1, 1), "José Gómez Romero, conocido artísticamente como Dyango es un cantante español de música romántica.", " ", " ", "ijngr024", "ijngr024", "https://bit.ly/3jwA8JS");
				icu.nuevoArtista("alcides", "alcides@tuta.io", "Alcides", "Violeta", LocalDateTime.of(1952, 7, 17, 1, 1), "Su carrera comienza en 1976 cuando forma la banda Los Playeros junto a su hermano Víctor. Al poco tiempo se mudan a San Luis donde comienzan a hacerse conocidos en la escena musical. Su éxito a nivel nacional llega a comienzos de los años 1990 cuando desembarca en Buenos Aires y graba el éxito \"Violeta\", originalmente compuesta e interpretada en 1985 por el músico brasileño Luiz Caldas bajo el título «Fricote».", " ", " ", "987mnbgh", "987mnbgh", "https://bit.ly/3nnpAiu");
			} catch (UsuarioRepetidoExepcion | PasswordErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			icu.seguirUsuario("vpeople", "bruceTheBoss");
			icu.seguirUsuario("dmode", "clauper");
			icu.seguirUsuario("dmode", "bruceTheBoss");
			icu.seguirUsuario("clauper", "vpeople");
			icu.seguirUsuario("clauper", "dmode");
			icu.seguirUsuario("clauper", "dyangounchained");
			icu.seguirUsuario("bruceTheBoss", "vpeople");
			icu.seguirUsuario("bruceTheBoss", "dmode");
			icu.seguirUsuario("bruceTheBoss", "clauper");
			icu.seguirUsuario("bruceTheBoss", "house");
			icu.seguirUsuario("tripleNelson", "clauper");
			icu.seguirUsuario("tripleNelson", "la_ley");
			icu.seguirUsuario("tripleNelson", "waston");
			icu.seguirUsuario("la_ley", "dmode");
			icu.seguirUsuario("la_ley", "lospimpi");
			icu.seguirUsuario("la_ley", "waston");
			icu.seguirUsuario("lospimpi", "dmode");
			icu.seguirUsuario("lospimpi", "dyangounchained");
			icu.seguirUsuario("lospimpi", "alcides");
			icu.seguirUsuario("dyangounchained", "tripleNelson");
			icu.seguirUsuario("dyangounchained", "lospimpi");
			icu.seguirUsuario("alcides", "lospimpi");
			icu.seguirUsuario("alcides", "sergiop");
			icu.seguirUsuario("eleven11", "lospimpi");
			icu.seguirUsuario("eleven11", "dyangounchained");
			icu.seguirUsuario("eleven11", "waston");
			icu.seguirUsuario("eleven11", "chino");
			icu.seguirUsuario("eleven11", "tonyp");
			icu.seguirUsuario("costas", "vpeople");
			icu.seguirUsuario("costas", "dmode");
			icu.seguirUsuario("costas", "clauper");
			icu.seguirUsuario("costas", "bruceTheBoss");
			icu.seguirUsuario("costas", "tripleNelson");
			icu.seguirUsuario("costas", "la_ley");
			icu.seguirUsuario("costas", "lospimpi");
			icu.seguirUsuario("costas", "dyangounchained");
			icu.seguirUsuario("costas", "alcides");
			icu.seguirUsuario("waston", "dmode");
			icu.seguirUsuario("waston", "clauper");
			icu.seguirUsuario("waston", "bruceTheBoss");
			icu.seguirUsuario("waston", "house");
			icu.seguirUsuario("house", "bruceTheBoss");
			icu.seguirUsuario("house", "la_ley");
			icu.seguirUsuario("house", "dyangounchained");
			icu.seguirUsuario("sergiop", "vpeople");
			icu.seguirUsuario("sergiop", "la_ley");
			icu.seguirUsuario("sergiop", "lospimpi");
			icu.seguirUsuario("sergiop", "chino");
			icu.seguirUsuario("sergiop", "tonyp");
			icu.seguirUsuario("sergiop", "lachiqui");
			icu.seguirUsuario("chino", "alcides");
			icu.seguirUsuario("chino", "sergiop");
			icu.seguirUsuario("tonyp", "alcides");
			icu.seguirUsuario("tonyp", "sergiop");
			icu.seguirUsuario("lachiqui", "lospimpi");
			icu.seguirUsuario("lachiqui", "alcides");
			icu.seguirUsuario("cbochinche", "la_ley");
			icu.seguirUsuario("cbochinche", "lospimpi");
			icu.seguirUsuario("cbochinche", "alcides");
			icu.seguirUsuario("cbochinche", "chino");
			icu.seguirUsuario("cbochinche", "tonyp");
			icu.seguirUsuario("cbochinche", "lachiqui");

			try {
				iec.agregarPlataforma("Instagram Live", "Funcionalidad de la red social Instagram, con la que los usuarios pueden transmitir vídeos en vivo.", "https://www.instagram.com/liveoficial");
				iec.agregarPlataforma("Facebook Watch", "Servicio de video bajo demanda operado por Facebook.", "https://www.facebook.com/watch/");
				iec.agregarPlataforma("Twitter Live", "Aplicación de Twitter para la transmisión de video en directo (streaming).", "https://twitter.com/");
				iec.agregarPlataforma("Youtube", "Sitio web de origen estadounidense dedicado a compartir videos.", "https://www.youtube.com/");
			} catch (PlataformaRepetidaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			iec.ingresarDatosCategoria("Bandas Latinas");
			String[] bandasLatinas = {"Bandas Latinas"};
			iec.ingresarDatosCategoria("Solistas");
			String[] solistas = {"Solistas"};
			iec.ingresarDatosCategoria("Rock en Inglés");
			String[] rockEnIngles = {"Rock en Inglés"};
			iec.ingresarDatosCategoria("Música Tropical");
			String[] musicaTropical = {"Música Tropical"};

			try {
				iec.crearEspectaculo("Instagram Live", "vpeople", "Los Village Volvieron", "Espectáculo de retorno de los Village People.", 90, 1, 800, "https:// www.instagram.com/realvillagepeople/", 550, new Date(120, 2, 31), rockEnIngles, null, "Meet & greet (encuentro) virtual con integrantes de Village People y un accesorio de indumentaria de la banda que será elegido por el ganador, como ser el penacho de plumas del jefe indio (sujeto a disponibilidad). Info: https://bit.ly/sorteovp", 2, "N8FxU1nmLWg");
				iec.aceptarEspectaculo("Los Village Volvieron", true);
				iec.crearEspectaculo("Facebook Watch", "dmode", "Global Spirit", "Espectáculo donde se presenta el album Spirit.", 120, 1, 1300, "https://es-la.facebook.com/depechemode", 750, new Date(120, 3, 20), rockEnIngles, null, "Box Set multimedia “Depeche Mode: SPIRITS in the Forest”, que sigue a la banda en su Global Spirit Tour 2017/2018, que vio a Depeche Mode tocar para más de 3 millones de fanáticos en 115 shows en todo el mundo. El Box Set contiene 2 CDs y 2 DVDs o 2 CDs y 1 Blu-ray (a elección). Info: https://bit.ly/sorteodm", 3, "2qxcr6T9pNM");
				iec.aceptarEspectaculo("Global Spirit", true);
				iec.crearEspectaculo("Twitter Live", "clauper", "Memphis Blues World", "Espectáculo promoviendo album Memphis Blues.", 110, 1, 1000, "https://twitter.com/cyndilauper", 800, new Date(120, 4, 30), solistas, null, "Meet & greet (encuentro) virtual con la legendaria cantante e ícono del Pop, que inspiró a tantas otras cantante femeninas como Madonna y Lady Gaga (aunque ellas jamás lo admitirían).", 2, "ivHp3_gYXIc");
				iec.aceptarEspectaculo("Memphis Blues World", true);
				iec.crearEspectaculo("Youtube", "bruceTheBoss", "Springsteen on Broadway", "Springsteen tocando guitarra o piano y relatando anécdotas recogidas en su autobiografía de 2016, Born to Run.", 100, 1, 1500, "http://www.youtube.com/BruceSpringsteen", 980, new Date(120, 5, 7), rockEnIngles, null, "Album completo “Springsteen On Broadway” en formato MP3 o CD (a elección). Info: https://bit.ly/sorteobs", 2, "M1xDzgob1JI");
				iec.aceptarEspectaculo("Springsteen on Broadway", true);
				iec.crearEspectaculo("Twitter Live", "lospimpi", "Bien de Familia", "El dúo estará presentando sus mas sonados éxitos y también nuevas canciones.", 150, 1, 500, "https://twitter.com/PimpinelaNet", 500, new Date(120, 6, 8), bandasLatinas, null, "¿Es cierto que son hermanos? ¿La voz de Lucía puede romper una copa de cristal? ¿Joaquín quiere dejar Pimpinela y ser el vocalista de una banda de heavy metal? Todas estas preguntas y muchas más podrás hacérselas a tus ídolos en un encuentro on-line exclusivo para los ganadores de este sorteo.", 1, "dPSlBRg0HeA");
				iec.aceptarEspectaculo("Bien de Familia", true);
				iec.crearEspectaculo("Twitter Live", "alcides", "30 años", "Espectáculo conmemorando los 30 años de Violeta.", 80, 3, 150, "https://twitter.com/alcides_shows", 450, new Date(120, 6, 31), musicaTropical, null, "Entrada en platea VIP para el primer show presencial que realice Alcides a partir de enero de 2021 (una vez que el artista haya recibido la vacuna contra el SARS-COV-2), más 1 litro de Fernet de marca a confirmar.", 3, "65Pu6WP0bag");
				iec.aceptarEspectaculo("30 años", true);
				iec.crearEspectaculo("Youtube", "dyangounchained", "Grandes Éxitos 2020", "Espectáculo de gira con los temas de siempre", 120, 3, 4, "https://www.youtube.com/c/dyangooficial", 550, new Date(120, 8, 1), solistas, null, "Album completo “Y Ahora Que” para descargar en formato FLAC (24 bits, 44.1 kHz). Info: https://bit.ly/sorteody", 2, "NxFeibjFt3k");
				iec.aceptarEspectaculo("Grandes Éxitos 2020", true);
				iec.crearEspectaculo("Instagram Live", "tripleNelson", "Llega a Casa", "Primer Espectáculo con transmisión por streaming", 100, 100, 1500, "https://www.instagram.com/latriplenelson/", 400, new Date(120, 4, 20), bandasLatinas, null, "Entrada doble para espectáculo “Mi Bien” a realizarse en el Auditorio Nacional del SODRE.", 2, "m7r3YIFRI3k");
				iec.aceptarEspectaculo("Llega a Casa", true);
				iec.crearEspectaculo("Twitter Live", "alcides", "Nochebuena con Alcides y amigos", "Esta nochebuena, festejamos con Alcides y grandes invitados", 60, 1, 3, "https://twitter.com/alcides_shows", 600, new Date(120, 10, 25), musicaTropical, null, null, 0, "https://youtu.be/65Pu6WP0bag");
				iec.crearEspectaculo("Twitter Live", "alcides", "Fin de Año con Alcides y amigos", "Este fin de año, festejamos con Alcides y grandes invitados", 60, 1, 3, "https://twitter.com/alcides_shows", 700, new Date(120, 10, 25), musicaTropical, null, null, 0, "https://youtu.be/65Pu6WP0bag");
				iec.aceptarEspectaculo("Fin de Año con Alcides y amigos", false);
			} catch (EspectaculoRepetidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UsuarioNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MaxEspMenorMinEspException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EspectaculoNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			List<String> artistasFuncion = new ArrayList<String>();
			try {
				artistasFuncion.add("dmode");
				artistasFuncion.add("clauper");
				String urlimg = null;
				iec.ingresarDatosFuncion("Los Village Volvieron - 1", new Date(120, 3, 15, 15, 30),
						artistasFuncion, new Date(120, 2, 31), "Los Village Volvieron", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("bruceTheBoss");
				iec.ingresarDatosFuncion("Los Village Volvieron - 2", new Date(120, 4, 1, 17, 0), 
						artistasFuncion, new Date(120, 2, 31), "Los Village Volvieron", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("bruceTheBoss");
				artistasFuncion.add("clauper");
				iec.ingresarDatosFuncion("Los Village Volvieron - 3", new Date(120, 5, 1, 18, 0), 
						artistasFuncion, new Date(120, 2, 31), "Los Village Volvieron", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("vpeople");
				iec.ingresarDatosFuncion("Global Spirit (I)", new Date(120, 5, 10, 19, 0), 
						artistasFuncion, new Date(120, 3, 20), "Global Spirit", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("clauper");
				artistasFuncion.add("bruceTheBoss");
				iec.ingresarDatosFuncion("Global Spirit (II)", new Date(120, 6, 10, 20, 0), 
						artistasFuncion, new Date(120, 3, 20), "Global Spirit", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("lospimpi");
				iec.ingresarDatosFuncion("Global Spirit (III)", new Date(120, 7, 10, 17, 45), 
						artistasFuncion, new Date(120, 3, 20), "Global Spirit", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("bruceTheBoss");
				iec.ingresarDatosFuncion("Memphis Blues World - A", new Date(120, 7, 15, 16, 30), 
						artistasFuncion, new Date(120, 4, 30), "Memphis Blues World", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("bruceTheBoss");
				artistasFuncion.add("dmode");
				iec.ingresarDatosFuncion("Memphis Blues World - B", new Date(120, 7, 31, 19, 30), 
						artistasFuncion, new Date(120, 4, 30), "Memphis Blues World", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("lospimpi");
				artistasFuncion.add("bruceTheBoss");
				iec.ingresarDatosFuncion("Memphis Blues World - C", new Date(120, 8, 30, 20, 0), 
						artistasFuncion, new Date(120, 4, 30),  "Memphis Blues World", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("dmode");
				artistasFuncion.add("tripleNelson");
				iec.ingresarDatosFuncion("Springsteen on Broadway - i", new Date(120, 8, 1, 19, 30), 
						artistasFuncion, new Date(120, 5, 7), "Springsteen on Broadway", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("tripleNelson");
				artistasFuncion.add("la_ley");
				iec.ingresarDatosFuncion("Springsteen on Broadway - ii", new Date(120, 8, 30, 17, 0),
						artistasFuncion, new Date(120, 5, 7), "Springsteen on Broadway", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("la_ley");
				iec.ingresarDatosFuncion("Springsteen on Broadway - iii", new Date(120, 9, 15, 20, 0), 
						artistasFuncion, new Date(120, 5, 7), "Springsteen on Broadway", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("alcides");
				iec.ingresarDatosFuncion("Bien de Familia - A", new Date(120, 8, 25, 19, 0), 
						artistasFuncion, new Date(120, 6, 8), "Bien de Familia", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("tripleNelson");
				iec.ingresarDatosFuncion("Bien de Familia - B", new Date(120, 9, 25, 18, 30), 
						artistasFuncion, new Date(120, 6, 8), "Bien de Familia", urlimg);
				artistasFuncion.clear();
				iec.ingresarDatosFuncion("Bien de Familia - C", new Date(120, 10, 25, 17, 45), 
						artistasFuncion, new Date(120, 6, 8), "Bien de Familia", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("dyangounchained");
				iec.ingresarDatosFuncion("30 años - 1", new Date(120, 8, 1, 21, 0),
						artistasFuncion, new Date(120, 6, 31), "30 años", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("lospimpi");
				artistasFuncion.add("dyangounchained");
				iec.ingresarDatosFuncion("30 años - 2", new Date(120, 9, 1, 21, 0), 
						artistasFuncion, new Date(120, 6, 31), "30 años", urlimg);
				artistasFuncion.clear();
				iec.ingresarDatosFuncion("30 años - 3", new Date(120, 10, 15, 21, 0),
						artistasFuncion, new Date(120, 6, 31), "30 años", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("lospimpi");
				iec.ingresarDatosFuncion("Grandes Éxitos 2020 - Dia", new Date(120, 11, 19, 17, 0),
						artistasFuncion, new Date(120, 10, 25), "Grandes Éxitos 2020", urlimg);
				artistasFuncion.clear();
				artistasFuncion.add("lospimpi");
				iec.ingresarDatosFuncion("Grandes Éxitos 2020 - Noche", new Date(120, 11, 19, 21, 0),
						artistasFuncion, new Date(120, 10, 25), "Grandes Éxitos 2020", urlimg);
				artistasFuncion.clear();
				iec.ingresarDatosFuncion("Llega a Casa - 1", new Date(120, 11, 18, 21, 30),
						artistasFuncion, new Date(120, 10, 24), "Llega a Casa", urlimg);
				artistasFuncion.clear();
				iec.ingresarDatosFuncion("Llega a Casa - 2", new Date(120, 11, 19, 21, 30),
						artistasFuncion, new Date(120, 10, 24), "Llega a Casa", urlimg);

			} catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				iec.crearPaquete("Paquete de Bandas", "Paquete de bandas musicales.", new Date(120, 4, 1), new Date(120, 6, 31), 20, new Date(120, 3, 30), "https://bit.ly/3l9Vihe");
				iec.crearPaquete("Paquete Solistas", "Paquete de solistas.", new Date(120, 7, 1), new Date(120, 8, 30), 30, new Date(120, 6, 15), "https://bit.ly/2HSF4e0");
				iec.crearPaquete("Paquete Latino", "Paquete de espectaculos latinos.", new Date(120, 7, 15), new Date(120, 10, 25), 15, new Date(120, 7, 1), "https://bit.ly/3ndBhIw");
				iec.crearPaquete("La Triple Dyango", "Para los rockeros románticos", new Date(120, 10, 1), new Date(120, 11, 23), 10, new Date(120, 10, 25), "https://bit.ly/2JcPJks");

			} catch (PaqueteRepetidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FinVigAntesIniVigException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			iec.agregarEspectaculoAPaquete("Instagram Live", "Los Village Volvieron", 
					"Paquete de Bandas");
			iec.agregarEspectaculoAPaquete("Facebook Watch", "Global Spirit", 
					"Paquete de Bandas");

			iec.agregarEspectaculoAPaquete("Twitter Live", "Memphis Blues World", "Paquete Solistas");
			iec.agregarEspectaculoAPaquete("Youtube", "Springsteen on Broadway", "Paquete Solistas");

			iec.agregarEspectaculoAPaquete("Twitter Live", "Bien de Familia", "Paquete Latino");
			iec.agregarEspectaculoAPaquete("Twitter Live", "30 años", "Paquete Latino");

			iec.agregarEspectaculoAPaquete("Youtube", "Grandes Éxitos 2020", "La Triple Dyango");
			iec.agregarEspectaculoAPaquete("Instagram Live", "Llega a Casa", "La Triple Dyango");

			try {
				icu.CompraPaquete("tonyp", "Paquete de Bandas", new Date(120, 4, 1));
				icu.CompraPaquete("lachiqui", "Paquete de Bandas", new Date(120, 4, 20));
				icu.CompraPaquete("costas", "Paquete Latino", new Date(120, 8, 9));
				icu.CompraPaquete("eleven11", "Paquete Solistas", new Date(120, 7, 16));
				icu.CompraPaquete("waston", "Paquete Solistas", new Date(120, 7, 26));
				icu.CompraPaquete("lachiqui", "La Triple Dyango", new Date(120, 10, 25));
				icu.CompraPaquete("cbochinche", "La Triple Dyango", new Date(120, 10, 26));
			} catch (YaComproPaqueteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  

			List<String> registrosEspectador = new ArrayList<String>();
			try {

				iec.confirmarRegistroAFuncion("Instagram Live", "", "Los Village Volvieron", 
						"Los Village Volvieron - 1", "costas", registrosEspectador, new Date(120, 3, 9));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Instagram Live", "", "Los Village Volvieron", 
						"Los Village Volvieron - 1", "sergiop", registrosEspectador, new Date(120, 3, 10));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Instagram Live", "", "Los Village Volvieron", 
						"Los Village Volvieron - 1", "chino", registrosEspectador, new Date(120, 3, 12));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Instagram Live", "", "Los Village Volvieron", 
						"Los Village Volvieron - 2", "chino", registrosEspectador, new Date(120, 3, 15));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Instagram Live", "", "Los Village Volvieron", 
						"Los Village Volvieron - 2", "tonyp", registrosEspectador, new Date(120, 3, 20));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Instagram Live", "", "Los Village Volvieron", 
						"Los Village Volvieron - 2", "costas", registrosEspectador, new Date(120, 3, 25));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Instagram Live", "", "Los Village Volvieron", 
						"Los Village Volvieron - 2", "lachiqui", registrosEspectador, new Date(120, 3, 28));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Instagram Live", "", "Los Village Volvieron", 
						"Los Village Volvieron - 3", "cbochinche", registrosEspectador, new Date(120, 3, 16));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Instagram Live", "", "Los Village Volvieron", 
						"Los Village Volvieron - 3", "costas", registrosEspectador, new Date(120, 4, 15));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Instagram Live", "Paquete de Bandas", 
						"Los Village Volvieron", "Los Village Volvieron - 3", "lachiqui", registrosEspectador, 
						new Date(120, 4, 20));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "", "Global Spirit", 
						"Global Spirit (I)", "eleven11", registrosEspectador, new Date(120, 4, 5));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "", "Global Spirit", 
						"Global Spirit (I)", "waston", registrosEspectador, new Date(120, 4, 10));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "", "Global Spirit", 
						"Global Spirit (I)", "sergiop", registrosEspectador, new Date(120, 4, 15));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "Paquete de Bandas", 
						"Global Spirit", "Global Spirit (I)", "tonyp", registrosEspectador, 
						new Date(120, 4, 20));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "", "Global Spirit", 
						"Global Spirit (II)", "house", registrosEspectador, new Date(120, 5, 8));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "", "Global Spirit", 
						"Global Spirit (II)", "waston", registrosEspectador, new Date(120, 5, 13));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "Paquete de Bandas", 
						"Global Spirit", "Global Spirit (II)", "lachiqui", registrosEspectador, 
						new Date(120, 5, 25));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "", "Global Spirit", 
						"Global Spirit (III)", "cbochinche", registrosEspectador, new Date(120, 6, 5));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "", "Global Spirit", 
						"Global Spirit (III)", "sergiop", registrosEspectador, new Date(120, 6, 1));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Facebook Watch", "", "Global Spirit", 
						"Global Spirit (III)", "chino", registrosEspectador, new Date(120, 6, 18));
				registrosEspectador.clear();
				registrosEspectador.add("Los Village Volvieron - 2");
				registrosEspectador.add("Los Village Volvieron - 3");
				registrosEspectador.add("Global Spirit (II)");
				iec.confirmarRegistroAFuncion("Twitter Live", "", "Memphis Blues World", 
						"Memphis Blues World - A", "lachiqui", registrosEspectador, new Date(120, 6, 19));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "Paquete Solistas", 
						"Memphis Blues World", "Memphis Blues World - B", "eleven11", registrosEspectador, 
						new Date(120, 7, 17));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "Memphis Blues World", 
						"Memphis Blues World - B", "house", registrosEspectador, new Date(120, 7, 20));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "Memphis Blues World", 
						"Memphis Blues World - B", "chino", registrosEspectador, new Date(120, 7, 23));
				registrosEspectador.clear();
				registrosEspectador.add("Los Village Volvieron - 1");
				registrosEspectador.add("Los Village Volvieron - 2");
				registrosEspectador.add("Los Village Volvieron - 3");
				iec.confirmarRegistroAFuncion("Twitter Live", "", "Memphis Blues World", 
						"Memphis Blues World - C", "costas", registrosEspectador, new Date(120, 7, 15));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "Paquete Solistas", 
						"Memphis Blues World", "Memphis Blues World - C", "waston", registrosEspectador, 
						new Date(120, 7, 26));
				registrosEspectador.clear();
				registrosEspectador.add("Los Village Volvieron - 1");
				registrosEspectador.add("Los Village Volvieron - 2");
				registrosEspectador.add("Global Spirit (III)");
				iec.confirmarRegistroAFuncion("Youtube", "", "Springsteen on Broadway", 
						"Springsteen on Broadway - i", "chino", registrosEspectador, new Date(120, 6, 19));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "", "Springsteen on Broadway", 
						"Springsteen on Broadway - i", "tonyp", registrosEspectador, new Date(120, 7, 16));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "", "Springsteen on Broadway", 
						"Springsteen on Broadway - i", "lachiqui", registrosEspectador, new Date(120, 7, 24));
				registrosEspectador.clear();
				registrosEspectador.add("Los Village Volvieron - 1");
				registrosEspectador.add("Global Spirit (I)");
				registrosEspectador.add("Global Spirit (III)");
				iec.confirmarRegistroAFuncion("Youtube", "", "Springsteen on Broadway", 
						"Springsteen on Broadway - ii", "sergiop", registrosEspectador, new Date(120, 7, 1));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "", "Springsteen on Broadway", 
						"Springsteen on Broadway - ii", "house", registrosEspectador, new Date(120, 7, 30));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "Paquete Solistas", "Springsteen on Broadway", 
						"Springsteen on Broadway - iii", "eleven11", registrosEspectador, new Date(120, 7, 16));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "", "Springsteen on Broadway", 
						"Springsteen on Broadway - iii", "costas", registrosEspectador, new Date(120, 7, 16));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "Paquete Solistas", "Springsteen on Broadway", 
						"Springsteen on Broadway - iii", "waston", registrosEspectador, new Date(120, 8, 1));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "", "Springsteen on Broadway", 
						"Springsteen on Broadway - iii", "sergiop", registrosEspectador, new Date(120, 8, 5));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "Bien de Familia", 
						"Bien de Familia - A", "house", registrosEspectador, new Date(120, 7, 16));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "Bien de Familia", 
						"Bien de Familia - A", "cbochinche", registrosEspectador, new Date(120, 8, 3));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "Bien de Familia", 
						"Bien de Familia - B", "eleven11", registrosEspectador, new Date(120, 7, 16));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "Bien de Familia", 
						"Bien de Familia - B", "cbochinche", registrosEspectador, new Date(120, 8, 6));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "Paquete Latino", 
						"Bien de Familia", "Bien de Familia - C", "costas", registrosEspectador, 
						new Date(120, 8, 1));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "30 años", 
						"30 años - 1", "sergiop", registrosEspectador, new Date(120, 7, 16));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "30 años", 
						"30 años - 1", "eleven11", registrosEspectador, new Date(120, 7, 20));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "30 años", 
						"30 años - 1", "tonyp", registrosEspectador, new Date(120, 7, 31));
				registrosEspectador.clear(); 
				iec.confirmarRegistroAFuncion("Twitter Live", "", "30 años", 
						"30 años - 2", "chino", registrosEspectador, new Date(120, 7, 16));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "", "30 años", 
						"30 años - 2", "tonyp", registrosEspectador, new Date(120, 7, 20));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Twitter Live", "Paquete Latino", 
						"30 años", "30 años - 2", "costas", registrosEspectador, new Date(120, 8, 2));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "La Triple Dyango", "Grandes Éxitos 2020", 
						"Grandes Éxitos 2020 - Dia", "cbochinche", registrosEspectador, new Date(120, 10, 26));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "", "Grandes Éxitos 2020", 
						"Grandes Éxitos 2020 - Dia", "costas", registrosEspectador, new Date(120, 10, 27));
				registrosEspectador.clear();
				iec.confirmarRegistroAFuncion("Youtube", "La Triple Dyango", "Grandes Éxitos 2020", 
						"Grandes Éxitos 2020 - Dia", "lachiqui", registrosEspectador, new Date(120, 10, 28));
				registrosEspectador.clear();

			} catch (FechaPasadaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FuncionLlenaExepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoEsEspectadorExepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EspectadorRegistradoEnFuncionExepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RegistroNoSePuedeCanjearException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ErrorCantidadCanjeExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String[] ganadores1 = { "lachiqui" };
				iec.sortearPremiosArreglado("Twitter Live", "Memphis Blues World",
						"Memphis Blues World - A", new Date(120, 7, 17), ganadores1);
				String[] ganadores2 = { "eleven11", "house" };
				iec.sortearPremiosArreglado("Twitter Live", "Memphis Blues World",
						"Memphis Blues World - B", new Date(120, 8, 1), ganadores2);
				String[] ganadores3 = { "costas", "waston" };
				iec.sortearPremiosArreglado("Twitter Live", "Memphis Blues World",
						"Memphis Blues World - C", new Date(120, 8, 31), ganadores3);
				String[] ganadores4 = { "sergiop", "eleven11", "tonyp" };
				iec.sortearPremiosArreglado("Twitter Live", "30 años",
						"30 años - 1", new Date(120, 8, 30), ganadores4);
				String[] ganadores5 = { "chino", "tonyp", "costas" };
				iec.sortearPremiosArreglado("Twitter Live", "30 años",
						"30 años - 2", new Date(120, 9, 30), ganadores5);
			} catch (FechaPasadaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				icu.marcarEspectaculoFavorito("eleven11", "Global Spirit");
				icu.marcarEspectaculoFavorito("eleven11", "30 años");
				icu.marcarEspectaculoFavorito("costas", "Los Village Volvieron");
				icu.marcarEspectaculoFavorito("costas", "Global Spirit");
				icu.marcarEspectaculoFavorito("costas", "Memphis Blues World");
				icu.marcarEspectaculoFavorito("waston", "Springsteen on Broadway");
				icu.marcarEspectaculoFavorito("house", "Memphis Blues World");
				icu.marcarEspectaculoFavorito("house", "Springsteen on Broadway");
				icu.marcarEspectaculoFavorito("sergiop", "Springsteen on Broadway");
				icu.marcarEspectaculoFavorito("sergiop", "30 años");
				icu.marcarEspectaculoFavorito("chino", "Los Village Volvieron");
				icu.marcarEspectaculoFavorito("chino", "Global Spirit");
				icu.marcarEspectaculoFavorito("chino", "30 años");
				icu.marcarEspectaculoFavorito("tonyp", "Bien de Familia");
				icu.marcarEspectaculoFavorito("lachiqui", "Los Village Volvieron");
				icu.marcarEspectaculoFavorito("cbochinche", "Global Spirit");
			} catch (UsuarioNoExisteException | EspectaculoNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			icu.agregarPuntaje(5, "Los Village Volvieron", "chino");
			icu.agregarPuntaje(2, "Los Village Volvieron", "tonyp");
			icu.agregarPuntaje(3, "Los Village Volvieron", "costas");
			icu.agregarPuntaje(4, "Los Village Volvieron", "lachiqui");
			icu.agregarPuntaje(4, "Global Spirit", "eleven11");
			icu.agregarPuntaje(1, "Global Spirit", "waston");
			icu.agregarPuntaje(2, "Global Spirit", "sergiop");
			icu.agregarPuntaje(2, "Global Spirit", "tonyp");
			icu.agregarPuntaje(5, "Global Spirit", "cbochinche");
			icu.agregarPuntaje(5, "Global Spirit", "chino");
			icu.agregarPuntaje(2, "Memphis Blues World", "eleven11");
			icu.agregarPuntaje(4, "Memphis Blues World", "house");
			icu.agregarPuntaje(2, "Memphis Blues World", "chino");
			icu.agregarPuntaje(3, "Springsteen on Broadway", "sergiop");
			icu.agregarPuntaje(4, "Springsteen on Broadway", "house");
			icu.agregarPuntaje(2, "Springsteen on Broadway", "eleven11");
			icu.agregarPuntaje(1, "Springsteen on Broadway", "costas");
			icu.agregarPuntaje(5, "Springsteen on Broadway", "waston");
			icu.agregarPuntaje(1, "Bien de Familia", "house");
			icu.agregarPuntaje(4, "Bien de Familia", "cbochinche");
			icu.agregarPuntaje(5, "30 años", "chino");
			icu.agregarPuntaje(3, "30 años", "tonyp");
			icu.agregarPuntaje(2, "30 años", "costas");

			try {
				iec.finalizarEspectaculo("clauper", "Memphis Blues World");
				iec.finalizarEspectaculo("alcides", "30 años");
			} catch (EstadoEspectaculoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		UsuarioController.cargar_datos = false;
	}
}

