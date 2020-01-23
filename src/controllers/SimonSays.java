package controllers;

import org.sikuli.basics.Settings;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;

import java.util.Arrays;

public class SimonSays {

    private Modules mod = new Modules();
    public Location blue = mod.selected.getCenter().above(75);
    public Location yellow = mod.selected.getCenter().above(20).right(50);
    public Location green = mod.selected.getCenter().below(50);
    public Location red = mod.selected.getCenter().above(20).left(50);

    public Pattern blueImg = new Pattern("C:/Users/pzpjfb/Documents/Automation/Java/Projects/KTaNE-Solver/src/references/simon/blue.png").similar(0.90);
    public Pattern yellowImg = new Pattern("C:/Users/pzpjfb/Documents/Automation/Java/Projects/KTaNE-Solver/src/references/simon/yellow.png").similar(0.90);
    public Pattern greenImg = new Pattern("C:/Users/pzpjfb/Documents/Automation/Java/Projects/KTaNE-Solver/src/references//simon/green.png").similar(0.90);
    public Pattern redImg = new Pattern("C:/Users/pzpjfb/Documents/Automation/Java/Projects/KTaNE-Solver/src/references//simon/red.png").similar(0.90);



    public void main() throws InterruptedException {
//        blue.hover();
//        Thread.sleep(2000);
//        yellow.hover();
//        Thread.sleep(2000);
//        green.hover();
//        Thread.sleep(2000);
//        red.hover();
        mod.selected.highlight(2);
    }


    public void getCurrentLight() throws FindFailed {
//        if (mod.selected.has(greenImg,4.0)) {
//            System.out.println("Green");
//        }else if (mod.selected.has(yellowImg,4.0)) {
//            System.out.println("Yellow");
//        }else if (mod.selected.has(redImg,4.0)) {
//            System.out.println("Red");
//        }else if (mod.selected.has(blueImg,4.0)) {
//            System.out.println("Blue");
//        }
        Settings.AutoWaitTimeout = 5.0f;
        mod.selected.setAutoWaitTimeout(5.0f);
        mod.selected.wait(greenImg).highlight(2,"GREEN");


    }

}
