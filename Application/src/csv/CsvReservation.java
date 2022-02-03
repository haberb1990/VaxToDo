package csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import lib.Reservation;

/**
 * Classe permettant de g�rer la base de donn�es des r�servations.
 * @author Hani Berchan
 * @version 1.0
 */
public class CsvReservation {


	/**
	 * M�thode qui r�cup�re toutes les r�servations dans le fichier CSV et les convertis en liste
	 * @author Hani Berchan
	 * @param CSV_FILE_PATH Chemin vers le fichier CSV
	 * @return La liste des r�servations contenu dans le CSV
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws ParseException Signal une erreur si le parse �choue
	 */
	public static List<Reservation> readReservationDetails(String CSV_FILE_PATH) throws IOException, CsvValidationException,
				CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		// list to add all objects
		List<Reservation> reservationList = new ArrayList<>();
		// read file
		try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
				CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).build();) {
			String[] nextRecord;
			while ((nextRecord = csvReader.readNext()) != null) {
				String reservationNumber = nextRecord[0];
				String firstName = nextRecord[1];
				String lastName = nextRecord[2];
				String visitDate = nextRecord[3];
				String time = nextRecord[4];
				int dose = Integer.parseInt(nextRecord[5]);
				String email = nextRecord[6];
				
				reservationList.add(
						new Reservation(reservationNumber, firstName, lastName, visitDate, time, dose, email)
				);
			}
		}
		return reservationList;
	}


	/**
	 * M�thode qui permet d'ajouter une r�servation au CSV.
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param reservation La r�servation a ajouter � la base de donn�es
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 */
	@SuppressWarnings("unchecked")
	public static void addOneReservation(String SAMPLE_CSV_FILE_PATH, Reservation reservation)
			throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH), 
				StandardOpenOption.APPEND, StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "reservationNumber", "firstName", "lastName", "visitDate", "time", 
					"dose", "email" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Reservation.class);
			strategy.setColumnMapping(columns);

			StatefulBeanToCsv<Reservation> beanToCsv = new StatefulBeanToCsvBuilder<Reservation>(writer)
					.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
					.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
					.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<Reservation> myreservation = new ArrayList<>();
			myreservation.add(reservation);
			beanToCsv.write(myreservation);
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
			String[] columns = { "reservationNumber", "firstName", "lastName", "visitDate", "time", 
					"dose", "email" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Reservation.class);
			strategy.setColumnMapping(columns);

			StatefulBeanToCsv<Reservation> beanToCsv = new StatefulBeanToCsvBuilder<Reservation>(writer)
			.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
			.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
			.withOrderedResults(false).withMappingStrategy(strategy).build();

			List<Reservation> myReservation = new ArrayList<>();

			beanToCsv.write(myReservation);
		}
	}

	/**
	 * M�thode qui permet de supprimer une r�servation du CSV
	 * @author Hani Berchan
	 * @param SAMPLE_CSV_FILE_PATH Chemin vers le fichier CSV
	 * @param reservationNumber Num�ro de r�servation du rendez-vous a supprimer
	 * @throws IOException Signal une erreur dans les entr�es/sorties
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donn�e n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws CsvValidationException Signal une erreur si la validation du CSV �choue
	 * @throws ParseException Signal une erreur si le parse �choue
	 */	
	@SuppressWarnings("unchecked")
	public static void removeOneReservation(String SAMPLE_CSV_FILE_PATH, String reservationNumber) throws IOException,
	CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, CsvValidationException, ParseException {
		List<Reservation> iniReservationlist = new ArrayList<>();
		List<Reservation> newReservationlist = new ArrayList<>();
		iniReservationlist = readReservationDetails(SAMPLE_CSV_FILE_PATH);

		for (Reservation reservation : iniReservationlist) {
			if (!reservation.getReservationNumber().equals(reservationNumber)) {
				newReservationlist.add(reservation);
			}
		}
		deleteFile(SAMPLE_CSV_FILE_PATH);

		try (BufferedWriter writer2 = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE_PATH),
				StandardOpenOption.CREATE);) {
			// columns name
			String[] columns = { "reservationNumber", "firstName", "lastName", "visitDate", "time", 
					"dose", "email" };

			// create a mapping strategy
			@SuppressWarnings("rawtypes")
			ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
			strategy.setType(Reservation.class);
			strategy.setColumnMapping(columns);
			strategy.generateHeader(columns);

			StatefulBeanToCsv<Reservation> beanToCsv = new StatefulBeanToCsvBuilder<Reservation>(writer2)
				.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
				.withEscapechar(CSVWriter.DEFAULT_ESCAPE_CHARACTER).withLineEnd(CSVWriter.DEFAULT_LINE_END)
				.withOrderedResults(false).withMappingStrategy(strategy).build();

			beanToCsv.write(newReservationlist);
		}
	}
}
