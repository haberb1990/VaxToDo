package csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import com.opencsv.CSVReaderBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import lib.Questionnaire;
import lib.QuestionnaireControler;
import lib.Vaccine;

/**
 * Classe permettant de g�rer la base de donn�es des questionnaires.
 * @author Hani Berchan
 * @version 1.0
 */
public class CsvQuestionnaire {

	static SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD");


	/**
	 * M�thode qui r�cup�re tous les questionnaires dans le fichier CSV et les convertis en liste
	 * @author Hani Berchan
	 * @param CSV_FILE_PATH Chemin vers le fichier CSV
	 * @return La liste des questionnaire contenu dans le CSV
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws ParseException Signal une erreur si le parse �choue
	 */
	public static List<Questionnaire> readQuestionnaireDetails(String CSV_FILE_PATH) throws IOException,
			CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		//list to add all objects
		List<Questionnaire> qList = new ArrayList<>();
		//read file
		try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).build();) {

			String[] nextRecord;

			while ((nextRecord = csvReader.readNext()) != null) {
				String accountNumber = nextRecord[0];
				String firstName = nextRecord[1];
				String lastName = nextRecord[2];
				String birthDate = nextRecord[3];
				String healthInsuranceCard = nextRecord[4];
				String visitDate = nextRecord[5];
				Vaccine choiceVac = Vaccine.valueOf(nextRecord[9]);
				boolean hasContractedCovid = Boolean.parseBoolean(nextRecord[6]);
				boolean hasCovidSymptoms = Boolean.parseBoolean(nextRecord[7]);
				boolean hasAllergies = Boolean.parseBoolean(nextRecord[8]);
				boolean hasVaccine = Boolean.parseBoolean(nextRecord[10]);
				Vaccine vaccineName = Vaccine.valueOf(nextRecord[11]);
				String vaccineCode = nextRecord[12];
				qList.add(new Questionnaire(accountNumber, firstName, lastName, birthDate, healthInsuranceCard,
						visitDate, hasContractedCovid,  hasCovidSymptoms, 
						 hasAllergies,choiceVac, hasVaccine, vaccineName, vaccineCode));
			}

		}
		return qList;
	}



	/**
	 * M�thode qui permet d'ajouter un quesionnaire au CSV.
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param q Le questionnaire a ajouter � la base de donn�es
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void addOneQ(String SAMPLE_CSV_FILE_PATH, Questionnaire q)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "healthInsuranceCard",
					"visitDate", "hasContractedCovid", "hasCovidSymptoms", "hasAllergies","choiceVac","hasVaccine", "vaccineName",
					"vaccineCode" };
			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Questionnaire.class);
			strategy.setColumnMapping(columns);

			StatefulBeanToCsv<Questionnaire> beanToCsv = new StatefulBeanToCsvBuilder<Questionnaire>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<Questionnaire> myQ = new ArrayList<>();
			myQ.add(q);
			beanToCsv.write(myQ);
		}

	}


	/**
	 * M�thode qui permet de supprimer le fichier CSV
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	private static void deleteFile(String SAMPLE_CSV_FILE_PATH)
			throws IOException, CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
				StandardOpenOption.DELETE_ON_CLOSE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "healthInsuranceCard",
					"visitDate", "hasContractedCovid", "hasCovidSymptoms", "hasAllergies","choiceVac","hasVaccine", "vaccineName",
					"vaccineCode" };
			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Questionnaire.class);
			strategy.setColumnMapping(columns);

			StatefulBeanToCsv<Questionnaire> beanToCsv = new StatefulBeanToCsvBuilder<Questionnaire>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<Questionnaire> myQ = new ArrayList<>();

			beanToCsv.write(myQ);
		}
	}

	
	/**
	 * M�thode qui permet de modifier un questionnaire dans le CSV
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param newQList La nouvelle list de questionnaire
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void writeQ(String SAMPLE_CSV_FILE_PATH, List<Questionnaire> newQList)
			throws CsvValidationException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		deleteFile(SAMPLE_CSV_FILE_PATH);

		try (BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
						StandardOpenOption.CREATE);) {
			//columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "healthInsuranceCard",
					"visitDate", "hasContractedCovid", "hasCovidSymptoms", "hasAllergies","choiceVac","hasVaccine", "vaccineName",
					"vaccineCode" };

			//create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Questionnaire.class);
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);

			StatefulBeanToCsv<Questionnaire> beanToCsv = new StatefulBeanToCsvBuilder<Questionnaire>(writer2)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			beanToCsv.write(newQList);
		}
	}
	
	/**
	 * M�thode qui permet de supprimer un questionnaire du CSV
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param accountNumber Num�ro de compte du client associ� au questionnaire
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws ParseException Signal une erreur si le parse �choue
	 */	
	@SuppressWarnings("unchecked")
	public static void removeOneQuestionnaireProfile(String SAMPLE_CSV_FILE_PATH, String accountNumber)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, CsvValidationException,
			ParseException {
		List<Questionnaire> iniQlist = new ArrayList<>();
		List<Questionnaire> newQlist = new ArrayList<>();
		iniQlist = QuestionnaireControler.getQuestionnaireFiles();
		for (Questionnaire q : iniQlist) {
			if (!q.getAccountNumber().equals(accountNumber)) {
				newQlist.add(q);
			}

		}
		deleteFile(SAMPLE_CSV_FILE_PATH);

		try (BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
						StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "healthInsuranceCard",
					"visitDate", "hasContractedCovid", "hasCovidSymptoms", "hasAllergies", "choiceVac","hasVaccine", "vaccineName",
					"vaccineCode" };
			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Questionnaire.class);
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);

			StatefulBeanToCsv<Questionnaire> beanToCsv = new StatefulBeanToCsvBuilder<Questionnaire>(writer2)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			beanToCsv.write(newQlist);
		}

	}

}
