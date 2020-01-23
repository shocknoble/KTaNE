package controllers;

import org.sikuli.script.Location;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.util.ArrayList;

public class Memory {


    public String refpath = "C:/Users/pzpjfb/Documents/Automation/Java/Projects/KTaNE-Solver/src/references/memory/";
    private Modules mod = new Modules();
    public ArrayList<Pattern> numPressed = new ArrayList<Pattern>();
    public ArrayList<Integer> numPosition = new ArrayList<Integer>();
    public Integer stage = 0;

    public Pattern one = new Pattern(refpath+"one.png");
    public Pattern two = new Pattern(refpath+"two.png");
    public Pattern three = new Pattern(refpath+"three.png");
    public Pattern four = new Pattern(refpath+"four.png");

    public Pattern bigOne = new Pattern(refpath+"bigone.png");
    public Pattern bigTwo = new Pattern(refpath+"bigtwo.png");
    public Pattern bigThree = new Pattern(refpath+"bigthree.png");
    public Pattern bigFour = new Pattern(refpath+"bigfour.png");

    public Location button1 = mod.selected.getCenter().left(95).below(60);
    public Location button2 = mod.selected.getCenter().left(55).below(60);
    public Location button3 = mod.selected.getCenter().right(10).below(60);
    public Location button4 = mod.selected.getCenter().right(35).below(60);


    public Region allButtons = new Region(mod.selected.getCenter().x-125,mod.selected.getCenter().y+23,180,80);



    public Pattern getDisplay() {
        System.out.println("Getting display");
        Match num = mod.selected.findBest(bigOne,bigTwo,bigThree,bigFour);
        System.out.println(num.getImageFilename());
        switch (num.getIndex()) {
            case 0:
                System.out.println("Display says 1");
                return bigOne;
            case 1:
                System.out.println("Display says 2");
                return bigTwo;
            case 2:
                System.out.println("Display says 3");
                return bigThree;
            case 3:
                System.out.println("Display says 4");
                return bigFour;
            default:
                return null;
        }
    }


    public void solveMemory() throws InterruptedException {
        allButtons.setRaster(1,4);

        turnOne(getDisplay()).click();
        new Location(0, 0).hover();
        Thread.sleep(3500);
        turnTwo(getDisplay()).click();
        new Location(0, 0).hover();
        Thread.sleep(3500);
        turnThree(getDisplay()).click();
        new Location(0, 0).hover();
        Thread.sleep(3500);
        turnFour(getDisplay()).click();
        new Location(0, 0).hover();
        Thread.sleep(3500);
        turnFive(getDisplay()).click();
        new Location(0, 0).hover();
        Thread.sleep(3500);
    }

    public Location turnOne(Pattern num) {
        if (num == bigOne) {
            numPressed.add(getButtonPressed(allButtons.getCol(1)));
            numPosition.add(2);
            return button2;
        }else if (num == bigTwo) {
            numPressed.add(getButtonPressed(allButtons.getCol(1)));
            numPosition.add(2);
            return button2;
        }else if (num == bigThree) {
            numPressed.add(getButtonPressed(allButtons.getCol(2)));
            numPosition.add(3);
            return button3;
        }else if (num == bigFour) {
            numPressed.add(getButtonPressed(allButtons.getCol(3)));
            numPosition.add(4);
            return button4;
        }else {
            System.out.println("No button found?");
            return null;
        }

    }

    public Location turnTwo(Pattern num) {
        if (num == bigOne) {
            for (int b = 0; b < allButtons.getCols(); b++) {
                if (allButtons.getCol(b).has(four)) {
                    numPressed.add(four);
                    numPosition.add(b+1);
                    return allButtons.getCol(b).getCenter();
                }
            }
            return null;
        }else if (num == bigTwo || num == bigFour) {
            numPressed.add(getButtonPressed(allButtons.getCol(numPosition.get(0)-1)));
            numPosition.add(numPosition.get(0));
            return allButtons.getCol(numPosition.get(0)-1).getCenter();
        }
        else if (num == bigThree) {
            numPressed.add(getButtonPressed(allButtons.getCol(0)));
            numPosition.add(1);
            return allButtons.getCol(0).getCenter();
        }else {
            System.out.println("No button found?");
            return null;
        }

    }

    public Location turnThree(Pattern num) {
        if (num == bigOne) {
            numPressed.add(numPressed.get(1));
            for (int b = 0; b < allButtons.getCols(); b++) {
                if (allButtons.getCol(b).has(numPressed.get(1))) {
                    numPosition.add(b+1);
                    return allButtons.getCol(b).getCenter();
                }
            }
            return null;
        }else if (num == bigTwo) {
            numPressed.add(numPressed.get(0));
            for (int b = 0; b < allButtons.getCols(); b++) {
                if (allButtons.getCol(b).has(numPressed.get(0))) {
                    numPosition.add(b+1);
                    return allButtons.getCol(b).getCenter();
                }
            }
            return null;
        }else if (num == bigThree) {
            numPressed.add(getButtonPressed(allButtons.getCol(2)));
            numPosition.add(3);
            return allButtons.getCol(2).getCenter();
        }else if (num == bigFour) {
            numPressed.add(four);
            for (int b = 0; b < allButtons.getCols(); b++) {
                if (allButtons.getCol(b).has(four)) {
                    numPosition.add(b+1);
                    return allButtons.getCol(b).getCenter();
                }
            }
            return null;
        }else {
            System.out.println("No button found?");
            return null;
        }
    }

    public Location turnFour(Pattern num) {
        if (num == bigOne) {
            numPressed.add(getButtonPressed(allButtons.getCol(numPosition.get(0)-1)));
            numPosition.add(numPosition.get(0));
            return allButtons.getCol(numPosition.get(0)-1).getCenter();
        }else if (num == bigTwo) {
            numPressed.add(getButtonPressed(allButtons.getCol(0)));
            numPosition.add(1);
            return allButtons.getCol(0).getCenter();
        }else if (num == bigThree || num == bigFour) {
            numPressed.add(getButtonPressed(allButtons.getCol(numPosition.get(1)-1)));
            numPosition.add(numPosition.get(1));
            return allButtons.getCol(numPosition.get(1)-1).getCenter();
        }else {
            System.out.println("No button found?");
            return null;
        }
    }

    public Location turnFive(Pattern num) {
        if (num == bigOne) {
            numPressed.add(numPressed.get(0));
            for (int b = 0; b < allButtons.getCols(); b++) {
                if (allButtons.getCol(b).has(numPressed.get(0))) {
                    numPosition.add(b+1);
                    return allButtons.getCol(b).getCenter();
                }
            }
            return null;
        }else if (num == bigTwo) {
            numPressed.add(numPressed.get(1));
            for (int b = 0; b < allButtons.getCols(); b++) {
                if (allButtons.getCol(b).has(numPressed.get(1))) {
                    numPosition.add(b+1);
                    return allButtons.getCol(b).getCenter();
                }
            }
            return null;
        }else if (num == bigThree) {
            numPressed.add(numPressed.get(3));
            for (int b = 0; b < allButtons.getCols(); b++) {
                if (allButtons.getCol(b).has(numPressed.get(3))) {
                    numPosition.add(b+1);
                    return allButtons.getCol(b).getCenter();
                }
            }
            return null;
        }else if (num == bigFour) {
            numPressed.add(numPressed.get(2));
            for (int b = 0; b < allButtons.getCols(); b++) {
                if (allButtons.getCol(b).has(numPressed.get(2))) {
                    numPosition.add(b+1);
                    return allButtons.getCol(b).getCenter();
                }
            }
            return null;
        }else {
            System.out.println("No button found?");
            return null;
        }
    }


    public Pattern getButtonPressed(Region button) {
        Match btn = button.findBest(one,two,three,four);
        switch (btn.getIndex()) {
            case 0:
                return one;
            case 1:
                return two;
            case 2:
                return three;
            case 3:
                return four;
            default:
                return null;
        }
    }



}
