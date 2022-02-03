import java.awt.Color;
import java.awt.EventQueue;
import lib.*;

import javax.swing.JFrame;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import com.toedter.calendar.JDateChooser;

import Account.Employee;
import Account.LogInControler;
import csv.CsvReservation;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.JCheckBox;

/**
 * Classe offrant une interface graphique pour la gestion des rendez-vous
 * @author Paul-Ulrich Nguimeya Demefack
 * @version 1.0
 */
public class ReservationCalendar {

	private JFrame frmReservationCalendar;
	private JOptionPane confirm;
	protected int row;
	protected int column;
	protected boolean calendarInfo;
	private JTextField nameTextField;
	private JTextField surnameTextField;
	private JTextField textFieldMail;

	static DefaultTableModel dtm;
	private JTable table;

	/**
	 * Constructeur qui initialize l'interface graphique
	 */
	@SuppressWarnings("serial")
	public ReservationCalendar() {
		initialize();

		dtm = new DefaultTableModel(new Object[][] {},
				new String[] { "Numéro de Réservation", "Nom", "Prénom", "Dose", "Email" }){
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtm);
	}

	/**
	 * Méthode qui initialize l'interface graphique
	 */
	private void initialize() {
		frmReservationCalendar = new JFrame();
		frmReservationCalendar.setTitle("Calendrier des R\u00E9servations");
		frmReservationCalendar.setBounds(100, 100, 1110, 506);
		frmReservationCalendar.getContentPane().setBackground(new Color(0, 191, 255));
		frmReservationCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReservationCalendar.getContentPane().setLayout(null);
		frmReservationCalendar.setVisible(true);

		// Calendrier
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getJCalendar().getDayChooser().addDateEvaluator(new DateEvaluator());
		dateChooser.setBounds(90, 16, 117, 19);
		frmReservationCalendar.getContentPane().add(dateChooser);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");

		JLabel calendarLabel = new JLabel("Calendrier :");
		calendarLabel.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		calendarLabel.setBounds(10, 16, 77, 13);
		frmReservationCalendar.getContentPane().add(calendarLabel);

		JLabel timeSlotLabel = new JLabel("Plages Horaires :");
		timeSlotLabel.setFont(new Font("Times New Roman", Font.ITALIC, 12));
		timeSlotLabel.setBounds(245, 16, 105, 13);
		frmReservationCalendar.getContentPane().add(timeSlotLabel);

		JButton accessButton = new JButton("Acc\u00E9der");
		accessButton.setBounds(437, 16, 85, 21);
		frmReservationCalendar.getContentPane().add(accessButton);

		JButton quitButton = new JButton("Quitter");
		quitButton.setBounds(999, 432, 85, 21);
		frmReservationCalendar.getContentPane().add(quitButton);

		@SuppressWarnings("rawtypes")
		JComboBox time = new JComboBox();
		time.setEditable(false);
		time.setBounds(342, 16, 85, 21);
		frmReservationCalendar.getContentPane().add(time);

		// Nom
		JLabel lastnameLabel = new JLabel("Nom :");
		lastnameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lastnameLabel.setBounds(10, 84, 85, 13);
		frmReservationCalendar.getContentPane().add(lastnameLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(143, 81, 117, 19);
		frmReservationCalendar.getContentPane().add(nameTextField);
		nameTextField.setToolTipText("Veuillez entrer votre Nom");

		nameTextField.setColumns(10);

		// Prenom
		JLabel firstNameLabel = new JLabel("Prenom");
		firstNameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		firstNameLabel.setBounds(10, 120, 45, 13);
		frmReservationCalendar.getContentPane().add(firstNameLabel);

		surnameTextField = new JTextField();
		surnameTextField.setColumns(10);
		surnameTextField.setBounds(143, 117, 117, 19);
		frmReservationCalendar.getContentPane().add(surnameTextField);
		surnameTextField.setToolTipText("Veuillez entrer votre Prénom");

		// Retour
		JButton returnButton = new JButton("Retour");
		returnButton.setBounds(904, 432, 85, 21);
		frmReservationCalendar.getContentPane().add(returnButton);

		// Dose
		JRadioButton radio1 = new JRadioButton("1");
		radio1.setBounds(143, 203, 64, 21);
		frmReservationCalendar.getContentPane().add(radio1);

		JRadioButton radio2 = new JRadioButton("2");
		radio2.setBounds(214, 203, 65, 21);
		frmReservationCalendar.getContentPane().add(radio2);

		JLabel labelDose = new JLabel("Dose :");
		labelDose.setLabelFor(radio1);
		labelDose.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		labelDose.setBounds(10, 201, 45, 25);
		frmReservationCalendar.getContentPane().add(labelDose);

		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);

		// E-mail
		JLabel mailLabel = new JLabel("Adresse Courriel :");
		mailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mailLabel.setBounds(10, 162, 96, 13);
		frmReservationCalendar.getContentPane().add(mailLabel);

		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		textFieldMail.setBounds(143, 159, 117, 19);
		textFieldMail.setToolTipText("Veuillez entrer votre E-mail");

		frmReservationCalendar.getContentPane().add(textFieldMail);

		// visite spontane check box
		JCheckBox visitTypeCheckBox = new JCheckBox("Visite spontan\u00E9e");
		visitTypeCheckBox.setBounds(10, 244, 163, 23);
		frmReservationCalendar.getContentPane().add(visitTypeCheckBox);
		
		// Ajouter
		JButton addButton = new JButton("Ajouter");
		addButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		addButton.setBounds(10, 288, 85, 21);
		frmReservationCalendar.getContentPane().add(addButton);

		// Supprimer
		JButton cancelButton = new JButton("Annuler");
		cancelButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		cancelButton.setBounds(141, 288, 85, 21);
		frmReservationCalendar.getContentPane().add(cancelButton);

		// Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 77, 757, 318);
		frmReservationCalendar.getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		// initialisation de la liste d'heures
		int i = 0;
		for (i = 8; i <= 17; i++) {
			time.addItem((i < 10 ? "0" + i : i) + ":00"); // HH:mm
		}
		time.setSelectedItem(0);
		
		//Ajout d'une date par défaut valide dans le dateChooser
		Date defaultDate = new Date();
		dateChooser.setDate(defaultDate);
		if(defaultDate.getDay() == 0) {
			Calendar c = dateChooser.getCalendar();
			c.add(Calendar.DAY_OF_MONTH, 1);
			dateChooser.setDate(c.getTime());
		}
		if(defaultDate.getDay() == 6) {
			Calendar c = dateChooser.getCalendar();
			c.add(Calendar.DAY_OF_MONTH, 2);
			dateChooser.setDate(c.getTime());
		}
		

		// Actionperformed Ajouter //
		addButton.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Date reservationDate = dateChooser.getDate();
				String temps = time.getSelectedItem().toString();

				String dose = "";
				String firstName = surnameTextField.getText(), email = textFieldMail.getText(),
						lastName = nameTextField.getText();

				boolean state = true;

				// dose
				if (radio1.isSelected()) {
					state = true;
					dose = radio1.getActionCommand();
					labelDose.setForeground(Color.black);
				} else if (radio2.isSelected()) {
					state = true;
					labelDose.setForeground(Color.black);
					dose = radio2.getActionCommand();
				} else {
					labelDose.setForeground(Color.red);
					state = false;
				}

				// Lastname
				lastnameLabel.setForeground(lastName.equals("") ? Color.red : Color.black);
				state = !lastName.equals("") && state;

				// firstname
				firstNameLabel.setForeground(firstName.equals("") ? Color.red : Color.black);
				state = !firstName.equals("") && state;

				// email
				mailLabel.setForeground(email.equals("") ? Color.red : Color.black);
				state = !email.equals("") && state;

				if (state) {
					try {
						//Vérifier qu'il reste de la place
						String date = df.format(reservationDate);
						List<Reservation> reservations = ReservationControler.getReservations(date, temps);
						if(reservations.size() >= 15) {
							throw new Error("Il n'y a plus de place disponible pour cette date");
						}
						reservationDate.setHours(time.getSelectedIndex() + 8);
						boolean isSpontaneousVisit = !visitTypeCheckBox.isSelected();
						if(isSpontaneousVisit) {
							//Vérifier que le rendez-vous est pris 72 heures à l'avance
							Calendar c = Calendar.getInstance();
							c.add(Calendar.DAY_OF_MONTH, 3);
							if(reservationDate.before(c.getTime())) {
								throw new Error("Le rendez-vous doit être pris 72 heure à l'avance");
							}
						}
						else {
							if(reservationDate.before(new Date())) {
								throw new Error("Le rendez-vous ne peut pas être pris avant aujourd'hui");
							}
						}
						Reservation reserv = new Reservation(firstName, lastName, date, temps,
								Integer.parseInt(dose), email);
						boolean created = ReservationControler.addReservation(reserv);

						if (created) {
							JOptionPane.showConfirmDialog(null, "Reservation créée avec succès !\n" + reserv.sendNotification(), "Confirmation",
									JOptionPane.PLAIN_MESSAGE);

							reservationDetails(df.format(dateChooser.getDate()), time.getSelectedItem().toString());

							try {
								CsvReservation.addOneReservation(".\\csv\\RSVPDatabase.csv", reserv);
							} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showConfirmDialog(null, "RDV n'a pas pu être créé, une erreur est survenu",
									"RDV non effectué", JOptionPane.PLAIN_MESSAGE);

						}
					} catch (Error err) {
						JOptionPane.showConfirmDialog(null, err.getMessage(), "Corriger les erreurs suivantes :",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
			}

		});

		accessButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {

					public void run() {

						try {
							if (time.getSelectedItem() != null) {

								reservationDetails(df.format(dateChooser.getDate()), time.getSelectedItem().toString());

							} else {
								confirm.showConfirmDialog(null, "Choisissez une Date et une Plage Horaire", "Message",
										JOptionPane.PLAIN_MESSAGE);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});
			}

		});

		nameTextField.setText("");
		surnameTextField.setText("");

		// Actionperformed Retour //
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frmReservationCalendar.dispose();
							if(LogInControler.getLoggedAgent() instanceof Employee) {
								new MenuEmploye();
							}
							else {
								new MenuVolunteer();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		// Actionperformed Quitter //
		quitButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							int resultat = confirm.showConfirmDialog(null, "Voulez vous Vraiment quitter ?", "Quitter",
									JOptionPane.OK_CANCEL_OPTION);
							if (resultat == 0) { // reponse oui
								System.exit(0);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});

		// Actionperformed Supprimer //
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Supprimer la Réservation ?", "Supprimer",
						JOptionPane.YES_NO_OPTION);
				if (choice == 0) {
					String reservationNumber = dtm.getValueAt(row, 0).toString();
					boolean deleted = ReservationControler.cancelReservation(reservationNumber);
					if (deleted) {
						dtm.removeRow(row);
						// remove from csv
						try {
							CsvReservation.removeOneReservation(".\\csv\\RSVPDatabase.csv", reservationNumber);
						} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | CsvValidationException
								| IOException | ParseException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showConfirmDialog(null,
								"La Reservation n'a pas pu être supprimée, une erreur est survenu",
								"Suppression de réservation non effectuée", JOptionPane.PLAIN_MESSAGE);
					}
					reservationDetails(df.format(dateChooser.getDate()), time.getSelectedItem().toString());
				}
			}
		});

	}

	/**
	 * Méthode qui met à jour les informations des réservations du tableau
	 * en fonction de la date et l'heure choisi
	 * @param date Date des rendez-vous
	 * @param time Heure des rendez-vous
	 */
	public void reservationDetails(String date, String time) {
		List<Reservation> reservationList = ReservationControler.getReservations(date, time);
		dtm.setRowCount(0);
		for (int i = 0; i < reservationList.size(); i++) {
			Object[] obj = { 
					reservationList.get(i).getReservationNumber(), 
					reservationList.get(i).getLastName(),
					reservationList.get(i).getFirstName(), 
					reservationList.get(i).getDose(),
					reservationList.get(i).getEmail() 
			};
			dtm.addRow(obj);
		}
	}
}
