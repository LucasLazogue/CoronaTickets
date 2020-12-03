package Presentacion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import logica.Fabrica;
import logica.IEspectaculoController;

import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SeleccionarCategoriasEspectaculo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JPanel contCheckBoxArt;

	public SeleccionarCategoriasEspectaculo(List<String> categoriasSeleccionadas) {
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		categoriasSeleccionadas.clear();
		
		Fabrica f = Fabrica.getInstancia();
		IEspectaculoController cont = f.getIEspectaculoController();
		
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
						for(int i = 0; i < checkboxes.length; i++) {
							JCheckBox chk = (JCheckBox) checkboxes[i];
							if(chk.isSelected())
								categoriasSeleccionadas.add(chk.getText());
						}
						contCheckBoxArt.removeAll();
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						contCheckBoxArt.removeAll();
						categoriasSeleccionadas.clear();
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
		
		//Agregar las categorias
		String[] categorias = cont.listarCategorias();		
		for (int i = 0; i < categorias.length; i++) {
			JCheckBox art = new JCheckBox(categorias[i]);
			contCheckBoxArt.add(art);
		}
	}
}