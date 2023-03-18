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
	final private String ALTA_COCHE = "INSERT INTO coche VALUES(?, ?, ?, ?, ?)";
	final private String LISTAR_COCHES = "SELECT matricula FROM coche";
	final private String LISTAR_COCHES_DISPONIBLES = "SELECT matricula FROM coche WHERE matricula not in (SELECT matricula FROM pertenece)";
	final private String MOSTRAR_COCHE = "SELECT * FROM coche WHERE matricula = ?";
	final private String LISTAR_USUARIOS = "SELECT dni FROM usuario";
	final private String MOSTRAR_USUARIO = "SELECT * FROM usuario WHERE dni = ?";
	final private String CAMBIAR_PROPIETARIO = "INSERT INTO pertenece VALUES(?, ?)";
	final private String MOSTRAR_POR_DNI = "SELECT * FROM usuario WHERE dni = ?";
	final private String MODIFICAR_USUARIO = "UPDATE USUARIO SET contrasenia = ?, telefono = ?, fecha_nac = ?, titulacion = ? WHERE dni = ?";
	final private String ELIMINAR_USUARIO = "DELETE FROM USUARIO WHERE dni = ?";
	final private String LISTAR_PROPIETARIOS = "SELECT distinct(dni) FROM pertenece";
//	final private String LISTAR_DATOS_PROPIETARIOS = "SELECT u.dni, nombre, p.matricula, marca, modelo, edad, precio FROM usuario u Join pertenece p on u.dni = p.dni Join coche c on c.matricula = p.matricula";
	final private String MOSTRAR_TODOS_LOS_COCHES_PROPIETARIOS = "SELECT c.* FROM coche c Join pertenece p on c.matricula = p.matricula WHERE dni = ?";
	final private String BORRAR_COCHE = "DELETE FROM coche WHERE matricula = ?";
	final private String MOSTRAR_PROPIETARIO_DE_COCHE = "SELECT u.* FROM usuario u Join pertenece p on u.dni = p.dni WHERE matricula = ?";
	final private String LISTAR_COCHES_CON_PROPIETARIO = "SELECT matricula FROM pertenece";

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

			System.out.println(Date.valueOf(usu.getFecha_nac()));

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
		Usuario usu = new Usuario();
		ResultSet rs;

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(INICIAR_SESION);

			stmt.setString(1, nombre);
			stmt.setString(2, contrasenia);

			rs = stmt.executeQuery();

			if (rs.next()) {
				usu = setUsuario(usu, rs);
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
	public List<Coche> listarCochesDisponibles() {
		List<Coche> coches = new ArrayList<>();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(LISTAR_COCHES_DISPONIBLES);
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
				coche = setCoche(coche, rs);
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
		Usuario usu = new Usuario();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(MOSTRAR_USUARIO);

			stmt.setString(1, dni);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usu = setUsuario(usu, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
		return usu;
	}

	@Override
	public void modificarPropietario(String dni, String matricula) {
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(CAMBIAR_PROPIETARIO);
			stmt.setString(1, dni);
			stmt.setString(2, matricula);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
	}

	@Override
	public Usuario mostrarUsuarioPorDni(String dni) {
		Usuario usu = new Usuario();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(MOSTRAR_POR_DNI);
			stmt.setString(1, dni);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				usu = setUsuario(usu, rs);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
		return usu;
	}

	@Override
	public void modificarUsuario(Usuario usu) {
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(MODIFICAR_USUARIO);
			stmt.setString(1, usu.getContrasenia());
			stmt.setString(2, usu.getTelefono() + "");
			stmt.setString(3, usu.getFecha_nac() + "");
			stmt.setString(4, usu.getTitulacion());
			stmt.setString(5, usu.getDni());

			stmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
	}

	@Override
	public void eliminarUsuario(String dni) {
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(ELIMINAR_USUARIO);
			stmt.setString(1, dni);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Usuario> listarUsuariosPropietarios() {
		List<Usuario> usuarios = new ArrayList<>();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(LISTAR_PROPIETARIOS);

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
	public List<Coche> mostrarTodosCochesPropietaro(String dni) {
		List<Coche> coches = new ArrayList<>();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(MOSTRAR_TODOS_LOS_COCHES_PROPIETARIOS);
			stmt.setString(1, dni);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Coche coc = new Coche();
				coc = setCoche(coc, rs);
				coches.add(coc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
		return coches;
	}

	@Override
	public void eliminarCoche(String matricula) {
		this.abrirConexion();

		try {
			stmt = con.prepareStatement(BORRAR_COCHE);
			stmt.setString(1, matricula);

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.cerrarConexion();
	}

	@Override
	public Usuario mostrarPropietario(String matricula) {
		Usuario usu = new Usuario();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(MOSTRAR_PROPIETARIO_DE_COCHE);
			stmt.setString(1, matricula);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				setUsuario(usu, rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usu;
	}

	private Usuario setUsuario(Usuario usu, ResultSet rs) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			usu.setUsuario(rs.getString("nombre"));
			usu.setContrasenia(rs.getString("contrasenia"));
			usu.setDni(rs.getString("dni"));
			usu.setTelefono(Integer.parseInt(rs.getString("telefono")));
			usu.setFecha_nac(LocalDate.parse(rs.getDate("fecha_nac") + "", formateador));
			usu.setGenero(rs.getString("genero").charAt(0));
			usu.setTitulacion(rs.getString("titulacion"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usu;
	}

	private Coche setCoche(Coche coc, ResultSet rs) {
		try {
			coc.setMatricula(rs.getString("matricula"));
			coc.setMarca(rs.getString("marca"));
			coc.setModelo(rs.getString("modelo"));
			coc.setEdad(Integer.parseInt(rs.getString("edad")));
			coc.setPrecio(Float.parseFloat(rs.getString("precio")));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return coc;
	}

	@Override
	public List<Coche> listarCocheConPropietario() {
		List<Coche> coches = new ArrayList<>();

		this.abrirConexion();

		try {
			stmt = con.prepareStatement(LISTAR_COCHES_CON_PROPIETARIO);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Coche coc = new Coche();
				coc.setMatricula(rs.getString("matricula"));
				coches.add(coc);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return coches;
	}
}
