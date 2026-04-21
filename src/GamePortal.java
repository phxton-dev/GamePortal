import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Game.Game;
import cards.BlackjackGame;
import Quiz.Quiz;
import Game.ErrorCheck;

public class GamePortal {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Game> games = new ArrayList<Game>();
    
    public static void main(String[] args) {
        HashMap<String, Integer> gameCounts = new HashMap<String, Integer>();
        File f = new File("Highscore.csv");
        
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {
                System.err.println("Could not create Highscore.csv file: " + e.getMessage());
            }
        }
        
        boolean playing = true;
        while (playing) {
            loadGames();
            
            System.out.println("\n===== GAME PORTAL =====");
            System.out.println("Which game would you like to play?");
            printGameChoices();
            System.out.println("[" + (games.size() + 1) + "]: Exit Game Portal");
            
            int choice = ErrorCheck.getInt(sc);
            
            if (choice == games.size() + 1) {
                System.out.println("Thanks for playing! Goodbye!");
                playing = false;
                break;
            }
            
            if (choice < 1 || choice > games.size()) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            
            Game g = games.get(choice - 1);
            System.out.println("\nYou're playing: " + g.getGameName());

            g.play();
            g.writeHighScore(f);
            
            String gameKey = g.getGameName();
            if (gameCounts.containsKey(gameKey)) {
                gameCounts.put(gameKey, gameCounts.get(gameKey) + 1);
            } else {
                gameCounts.put(gameKey, 1);
            }
        }
        sc.close();
    }

    public static void loadGames() {
        games.clear();
        games.add(new MovingSymbols());
        games.add(new Quiz());
        games.add(new BlackjackGame());
    }

    public static void printGameChoices() {
        int n = 1;
        for (Game s : games) {
            System.out.println("[" + (n++) + "]: " + s.getGameName());
        }
    }

    public static Game getGameChoice() {
        int choice = ErrorCheck.getInt(sc);
        // for it to be numbered, we can't use hashmaps.
        while (choice < 1 || choice > games.size()) {
            System.out.println("We don't have this number. Try again.");
            choice = ErrorCheck.getInt(sc);
        }

        // valid game choice
        return games.get(choice - 1);
    }
}