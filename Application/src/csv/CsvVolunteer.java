package csv;

import Account.Volunteer;

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

/**
 * Classe permettant de gérer la base de données des bénévoles.
 * @author Hani Berchan et Benjamin Roy
 * @version 1.0
 */
public class CsvVolunteer {

	static SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD");


	/**
	 * Méthode qui récupère tous les bénévoles dans le fichier CSV et les convertis en liste
	 * @author Hani Berchan et Benjamin Roy
	 * @param CSV_FILE_PATH Chemin vers le fichier CSV
	 * @return La liste des bénévoles contenu dans le CSV
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static List<Volunteer> readVolunteerDetails(String CSV_FILE_PATH) throws IOException, CsvValidationException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		// list to add all objects
		List<Volunteer> volunteerList = new ArrayList<>();
		// read file
		try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).build();) {

			String[] nextRecord;

			while ((nextRecord = csvReader.readNext()) != null) {
				String accountNumber = nextRecord[0];
				String firstName = nextRecord[1];
				String lastName = nextRecord[2];
				String birthDate = nextRecord[3];
				String email = nextRecord[4];
				String phone = nextRecord[5];
				String adress = nextRecord[6];
				String zipCode = nextRecord[7];
				String city = nextRecord[8];
				int id = Integer.valueOf(nextRecord[9]);
				String password = nextRecord[10];
				volunteerList.add(
						new Volunteer(accountNumber, firstName, lastName, birthDate, email, phone, adress, zipCode, city, id, password));
			}

		}
		return volunteerList;
	}


	/**
	 * Méthode qui permet d'ajouter un bénévole au CSV.
	 * @author Hani Berchan et Benjamin Roy
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param volunteer Le bénévole a ajouter à la base de données
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void addOneAccount(String SAMPLE_CSV_FILE_PATH, Volunteer volunteer)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "email", "phone", "address",
					"zipCode", "city", "id", "password" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Volunteer.class);
			strategy.setColumnMapping(columns);

			StatefulBeanToCsv<Volunteer> beanToCsv = new StatefulBeanToCsvBuilder<Volunteer>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<Volunteer> myvolunteer = new ArrayList<>();
			myvolunteer.add(volunteer);
			beanToCsv.write(myvolunteer);
		}
	}


	/**
	 * Méthode qui permet de supprimer le fichier CSV
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void deleteFile(String SAMPLE_CSV_FILE_PATH)
			throws IOException, CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
				StandardOpenOption.DELETE_ON_CLOSE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "email", "phone", "address",
					"zipCode", "city", "id", "password" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Volunteer.class);
			strategy.setColumnMapping(columns);

			StatefulBeanToCsv<Volunteer> beanToCsv = new StatefulBeanToCsvBuilder<Volunteer>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<Volunteer> myVolunteer = new ArrayList<>();

			beanToCsv.write(myVolunteer);
		}
	}

	/**
	 * Méthode qui permet de supprimer un bénévole du CSV
	 * @author Hani Berchan et Benjamin Roy
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param accountNumber Numéro de compte du bénévole a supprimer
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	@SuppressWarnings("unchecked")
	public static void removeOneVolunteer(String SAMPLE_CSV_FILE_PATH, String accountNumber) throws IOException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, CsvValidationException, ParseException {
		List<Volunteer> iniVolunteerlist = new ArrayList<>();
		List<Volunteer> newVolunteerlist = new ArrayList<>();
		iniVolunteerlist = readVolunteerDetails(SAMPLE_CSV_FILE_PATH);
		for (Volunteer volunteer : iniVolunteerlist) {
			if (!volunteer.getAccountNumber().equals(accountNumber)) {
				newVolunteerlist.add(volunteer);
			}
		}
		deleteFile(SAMPLE_CSV_FILE_PATH);
		try (BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
						StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "email", "phone", "address",
					"zipCode", "city", "id", "password" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Volunteer.class);
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);

			StatefulBeanToCsv<Volunteer> beanToCsv = new StatefulBeanToCsvBuilder<Volunteer>(writer2)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			beanToCsv.write(newVolunteerlist);
		}

	}

	/**
	 * Méthode qui permet de modifier un bénévole dans le CSV
	 * @author Hani Berchan et Benjamin Roy
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param newVolunteerlist La nouvelle list de bénévole
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void writeVolunteer(String SAMPLE_CSV_FILE_PATH, List<Volunteer> newVolunteerlist)
			throws CsvValidationException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		deleteFile(SAMPLE_CSV_FILE_PATH);

		try (BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
						StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "email", "phone", "address",
					"zipCode", "city", "id", "password" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Volunteer.class);
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);

			StatefulBeanToCsv<Volunteer> beanToCsv = new StatefulBeanToCsvBuilder<Volunteer>(writer2)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			beanToCsv.write(newVolunteerlist);
		}
	}

}
