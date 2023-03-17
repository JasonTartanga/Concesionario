package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Coche;
import clases.Usuario;
import modelo.DAO;

public class VehiculoAUsuario extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JComboBox<String> listaUsuarios;
	private JComboBox<String> listaVehiculos;
	private JButton btnConfirmar;

	private List<Usuario> usuarios;
	private List<Coche> vehiculos;

	private MenuVehiculos menu;
	private DAO dao;

	public VehiculoAUsuario(MenuVehiculos menu, boolean b, DAO dao){
		super(menu);
		this.setModal(b);

		this.menu = menu;
		this.dao = dao;

		setIconImage(Toolkit.getDefaultToolkit().getImage(VehiculoAUsuario.class.getResource("/utilidades/coche.png")));
		setResizable(false);
		setTitle("Asignar Vehiculo a Usuario");
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
		
		JLabel lblNewLabel = new JLabel("Vehiculos disponibles:");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(234, 54, 265, 39);
		contentPanel.add(lblNewLabel);

		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setFont(new Font("Serif", Font.PLAIN, 30));
		lblUsuarios.setBounds(315, 240, 104, 39);
		contentPanel.add(lblUsuarios);

		vehiculos = dao.listarCochesDisponibles();

		listaVehiculos = new JComboBox<String>();
		listaVehiculos.setBounds(144, 147, 445, 39);
		contentPanel.add(listaVehiculos);
		for (Coche coche : vehiculos) {
			listaVehiculos.addItem(coche.getMatricula());
		}
		listaVehiculos.setSelectedIndex(-1);

		usuarios = dao.listarUsuarios();

		listaUsuarios = new JComboBox<String>();
		listaUsuarios.setBounds(144, 333, 445, 39);
		contentPanel.add(listaUsuarios);
		for (Usuario usuario : usuarios) {
			listaUsuarios.addItem(usuario.getDni());
		}
		listaUsuarios.setSelectedIndex(-1);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Serif", Font.PLAIN, 22));
		btnConfirmar.setBounds(270, 470, 193, 57);
		contentPanel.add(btnConfirmar);
		btnConfirmar.addActionListener(this);
	}

	protected void volver() {
		this.dispose();
		menu.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnConfirmar)) {
			modificarPropietario();
		}
		
	}

	private void modificarPropietario() {
		String dni = listaUsuarios.getItemAt(listaUsuarios.getSelectedIndex());
		String matricula = listaVehiculos.getItemAt(listaVehiculos.getSelectedIndex());
		
		dao.modificarPropietario(dni, matricula);
		JOptionPane.showMessageDialog(this, "Coche vinculado correctamente", "", 3);
		
		this.dispose();
		menu.setVisible(true);
		
	}
}
