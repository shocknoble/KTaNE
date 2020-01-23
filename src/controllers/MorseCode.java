package controllers;

import org.sikuli.script.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;


public class MorseCode {

    public Modules mod = new Modules();

    public Location light = mod.selected.getCenter().above(105).left(45);
    public Color lightOn = new Color(251,222,71);
    public Color lightOff = new Color(117,62,41);

    public HashMap<String, String> letterSeqs = new HashMap<String,String>();


    /*
            Jan 16th at 5:47pm:

    From *selected* center, up 100 px and left 45 px, the light ON  gives off r245 g207 b67
    From *selected* center, up 100 px and left 45 px, the light OFF gives off r81  g45  b29

    From *selected* center, up 105 px and left 45 px, the light ON  gives off r251 g222 b71
    From *selected* center, up 105 px and left 45 px, the light OFF gives off r117 g62  b41

    Morse DOT  roughly equates to 4 (four) / 5(five)  frames of the light being on
    Morse DASH roughly equates to 11(eleven) / 15(fifteen) frames of the light being on
    Morse SPACE between any two DOT/DASHes within a single letter is measured at 8(eight) frames of the light being off
    Morse SPACE between any two LETTERS is measured at 30(thirty) frames of the light being off
    Morse SPACE between the end and beginning of the WORD is likely measured at 75(seventy five) frames of the light being off


     */
    public void waitForSpace() throws InterruptedException {

        Integer counter = 0;
        while (counter < 70) {
            Integer red = light.getColor().getRed();
            System.out.println(counter);
            if (Math.abs(red-lightOff.getRed()) < 5) {
                counter++;
            }else {
                counter = 0;
            }
        }
        System.out.println("Starting now");
        getFrames();



    }





    public void getFrames() throws InterruptedException {
        ArrayList<Integer> frames = new ArrayList<>();
        Integer counter = 0;
        Integer fullTime = 0;
        while (counter < 140 && fullTime < 1400) {
            Integer red = light.getColor().getRed();

            if (Math.abs(red - lightOn.getRed()) < 5) {
                frames.add(1);
                counter = 0;
            } else {
                frames.add(0);
                counter ++;
            }
            fullTime++;
            Thread.sleep(1);
        }
        System.out.println("FRAMES" + frames);
        parseAllFrames(frames);

    }




    public void parseAllFrames(ArrayList<Integer> frames) throws InterruptedException {
        ArrayList<String> results = new ArrayList<String>();
        ArrayList<Integer> fullString = cutFirstFrames(frames);
        System.out.println("ALL BUT FIRST FRAMES        : " + fullString);
        fullString = cutLastFrames(fullString);
        System.out.println("ALL BUT FIRST & LAST FRAMES : " + fullString);
        Integer spaces = 0;
        while (spaces < 3 && fullString.size() > 20) {

            System.out.println("REMAINING FRAMES : " + fullString.size());
            Integer length = getNextD(fullString);
            String temp = dashOrDot(getNextD(fullString),fullString.get(0));
            if (temp != ""){
                results.add(dashOrDot(getNextD(fullString),fullString.get(0)));
            }
            if (temp == "space") {
                spaces++;
            }
            for (int f = 0; f < length; f++) {
                fullString.remove(0);
            }
        }
        System.out.println(results);

        String firstLetter = "";
        Integer firstspace = 0;
        String secondLetter = "";
        Integer secondspace = 0;
        String thirdLetter = "";

        for (int t = 0 ; t < results.size(); t++) {
            if (results.get(t) != "space") {
                firstLetter += (results.get(t)+" ");
            }else {
                firstspace = t;
                break;
            }
        }
        for (int t = firstspace+1; t < results.size(); t++) {
            if (results.get(t) != "space") {
                secondLetter += (results.get(t)+" ");
            }else {
                secondspace = t;
                break;
            }
        }
        for (int t = secondspace+1; t < results.size(); t++) {
            if (results.get(t) != "space") {
                thirdLetter += (results.get(t)+" ");
            }else {
                break;
            }
        }
        firstLetter = firstLetter.substring(0,firstLetter.length()-1);
        secondLetter = secondLetter.substring(0,secondLetter.length()-1);
        thirdLetter = thirdLetter.substring(0,thirdLetter.length()-1);
        String first3 = decodeLetter(firstLetter)+decodeLetter(secondLetter)+decodeLetter(thirdLetter);
        System.out.println(first3);

        moveCounter(possibleWordMappings(first3));

    }

    public ArrayList<Integer> cutFirstFrames(ArrayList<Integer> frames) {
        ArrayList<Integer> cutFrames = new ArrayList<>();
        Integer cutIndex = null;

        for (int f = 1; f < frames.size(); f++) {
            if (frames.get(f) != frames.get(0)) {
                cutIndex = f;
                break;
            }
        }

        for (int f = 0; f < cutIndex; f++) {
            frames.remove(0);
        }
        cutFrames = frames;
        return cutFrames;
    }

    public ArrayList<Integer> cutLastFrames(ArrayList<Integer> frames) {

        Integer cutIndex = null;
       for (int f = frames.size()-2; f > 0; f--) {
           if (frames.get(f) != frames.get(frames.size()-1)) {
               cutIndex = frames.size()-f;
               break;
           }
       }
        for (int f = 1 ; f < cutIndex; f++) {
            frames.remove(frames.size()-1);
        }

        return frames;
    }


    public Integer getNextD(ArrayList<Integer> frames) {
        Integer count = null;

        for (int f = 1; f < frames.size(); f++) {
            if (frames.size() != 0) {
                if (frames.get(f) != frames.get(0)) {
                    count = f;
                    break;
                }
            }
        }
        return count;
    }

    public String dashOrDot(Integer frames, Integer bit) {
        switch (bit) {
            case 0:
                switch (frames) {
                    case 14:
                    case 15:
                    case 16:
                    case 17:

                        return "";
                    case 58:
                    case 59:
                    case 60:
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                        System.out.print("End of letter...");
                        return "space";
                    case 149:
                    case 150:
                    case 151:
                    case 152:
                        System.out.print("End of WORD");
                        return "END";
                    default:
                        System.out.println("Unrecognized frame length with light off : " + frames);
                        break;
                }
            case 1:
                switch (frames) {
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                        return "dot";
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                    case 50:
                        return "dash";
                    default:
                        System.out.println("Unrecognized frame length with light on : " + frames);
                        return "";
                }
        }
        return"";
    }

    public void createAllMorseLetters() {

        letterSeqs.put("dot dash", "a");
        letterSeqs.put("dash dot dot dot", "b");
        letterSeqs.put("dash dot dash dot", "c");
        letterSeqs.put("dot", "e");
        letterSeqs.put("dot dot dash dot", "f");
        letterSeqs.put("dash dash dot", "g");
        letterSeqs.put("dot dot dot dot", "h");
        letterSeqs.put("dot dot", "i");
        letterSeqs.put("dash dot dash", "k");
        letterSeqs.put("dot dash dot dot", "l");
        letterSeqs.put("dash dash", "m");
        letterSeqs.put("dash dot", "n");
        letterSeqs.put("dash dash dash", "o");
        letterSeqs.put("dot dash dot", "r");
        letterSeqs.put("dot dot dot", "s");
        letterSeqs.put("dash", "t");
        letterSeqs.put("dot dot dot dash", "v");
        letterSeqs.put("dash dot dot dash", "x");

    }


    public Integer possibleWordMappings(String prefix) {
        ArrayList<String> words = new ArrayList<String>(Arrays.asList(new String[] {"shell","halls","slick","trick","boxes","leaks","strobe","bistro","flick","bombs","break","brick","steak","sting","vector","beats"}));

        for (int s = 0; s < words.size(); s++) {
            if (words.get(s).startsWith(prefix)) {
                System.out.println("WORD HAS INDEX OF : " + s);;
                return s;
            }
        }
        for (int s = 0; s < words.size(); s++) {
            if (words.get(s).contains(prefix)) {
                System.out.println("Word not found from prefix only, current index is a partial match : " + s);
                return s;
            }
        }
        System.out.println("Nothing found for morse word???");
        return null;
    }


    public String decodeLetter(String ticks) {
        System.out.println("TICKS TO DECODE INTO A LETTER : " + ticks);
        return  letterSeqs.get(ticks);
    }

    public void moveCounter(Integer clicks) throws InterruptedException {
        for (int c = 0; c < clicks; c++) {
            mod.selected.getCenter().right(80).below(20).click();
            Thread.sleep(100);
        }
        Thread.sleep(500);
        mod.selected.getCenter().below(90).click();
    }

}
