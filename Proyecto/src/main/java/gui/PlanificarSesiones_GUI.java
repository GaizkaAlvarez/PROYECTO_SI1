package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import businessLogic.BLFacade;
import domain.*;

public class PlanificarSesiones_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombreSala;
	private JTextArea textAreaResultados;

	private BLFacade logicaNegocio = MainGUI.getBusinessLogic();
	private JTextField textHoraInicio;
	private JTextField textHoraFinal;
	
	private DefaultComboBoxModel<String> actividadesComboBoxModel = new DefaultComboBoxModel<String>();
	private JComboBox<String> comboBox2 = new JComboBox<String>();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				PlanificarSesiones_GUI frame = new PlanificarSesiones_GUI();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public PlanificarSesiones_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 348);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNombreSala = new JLabel("Nombre Sala:");
		lblNombreSala.setBounds(20, 20, 120, 20);
		contentPane.add(lblNombreSala);

		textNombreSala = new JTextField();
		textNombreSala.setBounds(150, 20, 150, 25);
		contentPane.add(textNombreSala);

		JButton botonConsultar = new JButton("Consultar Sesiones");
		botonConsultar.setBounds(380, 17, 180, 30);
		contentPane.add(botonConsultar);

		textAreaResultados = new JTextArea();
		textAreaResultados.setEditable(false);
		textAreaResultados.setLineWrap(true);
		textAreaResultados.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(textAreaResultados);
		scrollPane.setBounds(20, 55, 540, 119);
		contentPane.add(scrollPane);
		
		JLabel lblNombreActividad = new JLabel("Actividad:");
		lblNombreActividad.setBounds(20, 184, 120, 20);
		contentPane.add(lblNombreActividad);
		
		JLabel lblHoraInicio = new JLabel("Hora Inicio: (int)");
		lblHoraInicio.setBounds(20, 214, 120, 20);
		contentPane.add(lblHoraInicio);
		
		JLabel lblHoraFinal = new JLabel("Hora Final: (int)");
		lblHoraFinal.setBounds(20, 251, 120, 20);
		contentPane.add(lblHoraFinal);
		
		textHoraInicio = new JTextField();
		textHoraInicio.setBounds(150, 214, 150, 25);
		contentPane.add(textHoraInicio);
		
		textHoraFinal = new JTextField();
		textHoraFinal.setBounds(150, 249, 150, 25);
		contentPane.add(textHoraFinal);
		
		JButton btnAñadirSesion = new JButton("Añadir Sesion");
		btnAñadirSesion.setBounds(363, 184, 197, 119);
		contentPane.add(btnAñadirSesion);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(20, 281, 78, 23);
		contentPane.add(lblFecha);
		
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(150, 284, 150, 20);
        contentPane.add(dateChooser);
        
        actividadesComboBoxModel.removeAllElements();
        for (Actividad unekoa : logicaNegocio.getActividades()) {
        	actividadesComboBoxModel.addElement(unekoa.getName() + ", " + unekoa.getGradoExigencia());
        }
        comboBox2.setBounds(150, 184, 150, 21);
        contentPane.add(comboBox2);
        comboBox2.setModel(actividadesComboBoxModel);
        comboBox2.setSelectedIndex(0);
        
        comboBox2.setVisible(false);
		dateChooser.setVisible(false);
		lblHoraFinal.setVisible(false);
		lblHoraInicio.setVisible(false);
		lblNombreActividad.setVisible(false);
		lblFecha.setVisible(false);
		btnAñadirSesion.setVisible(false);
		textHoraFinal.setVisible(false);
		textHoraInicio.setVisible(false);

		// Acción del botón
		botonConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaResultados.setText("");
				if (textNombreSala.getText().isEmpty()) {
					JOptionPane.showMessageDialog(
	                		PlanificarSesiones_GUI.this,
	                    "Debes rellenar todos los campos",
	                    "ERROR",
	                    JOptionPane.ERROR_MESSAGE
	                );
	                return;
				}
				
				if (!logicaNegocio.existeSala(textNombreSala.getText())) {
					JOptionPane.showMessageDialog(
	                		PlanificarSesiones_GUI.this,
	                    "La sala indicada no existe",
	                    "ERROR",
	                    JOptionPane.ERROR_MESSAGE
	                );
	                return;
				}
				
				List<Sesion> listaSesiones = logicaNegocio.getSesionesDeSala(textNombreSala.getText());
				if (listaSesiones.isEmpty()) {
					JOptionPane.showMessageDialog(
	                		PlanificarSesiones_GUI.this, 
	                		"No hay sesiones en la sala","ERROR",
	                		JOptionPane.ERROR_MESSAGE);
				} else {
					for (Sesion sesion : listaSesiones) {
						textAreaResultados.append(sesion.getActividad().getName() + " " + sesion.getActividad().getGradoExigencia() + ": " + sesion.toString() + "\n\n");
					}
					
					dateChooser.setVisible(true);
					lblHoraFinal.setVisible(true);
					lblHoraInicio.setVisible(true);
					lblNombreActividad.setVisible(true);
					lblFecha.setVisible(true);
					btnAñadirSesion.setVisible(true);
					textHoraFinal.setVisible(true);
					textHoraInicio.setVisible(true);
					comboBox2.setVisible(true);
				}
			}
		});
		
		btnAñadirSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				try {
					textAreaResultados.setText("");
					if (dateChooser.getDate() == null || textHoraInicio.getText().isEmpty() || textHoraFinal.getText().isEmpty()) {
						JOptionPane.showMessageDialog(
		                		PlanificarSesiones_GUI.this,
		                    "Debes rellenar todos los campos",
		                    "ERROR",
		                    JOptionPane.ERROR_MESSAGE
		                );
		                return;
					}
					
					Date fecha = dateChooser.getDate();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(fecha);

					int año = calendar.get(Calendar.YEAR);
					int mes = calendar.get(Calendar.MONTH) + 1;
					int dia = calendar.get(Calendar.DAY_OF_MONTH);
					int hIni = Integer.parseInt(textHoraInicio.getText());
					int hFin = Integer.parseInt(textHoraFinal.getText());
					
					//Control de errores, horas
					if(hIni < 7 || hIni > 21) {
						JOptionPane.showMessageDialog(PlanificarSesiones_GUI.this, "La hora de inicio debe estar entre 7 y 21.", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if(hFin > 22 || hFin < 8) {
						JOptionPane.showMessageDialog(PlanificarSesiones_GUI.this, "La hora de final debe estar entre 8 y 22.", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					if (hIni >= hFin || hFin-hIni!=1) {
						JOptionPane.showMessageDialog(PlanificarSesiones_GUI.this, "La hora del final debe ser mayor que la hora de inicio y las sesiones deben ser de una hora.", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}

					//Control de errores, fecha
					LocalDate date = LocalDate.now();
					if(año < date.getDayOfYear() || (año == date.getDayOfYear() && mes < date.getDayOfMonth()))  {
						JOptionPane.showMessageDialog(PlanificarSesiones_GUI.this, "Todavia no se puede viajar al pasado.", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;		
					}

					//No compruebo porque llegados a este punto no hay error 
					Date localDia = Date.from(LocalDate.of(año, mes, dia).atStartOfDay(ZoneId.systemDefault()).toInstant());
					Date horaIni = Date.from(LocalTime.of(hIni, 0).atDate(LocalDate.of(1970, 1, 1))  // usamos una fecha base
			                .atZone(ZoneId.systemDefault())
			                .toInstant()); 
					Date horaFinal = Date.from(LocalTime.of(hFin, 0).atDate(LocalDate.of(1970, 1, 1))  // usamos una fecha base
			                .atZone(ZoneId.systemDefault())
			                .toInstant()); 

					String[] partes = ((String)comboBox2.getSelectedItem()).split(",");
					String nombre2 = partes[0];
					int gradoExigencia = Integer.parseInt(partes[1].trim());
					
					switch (logicaNegocio.addSesionSala(textNombreSala.getText(), nombre2, gradoExigencia , localDia , horaIni, horaFinal)) {
					case 0: //Caso bueno
						JOptionPane.showMessageDialog(PlanificarSesiones_GUI.this, "Sesion añadida correctamente.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					case -1: //Caso lleno
						JOptionPane.showMessageDialog(PlanificarSesiones_GUI.this, "Sala llena.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;							
					case -2: //Caso ocupado
						JOptionPane.showMessageDialog(PlanificarSesiones_GUI.this, "Sala ocupada ese dia a esa hora.", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
						break;
					}
					return;
				} catch (Exception ex1) {
					JOptionPane.showMessageDialog(PlanificarSesiones_GUI.this, "El grado de exigencia y las horas deben ser numeros enteros.", "ERROR", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
	}
}