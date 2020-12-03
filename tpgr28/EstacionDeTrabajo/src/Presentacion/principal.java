package Presentacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exepciones.ErrorCantidadCanjeExcepcion;
import exepciones.EspectaculoNoExisteException;
import exepciones.EspectaculoRepetidoException;
import exepciones.EspectadorRegistradoEnFuncionExepcion;
import exepciones.FechaPasadaException;
import exepciones.FuncionLlenaExepcion;
import exepciones.FuncionRepetidaException;
import exepciones.MaxEspMenorMinEspException;
import exepciones.NoEsEspectadorExepcion;
import exepciones.PaqueteRepetidoException;
import exepciones.PasswordErrorException;
import exepciones.PlataformaRepetidaException;
import exepciones.RegistroNoSePuedeCanjearException;
import exepciones.UsuarioNoExisteException;
import exepciones.UsuarioRepetidoExepcion;
import exepciones.YaComproPaqueteException;
import exepciones.FinVigAntesIniVigException;
import logica.Espectaculo;
import logica.Fabrica;
import logica.Funcion;
import logica.IEspectaculoController;
import logica.IUsuarioController;
import logica.ManejadorPlataforma;
import logica.Plataforma;
import servidor.Publicador;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;

public class principal extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
			    Publicador p = new Publicador();
			    p.publicar();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCasosDeUso = new JMenu("Casos de uso");
		menuBar.add(mnCasosDeUso);
		
		JMenu mnAltaDeUsuario = new JMenu("Alta de Usuario");
        mnCasosDeUso.add(mnAltaDeUsuario);

        JMenuItem mntmIngresarArtista = new JMenuItem("Ingresar Artista");
        mntmIngresarArtista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JInternalFrameArtista ventana = new JInternalFrameArtista();
				getContentPane().add(ventana);
				ventana.toFront();
            }
        });
        mnAltaDeUsuario.add(mntmIngresarArtista);

        JMenuItem mntmIngresarEspectador = new JMenuItem("Ingresar Espectador");
        mntmIngresarEspectador.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JInternalFrameEspectador ventana = new JInternalFrameEspectador();
				getContentPane().add(ventana);
				ventana.toFront();
            }
        });
        mnAltaDeUsuario.add(mntmIngresarEspectador);
		
		JMenuItem mntmConsultaDeUsuario = new JMenuItem("Consulta de Usuario");
		mntmConsultaDeUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaDeUsuario ventana = new ConsultaDeUsuario();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mnCasosDeUso.add(mntmConsultaDeUsuario);
		
		JMenuItem mntmAltaDeCategoria = new JMenuItem("Alta de Categoria");
		mntmAltaDeCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaDeCategoria ventana = new AltaDeCategoria();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mnCasosDeUso.add(mntmAltaDeCategoria);
		
		JMenuItem mntmAltaDeEspectculo = new JMenuItem("Alta de Espectáculo");
		mntmAltaDeEspectculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaDeEspectaculo ventana = new AltaDeEspectaculo();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mnCasosDeUso.add(mntmAltaDeEspectculo);
		
		JMenuItem mntmConsultaDeEspectculo = new JMenuItem("Consulta de Espectáculo");
		mntmConsultaDeEspectculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaDeEspectaculo ventana = new ConsultaDeEspectaculo();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		
		JMenuItem mntmAceptarrechazarEspectculo = new JMenuItem("Aceptar/Rechazar Espectáculo");
		mntmAceptarrechazarEspectculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AceptarRechazarEspectaculo ventana = new AceptarRechazarEspectaculo();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mntmAceptarrechazarEspectculo.setHorizontalAlignment(SwingConstants.LEFT);
		mnCasosDeUso.add(mntmAceptarrechazarEspectculo);
		mnCasosDeUso.add(mntmConsultaDeEspectculo);
		
		JMenuItem mntmAltaDeFuncin = new JMenuItem("Alta de Función de Espectáculo");
		mntmAltaDeFuncin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaDeFuncionDeEspectaculo ventana = new AltaDeFuncionDeEspectaculo();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mnCasosDeUso.add(mntmAltaDeFuncin);
		
		JMenuItem mntmConsultaDeFuncin = new JMenuItem("Consulta de Función de Espectáculo");
		mntmConsultaDeFuncin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultaDeFuncionDeEspectaculo ventana = new ConsultaDeFuncionDeEspectaculo();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mnCasosDeUso.add(mntmConsultaDeFuncin);
		
		JMenuItem mntmRegistroAFuncin = new JMenuItem("Registro a Función de Espectáculo\n");
		mntmRegistroAFuncin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistroAFuncion ventana = new RegistroAFuncion();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mnCasosDeUso.add(mntmRegistroAFuncin);
		
		JMenuItem mntmCrearPaqueteDe = new JMenuItem("Crear Paquete de Espectáculos");
		mntmCrearPaqueteDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearPaqueteDeEspectaculos ventana = new CrearPaqueteDeEspectaculos();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mnCasosDeUso.add(mntmCrearPaqueteDe);
		
		JMenuItem mntmAgregarEspectculoA = new JMenuItem("Agregar Espectáculo a Paquete");
		mntmAgregarEspectculoA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarEspectaculoAPaquete ventana = new AgregarEspectaculoAPaquete();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mnCasosDeUso.add(mntmAgregarEspectculoA);
		
		JMenuItem mntmConsultaDePaquetes = new JMenuItem("Consulta de paquetes de espectáculos");
		mntmConsultaDePaquetes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaDePaqueteDeEspectaculos ventana = new consultaDePaqueteDeEspectaculos();
				getContentPane().add(ventana);
				ventana.toFront();
			}
		});
		mnCasosDeUso.add(mntmConsultaDePaquetes);
		
	    JMenuItem mntmGenerarCodigo = new JMenuItem("Generar Codigo");
	    mntmGenerarCodigo.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent arg0) {
	        GenerarCodigo ventana = new GenerarCodigo();
	        getContentPane().add(ventana);
	        ventana.toFront();
	      }
	    });
	    mnCasosDeUso.add(mntmGenerarCodigo);
		
		JMenu mnDatosDePrueba = new JMenu("Datos de prueba");
		menuBar.add(mnDatosDePrueba);
		
		JMenuItem mntmCargar = new JMenuItem("Cargar");
		mntmCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			   IUsuarioController ICU = Fabrica.getInstancia().getIUsuarioController();
				ICU.cargarDatosPrueba();
			}
		});
		mnDatosDePrueba.add(mntmCargar);
		
		
		//JPanel contentPane = new JPanel();
		JDesktopPane contentPane = new JDesktopPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 440, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 239, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}
}
