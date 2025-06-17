package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import businessLogic.BLFacade;
import domain.*;

public class ConsultarSesiones_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea textAreaResultados;

	private DefaultComboBoxModel<String> actividadesComboBoxModel = new DefaultComboBoxModel<String>();
	private JComboBox<String> comboBox = new JComboBox<String>();
	
	private BLFacade logicaNegocio = MainGUI.getBusinessLogic();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try { 
				ConsultarSesiones_GUI frame = new ConsultarSesiones_GUI();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ConsultarSesiones_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNombreActividad = new JLabel("Actividad:");
		lblNombreActividad.setBounds(20, 20, 120, 20);
		contentPane.add(lblNombreActividad);

		JButton botonConsultar = new JButton("Consultar sesiones");
		botonConsultar.setBounds(362, 15, 180, 25);
		contentPane.add(botonConsultar);

		textAreaResultados = new JTextArea();
		textAreaResultados.setEditable(false);
		textAreaResultados.setLineWrap(true);
		textAreaResultados.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textAreaResultados);
		scrollPane.setBounds(20, 50, 540, 280);
		contentPane.add(scrollPane);
		
		actividadesComboBoxModel.removeAllElements();
		for (Actividad unekoa : logicaNegocio.getActividades()) {
			actividadesComboBoxModel.addElement(unekoa.getName() + ", " + unekoa.getGradoExigencia());
		}
		comboBox.setBounds(147, 20, 180, 21);
		contentPane.add(comboBox);
		comboBox.setModel(actividadesComboBoxModel);
		comboBox.setSelectedIndex(0);

		// Acción del botón
		botonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaResultados.setText("");
				try {
			        String[] partes = ((String)comboBox.getSelectedItem()).split(",");
			        String nombre2 = partes[0];
			        int numero = Integer.parseInt(partes[1].trim());
					List<Sesion> sesiones = logicaNegocio.getSesiones(nombre2, numero);
					
					textAreaResultados.setText("");

					if (sesiones.isEmpty()) {
						textAreaResultados.setText("No se encontraron sesiones.");
					} else {
						for (Sesion s : sesiones) {
							textAreaResultados.append(s.toString() + "\n");
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(ConsultarSesiones_GUI.this, "La intensidad debe ser un número entero.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
	}
}
