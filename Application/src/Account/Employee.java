package Account;

/**
 * Classe permettant de créer un compte employé.
 * @author Benjamin Roy
 * @version 1.0
 */
public class Employee extends Agent {

	/**
	 * Constructeur permettant de créer un compte employé avec un 
	 * numéro de compte déjà existant.
	 * @author Benjamin Roy
	 * @param accountNumber Numéro de compte à 12 chiffre
	 * @param firstName Prénom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Numéro de téléphone à 10 chiffre
	 * @param address Addresse de domicile du client
	 * @param zipCode Code postal, sans espace, 6 caractères
	 * @param city Nom de la ville
	 * @param id Code d'identification à 9 chiffre
	 * @param password Mot de passe composé d'au moins 8 caractères contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caractère spécial.
	 */
	public Employee(String accountNumber, String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city, int id, String password) {
		super(accountNumber, firstName, lastName, birthDate, email, phone, address, zipCode, city, id, password);
	}
}
