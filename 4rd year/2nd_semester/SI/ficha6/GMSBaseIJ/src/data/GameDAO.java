/*
 * GameDAO
 * ruicouto in 10/abr/2017
 */
package data;

import business.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author ruicouto (IJ project: jfc)
 */
public class GameDAO {
    /**
     * Data is currently mocked
     * @return
     */
    public static List<Game> list() {
        List<Game> r = new ArrayList<>();
        r.add(new Game(1, "Tetris", 1989, 10, ""));
        r.add(new Game(2, "GTA V", 2013, 60, ""));
        r.add(new Game(3, "Assetto Corsa", 2014, 30, ""));
        r.add(new Game(4, "Fallout 4", 2014, 30, ""));
        r.add(new Game(5, "Breath of The Wild", 2017, 60, ""));
        r.add(new Game(6, "Rocket League", 2015, 0, ""));
        r.add(new Game(7, "F1 2018", 2018, 10, ""));
        r.add(new Game(8, "F1 2020", 2020, 60, ""));
        r.add(new Game(9, "F1 2021", 2021, 70, ""));
        r.add(new Game(10, "CS:GO", 2012, 12, ""));
        r.add(new Game(11, "NBA 2K21", 2021, 70, ""));
        return r;
    }


    public static List<Integer> getYears() {
        return GameDAO.list().stream().mapToInt(Game::getYear).boxed().collect(Collectors.toList());
    }

}
