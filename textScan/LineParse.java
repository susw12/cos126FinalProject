import java.awt.Color;

public class LineParse {
    private int width; // width of original text picture
    private int height; // height of original text picture
    private Picture fullText; // image of full contrasted text

    public LineParse(Picture a) {
        fullText = a;
        height = a.height();
        width = a.width();
    }

    // Sees if a row in the original picture has black
    public boolean ContainsBlack(int b) {
        for (int i = 0; i < width; i++) {
            if (fullText.get(i, b).equals(Color.BLACK)) {
                return true;
            }
        }
        return false;
    }

    // Sees if a column in the pre-processed picture has black
    public static boolean ContainsBlack(int columnnumber, Picture b) {
        int height = b.height();
        for (int i = 0; i < height; i++) {
            if (b.get(columnnumber, i).equals(Color.BLACK)) {
                return true;
            }
        }
        return false;
    }

    // Recognizes the top and bottom of each line and crops the original picture vertically
    public Queue<Picture> preSeparateLines() {
        Queue<Picture> Lines = new Queue<Picture>();
        for (int i = 0; i < height; i++) {
            if (ContainsBlack(i)) {
                int lineStart = i;
                int lineEnd = 0;
                for (int j = i; j < height; j++) {
                    if (!ContainsBlack(j)) {
                        lineEnd = j - 1;
                        break;
                    }
                }
                Picture line = new Picture(width, (lineEnd - lineStart));
                for (int k = 0; k < line.height(); k++) {
                    for (int m = 0; m < line.width(); m++) {
                        Color original = fullText.get(m, k + i);
                        line.set(m, k, original);
                    }
                }
                Lines.enqueue(line);
                i += (lineEnd - lineStart);
            }
        }
        return Lines;
    }

    public static Queue<Picture> SeparateLines(Queue<Picture> a) {
        Queue<Picture> lines = new Queue<Picture>();
        while (!a.isEmpty()) {
            Picture line = a.dequeue();
            int width = line.width();
            int widthstart = 0;
            int widthend = 0;
            for (int i = 0; i < width; i++) {
                if (ContainsBlack(i, line)) {
                    widthstart = i;
                    for (int j = line.width() - 1; j >= 0; j--) {
                        if (ContainsBlack(j, line)) {
                            widthend = j;
                            break;
                        }
                    }
                    break;
                }
            }
            Picture processedLine = new Picture(widthend - widthstart, line.height());
            for (int k = 0; k < processedLine.height(); k++) {
                for (int m = 0; m < processedLine.width(); m++) {
                    Color original = line.get(m + widthstart, k);
                    processedLine.set(m, k, original);
                }
            }
            lines.enqueue(processedLine);
        }
        return lines;
    }


    public static void main(String[] args) {
        Picture AndrewSuj = new Picture("AndrewSujay.jpg");
        Pic AndrewSujay = new Pic(AndrewSuj);
        Picture ContrastAndrewSujay = AndrewSujay.Contrast();
        ContrastAndrewSujay.save("AndrewSujayContrast.jpg");
        LineParse AndrewSujayParse = new LineParse(ContrastAndrewSujay);
        Queue<Picture> lines = AndrewSujayParse.preSeparateLines();
        Queue<Picture> processedLines = LineParse.SeparateLines(lines);
        StdOut.print(processedLines.length());
        Picture a = processedLines.dequeue();
        a.save("AndrewSujayContrastLine1.jpg");
    }
}




