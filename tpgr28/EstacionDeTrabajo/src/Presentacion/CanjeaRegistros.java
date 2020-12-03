package Presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import logica.DataFuncion;
import logica.DataRegistro;
import logica.Espectador;
import logica.Fabrica;
import logica.IEspectaculoController;
import logica.IUsuarioController;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CanjeaRegistros extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField txtRegistro;
	JPanel contCheckBoxArt;
	int cantRegistros = 3;
	//private List<DataRegistro> registrosCanje = new ArrayList<DataRegistro>();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CanjeaRegistros dialog = new CanjeaRegistros(null, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CanjeaRegistros(String nombreUsuario, List<String> registrosCanjeados) {
		
		Fabrica f = Fabrica.getInstancia();
		IUsuarioController ICU = f.getIUsuarioController();
		IEspectaculoController IEC = f.getIEspectaculoController();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Component[] checkboxes = contCheckBoxArt.getComponents();
						//contar cantidad de seleccionados
						int cantRegistros = 0;
						for(int i = 0; i < checkboxes.length; i++) {
							JCheckBox chk = (JCheckBox) checkboxes[i];
							if(chk.isSelected()) {
								registrosCanjeados.add(chk.getText());
								cantRegistros++;
							}
						}
						if(cantRegistros != 3) {
							JOptionPane.showMessageDialog(getContentPane(),
									"La cantidad de registros a canjear tiene que ser exactamente 0 o 3",
									"Aviso",
								    JOptionPane.PLAIN_MESSAGE);
						}
						else {
							contCheckBoxArt.removeAll();
							setVisible(false);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						contCheckBoxArt.removeAll();
						registrosCanjeados.clear();
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		contCheckBoxArt = new JPanel();
		contCheckBoxArt.setLayout(new BoxLayout(contCheckBoxArt, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(contCheckBoxArt,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		getContentPane().add(scrollPane,BorderLayout.CENTER);	
		
		DataRegistro[] registros = ICU.listarFuncionesEspectador(nombreUsuario);
		
		cargarRegistros(registros);
		
		
	}
	
	private void cargarRegistros(DataRegistro[] registros) {
		for (DataRegistro registro : registros) {
			String data = registro.getFuncion();
			if(registro.getCosto() != 0) {
				JCheckBox art = new JCheckBox(data);
				contCheckBoxArt.add(art);
			}
		}
	}
	

}
