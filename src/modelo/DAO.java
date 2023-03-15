package modelo;

import clases.Usuario;

public interface DAO {

	public void altaPropietario(Usuario usu);
	
	public Usuario inicarSesion(String nombre, String contrasenia);
}
