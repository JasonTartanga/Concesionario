package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;

public class ModificarUsuario extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private MenuUsuario menu;
	private DAO dao;
	private Usuario usuario;

	private JTextField fecha_na;
	private JTextField telefono;
	private JPasswordField contrasenia;
	private JComboBox<String> titulacion;
	private JComboBox<String> listaUsuario;
	private JButton btnLimpiar;
	private JButton btnConfirmar;
	private List<Usuario> usuarios;

	public ModificarUsuario(MenuUsuario menu, boolean b, DAO dao) {
		super(menu);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ModificarUsuario.class.getResource("/utilidades/coche.png")));
		setResizable(false);
		setTitle("Modificar Usuario");
		this.setModal(b);

		this.menu = menu;
		this.dao = dao;

		setBounds(100, 100, 420, 577);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});

		
		JLabel lblNewLabel = new JLabel("Que usuario deseas modificar?");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 16));
		lblNewLabel.setBounds(109, 27, 185, 22);
		contentPanel.add(lblNewLabel);

		usuarios = dao.listarUsuarios();

		listaUsuario = new JComboBox<String>();
		listaUsuario.setBounds(61, 60, 281, 35);
		contentPanel.add(listaUsuario);
		for (Usuario usuario : usuarios) {
			listaUsuario.addItem(usuario.getDni());
		}
		listaUsuario.setSelectedIndex(-1);
		listaUsuario.addActionListener(this);

		fecha_na = new JTextField();
		fecha_na.setFont(new Font("Serif", Font.PLAIN, 14));
		fecha_na.setColumns(10);
		fecha_na.setBounds(154, 300, 200, 35);
		contentPanel.add(fecha_na);

		titulacion = new JComboBox<String>();
		titulacion.setBounds(154, 388, 200, 35);
		contentPanel.add(titulacion);
		titulacion.addItem("ESO");
		titulacion.addItem("Bachiller");
		titulacion.addItem("Formacion Profesional");
		titulacion.addItem("Universidad");
		titulacion.setSelectedIndex(-1);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(SystemColor.controlHighlight);
		btnLimpiar.setFont(new Font("Serif", Font.PLAIN, 16));
		btnLimpiar.setBounds(48, 482, 129, 45);
		contentPanel.add(btnLimpiar);
		btnLimpiar.addActionListener(this);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(SystemColor.controlHighlight);
		btnConfirmar.setFont(new Font("Serif", Font.PLAIN, 16));
		btnConfirmar.setBounds(225, 482, 129, 45);
		contentPanel.add(btnConfirmar);
		btnConfirmar.addActionListener(this);

		JLabel lblNewLabel_1 = new JLabel("Contraseña:");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(47, 146, 66, 19);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Telefono:");
		lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(51, 218, 53, 19);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Fech_na:");
		lblNewLabel_3.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(52, 306, 51, 19);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Titulacion");
		lblNewLabel_4.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(50, 394, 56, 19);
		contentPanel.add(lblNewLabel_4);

		telefono = new JTextField();
		telefono.setFont(new Font("Serif", Font.PLAIN, 14));
		telefono.setBounds(153, 212, 200, 35);
		contentPanel.add(telefono);
		telefono.setColumns(10);

		contrasenia = new JPasswordField();
		contrasenia.setFont(new Font("Serif", Font.PLAIN, 14));
		contrasenia.setBounds(154, 140, 200, 35);
		contentPanel.add(contrasenia);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 114, 384, 2);
		contentPanel.add(separator);
	}

	protected void volver() {
		this.dispose();
		menu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpiar)) {
			limpiar();
		} else if (e.getSource().equals(listaUsuario)) {
			mostrar();
		} else if (e.getSource().equals(btnConfirmar)) {
			if (comprobar()) {
				modificar();
			}
		}

	}

	@SuppressWarnings("deprecation")
	private void modificar() {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		usuario.setContrasenia(contrasenia.getText());
		usuario.setTelefono(Integer.parseInt(telefono.getText()));
		usuario.setFecha_nac(LocalDate.parse(fecha_na.getText(), formateador));
		usuario.setTitulacion(titulacion.getItemAt(titulacion.getSelectedIndex()));

		dao.modificarUsuario(usuario);

		JOptionPane.showMessageDialog(this, "Usuario modificado con exito", "", 3);
		this.dispose();
		menu.setVisible(true);
	}

	private void mostrar() {
		usuario = dao.mostrarUsuarioPorDni(listaUsuario.getItemAt(listaUsuario.getSelectedIndex()));

		int indice = -1;

		contrasenia.setText(usuario.getContrasenia());
		telefono.setText(usuario.getTelefono() + "");
		fecha_na.setText(usuario.getFecha_nac() + "");

		switch (usuario.getTitulacion()) {
		case "ESO":
			indice = 0;
			break;
		case "Bachiller":
			indice = 1;
			break;
		case "Formacion Profesional":
			indice = 2;
			break;
		case "Universidad":
			indice = 3;
			break;

		default:
			break;
		}

		titulacion.setSelectedIndex(indice);

	}

	@SuppressWarnings("deprecation")
	private boolean comprobar() {
		boolean correcto = true;

		// Comprobamos la contraseña
		if (contrasenia.getText().length() > 25 && !contrasenia.getText().isBlank()) {
			contrasenia.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "La contraseña no puede contener mas de 50 caracteres", "ERROR", 0);
			correcto = false;
		} else {
			contrasenia.setBackground(new Color(102, 204, 102));
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
		try {
			DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			@SuppressWarnings("unused")
			LocalDate fecha = LocalDate.parse(fecha_na.getText(), formateador);
			fecha_na.setBackground(new Color(102, 204, 102));

		} catch (DateTimeParseException e) {
			fecha_na.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "Introduce una fecha valida");
			correcto = false;
		}

		// Comprobamos la titulacion
		if (titulacion.getSelectedIndex() == -1) {
			titulacion.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "Introduce una titulacon");
			correcto = false;
		} else {
			titulacion.setBackground(new Color(102, 204, 102));
		}

		if (correcto) {
			return true;
		} else {
			return false;
		}
	}

	private void limpiar() {
		JTextField[] campos = { contrasenia, telefono, fecha_na };

		for (int i = 0; i < campos.length; i++) {
			campos[i].setText("");
		}
		titulacion.setSelectedIndex(-1);
	}
}
