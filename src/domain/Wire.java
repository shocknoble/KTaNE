package domain;

import org.sikuli.script.Location;

import java.util.ArrayList;
import java.util.Arrays;

public class Wire {

    public Integer red;
    public Integer green;
    public Integer blue;
    public Integer position;
    public Integer offset;
    public String color;
    public static ArrayList<Integer> Positions = new ArrayList<Integer>(Arrays.asList(-110,-70,-30,9,50,90));

    public Wire(Integer red, Integer green, Integer blue, Integer position) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.position = position;
        this.offset = Positions.get(this.position);
    }

}
