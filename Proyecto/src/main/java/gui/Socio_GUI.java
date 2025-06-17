package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Socio_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Socio_GUI frame = new Socio_GUI();
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
	public Socio_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 510);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¿Qué quieres hacer?");
		lblNewLabel.setBounds(264, 10, 189, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnReservarSesion = new JButton("RESERVAR SESION");
		btnReservarSesion.setBounds(10, 209, 587, 89);
		contentPane.add(btnReservarSesion);
		
		JButton btnCancelarReserva = new JButton("CANCELAR RESERVA");
		btnCancelarReserva.setBounds(10, 121, 587, 89);
		contentPane.add(btnCancelarReserva);
		
		JButton btnConsultarSesiones = new JButton("CONSULTAR SESIONES");
		btnConsultarSesiones.setBounds(10, 33, 587, 89);
		contentPane.add(btnConsultarSesiones);
		
		JButton btnPagarFactura = new JButton("PAGAR FACTURA");
		btnPagarFactura.setBounds(10, 384, 587, 89);
		contentPane.add(btnPagarFactura);
		
		JButton btnConsultarFacturas = new JButton("CONSULTAR FACTURAS");
		btnConsultarFacturas.setBounds(10, 298, 587, 89);
		contentPane.add(btnConsultarFacturas);
		
		btnCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame añadiractividadFrame = new CancelarReserva_GUI();
				añadiractividadFrame.setVisible(true);
			}
		});
		
		btnConsultarFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame añadiractividadFrame2 = new ConsultarFacturas_GUI();
				añadiractividadFrame2.setVisible(true);				
			}
		});
		
		btnConsultarSesiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame añadiractividadFrame3 = new ConsultarSesiones_GUI();
				añadiractividadFrame3.setVisible(true);				
			}
		});
		
		btnPagarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame añadiractividadFrame4 = new PagarFactura_GUI();
				añadiractividadFrame4.setVisible(true);
			}
		});
		
		btnReservarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame añadiractividadFrame5 = new ReservarSesion_GUI();
				añadiractividadFrame5.setVisible(true);				
			}
		});
		
	}

}
