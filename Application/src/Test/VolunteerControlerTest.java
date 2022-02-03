package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import Account.Volunteer;
import Account.VolunteerControler;
import org.junit.Assert;

public class VolunteerControlerTest {

	@Test
	public void testAddVolunteer() {
		Volunteer volunteer = new Volunteer("Hani", "Berchan", "1990-09-22",
				"hani.berchan@umontreal.ca", "5149999999", "1927 Rue Goyer",
				"H5V2Y9", "Laval","Han12345!");
		VolunteerControler.addVolunteer(volunteer);
		Assert.assertTrue(VolunteerControler.getVolunteers().contains(volunteer));
	}

	@Test
	public void testModifyVolunteer() {
		Volunteer volunteer = new Volunteer("Hani", "Berchan", "1990-09-22",
				"hani.berchan@umontreal.ca", "5149999999", "1927 Rue Goyer",
				"H5V2Y9", "Laval","Han12345!");
		VolunteerControler.deleteVolunteer(volunteer.getAccountNumber());
		Assert.assertFalse(VolunteerControler.getVolunteers().contains(volunteer));
	}

}
