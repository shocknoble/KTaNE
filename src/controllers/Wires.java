package controllers;


import domain.Wire;
import main.Main;
import org.sikuli.script.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Wire     Position(From Center)      RGB For Red        Blue         Black        White          Yellow
 *
 *  1            up 110                  255,64,28                     5,5,4     235,227,209      255,219,7
 *
 *  2            up 70                  255,106,41                     7,6,6     255,255,255     255,255,41
 *
 *  3            up 30                                                 3,3,3     236,225,210      255,218,7
 *
 *  4           down 10                                 44,63,159                195,186,173     216,180,6
 *
 *  5           down 50                255,100,38                      3,4,3                     243,200,7
 *
 *  6           down 90                255,60,26        66,106,237
 */

public class Wires {

    public  Integer count;
    public  Integer reds, blues, blacks, whites, yellows, blanks;
    public  ArrayList<Wire> allWires = new ArrayList<Wire>();
    public  ArrayList<String> allColors = new ArrayList<String>();

    public void countWires() {
        Modules mod = new Modules();

        reds = 0;
        blues = 0;
        blacks = 0;
        whites = 0;
        yellows = 0;
        blanks = 0;
        for (int w = 0; w < 6; w++) {
            Location currentPos = mod.selected.getCenter().left(85).below(Wire.Positions.get(w));
            ArrayList<Integer>colors = getWireColor(currentPos);
            allWires.add(new Wire(colors.get(0),colors.get(1),colors.get(2),w));
        }
        for (Wire w : allWires) {
            Integer red = w.red;
            Integer green = w.green;
            Integer blue = w.blue;
            if (red+green+blue == 0) {
                System.out.println("Adding to Black");
                w.color = "black";
                blacks ++;
            }else if (red > 20 && green >= 20 && blue >= 18) {
                System.out.println("Adding to White");
                w.color = "white";
                whites ++;
            }else if (blue > red && blue > green && blue > 10) {
                System.out.println("Adding to Blue");
                w.color = "blue";
                blues ++;
            }else if (red > 20 && green < 10 && blue < 10) {
                System.out.println("Adding to Red");
                w.color = "red";
                reds++;
            }else if (red > 20 && green >= 20 && blue < 10) {
                System.out.println("Adding to Yellow");
                w.color = "yellow";
                yellows++;
            }else {
                System.out.println("Adding to Blank");
                w.color = "blank";
                blanks++;
            }


            System.out.print("\n"+w.position+"\n");
        }
        count = reds+blues+blacks+whites+yellows;
        System.out.println("All Wires : " + count);
        for (Wire w : allWires) {
            allColors.add(w.color);
        }
        System.out.println("REDS : "+reds+"\nBLUES: "+blues+"\nBLACKS: "+blacks+"\nWHITES: "+whites+"\nYELLOWS: "+yellows+"\nBLANKS: "+blanks);
        mod.selected.getCenter().left(85).below(Wire.Positions.get(cutWires())).click();
        cutWires();

    }


    public  ArrayList<Integer> getWireColor(Location spot) {

        Color rgb = spot.getColor();
        Integer red = Math.round(rgb.getRed()/10), green = Math.round(rgb.getGreen()/10), blue = Math.round(rgb.getBlue()/10);
        System.out.println("RED: "+red+" - GREEN: " + green+ " - BLUE: "+ blue);
        return new ArrayList<Integer>(Arrays.asList(red,green,blue));

    }


    public  Integer cutWires() {
        switch (count) {
            case 3:
                if (reds == 0) {
                    return 2;
                }else if (getLastWire().color == "white") {
                    return getLastWire().position;
                }else if (blues > 1) {
                    return getLastWireByColor("blue").position;
                }else {
                    return getLastWire().position;
                }
            case 4:
                if (reds > 1 && Integer.valueOf(Main.serialNo.substring(Main.serialNo.length()-1))%2 == 1) {
                    return getLastWireByColor("red").position;
                }else if (getLastWire().color == "yellow" && reds ==0) {
                    return getFirstWire().position;
                }else if (blues == 1) {
                    return getFirstWire().position;
                }else if (yellows > 1) {
                    return getLastWire().position;
                }else {
                    return getWireByIndex(1).position;
                }
            case 5:
                if (getLastWire().color == "black" && Integer.valueOf(Main.serialNo.substring(Main.serialNo.length()-1))%2 == 1) {
                    return getWireByIndex(3).position;
                }else if (reds == 1 && yellows > 1) {
                    return getFirstWire().position;
                }else if (blacks == 0) {
                    return getWireByIndex(1).position;
                }else {
                    return getFirstWire().position;
                }
            case 6:
                if (yellows == 0 && Integer.valueOf(Main.serialNo.substring(Main.serialNo.length()-1))%2 == 1) {
                    return getWireByIndex(2).position;
                }else if (yellows == 1 && whites > 1) {
                    return getWireByIndex(3).position;
                }else if (reds == 0) {
                    return getLastWire().position;
                }else {
                    return getWireByIndex(3).position;
                }
            default:
                break;
        }
        return null;
    }

    public  Wire getLastWire() {
        ArrayList<Wire> temp  = new ArrayList<Wire>();
        for (Wire w : allWires) {
            if (w.color != "blank") {
                temp.add(w);
            }
        }
        return temp.get(temp.size()-1);
    }

    public  Wire getFirstWire() {
        ArrayList<Wire> temp  = new ArrayList<Wire>();
        for (Wire w : allWires) {
            if (w.color != "blank") {
                temp.add(w);
            }
        }
        return temp.get(0);
    }

    public  Wire getWireByIndex(Integer index) {
        ArrayList<Wire> temp  = new ArrayList<Wire>();
        for (Wire w : allWires) {
            if (w.color != "blank") {
                temp.add(w);
            }
        }
        return temp.get(index);
    }

    public  Wire getLastWireByColor(String color) {
        ArrayList<String> colors = new ArrayList<String>();
        for (Wire w : allWires) {
            colors.add(w.color);
        }
        return allWires.get(colors.lastIndexOf(color));
    }

}
