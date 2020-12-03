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
import logica.DataFuncion;
import logica.DataPaquete;
import logica.Fabrica;
import logica.IEspectaculoController;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaDeEspectaculo extends JInternalFrame {
	private IEspectaculoController ICont;
	private JComboBox<String> cboxPlataformas;
	private JComboBox<String> cboxEspectaculos;
	private JComboBox<String> cboxPaquetes;
	private JComboBox<String> cboxFunciones;
	private JTextArea txtEspectaculo;
	private JTextArea txtPaquete;
	private JTextArea txtFuncion;
	private JComboBox<String> cboxCategorias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaDeEspectaculo frame = new ConsultaDeEspectaculo();
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
	public ConsultaDeEspectaculo() {
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 500, 518);
		Fabrica f = Fabrica.getInstancia();
		ICont = f.getIEspectaculoController();
		
		JLabel lblSeleccionePlat = new JLabel("Seleccione una plataforma:");
		
		JLabel lblSeleccioneUnEspectaculo = new JLabel("Seleccione un espectaculo:");
		
		JLabel lblDatosDelEspectaculo = new JLabel("Datos del espectáculo seleccionado:");
		lblDatosDelEspectaculo.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblListaDePaquetes = new JLabel("Lista de paquetes que contienen al espectáculo seleccionado:");
		
		cboxPaquetes = new JComboBox<String>();
		cboxPaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cboxPaquetes.getSelectedItem() != null) {
					String paq = String.valueOf(cboxPaquetes.getSelectedItem());
					DataPaquete dtP = ICont.seleccionarPaquete(paq);
					String cont = "Nombre: " + dtP.getNombre() + "\n"
							+ "Válido desde " + dtP.getInicioVigencia() + " hasta " + dtP.getFinVigencia() + "\n"
							+ "Descuento: " + dtP.getDescuento() + "\n"
							+ "Fecha de alta: " + dtP.getFechaDeAlta() +"\n"; 
					txtPaquete.setText(cont);
				} else
					txtPaquete.setText("El espectaculo no pertenece a ningún paquete.");
			}
		});
		
		JLabel lblDatosDelPaquete = new JLabel("Datos del paquete seleccionado:");
		lblDatosDelPaquete.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		
		JLabel lblListaDeFunciones = new JLabel("Lista de funciones del espectáculo seleccionado:");
		
		cboxFunciones = new JComboBox<String>();
		cboxFunciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plat = String.valueOf(cboxPlataformas.getSelectedItem());
				String esp = String.valueOf(cboxEspectaculos.getSelectedItem());
				if (cboxFunciones.getSelectedItem() != null) {
					String func = String.valueOf(cboxFunciones.getSelectedItem());
					DataFuncion dtF;
					try {
						dtF = ICont.consultaFuncion(func, esp);
						String cont = "Nombre: " + dtF.getNombre() + "\n"
								+ "Fecha y hora de inicio: " + dtF.getFechaHoraInicio() + "\n"
								+ "Fecha de alta: " + dtF.getFechaAlta() + "\n";
						String[] artistas = dtF.getArtistas();
						if (artistas != null) {
							cont += "Artistas invitados: ";
							for (int i = 0; i < artistas.length - 1; i++) {
								cont += artistas[i] + ", ";
							}
							cont += artistas[artistas.length - 1] + ".";
						} else {
							cont += "No hay artistas invitados.";
						}
						txtFuncion.setText(cont);
					} catch (EspectaculoNoExisteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					txtFuncion.setText("El espectaculo no tiene funiciones.");
			}
		});
		
		JLabel lblDatosDeLa = new JLabel("Datos de la función seleccionada:");
		lblDatosDeLa.setFont(new Font("DejaVu Sans Condensed", Font.BOLD, 14));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		
		cboxPlataformas = new JComboBox<String>();
		cboxPlataformas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cboxEspectaculos.removeAllItems();
				String[] espectaculos = ICont.listarEspectaculos(String.valueOf(cboxPlataformas.getSelectedItem()));
				if (espectaculos == null) {
					JOptionPane.showMessageDialog(null, "La plataforma no tiene espectáculos");
				} else {
					for (int i = 0; i < espectaculos.length; i++)
						cboxEspectaculos.addItem(espectaculos[i]);
				}
			}
		});
		
		cboxEspectaculos = new JComboBox<String>();
		cboxEspectaculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String esp = String.valueOf(cboxEspectaculos.getSelectedItem());
				if (cboxEspectaculos.getSelectedItem() != null) {
					try {
						DataEspectaculo dtE = ICont.seleccionarEspectaculo(esp);
						String cont = "Nombre: " + dtE.getNombre() + "\n"
								+ "Descripción: " + dtE.getDescripcion() + "\n"
								+ "Duración: " + dtE.getDuracion() + "\n"
								+ "Cantidad de espectadores mínimos: " + dtE.getMinEspectadores() + "\n"
								+ "Cantidad de espectadores máximos: " + dtE.getMaxEspectadores() + "\n"
								+ "Costo: " + dtE.getCosto() + "\n"
								+ "URL: " + dtE.getUrl() + "\n"
								+ "Fecha de registro: " + dtE.getFechaRegistro() + "\n";
						if (dtE.getUrlImg() != null)
							cont += "URL de imagen: " + dtE.getUrlImg() + "\n";
						else cont += "No hay URL de imagen. \n";
						if (dtE.getUrlVideo() != null)
              cont += "URL de video: " + dtE.getUrlVideo() + "\n";
            else cont += "No hay URL de video. \n";
						cont += "Descripción del premio: " + dtE.getPremio().getDescripcion() + "\n"
						    + "Cantidad de premios a sortear por función: " + dtE.getPremio().getCantPremiosPorFuncion() + "\n";
						txtEspectaculo.setText(cont);
						String[] paquetes = dtE.getNombresPaquetes();
						String[] funciones = dtE.getNombresFunciones();
						String[] categorias = dtE.getNombresCategorias();
						//Cargar lista de paquetes
						cboxPaquetes.removeAllItems();
						if (dtE.getNombresPaquetes() == null) {
							txtPaquete.setText("El espectáculo no pertenece a ningún paquete.");
						} else {
							for (int i = 0; i < paquetes.length; i++)
								cboxPaquetes.addItem(paquetes[i]);
						}
						//Cargar lista de funciones
						cboxFunciones.removeAllItems();
						if (dtE.getNombresFunciones() == null) {
							txtFuncion.setText("El espectáculo no tiene funciones.");
						} else {
							for (int i = 0; i < funciones.length; i++)
								cboxFunciones.addItem(funciones[i]);
						}
						//Cargar lista de categorías
						cboxCategorias.removeAllItems();
						if (dtE.getNombresCategorias() == null) {
							txtFuncion.setText("El espectáculo no tiene categorías.");
						} else {
							for (int i = 0; i < categorias.length; i++)
								cboxCategorias.addItem(categorias[i]);
						}
					} catch (EspectaculoNoExisteException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage());
					}
				} else
					txtEspectaculo.setText("La plataforma no tiene espectáculos.");
			}
		});
		
		JLabel lblListaDeCategoras = new JLabel("Lista de categorías del espectáculo seleccionado:");
		
		cboxCategorias = new JComboBox<String>();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
						.addComponent(cboxFunciones, 0, 466, Short.MAX_VALUE)
						.addComponent(scrollPane_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
						.addComponent(lblDatosDelEspectaculo)
						.addComponent(lblListaDePaquetes)
						.addComponent(cboxPaquetes, 0, 466, Short.MAX_VALUE)
						.addComponent(lblDatosDelPaquete, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblListaDeFunciones, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDatosDeLa, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblSeleccioneUnEspectaculo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblSeleccionePlat, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(cboxEspectaculos, 0, 260, Short.MAX_VALUE)
								.addComponent(cboxPlataformas, 0, 260, Short.MAX_VALUE)))
						.addComponent(lblListaDeCategoras, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboxCategorias, 0, 466, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeleccionePlat)
						.addComponent(cboxPlataformas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeleccioneUnEspectaculo)
						.addComponent(cboxEspectaculos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDatosDelEspectaculo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblListaDePaquetes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxPaquetes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDatosDelPaquete, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addComponent(lblListaDeFunciones)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxFunciones, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDatosDeLa, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1_1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblListaDeCategoras)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cboxCategorias, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		txtEspectaculo = new JTextArea();
		txtEspectaculo.setEditable(false);
		scrollPane.setViewportView(txtEspectaculo);
		
		txtPaquete = new JTextArea();
		txtPaquete.setEditable(false);
		scrollPane_1.setViewportView(txtPaquete);
		
		txtFuncion = new JTextArea();
		txtFuncion.setEditable(false);
		scrollPane_1_1.setViewportView(txtFuncion);
		getContentPane().setLayout(groupLayout);
		this.setVisible(true);

		String[] plataformas = ICont.getPlataformas();
		if (plataformas == null) {
			JOptionPane.showMessageDialog(null, "No hay plataformas en el sistema, el caso de uso no se puede llevar a cabo");
			doDefaultCloseAction();
		}else {
			for (int i = 0; i < plataformas.length; i++)
				cboxPlataformas.addItem(plataformas[i]);
		}
	}

}
