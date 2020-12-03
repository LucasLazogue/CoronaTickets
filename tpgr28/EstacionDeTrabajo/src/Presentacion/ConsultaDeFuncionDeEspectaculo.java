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
import logica.DataFuncion;
import logica.Fabrica;
import logica.IEspectaculoController;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ConsultaDeFuncionDeEspectaculo extends JInternalFrame {
	private static IEspectaculoController ICE;
	private static JComboBox<String> PlataformaCB;
	private static JComboBox<String> EspectaculoCB;
	private static JComboBox<String> FuncionCB;
	private Boolean existeEspectaculo;
	private Boolean existeFuncion;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaDeFuncionDeEspectaculo frame = new ConsultaDeFuncionDeEspectaculo();
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
	public ConsultaDeFuncionDeEspectaculo() {
		setIconifiable(true);
		this.setVisible(true);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		//Fabrica, Controllers y Manejadores
		Fabrica fabrica = Fabrica.getInstancia();
		ICE = fabrica.getIEspectaculoController();
		
		//Titulo
		JLabel lblConsultaDeFuncion = new JLabel("Consulta de Funcion de Espectaculo");
		
		//Plataforma
		JLabel lblPlataforma = new JLabel("Plataforma");
		
		PlataformaCB = new JComboBox<String>();
		/*PlataformaCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});*/
		String[] nombresPlataformas = ICE.getPlataformas();
		if(nombresPlataformas == null) {
			JOptionPane.showMessageDialog(null, "No existen plataformas");
			doDefaultCloseAction();
		}else {
			for(int i=0; i<nombresPlataformas.length; i++) {
				PlataformaCB.addItem(nombresPlataformas[i]);
			}
		}
		
		//Espectaculo
		JLabel lblEspectaculo = new JLabel("Espectaculo");
		
		EspectaculoCB = new JComboBox<String>();
		/*EspectaculoCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});*/
		/*if(nombresPlataformas != null) {
			existeEspectaculo = cargarEspectaculos();
		}*/
		
		//Evento actualizr Espectaculos
		PlataformaCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(PlataformaCB.getSelectedItem()!=null) {
					existeEspectaculo = cargarEspectaculos();
				}
			}
		});
		
		//Funcion
		JLabel lblFuncion = new JLabel("Funcion");
		
		FuncionCB = new JComboBox<String>();
		/*FuncionCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});*///existeEspectaculo
		/*if(nombresPlataformas!=null && ICE.listarEspectaculos((String) PlataformaCB.getSelectedItem())!=null) {
			existeFuncion = cargarFunciones();
		}*/
		
		//Evento actualizar Funciones
		EspectaculoCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(EspectaculoCB.getSelectedItem()!=null) {
					existeFuncion = cargarFunciones();
				}
			}
		});
		
		//Mostrar datos Funcion
		FuncionCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(EspectaculoCB.getSelectedItem()!=null && FuncionCB.getSelectedItem()!=null) {
					String nombreFuncion = (String) FuncionCB.getSelectedItem();
					String nombreEspectaculo = (String) EspectaculoCB.getSelectedItem();
					DataFuncion dataF;
					try {
						dataF = ICE.consultaFuncion(nombreFuncion, nombreEspectaculo);
						String info = "Nombre de Funcion: " + dataF.getNombre() + '\n' +
								"Nombre del Espectaculo: " + dataF.getEspectaculo() + '\n' +
								"Fecha de Inicio: " + dataF.getFechaHoraInicio().toString() + '\n' +
								"Fecha de Alta: " + dataF.getFechaAlta().toString() + '\n' +
								"Artistas Invitados: ";
						if(dataF.getArtistas()!=null) {
							for(int i=0; i<dataF.getArtistas().length-1; i++) {
								info = info + dataF.getArtistas()[i] + ", ";
							}
							info = info + dataF.getArtistas()[dataF.getArtistas().length-1];
						}
						textArea.setText(info);
					} catch (EspectaculoNoExisteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		//GroupLayout
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblConsultaDeFuncion)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPlataforma)
								.addComponent(lblEspectaculo)
								.addComponent(lblFuncion)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(FuncionCB, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(EspectaculoCB, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(PlataformaCB, Alignment.LEADING, 0, 118, Short.MAX_VALUE)))
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblConsultaDeFuncion)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPlataforma)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(PlataformaCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEspectaculo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(EspectaculoCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblFuncion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(FuncionCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		//Texto
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		getContentPane().setLayout(groupLayout);

	}
	
	private static Boolean cargarEspectaculos() {
		String nombrePlataforma = String.valueOf(PlataformaCB.getSelectedItem());
		String[] nombresEspectaculos = ICE.listarEspectaculos(nombrePlataforma);
		EspectaculoCB.removeAllItems();
		if(nombresEspectaculos == null) {
			JOptionPane.showMessageDialog(null, "No existen espectaculos para " + nombrePlataforma);
			String[] conjuntoVacio = new String[1];
			conjuntoVacio[0] = "Vacio";
			EspectaculoCB.addItem(conjuntoVacio[0]);
			return false;
		}else {
			for(int i=0; i<nombresEspectaculos.length; i++) {
				EspectaculoCB.addItem(nombresEspectaculos[i]);
			}
			return true;
		}
	}
	
	private static Boolean cargarFunciones() {
		String nombrePlataforma = String.valueOf(PlataformaCB.getSelectedItem());
		String nombreEspectaculo = String.valueOf(EspectaculoCB.getSelectedItem());
		String[] nombresFunciones = ICE.listarFuncionesEspectaculo(nombrePlataforma, nombreEspectaculo);
		FuncionCB.removeAllItems();
		if(nombresFunciones == null) {
			JOptionPane.showMessageDialog(null, "No existen funciones para " + nombreEspectaculo);
			String[] conjuntoVacio = new String[1];
			conjuntoVacio[0] = "Vacio";
			FuncionCB.addItem(conjuntoVacio[0]);
			return false;
		}else {
			for(int i=0; i<nombresFunciones.length; i++) {
				FuncionCB.addItem(nombresFunciones[i]);
			}
			return true;
		}
	}
}
