package csv;

import lib.VaccinationProfile;
import lib.VaccinationProfileControler;
import lib.Vaccine;

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
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

/**
 * Classe permettant de gérer la base de données des profiles de vaccination.
 * @author Hani Berchan
 * @version 1.0
 */
public class CsvVaccinationProfile {


	/**
	 * Méthode qui récupère tous les profiles de vaccination dans le fichier CSV 
	 * et les convertis en liste
	 * @author Hani Berchan
	 * @param CSV_FILE_PATH Chemin vers le fichier CSV
	 * @return La liste des profiles de vaccination contenu dans le CSV
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static List<VaccinationProfile> readVPDetails(String CSV_FILE_PATH) throws IOException,
			CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		// list to add all objects
		List<VaccinationProfile> vpList = new ArrayList<>();
		// read file
		try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).build();) {

			String[] nextRecord;

			while ((nextRecord = csvReader.readNext()) != null) {
				String accountNumber = nextRecord[0];
				String vaccinationDate = nextRecord[1];
				int dose = Integer.parseInt(nextRecord[2]);
				Vaccine vaccineName = Vaccine.valueOf(nextRecord[3]);
				String vaccineCode = nextRecord[4];
				vpList.add(new VaccinationProfile(accountNumber, vaccinationDate, dose, vaccineName, vaccineCode));
			}

		}
		return vpList;
	}


	/**
	 * Méthode qui permet d'ajouter un profile de vaccination au CSV.
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param vp Le profile de vaccination a ajouter à la base de données
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void addOneVP(String SAMPLE_CSV_FILE_PATH, VaccinationProfile vp)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH), StandardOpenOption.APPEND,
				StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "vaccinationDate", "dose", "vaccineName", "vaccineCode" };
			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(VaccinationProfile.class);
			strategy.setColumnMapping(columns);

			StatefulBeanToCsv<VaccinationProfile> beanToCsv = new StatefulBeanToCsvBuilder<VaccinationProfile>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<VaccinationProfile> myVp = new ArrayList<>();
			myVp.add(vp);
			beanToCsv.write(myVp);
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
			String[] columns = { "accountNumber", "vaccinationDate", "dose", "vaccineName", "vaccineCode" };
			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(VaccinationProfile.class);
			strategy.setColumnMapping(columns);

			StatefulBeanToCsv<VaccinationProfile> beanToCsv = new StatefulBeanToCsvBuilder<VaccinationProfile>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<VaccinationProfile> myVp = new ArrayList<>();

			beanToCsv.write(myVp);
		}
	}

	/**
	 * Méthode qui permet de supprimer un profile de vaccination du CSV
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param accountNumber Numéro de compte du client associé au profile de vaccination a supprimer
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	@SuppressWarnings("unchecked")
	public static void removeOneVaccinationProfile(String SAMPLE_CSV_FILE_PATH,
			String accountNumber) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException,
			CsvValidationException, ParseException {
		List<VaccinationProfile> iniVplist = new ArrayList<>();
		List<VaccinationProfile> newVplist = new ArrayList<>();
		iniVplist = VaccinationProfileControler.getVaccinationProfiles();

		for (VaccinationProfile vp : iniVplist) {
			if (!vp.getAccountNumber().equals(accountNumber)) {

				newVplist.add(vp);
			}
		}
		deleteFile(SAMPLE_CSV_FILE_PATH);

		try (BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
					StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "vaccinationDate", "dose", "vaccineName", "vaccineCode" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(VaccinationProfile.class);
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);

			StatefulBeanToCsv<VaccinationProfile> beanToCsv = new StatefulBeanToCsvBuilder<VaccinationProfile>(writer2)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			beanToCsv.write(newVplist);
		}

	}


	/**
	 * Méthode qui permet de modifier un profile de vaccination dans le CSV
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param newVpList La nouvelle list de profile de vaccination
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void writeVP(String SAMPLE_CSV_FILE_PATH, List<VaccinationProfile> newVpList)
			throws CsvValidationException, IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {

		deleteFile(SAMPLE_CSV_FILE_PATH);

		try (BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH), StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "accountNumber", "vaccinationDate", "dose", "vaccineName", "vaccineCode" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(VaccinationProfile.class);
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);

			StatefulBeanToCsv<VaccinationProfile> beanToCsv = new StatefulBeanToCsvBuilder<VaccinationProfile>(writer2)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			beanToCsv.write(newVpList);
		}
	}

}
