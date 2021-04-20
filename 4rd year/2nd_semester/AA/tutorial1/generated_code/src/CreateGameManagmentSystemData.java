/**
 * Licensee: 
 * License Type: Evaluation
 */
import org.orm.*;
public class CreateGameManagmentSystemData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = GameManagmentSystemPersistentManager.instance().getSession().beginTransaction();
		try {
			User user = UserDAO.createUser();
			// Initialize the properties of the persistent object here
			UserDAO.save(user);
			Game game = GameDAO.createGame();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : year
			GameDAO.save(game);
			Platform platform = PlatformDAO.createPlatform();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : year
			PlatformDAO.save(platform);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateGameManagmentSystemData createGameManagmentSystemData = new CreateGameManagmentSystemData();
			try {
				createGameManagmentSystemData.createTestData();
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
