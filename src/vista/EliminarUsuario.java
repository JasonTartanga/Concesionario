package vista;

import java.awt.BorderLayout;
import java.awt.Font;
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

import clases.Usuario;
import modelo.DAO;
import java.awt.SystemColor;

public class EliminarUsuario extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private JComboBox<String> listaUsuarios;
	private JButton btnEliminar;
	private List<Usuario> usuarios;

	private MenuUsuario menu;
	private DAO dao;

	public EliminarUsuario(MenuUsuario menu, boolean b, DAO dao) {
		super(menu);
		this.setModal(b);
		
		this.menu = menu;
		this.dao = dao;
		
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
		
		JLabel lblNewLabel = new JLabel("Que usuario deseas elimiar?");
		lblNewLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		lblNewLabel.setBounds(202, 91, 329, 39);
		contentPanel.add(lblNewLabel);

		usuarios = dao.listarUsuarios();
	
		listaUsuarios = new JComboBox<String>();
		listaUsuarios.setBackground(SystemColor.controlHighlight);
		listaUsuarios.setFont(new Font("Serif", Font.PLAIN, 30));
		listaUsuarios.setBounds(179, 221, 375, 47);
		contentPanel.add(listaUsuarios);
		for (Usuario usuario : usuarios) {
			listaUsuarios.addItem(usuario.getDni());
		}
		listaUsuarios.setSelectedIndex(-1);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(SystemColor.controlHighlight);
		btnEliminar.setFont(new Font("Serif", Font.PLAIN, 30));
		btnEliminar.setBounds(254, 421, 226, 77);
		contentPanel.add(btnEliminar);
		btnEliminar.addActionListener(this);

	}

	protected void volver() {
		this.dispose();
		menu.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnEliminar)) {
			eliminar();
		}
		
	}

	private void eliminar() {
		Usuario usu = dao.mostrarUsuario(listaUsuarios.getItemAt(listaUsuarios.getSelectedIndex()));
		
		int opc = JOptionPane.showConfirmDialog(this, "Seguro que quieres eliminar a " + usu.getUsuario());
		
		if(opc == 0) {
			dao.eliminarUsuario(usu.getDni());
			JOptionPane.showMessageDialog(this, "Usuario eliminado correctamente", "", 3);
			volver();
		}else {
			listaUsuarios.setSelectedIndex(-1);
		}
		
		
		
	}

}
