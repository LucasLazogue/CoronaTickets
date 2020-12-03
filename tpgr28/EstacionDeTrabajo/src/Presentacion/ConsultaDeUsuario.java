package Presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import exepciones.EspectaculoNoExisteException;
import exepciones.FuncionNoExisteException;
import exepciones.UsuarioNoExisteException;
import logica.DataArtista;
import logica.DataEspectaculo;
import logica.DataEspectador;
import logica.DataFuncion;
import logica.DataUsuario;
import logica.Fabrica;
import logica.IUsuarioController;
import logica.ManejadorUsuarios;

import javax.swing.JTextArea;

public class ConsultaDeUsuario extends JInternalFrame {
	private static DataUsuario dtu;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaDeUsuario frame = new ConsultaDeUsuario();
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
	public ConsultaDeUsuario() {
		setClosable(true);
		setVisible(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		Fabrica f = Fabrica.getInstancia();
		IUsuarioController IUC = f.getIUsuarioController();
		ManejadorUsuarios mu = ManejadorUsuarios.getManejadorUsuarios();
		
		setBounds(100, 100, 396, 464);
		
		JLabel lblSeleccionarUsuario = new JLabel("Seleccionar usuario");
		
		JLabel lblNewLabel = new JLabel("Datos de usuario seleccionado");
		lblNewLabel.setVisible(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		
		
		JLabel lblListaDeUsuario = new JLabel("Lista de usuario");
		lblListaDeUsuario.setVisible(false);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setVisible(false);
		
		JLabel lblElelementoSeleccionadoDe = new JLabel("Elelemento seleccionado de la lista");
		lblElelementoSeleccionadoDe.setVisible(false);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVisible(false);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setVisible(false);
		scrollPane_1.setViewportView(textArea_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setVisible(false);
		scrollPane.setViewportView(textArea);
		
		
		JComboBox comboBox = new JComboBox();
		String[] usu;
		try {
			usu = IUC.listarUsuarios();
			for (int i=0; i<usu.length; i++) {
				comboBox.addItem(usu[i]);
			}
		} catch (UsuarioNoExisteException e2) {
			JOptionPane.showMessageDialog(null, "No hay usuarios en el sistema, el caso de uso no se puede llevar a cabo");
			doDefaultCloseAction();
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem() != null) {
					comboBox_1.removeAllItems();
					lblNewLabel.setVisible(true);
					scrollPane.setVisible(true);
					textArea.setVisible(true);
					String usuario = String.valueOf(comboBox.getSelectedItem());
					try {
						DataUsuario usu = IUC.mostrarDatosUsuario(usuario);
						dtu = usu;
						if(usu instanceof DataArtista) {
							DataArtista da = (DataArtista) usu;
							String cont = "Nickname: " + da.getNickname() + "\n"
								+ "Email: " + da.getEmail() + "\n"
								+ "Nombre: " + da.getNombre() + "\n"
								+ "Apellido: " + da.getApellido() +"\n"
								+ "Descripcion: " + da.getDescripcion() +"\n"
								+ "Biografia: " + da.getBiografia() +"\n"
								+ "Web: " + da.getWeb() +"\n";
							textArea.setText(cont);	
							String[] espec = da.getEspectaculos();
							if (espec != null) {
								lblListaDeUsuario.setText("Espectaculos creados");
								for(int i=0; i<espec.length; i++) {
									comboBox_1.addItem(espec[i]);
								}
								lblListaDeUsuario.setVisible(true);
								comboBox_1.setVisible(true);
							}
							else {
								lblListaDeUsuario.setVisible(false);
								comboBox_1.setVisible(false);
								lblElelementoSeleccionadoDe.setVisible(false);
								scrollPane_1.setVisible(false);
								textArea_1.setVisible(false);
							}
						}
						else {
							DataEspectador de = (DataEspectador) usu;
							String cont = "Nickname: " + de.getNickname() + "\n"
								+ "Email: " + de.getEmail() + "\n"
								+ "Nombre: " + de.getNombre() + "\n"
								+ "Apellido: " + de.getApellido() +"\n";
							textArea.setText(cont);	
							
							
							String[] fun = de.getFunciones();
							if (fun != null) {
								lblListaDeUsuario.setText("Funciones registradas");
								for(int i=0; i < fun.length; i++) {
									comboBox_1.addItem(fun[i]);
								}
								lblListaDeUsuario.setVisible(true);
								comboBox_1.setVisible(true);
							}
							else {
								lblListaDeUsuario.setVisible(false);
								comboBox_1.setVisible(false);
								lblElelementoSeleccionadoDe.setVisible(false);
								scrollPane_1.setVisible(false);
								textArea_1.setVisible(false);
							}

						}
					} catch (UsuarioNoExisteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedItem() != null) {
					lblElelementoSeleccionadoDe.setVisible(true);
					scrollPane_1.setVisible(true);
					textArea_1.setVisible(true);
					if(dtu instanceof DataArtista) {
						try {
							lblElelementoSeleccionadoDe.setText("Espectaculo seleccionado");
							DataEspectaculo de = IUC.seleccionarEspectaculo(dtu.getNickname(),String.valueOf(comboBox_1.getSelectedItem()));
							String cont = "Nombre: " + de.getNombre() + "\n"
									+ "Descripción: " + de.getDescripcion() + "\n"
									+ "Duración: " + de.getDuracion() + "\n"
									+ "Cantidad de espectadores mínimos: " + de.getMinEspectadores() + "\n"
									+ "Cantidad de espectadores máximos: " + de.getMaxEspectadores() + "\n"
									+ "Costo: " + de.getCosto() + "\n"
									+ "URL: " + de.getUrl() + "\n"
									+ "Fecha de registro: " + de.getFechaRegistro() + "\n"
									+ "Categorías: ";
									String[] cat = de.getNombresCategorias();
									if (cat != null)
										for (int i = 0; i< cat.length; i++)
											cont = cont + cat[i] + ", ";
									cont = cont + "\n";
							textArea_1.setText(cont);
						
						} catch (EspectaculoNoExisteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						try {
							lblElelementoSeleccionadoDe.setText("Funcion seleccionada");
							DataFuncion df = IUC.seleccionarFuncion(dtu.getNickname(), String.valueOf(comboBox_1.getSelectedItem()));
							String cont = "Nombre: " + df.getNombre() + "\n"
									+ "Fecha y hora de inicio: " + df.getFechaHoraInicio() + "\n"
									+ "Nombre del espectáculo asociado : " + df.getEspectaculo() + "\n"
									+ "Fecha y hora de alta: " + df.getFechaAlta() + "\n";
							textArea_1.setText(cont);
						} catch (FuncionNoExisteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblSeleccionarUsuario)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, 0, 204, Short.MAX_VALUE))
						.addComponent(lblNewLabel, Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblListaDeUsuario, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblElelementoSeleccionadoDe, Alignment.LEADING))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSeleccionarUsuario)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblListaDeUsuario)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblElelementoSeleccionadoDe)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(101, Short.MAX_VALUE))
		);
		

		getContentPane().setLayout(groupLayout);
		
	}
}
