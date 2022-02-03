package Account;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import csv.CsvVolunteer;

/**
 * Classe permettant de gérer les comptes bénévoles en interagissant avec la base de données
 * @author Benjamin Roy
 * @version 1.0
 */
public class VolunteerControler {

	private static List<Volunteer> volunteers = new ArrayList<Volunteer>();
	
	/**
	 * Méthode qui initialise la liste des bénévoles en récupérant tout les comptes existant
	 * dans la base de données.
	 * @author Benjamin Roy
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static void initializer() throws CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		volunteers = CsvVolunteer.readVolunteerDetails(".\\csv\\volunteerDatabase.csv");
		for(Volunteer volunteer : volunteers) {
			AccountRepository.addAgent(volunteer);
		}
	}
	
	/**
	 * Méthode qui ajoute un bénévole à la liste et à la liste des comptes agents
	 * @author Benjamin Roy
	 * @param account Bénévole a ajouter
	 * @return Un boolean qui indique le succès de l'opération. 
	 */
	public static boolean addVolunteer(Volunteer account) {
		AccountRepository.addAgent(account);
		return volunteers.add(account);
		
	}
	
	
	/**
	 * Méthode qui modifie un bénévole présent dans la liste
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
	public static boolean modifyVolunteer(String accountNumber, String firstName, String lastName, 
			String birthDate, String email, String phone, String address, String zipCode, String city) {
		Volunteer account = getVolunteer(accountNumber);
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
		if(address != null) {
			account.setAddress(address);
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
	 * Méthode qui supprimer un bénévole de la liste et de la liste 
	 * des comptes agents
	 * @author Benjamin Roy
	 * @param accountNumber Numéro de compte à 12 chiffre
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean deleteVolunteer(String accountNumber) {
		Volunteer account = getVolunteer(accountNumber);
		AccountRepository.deleteAgent(account);
		return volunteers.remove(account);
	}
	
	
	/**
	 * Méthode qui récupère un bénévole présent dans la liste
	 * @author Benjamin Roy
	 * @param accountNumber Numéro de compte à 12 chiffre
	 * @return Un bénévole associé au numéro de compte.
	 */
	public static Volunteer getVolunteer(String accountNumber) {
		return volunteers.stream().filter(
				volunteer -> volunteer.getAccountNumber().equals(accountNumber)
			).findAny().orElse(null);
	}

	
	public static List<Volunteer> getVolunteers() {
		return volunteers;
	}
	
}
