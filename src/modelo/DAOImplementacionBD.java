package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import clases.Usuario;

public class DAOImplementacionBD implements DAO {

	private Connection con = null;
	private PreparedStatement stmt;

	final private String URL = "jdbc:mysql://localhost:3306/CONCESIONARIO?serverTimezone=Europe/Madrid&useSSL=false";
	final private String USER = "root";
	final private String PASSWORD = "abcd*1234";

	final private String INICIAR_SESION = "SELECT * FROM usuario WHERE nombre = ? and contrasenia = ?";
	final private String ALTA_PROPIETARIO = "INSERT INTO usuario VALUES(?, ?, ?, ?, ?, ?, ?)";

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
			
			System.out.println(stmt);
			System.out.println(rs);
			System.out.println(rs.getRow());
			
			if (rs.next()) {
				usu = new Usuario();
				usu.setUsuario(rs.getString("nombre"));
				usu.setContrasenia(rs.getString("contrasenia"));
				usu.setDni(rs.getString("dni"));
				usu.setTelefono(rs.getInt("telefono"));
				usu.setFecha_nac(LocalDate.parse(rs.getDate("fecha_nac")+ "", formateador));
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

}
