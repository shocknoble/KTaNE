package controllers;

import domain.Letter;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.util.ArrayList;
import java.util.Arrays;

public class Passwords {

    public ArrayList<String> words = new ArrayList<String>(Arrays.asList(new String[]{"about", "after","again","below","could","every","first","found","great","house","large","learn","never","other","place","plant","point","right","small","sound","spell","still","study","their","there","these","thing","think","three","water","where","which","world","would","write"}));
    public ArrayList<String> possibleWords = new ArrayList<String>();
    public Modules mod = new Modules();
    public ArrayList<Letter> allLetters = new ArrayList<Letter>();
    public Region boxes = mod.selected.grow(-30,-53,-110,-120);
    Letter a = new Letter("a");
    Letter b = new Letter("b");
    Letter c = new Letter("c");
    Letter d = new Letter("d");
    Letter e = new Letter("e");
    Letter f = new Letter("f");
    Letter g = new Letter("g");
    Letter h = new Letter("h");
    Letter i = new Letter("i");
    Letter j = new Letter("j");
    Letter k = new Letter("k");
    Letter l = new Letter("l");
    Letter m = new Letter("m");
    Letter n = new Letter("n");
    Letter o = new Letter("o");
    Letter p = new Letter("p");
    Letter q = new Letter("q");
    Letter r = new Letter("r");
    Letter s = new Letter("s");
    Letter t = new Letter("t");
    Letter u = new Letter("u");
    Letter v = new Letter("v");
    Letter w = new Letter("w");
    Letter x = new Letter("x");
    Letter y = new Letter("y");
    Letter z = new Letter("z");

    public void initModule() {
        allLetters.addAll(Arrays.asList(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z));
        boxes.setRaster(0,5);
        System.out.println(boxes.getCol(0));
    }

    public ArrayList<String> getLettersByColumn(Integer col) throws InterruptedException {
        ArrayList<String> letters = new ArrayList<String>();
        Region letterSpace = boxes.getCol(col).grow(2,2,3,3);
        for (int ltr = 0; ltr < 6; ltr ++) {
            Match letterFound = letterSpace.findBest(a.icon,b.icon,c.icon,d.icon,e.icon,f.icon,g.icon,h.icon,i.icon,j.icon,k.icon,l.icon,m.icon,n.icon,o.icon,p.icon,q.icon,r.icon,s.icon,t.icon,u.icon,v.icon,w.icon,x.icon,y.icon,z.icon);
            try {
                letters.add(allLetters.get(letterFound.getIndex()).letter);
            } catch (Exception e) {
                continue;

            }
            System.out.println("Adding letter " + allLetters.get(letterFound.getIndex()).letter + " to the list.");
            boxes.getCol(col).getCenter().above(70).click();
            Thread.sleep(100);
        }
        return letters;
    }

    public ArrayList<String> getAllPossibleWords() throws InterruptedException {
        ArrayList<String> currentCol = getLettersByColumn(0);
        for (String word : words) {
            if (currentCol.contains(word.substring(0,1))) {
                possibleWords.add(word);
            }
        }
        return currentCol;
    }

    public void spellFinalWord(String word) {
        ArrayList<Pattern> lettersToFind = new ArrayList<Pattern>();
        for (int in = 0; in < 5; in++) {
            for (Letter letter : allLetters) {
                if (letter.letter.equalsIgnoreCase(word.substring(in,in+1))) {
                    lettersToFind.add(letter.icon);
                    System.out.println("Adding " +letter.letter+ " to final word!");
                    break;
                }
            }
        }
        for (int col = 0; col < 5; col++) {
            while (true) {
                if (!boxes.getCol(col).has(lettersToFind.get(col).similar(0.91),0.75)) {
                     boxes.getCol(col).getCenter().above(70).click();
                } else {
                    break;
                }
            }
        }
    }


    public boolean fullSolve() throws FindFailed, InterruptedException {
        initModule();

        ArrayList<String> col0 = getAllPossibleWords();
        ArrayList<String> col1 = getLettersByColumn(1);
        ArrayList<String> col2 = getLettersByColumn(2);
        ArrayList<String> col3 = getLettersByColumn(3);
        ArrayList<String> col4 = getLettersByColumn(4);

        System.out.println(col0);
        System.out.println(col1);
        System.out.println(col2);
        System.out.println(col3);
        System.out.println(col4);

        for (String word : possibleWords) {
            System.out.println("POSSIBLE WORD : "+word);
            if (col0.contains(word.substring(0,1)) && col1.contains(word.substring(1,2)) && col2.contains(word.substring(2,3)) && col3.contains(word.substring(3,4)) && col4.contains(word.substring(4,5))) {
                spellFinalWord(word);
                mod.selected.getCenter().below(100).click();
                return true;
            }
        }
        return false;

    }




}
