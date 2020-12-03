package Presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import exepciones.EspectaculoNoExisteException;
import logica.DataEspectaculo;
import logica.DataPaquete;
import logica.Fabrica;
import logica.IEspectaculoController;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class consultaDePaqueteDeEspectaculos extends JInternalFrame {
	private JTextArea txtEspectaculo;
	private JComboBox<String> cboxEspectaculos;
	private JTextArea txtPaquete;
	private JComboBox<String> cboxPaquetes;
	IEspectaculoController ICont;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					consultaDePaqueteDeEspectaculos frame = new consultaDePaqueteDeEspectaculos();
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
	public consultaDePaqueteDeEspectaculos() {
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		Fabrica f = Fabrica.getInstancia();
		ICont = f.getIEspectaculoController();
		
		JLabel lblSeleccionesUnPaquete = new JLabel("Seleccione un paquete:");
		
		cboxPaquetes = new JComboBox<String>();
		cboxPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboxPaquetes.getSelectedItem() != null) {
					String paq = String.valueOf(cboxPaquetes.getSelectedItem());
					DataPaquete dtP = ICont.seleccionarPaquete(paq);
					String cont = "Nombre: " + dtP.getNombre() + "\n"
							+ "Válido desde " + dtP.getInicioVigencia() + " hasta " + dtP.getFinVigencia() + "\n"
							+ "Descuento: " + dtP.getDescuento() + "\n"
							+ "Fecha de alta: " + dtP.getFechaDeAlta() +"\n"
							+ "Categorías: ";
					String[] gen = dtP.getGeneros();
					if (gen != null)
						for (int i = 0; i< gen.length; i++)
							cont = cont + gen[i] + " ";
					cont = cont + "\n";
					if (dtP.getUrlImagen() != null)
						cont = cont + "Url de Imagen: " + dtP.getUrlImagen() + "\n"; 
					txtPaquete.setText(cont);
					String[] espectaculos = dtP.getEspectaculos();
					//Cargar lista de esectáculos
					cboxEspectaculos.removeAllItems();
					if (espectaculos == null) {
						txtEspectaculo.setText("El paquete no tiene espectáculos.");
					} else {
						for (int i = 0; i < espectaculos.length; i++)
							cboxEspectaculos.addItem(espectaculos[i]);
					}
				} else
					txtPaquete.setText("No hay un paquete seleciconado.");
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblSeleccioneUnEspectculo = new JLabel("Seleccione un espectáculo:");
		
		cboxEspectaculos = new JComboBox<String>();
		cboxEspectaculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String paq = String.valueOf(cboxPaquetes.getSelectedItem());
				String esp = String.valueOf(cboxEspectaculos.getSelectedItem());
				if (cboxEspectaculos.getSelectedItem() != null) {
					DataEspectaculo dtE = ICont.seleccionarEspectaculoDePaquete(paq, esp);
					String cont = "Nombre: " + dtE.getNombre() + "\n"
							+ "Descripción: " + dtE.getDescripcion() + "\n"
							+ "Duración: " + dtE.getDuracion() + "\n"
							+ "Cantidad de espectadores mínimos: " + dtE.getMinEspectadores() + "\n"
							+ "Cantidad de espectadores máximos: " + dtE.getMaxEspectadores() + "\n"
							+ "Costo: " + dtE.getCosto() + "\n"
							+ "URL: " + dtE.getUrl() + "\n"
							+ "Fecha de registro: " + dtE.getFechaRegistro();
					txtEspectaculo.setText(cont);
				}
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblSeleccionesUnPaquete)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboxPaquetes, 0, 237, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblSeleccioneUnEspectculo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboxEspectaculos, 0, 211, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeleccionesUnPaquete)
						.addComponent(cboxPaquetes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeleccioneUnEspectculo)
						.addComponent(cboxEspectaculos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txtEspectaculo = new JTextArea();
		txtEspectaculo.setEditable(false);
		scrollPane_1.setViewportView(txtEspectaculo);
		
		txtPaquete = new JTextArea();
		txtPaquete.setEditable(false);
		scrollPane.setViewportView(txtPaquete);
		getContentPane().setLayout(groupLayout);
		this.setVisible(true);

		String[] paquetes = ICont.listarPaquetes();
		if (paquetes == null) {
			JOptionPane.showMessageDialog(null, "No hay paquetes en el sistema, el caso de uso no se puede llevar a cabo");
			doDefaultCloseAction();
		} else {
			for (int i = 0; i < paquetes.length; i++)
				cboxPaquetes.addItem(paquetes[i]);
		}

	}

}
