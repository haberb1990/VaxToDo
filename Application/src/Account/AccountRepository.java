package Account;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import csv.CsvEmployee;

/**
 * Classe permettant de gérer les comptes agents en interagissant avec la base de données
 * @author Benjamin Roy
 * @version 1.0
 */
public class AccountRepository {

	private static List<Agent> agents = new ArrayList<Agent>();
	
	
	/**
	 * Méthode qui initialise la liste des agents en récupérant tout les empoyés existant
	 * dans la base de données.
	 * @author Benjamin Roy
	 * @throws CsvValidationException Signal une erreur si la validation du CSV échoue
	 * @throws CsvDataTypeMismatchException Signal une erreur si le type de donnée n'est pas le bon
	 * @throws CsvRequiredFieldEmptyException Signal une erreur si un field est vide et qu'il est requis
	 * @throws IOException Signal une erreur dans les entrées/sorties
	 * @throws ParseException Signal une erreur si le parse échoue
	 */
	public static void initializer() throws CsvValidationException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException, IOException, ParseException {
		agents = CsvEmployee.readEmployeeDetails(".\\csv\\employeeDatabase.csv");
	}
	
	/**
	 * Méthode qui vérifie si un agents existe.
	 * @author Benjamin Roy
	 * @param id Le code d'iditifiant d'un agent à 9 chiffre
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	public static boolean hasAgent(int id) {
		return agents.stream().anyMatch(agent -> agent.getId() == id);
	}
	
	/**
	 * Méthode qui récupère un agent associé au code d'identifiant et du mot de passe
	 * passé en paramètre.
	 * @author Benjamin Roy
	 * @param id Code d'identifiant à 9 chiffre
	 * @param password Mot de passe composé d'au moins 8 caractères contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caractère spécial.
	 * @return Un agent
	 */
	public static Agent getAgent(int id, String password) {
		return agents.stream().filter(agent -> 
			agent.getId() == id && agent.getPassword().equals(password)
		).findAny().orElse(null);
	}

	/**
	 * Méthode qui ajoute un agent à la liste
	 * @author Benjamin Roy
	 * @param account Agent a ajouter
	 * @return Un boolean qui indique le succès de l'opération. 
	 */
	protected static boolean addAgent(Agent account) {
		return agents.add(account);
	}

	/**
	 * Méthode qui supprimer un bénévole de la liste
	 * @author Benjamin Roy
	 * @param account Bénévole a Supprimer
	 * @return Un boolean qui indique le succès de l'opération.
	 */
	protected static boolean deleteAgent(Volunteer account) {
		return agents.remove(account);
	}
}
