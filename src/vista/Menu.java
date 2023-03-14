package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnListarVehiculo;
	private JButton btnAltaCoche;
	private JButton btnNewButton;
	private JButton btnNewButton1;

	public Menu(IniciarSesion iniciarSesion, boolean b) {
		super(iniciarSesion);
		this.setModal(b);

		setBounds(100, 100, 750, 557);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnAltaCoche = new JButton("Alta Vehiculo");
		btnAltaCoche.setBackground(SystemColor.controlHighlight);
		btnAltaCoche.setFont(new Font("Serif", Font.PLAIN, 30));
		btnAltaCoche.setBounds(24, 30, 330, 214);
		contentPanel.add(btnAltaCoche);
		btnAltaCoche.addActionListener(this);

		btnListarVehiculo = new JButton("Listar Vehiculos");
		btnListarVehiculo.setFont(new Font("Serif", Font.PLAIN, 30));
		btnListarVehiculo.setBackground(SystemColor.controlHighlight);
		btnListarVehiculo.setBounds(378, 30, 330, 214);
		contentPanel.add(btnListarVehiculo);
		btnListarVehiculo.addActionListener(this);

		btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("Serif", Font.PLAIN, 30));
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setBounds(24, 274, 330, 214);
		contentPanel.add(btnListarVehiculo);
		btnNewButton.addActionListener(this);

		btnNewButton1 = new JButton("");
		btnNewButton1.setFont(new Font("Serif", Font.PLAIN, 30));
		btnNewButton1.setBackground(SystemColor.controlHighlight);
		btnNewButton1.setBounds(378, 274, 330, 214);
		contentPanel.add(btnNewButton1);
		contentPanel.add(btnListarVehiculo);
		btnNewButton1.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAltaCoche)) {
			altaCoche();
		}

	}

	private void altaCoche() {
		IntroducirCoche ic = new IntroducirCoche(this, true);
		this.setVisible(false);
		ic.setVisible(false);

	}

}
