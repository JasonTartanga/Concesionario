package modelo;

import java.sql.ResultSet;

import clases.Usuario;

public interface DAO {

	public void altaPropietario(Usuario usu);
	
	public ResultSet inicarSesion();
}
