package modelo;

import java.util.List;

import clases.Coche;
import clases.Usuario;

public interface DAO {

	public void altaPropietario(Usuario usu);
	
	public Usuario inicarSesion(String nombre, String contrasenia);
	
	public void altaCoche(Coche coc);
	
	public List<Coche> listarCoches();
	
	public Coche mostrarCoche(String matricula);
	
	public List<Usuario> listarUsuarios();
	
	public Usuario mostrarUsuario(String usuario);
}
