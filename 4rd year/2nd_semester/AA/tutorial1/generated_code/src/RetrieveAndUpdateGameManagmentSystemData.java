/**
 * Licensee: 
 * License Type: Evaluation
 */
import org.orm.*;
public class RetrieveAndUpdateGameManagmentSystemData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = GameManagmentSystemPersistentManager.instance().getSession().beginTransaction();
		try {
			User user = UserDAO.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			UserDAO.save(user);
			Game game = GameDAO.loadGameByQuery(null, null);
			// Update the properties of the persistent object
			GameDAO.save(game);
			Platform platform = PlatformDAO.loadPlatformByQuery(null, null);
			// Update the properties of the persistent object
			PlatformDAO.save(platform);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving User by UserCriteria");
		UserCriteria userCriteria = new UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//userCriteria.ID.eq();
		System.out.println(userCriteria.uniqueUser());
		
		System.out.println("Retrieving Game by GameCriteria");
		GameCriteria gameCriteria = new GameCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//gameCriteria.ID.eq();
		System.out.println(gameCriteria.uniqueGame());
		
		System.out.println("Retrieving Platform by PlatformCriteria");
		PlatformCriteria platformCriteria = new PlatformCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//platformCriteria.ID.eq();
		System.out.println(platformCriteria.uniquePlatform());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateGameManagmentSystemData retrieveAndUpdateGameManagmentSystemData = new RetrieveAndUpdateGameManagmentSystemData();
			try {
				retrieveAndUpdateGameManagmentSystemData.retrieveAndUpdateTestData();
				//retrieveAndUpdateGameManagmentSystemData.retrieveByCriteria();
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
