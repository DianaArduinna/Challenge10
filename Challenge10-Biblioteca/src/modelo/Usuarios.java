package modelo;

public class Usuarios {
	
	private String rut;
	private String clave;
	private String name;
	

	public Usuarios(String rut, String clave, String name) {
		this.rut = rut;
		this.clave = clave;
		this.name = name;
	
	}
	
	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}	

}
