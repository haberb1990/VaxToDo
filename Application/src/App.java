/* App.java 
 * classe qui permet l'affichage de l'interface graphique:
 * Accès au Menu principal
 */

import java.awt.EventQueue;
import java.io.IOException;
import java.text.ParseException;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;

import Account.AccountRepository;
import Account.ClientControler;
import Account.VolunteerControler;
import lib.QuestionnaireControler;
import lib.ReservationControler;
import lib.VaccinationProfileControler;


/**
 * Classe qui permet de démarrer l'application
 */
public class App {	
	/**
	 * Main qui permet de démarrer l'application.
	 * @param args Les arguments passé à la commande
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static void main(String[] args) throws CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		AccountRepository.initializer();
		ClientControler.initializer();
		VolunteerControler.initializer();
		ReservationControler.initializer();
		QuestionnaireControler.initializerQ();
		VaccinationProfileControler.initializerVP();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new LogIn();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
