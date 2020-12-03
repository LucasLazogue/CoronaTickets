package Presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import exepciones.EspectaculoNoExisteException;
import logica.Fabrica;
import logica.IEspectaculoController;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AceptarRechazarEspectaculo extends JInternalFrame {
	private IEspectaculoController ICont;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AceptarRechazarEspectaculo frame = new AceptarRechazarEspectaculo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AceptarRechazarEspectaculo() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setResizable(true);
		setTitle("Aceptar/Rechazar espectáculo");
		setBounds(100, 100, 450, 208);
		
		Fabrica f = Fabrica.getInstancia();
		ICont = f.getIEspectaculoController();
		
		JLabel lblSeleccioneUnEspectculo = new JLabel("Seleccione un espectáculo: ");
		
		JLabel lblEspectaculosIngresadosQue = new JLabel("A continuación se listan los espectaculos ingresados");
		
		JLabel lblQueAnNo = new JLabel("que aún no han sido aceptados o rechazados.");
		
		JComboBox<String> cboxEspectaculos = new JComboBox<String>();
		
		JButton btnAceptar = new JButton("Aceptar espectáculo");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ICont.aceptarEspectaculo(String.valueOf(cboxEspectaculos.getSelectedItem()), true);
					doDefaultCloseAction();
				} catch (EspectaculoNoExisteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		JButton btnRechazar = new JButton("Rechazar espectáculo");
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ICont.aceptarEspectaculo(String.valueOf(cboxEspectaculos.getSelectedItem()), false);
					doDefaultCloseAction();
				} catch (EspectaculoNoExisteException e1) {
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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnAceptar)
							.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
							.addComponent(btnRechazar))
						.addComponent(lblEspectaculosIngresadosQue, GroupLayout.PREFERRED_SIZE, 416, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSeleccioneUnEspectculo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cboxEspectaculos, 0, 207, Short.MAX_VALUE))
						.addComponent(btnCancelar)
						.addComponent(lblQueAnNo))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEspectaculosIngresadosQue)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblQueAnNo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(cboxEspectaculos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSeleccioneUnEspectculo))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAceptar)
						.addComponent(btnRechazar))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(btnCancelar)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);

		String[] espectaculos = ICont.listarEspectaculosEstadoIngresado();
		if (espectaculos == null) {
			JOptionPane.showMessageDialog(null, "No hay espectáculos que aún no hayan sido aceptados o rechazados");
			doDefaultCloseAction();
		}else {
			for (int i = 0; i < espectaculos.length; i++)
				cboxEspectaculos.addItem(espectaculos[i]);
		}
	}
}
