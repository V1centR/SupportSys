package com.supportsys.test;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.supportsys.controller.ClientController;

public class ClientTest {

	@Test
	public void listClients() throws JSONException
	{
		String listClient = new ClientController().getClients("null", "888");
		JSONObject testString = new JSONObject(listClient);
	}
}
