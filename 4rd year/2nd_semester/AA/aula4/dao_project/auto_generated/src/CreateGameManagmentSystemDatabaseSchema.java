/**
 * Licensee: Pedro Ribeiro(Universidade do Minho)
 * License Type: Academic
 */
import org.orm.*;
public class CreateGameManagmentSystemDatabaseSchema {
	public static void main(String[] args) {
		try {
			ORMDatabaseInitiator.createSchema(GameManagmentSystemPersistentManager.instance());
			GameManagmentSystemPersistentManager.instance().disposePersistentManager();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
