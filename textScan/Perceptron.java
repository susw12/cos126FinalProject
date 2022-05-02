public class Perceptron {
    private int n; // int n represents # of inputs
    private double[] weights; // array of weights calculated from inputs

    // Creates perceptron of n inputs. Also creates an array of n weights/initializes
    // them to 0.
    public Perceptron(int m) {
        n = m;
        weights = new double[n];
    }

    // Returns number of inputs n
    public int numberOfInputs() {
        return n;
    }

    // Returns weighted sum derived from weight vector and x array
    public double weightedSum(double[] x) {
        double weightedSum = 0;
        for (int i = 0; i < n; i++) {
            double weight = weights[i] * x[i];
            weightedSum += weight;
        }
        return weightedSum;
    }

    // Predicts label (+1 or -1) of input x. Returns +1 if weighted sum > 0 and -1 if
    // weighted sum <= 0.
    public int predict(double[] x) {
        double prePrediction = weightedSum(x);
        int prediction = 0;
        if (prePrediction <= 0) prediction = -1;
        if (prePrediction > 0) prediction = 1;
        return prediction;
    }

    // Trains perceptron on labeled (+1 or -1) input of x
    // Weight vectors updated accordingly.
    public void train(double[] x, int label) {
        int prediction = predict(x);
        if (prediction > label) {
            for (int i = 0; i < n; i++) {
                weights[i] = weights[i] - x[i];
            }
        }
        if (prediction < label) {
            for (int j = 0; j < n; j++) {
                weights[j] = weights[j] + x[j];
            }
        }
    }


    // Returns representation of weight vector by appending weight array values
    // sequentially to a string variable.
    public String toString() {
        String weightValues = "(";
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                weightValues = weightValues + weights[i] + ")";
                break;
            }
            weightValues = weightValues + weights[i] + ", ";
        }
        return weightValues;
    }

    // Presents a testing case that calls all instance methods.
    public static void main(String[] args) {
        Perceptron a = new Perceptron(3);
        StdOut.println(a);
        StdOut.println(a.numberOfInputs());
        double[] b = { 2, 3, 4 };
        StdOut.println(a.weightedSum(b));
        StdOut.println(a.predict(b));
        a.train(b, 1);
        StdOut.println(a);
    }
}
