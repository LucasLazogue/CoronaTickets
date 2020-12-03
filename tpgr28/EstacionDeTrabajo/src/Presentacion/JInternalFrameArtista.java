package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import exepciones.PasswordErrorException;
import exepciones.UsuarioRepetidoExepcion;
import logica.Fabrica;
import logica.IUsuarioController;

import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;

public class JInternalFrameArtista extends JInternalFrame{
	private JTextField artistaNickname;
	private JTextField artistaEmail;
	private JTextField artistaNombre;
	private JTextField artistaApellido;
	private JTextField artistaWeb;
	private JTextField artistaUrlImg;
	private JTextField artistaPassword;
	private JTextField artistaVerifiPassword;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JInternalFrameArtista frame = new JInternalFrameArtista();
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
	public JInternalFrameArtista() {
		setClosable(true);
		Fabrica f = Fabrica.getInstancia();
		//IEspectaculoController IEC = f.getIEspectaculoController();
		IUsuarioController ICU = f.getIUsuarioController();
		//setBackground(Color.LIGHT_GRAY);
		//setMaximum(true);
		setVisible(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setBounds(100, 100, 483, 471);
		
		JLabel lblNickname = new JLabel("Ingrese si nickname");
		
		JLabel lblEmail = new JLabel("Ingrese su email");
		
		artistaNickname = new JTextField();
		artistaNickname.setColumns(10);
		
		artistaEmail = new JTextField();
		artistaEmail.setColumns(10);
		
		JLabel lblNombre = new JLabel("Ingrese su nombre");
		
		artistaNombre = new JTextField();
		artistaNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Ingrese su apellido");
		
		artistaApellido = new JTextField();
		artistaApellido.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("fecha de nacimiento");
		
		JLabel lblDescripcion = new JLabel("descripcion (opcional)");
		
		JLabel lblBiografia = new JLabel("biografia (opcional)");
		
		JLabel lblWeb = new JLabel("web (opcional)");
		
		artistaWeb = new JTextField();
		artistaWeb.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JTextArea artistaBiografia = new JTextArea();
		scrollPane_1.setViewportView(artistaBiografia);
		
		JTextArea artistaDescripcion = new JTextArea();
		scrollPane.setViewportView(artistaDescripcion);
		
		
		JSpinner spinner = new JSpinner();
		SpinnerDateModel fechaModel = new SpinnerDateModel();
		spinner.setModel(fechaModel);
		spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				doDefaultCloseAction();
			}
		});
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Date d = (Date) spinner.getValue();
					LocalDateTime dt = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
					ICU.nuevoArtista(artistaNickname.getText(), artistaEmail.getText(), artistaNombre.getText(), artistaApellido.getText(), dt, artistaDescripcion.getText(), artistaBiografia.getText(), artistaWeb.getText(), artistaPassword.getText(), artistaVerifiPassword.getText(), artistaUrlImg.getText());
					doDefaultCloseAction();
				}
				catch(UsuarioRepetidoExepcion | PasswordErrorException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		JLabel lblUrlDeImagen = new JLabel("url de imagen");
		
		artistaUrlImg = new JTextField();
		artistaUrlImg.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		
		artistaPassword = new JTextField();
		artistaPassword.setColumns(10);
		
		JLabel lblVerificacionDePassword = new JLabel("verificacion de password");
		
		artistaVerifiPassword = new JTextField();
		artistaVerifiPassword.setColumns(10);
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBiografia)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNickname)
								.addComponent(lblEmail)
								.addComponent(lblNombre)
								.addComponent(lblApellido))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(artistaNickname, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
								.addComponent(artistaEmail, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
								.addComponent(artistaNombre, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
								.addComponent(artistaApellido, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFechaDeNacimiento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescripcion)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblWeb)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(artistaWeb, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnOk)
							.addGap(18)
							.addComponent(btnCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUrlDeImagen)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(artistaUrlImg, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(artistaPassword, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVerificacionDePassword)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(artistaVerifiPassword, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNickname)
						.addComponent(artistaNickname, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEmail)
						.addComponent(artistaEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(artistaNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(artistaApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaDeNacimiento)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBiografia)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWeb)
						.addComponent(artistaWeb, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUrlDeImagen)
						.addComponent(artistaUrlImg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(artistaPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVerificacionDePassword)
						.addComponent(artistaVerifiPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnOk))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}

