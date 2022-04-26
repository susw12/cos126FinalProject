import java.awt.Color;

public class Pic {
    private Picture b; // creates instance variable picture

    // initializes instance variable b to equal picture a (input picture)
    private Pic(Picture a) {
        b = a;
    }

    // highlights contrast of the picture
    public Picture Contrast() {
        int height = b.height();
        int width = b.width();
        Picture picture = new Picture(width, height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Color color = b.get(i, j);
                int green = color.getGreen();
                int blue = color.getBlue();
                int red = color.getRed();
                double prewhite = Math.max(green / 255.0, blue / 255.0);
                double white = Math.max(red / 255.0, prewhite);
                double black = 1.0 - white;
                if (black < white) picture.set(i, j, Color.WHITE);
                if (black > white) picture.set(i, j, Color.BLACK);
            }
        }
        return picture;
    }

    public static void main(String[] args) {
        Picture test = new Picture("Test2.jpg");
        int height = test.height();
        int width = test.width();
        StdOut.print(height + " " + width);
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(-width / 2, width / 2);
        StdDraw.setYscale(-height / 2, height / 2);
        StdDraw.picture(0, 0, "Test2.jpg");
        StdDraw.show();
        Pic transform = new Pic(test);
        Picture transformed = transform.Contrast();
        transformed.save("Transformed.jpg");
        StdDraw.picture(0, 0, "Transformed.jpg");
    }
}
