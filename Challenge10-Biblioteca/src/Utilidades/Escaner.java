package Utilidades;

import java.util.Scanner;

public class Escaner {
	
	static Scanner sc = new Scanner(System.in);
	

	
	public static Integer leerNumeroConMens(int maximo, int minimo, String mensaje) {
		System.out.println(mensaje);
		int valor = 0;
		while (valor == 0) {
			try {
				valor =Integer.parseInt(sc.nextLine());
				if (valor<= maximo && valor >= minimo) {
					return valor;
				}else {
					valor= -1;
				}
				
			} catch (Exception e) {
				
			}finally {
				if(valor == -1) {
					System.out.println("Ingrese numero valido");
				}
				valor =0;
			}
			
		}return valor;
	}

}
