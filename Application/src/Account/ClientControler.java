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
 * Classe permettant de gérer les comptes clients en interagissant avec la base de données
 * @author Benjamin Roy
 * @version 1.0
 */
public class ClientControler {

	private static List<Client> clients = new ArrayList<Client>();

	
	/**
	 * Méthode qui initialise la liste des clients en récupérant tout les comptes existant
	 * dans la base de données.
	 * @author Benjamin Roy
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static void initializer() throws CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		clients = CsvClient.readClientDetails(".\\csv\\clientDatabase.csv");
	}
	
	public static List<Client> getClients(){
		return clients;
	}

	/**
	 * Méthode qui ajoute un client à la liste
	 * @author Benjamin Roy
	 * @param account Client a ajouter
	 * @return Un boolean qui indique le succès de l'opération. 
	 */
	public static boolean addClient(Client account) {
		return clients.add(account);
	}
	
	/**
	 * Méthode qui modifie un client présent dans la liste
	 * @author Benjamin Roy
	 * @param accountNumber Numéro de compte à 12 chiffre
	 * @param firstName Prénom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Numéro de téléphone à 10 chiffre
	 * @param address L'addresse 
	 * @param zipCode Code postal, sans espace, 6 caractères
	 * @param city Nom de la ville
	 * @return Un boolean qui indique le succès de l'opération.
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
	 * Méthode qui supprimer un client de la liste
	 * @author Benjamin Roy
	 * @param accountNumber Numéro de compte à 12 chiffre
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean deleteClient(String accountNumber) {
		Client account = getClient(accountNumber);
		return clients.remove(account);
		
	}
	
	/**
	 * Méthode qui récupère un client présent dans la liste
	 * @author Benjamin Roy
	 * @param accountNumber Numéro de compte à 12 chiffre
	 * @return Un client associé au numéro de compte.
	 */
	public static Client getClient(String accountNumber) {
		return clients.stream().filter(client -> client.getAccountNumber().equals(accountNumber))
				.findAny().orElse(null);
	}

}
