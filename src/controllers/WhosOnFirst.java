package controllers;

import domain.first.First;
import domain.first.Label;
import org.sikuli.script.Location;
import org.sikuli.script.Region;

import java.util.ArrayList;
import java.util.Arrays;

public class WhosOnFirst {

    Modules mod = new Modules();
    public Region buttons = mod.selected.grow(-29,-96,-90,-41);
    public Region textDisplay = new Region(mod.selected.getTopLeft().x+50,mod.selected.getTopLeft().y+30,110,28);

    public ArrayList<First> allDisplays;
    public ArrayList<Label> allLabels;

    public void createTexts() {

        buttons.setRaster(3,2);

        First yes = new First("YES",2);
        First first = new First("FIRST",1);
        First display = new First ("DISPLAY",5);
        First okay = new First("OKAY",1);
        First says = new First("SAYS",5);
        First nothing = new First("NOTHING",2);
        First na = new First("",4);
        First blank = new First("BLANK",3);
        First no = new First("NO",5);
        First led = new First("LED",2);
        First lead = new First("LEAD",5);
        First read = new First("READ",3);
        First red = new First("RED",3);
        First reed = new First("REED",4);
        First leed = new First("LEED",4);
        First holdon = new First("HOLD ON",5);
        First you = new First("YOU",3);
        First youare = new First("YOU ARE",5);
        First your = new First("YOUR",3);
        First youre = new First("YOU'RE",3);
        First ur = new First("UR",0);
        First there = new First("THERE",5);
        First theyre = new First("THEY'RE",4);
        First their = new First("THEIR",3);
        First theyare = new First("THEY ARE",2);
        First see = new First("SEE",5);
        First c = new First("C",1);
        First cee = new First("CEE",5);

        Label readyLabel = new Label("READY",new String[]{"YES", "OKAY", "WHAT", "MIDDLE", "LEFT", "PRESS", "RIGHT", "BLANK", "READY", "NO", "FIRST", "UHHH", "NOTHING", "WAIT"});
        Label firstLabel = new Label("FIRST", new String[]{"LEFT", "OKAY", "YES", "MIDDLE", "NO", "RIGHT", "NOTHING", "UHHH", "WAIT", "READY", "BLANK", "WHAT", "PRESS", "FIRST"});
        Label noLabel = new Label("NO",new String[]{ "BLANK", "UHHH", "WAIT", "FIRST", "WHAT", "READY", "RIGHT", "YES", "NOTHING", "LEFT", "PRESS", "OKAY", "NO", "MIDDLE"});
        Label blankLabel = new Label("BLANK", new String[]{"WAIT", "RIGHT", "OKAY", "MIDDLE", "BLANK", "PRESS", "READY", "NOTHING", "NO", "WHAT", "LEFT", "UHHH", "YES", "FIRST"});
        Label nothingLabel = new Label("NOTHING", new String[]{"UHHH","RIGHT","OKAY","MIDDLE","YES","BLANK","NO","PRESS","LEFT","WHAT","WAIT","FIRST","NOTHING","READY"});
        Label yesLabel = new Label("YES", new String[]{"OKAY","RIGHT","UHHH","MIDDLE","FIRST","WHAT","PRESS","READY","NOTHING","YES","LEFT","BLANK","NO","WAIT"});
        Label whatLabel = new Label("WHAT", new String[]{"UHHH","WHAT","LEFT","NOTHING","READY","BLANK","MIDDLE","NO","OKAY","FIRST","WAIT","YES","PRESS","RIGHT"});
        Label uhhhLabel = new Label("UHHH", new String[]{"READY","NOTHING","LEFT","WHAT","OKAY","YES","RIGHT","NO","PRESS","BLANK","UHHH","MIDDLE","WAIT","FIRST"});
        Label leftLabel = new Label("LEFT", new String[]{"RIGHT","LEFT","FIRST","NO","MIDDLE","YES","BLANK","WHAT","UHHH","WAIT","PRESS","READY","OKAY","NOTHING"});
        Label rightLabel = new Label("RIGHT", new String[]{"YES","NOTHING","READY","PRESS","NO","WAIT","WHAT","RIGHT","MIDDLE","LEFT","UHHH","BLANK","OKAY","FIRST"});
        Label middleLabel = new Label("MIDDLE", new String[]{"BLANK","READY","OKAY","WHAT","NOTHING","PRESS","NO","WAIT","LEFT","MIDDLE","RIGHT","FIRST","UHHH","YES"});
        Label okayLabel = new Label("OKAY", new String[]{"MIDDLE","NO","FIRST","YES","UHHH","NOTHING","WAIT","OKAY","LEFT","READY","BLANK","PRESS","WHAT","RIGHT"});
        Label waitLabel = new Label("WAIT", new String[]{"UHHH","NO","BLANK","OKAY","YES","LEFT","FIRST","PRESS","WHAT","WAIT","NOTHING","READY","RIGHT","MIDDLE"});
        Label pressLabel = new Label("PRESS", new String[]{"RIGHT","MIDDLE","YES","READY","PRESS","OKAY","NOTHING","UHHH","BLANK","LEFT","FIRST","WHAT","NO","WAIT"});
        Label youLabel = new Label("YOU", new String[]{"SURE","YOU ARE","YOUR","YOU'RE","NEXT","UH HUH","UR","HOLD","WHAT?","YOU","UH","UH","LIKE","DONE","U"});
        Label youareLabel = new Label("YOU ARE", new String[]{"YOUR","NEXT","LIKE","UH HUH","WHAT?","DONE","UH UH","HOLD","YOU","U","YOU'RE","SURE","UR","YOU ARE"});
        Label yourLabel = new Label("YOUR", new String[]{"UH UH","YOU ARE","UH HUH","YOUR","NEXT","UR","SURE","U","YOU'RE","YOU","WHAT?","HOLD","LIKE","DONE"});
        Label youreLabel = new Label("YOU'RE", new String[]{"YOU","YOU'RE","UR","NEXT","UH UH","YOU ARE","U","YOUR","WHAT?","UH HUH","SURE","DONE","LIKE","HOLD"});
        Label urLabel = new Label("UR", new String[]{"DONE","U","UR","UH HUH","WHAT?","SURE","YOUR","HOLD","YOU'RE","LIKE","NEXT","UH UH","YOU ARE","YOU"});
        Label uLabel = new Label("U", new String[]{"UH HUH","SURE","NEXT","WHAT?","YOU'RE","UR","UH UH","DONE","U","YOU","LIKE","HOLD","YOU ARE","YOUR"});
        Label uhhuhLabel = new Label("UH HUH", new String[]{"UH HUH","YOUR","YOU ARE","YOU","DONE","HOLD","UH UH","NEXT","SURE","LIKE","YOU'RE","UR","U","WHAT?"});
        Label uhuhLabel = new Label("UH UH", new String[]{"UR","U","YOU ARE","YOU'RE","NEXT","UH UH","DONE","YOU","UH HUH","LIKE","YOUR","SURE","HOLD","WHAT?"});
        Label whatQLabel = new Label("WHAT?", new String[]{"YOU","HOLD","YOU'RE","YOUR","U","DONE","UH UH","LIKE","YOU ARE","UH HUH","UR","NEXT","WHAT?","SURE"});
        Label doneLabel = new Label("DONE", new String[]{"SURE","UH HUH","NEXT","WHAT?","YOUR","UR","YOU'RE","HOLD","LIKE","YOU","U","YOU ARE","UH UH","DONE"});
        Label nextLabel = new Label("NEXT", new String[]{"WHAT?","UH HUH","UH UH","YOUR","HOLD","SURE","NEXT","LIKE","DONE","YOU ARE","UR","YOU'RE","U","YOU"});
        Label holdLabel = new Label("HOLD", new String[]{"YOU ARE","U","DONE","UH UH","YOU","UR","SURE","WHAT?","YOU'RE","NEXT","HOLD","UH UH","YOUR","LIKE"});
        Label sureLabel = new Label("SURE", new String[]{"YOU ARE","DONE","LIKE","YOU'RE","YOU","HOLD","UH HUH","UR","SURE","U","WHAT?","NEXT","YOUR","UH UH"});
        Label likeLabel = new Label("LIKE", new String[]{"YOU'RE","NEXT","U","UR","HOLD","DONE","UH UH","WHAT?","UH HUH","YOU","LIKE","SURE","YOU ARE","YOUR"});


        allDisplays = new ArrayList<First>(Arrays.asList(yes, first, display, okay, says, nothing, na, blank, no, led, lead, read, red, reed, leed, holdon, you, youare, your, youre, ur, there, theyre, their, theyare, see, c, cee));
        allLabels = new ArrayList<Label>(Arrays.asList(readyLabel, firstLabel, noLabel,blankLabel,nothingLabel,yesLabel, whatLabel,uhhhLabel,leftLabel,rightLabel,middleLabel,okayLabel,waitLabel,pressLabel,youLabel,youareLabel,yourLabel,youreLabel,urLabel,uLabel,uhhuhLabel,uhuhLabel,whatQLabel,doneLabel,nextLabel,holdLabel,sureLabel,likeLabel));
    }

    public Integer getDisplay() {
        String txt = textDisplay.text();
        for (First display : allDisplays) {
            if (display.text.equalsIgnoreCase(txt)) {
                System.out.println(display.text);
                return display.buttonToRead;
            }
        }
        return allDisplays.get(6).buttonToRead;
    }


    public String getButtons(Integer buttonToRead) {
        System.out.println(textDisplay.text());
        int row;
        int col;

        switch (buttonToRead) {
            case 0:
                row = 0;
                col = 0;
                break;
            case 1:
                row = 0;
                col = 1;
                break;
            case 2:
                row = 1;
                col = 0;
                break;
            case 3:
                row = 1;
                col = 1;
                break;
            case 4:
                row = 2;
                col = 0;
                break;
            default:
                row = 2;
                col = 1;
                break;
        }

        String listOwner = buttons.getCell(row,col).grow(-7,-7,-10,-5).text();
        System.out.println("WORD TO GET LIST FROM : " + listOwner);
        if (listOwner == "YOURE") {
            listOwner = "YOU'RE";
        };
        return listOwner.trim();
    }


    public ArrayList<String> getWordList(String label) {
        ArrayList<String> wordlist = new ArrayList<String>();
        for (Label lbl : allLabels) {
            if (label.equalsIgnoreCase(lbl.text)) {
                wordlist = lbl.wordList;
                System.out.println("This is the word list to look for and then click : " + wordlist);
                return wordlist;
            }
        }
        return null;
    }

    public boolean findButtonFromList(ArrayList<String> list) {
        for (String word : list) {
            for (int r = 0; r < 3; r ++) {
                for (int c = 0; c < 2; c ++) {
                    if (buttons.getCell(r, c).grow(-7,-7,-10,-5).text().equalsIgnoreCase(word)) {
                        buttons.getCell(r,c).grow(-7,-7,-10,-5).click();
                        new Location(0,0).hover();
                        return true;
                    }
                }
            }
        }return false;
    }

    public void fullSolve() throws InterruptedException {
        createTexts();
        findButtonFromList(getWordList(getButtons(getDisplay())));
        Thread.sleep(5000);
        createTexts();
        findButtonFromList(getWordList(getButtons(getDisplay())));
        Thread.sleep(5000);
        createTexts();
        findButtonFromList(getWordList(getButtons(getDisplay())));
        Thread.sleep(5000);
    }

}
