import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import java.awt.Component;

import java.awt.Color;
import javax.swing.SwingConstants;

/**
 * Classe offrant une interface graphique pour le menu principal
 * des employés
 * @author Benjamin Roy et Paul-Ulrich Nguimeya Demefack
 * @version 1.0
 */
public class MenuEmploye {

	private JFrame frameEmp;
	private JOptionPane confirmer;

	/**
	 * Constructeur qui initialize l'interface graphique
	 */
	public MenuEmploye() {
		initialize();
	}

	/**
	 * Méthode qui initialize l'interface graphique
	 */
	private void initialize() {
		frameEmp = new JFrame();
		frameEmp.setBounds(100, 100, 550, 411);
		frameEmp.getContentPane().setBackground(new Color(0, 191, 255));
		frameEmp.getContentPane().setLayout(null);
		frameEmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//////// Design //////////////////////////////////////////////////
		
		// panel for the main menu
		JPanel borderMP = new JPanel();
		borderMP.setBackground(new Color(0, 191, 255));
		borderMP.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 0, 0), new Color(0, 191, 255)),
						"Menu Principal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		borderMP.setBounds(10, 11, 514, 350);
		frameEmp.getContentPane().add(borderMP);
		borderMP.setLayout(null);
		
		JPanel panelMP = new JPanel();
		panelMP.setBounds(6, 15, 498, 324);
		borderMP.add(panelMP);
		panelMP.setBackground(new Color(0, 191, 255));
		panelMP.setLayout(null);
		
		
		// button for the client page
		JButton btnClient = new JButton("Gestions des clients");
		btnClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnClient.setBounds(29, 39, 181, 37);
		panelMP.add(btnClient);

		// button for the volunteers
		JButton btnVolunteers = new JButton("Gestions des Bénévoles");
		btnVolunteers.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnVolunteers.setBounds(29, 103, 181, 37);
		panelMP.add(btnVolunteers);
		
		JButton btnReservation = new JButton("Calendrier des rendez-vous");
		btnReservation.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnReservation.setBounds(243, 39, 232, 37);
		panelMP.add(btnReservation);
		
		JButton btnVaccProfile = new JButton("Gestion des profils de vaccination");
		btnVaccProfile.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnVaccProfile.setBounds(243, 103, 232, 37);
		panelMP.add(btnVaccProfile);
		
		JButton btnQuestionnaire = new JButton("Gestion des questionnaires");
		btnQuestionnaire.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnQuestionnaire.setBounds(29, 169, 181, 37);
		panelMP.add(btnQuestionnaire);
		
		
		JButton quit = new JButton("Quitter");
	    quit.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    quit.setBounds(376, 282, 112, 31);
	    panelMP.add(quit);
		
		///////////Action Listener /////////////////////////////////////
		
		// Actionperformed client managment
		btnClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameEmp.dispose();
							new UIClient();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		btnVolunteers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameEmp.dispose();
							new UIVolunteer(); 
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
							frameEmp.dispose();
							new ReservationCalendar();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		btnVaccProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameEmp.dispose();
							new UIVaccinationProfile();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		btnQuestionnaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frameEmp.dispose();
							new UIQuestionnaire();
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
				
		
		frameEmp.setVisible(true);
	}
}
