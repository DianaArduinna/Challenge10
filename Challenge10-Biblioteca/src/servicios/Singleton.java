package servicios;

import java.util.List;

import lectura.XlsHandler;
import modelo.Articulos;
import modelo.Usuarios;

public class Singleton {

	private static Singleton singleton; //instancia
	private List<Articulos> articulos;
	private List<Usuarios> users;
	
	public static Singleton getInstance() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if(singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
	
	public static synchronized List<Articulos> getArticulos(){
		return getInstance().articulos;		
	}
	
	public static synchronized void setArticulos (List<Articulos> articulos) {
		getInstance().articulos = articulos;
	}
	
	public static List<Usuarios> getUsers(){
		return getInstance().users;
	}
	
	public static void setUsers(List<Usuarios>users) {
		getInstance().users = users;
		}
	
	public static synchronized boolean guardarCambios() {
		XlsHandler hdlr = new XlsHandler();
		return hdlr.guardarArticulos(getInstance().articulos).getResultado();
	}
	
	
	
}
