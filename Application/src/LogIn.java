import java.awt.Color;
import javax.swing.JTextField;

import Account.Agent;
import Account.Employee;
import Account.LogInControler;
import Account.Volunteer;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe offrant une interface graphique pour la connexion au système
 * @author Benjamin Roy
 * @version 1.0
 */
public class LogIn {

	private JFrame frameLogin;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private JLabel labelUser;
	private JLabel labelpassword;
	private JOptionPane confirmer;

	/**
	 * Constructeur qui initialize l'interface graphique
	 */
	public LogIn() {
		initialize();
	}

	/**
	 * Méthode qui initialize l'interface graphique
	 */
	private void initialize() {
		frameLogin = new JFrame();
		frameLogin.getContentPane().setBackground(new Color(0, 191, 255));
		frameLogin.getContentPane().setLayout(null);
		frameLogin.setTitle("Log in");
		frameLogin.setBounds(100, 100, 450, 300);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// label for the Title
		JLabel labelTitre = new JLabel("Connection");
		labelTitre.setFont(new Font("Times New Roman", Font.BOLD, 16));
		labelTitre.setBounds(138, 10, 170, 13);
		frameLogin.getContentPane().add(labelTitre);
						
		// textfield for the username
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(193, 92, 96, 19);
		frameLogin.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		// textField for the password
		passwordField = new JPasswordField();
		passwordField.setBounds(193, 121, 96, 19);
		frameLogin.getContentPane().add(passwordField);

		// label for the usename
		labelUser = new JLabel("Code d'identification:");
		labelUser.setLabelFor(textFieldUsername);
		labelUser.setBounds(42, 95, 141, 19);
		frameLogin.getContentPane().add(labelUser);

		// label for the password
		labelpassword = new JLabel("Mot de passe :");
		labelpassword.setLabelFor(passwordField);
		labelpassword.setBounds(71, 121, 87, 19);
		frameLogin.getContentPane().add(labelpassword);	
		   
		// buttton to log in
		JButton btnLogin = new JButton("Connecter");
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnLogin.setBounds(153, 175, 109, 21);
		frameLogin.getContentPane().add(btnLogin);
			
		// Button to quit
		JButton btnQuit = new JButton("Quitter");
		btnQuit.setBounds(326, 232, 85, 21);
		frameLogin.getContentPane().add(btnQuit);
			
		// Actionperformed quit
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("static-access")
				int resultat = confirmer.showConfirmDialog(null, "Voulez vous Vraiment quitter VaxToDo?", 
						"Quitter", JOptionPane.OK_CANCEL_OPTION);
				if (resultat == 0) { // Yes
					System.exit(0);
				}
			}
		});	
			
		// actionperformed log in 
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldUsername.getText().equals("") || passwordField.getPassword() == null) {
					JOptionPane.showMessageDialog(null, "L'un des champ est vide.");
					return;
				}
				try {
					int id = Integer.parseInt(textFieldUsername.getText());
					String password = String.valueOf(passwordField.getPassword());		
					Agent agent = LogInControler.login(id, password);
					if(agent == null) {
						throw new Error();
					}
					JOptionPane.showMessageDialog(null, "Connection Réussite");
				 	if(agent instanceof Employee) {
						new MenuEmploye();
					}
					else {
						new MenuVolunteer();
					}
				 	frameLogin.dispose();
				}
				catch (Error err) {
					JOptionPane.showMessageDialog(null, "Connection Echouée");
				}
				catch(NumberFormatException exeption) {
					JOptionPane.showMessageDialog(null, "Le code d'identification doit être un code à 9 chiffre");
				}
			}
		});
		frameLogin.setVisible(true);
	}
}
