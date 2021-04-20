import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        try {

            // <--! Configuration !-->
            Configuration configuration = new Configuration().configure();
            StandardServiceRegistry sr = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // <--! SessionFactory ! -->
            SessionFactory sf = configuration.buildSessionFactory(sr);

            // <--! Session !-->
            Session s = sf.openSession();
            s.setFlushMode(FlushMode.COMMIT); //propagate changes on commit

            // <--! Start the transaction !-->
            Transaction t = s.beginTransaction();

            // <--! Creates platform !-->
            Platform p = new Platform();
            p.setName("PlayStation 4");
            p.setYear(2010);
            p.setDescription("Gaming console");
            p.setManufacturer("SONY");

            s.save(p);

            // <--! Creates Game !-->
            Game g1 = new Game();
            g1.setName("GTA V");
            g1.setYear(2002);
            g1.setPrice(59.99);
            g1.setDescription("Very violent game");
            g1.setPlatform(p);

            // <--! Save the object !-->
            s.save(g1);

            try {
                //6 - commit the transaction
                t.commit();
            } catch (Exception e) {
                //6 - rollback in case of exception
                t.rollback();
                e.printStackTrace();
                System.out.println("Unable to commit changes");
            }

            Query query = s.createQuery("from Game");
            List results = query.list();
            System.out.println("Number of entries: " + results.size());
            Game lg = (Game) results.get(0);
            System.out.println(lg.getDescription());

            //7 - Close the session and end process
            s.close();
            StandardServiceRegistryBuilder.destroy(sr);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to connect to hibernate");
        }

    }
}
