// I used Ari Hallak's penguin, Dilan Shah's ball, and Maria Sek's llama ASCII art.
import java.util.Random;
public class MovingSymbols {
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
        "\\___)=(___/ AH\n";

    private static final String BALL =
        "   __   \n" +
        "  /  \\  \n" +
        " (    ) \n" +
        "( ++++ )\n" +
        "(      )\n" +
        " (    ) \n" +
        "  \\  /  \n" +
        "   -- DS\n";

    private static final String LLAMA =
        " /^---^\\            \n" +
        " | . . |            \n" +
        " \\  `  /    MS      \n" +
        " /=====\\_________   \n" +
        "/                \\[]\n" +
        "\\                /  \n" +
        " ----------------   \n" +
        " | | | |  | | | |   \n" +
        " [|] [|]  [|] [|]   \n";

    public static void main(String[] args) throws InterruptedException {
        final String esc = "\u001b[";
        final String clear = esc + "H" + esc + "2J";
        final int width = 80;
        final int height = 24;
        Random rand = new Random();

        AsciiArt symbolArt = new AsciiArt(SYMBOL);
        AsciiArt penguinArt = new AsciiArt(PENGUIN);
        AsciiArt ballArt = new AsciiArt(BALL);
        AsciiArt llamaArt = new AsciiArt(LLAMA);

        AsciiArt[] arts = new AsciiArt[] { symbolArt, penguinArt, ballArt, llamaArt };

        while (true) {
            AsciiArt art = arts[rand.nextInt(arts.length)];

            int x = rand.nextInt(Math.max(1, width - art.getWidth()));
            int y = rand.nextInt(Math.max(1, height - art.getHeight()));

            StringBuilder sb = new StringBuilder();
            sb.append(clear);
            for (int i = 0; i < y; i++) {
                sb.append("\n");
            }
            String[] lines = art.getArt().split("\n");
            for (String line : lines) {
                for (int j = 0; j < x; j++) {
                    sb.append(" ");
                }
                sb.append(line).append("\n");
            }

            System.out.print(sb.toString());
            Thread.sleep(500);
        }
    }
}
