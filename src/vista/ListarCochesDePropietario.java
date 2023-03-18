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

import clases.Coche;
import clases.Usuario;
import modelo.DAO;

public class ListarCochesDePropietario extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JComboBox<String> listaPropietarios;
	private JTextArea pantalla;
	private List<Usuario> usuarios;

	private MenuUsuario menu;
	private DAO dao;

	public ListarCochesDePropietario(MenuUsuario menu, boolean b, DAO dao) {
		super(menu);
		this.setModal(b);

		this.menu = menu;
		this.dao = dao;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ListarCochesDePropietario.class.getResource("/utilidades/coche.png")));
		setResizable(false);
		setTitle("Listar Coches de Usuarios");
		setBounds(100, 100, 750, 577);
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

		JLabel lblNewLabel = new JLabel("Que propietario quieres Listar?");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(184, 56, 366, 39);
		contentPanel.add(lblNewLabel);

		usuarios = dao.listarUsuariosPropietarios();

		listaPropietarios = new JComboBox<String>();
		listaPropietarios.setBounds(147, 151, 439, 34);
		contentPanel.add(listaPropietarios);
		for (Usuario usuario : usuarios) {
			listaPropietarios.addItem(usuario.getDni());
		}
		listaPropietarios.setSelectedIndex(-1);
		listaPropietarios.addActionListener(this);

		pantalla = new JTextArea();
		pantalla.setWrapStyleWord(true);
		pantalla.setLineWrap(true);
		pantalla.setEditable(false);
		pantalla.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pantalla.setBounds(69, 241, 596, 238);
		contentPanel.add(pantalla);
	}

	protected void volver() {
		this.dispose();
		menu.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(listaPropietarios)) {
			listar();
		}

	}

	private void listar() {
		String mensaje = "";
		List<Coche> coches = dao
				.mostrarTodosCochesPropietaro(listaPropietarios.getItemAt(listaPropietarios.getSelectedIndex()));

		for (Coche coche : coches) {
			mensaje += "---------- " + coche.getMatricula() + " ----------\n";
			mensaje += "Marca:\t" + coche.getMarca() + "\n";
			mensaje += "Modelo:\t" + coche.getModelo() + "\n";
			mensaje += "Edad:\t" + coche.getEdad() + "\n";
			mensaje += "Precio:\t" + coche.getPrecio() + "\n\n";
		}
		
		pantalla.setText(mensaje);

	}
}
