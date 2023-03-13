package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class Registrar extends JDialog {


	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField contrasenia;
	private JTextField dni;
	private JTextField telefono;
	private JTextField direccion;

	public Registrar(VMain vMain, boolean b) {
		super(vMain);
		this.setModal(b);
		
		setBounds(100, 100, 500, 650);
		setLocationRelativeTo(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(SystemColor.controlHighlight);
		btnLimpiar.setFont(new Font("Serif", Font.PLAIN, 18));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimpiar.setBounds(54, 543, 161, 57);
		contentPanel.add(btnLimpiar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(SystemColor.controlHighlight);
		btnConfirmar.setFont(new Font("Serif", Font.PLAIN, 18));
		btnConfirmar.setBounds(269, 543, 161, 57);
		contentPanel.add(btnConfirmar);
		
		JLabel JLabel1 = new JLabel("Usuario:");
		JLabel1.setFont(new Font("Serif", Font.PLAIN, 16));
		JLabel1.setBounds(26, 23, 50, 22);
		contentPanel.add(JLabel1);
		
		JLabel JLabel2 = new JLabel("Contraseña:");
		JLabel2.setFont(new Font("Serif", Font.PLAIN, 16));
		JLabel2.setBounds(26, 75, 72, 22);
		contentPanel.add(JLabel2);
		
		textField = new JTextField();
		textField.setBounds(183, 22, 257, 29);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		contrasenia = new JPasswordField();
		contrasenia.setBounds(183, 74, 257, 29);
		contentPanel.add(contrasenia);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 114, 464, 8);
		contentPanel.add(separator);
		
		JLabel lblNewLabel = new JLabel("DNI:");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel.setBounds(32, 146, 42, 19);
		contentPanel.add(lblNewLabel);
		
		dni = new JTextField();
		dni.setBounds(186, 146, 257, 29);
		contentPanel.add(dni);
		dni.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setFont(new Font("Serif", Font.PLAIN, 14));
		lblTelefono.setBounds(29, 211, 53, 19);
		contentPanel.add(lblTelefono);
		
		telefono = new JTextField();
		telefono.setColumns(10);
		telefono.setBounds(186, 208, 257, 29);
		contentPanel.add(telefono);
		
		JLabel lblNewLabel_1 = new JLabel("Genero");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(26, 334, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		JRadioButton rdbtnHombre = new JRadioButton("Hombre");
		rdbtnHombre.setFont(new Font("Serif", Font.PLAIN, 14));
		rdbtnHombre.setBounds(183, 334, 109, 23);
		contentPanel.add(rdbtnHombre);
		
		JRadioButton rdbtnMujer = new JRadioButton("Mujer");
		rdbtnMujer.setFont(new Font("Serif", Font.PLAIN, 14));
		rdbtnMujer.setBounds(183, 373, 109, 23);
		contentPanel.add(rdbtnMujer);
		
		JRadioButton rdbtnOtro = new JRadioButton("Otro");
		rdbtnOtro.setFont(new Font("Serif", Font.PLAIN, 14));
		rdbtnOtro.setBounds(183, 409, 109, 23);
		contentPanel.add(rdbtnOtro);
		
		JComboBox titulacion = new JComboBox();
		titulacion.setBounds(183, 464, 257, 29);
		contentPanel.add(titulacion);
		
		JLabel lblNewLabel_2 = new JLabel("Titulacion:");
		lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(26, 471, 59, 19);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblDireccion = new JLabel("Direccion:");
		lblDireccion.setFont(new Font("Serif", Font.PLAIN, 14));
		lblDireccion.setBounds(26, 279, 58, 19);
		contentPanel.add(lblDireccion);
		
		direccion = new JTextField();
		direccion.setColumns(10);
		direccion.setBounds(186, 276, 257, 29);
		contentPanel.add(direccion);
	}
}
