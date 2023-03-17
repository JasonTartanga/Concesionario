package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.DAO;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;

public class Menu extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private DAO dao;
	private VMain main;
	private JButton btnUsuario;
	private JButton btnVehiculos;
	private JLabel lblNewLabel;

	public Menu(VMain main, boolean b, DAO dao) {
		super(main);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/utilidades/coche.png")));
		setTitle("Menu");
		setResizable(false);
		this.setModal(b);

		this.dao = dao;
		this.main = main;
		setBounds(100, 100, 750, 557);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnUsuario = new JButton("Usuarios");
		btnUsuario.setFont(new Font("Serif", Font.PLAIN, 30));
		btnUsuario.setForeground(new Color(0, 0, 0));
		btnUsuario.setBackground(SystemColor.controlHighlight);
		btnUsuario.setBounds(31, 169, 320, 180);
		contentPanel.add(btnUsuario);
		btnUsuario.addActionListener(this);

		btnVehiculos = new JButton("Vehiculos");
		btnVehiculos.setFont(new Font("Serif", Font.PLAIN, 30));
		btnVehiculos.setForeground(new Color(0, 0, 0));
		btnVehiculos.setBackground(SystemColor.controlHighlight);
		btnVehiculos.setBounds(382, 169, 320, 180);
		contentPanel.add(btnVehiculos);
		btnVehiculos.addActionListener(this);

		lblNewLabel = new JLabel("Que quieres gestionar?");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(232, 36, 269, 39);
		contentPanel.add(lblNewLabel);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				volver();
			}
		});

	}

	protected void volver() {
		this.dispose();
		main.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnUsuario)) {
			usuario();
		} else if (e.getSource().equals(btnVehiculos)) {
			vehiculos();
		}
	}

	private void usuario() {
		MenuUsuario usu = new MenuUsuario(this, true, dao);
		this.setVisible(false);
		usu.setVisible(true);
	}

	private void vehiculos() {
		MenuVehiculos vehi = new MenuVehiculos(this, true, dao);
		this.setVisible(false);
		vehi.setVisible(true);
	}

}
