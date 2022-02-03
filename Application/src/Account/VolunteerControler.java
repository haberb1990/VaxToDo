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
 * Classe permettant de g�rer les comptes b�n�voles en interagissant avec la base de donn�es
 * @author Benjamin Roy
 * @version 1.0
 */
public class VolunteerControler {

	private static List<Volunteer> volunteers = new ArrayList<Volunteer>();
	
	/**
	 * M�thode qui initialise la liste des b�n�voles en r�cup�rant tout les comptes existant
	 * dans la base de donn�es.
	 * @author Benjamin Roy
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws ParseException Signal une erreur si le parse �choue
	 */
	public static void initializer() throws CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		volunteers = CsvVolunteer.readVolunteerDetails(".\\csv\\volunteerDatabase.csv");
		for(Volunteer volunteer : volunteers) {
			AccountRepository.addAgent(volunteer);
		}
	}
	
	/**
	 * M�thode qui ajoute un b�n�vole � la liste et � la liste des comptes agents
	 * @author Benjamin Roy
	 * @param account B�n�vole a ajouter
	 * @return Un boolean qui indique le succ�s de l'op�ration. 
	 */
	public static boolean addVolunteer(Volunteer account) {
		AccountRepository.addAgent(account);
		return volunteers.add(account);
		
	}
	
	
	/**
	 * M�thode qui modifie un b�n�vole pr�sent dans la liste
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
	 * M�thode qui supprimer un b�n�vole de la liste et de la liste 
	 * des comptes agents
	 * @author Benjamin Roy
	 * @param accountNumber Num�ro de compte � 12 chiffre
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	public static boolean deleteVolunteer(String accountNumber) {
		Volunteer account = getVolunteer(accountNumber);
		AccountRepository.deleteAgent(account);
		return volunteers.remove(account);
	}
	
	
	/**
	 * M�thode qui r�cup�re un b�n�vole pr�sent dans la liste
	 * @author Benjamin Roy
	 * @param accountNumber Num�ro de compte � 12 chiffre
	 * @return Un b�n�vole associ� au num�ro de compte.
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
