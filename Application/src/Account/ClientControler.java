package Account;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import csv.CsvClient;

/**
 * Classe permettant de g�rer les comptes clients en interagissant avec la base de donn�es
 * @author Benjamin Roy
 * @version 1.0
 */
public class ClientControler {

	private static List<Client> clients = new ArrayList<Client>();

	
	/**
	 * M�thode qui initialise la liste des clients en r�cup�rant tout les comptes existant
	 * dans la base de donn�es.
	 * @author Benjamin Roy
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws ParseException Signal une erreur si le parse �choue
	 */
	public static void initializer() throws CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		clients = CsvClient.readClientDetails(".\\csv\\clientDatabase.csv");
	}
	
	public static List<Client> getClients(){
		return clients;
	}

	/**
	 * M�thode qui ajoute un client � la liste
	 * @author Benjamin Roy
	 * @param account Client a ajouter
	 * @return Un boolean qui indique le succ�s de l'op�ration. 
	 */
	public static boolean addClient(Client account) {
		return clients.add(account);
	}
	
	/**
	 * M�thode qui modifie un client pr�sent dans la liste
	 * @author Benjamin Roy
	 * @param accountNumber Num�ro de compte � 12 chiffre
	 * @param firstName Pr�nom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Num�ro de t�l�phone � 10 chiffre
	 * @param address L'addresse 
	 * @param zipCode Code postal, sans espace, 6 caract�res
	 * @param city Nom de la ville
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	public static boolean modifyClient(String accountNumber, String firstName, String lastName, 
			String birthDate, String email, String phone, String address, String zipCode, String city) {
		Client account = getClient(accountNumber);
		if(firstName != null) {
			account.setFirstName(firstName);
		}
		if(lastName != null) {
			account.setLastName(lastName);
		}
		if(birthDate != null) {
			account.setBirthDate(birthDate);
		}
		if(email != null) {
			account.setEmail(email);
		}
		if(phone != null) {
			account.setPhone(phone);
		}
		if(zipCode != null) {
			account.setZipCode(zipCode);
		}
		if(city != null) {
			account.setCity(city);
		}
		return true;
		
	}
	
	/**
	 * M�thode qui supprimer un client de la liste
	 * @author Benjamin Roy
	 * @param accountNumber Num�ro de compte � 12 chiffre
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	public static boolean deleteClient(String accountNumber) {
		Client account = getClient(accountNumber);
		return clients.remove(account);
		
	}
	
	/**
	 * M�thode qui r�cup�re un client pr�sent dans la liste
	 * @author Benjamin Roy
	 * @param accountNumber Num�ro de compte � 12 chiffre
	 * @return Un client associ� au num�ro de compte.
	 */
	public static Client getClient(String accountNumber) {
		return clients.stream().filter(client -> client.getAccountNumber().equals(accountNumber))
				.findAny().orElse(null);
	}

}
