package lib;

import com.opencsv.bean.CsvBindByName;

import Account.ClientControler;


/**
 * Classe permettant de créer un questionnaire, de modifier ses attributs et de
 * l'imprimer.
 * @author Benjamin Roy et Hani Berchan
 * @version 1.0
 */
public class Questionnaire {
	@CsvBindByName(column = "accountNumber")
	private String accountNumber;
	@CsvBindByName(column = "firstName")
	private String firstName;
	@CsvBindByName(column = "lastName")
	private String lastName;
	@CsvBindByName(column = "birthDate")
	private String birthDate;
	@CsvBindByName(column = "healthInsuranceCard")
	private String healthInsuranceCard;
	@CsvBindByName(column = "visitDate")
	private String visitDate;
	@CsvBindByName(column = "hasContractedCovid")
	private boolean hasContractedCovid;
	@CsvBindByName(column = "hasCovidSymptoms")
	private boolean hasCovidSymptoms;
	@CsvBindByName(column = "hasAllergies")
	private boolean hasAllergies;
	@CsvBindByName(column = "choiceVac")
	private Vaccine choiceVac;
	@CsvBindByName(column = "hasVaccine")
	private boolean hasVaccine;
	@CsvBindByName(column = "vaccineName")
	private Vaccine vaccineName;
	@CsvBindByName(column = "vaccineCode")
	private String vaccineCode;
	
	/**
	 * Constructeur permettant de créer un nouveau questionnaire et valide 
	 * si les attributs sont sous un format valide.
	 * @author Hani Berchan
	 * @param accountNumber Numéro de compte du client
	 * @param firstName Prénom
	 * @param lastName Nom
	 * @param birthDate Date de naissance (YYYY-MM-DD)
	 * @param healthInsuranceCard Carte d'assurance maladie, sans espace, à 12 chiffres
	 * @param visitDate Date de visite (YYYY-MM-DD)
	 * @param hasContractedCovid Est-ce que le client à contracter le covid?
	 * @param hasCovidSymptoms Est-ce que le client à des symptômes de covid?
	 * @param hasAllergies Est-ce que le client à des allergies?
	 * @param hasVaccine Est-ce que le client à fait son vaccin?
	 * @param choiceVac Le choix du vaccin
	 * @param vaccineName Le nom du vaccin
	 * @param vaccineCode Le code du vaccin, limite de 24 caractères 
	 */
	public Questionnaire(String accountNumber, String firstName, String lastName, String birthDate, String healthInsuranceCard,
			String visitDate, boolean hasContractedCovid, boolean hasCovidSymptoms, boolean hasAllergies,
			Vaccine choiceVac, boolean hasVaccine,Vaccine vaccineName, String vaccineCode) {
		super();
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.healthInsuranceCard = healthInsuranceCard;
		this.visitDate = visitDate;
		this.hasContractedCovid = hasContractedCovid;
		this.hasCovidSymptoms = hasCovidSymptoms;
		this.hasAllergies = hasAllergies;
		this.hasVaccine = hasVaccine;
		this.choiceVac = choiceVac;
		this.vaccineName = vaccineName;
		this.vaccineCode = vaccineCode;
		validate();
	}
	
	
	public Vaccine getChoiceVac() {
		return choiceVac;
	}


	public void setChoiceVac(Vaccine choiceVac) {
		this.choiceVac = choiceVac;
	}


	public boolean isHasVaccine() {
		return hasVaccine;
	}


	public void setHasVaccine(boolean hasVaccine) {
		this.hasVaccine = hasVaccine;
	}

	/**
	 * Méthode permettant de valider les différents attributs de la classe.
	 * @author Benjamin Roy et Hani Berchan
	 * @throws Error si un ou plusieurs attributs n'est pas sous un bon format.
	 */
	private void validate() {
		String message = "";
        if(!Validator.match("^[0-9]{12}$", accountNumber)) {
            message += "Le numéro de compte doit contenir 12 chiffre.\n";
        }
		
		if(ClientControler.getClient(accountNumber) == null) {
			message += "Le compte n'existe pas.\n"; 
		}
        if(!Validator.match("^[a-zA-ZÀ-ý '-]{1,50}$", firstName)) {
            message += "Le prénom n'est pas valide.\n";
        }
        if(!Validator.match("^[a-zA-ZÀ-ý '-]{1,50}$", lastName)) {
            message += "Le nom n'est pas valide.\n";
        }
        if(!Validator.match("^[0-9]{4}-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", birthDate)) {
            message += "La date de naissance n'est pas valide.";
        }
        if(!Validator.match("^[A-Z0-9]{12}$", healthInsuranceCard)) {
            message += "La carte d'assurance maladie n'est pas valide.\n";
        }
        if(!Validator.match("^[0-9]{4}-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$", visitDate)) {
            message += "La date de visite n'est pas valide.";
        }
        if(message.length() > 0) {
            throw new Error(message);
        }
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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


	public String getHealthInsuranceCard() {
		return healthInsuranceCard;
	}


	public void setHealthInsuranceCard(String healthInsuranceCard) {
		this.healthInsuranceCard = healthInsuranceCard;
	}


	public String getVisitDate() {
		return visitDate;
	}


	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}


	public boolean isHasContractedCovid() {
		return hasContractedCovid;
	}


	public void setHasContractedCovid(boolean hasContractedCovid) {
		this.hasContractedCovid = hasContractedCovid;
	}


	public boolean isHasCovidSymptoms() {
		return hasCovidSymptoms;
	}


	public void setHasCovidSymptoms(boolean hasCovidSymptoms) {
		this.hasCovidSymptoms = hasCovidSymptoms;
	}


	public boolean isHasAllergies() {
		return hasAllergies;
	}


	public void setHasAllergies(boolean hasAllergies) {
		this.hasAllergies = hasAllergies;
	}


	public Vaccine getVaccineName() {
		return vaccineName;
	}


	public void setVaccineName(Vaccine vaccineName) {
		this.vaccineName = vaccineName;
	}


	public String getVaccineCode() {
		return vaccineCode;
	}


	public void setVaccineCode(String vaccineCode) {
		this.vaccineCode = vaccineCode;
	}

	/**
	 * Méthode permettant l'impression du questionnaire
	 * @author Benjamin Roy
	 * @return Un String de document en impression
	 */
	public String printQuestionnaire() {
		return "Document en cours d'impression";
	}
	
}
