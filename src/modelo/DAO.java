package modelo;

import java.util.List;

import clases.Usuario;

public interface DAO {

	public void altaPropietario(Usuario usu);
	
	public List<Usuario> inicarSesion();
}
