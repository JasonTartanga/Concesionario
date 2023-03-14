package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;

public class Registrar extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField usuario;
	private JPasswordField contrasenia;
	private JTextField dni;
	private JTextField telefono;
	private JTextField fecha_nac;
	private JComboBox<String> titulacion;

	private JRadioButton rdbtnHombre;
	private JRadioButton rdbtnMujer;
	private JRadioButton rdbtnOtro;
	private JButton btnLimpiar;
	private JButton btnConfirmar;

	private ButtonGroup genero;

	private DAO dao;

	public Registrar(VMain vMain, boolean b, DAO dao) {
		super(vMain);

		this.dao = dao;
		setTitle("Registrarse");
		this.setModal(b);

		setBounds(100, 100, 500, 650);
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(SystemColor.controlHighlight);
		btnLimpiar.setFont(new Font("Serif", Font.PLAIN, 18));
		btnLimpiar.setBounds(54, 543, 161, 57);
		contentPanel.add(btnLimpiar);
		btnLimpiar.addActionListener(this);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(SystemColor.controlHighlight);
		btnConfirmar.setFont(new Font("Serif", Font.PLAIN, 18));
		btnConfirmar.setBounds(269, 543, 161, 57);
		contentPanel.add(btnConfirmar);
		btnConfirmar.addActionListener(this);

		JLabel JLabel1 = new JLabel("Usuario:");
		JLabel1.setFont(new Font("Serif", Font.PLAIN, 16));
		JLabel1.setBounds(26, 23, 50, 22);
		contentPanel.add(JLabel1);

		JLabel JLabel2 = new JLabel("Contraseña:");
		JLabel2.setFont(new Font("Serif", Font.PLAIN, 16));
		JLabel2.setBounds(26, 75, 72, 22);
		contentPanel.add(JLabel2);

		usuario = new JTextField();
		usuario.setBounds(183, 22, 257, 29);
		contentPanel.add(usuario);
		usuario.setColumns(10);

		contrasenia = new JPasswordField();
		contrasenia.setBounds(183, 74, 257, 29);
		contentPanel.add(contrasenia);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 114, 464, 8);
		contentPanel.add(separator);

		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel.setBounds(32, 146, 42, 19);
		contentPanel.add(lblNewLabel);

		dni = new JTextField();
		dni.setBounds(186, 146, 257, 29);
		contentPanel.add(dni);
		dni.setColumns(10);

		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Serif", Font.PLAIN, 14));
		lblTelefono.setBounds(29, 211, 53, 19);
		contentPanel.add(lblTelefono);

		telefono = new JTextField();
		telefono.setColumns(10);
		telefono.setBounds(186, 208, 257, 29);
		contentPanel.add(telefono);

		JLabel lblNewLabel_1 = new JLabel("Genero");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 334, 46, 14);
		contentPanel.add(lblNewLabel_1);

		rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setFont(new Font("Serif", Font.PLAIN, 14));
		rdbtnHombre.setBounds(183, 334, 109, 23);
		contentPanel.add(rdbtnHombre);

		rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setFont(new Font("Serif", Font.PLAIN, 14));
		rdbtnMujer.setBounds(183, 373, 109, 23);
		contentPanel.add(rdbtnMujer);

		rdbtnOtro = new JRadioButton("Otro");
		rdbtnOtro.setEnabled(false);
		rdbtnOtro.setFont(new Font("Serif", Font.PLAIN, 14));
		rdbtnOtro.setBounds(183, 409, 109, 23);
		contentPanel.add(rdbtnOtro);
		
		genero = new ButtonGroup();
		genero.add(rdbtnHombre);
		genero.add(rdbtnMujer);

		titulacion = new JComboBox<String>();
		titulacion.setBounds(183, 464, 257, 29);
		contentPanel.add(titulacion);
		titulacion.setSelectedIndex(-1);
		titulacion.addItem("ESO");
		titulacion.addItem("Bachiller");
		titulacion.addItem("Formacion Profesional");
		titulacion.addItem("Universidad");

		JLabel lblNewLabel_2 = new JLabel("Titulacion:");
		lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(26, 471, 59, 19);
		contentPanel.add(lblNewLabel_2);

		JLabel lblDireccion = new JLabel("Fecha nacimiento:");
		lblDireccion.setFont(new Font("Serif", Font.PLAIN, 14));
		lblDireccion.setBounds(26, 279, 102, 19);
		contentPanel.add(lblDireccion);

		fecha_nac = new JTextField();
		fecha_nac.setColumns(10);
		fecha_nac.setBounds(186, 276, 257, 29);
		contentPanel.add(fecha_nac);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpiar)) {
			limpiar();
		} else if (e.getSource().equals(btnConfirmar)) {
			if (confirmar()) {
				altaUsuario();
			}
		}
	}

	private void altaUsuario() {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fecha = LocalDate.parse(fecha_nac.getText(), formateador);

		char genero;
		if (rdbtnHombre.isSelected()) {
			genero = 'H';
		} else {
			genero = 'M';
		}

		Usuario crear = new Usuario();
		crear.setUsuario(usuario.getText());
		crear.setContrasenia(contrasenia.getText());
		crear.setDni(dni.getText());
		crear.setTelefono(Integer.parseInt(telefono.getText()));
		crear.setFecha_nac(fecha);
		crear.setGenero(genero);
		crear.setTitulacion(titulacion.getItemAt(titulacion.getSelectedIndex()));

		dao.altaPropietario(crear);
	}

	@SuppressWarnings("deprecation")
	private boolean confirmar() {
		JTextField[] campos = { usuario, contrasenia, dni, telefono, fecha_nac };
		boolean correcto = true;

		// Comprobamos si los campos estan vacios
		for (int i = 0; i < campos.length; i++) {
			if (campos[i].getText().isBlank()) {
				campos[i].setBackground(new Color(153, 51, 51));
				correcto = false;
			} else {
				usuario.setBackground(new Color(102, 204, 102));
			}
		}

		if (!correcto) {
			JOptionPane.showMessageDialog(null, "Rellene todos los campos", "ERROR", 0);
			return false;
		}

		// Comprobamos que el usuario
		if (usuario.getText().length() > 50) {
			usuario.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "El nombre no puede contener mas de 50 caracteres", "ERROR", 0);
			correcto = false;
		} else {
			usuario.setBackground(new Color(102, 204, 102));
		}

		// Comprobamos la contraseña
		if (contrasenia.getText().length() > 25) {
			JOptionPane.showMessageDialog(null, "La contraseña no puede contener mas de 50 caracteres", "ERROR", 0);
			correcto = false;
		} else {
			contrasenia.setBackground(new Color(102, 204, 102));
		}

		// Comprobamos el dni
		if (!comprobarDni(dni.getText())) {
			JOptionPane.showMessageDialog(null, "El DNI no es correcto", "ERROR", 0);
			correcto = false;
		} else {
			dni.setBackground(new Color(102, 204, 102));
		}

		// Comprobamos el telefono
		if (telefono.getText().length() != 9) {
			telefono.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "El telefono no tiene 9 digitos", "ERROR", 0);
			correcto = false;
		} else {
			telefono.setBackground(new Color(102, 204, 102));
		}

		// Comrpobamos la fecha
		if (fecha_nac.getText().length() > 25) {
			fecha_nac.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "La fecha no es correcta");
			correcto = false;
		} else {
			fecha_nac.setBackground(new Color(102, 204, 102));
		}

		// Comprobamos el genero

		return true;
	}

	private void limpiar() {
		usuario.setText("");
		contrasenia.setText("");
		dni.setText("");
		telefono.setText("");
		fecha_nac.setText("");
		rdbtnHombre.setSelected(false);
		rdbtnMujer.setSelected(false);
		titulacion.setSelectedIndex(-1);
	}

	public boolean comprobarDni(String dni) {
		if (dni.length() != 9) {
			return false;
		}

		String numeroStr = dni.substring(0, 8);
		int numero;
		try {
			numero = Integer.parseInt(numeroStr);
		} catch (NumberFormatException e) {
			return false;
		}

		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		char letra = Character.toUpperCase(dni.charAt(8));
		if (letras.indexOf(letra) == -1) {
			return false;
		}

		int resto = numero % 23;
		char letraCalculada = letras.charAt(resto);
		return letraCalculada == letra;
	}
}
