import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import Account.Client;
import Account.ClientControler;

/**
 * Classe offrant une interface graphique pour la gestion des clients
 * pour la recherche uniquement. Pour les utilisateurs bénévoles.
 * @author Hani Berchan
 * @version 1.0
 */
public class UIClientReadOnly {

	private JFrame frameCV;
	private JOptionPane confirmer;
	private JTextField searchField;
	

	// variable for table
	int row;
	static DefaultTableModel dtm;
	private JTable table;
	
	/**
	 * Constructeur qui initialize l'interface graphique
	 */
	@SuppressWarnings("serial")
	public UIClientReadOnly() {
		initialize();
		dtm = new DefaultTableModel(new Object[][] {},
				new String[] {"Numéro de compte" ,"Prénom", "Nom", "Naissance", "Adresse Courriel", "Téléphone" ,"Adresse" ,"Code Postal", "Ville"}) {
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
		frameCV = new JFrame("Clients");
		frameCV.setBounds(150, 150, 1000, 605);
		frameCV.getContentPane().setBackground(new Color(0, 191, 255));
		frameCV.getContentPane().setLayout(null);
		frameCV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 45, 908, 322);
		frameCV.getContentPane().add(scrollPane);
				
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		// button to find client
		JButton findCV = new JButton("Rechercher");
		findCV.setFont(new Font("Times New Roman", Font.BOLD, 10));
		findCV.setBounds(310, 423, 103, 25);
		frameCV.getContentPane().add(findCV);
						
		searchField = new JTextField();
		searchField.setToolTipText("rechercher le client ici...");
		searchField.setBounds(54, 422, 175, 25);
		frameCV.getContentPane().add(searchField);
		searchField.setColumns(10);
						
		// bouton to refresh table
		JButton refresh = new JButton("Rafraichir");
		refresh.setFont(new Font("Times New Roman", Font.BOLD, 12));
		refresh.setBounds(835, 436, 112, 31);
		frameCV.getContentPane().add(refresh);
		
		// button to quit
		JButton quit = new JButton("Quitter");
		quit.setFont(new Font("Times New Roman", Font.BOLD, 12));
		quit.setBounds(835, 490, 112, 31);
		frameCV.getContentPane().add(quit);
		   
		// button return
		JButton returnCV = new JButton("Retour");
		returnCV.setFont(new Font("Times New Roman", Font.BOLD, 12));
		returnCV.setBounds(666, 490, 112, 31);
		frameCV.getContentPane().add(returnCV);
		
////////////////////////////////////////////////////////////////////////
//Action performed to quit page
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
//Action performed find client
///////////////////////////////////////////////////////////////////////				

		findCV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				showDetails(searchField.getText());
			}
		});	

		
////////////////////////////////////////////////////////////////////////
//Action performed return table
///////////////////////////////////////////////////////////////////////	

		returnCV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							new MenuVolunteer();
							frameCV.dispose();
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
				showDetails("");
				searchField.setText("");
			}
		});

	frameCV.setVisible(true);
	}	
}
