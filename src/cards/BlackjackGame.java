package cards;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import processing.core.PApplet;
import Game.GameWriteable;

public class BlackjackGame implements GameWriteable {
    private App processingApp;
    private CountDownLatch latch;

    public BlackjackGame() {
        this.processingApp = null;
    }

    @Override
    public String getGameName() {
        return "Blackjack";
    }

    @Override
    public void play() {
        processingApp = new App();
        
        latch = new CountDownLatch(1);
        processingApp.setGameLatch(latch);
        
        PApplet.runSketch(new String[]{"Blackjack"}, processingApp);
        
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.err.println("Game interrupted: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String getScore() {
        if (processingApp != null) {
            int totalSpent = 1000 * (processingApp.cardGame.getBuyIn() + 1);
            int profit = processingApp.cardGame.balance - totalSpent;
            return String.valueOf(profit);
        }
        return "0";
    }

    @Override
    public boolean isHighScore(String score, String currentHighScore) {
        try {
            int newScore = Integer.parseInt(score);
            
            // If no previous high score, this is a high score
            if (currentHighScore == null || currentHighScore.isEmpty()) {
                return true;
            }
            
            int bestScore = Integer.parseInt(currentHighScore);
            // Higher profit = better score
            return newScore > bestScore;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void writeHighScore(File f) {
        // Use the default implementation from GameWriteable
        GameWriteable.super.writeHighScore(f);
    }
}
