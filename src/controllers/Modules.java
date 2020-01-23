package controllers;

import domain.Keypad;
import main.Main;
import org.sikuli.basics.Settings;
import org.sikuli.script.*;

import java.util.ArrayList;

import static controllers.Keypads.*;
import static java.lang.Thread.sleep;

public class Modules implements Runnable {

    public  Pattern timer = new Pattern("modules//timer.png").mask().similar(0.90);
    public  Pattern timerUR = new Pattern("modules//timerUR.png").mask().similar(0.90);

    public  Region game = App.focus("Keep Talking and Nobody Explodes").window();
    public  Region bomb = game.grow(-500,-460,-240,-205);

    public  Region selected = new Region(bomb.getCenter().left(140).x,bomb.getCenter().above(157).y,290,275);
    public  Region kps = selected.grow(-15,-70,-60,-20);

    public  String refpath = "modules//";
    public  Pattern[] modules;;
    public  Pattern[] allModules() {

        Pattern button      = new Pattern(refpath+"button.png").mask().similar(0.90);
        Pattern wires       = new Pattern(refpath+"wires.png").mask().similar(0.90);
        Pattern patterns    = new Pattern(refpath+"patterns.png").mask().similar(0.90);
        Pattern maze        = new Pattern(refpath+"maze.png").mask().similar(0.90);
        Pattern memory      = new Pattern(refpath+"memory.png").mask().similar(0.90);
        Pattern morse       = new Pattern(refpath+"morse.png").mask().similar(0.90);
        Pattern first       = new Pattern(refpath+"first.png").mask().similar(0.90);
        Pattern password    = new Pattern(refpath+"password.png").mask().similar(0.90);
        Pattern timer       = new Pattern(refpath+"timer.png").similar(0.90);


        modules = new Pattern[]{button, wires, patterns,maze,memory,morse,first,password, timer, timerUR};

        return modules;
    }

    public void solvePatterns() throws FindFailed {
        kps.setRaster(2,2);

        ArrayList<Keypad>found = new ArrayList<Keypad>();
        for (int r = 0; r < 2; r++) {
            for (int c = 0; c < 2; c++) {
                Match cell = kps.getCell(r, c).findBest(cdot.icon, doubleK.icon, edot.icon, magnify.icon, question.icon, spaceship.icon, squiggleH.icon, tongue.icon, trident.icon, whitestar.icon, six.icon, omega.icon, notequal.icon, lightningN.icon, monkas.icon, lambda.icon, half3.icon, disney.icon, ae.icon, at.icon, tampabay.icon, blackstar.icon, devil3.icon, paragraph.icon, lightning.icon, copyright.icon, cflipped.icon);
                System.out.println(cell.getImageFilename());
                String name = cell.getImageFilename().substring(cell.getImageFilename().lastIndexOf("\\") + 1, cell.getImageFilename().lastIndexOf(".png"));
                System.out.println("ICON FOUND : " + name);
                System.out.println("CELL INDEX : " + cell.getIndex());
                found.add(allKeypads.get(cell.getIndex()));

            }
        }
        System.out.println("FOUND SYMBOLS : " + found.size());
        ArrayList<Keypad> order = Keypads.getKeypadList(found);

        for (int k = 0; k < order.size(); k++) {
            if (found.contains(order.get(k)) == false) {
                continue;
            }
            kps.click(order.get(k).icon);
        }
    }

    public void turnBomb() throws InterruptedException {
        Settings.MoveMouseDelay = 0.5f;
        Settings.ClickDelay = 0;
        Thread.sleep(1000);
        selected.getCenter().hover();
        Mouse.down(Mouse.RIGHT);
        selected.getCenter().left(525).hover();
        Mouse.up(Mouse.RIGHT);
        Thread.sleep(1000);
        Settings.MoveMouseDelay = 0.1f;
    }

    public void run() {
        try {
            Thread.sleep(2000);
            bomb.setRaster(2, 3);
            game.getTopLeft().click();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int t = 0; t < 2; t++) {
            try {

                for (int r = 0; r < bomb.getRows(); r++) {
                    for (int c = 0; c < bomb.getCols(); c++) {
                        if (bomb.getCell(r, c).has(timer) || bomb.getCell(r,c).has(timerUR)) {

                            System.out.println("Timer found");
                            continue;
                        } else {
                            bomb.getCell(r, c).click();
                            bomb.getTopLeft().hover();
                            sleep(500);
                            Match cell = selected.findBest(allModules());
                            Buttons button = new Buttons();
                            Mazes mazes = new Mazes();
                            Memory mem = new Memory();
                            MorseCode morse = new MorseCode();
                            Passwords pass = new Passwords();
                            WhosOnFirst first = new WhosOnFirst();
                            Wires wires = new Wires();
                            System.out.println("Current Time  - " + (Math.round(Math.floor(Main.getCountdown()/60))+":"+Main.getCountdown()%60));
                            switch (cell.getIndex()) {
                                case 0:
                                    button.fullSolve();
                                    break;
                                case 1:
                                    wires.countWires();
                                    break;
                                case 2:
                                    solvePatterns();
                                    break;
                                case 3:
                                    selected.setRaster(6, 6);
                                    mazes.createMazes();
                                    mazes.fullSolution();
                                    break;
                                case 4:
                                    mem.solveMemory();
                                    break;
                                case 5:
                                    morse.createAllMorseLetters();
                                    morse.waitForSpace();
                                    break;
                                case 6:
                                    first.fullSolve();
                                    break;
                                case 7:
                                    pass.fullSolve();
                                    break;
                                default:
                                    System.out.println(cell.getImageFilename());
                                    break;
                            }
                            Settings.MoveMouseDelay = 0;
                            Mouse.reset();
                            Settings.MoveMouseDelay = 0.1f;
                            bomb.rightClick();
                            sleep(1000);
                        }
                    }
                }
                turnBomb();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }Main.solver.interrupt();
    }

}
