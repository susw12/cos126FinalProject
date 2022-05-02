public class MultiPerceptron {
    private int m; // # of perceptrons/classes
    private int n; // #length of input feature vector
    private Perceptron[] perceptrons; // array of Perceptron objects

    // Creates multi-perceptron object with m classes/n inputs. Creates an array of m
    // perceptrons each with n inputs initialized to 0.
    public MultiPerceptron(int a, int b) {
        m = a;
        n = b;
        perceptrons = new Perceptron[m];
        for (int i = 0; i < m; i++) {
            perceptrons[i] = new Perceptron(b);
        }
    }

    // Returns number of classes m
    public int numberOfClasses() {
        return m;
    }

    // Returns number of inputs n (length of the feature vector)
    public int numberOfInputs() {
        return n;
    }

    // Returns predicted label (between 0 and m-1) for given input
    public int predictMulti(double[] x) {
        double greatestSum = perceptrons[0].weightedSum(x);
        int predictedClass = 0;
        for (int i = 1; i < m; i++) {
            double sum = perceptrons[i].weightedSum(x);
            if (sum > greatestSum) {
                greatestSum = sum;
                predictedClass = i;
            }
        }
        return predictedClass;
    }

    // Trains multi-perceptron on the labeled (between 0 and m-1) input and updates
    // weight arrays accordingly
    public void trainMulti(double[] x, int label) {
        for (int i = 0; i < m; i++) {
            if (i == label) {
                perceptrons[i].train(x, 1);
            }
            if (i != label) {
                perceptrons[i].train(x, -1);
            }
        }
    }

    // Returns string representation of MultiPerceptron by recalling Perceptron.java
    // methods.
    public String toString() {
        String weightPerceptrons = "(";
        for (int i = 0; i < m; i++) {
            if (i == (m - 1)) {
                weightPerceptrons = weightPerceptrons +
                        perceptrons[i].toString() + ")";
                break;
            }
            weightPerceptrons = weightPerceptrons + perceptrons[i].toString() + ", ";
        }
        return weightPerceptrons;
    }

    // Tests class by calling all instance methods.
    public static void main(String[] args) {
        int m = 2;
        int n = 3;

        double[] training1 = { 3.0, 4.0, 5.0 };  // class 1
        double[] training2 = { 2.0, 0.0, -2.0 };  // class 0
        double[] training3 = { -2.0, 0.0, 2.0 };  // class 1
        double[] training4 = { 5.0, 4.0, 3.0 };  // class 0

        MultiPerceptron perceptron = new MultiPerceptron(m, n);
        StdOut.println(perceptron.numberOfClasses());
        StdOut.println(perceptron.numberOfInputs());
        StdOut.println(perceptron);
        perceptron.trainMulti(training1, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training2, 0);
        StdOut.println(perceptron);
        perceptron.trainMulti(training3, 1);
        StdOut.println(perceptron);
        perceptron.trainMulti(training4, 0);
        StdOut.println(perceptron);
        double[] testing1 = { -1.0, -2.0, 3.0 };
        double[] testing2 = { 2.0, -5.0, 1.0 };

        StdOut.println(perceptron.predictMulti(testing1));
        StdOut.println(perceptron);
        StdOut.println(perceptron.predictMulti(testing2));
        StdOut.println(perceptron);
    }
}

