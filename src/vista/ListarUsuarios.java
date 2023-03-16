package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
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

	private JButton btnConfirmar;
	private JComboBox<String> listaUsuarios;
	private JTextArea pantalla;

	private Menu menu;
	private DAO dao;
	private List<Usuario> usuarios;

	public ListarUsuarios(Menu menu, boolean b, DAO dao) {
		super(menu);
		this.setModal(b);

		this.menu = menu;
		this.dao = dao;
		usuarios = dao.listarUsuarios();

		setBounds(100, 100, 750, 577);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(SystemColor.controlHighlight);
		btnConfirmar.setFont(new Font("Serif", Font.PLAIN, 18));
		btnConfirmar.setBounds(252, 467, 230, 60);
		contentPanel.add(btnConfirmar);
		btnConfirmar.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Que usuario quieres listar?");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNewLabel.setBounds(272, 51, 190, 24);
		contentPanel.add(lblNewLabel);

		listaUsuarios = new JComboBox<String>();
		listaUsuarios.setFont(new Font("Serif", Font.PLAIN, 18));
		listaUsuarios.setBounds(118, 112, 498, 40);
		contentPanel.add(listaUsuarios);
		for (Usuario usuario : usuarios) {
			listaUsuarios.addItem(usuario.getDni());
		}
		listaUsuarios.setSelectedIndex(-1);

		pantalla = new JTextArea();
		pantalla.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pantalla.setEditable(false);
		pantalla.setBounds(75, 199, 583, 215);
		contentPanel.add(pantalla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnConfirmar)) {
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
