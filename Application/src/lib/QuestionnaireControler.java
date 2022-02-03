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
 * Classe permettant de g�rer les questionnaires en interagissant avec la base de donn�es
 * @author Hani Berchan
 * @version 1.0
 */
public class QuestionnaireControler {

	private static List<Questionnaire> questionnaireFiles = new ArrayList<Questionnaire>();
	
	/**
	 * M�thode qui ajoute un questionnaire � la liste
	 * @author Hani Berchan
	 * @param file Questionnaire � ajouter
	 * @return Un boolean qui indique le succ�s de l'op�ration. 
	 */
	public static boolean addQuestionnaire(Questionnaire file) {
		return questionnaireFiles.add(file);
	}
	
	/**
	 * M�thode qui initialise la liste des questionnaires en r�cup�rant tous les comptes existant
	 * dans la base de donn�es.
	 * @author Hani Berchan
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws ParseException Signal une erreur si le parse �choue
	 */
	public static void initializerQ() throws CsvValidationException, CsvDataTypeMismatchException, 
	CsvRequiredFieldEmptyException, IOException, ParseException {
		questionnaireFiles = CsvQuestionnaire.readQuestionnaireDetails(".\\csv\\QuestionnaireDatabase.csv");
	}

	public static List<Questionnaire> getQuestionnaireFiles() {
		return questionnaireFiles;
	}
	/**
	 * M�thode qui r�cup�re un questionnaire pr�sent dans la liste
	 * @author Hani Berchan
	 * @param accountNumber Num�ro de compte � 12 chiffres
	 * @return Un questionnaire associ� au num�ro de compte.
	 */
	public static Questionnaire getQuestionnaire(String accountNumber) {
		return questionnaireFiles.stream().filter(quest -> 
			quest.getAccountNumber().equals(accountNumber)
		).findAny().orElse(null);
	}
	
	/**
	 * M�thode qui modifie un questionnaire dans la liste
	 * @author Hani Berchan
	 * @param accountNumber Num�ro de compte du client
	 * @param firstName Pr�nom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param healthInsuranceCard Carte d'assurance maladie, sans espace, � 12 chiffres
	 * @param visitDate Date de visite (YYYY-MM-DD)
	 * @param hasContractedCovid Est-ce que le client � contracter le covid?
	 * @param hasCovidSymptoms Est-ce que le client � des sympt�mes de covid?
	 * @param hasAllergies Est-ce que le client � des allergies?
	 * @param hasVaccine Est-ce que le client � fait son vaccin?
	 * @param choiceVac Le choix du vaccin
	 * @param vaccineName Le nom du vaccin
	 * @param vaccineCode Le code du vaccin, limite de 24 caract�res
	 * @return Un boolean qui indique le succ�s de l'op�ration.
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
	 * M�thode qui supprime un questionnaire de la liste 
	 * @param accountNumber Num�ro de compte
	 * @return Un boolean qui indique le succ�s de l'op�ration.
	 */
	public static boolean deleteQuestionnaire(String accountNumber) {
		Questionnaire q = getQuestionnaire(accountNumber);
		return questionnaireFiles.remove(q);
	}
}
