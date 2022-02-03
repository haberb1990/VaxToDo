package csv;

import Account.Client;

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
 * Classe permettant de gérer la base de données des clients.
 * @author Hani Berchan
 * @version 1.0
 */
public class CsvClient {

	static SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD");


	/**
	 * Méthode qui récupère tous les clients dans le fichier CSV et les convertis en liste
	 * @author Hani Berchan
	 * @param CSV_FILE_PATH Chemin vers le fichier CSV
	 * @return La liste des clients contenu dans le CSV
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static List<Client> readClientDetails(String CSV_FILE_PATH) throws IOException, CsvValidationException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		// list to add all objects
		List<Client> clientList = new ArrayList<>();
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
				clientList.add(
						new Client(accountNumber, firstName, lastName, birthDate, email, phone, adress, zipCode, city));
			}

		}
		return clientList;
	}


	
	/**
	 * Méthode qui permet d'ajouter un client au CSV.
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param client Le client a ajouter à la base de données
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void addOneAccount(String SAMPLE_CSV_FILE_PATH, Client client)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "email", "phone", "address",
					"zipCode", "city" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Client.class);
			strategy.setColumnMapping(columns);

			StatefulBeanToCsv<Client> beanToCsv = new StatefulBeanToCsvBuilder<Client>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<Client> myclient = new ArrayList<>();
			myclient.add(client);
			beanToCsv.write(myclient);
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
	private static void deleteFile(String SAMPLE_CSV_FILE_PATH)
			throws IOException, CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
				StandardOpenOption.DELETE_ON_CLOSE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "email", "phone", "address",
					"zipCode", "city" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Client.class);
			strategy.setColumnMapping(columns);

			
			StatefulBeanToCsv<Client> beanToCsv = new StatefulBeanToCsvBuilder<Client>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<Client> myClient = new ArrayList<>();

			beanToCsv.write(myClient);
		}
	}


	/**
	 * Méthode qui permet de supprimer un client du CSV
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param accountNumber Numéro de compte du client a supprimer
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	@SuppressWarnings("unchecked")
	public static void removeOneClient(String SAMPLE_CSV_FILE_PATH, String accountNumber) throws IOException,
			CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, CsvValidationException, ParseException {
		List<Client> iniClientlist = new ArrayList<>();
		List<Client> newClientlist = new ArrayList<>();
		iniClientlist = readClientDetails(SAMPLE_CSV_FILE_PATH);

		for (Client client : iniClientlist) {
			if (!client.getAccountNumber().equals(accountNumber)) {
				newClientlist.add(client);
			}
		}
		deleteFile(SAMPLE_CSV_FILE_PATH);

		try (BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
						StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "email", "phone", "address",
					"zipCode", "city" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Client.class);
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);

			StatefulBeanToCsv<Client> beanToCsv = new StatefulBeanToCsvBuilder<Client>(writer2)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			beanToCsv.write(newClientlist);
		}
	}


	/**
	 * Méthode qui permet de modifier un client dans le CSV
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param newClientlist La nouvelle list de client
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void writeClient(String SAMPLE_CSV_FILE_PATH, List<Client> newClientlist)
			throws CsvValidationException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		deleteFile(SAMPLE_CSV_FILE_PATH);

		try (BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
						StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "firstName", "lastName", "birthDate", "email", "phone", "address",
					"zipCode", "city" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Client.class);
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);

			StatefulBeanToCsv<Client> beanToCsv = new StatefulBeanToCsvBuilder<Client>(writer2)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			beanToCsv.write(newClientlist);
		}
	}
}
