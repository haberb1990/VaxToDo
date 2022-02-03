package Account;

/**
 * Classe permettant de créer un compte client.
 * @author Benjamin Roy
 * @version 1.0
 */
public class Client extends Account {
	
	/**
	 * Constructeur permettant de créer un nouveau compte client avec un numéro de compte
	 * générer aléatoirement puis valide si les attributs sont sous un format valide.
	 * @author Benjamin Roy
	 * @throws Error si un ou plusieurs attributs ne sont pas sous un bon format.
	 * @param firstName Prénom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Numéro de téléphone à 10 chiffre
	 * @param address L'addresse 
	 * @param zipCode Code postal, sans espace, 6 caractères
	 * @param city Nom de la ville
	 */
	public Client(String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city) {
		super(firstName, lastName, birthDate, email, phone, address, zipCode, city);
	}

	
	/**
	 * Constructeur permettant de créer un compte client avec un numéro de compte
	 * déjà existant.
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
	 */
	public Client(String accountNumber, String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city) {
		super(accountNumber, firstName, lastName, birthDate, email, phone, address, zipCode, city);
	}
	
	@Override
	public String toString() {
		return getAccountNumber() + ", " + getFirstName() + " " + getLastName() + ", naissance:" + 
				getBirthDate() + ", " + getEmail() + ", " + getPhone();
	}

}
