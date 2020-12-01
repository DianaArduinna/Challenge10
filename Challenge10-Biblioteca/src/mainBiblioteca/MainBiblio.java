package mainBiblioteca;


/* Este menu permite al usuario volver a seleccionar las opciones y seguir dentro de ellas hasta indicar salir.
 * 
 * El usuario de prueba es a160001237 y la clave es a1234.
 * Articulos a buscar : La Mision o Neverwhere 

 * No obstante, por muchos intentos que realice no logre crear una version autodidacta de muchas cosas en este ejercicio.
 * Temas como la transferencia de excel o la misma logica es algo que me cuesta manejar, pero creo que la mejor opcion es seguir
 * practicando.
 * */

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import lectura.XlsHandler;
import modelo.Articulos;
import modelo.Usuarios;
import servicios.SessionHandler;
import servicios.Singleton;

public class MainBiblio {

	static Scanner sc = new Scanner(System.in);

	public static XlsHandler hdlr = new XlsHandler();

	public static void main(String[] args) throws IOException, Throwable {

		// da inicio a singleton
		cargarDatos();
		
		
		boolean salir = false;
		int opcion;

		while (SessionHandler.getUsuarioActivo() == null) {
			
			System.out.println("-----------------------------------------------");
			System.out.println("Estas en Biblioteca Central");
			System.out.print("Ingrese rut : ");
			String rut = sc.nextLine();
			System.out.print("Ingrese clave : ");
			String clave = sc.nextLine();
		

			SessionHandler.setUsuarioActivo(buscarUsuario(rut, clave));

		}
		System.out.println("-----------------------------------------------");
		System.out.println("/// Bienvenido(a) " + SessionHandler.getUsuarioActivo().getName()+" ///");
		System.out.println("-----------------------------------------------");

		while (!salir && SessionHandler.getUsuarioActivo() != null) {
			System.out.println("Que desea hacer?");
			System.out.println();
			System.out.println("1. Buscar articulo");
			System.out.println("2. Reservar articulo");
			System.out.println("3. Salir");

			try {
				Scanner sv = new Scanner(System.in);
				opcion = sc.nextInt();

				switch (opcion) {

				case 1:
					System.out.print("Ingrese nombre de busqueda: ");
					String nameSearch = sv.nextLine();
					buscarArticulos(nameSearch);
					System.out.println("\n-----------------------------------------------");
					

					break;
				case 2:

					
					System.out.print("Ingrese codigo articulo: ");
					System.out.println("\n-----------------------------------------------");
					String codigo = sv.nextLine();
					int indice = 0;
					indice = findArticuloByCode(codigo);
					if (indice == 0) {
						System.out.println("-----------------------------------------------");
						System.out.println("Articulo no encontrado, intente nuevamente");
						System.out.println("-----------------------------------------------");
					}
					generarReserva(indice);

					break;
				case 3:
					System.out.println("-----------------------------------------------");
					System.out.println("Hasta pronto " + SessionHandler.getUsuarioActivo().getName());
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 1 y 3");
					sv.close();
				}

			} catch (InputMismatchException e) {
				System.out.println("Debe insertar un número");
			}

		}

	}

	// -------------------- Metodos -----------------------------------
	

	// ------ Carga de datos -------
	private static void cargarDatos() throws IOException {
		// Accede al lector de excel e inicia singleton
		Singleton.setArticulos(hdlr.leerArticulos()); 
		Singleton.setUsers(hdlr.leerUsuarios());
	}

	// ----- Busqueda de Articulos -----
	private static void buscarArticulos(String name) {
		System.out.println("-----------------------------------------------");
		System.out.println("Buscando articulos que contenga " + name + "...");
		System.out.println("-----------------------------------------------");

		for (Articulos articulo : Singleton.getArticulos()) {
			if (articulo.getName().contains(name)) {
				System.out.println(articulo);
			}
		}
		System.out.println("Busqueda finalizada.");
		System.out.println("-----------------------------------------------");

	}
	
	// ----- Generador de reserva -----
	private static void generarReserva(int indice) {
		if (!Singleton.getArticulos().get(indice).isReservado()) {
			Singleton.getArticulos().get(indice).setReservado(true);
			Singleton.guardarCambios();
			System.out.println("-----------------------------------------------");
			System.out.println("Reserva realizada");
			System.out.println("-----------------------------------------------");
		} else {
			System.out.println("-----------------------------------------------");
			System.out.println("El articulo no se encuentra disponible.");
			System.out.println("-----------------------------------------------");
		}
	}

	// ----- Buscador de Articulo por Codigo -----
	private static int findArticuloByCode(String codigoArticulo) {
		
		List<Articulos> cloneList = Singleton.getArticulos();

		for (int i = 0; i < cloneList.size(); i++) {
			if (cloneList.get(i).getCodigo().equals(codigoArticulo)) {
				return i;
			}
		}
		return 0;

	}

	// ----- Buscador de Usuario en lista -----
	private static Usuarios buscarUsuario(String rut, String clave) {
		List<Usuarios> userList = Singleton.getUsers();

		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getClave().equals(clave) && userList.get(i).getRut().equals(rut)) {
				return userList.get(i);
			}
		}
		return null;
	}
	
	

}
