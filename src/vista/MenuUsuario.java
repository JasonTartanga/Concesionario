package vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.DAO;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.SystemColor;

public class MenuUsuario extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JButton btnInsertar;
	private JButton btnListar;
	private JButton btnModificar;
	private JButton btnEliminar;

	private Menu menu;
	private DAO dao;

	public MenuUsuario(Menu menu, boolean b, DAO dao) {
		super(menu);
		this.setModal(b);

		this.menu = menu;
		this.dao = dao;

		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuVehiculos.class.getResource("/utilidades/coche.png")));
		setTitle("Menu Usuario");
		setResizable(false);
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

		btnInsertar = new JButton("Listar coches\r\n de usuarios");
		btnInsertar.setBackground(SystemColor.controlHighlight);
		btnInsertar.setFont(new Font("Serif", Font.PLAIN, 30));
		btnInsertar.setBounds(22, 46, 333, 200);
		contentPanel.add(btnInsertar);
		btnInsertar.addActionListener(this);

		btnListar = new JButton("Listar usuarios");
		btnListar.setBackground(SystemColor.controlHighlight);
		btnListar.setFont(new Font("Serif", Font.PLAIN, 30));
		btnListar.setBounds(377, 46, 333, 200);
		contentPanel.add(btnListar);
		btnListar.addActionListener(this);

		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(SystemColor.controlHighlight);
		btnModificar.setFont(new Font("Serif", Font.PLAIN, 30));
		btnModificar.setBounds(22, 292, 333, 200);
		contentPanel.add(btnModificar);
		btnModificar.addActionListener(this);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(SystemColor.controlHighlight);
		btnEliminar.setFont(new Font("Serif", Font.PLAIN, 30));
		btnEliminar.setBounds(377, 292, 333, 200);
		contentPanel.add(btnEliminar);
		btnEliminar.addActionListener(this);

	}

	protected void volver() {
		this.dispose();
		menu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnInsertar)) {
			introducir();
		} else if (e.getSource().equals(btnListar)) {
			listar();
		} else if (e.getSource().equals(btnModificar)) {
			modificar();
		} else if (e.getSource().equals(btnEliminar)) {
			eliminar();
		}

	}

	private void eliminar() {
		EliminarUsuario elim = new EliminarUsuario(this, true, dao);
		this.setVisible(false);
		elim.setVisible(true);
	}

	private void modificar() {
		ModificarUsuario vau = new ModificarUsuario(this, true, dao);
		this.setVisible(false);
		vau.setVisible(true);

	}

	private void listar() {
		ListarUsuarios list = new ListarUsuarios(this, true, dao);
		this.setVisible(false);
		list.setVisible(true);
	}

	private void introducir() {
		ListarCochesDePropietario list = new ListarCochesDePropietario(this, true, dao);
		this.setVisible(false);
		list.setVisible(true);

	}

}
