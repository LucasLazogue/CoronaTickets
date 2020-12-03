package Presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import logica.Fabrica;
import logica.IEspectaculoController;
import logica.ManejadorCategorias;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaDeCategoria extends JInternalFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaDeCategoria frame = new AltaDeCategoria();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AltaDeCategoria() {
		this.setVisible(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		//Fabrica, Controllers y Manejadores
		Fabrica fabrica = Fabrica.getInstancia();
		IEspectaculoController ICE = fabrica.getIEspectaculoController();
		ManejadorCategorias mC = ManejadorCategorias.getInstance();
		
		//Titulo
		JLabel lblAltaDeCategoria = new JLabel("Alta De Categoria");
		
		//Nombre Categoria
		JLabel lblNombreDeCategoria = new JLabel("Nombre De Categoria");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		//Confirmar
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreCategoria = textField.getText();
				if(mC.getCategoria(nombreCategoria)==null) {
					ICE.ingresarDatosCategoria(nombreCategoria);
					JOptionPane.showMessageDialog(null, "La categoria " + nombreCategoria + " fue ingresada con exito");
				}else {
					JOptionPane.showMessageDialog(null, "Ya existe una categoria con el nombre " + nombreCategoria);
				}
			}
		});
		
		//Cancelar
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAltaDeCategoria)
					.addContainerGap(309, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(170, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOk)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancelar))
						.addComponent(lblNombreDeCategoria)
						.addComponent(textField))
					.addGap(118))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAltaDeCategoria)
					.addGap(56)
					.addComponent(lblNombreDeCategoria)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnOk))
					.addContainerGap(114, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
