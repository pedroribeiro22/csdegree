/**
 * Licensee: 
 * License Type: Evaluation
 */
import org.orm.*;
public class ListGameManagmentSystemData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing User...");
		User[] users = UserDAO.listUserByQuery(null, null);
		int length = Math.min(users.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(users[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Game...");
		Game[] games = GameDAO.listGameByQuery(null, null);
		length = Math.min(games.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(games[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Platform...");
		Platform[] platforms = PlatformDAO.listPlatformByQuery(null, null);
		length = Math.min(platforms.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(platforms[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing User by Criteria...");
		UserCriteria userCriteria = new UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//userCriteria.ID.eq();
		userCriteria.setMaxResults(ROW_COUNT);
		User[] users = userCriteria.listUser();
		int length =users== null ? 0 : Math.min(users.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(users[i]);
		}
		System.out.println(length + " User record(s) retrieved."); 
		
		System.out.println("Listing Game by Criteria...");
		GameCriteria gameCriteria = new GameCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//gameCriteria.ID.eq();
		gameCriteria.setMaxResults(ROW_COUNT);
		Game[] games = gameCriteria.listGame();
		length =games== null ? 0 : Math.min(games.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(games[i]);
		}
		System.out.println(length + " Game record(s) retrieved."); 
		
		System.out.println("Listing Platform by Criteria...");
		PlatformCriteria platformCriteria = new PlatformCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//platformCriteria.ID.eq();
		platformCriteria.setMaxResults(ROW_COUNT);
		Platform[] platforms = platformCriteria.listPlatform();
		length =platforms== null ? 0 : Math.min(platforms.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(platforms[i]);
		}
		System.out.println(length + " Platform record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListGameManagmentSystemData listGameManagmentSystemData = new ListGameManagmentSystemData();
			try {
				listGameManagmentSystemData.listTestData();
				//listGameManagmentSystemData.listByCriteria();
			}
			finally {
				GameManagmentSystemPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
