package controllers;

import main.Main;
import org.sikuli.script.*;


import java.util.List;

public class Buttons {

    //yellow strip is  ~~ r255 g215 b18
    //blue strip is    ~~ r24 g104 b244
    //white strip is   ~~ r255 g255 b255

    boolean timeString = true;
    Modules mod = new Modules();
    String refpath = "C:/Users/pzpjfb/Documents/Automation/Java/Projects/KTaNE-Solver/src/references/button/";
    public Pattern blueabort = new Pattern(refpath+"blueabort.png").similar(.90);
    public Pattern reddetonate = new Pattern(refpath+"reddetonate.png").similar(0.90);
    public Pattern whitedetonate = new Pattern(refpath+"whitedetonate.png").similar(0.90);
    public Pattern yellowdetonate = new Pattern(refpath+"yellowdetonate.png").similar(0.90);
    public Pattern redhold = new Pattern(refpath+"redhold.png");
    public Pattern yellowany = new Pattern(refpath+"yellowany.png").mask().similar(0.95);
    public Pattern whiteany = new Pattern(refpath+"whiteany.png").mask().similar(0.95);



    public Pattern yellowStrip = new Pattern(refpath+"yellowstrip.png").similar(0.65);
    public Pattern blueStrip = new Pattern(refpath+"bluestrip.png").similar(0.65);
    public Pattern whiteStrip = new Pattern(refpath+"whitestrip.png").similar(0.65);



    public void fullSolve() throws InterruptedException, FindFailed {
        System.out.println("Batteries? : " +  Main.batteries);
        if (mod.selected.has(blueabort)) {
            mod.selected.getCenter().hover();
            Thread.sleep(150);
            Mouse.down(Mouse.LEFT);
            holdHandler(colorReader());

        }else if (Main.batteries > 1 && (mod.selected.has(whitedetonate) || mod.selected.has(yellowdetonate) || mod.selected.has(reddetonate))) {
            mod.selected.getCenter().hover();
            Thread.sleep(150);
            Mouse.down(Mouse.LEFT);
            Thread.sleep(100);
            Mouse.up(Mouse.LEFT);

        }else if (Main.car && mod.selected.has(whiteany)) {
            mod.selected.getCenter().hover();
            Thread.sleep(150);
            Mouse.down(Mouse.LEFT);
            Thread.sleep(100);
            holdHandler(colorReader());

        }else if (mod.selected.has(whiteany) && Main.car) {
            mod.selected.find(whiteany).highlight(2);
            System.out.println("White button with lit CAR label");
            mod.selected.getCenter().hover();
            Thread.sleep(150);
            Mouse.down(Mouse.LEFT);
            holdHandler(colorReader());

        }else if (Main.batteries > 2 && Main.frk) {
            System.out.println("At least three batteries with lit FRK Label");
            mod.selected.getCenter().hover();
            Thread.sleep(150);
            Mouse.down(Mouse.LEFT);
            Thread.sleep(100);
            Mouse.up(Mouse.LEFT);

        }else if (mod.selected.has(yellowany)) {
            mod.selected.find(yellowany).highlight(2);
            System.out.println("Yellow button");
            mod.selected.getCenter().hover();
            Thread.sleep(150);
            Mouse.down(Mouse.LEFT);
            holdHandler(colorReader());

        }else if (mod.selected.has(redhold)) {
            mod.selected.find(redhold).highlight(2);
            System.out.println("Red HOLD");
            mod.selected.getCenter().hover();
            Thread.sleep(150);
            Mouse.down(Mouse.LEFT);
            Thread.sleep(100);
            Mouse.up(Mouse.LEFT);

        }else {
            mod.selected.getCenter().hover();
            Thread.sleep(150);
            Mouse.down(Mouse.LEFT);
            Thread.sleep(100);
            holdHandler(colorReader());
        }
    }

    public String colorReader() throws InterruptedException {
        String color = "red";

        List<Match> stripFound =  mod.selected.grow(-210,0,0,0).findAny(yellowStrip,blueStrip,whiteStrip);
        if (stripFound.size() != 0) {
            color = stripFound.get(0).getImageFilename().substring(stripFound.get(0).getImageFilename().lastIndexOf("\\")+1,stripFound.get(0).getImageFilename().indexOf("strip"));
            System.out.println("STRIP COLOR FOUND : " + color);
        }
        Thread.sleep(1650);
        return color;
    }


    public boolean holdHandler(String color) throws InterruptedException {
        Integer number;
        System.out.println("Color per hold handler? : " + color);
        switch (color) {

            case "blue":
                number = 4;
                System.out.println("Release with a 4 in time");
                break;
            case "yellow":
                System.out.println("Release with a 5 in time");
                number = 5;
                break;
            default:
                System.out.println("Release with a 1 in time");
                number = 1;
                break;

        }
        while (timeString) {
            System.out.println(String.valueOf(Main.timeLeft%60));
            Thread.sleep(500);
            if (String.valueOf(Main.timeLeft % 60).contains(String.valueOf(number))) {
                Thread.sleep(750);
                Mouse.up(Mouse.LEFT);
                timeString = false;
                System.out.println("Break loop");
                break;
            }
        }return true;

    }





}
