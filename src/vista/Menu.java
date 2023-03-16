package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.DAO;

public class Menu extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnListarVehiculo;
	private JButton btnAltaCoche;
	private JButton btnListarUsuarios;
	private JButton btnAltaCoche_1;
	
	private DAO dao;

	public Menu(IniciarSesion iniciarSesion, boolean b, DAO dao) {
		super(iniciarSesion);
		this.setModal(b);

		this.dao = dao;
		
		setBounds(100, 100, 750, 557);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnAltaCoche = new JButton("Alta Vehiculo");
		btnAltaCoche.setBackground(SystemColor.controlHighlight);
		btnAltaCoche.setFont(new Font("Serif", Font.PLAIN, 30));
		btnAltaCoche.setBounds(10, 11, 330, 214);
		contentPanel.add(btnAltaCoche);
		btnAltaCoche.addActionListener(this);

		btnListarVehiculo = new JButton("Listar Vehiculos");
		btnListarVehiculo.setFont(new Font("Serif", Font.PLAIN, 30));
		btnListarVehiculo.setBackground(SystemColor.controlHighlight);
		btnListarVehiculo.setBounds(394, 11, 330, 214);
		contentPanel.add(btnListarVehiculo);
		btnListarVehiculo.addActionListener(this);
		
		btnAltaCoche_1 = new JButton("");
		btnAltaCoche_1.setFont(new Font("Serif", Font.PLAIN, 30));
		btnAltaCoche_1.setBackground(SystemColor.controlHighlight);
		btnAltaCoche_1.setBounds(10, 293, 330, 214);
		contentPanel.add(btnAltaCoche_1);
		
		btnListarUsuarios = new JButton("Listar Usuarios");
		btnListarUsuarios.setFont(new Font("Serif", Font.PLAIN, 30));
		btnListarUsuarios.setBackground(SystemColor.controlHighlight);
		btnListarUsuarios.setBounds(394, 293, 330, 214);
		contentPanel.add(btnListarUsuarios);
		btnListarUsuarios.addActionListener(this);

		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAltaCoche)) {
			altaCoche();
		}else if(e.getSource().equals(btnListarVehiculo)) {
			listarVehiculo();
		}else if(e.getSource().equals(btnListarUsuarios)) {
			listarUsuarios();
		}

	}

	private void listarUsuarios() {
		ListarUsuarios list = new ListarUsuarios(this, true, dao);
		this.setVisible(false);
		list.setVisible(true);
		
	}

	private void listarVehiculo() {
		ListarVehiculo list = new ListarVehiculo(this, true, dao);
		this.setVisible(false);
		list.setVisible(true);
	}

	private void altaCoche() {
		IntroducirCoche ic = new IntroducirCoche(this, true, dao);
		this.setVisible(false);
		ic.setVisible(true);

	}
}
