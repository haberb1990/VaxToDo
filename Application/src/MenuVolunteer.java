import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * Classe offrant une interface graphique pour le menu principal
 * des bénévoles
 * @author Hani Berchan et Paul-Ulrich Nguimeya Demefack
 * @version 1.0
 */
public class MenuVolunteer {

	private JFrame frameVolunteer;
	private JOptionPane confirmer;

	/**
	 * Constructeur qui initialize l'interface graphique
	 */
	public MenuVolunteer() {
		initialize();
	}

	/**
	 * Méthode qui initialize l'interface graphique
	 */
	private void initialize() {
		frameVolunteer = new JFrame();
		frameVolunteer.setBounds(100, 100, 532, 295);
		frameVolunteer.getContentPane().setBackground(new Color(0, 191, 255));
		frameVolunteer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameVolunteer.setTitle("Menu  B\u00E9n\u00E9vole");
		
		
		// panel for the main menu
				JPanel borderMP = new JPanel();
				borderMP.setBackground(new Color(0, 191, 255));
				borderMP.setBorder(
						new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 0, 0), new Color(0, 191, 255)),
								" Options ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				borderMP.setBounds(10, 11, 514, 350);
				frameVolunteer.getContentPane().add(borderMP);
				borderMP.setLayout(null);
				
				JPanel panelMP = new JPanel();
				panelMP.setBounds(6, 15, 498, 232);
				borderMP.add(panelMP);
				panelMP.setBackground(new Color(0,191,255));
				panelMP.setLayout(null);
				
				
				// button for the client page
				JButton btnClient = new JButton("Gestions des clients");
				btnClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
				btnClient.setBounds(29, 39, 181, 37);
				panelMP.add(btnClient);
				
				JButton btnReservation = new JButton("Calendrier des rendez-vous");
				btnReservation.setFont(new Font("Times New Roman", Font.BOLD, 12));
				btnReservation.setBounds(242, 39, 232, 37);
				panelMP.add(btnReservation);
				
				
				JButton quit = new JButton("Quitter");
			    quit.setFont(new Font("Times New Roman", Font.BOLD, 12));
			    quit.setBounds(376, 172, 112, 31);
			    panelMP.add(quit);
				
				///////////Action Listener /////////////////////////////////////
				
				// Actionperformed client managment
				btnClient.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									frameVolunteer.dispose();
									new UIClientReadOnly();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				});
				
				btnReservation.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									frameVolunteer.dispose();
									new ReservationCalendar();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				});
				
				quit.addActionListener(new ActionListener() {
					@SuppressWarnings("static-access")
					public void actionPerformed(ActionEvent e) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									int resultat = confirmer.showConfirmDialog(null, "Voulez vous Vraiment quitter ?",
											"Quitter", JOptionPane.OK_CANCEL_OPTION);
									if (resultat == 0) {
										System.exit(0);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});

					}
				});
		
		
		
		frameVolunteer.setVisible(true);
	}

}
