package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;

public class ListarUsuarios extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> listaUsuarios;
	private JTextArea pantalla;

	private MenuUsuario menu;
	private DAO dao;
	private List<Usuario> usuarios;

	public ListarUsuarios(MenuUsuario menuUsuario, boolean b, DAO dao) {
		super(menuUsuario);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarUsuarios.class.getResource("/utilidades/coche.png")));
		setTitle("Listar Usuarios");
		setResizable(false);
		this.setModal(b);

		this.menu = menuUsuario;
		this.dao = dao;
		usuarios = dao.listarUsuarios();

		setBounds(100, 100, 750, 577);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        volver();
		    }
		});

		JLabel lblNewLabel = new JLabel("Que usuario quieres listar?");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(210, 45, 313, 39);
		contentPanel.add(lblNewLabel);

		listaUsuarios = new JComboBox<String>();
		listaUsuarios.setFont(new Font("Serif", Font.PLAIN, 18));
		listaUsuarios.setBounds(118, 121, 498, 40);
		contentPanel.add(listaUsuarios);
		for (Usuario usuario : usuarios) {
			listaUsuarios.addItem(usuario.getDni());
		}
		listaUsuarios.setSelectedIndex(-1);
		listaUsuarios.addActionListener(this);

		pantalla = new JTextArea();
		pantalla.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pantalla.setEditable(false);
		pantalla.setBounds(75, 218, 583, 256);
		contentPanel.add(pantalla);
	}

	protected void volver() {
		this.dispose();
		menu.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(listaUsuarios)) {
			listar();
		}

	}

	private void listar() {
		Usuario usu = dao.mostrarUsuario(listaUsuarios.getItemAt(listaUsuarios.getSelectedIndex()));

		String mensaje = "Nombre:\t\t\t" + usu.getUsuario() + "\n";
		mensaje += "Contraseña:\t\t"+ usu.getContrasenia() + "\n";
		mensaje += "DNI:\t\t\t" + usu.getDni() + "\n";
		mensaje += "Telefono:\t\t" + usu.getTelefono() + "\n";
		mensaje += "Fecha de nacimientro:\t" + usu.getFecha_nac() + "\n";

		if (usu.getGenero() == 'H') {
			mensaje += "Genero:\t\t\tHombre\n";
		} else {
			mensaje += "Genero:\t\t\tMujer\n";
		}

		mensaje += "Titulacion:\t\t" + usu.getTitulacion() + "\n";

		pantalla.setText(mensaje);
	}

}
