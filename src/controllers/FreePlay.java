package controllers;


import org.sikuli.script.Location;

public class FreePlay {


    Modules mod = new Modules();
    Location timeUp = mod.game.getCenter().left(100).above(225);
    Location modsUp = mod.game.getCenter().left(100).above(140);
    Location startButton = mod.game.getCenter().left(140).below(200);

    public void startGame() throws InterruptedException {

        System.out.println(mod.game.getCenter());
        mod.game.getCenter().left(400).click();
        Thread.sleep(1000);

        for (int t = 0; t < 10; t++) {
            timeUp.click();
        }
        for (int t = 0; t < 8; t++) {
            modsUp.click();
        }
        startButton.click();
        Thread.sleep(2000);
        mod.game.getCenter().click();
        Thread.sleep(500);
        mod.game.getCenter().click();
        Thread.sleep(500);
        mod.game.getCenter().click();
        Thread.sleep(500);
        mod.game.getCenter().click();
        Thread.sleep(5500);
        mod.game.getCenter().click();
        Thread.sleep(2100);

        // These sleeps are spread out to make sure we get through the opening blackout period, and that the bomb is properly selected.
        // It's at the end of all of these that our timer starts so that we know the current time for the Button modules.

    }
}
