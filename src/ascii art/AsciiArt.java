public class AsciiArt {
    private String art;
    private int height;
    private int width;

    public AsciiArt(String art) {
        this.art = art;
        processArt();
    }

    public AsciiArt(AsciiArt original) {
        this.art = original.art;
        this.height = original.height;
        this.width = original.width;
    }

    private void processArt() {
        String[] lines = art.split("\n");
        this.height = lines.length;
        this.width = 0;
        for (String line : lines) {
            if (line.length() > this.width) {
                this.width = line.length();
            }
        }
    }

    public void resize(int width2) {
        String[] lines = art.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            while (line.length() < width2) {
                line += " ";
            }
            sb.append(line.substring(0, width2)).append("\n");
        }
        this.art = sb.toString();
        this.width = width2;
    }

    public String getArt() {
        return this.art;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public AsciiArt switchRowsAndCols() {
        String[] lines = art.split("\n");
        char[][] matrix = new char[height][width];
        for (int i = 0; i < height; i++) {
            String line = lines[i];
            for (int j = 0; j < width; j++) {
                matrix[i][j] = (j < line.length()) ? line.charAt(j) : ' ';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < width; j++) {
            for (int i = 0; i < height; i++) {
                sb.append(matrix[i][j]);
            }
            sb.append("\n");
        }
        return new AsciiArt(sb.toString());
    }

    static AsciiArt getWidestArt(AsciiArt[] asciis) {
        if (asciis.length == 0) return null;
        AsciiArt widest = asciis[0];
        for (AsciiArt art : asciis) {
            if (art.width > widest.width) widest = art;
        }
        return widest;
    }

    public void printArt() {
        System.out.print(this.art);
    }
}
