package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Encargado_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Encargado_GUI frame = new Encargado_GUI();
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
	public Encargado_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¿Qué quieres hacer?");
		lblNewLabel.setBounds(179, 10, 157, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnAñadirActividades = new JButton("AÑADIR ACTIVIDADES");
		btnAñadirActividades.setBounds(0, 33, 468, 89);
		contentPane.add(btnAñadirActividades);
		
		JButton btnPlanificarSesionesDe = new JButton("PLANIFICAR SESIONES DE LA SEMANA");
		btnPlanificarSesionesDe.setBounds(0, 117, 468, 89);
		contentPane.add(btnPlanificarSesionesDe);
		
		JButton btnEnviarFacturas = new JButton("ENVIAR FACTURAS");
		btnEnviarFacturas.setBounds(0, 202, 468, 89);
		contentPane.add(btnEnviarFacturas);
		
		btnAñadirActividades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame añadiractividadFrame = new AñadirActividad_GUI();
				añadiractividadFrame.setVisible(true);
			}
		});
		
		btnEnviarFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame añadiractividadFrame2 = new EnviarFacturas_GUI();
				añadiractividadFrame2.setVisible(true);				
			}
		});
		
		btnPlanificarSesionesDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame añadiractividadFrame3 = new PlanificarSesiones_GUI();
				añadiractividadFrame3.setVisible(true);				
			}
		});
	}

}
