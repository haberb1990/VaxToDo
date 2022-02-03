package Account;

/**
 * Classe permettant de cr�er un compte b�n�vole.
 * @author Benjamin Roy
 * @version 1.0
 */
public class Volunteer extends Agent {
	
	private Schedule[] schedule;

	/**
	 * Constructeur permettant de cr�er un nouveau compte b�n�vole avec un num�ro de compte
	 * g�n�rer al�atoirement puis valide si les attributs sont sous un format valide.
	 * @author Benjamin Roy
	 * @throws Error si un ou plusieurs attributs n'est pas sous un bon format.
	 * @param firstName Pr�nom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param email Courriel
	 * @param phone Num�ro de t�l�phone � 10 chiffre
	 * @param address L'addresse 
	 * @param zipCode Code postal, sans espace, 6 caract�res
	 * @param city Nom de la ville
	 * @param password Mot de passe compos� d'au moins 8 caract�res contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caract�re sp�cial.
	 */
	public Volunteer(String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city, String password) {
		super(firstName, lastName, birthDate, email, phone, address, zipCode, city, password);
	}

	/**
	 * Constructeur permettant de cr�er un compte agent avec un num�ro de compte
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
	 * @param id Code d'identification � 9 chiffre
	 * @param password Mot de passe compos� d'au moins 8 caract�res contenant au moins 1 chiffre, 1 majuscule, 1 minuscule et 1 caract�re sp�cial.
	 */
	public Volunteer(String accountNumber, String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city, int id, String password) {
		super(accountNumber, firstName, lastName, birthDate, email, phone, address, zipCode, city,
				id, password);

	}
	
	@Override
	public String toString() {
		return getAccountNumber() + ", " + getFirstName() + " " + getLastName() + ", naissance:" + 
				getBirthDate() + ", " + getEmail() + ", " + getPhone() + ", " + getAddress() + 
				", " + getZipCode() + ", " + getCity();
	}
}
