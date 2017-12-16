package com.supportsys.test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.JSONException;
import org.junit.Test;

import com.supportsys.controller.UserController;
import com.supportsys.entity.Client;
import com.supportsys.entity.User;
import com.supportsys.model.UserModel;

public class AddUserTest {

	@Test
	public void addUsersTest() throws JSONException {

		Integer numberOfUser = 50;

		String jsonItems = "";
		String gender = "";
		Integer confirmValue = 201;


		String name = "";
		String sName = "";
		String emailReady = "";
		String emailUserOk = "";

		String nameToEmail = "";
		String sNameToEmail = "";


		List<Client> clientList = new UserModel().getAllClients();


		for(Integer i=0; i<=numberOfUser;i++) {

			try {
				int randomClient = new Random().nextInt(clientList.size());
				Client item = clientList.get(randomClient);

				String domainEmail =  item.getName().toLowerCase().replaceAll("á|à|â|ã|ä","a").replaceAll("é|è|ê|ë","e").replaceAll("ó|ò|ô|õ","o").replaceAll("ç","c").replaceAll("í|ì|ĩ|î","i").replaceAll("[^a-zA-Z]+","");

				 name = name();
				 sName = sobreNome();

				 nameToEmail = name().replaceAll("á|à|â|ã|ä","a").replaceAll("é|è|ê|ë","e").replaceAll("ó|ò|ô|õ","o").replaceAll("ç","c").replaceAll("í|ì|ĩ|î","i");
				 sNameToEmail = sobreNome().replaceAll("á|à|â|ã|ä","a").replaceAll("é|è|ê|ë","e").replaceAll("ó|ò|ô|õ","o").replaceAll("ç","c").replaceAll("í|ì|ĩ|î","i");

				 emailReady = nameToEmail +"."+sNameToEmail+"@"+domainEmail+".com.br".replaceAll("á|à|â|ã|ä","a").replaceAll("é|è|ê|ë","e").replaceAll("ó|ò|ô|õ","o").replaceAll("ç","c").replaceAll("í|ì|ĩ|î","i");
				 emailUserOk = emailReady.toLowerCase().replaceAll(" ","");

				 System.out.println("Email Generated::" + emailUserOk);

			} catch (Exception e) {

				System.out.println("Erro:: " + e);
			}

			if(i%2 == 0)
			{
				gender = "F";
			}else {
				gender = "M";
			}

			jsonItems = "{\"hashSec\":\"\",\"hashItem\":\"undefined\",\"nameUser\":\""+ name +" \",\"sNameUser\":\""+sName+"\",\"gender\":\""+gender+"\",\"emailUser\":\""+emailUserOk+"\",\"selectClient\":\"4\",\"department\":\"29\",\"userGroup\":\"1\",\"idItem\":\"\"}";


			Integer newUser = new UserController().execUserAdd(jsonItems, null);

			assertEquals(confirmValue, newUser);
		}
	}

//
//	private String filterStr(String word)
//	{
//
//
//	}

	/**
	 *
	 * @throws JSONException
	 */
	@Test
	public void updateUsersTest() throws JSONException {

		String jsonItems = "";
		String gender = "";
		Integer i = 0;
		Integer confirmValue = 201;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<User> userList = new UserModel().getAllUsers();

		for(User userData: userList)
		{
			if(i%2 == 0)
			{
				gender = "F";
			}else {

				gender = "M";
			}
			jsonItems = "{\"hashSec\":\""+ userData.getIdConfEmail() +"\",\"hashItem\":\"undefined\",\"nameUser\":\""+ userData.getName() +" \",\"sNameUser\":\"jUnit 4.1\",\"gender\":\""+userData.getGender()+"\",\"emailUser\":\""+userData.getEmail()+"\",\"selectClient\":\""+userData.getDepartment().getClientBean().getId()+"\",\"department\":\""+userData.getDepartment().getId()+"\",\"userGroup\":\""+userData.getUserGroupBean().getId()+"\",\"idItem\":\""+userData.getId()+"\"}";

			Integer newUser = new UserController().execUserAdd(jsonItems, null);

			assertEquals(confirmValue, newUser);

			i++;

		}
	}

	private String sobreNome(){

		String[] nomes = new String[31];

		nomes[0] = "Ramos";
		nomes[1] = "Silva";
		nomes[2] = "Derek";
		nomes[3] = "Carvalho";
		nomes[4] = "Guimarães";
		nomes[5] = "Pereira";
		nomes[6] = "Nunes";
		nomes[7] = "Cavalcante";
		nomes[8] = "Donizetti";
		nomes[9] = "Barbosa";
		nomes[10] = "Almeida";
		nomes[11] = "Santos";
		nomes[12] = "de Jesus";
		nomes[13] = "Evangelista";
		nomes[14] = "Ribeiro";
		nomes[15] = "de Paula";
		nomes[16] = "Queiroz";
		nomes[17] = "Lourenço";
		nomes[18] = "Camargo";
		nomes[19] = "da Costa";
		nomes[20] = "Lima";
		nomes[21] = "Oliveira";
		nomes[22] = "Sousa";
		nomes[23] = "Ferreira";
		nomes[24] = "Alves";
		nomes[25] = "Monteiro";
		nomes[26] = "Barros";
		nomes[27] = "Freitas";
		nomes[28] = "Dias";
		nomes[29] = "Rodrigues";
		nomes[30] = "Nascimento";

		int random = new Random().nextInt(nomes.length);

		return nomes[random];
	}

	private String name(){

		String[] nomes = new String[31];

		nomes[0] = "Maria";
		nomes[1] = "Janey";
		nomes[2] = "Maritza";
		nomes[3] = "Rose";
		nomes[4] = "Rosangela";
		nomes[5] = "Carol";
		nomes[6] = "Catarine";
		nomes[7] = "Lísias";
		nomes[8] = "Inês";
		nomes[9] = "Márcia";
		nomes[10] = "Miguel";
		nomes[11] = "Pedro";
		nomes[12] = "Rodrigo";
		nomes[13] = "Otávio";
		nomes[14] = "Wagner";
		nomes[15] = "Vicente";
		nomes[16] = "Carol";
		nomes[17] = "Edna";
		nomes[18] = "Raul";
		nomes[19] = "Fábio";
		nomes[20] = "Michel";
		nomes[21] = "Adriano";
		nomes[22] = "Paulo";
		nomes[23] = "Francisco";
		nomes[24] = "Marcos";
		nomes[25] = "Guilherme";
		nomes[26] = "Guina";
		nomes[27] = "José";
		nomes[28] = "Hamilton";
		nomes[29] = "Sebastian";
		nomes[30] = "Edson";

		int random = new Random().nextInt(nomes.length);

		return nomes[random];
	}


}
