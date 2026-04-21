
package Quiz;

/*
 * Irene Feng Nov 2022
 * This is the class where we create the Quiz and run it. It has the main method.  
 */
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import Game.GameWriteable;

public class Quiz implements GameWriteable {
        static Scanner sc = new Scanner(System.in);
        // HashMap to track category popularity across multiple games
        static HashMap<String, Integer> categoryPopularity = new HashMap<>();
        private String resultCategory;
        
        public Quiz() {
                this.resultCategory = "None";
        }

        @Override
        public String getGameName() {
                return "BuzzFeed Quiz";
        }

        @Override
        public void play() {
                playQuiz();
        }

        private void playQuiz() {
                // Create Categories
                Category Vanilla = new Category("Vanilla",
                                "You have fast hands and no patience. You enjoy high risk, high reward and you speed through everything.");
                Category NetheriteOP = new Category("Netherite OP", 
                                "You are a patient person, preferring to take it slow and steady and take a duel of attrition; boring but reliable.");
                Category SMP = new Category("SMP (Survival multiplayer)",
                                "You are a strategic mastermind. You strive under un-optimal conditions when the odds are against you. You don’t have a set strategy, you adapt to what is around you.");
                // Create Questions
                Question q1 = new Question("Which one of these colors resonates with you the most?");
                // Attach Answers to Questions
                q1.possibleAnswers[0] = new Answer("Red", Vanilla);
                q1.possibleAnswers[1] = new Answer("Gold", SMP);
                q1.possibleAnswers[2] = new Answer("Grey", NetheriteOP);

                Question q2 = new Question("The movie theater is empty; you can choose to sit wherever you like. Where do you go?");
                q2.possibleAnswers[0] = new Answer("Wherever’s open, you’re already walking", Vanilla);
                q2.possibleAnswers[1] = new Answer("Middle row, middle seat, perfect balance", NetheriteOP);
                q2.possibleAnswers[2] = new Answer("A spot with a good view and a quick exit route", SMP);

                Question q3 = new Question("You’re at a party with a bunch of executives, what are you doing?");
                q3.possibleAnswers[0] = new Answer("You're talking with the executives, that's the most strategic use of your time, you think", SMP);
                q3.possibleAnswers[1] = new Answer("You're playing on the basketball court; full energy", Vanilla);
                q3.possibleAnswers[2] = new Answer("You're lying on the sofa, scrolling and taking life slow", NetheriteOP);

                Question q4 = new Question("You were assigned a school book, how fast do you read it?");
                q4.possibleAnswers[0] = new Answer("You read it as fast as you can, skimming it even", Vanilla);
                q4.possibleAnswers[1] = new Answer("You closely read every word, annotating with care", NetheriteOP);
                q4.possibleAnswers[2] = new Answer("Your reading speed varies, you go fast when a section isn’t important and lock in when it is", SMP);

                Question q5 = new Question("In a group conversation, you're usually the one to...");
                q5.possibleAnswers[0] = new Answer("Jump in often", Vanilla);
                q5.possibleAnswers[1] = new Answer("Connect everyone's ideas together", SMP);
                q5.possibleAnswers[2] = new Answer("Listen the longest before speaking", NetheriteOP);

                Question q6 = new Question("You are locked in a completely white infinity room as a social experiment. What do you do?");
                q6.possibleAnswers[0] = new Answer("You try to find cracks in the room to see if there is a way out", SMP);
                q6.possibleAnswers[1] = new Answer("You grow restless and start clawing at the walls like an animal", Vanilla);
                q6.possibleAnswers[2] = new Answer("You sit on the white chair in the middle of the room and meditate", NetheriteOP);

                Question q7 = new Question("You get the procedure for your lab double period. It is complicated and two pages long. You would…");
                q7.possibleAnswers[0] = new Answer("Skim it and just start", Vanilla);
                q7.possibleAnswers[1] = new Answer("Identify and read the parts that matter the most", SMP);
                q7.possibleAnswers[2] = new Answer("Read everything carefully", NetheriteOP);

                Question q8 = new Question("You’re about to try learning a new hobby. How do you start?");
                q8.possibleAnswers[0] = new Answer("Dive right in and see what happens", Vanilla);
                q8.possibleAnswers[1] = new Answer("Practice the basics until you master them", NetheriteOP);
                q8.possibleAnswers[2] = new Answer("Mix different techniques and see what fits", SMP);
                
                // For each question, ask, read input, store answer.
                Question[] qList = { q1, q2, q3, q4, q5, q6, q7, q8 };
                for (Question q : qList) {
                        Category c = q.ask(sc);
                        c.points++;
                }
                Category[] cList = { Vanilla, NetheriteOP, SMP};
                // these need to be in the same order or the points will be incorrect!
                int index = getMostPopularCatIndex(cList);
                System.out.println("If you were a Minecraft PVP kit, you would be: " + cList[index].label + ". ");
                System.out.println(cList[index].description);
                resultCategory = cList[index].label;
                updateCategoryPopularity(resultCategory);
                System.out.println("\nWould you like to play again? (1 = Yes, 2 = No)");
                int playAgain = sc.nextInt();
                if (playAgain == 1) {
                        for (Category c : cList) {
                                c.points = 0;
                        }
                        playQuiz();
                }

        }

        public static void gameIntro() {
                System.out.println("Which Minecraft PVP kit are you?");
                System.out.println("You get to choose numbers 1-3 for every question. Enter '1' to play!");
                int play = sc.nextInt();
                if (play != 1) {
                        System.out.println("Unidentifiable input. Please enter '1' to play");
                        gameIntro();
                }
        }

        public static int getMostPopularCatIndex(Category[] counts) {
                int maxCount = 0;
                int maxIndex = 0;
                for (int i = 0; i < counts.length; i++) {
                        if (counts[i].points > maxCount) {
                                maxCount = counts[i].points;
                                maxIndex = i;
                        }
                }
                return maxIndex;
        }
        
        public static void updateCategoryPopularity(String category) {
                if (categoryPopularity.containsKey(category)) {
                        categoryPopularity.put(category, categoryPopularity.get(category) + 1);
                } else {
                        categoryPopularity.put(category, 1);
                }
        }
        public static void displayCategoryPopularity() {
                System.out.println("\nCategory Popularity Statistics");
                System.out.println("Category,Count");
                for (Map.Entry<String, Integer> entry : categoryPopularity.entrySet()) {
                        System.out.println(entry.getKey() + "," + entry.getValue());
                }
                String mostPopular = "";
                int maxCount = 0;
                for (Map.Entry<String, Integer> entry : categoryPopularity.entrySet()) {
                        if (entry.getValue() > maxCount) {
                                maxCount = entry.getValue();
                                mostPopular = entry.getKey();
                        }
                }
                if (!mostPopular.isEmpty()) {
                        System.out.println("\nMost Popular Category Overall: " + mostPopular + " (" + maxCount + " times)");
                }
        }

        @Override
        public String getScore() {
                String mostPopular = getMostPopularCategory();
                int count = categoryPopularity.getOrDefault(mostPopular, 0);
                return mostPopular + ": " + count;
        }

        private String getMostPopularCategory() {
                String mostPopular = "None";
                int maxCount = 0;
                for (Map.Entry<String, Integer> entry : categoryPopularity.entrySet()) {
                        if (entry.getValue() > maxCount) {
                                maxCount = entry.getValue();
                                mostPopular = entry.getKey();
                        }
                }
                return mostPopular;
        }

        @Override
        public boolean isHighScore(String score, String currentHighScore) {
                if (currentHighScore == null) {
                        return true;
                }
                
                try {
                        int currentCount = Integer.parseInt(score.split(": ")[1]);
                        int highScoreCount = Integer.parseInt(currentHighScore.split(": ")[1]);
                        return currentCount > highScoreCount;
                } catch (Exception e) {
                        return false;
                }
        }

        @Override
        public void writeHighScore(File f) {
                GameWriteable.super.writeHighScore(f);
        }
}
