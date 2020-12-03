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
import logica.Historial;
import logica.IEspectaculoController;
import logica.IUsuarioController;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class GenerarCodigo extends JInternalFrame {

	private JFrame frame;
	private JTextField codigo;


	

	/**
	 * Create the application.
	 */
	public GenerarCodigo() {
		
       	setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar espectador a funcion");
        setVisible(true);
        setBounds(30, 30, 500, 114);
		Historial historial = Historial.getInstance();

		
		codigo = new JTextField();
		codigo.setEditable(false);
		codigo.setColumns(10);
		if (historial.getPass() == null) {
			codigo.setText("Sin asignar");
		} else {
			codigo.setText(historial.getPass());
		}
		
		JButton btnNewButton = new JButton("Generar código");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				historial.generarPass();
				codigo.setText("http://localhost:8080/web-1/Historial?codigo=" + historial.getPass());
			
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(codigo, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addComponent(codigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
	}

}
