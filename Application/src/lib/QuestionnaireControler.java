package lib;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import csv.CsvQuestionnaire;

/**
 * Classe permettant de gérer les questionnaires en interagissant avec la base de données
 * @author Hani Berchan
 * @version 1.0
 */
public class QuestionnaireControler {

	private static List<Questionnaire> questionnaireFiles = new ArrayList<Questionnaire>();
	
	/**
	 * Méthode qui ajoute un questionnaire à la liste
	 * @author Hani Berchan
	 * @param file Questionnaire à ajouter
	 * @return Un boolean qui indique le succès de l'opération. 
	 */
	public static boolean addQuestionnaire(Questionnaire file) {
		return questionnaireFiles.add(file);
	}
	
	/**
	 * Méthode qui initialise la liste des questionnaires en récupérant tous les comptes existant
	 * dans la base de données.
	 * @author Hani Berchan
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static void initializerQ() throws CsvValidationException, CsvDataTypeMismatchException, 
	CsvRequiredFieldEmptyException, IOException, ParseException {
		questionnaireFiles = CsvQuestionnaire.readQuestionnaireDetails(".\\csv\\QuestionnaireDatabase.csv");
	}

	public static List<Questionnaire> getQuestionnaireFiles() {
		return questionnaireFiles;
	}
	/**
	 * Méthode qui récupère un questionnaire présent dans la liste
	 * @author Hani Berchan
	 * @param accountNumber Numéro de compte à 12 chiffres
	 * @return Un questionnaire associé au numéro de compte.
	 */
	public static Questionnaire getQuestionnaire(String accountNumber) {
		return questionnaireFiles.stream().filter(quest -> 
			quest.getAccountNumber().equals(accountNumber)
		).findAny().orElse(null);
	}
	
	/**
	 * Méthode qui modifie un questionnaire dans la liste
	 * @author Hani Berchan
	 * @param accountNumber Numéro de compte du client
	 * @param firstName Prénom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param healthInsuranceCard Carte d'assurance maladie, sans espace, à 12 chiffres
	 * @param visitDate Date de visite (YYYY-MM-DD)
	 * @param hasContractedCovid Est-ce que le client à contracter le covid?
	 * @param hasCovidSymptoms Est-ce que le client à des symptômes de covid?
	 * @param hasAllergies Est-ce que le client à des allergies?
	 * @param hasVaccine Est-ce que le client à fait son vaccin?
	 * @param choiceVac Le choix du vaccin
	 * @param vaccineName Le nom du vaccin
	 * @param vaccineCode Le code du vaccin, limite de 24 caractères
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean modifyquestionnaire(String accountNumber, String firstName, String lastName, String birthDate, String healthInsuranceCard,
			String visitDate, boolean hasContractedCovid, boolean hasCovidSymptoms, boolean hasAllergies,
			Vaccine choiceVac, boolean hasVaccine,Vaccine vaccineName, String vaccineCode) {
		Questionnaire questionnaire = getQuestionnaire(accountNumber);
			questionnaire.setHasVaccine(hasVaccine);
			
			if(vaccineCode != null) {
			questionnaire.setVaccineCode(vaccineCode);
			}
			
			questionnaire.setVaccineName(vaccineName);
		
		return true;
	}
	/**
	 * Méthode qui supprime un questionnaire de la liste 
	 * @param accountNumber Numéro de compte
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean deleteQuestionnaire(String accountNumber) {
		Questionnaire q = getQuestionnaire(accountNumber);
		return questionnaireFiles.remove(q);
	}
}
