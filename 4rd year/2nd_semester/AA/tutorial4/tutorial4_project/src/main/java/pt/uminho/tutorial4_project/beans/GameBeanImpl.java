package pt.uminho.tutorial4_project.beans;

import pt.uminho.tutorial4_project.model.Game;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class GameBeanImpl implements GameBean {

    public void registerUser(final String email, final String username, final String password) {
        return;
    }

    public void registerGame(final String name, final int year, final float price, final String description, final String platform) {
        return;
    }

    public void registerPlatform(final String name, final int year, final String description, final String manufacturer) {
        return;
    }

    public List<Game> listUserGames(final int user_id) {
       return new ArrayList<>();
    }

    public List<Game> listAllGames() {
       return new ArrayList<>();
    }

    public Game searchGame(final int id) {
        return new Game();
    }

    public String sayHello() {
        return "Hello méne!";
    }
}