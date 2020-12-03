package Presentacion;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;

import exepciones.EspectaculoRepetidoException;
import exepciones.MaxEspMenorMinEspException;
import exepciones.UsuarioNoExisteException;

import javax.swing.SpinnerDateModel;

import logica.Fabrica;
import logica.IEspectaculoController;

import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class AltaDeEspectaculo extends JInternalFrame {
	private JTextField txtNickname;
	private JTextField txtNomEspectaculo;
	private JTextField txtDuracion;
	private JTextField txtCantEspectadoresMin;
	private JTextField txtCantEspectadoresMax;
	private JTextField txtUrl;
	private JTextField txtCosto;
	private IEspectaculoController ICont;
	private JTextArea txtDescripcion;
	private JSpinner spinnerFecha;
	private JTextField txtUrlImg;
	private List<String> categoriasSeleccionadas = new ArrayList<String>();
	private SeleccionarCategoriasEspectaculo seleccionarCategorias;
	private JTextField txtCantPremiosPorFuncion;
	private JTextField txtUrlVideo;
	private JTextArea txtDescripcionPremio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AltaDeEspectaculo frame = new AltaDeEspectaculo();
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
	public AltaDeEspectaculo() {
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(100, 100, 461, 455);
		
		Fabrica f = Fabrica.getInstancia();
		ICont = f.getIEspectaculoController();
		
		JLabel lblSeleccioneUnaPlataforma = new JLabel("Seleccione una plataforma:");
		
		JComboBox<String> comboBoxPlataformas = new JComboBox<String>();
		
		JLabel lblIngreseElNickname = new JLabel("Nickname del artista organizador:");
		
		txtNickname = new JTextField();
		txtNickname.setColumns(10);
		
		JLabel lblNombreDelNuevo = new JLabel("Nombre del nuevo espectáculo:");
		
		txtNomEspectaculo = new JTextField();
		txtNomEspectaculo.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblDuracin = new JLabel("Duración:");
		
		txtDuracion = new JTextField();
		txtDuracion.setColumns(10);
		
		JLabel lblEspectadoresMnimos = new JLabel("Cantidad de espectadores mínimos:");
		
		txtCantEspectadoresMin = new JTextField();
		txtCantEspectadoresMin.setColumns(10);
		
		JLabel lblCantidadDeEspectadores = new JLabel("Cantidad de espectadores máximos:");
		
		txtCantEspectadoresMax = new JTextField();
		txtCantEspectadoresMax.setColumns(10);
		
		JLabel lblUrlAsociada = new JLabel("URL asociada:");
		
		txtUrl = new JTextField();
		txtUrl.setColumns(10);
		
		JLabel lblCosto = new JLabel("Costo:");
		
		txtCosto = new JTextField();
		txtCosto.setColumns(10);
		
		JLabel lblFechaDeAlta = new JLabel("Fecha de alta:");
			
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doDefaultCloseAction();
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String plataforma = String.valueOf(comboBoxPlataformas.getSelectedItem());
					String nickname = txtNickname.getText();
					String nomEspectaculo = txtNomEspectaculo.getText();
					String descripcion = txtDescripcion.getText();
					String url = txtUrl.getText();
					int duracion = Integer.valueOf(txtDuracion.getText());
					int minEspectadores = Integer.valueOf(txtCantEspectadoresMin.getText());
					int maxEspectadores = Integer.valueOf(txtCantEspectadoresMax.getText());
					int costo = Integer.valueOf(txtCosto.getText());
					Date fechaAlta = (Date) spinnerFecha.getValue();
					String urlImg = txtUrlImg.getText();
					if (urlImg.contentEquals("")) urlImg = null;
					String descripcionPremio = txtDescripcionPremio.getText();
					int cantPremiosPorFuncion = Integer.valueOf(txtCantPremiosPorFuncion.getText());
					String urlVideo = txtUrlVideo.getText();
					if (urlVideo.contentEquals("")) urlVideo = null;
					String[] catSel = new String[categoriasSeleccionadas.size()];
					for (int i = 0; i < categoriasSeleccionadas.size(); i++) {
						catSel[i] = categoriasSeleccionadas.get(i);
					}
					ICont.crearEspectaculo(plataforma, nickname, nomEspectaculo, descripcion, duracion, minEspectadores, maxEspectadores,
					    url, costo, fechaAlta, catSel, urlImg, descripcionPremio, cantPremiosPorFuncion, urlVideo);
					doDefaultCloseAction();
				} catch (NumberFormatException | EspectaculoRepetidoException | UsuarioNoExisteException | MaxEspMenorMinEspException e1) {
					String comentario;
					if (e1 instanceof NumberFormatException) comentario = "No se ha ingresado un entero en un dato que sólo acepta enteros.";
					else if (e1 instanceof ParseException) comentario = "El formato de la fecha es inválido. Formato correcto: dd/mm/aaaa.";
					else comentario = e1.getMessage();
					JOptionPane.showMessageDialog(null, comentario);
				}
			}
		});
		
		spinnerFecha = new JSpinner();
		SpinnerDateModel fechaModel = new SpinnerDateModel();
		spinnerFecha.setModel(fechaModel);
		spinnerFecha.setEditor(new JSpinner.DateEditor(spinnerFecha, "dd/MM/yyyy"));
		
		JLabel lblUrlDeImagen = new JLabel("URL de imagen  (opcional):");
		
		txtUrlImg = new JTextField();
		txtUrlImg.setColumns(10);
		
		JButton btnSeleccionarCategorias = new JButton("Seleccionar categorías");
		btnSeleccionarCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionarCategorias = new SeleccionarCategoriasEspectaculo(categoriasSeleccionadas);
				seleccionarCategorias.setModal(true);
			}
		});
		
		JLabel lblDescripcinDelPremio = new JLabel("Descripción del premio:");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblCantidadDePremios = new JLabel("Cantidad de premios a sortear por función:");
		
		txtCantPremiosPorFuncion = new JTextField();
		txtCantPremiosPorFuncion.setColumns(10);
		
		JLabel lblUrlDeVideo = new JLabel("URL de video:");
		
		txtUrlVideo = new JTextField();
		txtUrlVideo.setColumns(10);
        
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
		  groupLayout.createParallelGroup(Alignment.LEADING)
		    .addGroup(groupLayout.createSequentialGroup()
		      .addContainerGap()
		      .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		        .addGroup(groupLayout.createSequentialGroup()
		          .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
		            .addGroup(groupLayout.createSequentialGroup()
		              .addComponent(lblSeleccioneUnaPlataforma)
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addComponent(comboBoxPlataformas, 0, 227, Short.MAX_VALUE))
		            .addGroup(groupLayout.createSequentialGroup()
		              .addComponent(lblIngreseElNickname)
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addComponent(txtNickname, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
		            .addGroup(groupLayout.createSequentialGroup()
		              .addComponent(lblNombreDelNuevo)
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addComponent(txtNomEspectaculo, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
		            .addGroup(groupLayout.createSequentialGroup()
		              .addComponent(lblDescripcin)
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE))
		            .addGroup(groupLayout.createSequentialGroup()
		              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addGroup(groupLayout.createSequentialGroup()
		                  .addComponent(lblDuracin)
		                  .addGap(13)
		                  .addComponent(txtDuracion, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
		                  .addPreferredGap(ComponentPlacement.UNRELATED)
		                  .addComponent(lblEspectadoresMnimos))
		                .addGroup(groupLayout.createSequentialGroup()
		                  .addComponent(lblCosto)
		                  .addPreferredGap(ComponentPlacement.RELATED)
		                  .addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
		                  .addPreferredGap(ComponentPlacement.RELATED)
		                  .addComponent(lblCantidadDeEspectadores)))
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		                .addComponent(txtCantEspectadoresMax, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
		                .addComponent(txtCantEspectadoresMin, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
		            .addGroup(groupLayout.createSequentialGroup()
		              .addComponent(lblUrlDeImagen)
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addComponent(txtUrlImg, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
		            .addGroup(groupLayout.createSequentialGroup()
		              .addComponent(btnSeleccionarCategorias)
		              .addGap(81)
		              .addComponent(btnOk)
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addComponent(btnCancelar))
		            .addGroup(groupLayout.createSequentialGroup()
		              .addComponent(lblFechaDeAlta)
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addComponent(spinnerFecha, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
		            .addGroup(groupLayout.createSequentialGroup()
		              .addComponent(lblUrlAsociada)
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addComponent(txtUrl, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE))
		            .addGroup(groupLayout.createSequentialGroup()
		              .addComponent(lblDescripcinDelPremio)
		              .addPreferredGap(ComponentPlacement.RELATED)
		              .addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
		              .addGap(2)))
		          .addGap(6))
		        .addGroup(groupLayout.createSequentialGroup()
		          .addComponent(lblCantidadDePremios)
		          .addPreferredGap(ComponentPlacement.RELATED)
		          .addComponent(txtCantPremiosPorFuncion, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
		          .addContainerGap())
		        .addGroup(groupLayout.createSequentialGroup()
		          .addComponent(lblUrlDeVideo)
		          .addPreferredGap(ComponentPlacement.RELATED)
		          .addComponent(txtUrlVideo, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
		          .addContainerGap())))
		);
		groupLayout.setVerticalGroup(
		  groupLayout.createParallelGroup(Alignment.LEADING)
		    .addGroup(groupLayout.createSequentialGroup()
		      .addContainerGap()
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblSeleccioneUnaPlataforma)
		        .addComponent(comboBoxPlataformas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblIngreseElNickname)
		        .addComponent(txtNickname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblNombreDelNuevo)
		        .addComponent(txtNomEspectaculo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		        .addComponent(lblDescripcin)
		        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblDuracin)
		        .addComponent(lblEspectadoresMnimos)
		        .addComponent(txtCantEspectadoresMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		        .addComponent(txtDuracion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblCantidadDeEspectadores)
		        .addComponent(txtCantEspectadoresMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		        .addComponent(lblCosto)
		        .addComponent(txtCosto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblUrlAsociada)
		        .addComponent(txtUrl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblFechaDeAlta)
		        .addComponent(spinnerFecha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblUrlDeImagen)
		        .addComponent(txtUrlImg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		        .addComponent(lblDescripcinDelPremio)
		        .addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(txtCantPremiosPorFuncion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
		        .addComponent(lblCantidadDePremios))
		      .addPreferredGap(ComponentPlacement.RELATED)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(lblUrlDeVideo)
		        .addComponent(txtUrlVideo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		      .addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
		      .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
		        .addComponent(btnCancelar)
		        .addComponent(btnOk)
		        .addComponent(btnSeleccionarCategorias))
		      .addContainerGap())
		);
		
		txtDescripcionPremio = new JTextArea();
		scrollPane_1.setViewportView(txtDescripcionPremio);
		
		txtDescripcion = new JTextArea();
		scrollPane.setViewportView(txtDescripcion);
		getContentPane().setLayout(groupLayout);
		this.setVisible(true);
		
		String[] plataformas = ICont.getPlataformas();
		if (plataformas == null) {
			JOptionPane.showMessageDialog(null, "No hay plataformas en el sistema, el caso de uso no se puede llevar a cabo");
			doDefaultCloseAction();
		}else {
			for (int i = 0; i < plataformas.length; i++)
				comboBoxPlataformas.addItem(plataformas[i]);
		}
	}
}
