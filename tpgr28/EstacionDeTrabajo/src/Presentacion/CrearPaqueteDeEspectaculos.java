package Presentacion;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import logica.Fabrica;
import logica.IEspectaculoController;

import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import exepciones.PaqueteRepetidoException;
import exepciones.FinVigAntesIniVigException;

public class CrearPaqueteDeEspectaculos extends JInternalFrame {
	
	private Fabrica f;
	private IEspectaculoController ICE;
	
	private JTextField nombre;
	private JTextField descripcion;
	private JTextField descuento;
	private JTextField urlImagen;


	public CrearPaqueteDeEspectaculos() {
		setBounds(100, 100, 500, 308);
       	setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("CrearPaqueteDeEspectaculos");
        setVisible(true);
        
        f = Fabrica.getInstancia();
        ICE = f.getIEspectaculoController();
        
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNombre = new JLabel("Nombre:");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 0;
		panel_1.add(lblNombre, gbc_lblNombre);
		
		nombre = new JTextField();
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.insets = new Insets(0, 0, 5, 0);
		gbc_nombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 0;
		panel_1.add(nombre, gbc_nombre);
		nombre.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 1;
		panel_1.add(lblDescripcion, gbc_lblDescripcion);
		
		descripcion = new JTextField();
		GridBagConstraints gbc_descripcion = new GridBagConstraints();
		gbc_descripcion.insets = new Insets(0, 0, 5, 0);
		gbc_descripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_descripcion.gridx = 1;
		gbc_descripcion.gridy = 1;
		panel_1.add(descripcion, gbc_descripcion);
		descripcion.setColumns(10);
		
		JLabel lblDescuento = new JLabel("Descuento:");
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.anchor = GridBagConstraints.WEST;
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.gridx = 0;
		gbc_lblDescuento.gridy = 2;
		panel_1.add(lblDescuento, gbc_lblDescuento);
		
		descuento = new JTextField();
		GridBagConstraints gbc_descuento = new GridBagConstraints();
		gbc_descuento.insets = new Insets(0, 0, 5, 0);
		gbc_descuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_descuento.gridx = 1;
		gbc_descuento.gridy = 2;
		panel_1.add(descuento, gbc_descuento);
		descuento.setColumns(10);
		
		JLabel lblUrlImagen = new JLabel("Url imagen");
		GridBagConstraints gbc_lblUrlImagen = new GridBagConstraints();
		gbc_lblUrlImagen.anchor = GridBagConstraints.WEST;
		gbc_lblUrlImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblUrlImagen.gridx = 0;
		gbc_lblUrlImagen.gridy = 3;
		panel_1.add(lblUrlImagen, gbc_lblUrlImagen);
		
		urlImagen = new JTextField();
		GridBagConstraints gbc_urlImagen = new GridBagConstraints();
		gbc_urlImagen.insets = new Insets(0, 0, 5, 0);
		gbc_urlImagen.fill = GridBagConstraints.HORIZONTAL;
		gbc_urlImagen.gridx = 1;
		gbc_urlImagen.gridy = 3;
		panel_1.add(urlImagen, gbc_urlImagen);
		urlImagen.setColumns(10);
		
		JLabel lblPerdvig = new JLabel("Período de vigencia:");
		GridBagConstraints gbc_lblPerdvig = new GridBagConstraints();
		gbc_lblPerdvig.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblPerdvig.insets = new Insets(0, 0, 5, 5);
		gbc_lblPerdvig.gridx = 0;
		gbc_lblPerdvig.gridy = 4;
		panel_1.add(lblPerdvig, gbc_lblPerdvig);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		panel_1.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblFi = new JLabel("Desde:");
		panel.add(lblFi);
		
		JSpinner spinnerIV = new JSpinner();
		SpinnerDateModel fechaModel1 = new SpinnerDateModel();
		spinnerIV.setModel(fechaModel1);
		spinnerIV.setEditor(new JSpinner.DateEditor(spinnerIV, "dd/MM/yyyy"));
		panel.add(spinnerIV);
		
		JLabel lblFv = new JLabel("Hasta:");
		panel.add(lblFv);
		
		JSpinner spinnerFV = new JSpinner();
		SpinnerDateModel fechaModel2 = new SpinnerDateModel();
		spinnerFV.setModel(fechaModel2);
		spinnerFV.setEditor(new JSpinner.DateEditor(spinnerFV, "dd/MM/yyyy"));
		panel.add(spinnerFV);
		
		
		JLabel lblFechaDeAta = new JLabel("Fecha de alta:");
		GridBagConstraints gbc_lblFechaDeAta = new GridBagConstraints();
		gbc_lblFechaDeAta.anchor = GridBagConstraints.NORTH;
		gbc_lblFechaDeAta.insets = new Insets(0, 0, 0, 5);
		gbc_lblFechaDeAta.gridx = 0;
		gbc_lblFechaDeAta.gridy = 5;
		panel_1.add(lblFechaDeAta, gbc_lblFechaDeAta);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 5;
		panel_1.add(panel_2, gbc_panel_2);
		
		JSpinner spinnerFechaA = new JSpinner();
		SpinnerDateModel fechaModel = new SpinnerDateModel();
		spinnerFechaA.setModel(fechaModel);
		spinnerFechaA.setEditor(new JSpinner.DateEditor(spinnerFechaA, "dd/MM/yyyy"));
		panel_2.add(spinnerFechaA);
		
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaAlta = (Date) spinnerFechaA.getValue();
				Date fechaIV = (Date) spinnerIV.getValue();
				Date fechaFV = (Date) spinnerFV.getValue();
				try {
					if (urlImagen.getText().length() > 0)
						ICE.crearPaquete(nombre.getText(), descripcion.getText(), fechaIV, fechaFV, Integer.parseInt(descuento.getText()), fechaAlta, urlImagen.getText());
					else
						ICE.crearPaquete(nombre.getText(), descripcion.getText(), fechaIV, fechaFV, Integer.parseInt(descuento.getText()), fechaAlta, null);
					setVisible(false);
				} catch (NumberFormatException | PaqueteRepetidoException | FinVigAntesIniVigException e1) {					
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
		gl_buttonPane.setHorizontalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addContainerGap(322, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancelar)
					.addContainerGap())
		);
		gl_buttonPane.setVerticalGroup(
			gl_buttonPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_buttonPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnOk)))
		);
		buttonPane.setLayout(gl_buttonPane);

	}
}
