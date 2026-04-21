import java.util.Random;
import java.io.File;
import Game.Game;

public class MovingSymbols implements Game {
    private static final String SYMBOL =
        "     +--,-+\n" +
        "     | ' \\\\|\n" +
        "     `.   ;\n" +
        "     | ;.'|\n" +
        "     ,'  `,\n" +
        "  _,-|`-.'|.._         _.-[\n" +
        ",:....-..:>...:-     ,/' /'\n" +
        "     |  | |        <'   ,'\n" +
        "     |  | |    ,Y''     | _.-'\n" +
        "     |  | | .,'        /'\\\n" +
        "     |  | | `.  ,Y''   /\n" +
        "     |  | |   \\'    ,'\n" +
        "     |  | |    \\\\ ,-'\n" +
        "     |  | |     -\n" +
        "     ,  | ,\n" +
        "      `.|/\n" +
        "        '\n";

    private static final String PENGUIN =
        "  .--.\n" +
        " |o_o |\n" +
        " |/_/ |\n" +
        "//   \\ \\\n" +
        "(|     | )\n" +
        "/'\\_   _/`\\\n" +
        "\\___)=(___/\n";

    private static final String BALL =
        "   __   \n" +
        "  /  \\  \n" +
        " (    ) \n" +
        "( ++++ )\n" +
        "(      )\n" +
        " (    ) \n" +
        "  \\  /  \n" +
        "   --\n";

    private static final String LLAMA =
        " /^---^\\ \n" +
        " | . . |\n" +
        " \\  `  /\n" +
        " /=====\\_________\n" +
        "/                \\[]\n" +
        "\\                /\n" +
        " ----------------\n" +
        " | | | |  | | | |\n" +
        " [|] [|]  [|] [|]\n";

    @Override
    public String getGameName() {
        return "ASCII Art";
    }

    @Override
    public void play() {
        System.out.println("\n=== ASCII Art Gallery ===\n");
        displaySymbols();
    }

    @Override
    public String getScore() {
        return "N/A";
    }

    @Override
    public void writeHighScore(File f) {
    }

    public static void displaySymbols() {
        Random rand = new Random();
        
        String[] arts = { SYMBOL, PENGUIN, BALL, LLAMA };

        for (int display = 0; display < 40; display++) {
            String art = arts[rand.nextInt(arts.length)];
            String[] lines = art.split("\n");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println();
            
            // Add delay to make animation visible
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
