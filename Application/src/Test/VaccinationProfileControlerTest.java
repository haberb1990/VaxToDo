package Test;

import org.junit.Assert;
import org.junit.Before;

import lib.Vaccine;

import org.junit.Test;

import Account.Client;
import Account.ClientControler;
import lib.VaccinationProfile;
import lib.VaccinationProfileControler;

public class VaccinationProfileControlerTest {

	@Test
	@Before
	public void testAddVaccinationProfile() {
		Client client = new Client("456344217896", "Benjamin", "Roy", "2000-08-11", "benjamin@roy.name", "1234567890", "123", "123456", "Ville");
		ClientControler.addClient(client);
		VaccinationProfile Vp = new VaccinationProfile("456344217896", "2021-12-11", 2, Vaccine.MODERNA, "123456");
		
		VaccinationProfileControler.addVaccinationProfile(Vp);
		Assert.assertTrue(VaccinationProfileControler.getVaccinationProfiles().contains(Vp));
	}

	@Test
	public void testModifyVaccinationProfile() {
		
		VaccinationProfileControler.modifyVaccinationProfile("456344217896", "2021-12-11", 2, Vaccine.MODERNA,"111156");
		
		Assert.assertTrue(VaccinationProfileControler.getVaccinationProfile("456344217896").getVaccineCode().equals("111156"));
	}

}
