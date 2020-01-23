package main;

import GUI.Queries;

import controllers.*;

import org.sikuli.basics.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static String serialNo;
    public static Integer batteries;
    public static boolean car, frk;
    public static Integer timeLeft = 600;

    public static Thread solver;

    public static void main(String[] args) throws InterruptedException {
        Settings.MoveMouseDelay = 0.1f;
        Settings.ClickDelay = 0.1f;
        Settings.AutoWaitTimeout = 1f;
        Settings.HighlightTransparent = false;
        Settings.ObserveScanRate = 15f;

        FreePlay play = new FreePlay();
        ScheduledExecutorService timer = Executors.newSingleThreadScheduledExecutor();
        play.startGame();
        timer.scheduleAtFixedRate(() -> timeLeft --, 1, 1, TimeUnit.SECONDS);
        Queries query = new Queries();

    }

    public static Integer getCountdown() {
        return timeLeft;
    }

}



