package Account;

/**
 * Classe permettant de cr�er un compte client.
 * @author Benjamin Roy
 * @version 1.0
 */
public class Client extends Account {
	
	/**
	 * Constructeur permettant de cr�er un nouveau compte client avec un num�ro de compte
	 * g�n�rer al�atoirement puis valide si les attributs sont sous un format valide.
	 * @author Benjamin Roy
	 * @throws Error si un ou plusieurs attributs ne sont pas sous un bon format.
	 * @param firstName Pr�nom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Num�ro de t�l�phone � 10 chiffre
	 * @param address L'addresse 
	 * @param zipCode Code postal, sans espace, 6 caract�res
	 * @param city Nom de la ville
	 */
	public Client(String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city) {
		super(firstName, lastName, birthDate, email, phone, address, zipCode, city);
	}

	
	/**
	 * Constructeur permettant de cr�er un compte client avec un num�ro de compte
	 * d�j� existant.
	 * @author Benjamin Roy
	 * @param accountNumber Num�ro de compte � 12 chiffre
	 * @param firstName Pr�nom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Num�ro de t�l�phone � 10 chiffre
	 * @param address Addresse de domicile du client
	 * @param zipCode Code postal, sans espace, 6 caract�res
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
