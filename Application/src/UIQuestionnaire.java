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
import javax.swing.JCheckBox;
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

import javax.swing.JTable;
import javax.swing.JScrollPane;

import lib.DateEvaluator;
import lib.Questionnaire;
import csv.CsvQuestionnaire;
import lib.QuestionnaireControler;
import lib.Vaccine;

/**
 * Classe offrant une interface graphique pour la gestion des questionnaires
 * @author Hani Berchan
 * @version 1.0
 */
public class UIQuestionnaire {

	private JFrame frameQ;
	private JTextField textFieldAccountNumber;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldHIC;
	private JTextField textFieldVaccineCode;
	private JTextField searchField;
	private JOptionPane confirm;

	// variable for table
	int row;
	static DefaultTableModel dtm;
	private JTable table;

	/**
	 * Constructeur qui initialize l'interface graphique
	 */
	@SuppressWarnings("serial")
	public UIQuestionnaire() {
		initialize();

		dtm = new DefaultTableModel(new Object[][] {},
				new String[] { "Numéro de Compte", "Prenom", "Nom", "Naissance", "Assurance", "Visite", "Contracter",
						"Symptomes", "Allergies", "Choix", "Vaccin fait", "Nom vaccin", "Code vaccin" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class, String.class, String.class, String.class, String.class,
					String.class };

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
		table.getColumnModel().getColumn(4).setPreferredWidth(100);

		if (QuestionnaireControler.getQuestionnaireFiles().size() > 0) {
			showQuestionnaireDetails("");
		}
	}

	/**
	 * Méthode qui met à jour les informations des questionnaires du tableau
	 * @param mot String qui filtre le tableau en fonction numéro de compte du client
	 */
	public static void showQuestionnaireDetails(String mot) {
		dtm.setRowCount(0);
		List<Questionnaire> listQ = QuestionnaireControler.getQuestionnaireFiles();
		if (mot.length() == 0) {
			dtm.setRowCount(0);
			for (int i = 0; i < listQ.size(); i++) {
				Object[] obj = { listQ.get(i).getAccountNumber(),
						listQ.get(i).getFirstName(),
						listQ.get(i).getLastName(),
						listQ.get(i).getBirthDate(),
						listQ.get(i).getHealthInsuranceCard(),
						listQ.get(i).getVisitDate(),
						listQ.get(i).isHasContractedCovid() ?"Oui":"Non",
						listQ.get(i).isHasCovidSymptoms() ?"Oui":"Non",
						listQ.get(i).isHasAllergies()?"Oui":"Non",
						listQ.get(i).getChoiceVac(),
						listQ.get(i).isHasVaccine()?"Oui":"Non",
						listQ.get(i).getVaccineName(),
						listQ.get(i).getVaccineCode() };
				dtm.addRow(obj);
			}

		} else {
			for (int i = 0; i < listQ.size(); i++) {
				if (listQ.get(i).getAccountNumber().startsWith(mot)) {
					Object[] obj = { listQ.get(i).getAccountNumber(),
							listQ.get(i).getFirstName(),
							listQ.get(i).getLastName(),
							listQ.get(i).getBirthDate(),
							listQ.get(i).getHealthInsuranceCard(),
							listQ.get(i).getVisitDate(),
							listQ.get(i).isHasContractedCovid()?"Oui":"Non",
							listQ.get(i).isHasCovidSymptoms()?"Oui":"Non",
							listQ.get(i).isHasAllergies()?"Oui":"Non",
							listQ.get(i).getChoiceVac(),
							listQ.get(i).isHasVaccine()?"Oui":"Non",
							listQ.get(i).getVaccineName(),
							listQ.get(i).getVaccineCode() };
					dtm.addRow(obj);
				}
			}

		}
	}

	/**
	 * Méthode qui initialize l'interface graphique
	 */
	private void initialize() {
		
		frameQ = new JFrame("Questionnaire");
		frameQ.setBounds(150, 150, 1405, 735);
		frameQ.getContentPane().setBackground(new Color(0, 191, 255));
		frameQ.getContentPane().setLayout(null);
		frameQ.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// account Number
		textFieldAccountNumber = new JTextField();
		textFieldAccountNumber.setToolTipText("Veuillez entrer le numéro de compte du client");
		textFieldAccountNumber.setBounds(179, 50, 167, 25);
		frameQ.getContentPane().add(textFieldAccountNumber);
		textFieldAccountNumber.setColumns(10);

		JLabel labelAccountNumber = new JLabel("Numéro de compte :");
		labelAccountNumber.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelAccountNumber.setLabelFor(textFieldAccountNumber);
		labelAccountNumber.setBounds(10, 50, 210, 25);
		frameQ.getContentPane().add(labelAccountNumber);	
	
		// client name
		textFieldFirstName = new JTextField();
		textFieldFirstName.setToolTipText("Veuillez entrer le nom du client");
		textFieldFirstName.setBounds(179, 90, 167, 25);
		frameQ.getContentPane().add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
				
		JLabel labelFirstName = new JLabel("Nom :");
		labelFirstName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelFirstName.setLabelFor(textFieldFirstName);
		labelFirstName.setBounds(10, 90, 210, 25);
		frameQ.getContentPane().add(labelFirstName);
				
		//client family name
		textFieldLastName = new JTextField();
		textFieldLastName.setToolTipText("Veuillez entrer le nom de famille du client");
		textFieldLastName.setBounds(179, 130, 167, 25);
		frameQ.getContentPane().add(textFieldLastName);
		textFieldLastName.setColumns(10);

		JLabel labelLastName = new JLabel("Prenom :");
		labelLastName.setLabelFor(textFieldLastName);
		labelLastName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelLastName.setBounds(10, 130, 210, 25);
		frameQ.getContentPane().add(labelLastName);
 
		// client birth date
		JDateChooser dateBirth = new JDateChooser();
		dateBirth.setBounds(179, 180, 167, 25);
		frameQ.getContentPane().add(dateBirth);
		SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");

		JLabel labelDateBirth = new JLabel("Date de Naissance :");
		labelDateBirth.setLabelFor(dateBirth);
		labelDateBirth.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelDateBirth.setBounds(10, 180, 210, 25);
		frameQ.getContentPane().add(labelDateBirth);
		
		// client health insurance card
		textFieldHIC = new JTextField();
		textFieldHIC.setToolTipText("Veuillez entrer l'assurance maladie du client");
		textFieldHIC.setBounds(179, 220, 167, 25);
		frameQ.getContentPane().add(textFieldHIC);
		textFieldHIC.setColumns(10);
		
		JLabel labelHIC = new JLabel("Assurance Maladie :");
		labelHIC.setLabelFor(textFieldHIC);
		labelHIC.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelHIC.setBounds(10, 220, 210, 25);
		frameQ.getContentPane().add(labelHIC);
		
		// visit date
		JDateChooser dateVisit = new JDateChooser();
		dateVisit.getJCalendar().getDayChooser().addDateEvaluator(new DateEvaluator());
		dateVisit.setBounds(179, 260, 167, 25);
		frameQ.getContentPane().add(dateVisit);

		JLabel labelDateVisit = new JLabel("Date de Visite :");
		labelDateVisit.setLabelFor(dateVisit);
		labelDateVisit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelDateVisit.setBounds(10, 260, 210, 25);
		frameQ.getContentPane().add(labelDateVisit);
		
		// has contracted covid
		JRadioButton radioContractedYes = new JRadioButton("Oui");
		radioContractedYes.setBounds(179, 300, 50, 21);
		frameQ.getContentPane().add(radioContractedYes);

		JRadioButton radioContractedNo = new JRadioButton("Non");
		radioContractedNo.setBounds(235, 300, 50, 21);
		frameQ.getContentPane().add(radioContractedNo);

		JLabel labelContracted = new JLabel("Contracter Covid :");
		labelContracted.setLabelFor(radioContractedYes);
		labelContracted.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelContracted.setBounds(10, 300, 210, 25);
		frameQ.getContentPane().add(labelContracted);

		ButtonGroup Contracted = new ButtonGroup();
		Contracted.add(radioContractedYes);
		Contracted.add(radioContractedNo);
		
		// has covid symptoms
		JRadioButton radioSymptomsYes = new JRadioButton("Oui");
		radioSymptomsYes.setBounds(179, 340, 50, 21);
		frameQ.getContentPane().add(radioSymptomsYes);

		JRadioButton radioSymptomsNo = new JRadioButton("Non");
		radioSymptomsNo.setBounds(235, 340, 50, 21);
		frameQ.getContentPane().add(radioSymptomsNo);

		JLabel labelSymptoms = new JLabel("Symptome Covid :");
		labelSymptoms.setLabelFor(radioSymptomsYes);
		labelSymptoms.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelSymptoms.setBounds(10, 340, 210, 25);
		frameQ.getContentPane().add(labelSymptoms);

		ButtonGroup Symptoms = new ButtonGroup();
		Symptoms.add(radioSymptomsYes);
		Symptoms.add(radioSymptomsNo);
		
		// has allergies
		JRadioButton radioAllergiesYes = new JRadioButton("Oui");
		radioAllergiesYes.setBounds(179, 380, 50, 21);
		frameQ.getContentPane().add(radioAllergiesYes);

		JRadioButton radioAllergiesNo = new JRadioButton("Non");
		radioAllergiesNo.setBounds(235, 380, 50, 21);
		frameQ.getContentPane().add(radioAllergiesNo);

		JLabel labelAllergies = new JLabel("Allergie :");
		labelAllergies.setLabelFor(radioAllergiesYes);
		labelAllergies.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelAllergies.setBounds(10, 380, 210, 25);
		frameQ.getContentPane().add(labelAllergies);

		ButtonGroup Allergies = new ButtonGroup();
		Allergies.add(radioAllergiesYes);
		Allergies.add(radioAllergiesNo);
		
		// vaccine choice
		JComboBox choiceCB = new JComboBox(Vaccine.values());
		choiceCB.setBounds(179, 420, 167, 25);
		frameQ.getContentPane().add(choiceCB);

		JLabel labelVaccinechoice = new JLabel("Choix du vaccin :");
		labelVaccinechoice.setLabelFor(choiceCB);
		labelVaccinechoice.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelVaccinechoice.setBounds(10, 420, 210, 25);
		frameQ.getContentPane().add(labelVaccinechoice);
		
		// Professional
		JCheckBox prof = new JCheckBox();
		prof.setBounds(179, 460, 25, 21);
		frameQ.getContentPane().add(prof);
		
		JLabel labelProfessional = new JLabel("Info post-vaccination :");
		labelProfessional.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelProfessional.setBounds(10, 460, 210, 25);
		labelProfessional.setLabelFor(prof);
		frameQ.getContentPane().add(labelProfessional);
		
		// has vaccine		
		JRadioButton radioVaccineYes = new JRadioButton("Oui");
		radioVaccineYes.setBounds(179, 500, 50, 21);
		frameQ.getContentPane().add(radioVaccineYes);

		JRadioButton radioVaccineNo = new JRadioButton("Non");
		radioVaccineNo.setBounds(235, 500, 50, 21);
		frameQ.getContentPane().add(radioVaccineNo);
		
		ButtonGroup HasVaccine = new ButtonGroup();
		HasVaccine.add(radioVaccineYes);
		HasVaccine.add(radioVaccineNo);

		JLabel labelHasVaccine = new JLabel("Fait le vaccin :");
		labelHasVaccine.setLabelFor(radioAllergiesYes);
		labelHasVaccine.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelHasVaccine.setBounds(10, 500, 210, 25);
		frameQ.getContentPane().add(labelHasVaccine);

		
		// vaccine Name
		JComboBox vaccineCB = new JComboBox(Vaccine.values());
		vaccineCB.setBounds(179, 540, 167, 25);
		frameQ.getContentPane().add(vaccineCB);

		JLabel labelVaccineName = new JLabel("Nom du vaccin :");
		labelVaccineName.setLabelFor(vaccineCB);
		labelVaccineName.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelVaccineName.setBounds(10, 540, 210, 25);
		frameQ.getContentPane().add(labelVaccineName);
		
		// vaccine code
		textFieldVaccineCode = new JTextField();
		textFieldVaccineCode.setToolTipText("Veuillez entrer le code du vaccin");
		textFieldVaccineCode.setBounds(179, 580, 167, 25);
		frameQ.getContentPane().add(textFieldVaccineCode);
		textFieldVaccineCode.setColumns(10);

		JLabel labelVaccineCode = new JLabel("Code du vaccin :");
		labelVaccineCode.setLabelFor(textFieldVaccineCode);
		labelVaccineCode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		labelVaccineCode.setBounds(10, 580, 210, 25);
		frameQ.getContentPane().add(labelVaccineCode);
		
		// initial condition
		labelHasVaccine.setVisible(false);
		labelVaccineName.setVisible(false);
		labelVaccineCode.setVisible(false);
		radioVaccineYes.setVisible(false);
		radioVaccineNo.setVisible(false);
		textFieldVaccineCode.setVisible(false);
		vaccineCB.setVisible(false);
		
		// access for the rest of the questionnaire
		prof.setSelected(false);
		prof.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if( prof.isSelected()) {
					labelHasVaccine.setVisible(true);
					labelVaccineName.setVisible(true);
					labelVaccineCode.setVisible(true);
					radioVaccineYes.setVisible(true);
					radioVaccineNo.setVisible(true);
					textFieldVaccineCode.setVisible(true);
					vaccineCB.setVisible(true);	
				} else {
				labelHasVaccine.setVisible(false);
				labelVaccineName.setVisible(false);
				labelVaccineCode.setVisible(false);
				radioVaccineYes.setVisible(false);
				radioVaccineNo.setVisible(false);
				textFieldVaccineCode.setVisible(false);
				vaccineCB.setVisible(false);
				}
			}
		});
		
		
		// table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(368, 50, 1011, 322);
		frameQ.getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		// Mouse click table
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				// account Number
				textFieldAccountNumber.setText(dtm.getValueAt(row, 0).toString());
				
				// first name
				textFieldFirstName.setText(dtm.getValueAt(row, 1).toString());
				
				// last name
				textFieldLastName.setText(dtm.getValueAt(row, 2).toString());
				
				// date birth
				try {
					dateBirth.setDate(dateformat.parse(dtm.getValueAt(row, 3).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				// health insurance card
				textFieldHIC.setText(dtm.getValueAt(row, 4).toString());
				
				// visit date
				try {
					dateVisit.setDate(dateformat.parse(dtm.getValueAt(row, 5).toString()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				// has contracted covid
				switch (dtm.getValueAt(row, 6).toString()) {
				case "Oui":
					radioContractedYes.setSelected(true);
					radioContractedNo.setSelected(false);
					break;
				case "Non":
					radioContractedNo.setSelected(true);
					radioContractedYes.setSelected(false);
					break;
				default:
					break;
				}
				
				// has Symptoms
				switch (dtm.getValueAt(row, 7).toString()) {
				case "Oui":
					radioSymptomsYes.setSelected(true);
					radioSymptomsNo.setSelected(false);
					break;
				case "Non":
					radioSymptomsNo.setSelected(true);
					radioSymptomsYes.setSelected(false);
					break;
				default:
					break;
				}
				
				// has Allergies
				switch (dtm.getValueAt(row, 8).toString()) {
				case "Oui":
					radioAllergiesYes.setSelected(true);
					radioAllergiesNo.setSelected(false);
					break;
				case "Non":
					radioAllergiesNo.setSelected(true);
					radioAllergiesYes.setSelected(false);
					break;
				default:
					break;
				}
				// choice vaccine
				switch (dtm.getValueAt(row, 9).toString()) {
				case "NONE":
					choiceCB.setSelectedItem(Vaccine.NONE);
				case "PFIZER":
					choiceCB.setSelectedItem(Vaccine.PFIZER);
					break;
				case "MODERNA":
					choiceCB.setSelectedItem(Vaccine.MODERNA);
					break;
				case "ASTRAZENECA":
					choiceCB.setSelectedItem(Vaccine.ASTRAZENECA);
					break;
				case "JANSSEN":
					choiceCB.setSelectedItem(Vaccine.JANSSEN);
					break;
				default:
					break;
				}
				// has vaccine
				switch (dtm.getValueAt(row, 10).toString()) {
				case "Oui":
					radioVaccineYes.setSelected(true);
					radioVaccineNo.setSelected(false);
					break;
				case "Non":
					radioVaccineYes.setSelected(true);
					radioVaccineNo.setSelected(false);
					break;
				default:
					break;
				}
				
				// vaccine name
				switch (dtm.getValueAt(row, 11).toString()) {
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
				textFieldVaccineCode.setText(dtm.getValueAt(row, 12).toString());
				
			}});
		
		// button to add questionnaire
		JButton addQ = new JButton("Ajouter");
		addQ.setBackground(Color.GREEN);
		addQ.setFont(new Font("Times New Roman", Font.BOLD, 12));
		addQ.setBounds(10, 640, 103, 37);
		frameQ.getContentPane().add(addQ);

		// button to modify questionnaire
		JButton modifyQ = new JButton("Modifier");
		modifyQ.setBackground(Color.ORANGE);
		modifyQ.setFont(new Font("Times New Roman", Font.BOLD, 12));
		modifyQ.setBounds(262, 640, 103, 37);
		frameQ.getContentPane().add(modifyQ);
		
		// button to find VP
		JButton findQuestionnaire = new JButton("Rechercher");
		findQuestionnaire.setFont(new Font("Times New Roman", Font.BOLD, 10));
		findQuestionnaire.setBounds(1276, 396, 103, 25);
		frameQ.getContentPane().add(findQuestionnaire);

		searchField = new JTextField();
		searchField.setToolTipText("rechercher le Questionnaire ici...");
		searchField.setBounds(1071, 396, 175, 25);
		frameQ.getContentPane().add(searchField);
		searchField.setColumns(10);

		// button to refresh table
		JButton refresh = new JButton("Rafraichir");
		refresh.setFont(new Font("Times New Roman", Font.BOLD, 12));
		refresh.setBounds(1267, 574, 112, 31);
		frameQ.getContentPane().add(refresh);
		
		// button return
		JButton returnQ = new JButton("Retour");
		returnQ.setFont(new Font("Times New Roman", Font.BOLD, 12));
		returnQ.setBounds(1116, 646, 112, 31);
		frameQ.getContentPane().add(returnQ);
		
		// button to quit
		JButton quit = new JButton("Quitter");
		quit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		quit.setBounds(1267, 646, 112, 31);
		frameQ.getContentPane().add(quit);

////////////////////////////////////////////////////////////////////////
//Action performed to quit page
///////////////////////////////////////////////////////////////////////
		quit.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							int resultat = confirm.showConfirmDialog(null, "Voulez vous Vraiment quitter ?",
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
//Action performed add Questionnaire
///////////////////////////////////////////////////////////////////////
		addQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean state = true;
				String contracted = "";
				String symptoms = "";
				String allergies = "";
				String hasVaccine = "";
				Date birth = dateBirth.getDate();
				Date visit = dateVisit.getDate();
						
				//account number
				String accountNumber = textFieldAccountNumber.getText();
				labelAccountNumber.setForeground(accountNumber.equals("") ? Color.red : Color.black);
				state = !accountNumber.equals("") && state;
				//first name
				String firstName = textFieldFirstName.getText();
				labelFirstName.setForeground(firstName.equals("") ? Color.red : Color.black);
				state = !firstName.equals("") && state;
				
				// last name
				String lastName = textFieldLastName.getText();
				labelLastName.setForeground(lastName.equals("") ? Color.red : Color.black);
				state = !lastName.equals("") && state;
				// birth date
				labelDateBirth.setForeground(birth == null ? Color.red : Color.black);
				state = !(birth == null) && state;
				// HIC
				String hic = textFieldHIC.getText();
				labelHIC.setForeground(hic.equals("") ? Color.red : Color.black);
				state = !hic.equals("") && state;
				
				// visit date
				labelDateVisit.setForeground(visit == null ? Color.red : Color.black);
				state = !(visit == null) && state;
				// hasContracted
				if (radioContractedYes.isSelected()) {
					state = true;
					contracted = radioContractedYes.getActionCommand();
					labelContracted.setForeground(Color.black);
				} else if (radioContractedNo.isSelected()) {
					state = true;
					labelContracted.setForeground(Color.black);
					contracted = radioContractedNo.getActionCommand();
				} else {
					labelContracted.setForeground(Color.red);
					state = false;
				}
				// hasSymptoms
				if (radioSymptomsYes.isSelected()) {
					state = true;
					symptoms = radioSymptomsYes.getActionCommand();
					labelSymptoms.setForeground(Color.black);
				} else if (radioSymptomsNo.isSelected()) {
					state = true;
					labelSymptoms.setForeground(Color.black);
					symptoms = radioSymptomsNo.getActionCommand();
				} else {
					labelSymptoms.setForeground(Color.red);
					state = false;
				}
				// has allergies
				if (radioAllergiesYes.isSelected()) {
					state = true;
					allergies = radioAllergiesYes.getActionCommand();
					labelAllergies.setForeground(Color.black);
				} else if (radioAllergiesNo.isSelected()) {
					state = true;
					labelAllergies.setForeground(Color.black);
					allergies = radioAllergiesNo.getActionCommand();
				} else {
					labelAllergies.setForeground(Color.red);
					state = false;
				}
				
				// vaccine choice
				String vaccineChoice = (choiceCB.getSelectedItem().toString());
				labelVaccinechoice.setForeground(vaccineChoice.equals("") ? Color.red : Color.black);
				state = !vaccineChoice.equals("") && state;
				
				// has vaccine
				if (radioVaccineYes.isSelected()) {
					state = true;
					hasVaccine = radioVaccineYes.getActionCommand();
					labelHasVaccine.setForeground(Color.black);
				} else if (radioVaccineNo.isSelected()) {
					state = true;
					labelHasVaccine.setForeground(Color.black);
					hasVaccine = radioVaccineNo.getActionCommand();
				} else {
					state = true;
					hasVaccine = radioVaccineNo.getActionCommand();
				}
				
				//  name vaccine
				Vaccine nameVac = Vaccine.valueOf(Vaccine.NONE.toString());
				
				// vaccine code
				String vacCode = "vide";
				
				if (state) {
					try {
						Vaccine vac = Vaccine.valueOf(vaccineChoice);
						Questionnaire q = new Questionnaire(accountNumber, firstName, lastName, dateformat.format(birth),
								hic,dateformat.format(visit), (contracted).equals("Oui"), (symptoms).equals("Oui"), 
								 (allergies).equals("Oui"), vac, (hasVaccine).equals("Oui"),
								 nameVac, vacCode);
						boolean created = QuestionnaireControler.addQuestionnaire(q);
						if (created) {
							// reset field
							textFieldAccountNumber.setText("");
							textFieldFirstName.setText("");
							textFieldLastName.setText("");
							dateBirth.setDate(null);
							textFieldHIC.setText("");
							dateVisit.setDate(null);
							Contracted.clearSelection();
							Symptoms.clearSelection();
							Allergies.clearSelection();
							choiceCB.setSelectedItem(Vaccine.NONE);
							HasVaccine.clearSelection();
							textFieldVaccineCode.setText("");
							
							String message = "Questionnaire ajouté dans le système, Voulez-vous imprimer le Questionnaire?"; //						
							int resultat = confirm.showConfirmDialog(null, message, "Confirmation de création",
									JOptionPane.OK_CANCEL_OPTION);
								if(resultat == 0) {
									JOptionPane.showMessageDialog(null, q.printQuestionnaire());
								} else {
									JOptionPane.showMessageDialog(null, "Document non imprimé");
								}
									
									try {
										CsvQuestionnaire.addOneQ(".\\csv\\QuestionnaireDatabase.csv",
												QuestionnaireControler.getQuestionnaireFiles()
														.get(QuestionnaireControler.getQuestionnaireFiles().size() - 1));
									} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e1) {
										e1.printStackTrace();
									}
										
						} else {
							JOptionPane.showConfirmDialog(null,
									"Le questionnaire n'a pas pu être créé, une erreur est survenu",
									"Création du questionnaire non effectuer", JOptionPane.PLAIN_MESSAGE);

						}
					} catch (Error err) {
						JOptionPane.showConfirmDialog(null, err.getMessage(), "Corriger les erreurs suivantes :",
								JOptionPane.PLAIN_MESSAGE);
					}
				
				showQuestionnaireDetails("");
				}}
		});


////////////////////////////////////////////////////////////////////////
//Action performed modify a questionnaire
///////////////////////////////////////////////////////////////////////		
		modifyQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean state = true;
				String contracted = "";
				String symptoms = "";
				String allergies = "";
				String hasVaccine = "";
				Date birth = dateBirth.getDate();
				Date visit = dateVisit.getDate();
						
				//account number
				String accountNumber = textFieldAccountNumber.getText();
				labelAccountNumber.setForeground(accountNumber.equals("") ? Color.red : Color.black);
				state = !accountNumber.equals("") && state;

				
				//first name
				String firstName = textFieldFirstName.getText();
				labelFirstName.setForeground(firstName.equals("") ? Color.red : Color.black);
				state = !firstName.equals("") && state;
				
				// last name
				String lastName = textFieldLastName.getText();
				labelLastName.setForeground(lastName.equals("") ? Color.red : Color.black);
				state = !lastName.equals("") && state;
				// birth date
				labelDateBirth.setForeground(birth == null ? Color.red : Color.black);
				state = !(birth == null) && state;
				// HIC
				String hic = textFieldHIC.getText();
				labelHIC.setForeground(hic.equals("") ? Color.red : Color.black);
				state = !hic.equals("") && state;
				// visit date
				labelDateVisit.setForeground(visit == null ? Color.red : Color.black);
				state = !(visit == null) && state;
				// hasContracted
				if (radioContractedYes.isSelected()) {
					state = true;
					contracted = radioContractedYes.getActionCommand();
					labelContracted.setForeground(Color.black);
				} else if (radioContractedNo.isSelected()) {
					state = true;
					labelContracted.setForeground(Color.black);
					contracted = radioContractedNo.getActionCommand();
				} else {
					labelContracted.setForeground(Color.red);
					state = false;
				}
				// hasSymptoms
				if (radioSymptomsYes.isSelected()) {
					state = true;
					symptoms = radioSymptomsYes.getActionCommand();
					labelSymptoms.setForeground(Color.black);
				} else if (radioSymptomsNo.isSelected()) {
					state = true;
					labelSymptoms.setForeground(Color.black);
					symptoms = radioSymptomsNo.getActionCommand();
				} else {
					labelSymptoms.setForeground(Color.red);
					state = false;
				}
				// has allergies
				if (radioAllergiesYes.isSelected()) {
					state = true;
					allergies = radioAllergiesYes.getActionCommand();
					labelAllergies.setForeground(Color.black);
				} else if (radioAllergiesNo.isSelected()) {
					state = true;
					labelAllergies.setForeground(Color.black);
					allergies = radioAllergiesNo.getActionCommand();
				} else {
					labelAllergies.setForeground(Color.red);
					state = false;
				}
				
				// vaccine choice
				String vaccineChoice = (choiceCB.getSelectedItem().toString());
				labelVaccinechoice.setForeground(vaccineChoice.equals("") ? Color.red : Color.black);
				state = !vaccineChoice.equals("") && state;
				
				// has vaccine
				if (radioVaccineYes.isSelected()) {
					state = true;
					hasVaccine = radioVaccineYes.getActionCommand();
					labelHasVaccine.setForeground(Color.black);
				} else if (radioVaccineNo.isSelected()) {
					state = true;
					labelHasVaccine.setForeground(Color.black);
					hasVaccine = radioVaccineNo.getActionCommand();
				} else {
					state = true;
					hasVaccine = radioVaccineNo.getActionCommand();
				}
				
				// vaccine name
				String vaccineName = (vaccineCB.getSelectedItem().toString());
				labelVaccineName.setForeground(vaccineName.equals("") ? Color.red : Color.black);
				state = !vaccineName.equals("") && state;
				
				
				//vaccine code
				String vaccineCode = textFieldVaccineCode.getText();
				labelVaccineCode.setForeground(vaccineCode.equals("") ? Color.red : Color.black);
				state = !vaccineCode.equals("") && state;
				
				if(state) {
					boolean modified = QuestionnaireControler.modifyquestionnaire(accountNumber, firstName, lastName, dateformat.format(birth),
										hic,dateformat.format(visit), (contracted).equals("Oui"), (symptoms).equals("Oui"), 
										(allergies).equals("Oui"), Vaccine.valueOf(vaccineChoice), (hasVaccine).equals("Oui"),
										Vaccine.valueOf(vaccineName), vaccineCode);
					if (modified) {
						JOptionPane.showConfirmDialog(null, "Modification enregistrée",
								"Confirmation", JOptionPane.PLAIN_MESSAGE);
						// reset fields
						textFieldAccountNumber.setText("");
						textFieldFirstName.setText("");
						textFieldLastName.setText("");
						dateBirth.setDate(null);
						textFieldHIC.setText("");
						dateVisit.setDate(null);
						Contracted.clearSelection();
						Symptoms.clearSelection();
						Allergies.clearSelection();
						choiceCB.setSelectedItem(Vaccine.NONE);
						HasVaccine.clearSelection();
						textFieldVaccineCode.setText("");
						vaccineCB.setSelectedItem(Vaccine.NONE);
						
						try {
							CsvQuestionnaire.writeQ(".\\csv\\QuestionnaireDatabase.csv",
									QuestionnaireControler.getQuestionnaireFiles());

						} catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | CsvValidationException
								| IOException e1) {
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showConfirmDialog(null, "Le questionnaire n'a pas pu être modifié, une erreur est survenu",
								"Modification du questionnaire non effectuer", JOptionPane.PLAIN_MESSAGE);
					}
				}
				showQuestionnaireDetails("");
								
			}});
		
////////////////////////////////////////////////////////////////////////
//Action performed return
///////////////////////////////////////////////////////////////////////	

		returnQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new MenuEmploye();
							frameQ.dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
////////////////////////////////////////////////////////////////////////
//Action performed refresh table
///////////////////////////////////////////////////////////////////////	

		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showQuestionnaireDetails("");
				searchField.setText("");
				// reset fields
				textFieldAccountNumber.setText("");
				textFieldFirstName.setText("");
				textFieldLastName.setText("");
				dateBirth.setDate(null);
				textFieldHIC.setText("");
				dateVisit.setDate(null);
				Contracted.clearSelection();
				Symptoms.clearSelection();
				Allergies.clearSelection();
				choiceCB.setSelectedItem(Vaccine.NONE);
				HasVaccine.clearSelection();
				textFieldVaccineCode.setText("");
				vaccineCB.setSelectedItem(Vaccine.NONE);
			}
		});

////////////////////////////////////////////////////////////////////////
//Action performed find Questionnaire
///////////////////////////////////////////////////////////////////////	
		findQuestionnaire.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showQuestionnaireDetails(searchField.getText());
			}
		});
		

		frameQ.setVisible(true);
	}
	

}
