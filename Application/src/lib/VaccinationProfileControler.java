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
 * Classe permettant de g�rer les profils de vaccination en interagissant avec la base de donn�es
 * @author Hani Berchan
 * @version 1.0
 */
public class VaccinationProfileControler {

	private static List<VaccinationProfile> vaccinationProfiles = new ArrayList<VaccinationProfile>();
	/**
	 * M�thode qui ajoute un profil de vaccination � la liste
	 * @author Benjamin Roy
	 * @param profile profile de vaccination � ajouter
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	public static boolean addVaccinationProfile(VaccinationProfile profile) {
		return vaccinationProfiles.add(profile);
	}
	/**
	 * M�thode qui initialise la liste des profils de vaccination en r�cup�rant tous
	 * les comptes existants dans la base de donn�es.
	 * @author Hani Berchan
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws ParseException Signal une erreur si le parse �choue
	 */
	public static void initializerVP() throws CsvValidationException, CsvDataTypeMismatchException, 
	CsvRequiredFieldEmptyException, IOException, ParseException {
		vaccinationProfiles = CsvVaccinationProfile.readVPDetails(".\\csv\\VPDatabase.csv");
	}
	
	public static List<VaccinationProfile> getVaccinationProfiles() {
		return vaccinationProfiles;
	}
	/**
	 * M�thode qui r�cup�re un profil de vaccination de la liste 
	 * @author Benjamin Roy
	 * @param accountNumber Num�ro de compte � 12 chiffres
	 * @return Un profil de vaccination associ� au num�ro de compte.
	 */
	public static VaccinationProfile getVaccinationProfile(String accountNumber) {
		return vaccinationProfiles.stream().filter(vaccinationProfile -> 
			vaccinationProfile.getAccountNumber().equals(accountNumber)
		).findAny().orElse(null);
	}
	/**
	 * M�thode qui modifie un profil de vaccination dans la liste
	 * @author Benjamin Roy et Hani Berchan
	 * @param accountNumber Num�ro de compte du client
	 * @param vaccinationDate La date de vaccination (YYYY-MM-DD)
	 * @param dose Le num�ro de dose (1 ou 2)
	 * @param vaccineName Le nom du vaccin
	 * @param vaccineCode Le code du vaccin, limite de 24 caract�res
	 * @return Un boolean qui indique le succ�s de l'op�ration.
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
	 * M�thode qui supprime un profil de vaccination de la liste 
	 * @param accountNumber Num�ro de compte
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	public static boolean deleteVaccinationProfile(String accountNumber) {
		VaccinationProfile vp = getVaccinationProfile(accountNumber);
		return vaccinationProfiles.remove(vp);
	}
}
