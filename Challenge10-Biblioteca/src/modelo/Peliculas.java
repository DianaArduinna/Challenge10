package modelo;

public class Peliculas extends Articulos {

	private int durSec;
	private String calidad;

	public Peliculas(int plazoMaximo, String name, String codigo, boolean isReservado, int durSec, String calidad) {
		super(plazoMaximo, name, codigo, isReservado);
		this.durSec = durSec;
		this.calidad = calidad;
	}

	public Peliculas() {

	}

	public int getDurSec() {
		return durSec;
	}

	public void setDurSec(int durSec) {
		this.durSec = durSec;
	}

	public String getCalidad() {
		return calidad;
	}

	public void setCalidad(String calidad) {
		this.calidad = calidad;
	}

	@Override
	public String toString() {
		
		return "/// Peliculas /// \n"
				+ "Nombre = " + name 
				+ "\nDuracion = " + durSec 
				+ "\nCalidad = " + calidad 
				+ "\nEstado reserva = " + isReservado
				+ "\nCodigo = " + codigo 
				+ "\nPlazo maximo de reserva = " + plazoMax + "\n"
				+"-----------------------------------------------";
	}

}
