package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
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

public class EliminarVehiculo extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JComboBox<String> listaCoche;
	private JButton btnConfirmar;

	private List<Coche> coches;
	private MenuVehiculos menu;
	private DAO dao;

	public EliminarVehiculo(MenuVehiculos menu, boolean b, DAO dao) {
		super(menu);
		this.setModal(b);

		this.menu = menu;
		this.dao = dao;

		setBounds(100, 100, 750, 577);
		setTitle("Eliminar coche");
		setIconImage(Toolkit.getDefaultToolkit().getImage(EliminarVehiculo.class.getResource("/utilidades/coche.png")));
		setResizable(false);
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
		
		JLabel lblNewLabel = new JLabel("Que coche quiere eliminar?");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(205, 98, 324, 39);
		contentPanel.add(lblNewLabel);

		coches = dao.listarCoches();

		listaCoche = new JComboBox<String>();
		listaCoche.setBackground(SystemColor.controlHighlight);
		listaCoche.setBounds(102, 235, 529, 39);
		contentPanel.add(listaCoche);
		for (Coche coche : coches) {
			listaCoche.addItem(coche.getMatricula());
		}
		listaCoche.setSelectedIndex(-1);

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(SystemColor.controlHighlight);
		btnConfirmar.setFont(new Font("Serif", Font.PLAIN, 20));
		btnConfirmar.setBounds(256, 447, 222, 66);
		contentPanel.add(btnConfirmar);
		btnConfirmar.addActionListener(this);
	}

	protected void volver() {
		this.dispose();
		menu.setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnConfirmar)) {
			eliminar();
		}

	}

	private void eliminar() {
		List<Coche> coches = dao.listarCocheConPropietario();
		Coche coc = dao.mostrarCoche(listaCoche.getItemAt(listaCoche.getSelectedIndex()));

		boolean tienePropietario = false;
		int opc;

		for (Coche coche : coches) {
			if (coche.getMatricula().equalsIgnoreCase(coc.getMatricula())) {
				Usuario usu = dao.mostrarPropietario(coc.getMatricula());
				opc = JOptionPane.showConfirmDialog(this,
						"Este coche le pertenece a " + usu.getUsuario() + " seguro que quieres eliminarlo?");
				tienePropietario = true;
				if (opc == 0) {
					dao.eliminarCoche(coc.getMatricula());
					JOptionPane.showMessageDialog(this, "El coche a sido eliminado con exito", "", 3);
				}
			}
		}

		if (!tienePropietario) {
			opc = JOptionPane.showConfirmDialog(this,
					"Seguro que quieres eliminar el " + coc.getMarca() + " " + coc.getModelo());
			if (opc == 0) {
				dao.eliminarCoche(coc.getMatricula());
				JOptionPane.showMessageDialog(this, "El coche a sido eliminado con exito", "", 3);
			}
		}
		listaCoche.setSelectedIndex(-1);
	}
}
