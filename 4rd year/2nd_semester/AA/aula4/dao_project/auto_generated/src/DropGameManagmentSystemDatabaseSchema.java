/**
 * Licensee: Pedro Ribeiro(Universidade do Minho)
 * License Type: Academic
 */
import org.orm.*;
public class DropGameManagmentSystemDatabaseSchema {
	public static void main(String[] args) {
		try {
			System.out.println("Are you sure to drop table(s)? (Y/N)");
			java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
			if (reader.readLine().trim().toUpperCase().equals("Y")) {
				ORMDatabaseInitiator.dropSchema(GameManagmentSystemPersistentManager.instance());
				GameManagmentSystemPersistentManager.instance().disposePersistentManager();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
