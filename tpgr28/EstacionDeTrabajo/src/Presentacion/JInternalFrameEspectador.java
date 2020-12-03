package Presentacion;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import exepciones.PasswordErrorException;
import exepciones.UsuarioRepetidoExepcion;

import javax.swing.SpinnerDateModel;

import logica.Fabrica;
import logica.IEspectaculoController;
import logica.IUsuarioController;

import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;

public class JInternalFrameEspectador extends JInternalFrame{
	private JTextField espectadorNickname;
	private JTextField espectadorEmail;
	private JTextField espectadorNombre;
	private JTextField espectadorApellido;
	private JTextField espectadorUrlImg;
	private JTextField espectadorPassword;
	private JTextField espectadorVerifiPass;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JInternalFrameEspectador frame = new JInternalFrameEspectador();
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
	public JInternalFrameEspectador() {
		setClosable(true);
		Fabrica f = Fabrica.getInstancia();
		//IEspectaculoController IEC = f.getIEspectaculoController();
		IUsuarioController ICU = f.getIUsuarioController();
		setVisible(true);
		//setMaximum(true);
		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setBounds(100, 100, 496, 354);
		
		JLabel lblNickname = new JLabel("Ingrese su nickname");
		
		JLabel lblEmail = new JLabel("Ingrese su email");
		
		espectadorNickname = new JTextField();
		espectadorNickname.setColumns(10);
		
		espectadorEmail = new JTextField();
		espectadorEmail.setColumns(10);
		
		JLabel lblNombre = new JLabel("Ingrese su nombre");
		
		espectadorNombre = new JTextField();
		espectadorNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Ingrese su apellido");
		
		espectadorApellido = new JTextField();
		espectadorApellido.setColumns(10);
		
		JLabel lblFechaDeNacimiento = new JLabel("Ingrese su fecha de nacimiento");
		
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
					ICU.nuevoEspectador(espectadorNickname.getText(), espectadorEmail.getText(), espectadorNombre.getText(), espectadorApellido.getText(), dt, espectadorPassword.getText(), espectadorVerifiPass.getText(), espectadorUrlImg.getText());
					doDefaultCloseAction();
				}
				catch(UsuarioRepetidoExepcion | PasswordErrorException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				//logica
			}
		});
		
		JLabel lblUrlDeImagen = new JLabel("url de imagen");
		
		espectadorUrlImg = new JTextField();
		espectadorUrlImg.setColumns(10);
		
		espectadorPassword = new JTextField();
		espectadorPassword.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		
		JLabel lblVerificacionDePassword = new JLabel("verificacion de password");
		
		espectadorVerifiPass = new JTextField();
		espectadorVerifiPass.setColumns(10);
		
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNickname)
								.addComponent(lblEmail)
								.addComponent(lblNombre)
								.addComponent(lblApellido))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(espectadorApellido, 233, 233, 233)
								.addComponent(espectadorNombre, 233, 233, 233)
								.addComponent(espectadorEmail, 233, 233, 233)
								.addComponent(espectadorNickname, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFechaDeNacimiento)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnOk)
							.addGap(18)
							.addComponent(btnCancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUrlDeImagen)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(espectadorUrlImg, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(espectadorPassword, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVerificacionDePassword)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(espectadorVerifiPass, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNickname)
						.addComponent(espectadorNickname, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(espectadorEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(espectadorNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApellido)
						.addComponent(espectadorApellido, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFechaDeNacimiento)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUrlDeImagen)
						.addComponent(espectadorUrlImg, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(espectadorPassword, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVerificacionDePassword)
						.addComponent(espectadorVerifiPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnOk))
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);

	}
}

