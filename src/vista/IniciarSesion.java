package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Usuario;
import modelo.DAO;

public class IniciarSesion extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField usuario;
	private JPasswordField contrasenia;
	private JButton btnConfirmar;
	private DAO dao;

	public IniciarSesion(VMain vMain, boolean b, DAO dao) {
		super(vMain);
		this.setModal(b);

		this.dao = dao;

		setBounds(100, 100, 750, 557);
		setLocationRelativeTo(null);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		usuario = new JTextField();
		usuario.setFont(new Font("Serif", Font.PLAIN, 30));
		usuario.setBounds(200, 95, 340, 58);
		contentPanel.add(usuario);
		usuario.setColumns(10);

		contrasenia = new JPasswordField();
		contrasenia.setFont(new Font("Serif", Font.PLAIN, 30));
		contrasenia.setBounds(200, 282, 340, 58);
		contentPanel.add(contrasenia);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Serif", Font.PLAIN, 20));
		btnConfirmar.setBounds(250, 455, 234, 52);
		contentPanel.add(btnConfirmar);
		btnConfirmar.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(316, 45, 101, 39);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Contraseña:");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(296, 232, 141, 39);
		contentPanel.add(lblNewLabel_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnConfirmar)) {
			if (comprobar()) {
				inicarSesion();
			} else {
				JOptionPane.showMessageDialog(null, "El usuario y contraseña no son correctos", "ERROR", 0);
			}
		}

	}

	private void inicarSesion() {
		Menu menu = new Menu(this, true);
		this.setVisible(false);
		menu.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	private boolean comprobar() {
		Usuario usu = dao.inicarSesion(usuario.getText(), contrasenia.getText());

		if (usu != null) {
			return true;
		} else {
			contrasenia.setText("");
			return false;
		}

	}
}
