package com.supportsys.test;

import static org.junit.Assert.assertEquals;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.supportsys.controller.ClientController;

public class ClientTest {

	@Test
	public void addClient() throws JSONException
	{
		Integer expected = 201;

		String dataClient = "{\"idItem\":\"0\",\"clientName\":\"Nextel\",\"addressClient\":\"Av Chucri Zaidan\",\"clientCity\":\"São Paulo\",\"districtClient\":\"Morumbi\",\"selectState\":\"26\",\"cnpjClient\":\"17.715.807/0001-03\",\"phoneClient\":\"11948112823\",\"emailClient\":\"test@test.de\",\"logoImage\":\"0\",\"levelSelect\":\"10\",\"active\":\"1\",\"description\":\"Client much IT users\",\"resetPassword\":\"0\"}";
		JSONObject jsonItems = new JSONObject(dataClient);

		try {

			Integer addClient = new ClientController().insertItem(jsonItems);
			assertEquals(expected,addClient);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:: " + e);
		}

	}


	@Test
	public void listClients() throws JSONException
	{
		String listClient = new ClientController().getClients("null", "888");
		JSONObject testString = new JSONObject(listClient);
	}
}
