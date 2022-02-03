package Account;
import java.util.Random;
import lib.Validator;
import com.opencsv.bean.CsvBindByName;


/**
 * Classe permettant de cr�er un compte, de modifier ses attributs et
 * d'envoy� un courriel de confirmation.
 * @author Benjamin Roy
 * @version 1.0
 */
public class Account {
	//Attributes
	@CsvBindByName(column = "accountNumber")
	private String accountNumber;
	@CsvBindByName(column = "firstName")
	private String firstName;
	@CsvBindByName(column = "lastName")
	private String lastName;
	@CsvBindByName(column = "birthDate")
	private String birthDate;
	@CsvBindByName(column = "email")
	private String email;
	@CsvBindByName(column = "phone")
	private String phone;
	@CsvBindByName(column = "adress")
	private String address;
	@CsvBindByName(column = "zipCode")
	private String zipCode;
	@CsvBindByName(column = "city")
	private String city;
	
	
	//Getters and Setters
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Constructeur permettant de cr�er un nouveau compte avec un num�ro de compte
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
	 */
	public Account(String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city) {
		generateAccountNumber();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		validate();
	}
	
	/**
	 * Constructeur permettant de cr�er un compte avec un num�ro de compte
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
	public Account(String accountNumber, String firstName, String lastName, String birthDate, String email, String phone,
			String address, String zipCode, String city) {
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
	}
	
	/**
	 * M�thode permettant de g�n�rer un num�ro de compte � 12 chiffre al�atoirement.
	 * @author Benjamin Roy
	 */
	private void generateAccountNumber() {
		Random rand = new Random();
		accountNumber = "";
		for(int i = 0; i < 12; i++) {
			accountNumber += rand.nextInt(10);
		}
	}
	
	
	/**
	 * M�thode permettant de valider les diff�rents attributs de la classe.
	 * @author Benjamin Roy
	 * @throws Error si un ou plusieurs attributs n'est pas sous un bon format.
	 */
	private void validate() {
		String message = "";
		if(!Validator.match("^[a-zA-Z�-� '-]{1,50}$", firstName)) {
			message += "Le pr�nom n'est pas valide.\n";
		}
		if(!Validator.match("^[a-zA-Z�-� '-]{1,50}$", lastName)) {
			message += "Le nom n'est pas valide.\n";
		}
		if(!Validator.match("^[0-9]{4}-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", birthDate)) {
			message += "La date n'est pas valide.";
		}
		if(!Validator.match("^[A-Z0-9.]+@[A-Z0-9]+\\.[A-Z0-9.]+", email)) {
			message += "Le email n'est pas valide.\n";
		}
		if(!Validator.match("^[0-9]{10}$", phone)) {
			message += "Le num�ro de t�l�phone doit contenir exactement 10 caract�re.\n";
		}
		if(address.length() > 100) {
			message += "L'adresse peut contenir 100 caract�re maximum\n";
		}
		if(zipCode.length() != 6) {
			message += "Le code postal doit contenir exactement 6 caract�re\n";
		}
		if(city.length() > 100) {
			message += "La ville peut contenir 50 caract�re maximum\n";
		}
		
		if(message.length() > 0) {
			throw new Error(message);
		}
	}
	
	
	/**
	 * M�thode permettant d'envoy� un courriel de confirmation de cr�ation de compte
	 * au courriel du compte.
	 * @author Benjamin Roy
	 * @return Un String de message de succ�s
	 */
	public String sendAccountNumber() {
		return "Le courriel a �t� envoy� �: " + email + " avec succ�s!";
	}
	
}
