package modelo;

public abstract class Articulos {

	protected String name;
	protected boolean isReservado;
	protected String codigo;
	protected int plazoMax;

	public Articulos(int plazoMaximo, String name, String codigo, boolean isReservado) {
		this.name = name;
		this.isReservado = isReservado;
		this.codigo = codigo;
	}

	public Articulos() {
		super();
	}

	public int getPlazoMax() {
		return plazoMax;
	}

	public void setPlazoMax(int plazoMax) {
		this.plazoMax = plazoMax;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isReservado() {
		return isReservado;
	}

	public void setReservado(boolean isReservado) {
		this.isReservado = isReservado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
