package csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import Account.Agent;
import Account.Employee;

/**
 * Classe permettant de gérer la base de données des employés.
 * @author Benjamin Roy et Hani Berchan
 * @version 1.0
 */
public class CsvEmployee {
	static SimpleDateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD");


	/**
	 * Méthode qui récupère tous les employés dans le fichier CSV et les convertis en liste
	 * @author Benjamin Roy et Hani Berchan
	 * @param CSV_FILE_PATH Chemin vers le fichier CSV
	 * @return La liste des employés contenu dans le CSV
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur dans les fields vide requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static List<Agent> readEmployeeDetails(String CSV_FILE_PATH) throws IOException, CsvValidationException,
	CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, ParseException {
		// list to add all objects
		List<Agent> employeeList = new ArrayList<>();
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
				employeeList.add(
						new Employee(accountNumber, firstName, lastName, birthDate, email, phone, adress, zipCode, city, id, password)
				);
			}

		}
		return employeeList;
	}
}
