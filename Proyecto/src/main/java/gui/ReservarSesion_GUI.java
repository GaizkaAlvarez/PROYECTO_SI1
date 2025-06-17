package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import businessLogic.BLFacade;
import domain.*;

public class ReservarSesion_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textAreaResultados;
	
	private DefaultComboBoxModel<Integer> numSocioBoxModel = new DefaultComboBoxModel<Integer>();
	private JComboBox<Integer> comboBox = new JComboBox<Integer>();
	
	private DefaultComboBoxModel<String> actividadesComboBoxModel = new DefaultComboBoxModel<String>();
	private JComboBox<String> comboBox2 = new JComboBox<String>();

	private BLFacade logicaNegocio = MainGUI.getBusinessLogic();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ReservarSesion_GUI frame = new ReservarSesion_GUI();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ReservarSesion_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 297);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNombreActividad = new JLabel("Actividad:");
		lblNombreActividad.setBounds(20, 20, 120, 20);
		contentPane.add(lblNombreActividad);

		JButton botonConsultar = new JButton("Reservar sesion");
		botonConsultar.setBounds(350, 20, 210, 50);
		contentPane.add(botonConsultar);

		textAreaResultados = new JTextArea();
		textAreaResultados.setEditable(false);
		textAreaResultados.setLineWrap(true);
		textAreaResultados.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textAreaResultados);
		scrollPane.setBounds(20, 80, 540, 166);
		contentPane.add(scrollPane);
		
		JLabel lblNumeroSocio = new JLabel("Numero Socio:");
		lblNumeroSocio.setBounds(20, 50, 120, 20);
		contentPane.add(lblNumeroSocio);
		
		numSocioBoxModel.removeAllElements();
		for (Integer unekoa : logicaNegocio.getNumeroSocios()) {
			numSocioBoxModel.addElement(unekoa);
		}
		comboBox.setBounds(150, 50, 177, 21);
		contentPane.add(comboBox);
		comboBox.setModel(numSocioBoxModel);
		comboBox.setSelectedIndex(0);
		
		actividadesComboBoxModel.removeAllElements();
		for (Actividad unekoa : logicaNegocio.getActividades()) {
			actividadesComboBoxModel.addElement(unekoa.getName() + ", " + unekoa.getGradoExigencia());
		}
		comboBox2.setBounds(147, 20, 180, 21);
		contentPane.add(comboBox2);
		comboBox2.setModel(actividadesComboBoxModel);
		comboBox2.setSelectedIndex(0);

		// Acción del botón
		botonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaResultados.setText("");
				try {
					
					String[] partes = ((String)comboBox2.getSelectedItem()).split(",");
					String nombre2 = partes[0];
					int gradoExigencia = Integer.parseInt(partes[1].trim());
					List<Sesion> sesiones = logicaNegocio.getSesiones(nombre2, gradoExigencia);

					if (sesiones.isEmpty()) {
						JOptionPane.showMessageDialog(ReservarSesion_GUI.this, "No se encontraron sesiones.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						return;
						//textAreaResultados.setText("No se encontraron sesiones.");
					} else {
						for (Sesion s : sesiones) {
							textAreaResultados.append(s.toString() + "\n");
						}
						
						String idSesionTexto = JOptionPane.showInputDialog(ReservarSesion_GUI.this, "Escribe el id de la sesión que quieres reservar:");					
						try {
							int numSocio = (Integer)comboBox.getSelectedItem();
							int idSesion = Integer.parseInt(idSesionTexto);
							//Control de errores, sesion
							boolean esta = false;
							for (Sesion sesion : sesiones) {
								if (sesion.getIdSesion() == idSesion) {
									esta = true;
									break;
								}
							}
							if (!esta) {
								JOptionPane.showMessageDialog(ReservarSesion_GUI.this, "Indique una sesion que este en la lista desplegada.", "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							
							
							int n = logicaNegocio.reservarSesion(numSocio, idSesion);	
							if (n == -1) {
								JOptionPane.showMessageDialog(ReservarSesion_GUI.this, "La sesion esta llena, se te ha añadido a la lista de espera.", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (n == -2) {
								JOptionPane.showMessageDialog(ReservarSesion_GUI.this, "Numero maximo de reservas alcanzado.", "Error", JOptionPane.ERROR_MESSAGE);
							} else if (n == -3) {
								JOptionPane.showMessageDialog(ReservarSesion_GUI.this, "El socio ya tiene una reserva de esta sesion", "Error", JOptionPane.ERROR_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(ReservarSesion_GUI.this, "El id de tu reserva es " + n, "Mensaje", JOptionPane.INFORMATION_MESSAGE);	
							}
							textAreaResultados.setText("");
							return;
						} catch (NumberFormatException e2) {
							return;
						}
					}	
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(ReservarSesion_GUI.this, "La intensidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
	}
}
