package modelo;

public class HandlerResponse {

	public String mensaje;
	public Boolean resultado;

	public HandlerResponse(String mensaje, Boolean resultado) {
		super();
		this.mensaje = mensaje;
		this.resultado = resultado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Boolean getResultado() {
		return resultado;
	}

	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}

}
