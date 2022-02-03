package Test;

import static org.junit.Assert.*; 

import org.junit.Test;
import org.junit.Assert;
import Account.Client;
import Account.ClientControler;

public class ClientControlerTest {

	@Test
	public void testAddClient() {
		Client client = new Client("Benjamin", "Roy", "2000-08-11", "benjamin@roy.name", "1234567890", "123", "123456", "Ville");
		ClientControler.addClient(client);
		Assert.assertTrue(ClientControler.getClients().contains(client));
	}

	@Test
	public void testDeleteClient() {
		Client client = new Client("Benjamin", "Roy", "2000-08-11", "benjamin.linkoer@gmail.com", 
				"1234567890", "890", "890890", "Ville");
		ClientControler.deleteClient(client.getAccountNumber());
		Assert.assertFalse(ClientControler.getClients().contains(client));
	}

}
