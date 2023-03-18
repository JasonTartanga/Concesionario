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
	
	public List<Coche> listarCochesDisponibles();
	
	public void modificarPropietario(String dni, String matricula);
	
	public Usuario mostrarUsuarioPorDni(String dni);
	
	public void modificarUsuario(Usuario usu);
	
	public void eliminarUsuario(String dni);
	
	public List<Usuario> listarUsuariosPropietarios();
	
	public List<Coche> mostrarTodosCochesPropietaro(String dni);
	
	public void eliminarCoche(String matricula);
	
	public Usuario mostrarPropietario(String matricula);
	
	public List<Coche> listarCocheConPropietario();
}
