import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Account.*;
import csv.CsvVolunteer;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import com.toedter.calendar.JDateChooser;

import javax.swing.JTable;
import javax.swing.JScrollPane;

/**
 * Classe offrant une interface graphique pour la gestion des bénévoles
 * @author Hani Berchan
 * @version 1.0
 */
public class UIVolunteer {

	// variables pour le tableau
	int row;
	int column;
	DefaultTableModel dtm;
	private JTable table;
	private JFrame frmVolunteer;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldPhone;
	private JTextField textFieldMail;
	private JTextField textFieldAdresse;
	private JTextField textFieldCodeP;
	private JTextField textFieldVille;
	private JOptionPane confirmer;
	private JTextField textFieldPassword;
	private JTextField searchField;

	/**
	 * Constructeur qui initialize l'interface graphique
	 */
	@SuppressWarnings("serial")
	public UIVolunteer() {
		initialize();

		dtm = new DefaultTableModel(new Object[][] {}, new String[] { "Numero de compte", "Prenom", "Nom",
				"Naissance", "Email", "Telephone", "Adresse", "Code Postal", "Ville" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(dtm);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(140);

		if (VolunteerControler.getVolunteers().size() > 0)
			personDetails("");

	}

	/**
	 * Méthode qui met à jour les informations des bénévoles du tableau
	 * @param mot String qui filtre le tableau en fonction du nom ou prénom du bénévole
	 */
	private void personDetails(String mot) {
		dtm.setRowCount(0);
		List<Volunteer> listVolunteer = VolunteerControler.getVolunteers();
		if (mot.length() == 0) {
			for (int i = 0; i < listVolunteer.size(); i++) {
				Object[] obj = { 
					listVolunteer.get(i).getAccountNumber(),
					listVolunteer.get(i).getFirstName(),
					listVolunteer.get(i).getLastName(),
					listVolunteer.get(i).getBirthDate(),
					listVolunteer.get(i).getEmail(),
					listVolunteer.get(i).getPhone(),
					listVolunteer.get(i).getAddress(),
					listVolunteer.get(i).getZipCode(),
					listVolunteer.get(i).getCity() 
				};
				dtm.addRow(obj);
			}
		} else {
			for (int i = 0; i < listVolunteer.size(); i++) {
				if (listVolunteer.get(i).getAccountNumber().contains(mot)) {
					Object[] obj = { 
						listVolunteer.get(i).getAccountNumber(),
						listVolunteer.get(i).getFirstName(),
						listVolunteer.get(i).getLastName(),
						listVolunteer.get(i).getBirthDate(),
						listVolunteer.get(i).getEmail(),
						listVolunteer.get(i).getPhone(),
						listVolunteer.get(i).getAddress(),
						listVolunteer.get(i).getZipCode(),
						listVolunteer.get(i).getCity() 
					};
					dtm.addRow(obj);
				}
			}
		}

	}

	/**
	 * Méthode qui initialize l'interface graphique
	 */
	private void initialize() {
		frmVolunteer = new JFrame();
		frmVolunteer.getContentPane().setBackground(new Color(0, 191, 255));
		frmVolunteer.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 10));
		frmVolunteer.setTitle("Gestion des Benevoles");
		frmVolunteer.setBounds(100, 100, 1207, 553);
		frmVolunteer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVolunteer.getContentPane().setLayout(null);

		// Ajouter
		JButton addButton = new JButton("Ajouter");
		addButton.setBackground(Color.GREEN);
		addButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		addButton.setBounds(10, 369, 85, 21);
		frmVolunteer.getContentPane().add(addButton);

		// Modifier
		JButton modifyButton = new JButton("Modifier");
		modifyButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		modifyButton.setBounds(10, 419, 85, 21);
		frmVolunteer.getContentPane().add(modifyButton);

		// Supprimer
		JButton deleteButton = new JButton("Supprimer");
		deleteButton.setBackground(Color.RED);
		deleteButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		deleteButton.setBounds(10, 469, 85, 21);
		frmVolunteer.getContentPane().add(deleteButton);

		// button to refresh table
		JButton refresh = new JButton("Rafraichir");
		refresh.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		refresh.setBounds(1096, 382, 85, 21);
		frmVolunteer.getContentPane().add(refresh);

		// Nom
		JLabel lastnameLabel = new JLabel("Nom :");
		lastnameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lastnameLabel.setBounds(10, 50, 85, 13);
		frmVolunteer.getContentPane().add(lastnameLabel);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(148, 47, 117, 19);
		frmVolunteer.getContentPane().add(textFieldNom);
		textFieldNom.setToolTipText("Veuillez entrer votre Nom");

		textFieldNom.setColumns(10);

		// Prenom
		JLabel surnameLabel = new JLabel("Prenom :");
		surnameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		surnameLabel.setBounds(10, 83, 45, 13);
		frmVolunteer.getContentPane().add(surnameLabel);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(148, 80, 117, 19);
		frmVolunteer.getContentPane().add(textFieldPrenom);
		textFieldPrenom.setToolTipText("Veuillez entrer votre Prénom");

		// Calendrier
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(148, 122, 117, 19);
		frmVolunteer.getContentPane().add(dateChooser);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		dateChooser.setToolTipText("Veuillez choisir une Date");

		JLabel birthDateLabel = new JLabel("Date De Naissance :");
		birthDateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		birthDateLabel.setBounds(10, 122, 100, 13);
		frmVolunteer.getContentPane().add(birthDateLabel);

		// E-mail
		JLabel mailLabel = new JLabel("Adresse Courriel :");
		mailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		mailLabel.setBounds(10, 158, 96, 13);
		frmVolunteer.getContentPane().add(mailLabel);

		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		textFieldMail.setBounds(148, 155, 117, 19);
		textFieldMail.setToolTipText("Veuillez entrer votre E-mail");

		frmVolunteer.getContentPane().add(textFieldMail);

		// button to find volunteer
		JButton findV = new JButton("Rechercher");
		findV.setFont(new Font("Times New Roman", Font.BOLD, 10));
		findV.setBounds(741, 423, 103, 25);
		frmVolunteer.getContentPane().add(findV);
						
		searchField = new JTextField();
		searchField.setToolTipText("rechercher le bénévole ici...");
		searchField.setBounds(508, 422, 175, 25);
		frmVolunteer.getContentPane().add(searchField);
		searchField.setColumns(10);
				
		// Boutton Quitter
		JButton quitButton = new JButton("Quitter");
		quitButton.setBounds(1096, 482, 85, 21);
		frmVolunteer.getContentPane().add(quitButton);

		// Boutton Retour
		JButton returnButton = new JButton("Retour");
		returnButton.setBounds(988, 482, 85, 21);
		frmVolunteer.getContentPane().add(returnButton);

		// Telephone
		JLabel phoneLabel = new JLabel("Numero de telephone :");
		phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		phoneLabel.setBounds(10, 197, 117, 13);
		frmVolunteer.getContentPane().add(phoneLabel);

		textFieldPhone = new JTextField();
		textFieldPhone.setColumns(10);
		textFieldPhone.setBounds(148, 194, 117, 19);
		textFieldPhone.setToolTipText("Veuillez entrer votre Numéro de Téléphone");

		textFieldPhone.addKeyListener((KeyListener) new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				char c = ke.getKeyChar();

				// condition pour entrer uniquement des chiffres
				if (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE
						|| c == KeyEvent.VK_DECIMAL || c == KeyEvent.VK_PERIOD) {
					textFieldPhone.setEditable(true);
					phoneLabel.setText("Numéro de Telephone :");
					phoneLabel.setForeground(Color.black);

				} else {
					textFieldPhone.setEditable(false);
					phoneLabel.setText("valeurs numeriques seulement!!");
					phoneLabel.setForeground(Color.red);
				}
			}
		});

		frmVolunteer.getContentPane().add(textFieldPhone);

		// Adresse
		JLabel adressLabel = new JLabel("Adresse (Num,Rue) :");
		adressLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		adressLabel.setBounds(10, 236, 117, 13);
		frmVolunteer.getContentPane().add(adressLabel);

		textFieldAdresse = new JTextField();
		textFieldAdresse.setBounds(148, 233, 117, 19);
		frmVolunteer.getContentPane().add(textFieldAdresse);
		textFieldAdresse.setToolTipText("Veuillez entrer votre Adresse");

		textFieldAdresse.setColumns(10);

		// Code Postal
		JLabel postalLabel = new JLabel("Code Postal :");
		postalLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		postalLabel.setBounds(10, 271, 100, 13);
		frmVolunteer.getContentPane().add(postalLabel);

		textFieldCodeP = new JTextField();
		textFieldCodeP.setBounds(148, 268, 117, 19);
		textFieldCodeP.setToolTipText("Veuillez entrer votre Code Postal");

		frmVolunteer.getContentPane().add(textFieldCodeP);
		textFieldCodeP.setColumns(10);

		// Ville
		JLabel villeLabel = new JLabel("Ville :");
		villeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		villeLabel.setBounds(10, 304, 45, 13);
		frmVolunteer.getContentPane().add(villeLabel);

		textFieldVille = new JTextField();
		textFieldVille.setBounds(148, 301, 117, 19);
		frmVolunteer.getContentPane().add(textFieldVille);
		textFieldVille.setToolTipText("Veuillez entrer votre Ville de résidence");

		textFieldVille.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(294, 49, 887, 322);
		frmVolunteer.getContentPane().add(scrollPane);

		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(148, 331, 117, 20);
		frmVolunteer.getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		passwordLabel.setLabelFor(textFieldPassword);
		passwordLabel.setBounds(10, 334, 85, 14);
		frmVolunteer.getContentPane().add(passwordLabel);

		// Tableau
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		frmVolunteer.setVisible(true);

		// Utiliser la souris pour click sur une ligne //

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				column = table.getSelectedColumn();
				textFieldNom.setText(dtm.getValueAt(row, 2).toString());
				textFieldPrenom.setText(dtm.getValueAt(row, 1).toString());
				textFieldMail.setText(dtm.getValueAt(row, 4).toString());
				textFieldPhone.setText(dtm.getValueAt(row, 5).toString());
				textFieldAdresse.setText(dtm.getValueAt(row, 6).toString());
				textFieldVille.setText(dtm.getValueAt(row, 8).toString());
				textFieldCodeP.setText(dtm.getValueAt(row, 7).toString());

				try {
					dateChooser.setDate(df.parse(dtm.getValueAt(row, 3).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);

		///// ActionPerformed Ajouter /////
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lastName = textFieldNom.getText(), firstName = textFieldPrenom.getText(),
						email = textFieldMail.getText(), phoneNum = textFieldPhone.getText(),
						address = textFieldAdresse.getText(), zipCode = textFieldCodeP.getText(),
						city = textFieldVille.getText(), password = textFieldPassword.getText();
				Date birthDate = dateChooser.getDate();
				boolean state = true;
				// nom
				lastnameLabel.setForeground(lastName.equals("") ? Color.red : Color.black);
				state = !lastName.equals("") && state;

				// prenom
				surnameLabel.setForeground(firstName.equals("") ? Color.red : Color.black);
				state = !firstName.equals("") && state;

				// date de naissance
				birthDateLabel.setForeground(birthDate == null ? Color.red : Color.black);
				state = !(birthDate == null) && state;

				// email
				mailLabel.setForeground(email.equals("") ? Color.red : Color.black);
				state = !email.equals("") && state;

				// numéro de téléphone
				boolean isValidPhone = !phoneNum.equals("") && phoneNum.length() <= 10;
				phoneLabel.setForeground(!isValidPhone ? Color.red : Color.black);
				state = isValidPhone && state;

				// Addresse
				adressLabel.setForeground(address.equals("") ? Color.red : Color.black);
				state = !address.equals("") && state;

				// ville
				villeLabel.setForeground(city.equals("") ? Color.red : Color.black);
				state = !city.equals("") && state;

				// Code Postal
				postalLabel.setForeground(zipCode.equals("") ? Color.red : Color.black);
				state = !zipCode.equals("") && state;

				// password
				passwordLabel.setForeground(password.equals("") ? Color.red : Color.black);
				state = !password.equals("") && state;

				if (state) { // la saisie est juste
					try {
						Volunteer account = new Volunteer(firstName, lastName, df.format(birthDate), email, phoneNum,
								address, zipCode, city, password);
						boolean created = VolunteerControler.addVolunteer(account);
						if (created) {
							textFieldNom.setText("");
							textFieldPrenom.setText("");
							dateChooser.setDate(null);
							textFieldPassword.setText("");	
							textFieldMail.setText("");
							textFieldAdresse.setText("");
							textFieldPhone.setText("");
							textFieldVille.setText("");
							textFieldCodeP.setText("");
							String message = account.sendAccountNumber() + "\nNuméro de compte: "
									+ account.getAccountNumber();
							JOptionPane.showConfirmDialog(null, message, "Compte créé avec succès :",
									JOptionPane.PLAIN_MESSAGE);
							try {
								CsvVolunteer.addOneAccount(".\\csv\\volunteerDatabase.csv", VolunteerControler
										.getVolunteers().get(VolunteerControler.getVolunteers().size() - 1));
							} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e1) {
								e1.printStackTrace();
								JOptionPane.showConfirmDialog(null, "Erreur", "Une erreur est survenu",
										JOptionPane.PLAIN_MESSAGE);
							}
						} else {
							JOptionPane.showConfirmDialog(null,
									"Le compte n'a pas pu être créé, une erreur est survenu",
									"Création de compte non effectuer", JOptionPane.PLAIN_MESSAGE);
						}
					} catch (Error err) {
						JOptionPane.showConfirmDialog(null, err.getMessage(), "Corriger les erreurs suivantes :",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
				personDetails("");
			}
		});

		// Actionperformed Retour //
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frmVolunteer.dispose();
							new MenuEmploye();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		// Actionperformed Modifier //
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lastName = textFieldNom.getText(), firstName = textFieldPrenom.getText(),
						email = textFieldMail.getText(), phoneNum = textFieldPhone.getText(),
						address = textFieldAdresse.getText(), zipCode = textFieldCodeP.getText(),
						city = textFieldVille.getText();
				Date birthDate = dateChooser.getDate();
				boolean state = true;
				// nom
				lastnameLabel.setForeground(lastName.equals("") ? Color.red : Color.black);
				state = !lastName.equals("") && state;

				// prenom
				surnameLabel.setForeground(firstName.equals("") ? Color.red : Color.black);
				state = !firstName.equals("") && state;

				// date de naissance
				birthDateLabel.setForeground(birthDate == null ? Color.red : Color.black);
				state = !(birthDate == null) && state;

				// email
				mailLabel.setForeground(email.equals("") ? Color.red : Color.black);
				state = !email.equals("") && state;

				// numéro de téléphone
				boolean isValidPhone = !phoneNum.equals("") && phoneNum.length() <= 10;
				phoneLabel.setForeground(!isValidPhone ? Color.red : Color.black);
				state = isValidPhone && state;

				// Addresse
				adressLabel.setForeground(address.equals("") ? Color.red : Color.black);
				state = !address.equals("") && state;

				// ville
				villeLabel.setForeground(city.equals("") ? Color.red : Color.black);
				state = !city.equals("") && state;

				// Code Postal
				postalLabel.setForeground(zipCode.equals("") ? Color.red : Color.black);
				state = !zipCode.equals("") && state;

				// Modification dans la liste et csv
				if (state) {
					String accountNumber = dtm.getValueAt(row, 0).toString();
					boolean modified = VolunteerControler.modifyVolunteer(accountNumber, firstName, lastName,
							df.format(birthDate), email, phoneNum, address, zipCode, city);
					if (modified) {
						textFieldNom.setText("");
						textFieldPrenom.setText("");
						dateChooser.setDate(null);
						textFieldPassword.setText("");	
						textFieldMail.setText("");
						textFieldAdresse.setText("");
						textFieldPhone.setText("");
						textFieldVille.setText("");
						textFieldCodeP.setText("");
						try {
							CsvVolunteer.writeVolunteer(".\\csv\\volunteerDatabase.csv",
									VolunteerControler.getVolunteers());
						} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | CsvValidationException
								| IOException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showConfirmDialog(null, "Le compte n'a pas pu être modifié, une erreur est survenu",
								"Modification de compte non effectuer", JOptionPane.PLAIN_MESSAGE);
					}
				}
				personDetails("");
			}
		});

		// Actionperformed Supprimer //
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Supprimer le bénévole ?", "Supprimer",
						JOptionPane.YES_NO_OPTION);
				if (choice == 0) {
					String accountNumber = dtm.getValueAt(row, 0).toString();
					boolean deleted = VolunteerControler.deleteVolunteer(accountNumber);
					if (deleted) {
						dtm.removeRow(row);
						textFieldNom.setText("");
						textFieldPrenom.setText("");
						dateChooser.setDate(null);
						textFieldPassword.setText("");	
						textFieldMail.setText("");
						textFieldAdresse.setText("");
						textFieldPhone.setText("");
						textFieldVille.setText("");
						textFieldCodeP.setText("");
						// remove from csv
						try {
							CsvVolunteer.removeOneVolunteer(".\\csv\\volunteerDatabase.csv", accountNumber);
						} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | CsvValidationException
								| IOException | ParseException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showConfirmDialog(null,
								"Le compte n'a pas pu être supprimé, une erreur est survenu",
								"Suppression de compte non effectuer", JOptionPane.PLAIN_MESSAGE);
					}
					personDetails("");
				}
			}
		});
///////////////////////////////////////////////////////////////////////	
//corriger le bug qui est que le bouton supprimer realise quand meme l'action meme si personne n'est selectionné
///////////////////////////////////////////////////////////////////////	

		
		// ActionPerformed Rafraichir //
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personDetails("");
				textFieldNom.setText("");
				textFieldPrenom.setText("");
				dateChooser.setDate(null);
				textFieldPassword.setText("");	
				textFieldMail.setText("");
				textFieldAdresse.setText("");
				textFieldPhone.setText("");
				textFieldVille.setText("");
				textFieldCodeP.setText("");

			}
		});
////////////////////////////////////////////////////////////////////////
//Action performed find client
///////////////////////////////////////////////////////////////////////				

		findV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				personDetails(searchField.getText());
			}
		});
		
		// Actionperformed Quitter //
		quitButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							int resultat = confirmer.showConfirmDialog(null, "Voulez vous Vraiment quitter ?",
									"Quitter", JOptionPane.OK_CANCEL_OPTION);
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
	}
}
