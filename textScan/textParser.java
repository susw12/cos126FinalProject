import java.util.Arrays;

public class textParser {
    public static void main(String[] args) {
        Picture andrewSuj = new Picture("transformedAndrewSuj.jpg");
        LineParse andrewSujParse = new LineParse(andrewSuj);
        Queue<Picture> lines = andrewSujParse.preSeparateLines();
        Queue<Picture> processedLines = LineParse.SeparateLines(lines);

        /*
        Picture a = processedLines.dequeue();
        a.save("processedLine1.jpg");
        Picture b = processedLines.dequeue();
        b.save("processedLine2.jpg");
        Picture c = processedLines.dequeue();
        c.save("processedLine3.jpg");
        */

        int lineNumber = processedLines.length();
        for (int i = 0; i < lineNumber; i++) {
            TextLine inputLine = new TextLine(processedLines.dequeue());
            boolean[] letters = inputLine.colFill();
            // System.out.println(Arrays.toString(inputLine.colFill()));
            boolean letterStatus = true;
            int start = 0;
            int end = 0;
            for (int c = 0; c < letters.length; c++) {
                if (letterStatus) {
                    if (!letters[c]) {
                        end = c - 1;

                    }
                }

            }

        }

    }
}
