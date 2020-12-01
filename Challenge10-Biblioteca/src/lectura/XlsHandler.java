package lectura;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import modelo.Articulos;
import modelo.HandlerResponse;
import modelo.Libros;
import modelo.Peliculas;
import modelo.Usuarios;

public class XlsHandler {
	
	
	
	String excelFilePathArticulos = "articulos.xls"; // Indica ubicacion del archivo a utilizar
	String excelFilePathUsuarios = "usuarios1.xls";
	Libros libros = new Libros();
	Peliculas pelicula = new Peliculas();
	
	

	// ----- Metodo que lee y obtiene la lista de articulos del excel y sobrescribe el archivo -----
	public HandlerResponse guardarArticulos(List<Articulos> lista) {

		/* crear libro y hoja de trabajo*/
		HSSFWorkbook libroGuardaArticulos = new HSSFWorkbook(); // instancia libro
		HSSFSheet hoja = libroGuardaArticulos.createSheet(); // hoja de trabajo

		int tipoArticulo = -1;
		int valorCeldaCuatro = 0;
		String valorCeldaCinco = "";
		HSSFCell celda = null;

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getClass().equals(Libros.class)) {
				tipoArticulo = 0;
				valorCeldaCuatro = (libros.getCtdadPages());
				valorCeldaCinco = (libros.getImprenta());
			} else {
				tipoArticulo = 1;
				valorCeldaCuatro = (pelicula.getDurSec());
				valorCeldaCinco = (pelicula.getCalidad());

			}

			// anade datos a la celda de fila 1

			HSSFRow fila = hoja.createRow(i);

			celda = fila.createCell((short) 0);
			celda.setCellValue(tipoArticulo);
			celda = fila.createCell((short) 1);
			celda.setCellValue(lista.get(i).getPlazoMax());  //obtiene valor general de articulos
			celda = fila.createCell((short) 2);
			celda.setCellValue(lista.get(i).getName());
			celda = fila.createCell((short) 3);
			celda.setCellValue(lista.get(i).getCodigo());
			celda = fila.createCell((short) 4);
			celda.setCellValue(valorCeldaCuatro); //da el valor ingresado mas arriba
			celda = fila.createCell((short) 5);
			celda.setCellValue(valorCeldaCinco);
			celda = fila.createCell((short) 6);
			celda.setCellValue(lista.get(i).isReservado());

		}

		// Exportacion

		try {

			FileOutputStream elFichero = new FileOutputStream(excelFilePathArticulos);
			libroGuardaArticulos.write(elFichero);
			elFichero.close();
			return new HandlerResponse("Peticion Finalizada", true);

		} catch (Exception e) {
		}
		try {
			libroGuardaArticulos.close();
		} catch (Exception e) {
		}
		return new HandlerResponse("Lo sentimos, intente mas tarde", false);

	}

	// ----- Metodo que obtiene los articulos -----

	public List<Articulos> leerArticulos() {
		Workbook workbookArticulos = null;
		FileInputStream inputString = null;
		List<Articulos> listaDeArticulos = new ArrayList<>();

		try {
			inputString = new FileInputStream(new File(excelFilePathArticulos));
			workbookArticulos = WorkbookFactory.create(inputString);

			Sheet sheet = workbookArticulos.getSheetAt(0);
			Row row = null;

			int cantidadFilas = sheet.getPhysicalNumberOfRows();

			Articulos articulo = null;
			for (int fila = 0; fila < cantidadFilas; fila++) {
				row = sheet.getRow(fila);

				if (row != null && row.getCell(0) != null) {
					if (row.getCell(0).getNumericCellValue() == 0) {

						articulo = new Libros(
								(int) row.getCell(1).getNumericCellValue(), //celda 1 dias reserva
								row.getCell(2).getStringCellValue(), // celda 2 nombre
								row.getCell(3).getStringCellValue(), // celda 3 codigo
								row.getCell(6).getBooleanCellValue(), // celda 6 flag reservado o no
								(int) row.getCell(4).getNumericCellValue(), // celda4 paginas
								row.getCell(5).getStringCellValue()); // celda 5 imprenta
					} else {
						articulo = new Peliculas(
								(int )row.getCell(1).getNumericCellValue(),
								row.getCell(2).getStringCellValue(), // celda 2 nombre
								row.getCell(3).getStringCellValue(), // celda 3 codigo
								row.getCell(6).getBooleanCellValue(), // celda 6 flag reservado o no
								(int) row.getCell(4).getNumericCellValue(), // celda 4 duracion
								row.getCell(5).getStringCellValue()); // celda 5 calidad

					}
					listaDeArticulos.add(articulo); 
				} else {
				break; // se rompe el ciclo
				}

			}

		} catch (Exception ioe) {
			ioe.printStackTrace();
			return new ArrayList<Articulos>();
		}
		try {
			workbookArticulos.close();

		} catch (Exception e) {
		}
		try {
			inputString.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		return listaDeArticulos;
	}

	// ----- Metodo que lee usuarios -----

	public List<Usuarios> leerUsuarios() {
		Workbook workbook = null;
		FileInputStream inputStream = null;
		List<Usuarios> listaDeUsuarios = new ArrayList<>(); // crea un nuevo arreglo con la info de los usuarios que vienen del

		try {
			inputStream = new FileInputStream(new File(excelFilePathUsuarios));
			workbook = WorkbookFactory.create(inputStream); // Creates the appropriate HSSFWorkbook / XSSFWorkbook from
															// the given InputStream.

			Sheet sheet = workbook.getSheetAt(0);
			Row row = null;

			int cantidadFilas = sheet.getPhysicalNumberOfRows();

			// leer filas completas
			Usuarios user = null;
			for (int r = 0; r < cantidadFilas; r++) {
				row = sheet.getRow(r);
				if (row != null) {
					user = new Usuarios(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue());
					listaDeUsuarios.add(user);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Usuarios>();
		}
		try {
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDeUsuarios;
	}

}
