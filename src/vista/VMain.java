package vista;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VMain extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JButton btnIniciarSesion;
	private JButton btnRegistrarse;

	public VMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 557);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBackground(SystemColor.controlHighlight);
		btnRegistrarse.setFont(new Font("Serif", Font.PLAIN, 24));
		btnRegistrarse.setBounds(218, 309, 297, 105);
		contentPane.add(btnRegistrarse);
		btnRegistrarse.addActionListener(this);

		btnIniciarSesion = new JButton("Iniciar Sesion");
		btnIniciarSesion.setBackground(SystemColor.controlHighlight);
		btnIniciarSesion.setFont(new Font("Serif", Font.PLAIN, 24));
		btnIniciarSesion.setBounds(218, 102, 297, 105);
		contentPane.add(btnIniciarSesion);
		btnIniciarSesion.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	if(e.getSource().equals(btnRegistrarse)) {
		registrarse();
	}else if(e.getSource().equals(btnIniciarSesion)) {
		iniciarSesion();
	}
		
	}

	private void iniciarSesion() {
		// TODO Auto-generated method stub
		
	}

	private void registrarse() {
		Registrar reg = new Registrar(this, true);
		this.setVisible(false);
		reg.setVisible(true);
	}
}
