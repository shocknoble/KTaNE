package domain;

import org.sikuli.script.Pattern;

import java.util.ArrayList;

public class Letter {

    public Pattern icon;
    public String letter;
    private String refpath = "password/";

    public Letter(String letter) {
        this.letter = letter;
        this.icon = new Pattern(refpath+this.letter+".png").similar(0.90);
    }
}
