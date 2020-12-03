package logica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataDatos {
	private String ip;
	private String url;
	private String browser;
	private String os;
	
	public DataDatos() {}
	
	public DataDatos(String ip, String url, String browser, String os) {
		this.ip = ip;
		this.url = url;
		this.browser = browser;
		this.os = os;
	}

	public String getIp() {
		return ip;
	}

	public String getUrl() {
		return url;
	}

	public String getBrowser() {
		return browser;
	}

	public String getOs() {
		return os;
	}
	
	

}
