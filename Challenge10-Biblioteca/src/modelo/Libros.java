package modelo;

public class Libros extends Articulos {

	private int ctdadPages;
	private String imprenta;

	public Libros(int plazoMaximo, String name, String codigo, boolean isReservado, int ctdadPages, String imprenta) {
		super(plazoMaximo, name, codigo, isReservado);
		this.ctdadPages = ctdadPages;
		this.imprenta = imprenta;

	}

	public Libros() {

	}

	public int getCtdadPages() {
		return ctdadPages;
	}

	public void setCtdadPages(int ctdadPages) {
		this.ctdadPages = ctdadPages;
	}

	public String getImprenta() {
		return imprenta;
	}

	public void setImprenta(String imprenta) {
		this.imprenta = imprenta;
	}

	@Override
	public String toString() {
		
		//Syst("-----------------------------------------------");
		return "/// Libros /// \n"
				+ "Nombre = " + name 
				+ "\nEstado de reserva = " + isReservado
				+ "\nCodigo = " + codigo 
				+ "\nPlazo maximo de reserva = " + plazoMax 
				+ "\nCantidad paginas = " + ctdadPages 
				+ "\nImprenta = " + imprenta +"\n"
				+"-----------------------------------------------";
	}

	

}
