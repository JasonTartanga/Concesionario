package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clases.Coche;
import modelo.DAO;
import java.awt.Toolkit;

public class IntroducirCoche extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField precio;
	private JTextField marca;
	private JTextField modelo;
	private JTextField edad;
	private JTextField matricula;

	private JButton btnLimpiar;
	private JButton btnConfirmar;

	private MenuVehiculos menu;
	private DAO dao;

	public IntroducirCoche(MenuVehiculos menu, boolean b, DAO dao) {
		super(menu);
		setIconImage(Toolkit.getDefaultToolkit().getImage(IntroducirCoche.class.getResource("/utilidades/coche.png")));
		setTitle("Introducir coches");
		setResizable(false);
		this.setModal(b);

		this.menu = menu;
		this.dao = dao;

		
		setBounds(100, 100, 750, 510);
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
		
		JLabel lblNewLabel = new JLabel("Matricula:");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNewLabel.setBounds(331, 33, 72, 24);
		contentPanel.add(lblNewLabel);

		precio = new JTextField();
		precio.setBounds(122, 254, 230, 27);
		contentPanel.add(precio);
		precio.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Marca:");
		lblNewLabel_1.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(27, 163, 68, 24);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Modelo:");
		lblNewLabel_2.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(379, 163, 68, 24);
		contentPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Edad:");
		lblNewLabel_3.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(379, 255, 68, 24);
		contentPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Precio:");
		lblNewLabel_4.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(27, 255, 68, 24);
		contentPanel.add(lblNewLabel_4);

		marca = new JTextField();
		marca.setColumns(10);
		marca.setBounds(122, 162, 230, 27);
		contentPanel.add(marca);

		modelo = new JTextField();
		modelo.setColumns(10);
		modelo.setBounds(474, 162, 230, 27);
		contentPanel.add(modelo);

		edad = new JTextField();
		edad.setColumns(10);
		edad.setBounds(474, 254, 230, 26);
		contentPanel.add(edad);

		matricula = new JTextField();
		matricula.setColumns(10);
		matricula.setBounds(252, 84, 230, 27);
		contentPanel.add(matricula);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(SystemColor.controlHighlight);
		btnLimpiar.setFont(new Font("Serif", Font.PLAIN, 18));
		btnLimpiar.setBounds(131, 393, 170, 55);
		contentPanel.add(btnLimpiar);
		btnLimpiar.addActionListener(this);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(SystemColor.controlHighlight);
		btnConfirmar.setFont(new Font("Serif", Font.PLAIN, 18));
		btnConfirmar.setBounds(432, 393, 170, 55);
		contentPanel.add(btnConfirmar);
		btnConfirmar.addActionListener(this);
	}
	
	protected void volver() {
		this.dispose();
		menu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnLimpiar)) {
			limpiar();
		} else if (e.getSource().equals(btnConfirmar)) {
			if (comprobar()) {
				alta();
			}
		}

	}

	private void alta() {
		Coche coc = new Coche();
		coc.setMatricula(matricula.getText());
		coc.setMarca(marca.getText());
		coc.setModelo(modelo.getText());
		coc.setEdad(Integer.parseInt(edad.getText()));
		coc.setPrecio(Float.parseFloat(precio.getText()));

		dao.altaCoche(coc);

		JOptionPane.showMessageDialog(this, "ALTA CORRECTA", "", 3);
		this.dispose();
		menu.setVisible(true);
	}

	private boolean comprobar() {
		boolean correcto = true;
		JTextField[] campos = { modelo, marca, edad, precio, matricula };

		for (int i = 0; i < campos.length; i++) {
			if (campos[i].getText().isBlank()) {
				campos[i].setBackground(new Color(153, 51, 51));
				correcto = false;
			}
		}

		if (!correcto) {
			return false;
		}

		// Comprobar matricula
		if (matricula.getText().length() != 7) {
			matricula.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "La matricula debe tener 7 caracteres", "ERROR", 0);
			correcto = false;
		} else {
			matricula.setBackground(new Color(102, 204, 102));
		}

		// Comprobar marca
		if (marca.getText().length() > 50) {
			marca.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "La marca no puede tener mas de 50 caracteres", "ERROR", 0);
			correcto = false;
		} else {
			marca.setBackground(new Color(102, 204, 102));
		}

		// Comprobar modelo
		if (modelo.getText().length() > 50) {
			modelo.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "El modelo no puede tener mas de 50 caracteres", "ERROR", 0);
			correcto = false;
		} else {
			modelo.setBackground(new Color(102, 204, 102));
		}

		// Comprobar la edad
		try {
			Integer.parseInt(edad.getText());
			edad.setBackground(new Color(102, 204, 102));
		} catch (NumberFormatException e) {

			edad.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "La matricula debe tener 7 caracteres", "ERROR", 0);
			correcto = false;
		}

		// Comprobar el precio
		try {
			Float.parseFloat(precio.getText());
			precio.setBackground(new Color(102, 204, 102));

		} catch (NumberFormatException e) {
			precio.setBackground(new Color(153, 51, 51));
			JOptionPane.showMessageDialog(null, "La matricula debe tener 7 caracteres", "ERROR", 0);
			correcto = false;
		}

		if (correcto) {
			return true;
		} else {
			return false;
		}

	}

	private void limpiar() {
		JTextField[] campos = { modelo, marca, edad, precio, matricula };

		for (int i = 0; i < campos.length; i++) {
			campos[i].setText("");
			campos[i].setBackground(new Color(240, 240, 240));
		}

	}

}
