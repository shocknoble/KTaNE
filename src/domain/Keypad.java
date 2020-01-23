package domain;


import main.Main;
import org.sikuli.script.Pattern;

import java.util.ArrayList;


public class Keypad {
    public String refpath = "C:/Users/pzpjfb/Documents/Automation/Java/Projects/KTaNE-Solver/src/references/keypads/";
    public String iconPath;
    public Pattern icon;
    public String name;
    public ArrayList<Integer> lists;
    public static ArrayList<Pattern> allPatterns = new ArrayList<Pattern>();

    public Keypad(String name, ArrayList<Integer> lists) {
        this.name = name;
        this.lists = lists;
        this.iconPath = name+".png";
        this.icon = new Pattern(refpath+iconPath).similar(0.85);
        allPatterns.add(this.icon);

    }
}
