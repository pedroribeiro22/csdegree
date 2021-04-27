package pt.uminho.tutorial4_project.beans;

import pt.uminho.tutorial4_project.model.Game;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GameBean {

    public void registerUser(final String email, final String username, final String password);

    public void registerGame(final String name, final int year, final float price, final String description, final String platform);

    public void registerPlatform(final String name, final int year, final String description, final String manufacturer);

    public List<Game> listUserGames(final int user_id);

    public List<Game> listAllGames();

    public Game searchGame(final int id);

    public String sayHello();
}
