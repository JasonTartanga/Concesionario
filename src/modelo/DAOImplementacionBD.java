package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import clases.Coche;
import clases.Usuario;

public class DAOImplementacionBD implements DAO {

	private Connection con = null;
	private PreparedStatement stmt;

	final private String URL = "jdbc:mysql://localhost:3306/CONCESIONARIO?serverTimezone=Europe/Madrid&useSSL=false";
	final private String USER = "root";
	final private String PASSWORD = "abcd*1234";

	final private String INICIAR_SESION = "SELECT * FROM usuario WHERE nombre = ? and contrasenia = ?";
	final private String ALTA_PROPIETARIO = "INSERT INTO usuario VALUES(?, ?, ?, ?, ?, ?, ?)";
	final private String ALTA_COCHE = "INSERT INTO coche VALUES(?, ?, ?, ?, ?, ?)";
	final private String LISTAR_COCHES = "SELECT matricula FROM coche";
	final private String MOSTRAR_COCHE = "SELECT * FROM coche WHERE matricula = ?";
	final private String LISTAR_USUARIOS = "SELECT dni FROM usuario";
	final private String MOSTRAR_USUARIO = "SELECT * FROM usuario WHERE dni = ?";

	private void abrirConexion() {
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	private void cerrarConexion() {
		try {
			if (con != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	public void altaPropietario(Usuario usu) {
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(ALTA_PROPIETARIO);

			stmt.setString(1, usu.getUsuario());
			stmt.setString(2, usu.getContrasenia());
			stmt.setString(3, usu.getDni());
			stmt.setInt(4, usu.getTelefono());
			stmt.setDate(5, Date.valueOf(usu.getFecha_nac()));
			stmt.setString(6, usu.getGenero() + "");
			stmt.setString(7, usu.getTitulacion());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();

		}

		this.cerrarConexion();
	}

	@Override
	public Usuario inicarSesion(String nombre, String contrasenia) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Usuario usu = null;
		ResultSet rs;

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(INICIAR_SESION);

			stmt.setString(1, nombre);
			stmt.setString(2, contrasenia);

			rs = stmt.executeQuery();

			if (rs.next()) {
				usu = new Usuario();
				usu.setUsuario(rs.getString("nombre"));
				usu.setContrasenia(rs.getString("contrasenia"));
				usu.setDni(rs.getString("dni"));
				usu.setTelefono(rs.getInt("telefono"));
				usu.setFecha_nac(LocalDate.parse(rs.getDate("fecha_nac") + "", formateador));
				usu.setGenero(rs.getString("genero").charAt(0));
				usu.setTitulacion(rs.getString("titulacion"));
			}

			rs.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		this.cerrarConexion();

		return usu;
	}

	@Override
	public void altaCoche(Coche coc) {
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(ALTA_COCHE);

			stmt.setString(1, coc.getMatricula());
			stmt.setString(2, coc.getMarca());
			stmt.setString(3, coc.getModelo());
			stmt.setInt(4, coc.getEdad());
			stmt.setFloat(5, coc.getPrecio());
			stmt.setString(6, null);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();

	}

	@Override
	public List<Coche> listarCoches() {
		List<Coche> coches = new ArrayList<>();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(LISTAR_COCHES);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Coche coche = new Coche();
				coche.setMatricula(rs.getString("matricula"));

				coches.add(coche);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
		return coches;
	}

	@Override
	public Coche mostrarCoche(String matricula) {
		Coche coche = new Coche();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(MOSTRAR_COCHE);
			stmt.setString(1, matricula);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				coche.setMatricula(rs.getString("matricula"));
				coche.setMarca(rs.getString("marca"));
				coche.setModelo(rs.getString("modelo"));
				coche.setEdad(Integer.parseInt(rs.getString("edad")));
				coche.setPrecio(Float.parseFloat(rs.getString("precio")));
				coche.setDni_propietario(rs.getString("dni_propietario"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
		return coche;
	}

	@Override
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(LISTAR_USUARIOS);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario usu = new Usuario();
				usu.setDni(rs.getString("dni"));
				usuarios.add(usu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.cerrarConexion();
		return usuarios;
	}

	@Override
	public Usuario mostrarUsuario(String dni) {
		@SuppressWarnings("unused")
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Usuario usu = new Usuario();
		
		this.abrirConexion();
		
		try {
			stmt = con.prepareStatement(MOSTRAR_USUARIO);
			
			stmt.setString(1, dni);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				usu.setUsuario(rs.getString("nombre"));
				usu.setContrasenia(rs.getString("contrasenia"));
				usu.setDni(rs.getString("dni"));
				usu.setTelefono(Integer.parseInt(rs.getString("telefono")));
				usu.setGenero(rs.getString("genero").charAt(0));
				usu.setTitulacion(rs.getString("titulacion"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.cerrarConexion();
		return usu;
	}

}
