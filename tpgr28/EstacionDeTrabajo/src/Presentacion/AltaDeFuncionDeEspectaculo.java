package Presentacion;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import exepciones.EspectaculoNoExisteException;
import exepciones.FuncionRepetidaException;

import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import logica.Artista;
import logica.Fabrica;
import logica.IEspectaculoController;
import logica.ManejadorUsuarios;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AltaDeFuncionDeEspectaculo extends JInternalFrame {
	private JTextField textField;
	private Boolean existeEspectaculo;
	private static JComboBox<String> PlataformaCB;
	private static JComboBox<String> EspectaculoCB;
	private static IEspectaculoController ICE;
	private JTextField urlimg;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaDeFuncionDeEspectaculo frame = new AltaDeFuncionDeEspectaculo();
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
	public AltaDeFuncionDeEspectaculo() {
		setIconifiable(true);
		this.setVisible(true);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = (int) screenSize.getWidth();
		int altura = (int) screenSize.getHeight();
		setBounds(altura/4, ancho/4, ancho/2, altura/2);
		
		//Fabrica, Controllers y Manejadores
		Fabrica fabrica = Fabrica.getInstancia();
		ICE = fabrica.getIEspectaculoController();
		//IUsuarioController ICU = fabrica.getIUsuarioController();
		ManejadorUsuarios mU = ManejadorUsuarios.getManejadorUsuarios();
		
		//Plataformas
		String[] nombresPlataformas = ICE.getPlataformas();
		PlataformaCB = new JComboBox<String>();
		/*PlataformaCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				existeEspectaculo = cargarEspectaculos();
			}
		});*/
		if(nombresPlataformas == null) {
			JOptionPane.showMessageDialog(null, "No existen plataformas");
			doDefaultCloseAction();
		}else {
			for(int i=0; i<nombresPlataformas.length; i++) {
				PlataformaCB.addItem(nombresPlataformas[i]);
			}
		}
		
		JLabel lblPlataforma = new JLabel("Plataforma");
		
		//Espectaculos
		//String nombrePlataforma = (String) PlataformaCB.getSelectedItem();
		//String[] nombresEspectaculos = ICE.listarEspectaculos(nombrePlataforma);
		EspectaculoCB = new JComboBox<String>();
		if(nombresPlataformas != null) {
			existeEspectaculo = cargarEspectaculos();
		}
		
		JLabel lblEspectaculo = new JLabel("Espectaculo");
		
		//Evento actualizar espectaculos
		PlataformaCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				existeEspectaculo = cargarEspectaculos();
			}
		});

		//Titulo
		JLabel lblAltaDeFuncion = new JLabel("Alta de Funcion de Espectaculo");
		
		//Artistas
		JPanel contCheckBoxArt = new JPanel();
		contCheckBoxArt.setLayout(new BoxLayout(contCheckBoxArt, BoxLayout.Y_AXIS));
			//Treaer a los artistas y obtener nicks
		Artista[] artistas = mU.getArtistas();
		if(artistas!=null) {
			String[] nickArtistas = new String[artistas.length];
			for(int i=0; i<artistas.length; i++) {
				nickArtistas[i] = artistas[i].getNickname();
			}
				//Agregar nicks a CheckBox
			for(int i=0; i<nickArtistas.length; i++) {
				JCheckBox art = new JCheckBox(nickArtistas[i]);
				contCheckBoxArt.add(art);
			}
		}
			//Crear ScrollPane
		JScrollPane scrollPane = new JScrollPane(contCheckBoxArt,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JLabel lblArtistas = new JLabel("Artistas");
		
		//Funcion
		JLabel lblNombreFuncion = new JLabel("Nombre Funcion:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		//Fecha y Hora de Inicio
		JLabel lblFechaYHora = new JLabel("Fecha y Hora de Inicio:");
		
		SpinnerModel spinnerModel = new SpinnerDateModel();
		JSpinner spinner = new JSpinner(spinnerModel);
		
		//Fecha de Alta
		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta:");
		
		SpinnerModel spinnerModelAlta = new SpinnerDateModel();
		JSpinner spinnerAlta = new JSpinner(spinnerModelAlta);
		
		//Url de la Imagen
		JLabel lblUrlImagen = new JLabel("Url Imagen:");
		
		urlimg = new JTextField();
		urlimg.setColumns(10);
		
		
		//Boton Confirmar
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombreFuncion = textField.getText();
				List<String> nickArtInv = new LinkedList<String>();
				if(artistas!=null) {
					Component[] checkboxes = contCheckBoxArt.getComponents();
					//contar cantidad de seleccionados
					int cant = 0;
					for(int i=0; i<checkboxes.length; i++) {
						JCheckBox chk = (JCheckBox) checkboxes[i];
						if(chk.isSelected()) {
							cant++;
						}
					}
					//separar seleccionadas
					JCheckBox[] selectedCheckboxes = new JCheckBox[cant];
					int j=0;
					for(int i=0; i<checkboxes.length; i++) {
						JCheckBox chk = (JCheckBox) checkboxes[i];
						if(chk.isSelected()) {
							selectedCheckboxes[j] = chk;
							j++;
						}
					}
					//obtener nicks
					for(int i=0; i<cant; i++) {
						nickArtInv.add(selectedCheckboxes[i].getText());
					}
				}else {
					nickArtInv = null;
				}
				Date horaInicio = (Date) spinner.getValue();
				Date fechaAlta = (Date) spinnerAlta.getValue();
				try {
					if(existeEspectaculo) {
						String nombreEspectaculo = (String) EspectaculoCB.getSelectedItem();
						ICE.ingresarDatosFuncion(nombreFuncion, horaInicio, nickArtInv, fechaAlta, nombreEspectaculo, urlimg.getText());
					}
					doDefaultCloseAction();
				} catch (FuncionRepetidaException | EspectaculoNoExisteException e) {
					JOptionPane.showMessageDialog(null, "La funcion " + nombreFuncion + "ya esta registrada");
				}
			}
		});
		
		//Boton Cancelar
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDefaultCloseAction();
			}
		});
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(116)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblEspectaculo)
								.addComponent(lblPlataforma)
								.addComponent(PlataformaCB, 0, 167, Short.MAX_VALUE)
								.addComponent(EspectaculoCB, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblFechaYHora, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFechaDeAlta)
								.addComponent(spinnerAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(68)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombreFuncion)
								.addComponent(lblAltaDeFuncion)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(121)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblArtistas)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
									.addGap(42))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(urlimg, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
										.addComponent(btnOk))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnCancel)
									.addPreferredGap(ComponentPlacement.RELATED))))
						.addComponent(lblUrlImagen))
					.addGap(12))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblAltaDeFuncion)
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPlataforma)
						.addComponent(lblArtistas))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(PlataformaCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblEspectaculo)
							.addGap(1)
							.addComponent(EspectaculoCB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblFechaYHora)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFechaDeAlta)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerAlta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(lblNombreFuncion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancel)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(btnOk)))
							.addGap(41))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUrlImagen)
								.addComponent(urlimg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		getContentPane().setLayout(groupLayout);
		//this.setVisible(true);

	}

	private static Boolean cargarEspectaculos() {
		String nombrePlataforma = (String) PlataformaCB.getSelectedItem();
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
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
