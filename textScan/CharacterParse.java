import java.awt.Color;

public class CharacterParse {
    private Picture line;
    private int width;
    private int height;
    private boolean[] colinfo;

    public CharacterParse(Picture received) {
        line = received;
        width = received.width();
        height = received.height();
        colinfo = new boolean[received.width()];
        for (int i = 0; i < received.width(); i++) {
            colinfo[i] = LineParse.ContainsBlack(i, received);
        }
    }

    public static boolean containsBlackHorizontal(int rownumber, Picture b) {
        int width = b.width();
        for (int i = 0; i < width; i++) {
            if (b.get(i, rownumber).equals(Color.BLACK)) return true;
        }
        return false;
    }

    public static Picture crop(Picture character) {
        int wide = character.width();
        int high = character.height();
        int startheight = 0;
        int endheight = 0;
        for (int i = 0; i < high; i++) {
            if (containsBlackHorizontal(i, character)) {
                startheight = i;
                for (int j = high - 1; j >= 0; j--) {
                    if (containsBlackHorizontal(j, character)) {
                        endheight = j;
                        break;
                    }
                }
                break;
            }
        }
        Picture cropCharacter = new Picture(wide, endheight - startheight);
        for (int m = 0; m < cropCharacter.width(); m++) {
            for (int n = 0; n < cropCharacter.height(); n++) {
                Color original = character.get(m, n + startheight);
                cropCharacter.set(m, n, original);
            }
        }
        return cropCharacter;
    }

    public static boolean characterWithBlack(Picture character) {
        boolean black = false;
        int wide = character.width();
        int high = character.height();
        for (int i = 0; i < wide; i++) {
            for (int j = 0; j < high; j++) {
                if (character.get(i, j).equals(Color.BLACK)) black = true;
            }
        }
        return black;
    }

    public Queue<Picture> characters() {
        Queue<Picture> precharacters = new Queue<Picture>();
        for (int i = 0; i < width; i++) {
            // Character detected - THIS WORKS (GIVES THE PROPER NUMBER OF CHARACTERS)
            if (colinfo[i]) {
                for (int j = i; j < width; j++) {
                    if (colinfo[j]) continue;
                    // Character end detected
                    if (!colinfo[j]) {
                        Picture character = new Picture(j - i, height);
                        for (int m = 0; m < character.height(); m++) {
                            for (int n = 0; n < character.width(); n++) {
                                Color original = line.get(n + i, m);
                                character.set(n, m, original);
                            }
                        }
                        precharacters.enqueue(character);
                        i += (j - i);
                        break;
                    }
                }
            }
            // Space detected - THIS WORKS (GIVES THE PROPER NUMBER OF SPACES)
            if (!colinfo[i]) {
                int count = 0;
                for (int j = i; j < width; j++) {
                    // Gives space between characters
                    if ((count < 20) && (colinfo[j + 1])) {
                        i += (j - i);
                        break;
                    }
                    // Spaces between words (inputs it as a picture in character queue)
                    if ((count >= 20) && (colinfo[j + 1] == false)) {
                        continue;
                    }
                    if ((count >= 20) && (colinfo[j + 1] == true)) {
                        Picture space = new Picture(j - i, height);
                        for (int m = 0; m < space.height(); m++) {
                            for (int k = 0; k < space.width(); k++) {
                                space.set(k, m, Color.WHITE);
                            }
                        }
                        precharacters.enqueue(space);
                        i += (j - i);
                        break;
                    }
                    count++;
                }
            }
        }
        // Crops the character so that it is entirely cropped to its borders
        Queue<Picture> characters = new Queue<Picture>();
        while (!precharacters.isEmpty()) {
            Picture character = precharacters.dequeue();
            boolean containsBlack = CharacterParse.characterWithBlack(character);
            if (containsBlack) {
                Picture croppedCharacter = CharacterParse.crop(character);
                characters.enqueue(croppedCharacter);
            }
            else characters.enqueue(character);
        }
        return characters;
    }

    public static double[] extractFeatures(Picture picture) {
        int width = picture.width();
        int height = picture.height();
        int totalPixels =  width * height;
        double[] pixels = new double[totalPixels];
        int currentPos = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[currentPos] = picture.get(x, y).getRed();
                currentPos++;
            }
        }

        return pixels;
    }
    public static void main(String[] args) {
        Picture AndrewSuj = new Picture(args[1]);
        Pic AndrewSujay = new Pic(AndrewSuj);
        Picture ContrastAndrewSujay = AndrewSujay.Contrast();
        // ContrastAndrewSujay.save("AndrewSujayContrast.jpg");
        LineParse AndrewSujayParse = new LineParse(ContrastAndrewSujay);
        Queue<Picture> lines = AndrewSujayParse.preSeparateLines();
        Queue<Picture> processedLines = LineParse.SeparateLines(lines);
        Picture line1 = processedLines.dequeue();
        CharacterParse charactersdata = new CharacterParse(line1);
        Queue<Picture> characters = charactersdata.characters();
        StdOut.println(characters.length());
        /*
        int count = 0;
        while (!characters.isEmpty()) {
            Picture character = characters.dequeue();
            character.save("character" + count + ".jpg");
            count++;
        }
         */

        In trainingFile = new In(args[0]);

        int classNumber = trainingFile.readInt();
        int width = trainingFile.readInt();
        int height = trainingFile.readInt();
        MultiPerceptron classer = new MultiPerceptron(classNumber, width*height);

        while (!trainingFile.isEmpty()) {
            String fileName = trainingFile.readString();
            Picture file = new Picture(fileName);
            int label = trainingFile.readInt();
            double[] fileProcessed = extractFeatures(file);
            classer.trainMulti(fileProcessed, label);
        }

        int classNumberTest = testingFile.readInt();
        int widthTest = testingFile.readInt();
        int heightTest = testingFile.readInt();
        int failedTests = 0;
        int totalTests = 0;

        while (!testingFile.isEmpty()) {
            totalTests++;
            String fileName = testingFile.readString();
            Picture file = new Picture(fileName);
            int label = testingFile.readInt();
            double[] fileProcessed = extractFeatures(file);
            int outputLabel = classer.predictMulti(fileProcessed);
            if (outputLabel != label) {
                failedTests++;
                System.out.println(fileName + ", label = " +
                                           label + ", predict = " + outputLabel);
            }
        }
        double errorRate = (double) failedTests/totalTests;
        System.out.println("test error rate = " + errorRate);
    }
}
