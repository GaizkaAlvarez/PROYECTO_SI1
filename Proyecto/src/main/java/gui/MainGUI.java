package gui;

import javax.swing.*;

import businessLogic.BLFacade;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	private JLabel lblQUESOy;
	
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		
		// this.setSize(271, 295);
		this.setSize(554, 346);
		
		jContentPane = new JPanel();
//		jContentPane.setLayout(new GridLayout(4, 1, 0, 0));
		
		
		setContentPane(jContentPane);
		jContentPane.setLayout(null);
		
		lblQUESOy = new JLabel("¿Qué eres?"); //$NON-NLS-1$ //$NON-NLS-2$
		lblQUESOy.setBounds(254, 10, 170, 13);
		jContentPane.add(lblQUESOy);
		
		JButton btnSocio = new JButton("SOCIO"); //$NON-NLS-1$ //$NON-NLS-2$
		btnSocio.setBounds(10, 33, 520, 89);
		jContentPane.add(btnSocio);
		
		JButton btnEncargado = new JButton("ENCARGADO");
		btnEncargado.setBounds(10, 121, 520, 89);
		jContentPane.add(btnEncargado);
		
		JButton btnOtro = new JButton("OTRO");
		btnOtro.setBounds(10, 209, 520, 89);
		jContentPane.add(btnOtro);
		
		btnOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame consultarSesiones_GUI = new ConsultarSesiones_GUI();
				consultarSesiones_GUI.setVisible(true);
			}
		});
		
		btnEncargado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame consultarSesiones_GUI2 = new Encargado_GUI();
				consultarSesiones_GUI2.setVisible(true);
			}
		});
		
		btnSocio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame consultarSesiones_GUI3 = new Socio_GUI();
				consultarSesiones_GUI3.setVisible(true);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
	}
} // @jve:decl-index=0:visual-constraint="0,0"

