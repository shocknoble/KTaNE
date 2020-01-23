package domain.first;

import java.util.ArrayList;
import java.util.Arrays;

public class Label {

    public String text;
    public ArrayList<String> wordList;

    public Label(String text, String[] words) {
        this.text = text;
        this.wordList = new ArrayList<String>(Arrays.asList(words));
    }
}
