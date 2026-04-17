public class StillLife {
    private static final String SIGN = "- EZ";
    public static void main(String[] args) throws Exception {
        boolean loop = args.length > 0 ? Boolean.parseBoolean(args[0]) : false;
        String[] art = getArtLines();
        do {
            printArt(art);
            printSignature();
            if (loop) {
                if (!sleep(2000)) return;
            }
        } while (loop);
    }
    public static String[] getArtLines() {
        return new String[] {
            "      ,.... ,-",
            "     ` ...P",
            "    --.--+-+--.",
            "   /   `+-'    `.",
            "  /              :",
            " -+               |",
            "  :               |",
            "   \\              ;",
            "    \\            /",
            "     `----------'"
        };
    }
    public static void printArt(String[] lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }
    public static void printSignature() {
        System.out.println("\n" + SIGN);
    }
    public static boolean sleep(int ms) {
        try { Thread.sleep(ms); return true; } catch (InterruptedException e) { Thread.currentThread().interrupt(); return false; }
    }
}
