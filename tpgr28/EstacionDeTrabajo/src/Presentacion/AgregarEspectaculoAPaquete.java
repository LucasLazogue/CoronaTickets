package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import exepciones.EspectadorRegistradoEnFuncionExepcion;
import exepciones.FuncionLlenaExepcion;
import exepciones.NoEsEspectadorExepcion;
import logica.Fabrica;
import logica.IEspectaculoController;
import logica.IUsuarioController;

import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridBagLayout;

public class AgregarEspectaculoAPaquete extends JInternalFrame {
	
	private IEspectaculoController ICE;

	private final JPanel contentPanel = new JPanel();
	private JFrame frame;
	@SuppressWarnings("rawtypes")
	private JComboBox listaPaquetes, listaPlataformas, listaEspectaculos;
	private JTextField nombreEspectador;	
	private String nombrePlataforma;





	public AgregarEspectaculoAPaquete() {
		
	Fabrica f = Fabrica.getInstancia();	
	ICE = f.getIEspectaculoController();

       	setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Agregar Espectaculo a Paquete");
        setVisible(true);
        setBounds(30, 30, 400, 280);
        
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
						String plat = String.valueOf(listaPlataformas.getSelectedItem());
						String paq = String.valueOf(listaPaquetes.getSelectedItem());
						String esp=  String.valueOf(listaEspectaculos.getSelectedItem());
						
						ICE.agregarEspectaculoAPaquete(plat, esp, paq);
						setVisible(false);
					}
				});

				

				okButton.setActionCommand("OK");
				botonera.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						doDefaultCloseAction();
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
				JPanel paquete = new JPanel();
				paquete.setBounds(20,20,20,20);
				paquete.setLayout(new FlowLayout(FlowLayout.LEFT));
				datos.add(paquete, BorderLayout.CENTER);
				{
					JTextPane txtPlataforma = new JTextPane();
					txtPlataforma.setEditable(false);
					txtPlataforma.setText("Seleccionar Paquete");					
					paquete.add(txtPlataforma);
				}
				{
					listaPaquetes = new JComboBox();
					paquete.add(listaPaquetes);
					cargarPaquetes();
				}
			}
			
			{
				JPanel plataforma = new JPanel();
				plataforma.setBounds(20,20,20,20);
				plataforma.setLayout(new FlowLayout(FlowLayout.LEFT));
				datos.add(plataforma, BorderLayout.CENTER);
				{
					JTextPane txtEspectaculo = new JTextPane();
					txtEspectaculo.setEditable(false);
					txtEspectaculo.setText("Seleccionar Plataforma:");					
					plataforma.add(txtEspectaculo);
				}
				{
					listaPlataformas = new JComboBox();
					plataforma.add(listaPlataformas);					
					listaPaquetes.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							listaPlataformas.removeAllItems();							
							cargarPlataformas();
						}
					});
				}
			}
			
			{
				JPanel espectaculo = new JPanel();
				espectaculo.setBounds(20,20,20,20);
				espectaculo.setLayout(new FlowLayout(FlowLayout.LEFT));
				datos.add(espectaculo, BorderLayout.CENTER);
				{
					JTextPane txtFuncion = new JTextPane();
					txtFuncion.setEditable(false);
					txtFuncion.setText("Seleccionar Espectaculo:");					
					espectaculo.add(txtFuncion);
				}
				{
					listaEspectaculos = new JComboBox();
					espectaculo.add(listaEspectaculos);
					String key = String.valueOf(listaPlataformas.getSelectedItem());
					String plat = String.valueOf(listaPaquetes.getSelectedItem());
					cargarEspectaculos(key,plat);
					listaPlataformas.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							listaEspectaculos.removeAllItems();
							String key = String.valueOf(listaPlataformas.getSelectedItem());
							String plat = String.valueOf(listaPaquetes.getSelectedItem());
							cargarEspectaculos(key,plat);
						}
					});
				}
			}
			
			{
				{
					nombreEspectador = new JTextField();
					nombreEspectador.setColumns(10);
				}
			}
			{
				JPanel canjea = new JPanel();
				canjea.setBounds(20,20,20,20);
				canjea.setLayout(new FlowLayout(FlowLayout.LEFT));
				datos.add(canjea, BorderLayout.CENTER);
			}
		}			
}	

private void cargarPaquetes() {
	String[] paquetes = ICE.listarPaquetes();
	if(paquetes != null) {
		for (String paquete: paquetes) {
			listaPaquetes.addItem(paquete);
		}
	}
	else {
		JOptionPane.showMessageDialog(null, "No hay paquetes en el sistema.");
		doDefaultCloseAction();
	}
			
}


private void cargarPlataformas() {
	String[] plataformas = ICE.getPlataformas();
	if(plataformas != null) {
		for (String espectaculo: plataformas) {
			listaPlataformas.addItem(espectaculo);
		}
	}
}

private void cargarEspectaculos(String nombrePlataforma, String nombrePaq) {
	if(nombrePlataforma != "null") {
    	String[] espectaculos = ICE.listarEspectaculosNoEnPaquete(nombrePlataforma, nombrePaq);
    	if(espectaculos != null) {
			for (int i = 0; i < espectaculos.length; i++) {
				listaEspectaculos.addItem(espectaculos[i]);
			}
    	}
	}
}
}
