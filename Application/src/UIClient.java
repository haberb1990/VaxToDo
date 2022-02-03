import java.awt.Color; 
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import Account.Client;
import Account.ClientControler;
import csv.CsvClient;
import csv.CsvQuestionnaire;
import csv.CsvVaccinationProfile;
import lib.QuestionnaireControler;
import lib.VaccinationProfileControler;


/**
 * Classe offrant une interface graphique pour la gestion des clients.
 * Pour les utilisateurs employé.
 * @author Hani Berchan
 * @version 1.0
 */
public class UIClient {

	private JFrame frameClient;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldEmail;
	private JTextField textFieldPhone;
	private JTextField searchField;
	private JTextField textFieldZipcode;
	private JTextField textFieldCity;
	private JTextField textFieldAdress;
	private JOptionPane confirmer;

	// variable for table
	int row;
	static DefaultTableModel dtm;
	private JTable table;
	
	/**
	 * Constructeur qui initialize l'interface graphique
	 */
	@SuppressWarnings("serial")
	public UIClient() {
		initialize();
		
		dtm = new DefaultTableModel(new Object[][] {},
				new String[] {"Numéro de compte" ,"Prénom", "Nom", "Naissance", "Adresse Courriel", "Téléphone" ,"Adresse" ,"Code Postal", "Ville"})

		{
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class, String.class,
					String.class,String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table.setModel(dtm);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().getColumn(8).setPreferredWidth(50);
		
		if(ClientControler.getClients().size() > 0) {
			showDetails("");
		}
	}
		
	/**
	 * Méthode qui met à jour les informations des bénévoles du tableau
	 * @param mot String qui filtre le tableau en fonction du numéro de 
	 * compte, du email, ou de la date de naissance du client
	 */
	public static void showDetails(String mot) {
		dtm.setRowCount(0);
		List<Client> listClient = ClientControler.getClients();
		if (mot.length() == 0) {
			dtm.setRowCount(0);
			for (int i = 0; i < listClient.size(); i++) {
				Object[] obj = { 
					listClient.get(i).getAccountNumber(),listClient.get(i).getFirstName(),
					listClient.get(i).getLastName(), listClient.get(i).getBirthDate(),
					listClient.get(i).getEmail(), listClient.get(i).getPhone(),listClient.get(i).getAddress(),
					listClient.get(i).getZipCode(),listClient.get(i).getCity()
				};
				dtm.addRow(obj);
			}
		}
		else {
			for (int i = 0; i < listClient.size(); i++) {
				if (listClient.get(i).getAccountNumber().contains(mot) || 
						listClient.get(i).getEmail().contains(mot) ||
						listClient.get(i).getBirthDate().contains(mot)) {
					Object[] obj = {
						listClient.get(i).getAccountNumber(),listClient.get(i).getFirstName(),
						listClient.get(i).getLastName(), listClient.get(i).getBirthDate(),
						listClient.get(i).getEmail(), listClient.get(i).getPhone(),listClient.get(i).getAddress(),
						listClient.get(i).getZipCode(),listClient.get(i).getCity()
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
		frameClient = new JFrame("Gestion Clients");
		frameClient.setBounds(150, 150, 1365, 605);
		frameClient.getContentPane().setBackground(new Color(0, 191, 255));
		frameClient.getContentPane().setLayout(null);
		frameClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// client name
		textFieldFirstName = new JTextField();
		textFieldFirstName.setToolTipText("Veuillez entrer le nom du client");
		textFieldFirstName.setBounds(179, 51, 167, 25);
		frameClient.getContentPane().add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		JLabel labelFirstName = new JLabel("Nom :");
		labelFirstName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelFirstName.setLabelFor(textFieldFirstName);
		labelFirstName.setBounds(10, 51, 210, 25);
		frameClient.getContentPane().add(labelFirstName);
		
		//client family name
		textFieldLastName = new JTextField();
		textFieldLastName.setToolTipText("Veuillez entrer le nom de famille du client");
		textFieldLastName.setBounds(179, 100, 167, 25);
		frameClient.getContentPane().add(textFieldLastName);
		textFieldLastName.setColumns(10);

		JLabel labelLastName = new JLabel("Prenom :");
		labelLastName.setLabelFor(textFieldLastName);
		labelLastName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelLastName.setBounds(10, 100, 210, 25);
		frameClient.getContentPane().add(labelLastName);

		// client date of birth
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(179, 150, 167, 25);
		frameClient.getContentPane().add(dateChooser);
		SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");

		JLabel labelDate = new JLabel("Date de Naissance :");
		labelDate.setLabelFor(dateChooser);
		labelDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelDate.setBounds(10, 150, 210, 25);
		frameClient.getContentPane().add(labelDate);

		// client email
		textFieldEmail = new JTextField();
		textFieldEmail.setToolTipText("Veuillez entrer l'adresse courriel du client");
		textFieldEmail.setBounds(179, 200, 167, 25);
		frameClient.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel labelEmail = new JLabel("Adresse Courriel :");
		labelEmail.setLabelFor(textFieldEmail);
		labelEmail.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelEmail.setBounds(10, 200, 210, 25);
		frameClient.getContentPane().add(labelEmail);
		
		// client phone number
		textFieldPhone = new JTextField();
		textFieldPhone.setToolTipText("Veuillez entrer le numéro de téléphone du client");
		textFieldPhone.setBounds(179, 250, 167, 25);
		frameClient.getContentPane().add(textFieldPhone);
		textFieldPhone.setColumns(10);
		
		JLabel labelPhone = new JLabel("Numéro de Téléphone :");
		labelPhone.setLabelFor(textFieldPhone);
		labelPhone.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelPhone.setBounds(10,250, 210, 25);
		frameClient.getContentPane().add(labelPhone);
		
		// adress 
		textFieldAdress = new JTextField();
		textFieldAdress.setToolTipText("Veuillez entrer l'adresse du client");
		textFieldAdress.setBounds(179, 300, 167, 25);
		frameClient.getContentPane().add(textFieldAdress);
		textFieldAdress.setColumns(10);
		
		JLabel labelAdress = new JLabel("Adresse :");
		labelAdress.setLabelFor(textFieldPhone);
		labelAdress.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelAdress.setBounds(10,300, 210, 25);
		frameClient.getContentPane().add(labelAdress);		
		
		// client zip code
		textFieldZipcode = new JTextField();
		textFieldZipcode.setToolTipText("Veuillez entrer le code postal du client");
		textFieldZipcode.setBounds(179, 350, 167, 25);
		frameClient.getContentPane().add(textFieldZipcode);
		textFieldZipcode.setColumns(10);
				
		JLabel labelZipcode = new JLabel("Code Postal :");
		labelZipcode.setLabelFor(textFieldZipcode);
		labelZipcode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelZipcode.setBounds(10,350, 210, 25);
		frameClient.getContentPane().add(labelZipcode);
		
		// client city
		textFieldCity = new JTextField();
		textFieldCity.setToolTipText("Veuillez entrer la ville du client");
		textFieldCity.setBounds(179, 400, 167, 25);
		frameClient.getContentPane().add(textFieldCity);
		textFieldCity.setColumns(10);
				
		JLabel labelCity = new JLabel("Ville :");
		labelCity.setLabelFor(textFieldCity);
		labelCity.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelCity.setBounds(10,400, 210, 25);
		frameClient.getContentPane().add(labelCity);
		
		// table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(408, 50, 908, 322);
		frameClient.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		// Mouse click table
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				textFieldFirstName.setText(dtm.getValueAt(row, 1).toString());
				textFieldLastName.setText(dtm.getValueAt(row, 2).toString());
				textFieldEmail.setText(dtm.getValueAt(row, 4).toString());
				textFieldPhone.setText(dtm.getValueAt(row, 5).toString());
				textFieldAdress.setText(dtm.getValueAt(row, 6).toString());
				textFieldZipcode.setText(dtm.getValueAt(row, 7).toString());
				textFieldCity.setText(dtm.getValueAt(row, 8).toString());


				try {
					dateChooser.setDate(dateformat.parse(dtm.getValueAt(row, 3).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		// button to add client
		JButton addClient = new JButton("Ajouter");
		addClient.setBackground(Color.GREEN);
		addClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
		addClient.setBounds(10, 487, 103, 37);
		frameClient.getContentPane().add(addClient);
		
		// button to remove client
		JButton removeClient = new JButton("Supprimer");
		removeClient.setBackground(new Color(178, 34, 34));
		removeClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
		removeClient.setBounds(136, 487, 103, 37);
		frameClient.getContentPane().add(removeClient);
		
		// button to modify client
		JButton modifyClient = new JButton("Modifier");
		modifyClient.setBackground(Color.ORANGE);
		modifyClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
		modifyClient.setBounds(262, 487, 103, 37);
		frameClient.getContentPane().add(modifyClient);

		
		// button to find client
		JButton findClient = new JButton("Rechercher");
		findClient.setFont(new Font("Times New Roman", Font.BOLD, 10));
		findClient.setBounds(741, 423, 103, 25);
		frameClient.getContentPane().add(findClient);
				
		searchField = new JTextField();
		searchField.setToolTipText("rechercher le client ici...");
		searchField.setBounds(508, 422, 175, 25);
		frameClient.getContentPane().add(searchField);
		searchField.setColumns(10);
				
		// bouton to refresh table
		JButton refresh = new JButton("Rafraichir");
		refresh.setFont(new Font("Times New Roman", Font.BOLD, 12));
		refresh.setBounds(1204, 435, 112, 31);
		frameClient.getContentPane().add(refresh);
		
		// button to quit
	   JButton quit = new JButton("Quitter");
	   quit.setFont(new Font("Times New Roman", Font.BOLD, 12));
	   quit.setBounds(1204, 490, 112, 31);
	   frameClient.getContentPane().add(quit);
	   
		// button return
		JButton returnClient = new JButton("Retour");
		returnClient.setFont(new Font("Times New Roman", Font.BOLD, 12));
		returnClient.setBounds(1073, 490, 112, 31);
		frameClient.getContentPane().add(returnClient);
			

	
////////////////////////////////////////////////////////////////////////
// Action performed to quit page
///////////////////////////////////////////////////////////////////////
	   quit.addActionListener(new ActionListener() {
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

////////////////////////////////////////////////////////////////////////
//Action performed add client
///////////////////////////////////////////////////////////////////////
	   addClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lastName = textFieldLastName.getText(),
						firstName = textFieldFirstName.getText(),
						email = textFieldEmail.getText(), 
						phoneNum = textFieldPhone.getText(), 
						address = textFieldAdress.getText(), 
						zipCode = textFieldZipcode.getText(),
						city = textFieldCity.getText();
				Date birthDate = dateChooser.getDate();
				boolean state = true;
				// nom
				labelLastName.setForeground(lastName.equals("") ? Color.red : Color.black);
				state = !lastName.equals("") && state;
				
				// prenom
				labelFirstName.setForeground(firstName.equals("") ? Color.red : Color.black);
				state = !firstName.equals("") && state;
				
				// date de naissance
				labelDate.setForeground(birthDate == null ? Color.red : Color.black);
				state = !(birthDate == null) && state;
				
				// email
				labelEmail.setForeground(email.equals("") ? Color.red : Color.black);
				state = !email.equals("") && state;
				
				// numéro de téléphone
				boolean isValidPhone = !phoneNum.equals("") && phoneNum.length() <= 10;
				labelPhone.setForeground(!isValidPhone ? Color.red : Color.black);
				state = isValidPhone && state;
				
				// Addresse
				labelAdress.setForeground(address.equals("") ? Color.red : Color.black);
				state = !address.equals("") && state;
				
				// ville
				labelCity.setForeground(city.equals("") ? Color.red : Color.black);
				state = !city.equals("") && state;
				
				// Code Postal
				labelZipcode.setForeground(zipCode.equals("") ? Color.red : Color.black);
				state = !zipCode.equals("") && state;
				
				if (state) { 
					try {
						Client account = new Client(firstName, lastName, dateformat.format(birthDate), email,
								phoneNum, address, zipCode, city);
						boolean created = ClientControler.addClient(account);
						if(created) {
							// reset field
							textFieldFirstName.setText("");
							textFieldLastName.setText("");
							textFieldEmail.setText("");
							textFieldPhone.setText("");
							textFieldAdress.setText("");
							textFieldZipcode.setText("");
							textFieldCity.setText("");
							dateChooser.setDate(null);
							
							String message = account.sendAccountNumber() + "\nNuméro de compte: " + account.getAccountNumber();
							JOptionPane.showConfirmDialog(null, message,"Compte créé avec succès :", JOptionPane.PLAIN_MESSAGE);
							try {
								CsvClient.addOneAccount(".\\csv\\clientDatabase.csv",
										ClientControler.getClients().get(ClientControler.getClients().size()-1));
							}
							catch (CsvDataTypeMismatchException
									| CsvRequiredFieldEmptyException | IOException e1) {
								e1.printStackTrace();
							}
						}
						else {
							JOptionPane.showConfirmDialog(
								null, 
								"Le compte n'a pas pu être créé, une erreur est survenu",
								"Création de compte non effectuer", 
								JOptionPane.PLAIN_MESSAGE
							);
						}
					} 
					catch (Error err) {
						JOptionPane.showConfirmDialog(null, err.getMessage(),"Corriger les erreurs suivantes :", JOptionPane.PLAIN_MESSAGE);	
					}
				}
				showDetails("");
			}
	   });
////////////////////////////////////////////////////////////////////////
//Action performed delete client
///////////////////////////////////////////////////////////////////////
	   removeClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Supprimer le client", "Supprimer",
						JOptionPane.YES_NO_OPTION);
				if (choice == 0) {
					String accountNumber = dtm.getValueAt(row, 0).toString();
					boolean deleted = ClientControler.deleteClient(accountNumber);
					if(deleted) {
						VaccinationProfileControler.deleteVaccinationProfile(accountNumber);
						QuestionnaireControler.deleteQuestionnaire(accountNumber);
						dtm.removeRow(row);
						// reset field
						textFieldFirstName.setText("");
						textFieldLastName.setText("");
						textFieldEmail.setText("");
						textFieldPhone.setText("");
						textFieldAdress.setText("");
						textFieldZipcode.setText("");
						textFieldCity.setText("");
						dateChooser.setDate(null);
						
						// remove from csv
						try {
							CsvQuestionnaire.removeOneQuestionnaireProfile(".\\csv\\QuestionnaireDataBase.csv", accountNumber);
							CsvVaccinationProfile.removeOneVaccinationProfile(".\\csv\\VPDataBase.csv", accountNumber);
							CsvClient.removeOneClient(".\\csv\\clientDatabase.csv", accountNumber);
						} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | CsvValidationException
								| IOException | ParseException e1) {
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showConfirmDialog(
							null, 
							"Le compte n'a pas pu être supprimé, une erreur est survenu",
							"Suppression de compte non effectuer",
							JOptionPane.PLAIN_MESSAGE
						);
					}
					showDetails("");
				}
			}
		});
	   
////////////////////////////////////////////////////////////////////////
//Action performed modify client
///////////////////////////////////////////////////////////////////////
		modifyClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lastName = textFieldLastName.getText(),
						firstName = textFieldFirstName.getText(),
						email = textFieldEmail.getText(), 
						phoneNum = textFieldPhone.getText(), 
						address = textFieldAdress.getText(), 
						zipCode = textFieldZipcode.getText(),
						city = textFieldCity.getText();
				Date birthDate = dateChooser.getDate();
				boolean state = true;
				// nom
				labelLastName.setForeground(lastName.equals("") ? Color.red : Color.black);
				state = !lastName.equals("") && state;
				
				// prenom
				labelFirstName.setForeground(firstName.equals("") ? Color.red : Color.black);
				state = !firstName.equals("") && state;
				
				// date de naissance
				labelDate.setForeground(birthDate == null ? Color.red : Color.black);
				state = !(birthDate == null) && state;
				
				// email
				labelEmail.setForeground(email.equals("") ? Color.red : Color.black);
				state = !email.equals("") && state;
				
				// numéro de téléphone
				boolean isValidPhone = !phoneNum.equals("") && phoneNum.length() <= 10;
				labelPhone.setForeground(!isValidPhone ? Color.red : Color.black);
				state = isValidPhone && state;
				
				// Addresse
				labelAdress.setForeground(address.equals("") ? Color.red : Color.black);
				state = !address.equals("") && state;
				
				// ville
				labelCity.setForeground(city.equals("") ? Color.red : Color.black);
				state = !city.equals("") && state;
				
				// Code Postal
				labelZipcode.setForeground(zipCode.equals("") ? Color.red : Color.black);
				state = !zipCode.equals("") && state;
				
				if (state) { 
					// modify in table (graphic)
					String accountNumber = dtm.getValueAt(row, 0).toString();
					boolean modified = ClientControler.modifyClient(accountNumber, firstName, lastName, dateformat.format(birthDate),
							email, phoneNum, address, zipCode, city);
					if(modified) {
						// reset field
						textFieldFirstName.setText("");
						textFieldLastName.setText("");
						textFieldEmail.setText("");
						textFieldPhone.setText("");
						textFieldAdress.setText("");
						textFieldZipcode.setText("");
						textFieldCity.setText("");
						dateChooser.setDate(null);
						try {
							CsvClient.writeClient(".\\csv\\clientDatabase.csv",
									ClientControler.getClients());
						} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | CsvValidationException
								| IOException e1) {
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showConfirmDialog(
								null, 
								"Le compte n'a pas pu être modifié, une erreur est survenu",
								"Modification de compte non effectuer", 
								JOptionPane.PLAIN_MESSAGE
						);
					}
					showDetails("");
				}
			}
		});
		
////////////////////////////////////////////////////////////////////////
//Action performed find client
///////////////////////////////////////////////////////////////////////				

		findClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showDetails(searchField.getText());
			}
		});
		
////////////////////////////////////////////////////////////////////////
//Action performed refresh table
///////////////////////////////////////////////////////////////////////	

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDetails("");
				searchField.setText("");
				// reset field
				textFieldFirstName.setText("");
				textFieldLastName.setText("");
				textFieldEmail.setText("");
				textFieldPhone.setText("");
				textFieldAdress.setText("");
				textFieldZipcode.setText("");
				textFieldCity.setText("");
				dateChooser.setDate(null);
			}
		});
		
		
////////////////////////////////////////////////////////////////////////
//Action performed return table
///////////////////////////////////////////////////////////////////////	
		
		returnClient.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
						new MenuEmploye();
						frameClient.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		}});
		
		frameClient.setVisible(true);
	}	
}