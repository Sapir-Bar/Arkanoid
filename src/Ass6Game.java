// 211540562 Sapir Bar

import Animation.AnimationRunner;
import Levels.DirectHit;
import Levels.GameFlow;
import Levels.Green3;
import Levels.LevelInformation;
import Levels.WideEasy;

import biuoop.GUI;
import biuoop.Sleeper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sapir Bar < sapirbar22@gmail.com >
 * @version 19.0.2
 * @since 06.05.2023
 * The class creates a Game-Flow and runs the game with the level information
 * received from the user at the command line.*/
public class Ass6Game {

    /**
     * convert a string to an integer.
     * @param string string
     * @return integer
     * @throws Exception try to convert a non-numerical value to an integer.
     */
    public static int addDigit(String string) throws Exception {
        return Integer.parseInt(string);
    }


    /**
     * The main method. Creates a new instance Game-Flow and calls the 'run' method on it
     * with a list of level-information.
     * @param arg The user may enter numbers representing different game levels
     * (1 - direct hit, 2 - wide easy, 3 - green 3).
     * If no data is entered the program will run the default.
     */
    public static void main(String[] arg) {
        GUI gui = new GUI("ArKanoid", 800, 600);
        Sleeper sleeper = new Sleeper();
        AnimationRunner runner = new AnimationRunner(gui, sleeper, 60);
        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<>();
        for (int i = 0; i < arg.length; i++) {
            try {
                int digit = addDigit(arg[i]);
                switch (digit) {
                    case 1:
                        levels.add(new DirectHit());
                        break;
                    case 2:
                        levels.add(new WideEasy());
                        break;
                    case 3:
                        levels.add(new Green3());
                        break;
                    default:
                }

            } catch (Exception e) {
            }
        }
        if (levels.isEmpty()) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
        }
        gameFlow.runLevels(levels);
        gui.close();
    }
}





