package servicios;

import modelo.Usuarios;

/*Permite manejar si el usuario esta activo*/

public class SessionHandler {
	
	
	    private static Usuarios usuarioActivo = null;
	    public static Usuarios getUsuarioActivo() {
	        return usuarioActivo;
	    }
	    public static void setUsuarioActivo(Usuarios usuarioActivo) {
	        SessionHandler.usuarioActivo = usuarioActivo;
	    }
	    
	

}
