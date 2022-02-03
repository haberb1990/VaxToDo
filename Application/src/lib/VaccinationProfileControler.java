package lib;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import csv.CsvVaccinationProfile;


/**
 * Classe permettant de gérer les profils de vaccination en interagissant avec la base de données
 * @author Hani Berchan
 * @version 1.0
 */
public class VaccinationProfileControler {

	private static List<VaccinationProfile> vaccinationProfiles = new ArrayList<VaccinationProfile>();
	/**
	 * Méthode qui ajoute un profil de vaccination à la liste
	 * @author Benjamin Roy
	 * @param profile profile de vaccination à ajouter
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean addVaccinationProfile(VaccinationProfile profile) {
		return vaccinationProfiles.add(profile);
	}
	/**
	 * Méthode qui initialise la liste des profils de vaccination en récupérant tous
	 * les comptes existants dans la base de données.
	 * @author Hani Berchan
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static void initializerVP() throws CsvValidationException, CsvDataTypeMismatchException, 
	CsvRequiredFieldEmptyException, IOException, ParseException {
		vaccinationProfiles = CsvVaccinationProfile.readVPDetails(".\\csv\\VPDatabase.csv");
	}
	
	public static List<VaccinationProfile> getVaccinationProfiles() {
		return vaccinationProfiles;
	}
	/**
	 * Méthode qui récupère un profil de vaccination de la liste 
	 * @author Benjamin Roy
	 * @param accountNumber Numéro de compte à 12 chiffres
	 * @return Un profil de vaccination associé au numéro de compte.
	 */
	public static VaccinationProfile getVaccinationProfile(String accountNumber) {
		return vaccinationProfiles.stream().filter(vaccinationProfile -> 
			vaccinationProfile.getAccountNumber().equals(accountNumber)
		).findAny().orElse(null);
	}
	/**
	 * Méthode qui modifie un profil de vaccination dans la liste
	 * @author Benjamin Roy et Hani Berchan
	 * @param accountNumber Numéro de compte du client
	 * @param vaccinationDate La date de vaccination (YYYY-MM-DD)
	 * @param dose Le numéro de dose (1 ou 2)
	 * @param vaccineName Le nom du vaccin
	 * @param vaccineCode Le code du vaccin, limite de 24 caractères
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean modifyVaccinationProfile(String accountNumber, String vaccinationDate, int dose, 
			Vaccine vaccineName, String vaccineCode) {
		VaccinationProfile profile = getVaccinationProfile(accountNumber);
		if(vaccinationDate != null) {
			profile.setVaccinationDate(vaccinationDate);
		}
		if(dose > 0) {
			profile.setDose(dose);
		}
		if(vaccineName != null) {
			profile.setVaccineName(vaccineName);
		}
		if(vaccineCode != null) {
			profile.setVaccineCode(vaccineCode);
		}
		return true;
	}
	
	/**
	 * Méthode qui supprime un profil de vaccination de la liste 
	 * @param accountNumber Numéro de compte
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean deleteVaccinationProfile(String accountNumber) {
		VaccinationProfile vp = getVaccinationProfile(accountNumber);
		return vaccinationProfiles.remove(vp);
	}
}
