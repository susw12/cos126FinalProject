public class ImageClassifier {

    // Creates a feature vector (1D array) from the given picture.
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

    // Takes a training file and a testing files and determines
    // the error rate of training. Then displays error rate and
    // shows which digits were classified incorrectly.
    public static void main(String[] args) {
        In trainingFile = new In(args[0]);
        In testingFile = new In(args[1]);

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
            int outLabel = classer.predictMulti(fileProcessed);
            if (outLabel != label) {
                failedTests++;
                System.out.println(fileName + ", label = " +
                                           label + ", predict = " + outputLabel);
            }
        }
        double errorRate = (double) failedTests/totalTests;
        System.out.println("test error rate = " + errorRate);
    }
}
