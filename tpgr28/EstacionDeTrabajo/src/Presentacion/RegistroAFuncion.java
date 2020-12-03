package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import exepciones.ErrorCantidadCanjeExcepcion;
import exepciones.EspectaculoNoExisteException;
import exepciones.EspectadorRegistradoEnFuncionExepcion;
import exepciones.FechaPasadaException;
import exepciones.FuncionLlenaExepcion;
import exepciones.NoEsEspectadorExepcion;
import exepciones.RegistroNoSePuedeCanjearException;
import logica.DataEspectaculo;
import logica.DataEspectador;
import logica.DataUsuario;
import logica.Fabrica;
import logica.IEspectaculoController;
import logica.IUsuarioController;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class RegistroAFuncion extends JInternalFrame {
	
	private IUsuarioController uc;
	private IEspectaculoController ec;

	private final JPanel contentPanel = new JPanel();
	private JFrame frame;
	private JTextField nombreUsuario;
	@SuppressWarnings("rawtypes")
	private JComboBox listaPlataformas, listaEspectaculos, listaFunciones, listaPaquetes, regDia, regMes, regAno, regHora, regMin;
	private JTextField nombreEspectador;
	
	private String nombrePlataforma, nombrePaquete;
	private CanjeaRegistros canjeaRegistros;
	
	private DataUsuario usr;
	private List<String> registrosCanjeados = new ArrayList<String>();
	
	private DataEspectaculo dEsp;
	

	/**
	 * Create the application.
	 */
	public RegistroAFuncion() {
			
		Fabrica f = Fabrica.getInstancia();
		uc = f.getIUsuarioController();
		ec = f.getIEspectaculoController();

	       	setResizable(true);
	        setIconifiable(true);
	        setMaximizable(true);
	        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	        setClosable(true);
	        setTitle("Registrar espectador a funcion");
	        setVisible(true);
	        setBounds(30, 30, 500, 350);
	        
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			{
				JPanel botonera = new JPanel();
				botonera.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(botonera, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String nomPlataforma = String.valueOf(listaPlataformas.getSelectedItem());
							String nomEspectaculo = String.valueOf(listaEspectaculos.getSelectedItem());
							String nomFunc = String.valueOf(listaFunciones.getSelectedItem());
							String nomPaq;
							if(String.valueOf(listaPaquetes.getSelectedItem()).equals("-"))
								nomPaq = "";
							else
								nomPaq = String.valueOf(listaPaquetes.getSelectedItem());
							int dDia = Integer.valueOf((String) regDia.getSelectedItem());
							int dMes = Integer.valueOf((String) regMes.getSelectedItem()) - 1;
							int dAno = (Integer.valueOf((String) regAno.getSelectedItem())) - 1900;
							int dHora = Integer.valueOf((String) regHora.getSelectedItem());
							int dMin = Integer.valueOf((String) regMin.getSelectedItem());
							 
							
							Date dFecha = new Date(dAno, dMes, dDia, dHora, dMin);

							
							try {
								ec.confirmarRegistroAFuncion(nomPlataforma, nomPaq, nomEspectaculo, nomFunc, usr.getNickname(), registrosCanjeados, dFecha);
								JOptionPane.showMessageDialog(getContentPane(),
										"Se ha agregado el registro con exito",
										"Aviso",
									    JOptionPane.PLAIN_MESSAGE);
								registrosCanjeados.clear();
								setVisible(false);
								
							} catch (FechaPasadaException e0) {
								 JOptionPane.showMessageDialog(okButton, e0.getMessage(), "Error funcion", JOptionPane.ERROR_MESSAGE);	
								
							} catch (FuncionLlenaExepcion e1) {
								 JOptionPane.showMessageDialog(okButton, e1.getMessage(), "Error funcion", JOptionPane.ERROR_MESSAGE);
								 
							} catch (NoEsEspectadorExepcion e2) {
				                JOptionPane.showMessageDialog(okButton, e2.getMessage(), "Error espectador", JOptionPane.ERROR_MESSAGE);
				                
							} catch (EspectadorRegistradoEnFuncionExepcion e3) {
				                JOptionPane.showMessageDialog(okButton, e3.getMessage(), "Error espectador", JOptionPane.ERROR_MESSAGE);
				                
							} catch (RegistroNoSePuedeCanjearException e3) {
				                JOptionPane.showMessageDialog(okButton, e3.getMessage(), "Error espectador", JOptionPane.ERROR_MESSAGE);
				                
							} catch (ErrorCantidadCanjeExcepcion e4) {
				                JOptionPane.showMessageDialog(okButton, e4.getMessage(), "Error espectador", JOptionPane.ERROR_MESSAGE);
							}
						}
					});

					okButton.setActionCommand("OK");
					botonera.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				
				{
					JButton cancelButton = new JButton("Cancelar");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							registrosCanjeados.clear();
							setVisible(false);
						}
					});
					cancelButton.setActionCommand("Cancel");
					botonera.add(cancelButton);
				}
			}
			{
				JPanel datos = new JPanel();
				datos.setLayout(new BoxLayout(datos, BoxLayout.Y_AXIS));
				getContentPane().add(datos,BorderLayout.CENTER);
				
				{
					JPanel espectador = new JPanel();
					espectador.setBounds(20,20,20,20);
					espectador.setLayout(new FlowLayout(FlowLayout.LEFT));
					datos.add(espectador, BorderLayout.CENTER);
					{
						JTextPane txtEspectador = new JTextPane();
						txtEspectador.setEditable(false);
						txtEspectador.setText("Ingrese nombre espectador");					
						espectador.add(txtEspectador);
					}
					{
						nombreEspectador = new JTextField();
						espectador.add(nombreEspectador);
						nombreEspectador.setColumns(10);
					}
					{
						JButton buscarUsuario = new JButton("CARGAR");
						buscarUsuario.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									usr = uc.mostrarDatosUsuario(nombreEspectador.getText());
								} catch (exepciones.UsuarioNoExisteException e1) {
									JOptionPane.showMessageDialog(buscarUsuario, e1.getMessage(), "Buscar Usuario", JOptionPane.ERROR_MESSAGE);
								}
								if (usr != null) {
									JOptionPane.showMessageDialog(getContentPane(),
											"El usuario " + usr.getNickname() + " ha sido cargado",
											"Aviso",
										    JOptionPane.PLAIN_MESSAGE);
									String key = String.valueOf(listaEspectaculos.getSelectedItem());
									listaPaquetes.removeAllItems();
									cargarPaquetes(usr.getNickname(), key);
								}
							}
						});
						espectador.add(buscarUsuario);
					}
				}
				
				{
					JPanel plataforma = new JPanel();
					plataforma.setBounds(20,20,20,20);
					plataforma.setLayout(new FlowLayout(FlowLayout.LEFT));
					datos.add(plataforma, BorderLayout.CENTER);
					{
						JTextPane txtPlataforma = new JTextPane();
						txtPlataforma.setEditable(false);
						txtPlataforma.setText("Seleccionar Plataforma");					
						plataforma.add(txtPlataforma);
					}
					{
						listaPlataformas = new JComboBox();
						plataforma.add(listaPlataformas);
						cargarPlataformas();
						nombrePlataforma = String.valueOf(listaPlataformas.getSelectedItem());
					}
				}
				
				{
					JPanel espectaculo = new JPanel();
					espectaculo.setBounds(20,20,20,20);
					espectaculo.setLayout(new FlowLayout(FlowLayout.LEFT));
					datos.add(espectaculo, BorderLayout.CENTER);
					{
						JTextPane txtEspectaculo = new JTextPane();
						txtEspectaculo.setEditable(false);
						txtEspectaculo.setText("Seleccionar espectaculo:");					
						espectaculo.add(txtEspectaculo);
					}
					{
						listaEspectaculos = new JComboBox();
						espectaculo.add(listaEspectaculos);
						String key = String.valueOf(listaPlataformas.getSelectedItem());
						cargarEspectaculos(key);
						listaPlataformas.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								listaEspectaculos.removeAllItems();
								String key = String.valueOf(listaPlataformas.getSelectedItem());
								cargarEspectaculos(key);
								nombrePlataforma = String.valueOf(listaPlataformas.getSelectedItem());
							}
						});
					}
				}
				
				{
					JPanel funcion = new JPanel();
					funcion.setBounds(20,20,20,20);
					funcion.setLayout(new FlowLayout(FlowLayout.LEFT));
					datos.add(funcion, BorderLayout.CENTER);
					{
						JTextPane txtFuncion = new JTextPane();
						txtFuncion.setEditable(false);
						txtFuncion.setText("Seleccionar funcion:");					
						funcion.add(txtFuncion);
					}
					{
						listaFunciones = new JComboBox();
						funcion.add(listaFunciones);
						String key = String.valueOf(listaEspectaculos.getSelectedItem());
						String plat = String.valueOf(listaPlataformas.getSelectedItem());
						cargarFunciones(plat, key);
						listaEspectaculos.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								listaFunciones.removeAllItems();
								String key = String.valueOf(listaEspectaculos.getSelectedItem());
								String plat = String.valueOf(listaPlataformas.getSelectedItem());
								cargarFunciones(plat, key);

								if(usr != null) {
									listaPaquetes.removeAllItems();
									cargarPaquetes(usr.getNickname(), key);
								}
								nombrePlataforma = String.valueOf(listaPlataformas.getSelectedItem());

							}
						});
					}
				}
				
				{
					JPanel paquete = new JPanel();
					paquete.setBounds(20,20,20,20);
					paquete.setLayout(new FlowLayout(FlowLayout.LEFT));
					datos.add(paquete, BorderLayout.CENTER);
					{
						JTextPane txtPaquete = new JTextPane();
						txtPaquete.setEditable(false);
						txtPaquete.setText("Seleccionar paquete:");					
						paquete.add(txtPaquete);
					}
					{
						listaPaquetes = new JComboBox();
						paquete.add(listaPaquetes);
						if(usr != null) {
							listaPaquetes.removeAllItems();
							String key = String.valueOf(listaEspectaculos.getSelectedItem());
							cargarPaquetes(usr.getNickname(), key);
						}


					}
				}
				{
					JPanel canjea = new JPanel();
					canjea.setBounds(20,20,20,20);
					canjea.setLayout(new FlowLayout(FlowLayout.LEFT));
					datos.add(canjea, BorderLayout.CENTER);
					{
						JLabel txtCanje = new JLabel("Canjea registros?");
						canjea.add(txtCanje);
					}
					{
						JButton btnCanje = new JButton("Agregar registros");
						btnCanje.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (usr != null) {
									canjeaRegistros = new CanjeaRegistros(usr.getNickname(), registrosCanjeados);
									canjeaRegistros.setModal(true);
									canjeaRegistros.setVisible(true);	
								} else {
									JOptionPane.showMessageDialog(null, "No hay un usuario seleccionado.");
								}
							}
						});
						canjea.add(btnCanje);
					}
					{
						JButton btnLimpiar = new JButton("Limpiar registros");
						btnLimpiar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								registrosCanjeados.clear();
								canjeaRegistros.cantRegistros = 0;
							}
						});
						canjea.add(btnLimpiar);
					}
				}
				{
					JPanel fechaRegistro = new JPanel();
					fechaRegistro.setBounds(20,20,20,20);
					fechaRegistro.setLayout(new FlowLayout(FlowLayout.LEFT));
					datos.add(fechaRegistro, BorderLayout.CENTER);
					{
						JLabel txtDia = new JLabel("dia");
						fechaRegistro.add(txtDia);
					}
					{
						regDia = new JComboBox();
						fechaRegistro.add(regDia);
					}
					{
						JSeparator separator = new JSeparator();
						fechaRegistro.add(separator);
					}
					{
						JSeparator separator = new JSeparator();
						fechaRegistro.add(separator);
					}
					{
						JLabel txtMes = new JLabel("Mes");
						fechaRegistro.add(txtMes);
					}
					{
						regMes = new JComboBox();
						fechaRegistro.add(regMes);
					}
					{
						JSeparator separator = new JSeparator();
						fechaRegistro.add(separator);
					}
					{
						JSeparator separator = new JSeparator();
						fechaRegistro.add(separator);
					}
					{
						JLabel txtAno = new JLabel("A\u00F1o");
						fechaRegistro.add(txtAno);
					}
					{
						regAno = new JComboBox();
						fechaRegistro.add(regAno);
					}
					{
						JSeparator separator = new JSeparator();
						fechaRegistro.add(separator);
					}
					{
						JSeparator separator = new JSeparator();
						fechaRegistro.add(separator);
					}
					{
						JLabel txtHora = new JLabel("Hora");
						fechaRegistro.add(txtHora);
					}
					{
						regHora = new JComboBox();
						fechaRegistro.add(regHora);
					}
					{
						JSeparator separator = new JSeparator();
						fechaRegistro.add(separator);
					}
					{
						JSeparator separator = new JSeparator();
						fechaRegistro.add(separator);
					}
					{
						JLabel txtMinutos = new JLabel("Minutos");
						fechaRegistro.add(txtMinutos);
					}
					{
						regMin = new JComboBox();
						fechaRegistro.add(regMin);
					}
					cargarFechas();
					
				}
				
			}			
	}	
	
	private void cargarPlataformas() {
    	String[] plataformas = ec.getPlataformas();
    	if(plataformas != null) {
			for (String plataforma: plataformas) {
				listaPlataformas.addItem(plataforma);
			}
    	} else {
    		JOptionPane.showMessageDialog(null, "No hay plataformas en el sistema.");
    		doDefaultCloseAction();
    	}
    }
    
	private void cargarEspectaculos(String nombrePlataforma) {
		if(listaPlataformas.getSelectedItem() != null) {
	    	String[] espectaculos = ec.listarEspectaculos(nombrePlataforma);
	    	if(espectaculos != null) {
				for (int i = 0; i < espectaculos.length; i++) {
					listaEspectaculos.addItem(espectaculos[i]);
				}
	    	}
		}
    }
    
    private void cargarFunciones(String nombrePlataforma, String nombreEspectaculo) {
    	if(listaPlataformas.getSelectedItem() != null && listaEspectaculos.getSelectedItem() != null){
	    	String[] funciones = ec.listarFuncionesEspectaculo(nombrePlataforma, nombreEspectaculo);
	    	if(funciones != null) {
		    	for (int i = 0; i < funciones.length; i++) {
					listaFunciones.addItem(funciones[i]);
				}
	    	}
    	}
    }
    
    private void cargarPaquetes(String nombreUsuario, String nombreEspectaculo) {
    	listaPaquetes.addItem("-");
    	String[] paqs = ((DataEspectador)usr).getPaquetes();
    	for(int i = 0; i < paqs.length; i++) {
        	String[] esps = ec.listarEspectaculosEnPaquete(nombrePlataforma, paqs[i]);
    		if(Arrays.asList(esps).contains(nombreEspectaculo))
    			listaPaquetes.addItem(paqs[i]);
    	}
    }
    
    private void cargarFechas() {
    	for(int i = 1; i <= 31; i++) {
    		regDia.addItem(String.valueOf(i));
    		if(i <= 12)
        		regMes.addItem(String.valueOf(i));
    	}
    	for(int i = 1990; i <= 2022; i++)
    		regAno.addItem(String.valueOf(i));
    	
    	for (int i = 0; i <= 59; i++) {
    		regMin.addItem(String.valueOf(i));
    		if(i <= 23)
    			regHora.addItem(String.valueOf(i));
    	}
    }
}
