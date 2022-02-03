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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import com.toedter.calendar.JDateChooser;

import Account.ClientControler;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import lib.DateEvaluator;
import lib.VaccinationProfile;
import lib.VaccinationProfileControler;
import lib.Vaccine;
import csv.CsvClient;
import csv.CsvVaccinationProfile;

/**
 * Classe offrant une interface graphique pour la gestion des profiles
 * de vaccination
 * @author Hani Berchan
 * @version 1.0
 */
public class UIVaccinationProfile {

	private JFrame frameVP;
	private JTextField textFieldAccountNumber;
	private JTextField textFieldVaccineCode;
	private JTextField searchField;
	private JOptionPane confirmer;

	// variable for table
	int row;
	static DefaultTableModel dtm;
	private JTable table;

	/**
	 * Constructeur qui initialize l'interface graphique
	 */
	public UIVaccinationProfile() {
		initialize();

		dtm = new DefaultTableModel(new Object[][] {}, new String[] { "Numéro de compte", "Date de Vaccination", "Dose",
				"Nom du vaccin", "Code du Vaccin" }) {
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
		if (VaccinationProfileControler.getVaccinationProfiles().size() > 0) {
			showVPDetails("");
		}
	}

	/**
	 * Méthode qui met à jour les informations des profiles de vaccination du tableau
	 * @param mot String qui filtre le tableau en fonction du accountNumber du client
	 */
	public static void showVPDetails(String mot) {
		dtm.setRowCount(0);
		List<VaccinationProfile> listVP = VaccinationProfileControler.getVaccinationProfiles();
		if (mot.length() == 0) {
			dtm.setRowCount(0);
			for (int i = 0; i < listVP.size(); i++) {
				Object[] obj = { 
					listVP.get(i).getAccountNumber(),
					listVP.get(i).getVaccinationDate(),
					listVP.get(i).getDose(),
					listVP.get(i).getVaccineName(),
					listVP.get(i).getVaccineCode() 
				};
				dtm.addRow(obj);
			}
		} else {
			for (int i = 0; i < listVP.size(); i++) {
				if (listVP.get(i).getAccountNumber().startsWith(mot)) {
					Object[] obj = { 
						listVP.get(i).getAccountNumber(),
						listVP.get(i).getVaccinationDate(),
						listVP.get(i).getDose(),
						listVP.get(i).getVaccineName(),
						listVP.get(i).getVaccineCode()
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
		frameVP = new JFrame("Profil de Vaccination");
		frameVP.setBounds(150, 150, 1365, 605);
		frameVP.getContentPane().setBackground(new Color(0, 191, 255));
		frameVP.getContentPane().setLayout(null);
		frameVP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// account Number
		textFieldAccountNumber = new JTextField();
		textFieldAccountNumber.setToolTipText("Veuillez entrer le numéro de compte du client");
		textFieldAccountNumber.setBounds(179, 51, 167, 25);
		frameVP.getContentPane().add(textFieldAccountNumber);
		textFieldAccountNumber.setColumns(10);

		JLabel labelAccountNumber = new JLabel("Numéro de compte :");
		labelAccountNumber.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelAccountNumber.setLabelFor(textFieldAccountNumber);
		labelAccountNumber.setBounds(10, 51, 210, 25);
		frameVP.getContentPane().add(labelAccountNumber);

		// vaccination date
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getJCalendar().getDayChooser().addDateEvaluator(new DateEvaluator());
		dateChooser.setBounds(179, 100, 167, 25);
		frameVP.getContentPane().add(dateChooser);
		SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");

		JLabel labelVaccinationDate = new JLabel("Date de vaccination :");
		labelVaccinationDate.setLabelFor(dateChooser);
		labelVaccinationDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelVaccinationDate.setBounds(10, 100, 210, 25);
		frameVP.getContentPane().add(labelVaccinationDate);

		// dose
		JRadioButton radio1 = new JRadioButton("1");
		radio1.setBounds(179, 152, 31, 21);
		frameVP.getContentPane().add(radio1);

		JRadioButton radio2 = new JRadioButton("2");
		radio2.setBounds(235, 152, 31, 21);
		frameVP.getContentPane().add(radio2);

		JLabel labelDose = new JLabel("Dose :");
		labelDose.setLabelFor(radio1);
		labelDose.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelDose.setBounds(10, 150, 210, 25);
		frameVP.getContentPane().add(labelDose);

		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		
		// Vaccine Name
		JComboBox vaccineCB = new JComboBox(Vaccine.values());
		vaccineCB.setBounds(179, 200, 167, 25);
		frameVP.getContentPane().add(vaccineCB);

		JLabel labelVaccineName = new JLabel("Nom du vaccin :");
		labelVaccineName.setLabelFor(vaccineCB);
		labelVaccineName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelVaccineName.setBounds(10, 200, 210, 25);
		frameVP.getContentPane().add(labelVaccineName);

		// Vaccine Code
		textFieldVaccineCode = new JTextField();
		textFieldVaccineCode.setToolTipText("Veuillez entrer le code du vaccin");
		textFieldVaccineCode.setBounds(179, 250, 167, 25);
		frameVP.getContentPane().add(textFieldVaccineCode);
		textFieldVaccineCode.setColumns(10);

		JLabel labelVaccineCode = new JLabel("Code du vaccin :");
		labelVaccineCode.setLabelFor(textFieldVaccineCode);
		labelVaccineCode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelVaccineCode.setBounds(10, 250, 210, 25);
		frameVP.getContentPane().add(labelVaccineCode);

		// table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(408, 50, 908, 322);
		frameVP.getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		// Mouse click table
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				// account Number
				textFieldAccountNumber.setText(dtm.getValueAt(row, 0).toString());
				// Vaccination date
				try {
					dateChooser.setDate(dateformat.parse(dtm.getValueAt(row, 1).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				// dose
				switch (dtm.getValueAt(row, 2).toString()) {
				case "1":
					radio1.setSelected(true);
					radio2.setSelected(false);
					break;
				case "2":
					radio2.setSelected(true);
					radio1.setSelected(false);
					break;
				default:
					break;
				}
				// vaccine name
				switch (dtm.getValueAt(row, 3).toString()) {
				case "NONE":
					vaccineCB.setSelectedItem(Vaccine.NONE);
					break;
				case "PFIZER":
					vaccineCB.setSelectedItem(Vaccine.PFIZER);
					break;
				case "MODERNA":
					vaccineCB.setSelectedItem(Vaccine.MODERNA);
					break;
				case "ASTRAZENECA":
					vaccineCB.setSelectedItem(Vaccine.ASTRAZENECA);
					break;
				case "JANSSEN":
					vaccineCB.setSelectedItem(Vaccine.JANSSEN);
					break;
				default:
					break;
				}
				// vaccine code
				textFieldVaccineCode.setText(dtm.getValueAt(row, 4).toString());
			}
		});

		// button to add vaccination profile
		JButton addVP = new JButton("Ajouter");
		addVP.setBackground(Color.GREEN);
		addVP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		addVP.setBounds(10, 487, 103, 37);
		frameVP.getContentPane().add(addVP);

		// button to modify vaccination profile
		JButton modifyVP = new JButton("Modifier");
		modifyVP.setBackground(Color.ORANGE);
		modifyVP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		modifyVP.setBounds(262, 487, 103, 37);
		frameVP.getContentPane().add(modifyVP);

		// button to find VP
		JButton findVaccinationProfile = new JButton("Rechercher");
		findVaccinationProfile.setFont(new Font("Times New Roman", Font.BOLD, 10));
		findVaccinationProfile.setBounds(741, 423, 103, 25);
		frameVP.getContentPane().add(findVaccinationProfile);

		searchField = new JTextField();
		searchField.setToolTipText("rechercher le VP ici...");
		searchField.setBounds(508, 422, 175, 25);
		frameVP.getContentPane().add(searchField);
		searchField.setColumns(10);

		// bouton to refresh table
		JButton refresh = new JButton("Rafraichir");
		refresh.setFont(new Font("Times New Roman", Font.BOLD, 12));
		refresh.setBounds(1204, 435, 112, 31);
		frameVP.getContentPane().add(refresh);

		
		// button to quit
		JButton quit = new JButton("Quitter");
		quit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		quit.setBounds(1204, 490, 112, 31);
		frameVP.getContentPane().add(quit);
		
		// button return
		JButton returnVP = new JButton("Retour");
		returnVP.setFont(new Font("Times New Roman", Font.BOLD, 12));
		returnVP.setBounds(1073, 490, 112, 31);
		frameVP.getContentPane().add(returnVP);

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
//Action performed add Vaccination Profile
///////////////////////////////////////////////////////////////////////
		addVP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accountNumber = textFieldAccountNumber.getText(), vaccineCode = textFieldVaccineCode.getText();
				Date vaccinationDate = dateChooser.getDate();
				String vaccineName = (vaccineCB.getSelectedItem().toString());
				String dose = "";
				boolean state = true;
				if(VaccinationProfileControler.getVaccinationProfile(accountNumber) != null) {
					JOptionPane.showMessageDialog(null, "Il y a déjà un profile de vaccination associé au compte: " + accountNumber);
					return;
				}
				// account Number
				labelAccountNumber.setForeground(accountNumber.equals("") ? Color.red : Color.black);
				state = !accountNumber.equals("") && state;

				// vaccination date
				labelVaccinationDate.setForeground(vaccinationDate == null ? Color.red : Color.black);
				state = !(vaccinationDate == null) && state;

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

				// vaccine name
				labelVaccineName.setForeground(vaccineName.equals("") ? Color.red : Color.black);
				state = !vaccineName.equals("") && state;

				// vaccine code
				labelVaccineCode.setForeground(vaccineCode.equals("") ? Color.red : Color.black);
				state = !vaccineCode.equals("") && state;

				if (state) {
					try {

						Vaccine vac = Vaccine.valueOf(vaccineName);
						VaccinationProfile vp = new VaccinationProfile(accountNumber,
								dateformat.format(vaccinationDate), Integer.parseInt(dose), vac, vaccineCode);
						boolean created = VaccinationProfileControler.addVaccinationProfile(vp);
						if (created) {
							// reset field
							textFieldAccountNumber.setText("");
							textFieldVaccineCode.setText("");
							dateChooser.setDate(null);
							group.clearSelection();
							vaccineCB.setSelectedItem(Vaccine.NONE);
							// pdf
							String message = "Rapport de vaccination produit:\n" +
						"- Nom : " + ClientControler.getClient(accountNumber).getFirstName() +" " + 
									ClientControler.getClient(accountNumber).getLastName() + "\n" +
									"- Date : " + ClientControler.getClient(accountNumber).getBirthDate() + "\n" +
									"- Vaccin reçu\n" + "	- Dose : " + vp.getDose() + "\n" +
									"\t"+ "- Vaccin : " + vp.getVaccineName() + "\n" +
									"\t"+ "- Date : " + vp.getVaccinationDate() + "\n" +
									"\t"+ "- Code de vaccin : " + vp.getVaccineCode() + "\n" +
									"Code QR : #################################";
									
							JOptionPane.showConfirmDialog(null, message, "Profile de vaccination créé avec succès :",
									JOptionPane.PLAIN_MESSAGE);
							
							String envoyer = vp.sendProofOfVaccination(ClientControler.getClient(accountNumber).getEmail());
							JOptionPane.showConfirmDialog(null, envoyer, "Envoyer profil de vaccination :",
									JOptionPane.PLAIN_MESSAGE);
							try {
								CsvVaccinationProfile.addOneVP(".\\csv\\VPDatabase.csv",
										VaccinationProfileControler.getVaccinationProfiles()
												.get(VaccinationProfileControler.getVaccinationProfiles().size() - 1));
							} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e1) {
								e1.printStackTrace();
							}
						} else {
							JOptionPane.showConfirmDialog(null,
									"Le profil de vaccination n'a pas pu être créé, une erreur est survenu",
									"Création du profil non effectuer", JOptionPane.PLAIN_MESSAGE);

						}
					} catch (Error err) {
						JOptionPane.showConfirmDialog(null, err.getMessage(), "Corriger les erreurs suivantes :",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
				showVPDetails("");
				
			}
		});

////////////////////////////////////////////////////////////////////////
//Action performed modify client
///////////////////////////////////////////////////////////////////////
		modifyVP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accountNumber = textFieldAccountNumber.getText(), vaccineCode = textFieldVaccineCode.getText();
				Date vaccinationDate = dateChooser.getDate();
				String vaccineName = (vaccineCB.getSelectedItem().toString());
				String dose = "";
				boolean state = true;
				// account Number
				labelAccountNumber.setForeground(accountNumber.equals("") ? Color.red : Color.black);
				state = !accountNumber.equals("") && state;

				// vaccination date
				labelVaccinationDate.setForeground(vaccinationDate == null ? Color.red : Color.black);
				state = !(vaccinationDate == null) && state;

				// dose
				if (radio1.isSelected()) {
					state = true;
					dose = radio1.getActionCommand();
				} else if (radio2.isSelected()) {
					state = true;
					dose = radio2.getActionCommand();
				}

				// vaccine name
				labelVaccineName.setForeground(vaccineName.equals("") ? Color.red : Color.black);
				state = !vaccineName.equals("") && state;

				// vaccine code
				labelVaccineCode.setForeground(vaccineCode.equals("") ? Color.red : Color.black);
				state = !vaccineCode.equals("") && state;

				if (state) {
					// modify in table (graphic)
					Vaccine vac = Vaccine.valueOf(vaccineName);
					boolean modified = VaccinationProfileControler.modifyVaccinationProfile(accountNumber,
							dateformat.format(vaccinationDate), Integer.parseInt(dose), vac, vaccineCode);
					if (modified) {
						// reset fields
						textFieldAccountNumber.setText("");
						textFieldVaccineCode.setText("");
						dateChooser.setDate(null);
						group.clearSelection();
						vaccineCB.setSelectedItem(Vaccine.NONE);
						try {
							CsvVaccinationProfile.writeVP(".\\csv\\VPDatabase.csv",
									VaccinationProfileControler.getVaccinationProfiles());
							VaccinationProfile vp = VaccinationProfileControler.getVaccinationProfile(accountNumber);
							String envoyer = vp.sendProofOfVaccination(ClientControler.getClient(accountNumber).getEmail());
							JOptionPane.showConfirmDialog(null, envoyer, "Envoyer profil de vaccination :",
									JOptionPane.PLAIN_MESSAGE);

						} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | CsvValidationException
								| IOException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showConfirmDialog(null, "Le compte n'a pas pu être modifié, une erreur est survenu",
								"Modification de compte non effectuer", JOptionPane.PLAIN_MESSAGE);
					}
					showVPDetails("");
				}
			}
		});

////////////////////////////////////////////////////////////////////////
//Action performed find vacccination profile
///////////////////////////////////////////////////////////////////////	
		findVaccinationProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showVPDetails(searchField.getText());
			}
		});

////////////////////////////////////////////////////////////////////////
//Action performed refresh table
///////////////////////////////////////////////////////////////////////	
		
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showVPDetails("");
				textFieldAccountNumber.setText("");
				textFieldVaccineCode.setText("");
				dateChooser.setDate(null);
				group.clearSelection();
				vaccineCB.setSelectedItem(Vaccine.NONE);
				searchField.setText("");
			}
		});
		
////////////////////////////////////////////////////////////////////////
//Action performed return
///////////////////////////////////////////////////////////////////////	
		
		returnVP.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
						new MenuEmploye();
						frameVP.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
		}});
		
		frameVP.setVisible(true);
	}

}
