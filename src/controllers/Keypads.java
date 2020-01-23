package controllers;

import domain.Keypad;

import java.util.ArrayList;
import java.util.Arrays;

public class Keypads {
            public static Keypad cdot =        new Keypad("cdot",        new ArrayList<Integer>(Arrays.asList(4)));
            public static Keypad cflipped =    new Keypad("cflipped",    new ArrayList<Integer>(Arrays.asList(0, 1)));
            public static Keypad doubleK =     new Keypad("doubleK",     new ArrayList<Integer>(Arrays.asList(2, 3)));
            public static Keypad edot =        new Keypad("edot",        new ArrayList<Integer>(Arrays.asList(2, 3)));
            public static Keypad magnify =     new Keypad("magnify",     new ArrayList<Integer>(Arrays.asList(1, 5)));
            public static Keypad question =    new Keypad("question",    new ArrayList<Integer>(Arrays.asList(1, 3)));
            public static Keypad spaceship =   new Keypad("spaceship",   new ArrayList<Integer>(Arrays.asList(0, 3)));
            public static Keypad squiggleH =   new Keypad("squiggleH",   new ArrayList<Integer>(Arrays.asList(0, 1)));
            public static Keypad tongue =      new Keypad("tongue",      new ArrayList<Integer>(Arrays.asList(3, 4)));
            public static Keypad trident =     new Keypad("trident",     new ArrayList<Integer>(Arrays.asList(4, 5)));
            public static Keypad whitestar =   new Keypad("whitestar",   new ArrayList<Integer>(Arrays.asList(3, 4)));
            public static Keypad six =         new Keypad("six",         new ArrayList<Integer>(Arrays.asList(3, 5)));
            public static Keypad omega =       new Keypad("omega",       new ArrayList<Integer>(Arrays.asList(5)));
            public static Keypad notequal =    new Keypad("notequal",    new ArrayList<Integer>(Arrays.asList(5)));
            public static Keypad lightningN =  new Keypad("lightningN",  new ArrayList<Integer>(Arrays.asList(5)));
            public static Keypad monkas =      new Keypad("monkas",      new ArrayList<Integer>(Arrays.asList(2)));
            public static Keypad lambda =      new Keypad("lambda",      new ArrayList<Integer>(Arrays.asList(0,2)));
            public static Keypad half3 =       new Keypad("half3",       new ArrayList<Integer>(Arrays.asList(2)));
            public static Keypad disney =      new Keypad("disney",      new ArrayList<Integer>(Arrays.asList(1,2)));
            public static Keypad ae =          new Keypad("ae",          new ArrayList<Integer>(Arrays.asList(5)));
            public static Keypad at =          new Keypad("at",          new ArrayList<Integer>(Arrays.asList(0)));
            public static Keypad tampabay =    new Keypad("tampabay",    new ArrayList<Integer>(Arrays.asList(3,4)));
            public static Keypad blackstar =   new Keypad("blackstar",   new ArrayList<Integer>(Arrays.asList(4)));
            public static Keypad devil3 =      new Keypad("devil3",      new ArrayList<Integer>(Arrays.asList(4)));
            public static Keypad paragraph =   new Keypad("paragraph",   new ArrayList<Integer>(Arrays.asList(3,4)));
            public static Keypad lightning =   new Keypad("lightning",   new ArrayList<Integer>(Arrays.asList(0)));
            public static Keypad copyright =   new Keypad("copyright",   new ArrayList<Integer>(Arrays.asList(2)));
    public static Keypad[] tempIcons = {
            cdot, doubleK, edot, magnify, question, spaceship, squiggleH, tongue, trident, whitestar, six, omega, notequal, lightningN, monkas, lambda, half3, disney, ae, at, tampabay, blackstar, devil3, paragraph, lightning,copyright,cflipped
    };


    public  static ArrayList<Keypad> allKeypads = new ArrayList<Keypad>(Arrays.asList(tempIcons));
            static ArrayList<Keypad> listA = new ArrayList<Keypad>(Arrays.asList(magnify,at,lambda,lightning,spaceship,squiggleH,cflipped));
            static ArrayList<Keypad> listB = new ArrayList<Keypad>(Arrays.asList(edot,magnify,cflipped,disney,whitestar,squiggleH,question));
            static ArrayList<Keypad> listC = new ArrayList<Keypad>(Arrays.asList(copyright,monkas,disney,doubleK,half3,lambda,whitestar));
            static ArrayList<Keypad> listD = new ArrayList<Keypad>(Arrays.asList(six,paragraph,tampabay,spaceship,doubleK,question,tongue));
            static ArrayList<Keypad> listE = new ArrayList<Keypad>(Arrays.asList(trident,tongue,tampabay,cdot,paragraph,devil3,blackstar));
            static ArrayList<Keypad> listF = new ArrayList<Keypad>(Arrays.asList(six,edot,notequal,ae,trident,lightningN,omega));
            static ArrayList<ArrayList> listOfLists = new ArrayList<ArrayList>(Arrays.asList(listA,listB,listC,listD,listE,listF));

    public static ArrayList getKeypadList(ArrayList<Keypad> icons) {
        for (int i = 0; i < icons.size(); i++) {
            System.out.println(icons.get(i).name);
        }
        ArrayList<ArrayList> possibleLists = listOfLists;
        for (int l = 0; l < listOfLists.size(); l++) {
            if (listOfLists.get(l).containsAll(icons)) {
                return listOfLists.get(l);
            }
        }
        System.out.println("Remaining lists: "+possibleLists.size());
        return possibleLists.get(0);
    }

}
