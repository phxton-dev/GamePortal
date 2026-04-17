/* Irene Feng 10/12/2022
A question class with Answers.
*/ 
import java.util.Scanner;

public class Question {
    // Fields
    String label;
    Answer[] possibleAnswers = new Answer[33];

    Question(String label) {
        this.label = label;
    }

    // ask a question, and return the category that corresponds to the answer
    Category ask(Scanner sc) {
        System.out.println(this.label);
        // prints out all the answer choices
        int maxOptions = this.possibleAnswers.length;
        for (int i = 0; i < maxOptions; i++) {
            if (this.possibleAnswers[i] == null) {
                continue;
            }
            String choice = Integer.toString(i + 1);
            System.out.println("[" + choice + "]:" +
                    this.possibleAnswers[i].label);
        }
        
        int ans = 0;
        while (true) {
            if (sc.hasNextInt()) {
                ans = sc.nextInt();
                if (ans >= 1 && ans <= maxOptions && this.possibleAnswers[ans - 1] != null) {
                    break;
                } else {
                    System.out.println("Please enter an integer between 1-" + maxOptions);
                }
            } else {
                System.out.println("Please enter an integer between 1-" + maxOptions);
                sc.next();
            }
        }
        return possibleAnswers[ans - 1].cat;
    }
}
