package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import clases.Coche;
import modelo.DAO;
import java.awt.Toolkit;

public class ListarVehiculo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JButton btnConfirmar;
	private JComboBox<String> listaCoches;
	private JTextArea pantalla;

	private MenuVehiculos menu;
	private DAO dao;
	private List<Coche> coches;

	public ListarVehiculo(MenuVehiculos menu, boolean b, DAO dao) {
		super(menu);
		setTitle("Listar Vehiculos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListarVehiculo.class.getResource("/utilidades/coche.png")));
		setResizable(false);
		this.setModal(b);

		this.menu = menu;
		this.dao = dao;
		coches = dao.listarCoches();

		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        volver();
		    }
		});
		
		setBounds(100, 100, 750, 577);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Serif", Font.PLAIN, 18));
		btnConfirmar.setBounds(257, 455, 220, 60);
		contentPanel.add(btnConfirmar);
		btnConfirmar.addActionListener(this);

		listaCoches = new JComboBox<String>();
		listaCoches.setBackground(SystemColor.controlHighlight);
		listaCoches.setFont(new Font("Serif", Font.PLAIN, 18));
		listaCoches.setBounds(98, 110, 543, 46);
		contentPanel.add(listaCoches);
		for (Coche coche : coches) {
			listaCoches.addItem(coche.getMatricula());
		}
		listaCoches.setSelectedIndex(-1);

		JLabel lblNewLabel = new JLabel("La informacion de que vehiculo quieres mostrar?");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		lblNewLabel.setBounds(194, 52, 352, 24);
		contentPanel.add(lblNewLabel);

		pantalla = new JTextArea();
		pantalla.setFont(new Font("Monospaced", Font.PLAIN, 20));
		pantalla.setEditable(false);
		pantalla.setBounds(98, 201, 543, 208);
		contentPanel.add(pantalla);
	}

	protected void volver() {
		this.dispose();
		menu.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnConfirmar)) {
			listar();
		}
	}

	private void listar() {
		Coche coche = dao.mostrarCoche(listaCoches.getItemAt(listaCoches.getSelectedIndex()));

		String mensaje = "Matricula:\t" + coche.getMatricula() + "\n";
		mensaje += "Marca:\t\t" + coche.getMarca() + "\n";
		mensaje += "Modelo:\t\t" + coche.getModelo() + "\n";
		mensaje += "Edad:\t\t" + coche.getEdad() + "\n";
		mensaje += "Precio:\t\t" + coche.getPrecio() + "\n";
		
		if(coche.getDni_propietario() == null) {
			mensaje += "No tiene propietario";
		}else {
			mensaje += "Propietario:\t" + dao.mostrarUsuarioPorDni(coche.getDni_propietario()).getUsuario()+ "\n";	
		}
		
		pantalla.setText(mensaje);
	}
}
