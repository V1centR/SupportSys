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
import com.supportsys.entity.Department;
import com.supportsys.entity.User;
import com.supportsys.entity.UserGroup;
import com.supportsys.model.UserModel;

public class AddUserTest {

	@Test
	public void addUsersTest() throws JSONException {

		Integer numberOfUser = 10;
		Integer confirmValue = 201;
		String jsonItems = "";
		String gender = "";
		String name = "";
		String sName = "";
		String emailReady = "";
		String emailUserOk = "";
		String nameToEmail = "";
		String sNameToEmail = "";

		List<Client> clientList = new UserModel().getAllClients();
		List<UserGroup> groupList = new UserModel().getGroups();

		for(Integer i=0; i<=numberOfUser;i++) {

			try {

				int randomClient = new Random().nextInt(clientList.size());
				int ramdomUserGroup = new Random().nextInt(groupList.size());

				Client clientItem = clientList.get(randomClient);
				UserGroup userGroupItem = groupList.get(ramdomUserGroup);

				List<Department> departmentList = new UserModel().getDepartments(clientItem.getId());

				Integer randomDepartment = new Random().nextInt(departmentList.size());
				Department departmentItem = departmentList.get(randomDepartment);

				String domainEmail =  clientItem.getName().toLowerCase().replaceAll("á|à|â|ã|ä|Á","a").replaceAll("é|è|ê|ë","e").replaceAll("ó|ò|ô|õ","o").replaceAll("ç","c").replaceAll("í|ì|ĩ|î","i").replaceAll("[^a-zA-Z]+","");

				name = name();
				sName = sobreNome();
				gender = name.substring(name.lastIndexOf(":")+1);

				name = name.replaceAll(":.*","");
				nameToEmail = filterStr(name);
				sNameToEmail = filterStr(sName);

				emailReady = nameToEmail+"."+sNameToEmail+"@"+domainEmail+".com.br";
				emailUserOk = filterStr(emailReady.toLowerCase()).replaceAll(" ","");

				jsonItems = "{\"hashSec\":\"\",\"hashItem\":\"undefined\",\"nameUser\":\""+ name +" \",\"sNameUser\":\""+sName+"\",\"gender\":\""+gender+"\",\"emailUser\":\""+emailUserOk+"\",\"selectClient\":\""+clientItem.getId()+"\",\"department\":\""+departmentItem.getId()+"\",\"userGroup\":\""+userGroupItem.getId()+"\",\"idItem\":\"\"}";

			} catch (Exception e) {

				System.out.println("Error:: " + e);
			}

			Integer newUser = new UserController().execUserAdd(jsonItems, null);

			assertEquals(confirmValue, newUser);
		}
	}


	/**
	 * Filter string to generate email
	 * @param word
	 * @return
	 */
	private String filterStr(String word)
	{
		String FilteredWord = word.
				replaceAll("á|à|â|ã|ä|Á","a").
				replaceAll("é|è|ê|ë","e").
				replaceAll("ó|ò|ô|õ","o").
				replaceAll("ç","c").
				replaceAll("í|ì|ĩ|î","i").
				replaceAll("ú|ü|Ú|Ù|ù|Ü|ũ|Ũ","u");

		return FilteredWord;
	}

	/**
	 * Update registered users
	 * @throws JSONException
	 */
	@Test
	public void updateUsersTest() throws JSONException {

		String jsonItems = "";
		String userGroup = "";
		Integer i = 0;
		Integer confirmValue = 201;

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("support");
		EntityManager em = emf.createEntityManager();

		List<User> userList = new UserModel().getAllUsers();

		for(User userData: userList)
		{
			jsonItems = "{\"hashSec\":\""+ userData.getIdConfEmail() +"\",\"hashItem\":\"undefined\",\"nameUser\":\""+ userData.getName() +" \",\"sNameUser\":\"jUnit 4.1\",\"gender\":\""+userData.getGender()+"\",\"emailUser\":\""+userData.getEmail()+"\",\"selectClient\":\""+userData.getDepartment().getClientBean().getId()+"\",\"department\":\""+userData.getDepartment().getId()+"\",\"userGroup\":\""+userData.getUserGroupBean().getId()+"\",\"idItem\":\""+userData.getId()+"\"}";

			Integer newUser = new UserController().execUserAdd(jsonItems, null);

			assertEquals(confirmValue, newUser);

			i++;

		}
	}


	/**
	 * Generate randomic user lastNames
	 * @return
	 */
	private String sobreNome(){

		String[] nomes = new String[71];

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
		nomes[31] = "Alexandre";
		nomes[32] = "Abreu";
		nomes[33] = "Amorim";
		nomes[34] = "Avelar";
		nomes[35] = "Bassan";
		nomes[36] = "Batista";
		nomes[37] = "Alexandre";
		nomes[38] = "Alexandre";
		nomes[39] = "Alexandre";
		nomes[40] = "Alexandre";
		nomes[41] = "Coelho";
		nomes[42] = "Alexandre";
		nomes[43] = "Teixeira";
		nomes[44] = "Ribas";
		nomes[45] = "Fernades";
		nomes[46] = "Claudino";
		nomes[47] = "Soares";
		nomes[48] = "Domingues";
		nomes[49] = "Lopes";
		nomes[50] = "Diegues";
		nomes[51] = "Marins";
		nomes[52] = "Gutierres";
		nomes[53] = "Carneiro";
		nomes[55] = "Bonfim";
		nomes[56] = "Saraiva";
		nomes[57] = "Sampaio";
		nomes[58] = "Pires";
		nomes[59] = "Magalhães";
		nomes[60] = "de Campos";
		nomes[61] = "de Ávila";
		nomes[62] = "Vargas";
		nomes[63] = "Brandão";
		nomes[64] = "Arruda";
		nomes[65] = "Garcia";
		nomes[66] = "Correia";
		nomes[67] = "Corrêa";
		nomes[68] = "Medina";
		nomes[69] = "Flores";
		nomes[70] = "Xavier";

		int random = new Random().nextInt(nomes.length);

		return nomes[random];
	}

	/**
	 * Return randomic name
	 * to build user names and based emails
	 * @return
	 */
	private String name(){

		String[] nomes = new String[131];

		nomes[0] = "Maria:F";
		nomes[1] = "Janey:F";
		nomes[2] = "Maritza:F";
		nomes[3] = "Rose:F";
		nomes[4] = "Rosangela:F";
		nomes[5] = "Carol:F";
		nomes[6] = "Catarine:F";
		nomes[7] = "Lísias:F";
		nomes[8] = "Inês:F";
		nomes[9] = "Márcia:F";
		nomes[10] = "Miguel:M";
		nomes[11] = "Pedro:M";
		nomes[12] = "Rodrigo:M";
		nomes[13] = "Otávio:M";
		nomes[14] = "Wagner:M";
		nomes[15] = "Vicente:M";
		nomes[16] = "Caroline:F";
		nomes[17] = "Edna:F";
		nomes[18] = "Raul:M";
		nomes[19] = "Fábio:M";
		nomes[20] = "Michel:M";
		nomes[21] = "Adriano:M";
		nomes[22] = "Paulo:M";
		nomes[23] = "Francisco:M";
		nomes[24] = "Marcos:M";
		nomes[25] = "Guilherme:M";
		nomes[26] = "Guina:M";
		nomes[27] = "José:M";
		nomes[28] = "Hamilton:M";
		nomes[29] = "Sebastian:M";
		nomes[30] = "Beatriz:F";
		nomes[19] = "Lucas:M";
		nomes[30] = "Laura:F";
		nomes[31] = "Gabriel:M";
		nomes[32] = "Patrícia:F";
		nomes[33] = "Rafael:M";
		nomes[34] = "Júlia:F";
		nomes[35] = "Gustavo:M";
		nomes[36] = "Manoela:F";
		nomes[37] = "João:M";
		nomes[38] = "Fernanda:F";
		nomes[39] = "Rafael:M";
		nomes[40] = "Larissa:F";
		nomes[41] = "Mateus:M";
		nomes[42] = "Renataa:F";
		nomes[43] = "Renato:M";
		nomes[44] = "Manoela:F";
		nomes[45] = "Edson:M";
		nomes[46] = "Giovanna:F";
		nomes[47] = "Bruno:M";
		nomes[48] = "Lorena:F";
		nomes[49] = "Felipe:M";
		nomes[50] = "Gabriela:F";
		nomes[51] = "Heitor:M";
		nomes[52] = "Gabrielle:F";
		nomes[53] = "Samuel:M";
		nomes[54] = "Cecília:F";
		nomes[55] = "Eduardo:M";
		nomes[56] = "Vanessa:F";
		nomes[57] = "Diego:M";
		nomes[58] = "Célia:F";
		nomes[59] = "Leonardo:M";
		nomes[60] = "Sara:F";
		nomes[61] = "Daniel:M";
		nomes[62] = "Gabrielle:F";
		nomes[64] = "Antônio:M";
		nomes[65] = "José:M";
		nomes[66] = "Luíza:F";
		nomes[67] = "Thiago:M";
		nomes[68] = "Rafaela:F";
		nomes[69] = "Alexandre:M";
		nomes[70] = "Carlos:M";
		nomes[71] = "Sabrina:F";
		nomes[72] = "André:M";
		nomes[73] = "Cíntia:F";
		nomes[74] = "Vitor:M";
		nomes[75] = "Heloísa:F";
		nomes[76] = "Caio:M";
		nomes[77] = "Thais:F";
		nomes[78] = "Francisco:M";
		nomes[79] = "Daniela:F";
		nomes[80] = "Marcelo:M";
		nomes[81] = "Adriana:F";
		nomes[82] = "David:M";
		nomes[83] = "Brenda:F";
		nomes[84] = "Joaquim:M";
		nomes[85] = "Isabella:F";
		nomes[86] = "Valdir:M";
		nomes[87] = "Raquel:F";
		nomes[88] = "Everton:M";
		nomes[89] = "Priscila:F";
		nomes[90] = "Maxwell:M";
		nomes[91] = "Marina:F";
		nomes[92] = "Roberto:M";
		nomes[93] = "Débora:F";
		nomes[94] = "Ricardo:M";
		nomes[95] = "Natália:F";
		nomes[96] = "Luiz:M";
		nomes[97] = "Isabel:F";
		nomes[98] = "Igor:M";
		nomes[99] = "Izabelle:F";
		nomes[100] = "Diogo:M";
		nomes[101] = "Emily:F";
		nomes[102] = "Fábio:M";
		nomes[103] = "Jaqueline:F";
		nomes[104] = "Luan:M";
		nomes[105] = "Marcela:F";
		nomes[106] = "Enoque:M";
		nomes[107] = "Luciana:F";
		nomes[108] = "Jonathan:M";
		nomes[109] = "Ana Paula:F";
		nomes[110] = "Jonas:M";
		nomes[111] = "Maria Eduarda:F";
		nomes[112] = "Renan:M";
		nomes[113] = "Ana Júlia:F";
		nomes[114] = "João Vítor:M";
		nomes[115] = "Maria Clara:F";
		nomes[116] = "Augusto:M";
		nomes[117] = "Maria Luíza:F";
		nomes[118] = "Douglas:M";
		nomes[119] = "Andressa:F";
		nomes[120] = "Danilo:M";
		nomes[121] = "Cristina:F";
		nomes[122] = "Levi:M";
		nomes[123] = "Sandra:F";
		nomes[124] = "Erick:M";
		nomes[125] = "Tatiana:F";
		nomes[126] = "Maicon:M";
		nomes[127] = "Tatiane:F";
		nomes[128] = "Alan:M";
		nomes[129] = "Maitê:F";
		nomes[130] = "Erika:F";

		int random = new Random().nextInt(nomes.length);

		return nomes[random];
	}


}
