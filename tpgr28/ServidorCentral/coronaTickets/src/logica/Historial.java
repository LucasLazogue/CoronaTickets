package logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Historial {

	private static Historial instance = null;
	private List<DataDatos> datos;
	private String pass = null;


	private Historial() {
		this.datos = new ArrayList<DataDatos>();
	}

	public static Historial getInstance() {
		if (instance == null) {
			instance = new Historial();
		}
		return instance;
	}

	public void agregarDato(DataDatos dato) {
		if (this.datos.size() < 10000) {
			this.datos.add(dato);
		}
	}

	public void agregarDato(String ip, String url, String browser, String os) {
		DataDatos data = new DataDatos(ip, url, browser, os);
		agregarDato(data);
	}

	public void generarPass() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		this.pass = saltStr;
		
		Timer timer = new Timer();
		
		TimerTask borrar = new TimerTask() {
			@Override
			public void run() {
				borrarPass();
			}
		};
		
		//180000
		timer.schedule(borrar, 180000);
		
	}
	
	public void borrarPass() {
		this.pass = null;
	}
	
	public String getPass() {
		return this.pass;
	}

	public List<DataDatos> getDatos(){
		return this.datos;
	}
}
