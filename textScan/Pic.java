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
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = b.get(i, j);
                int green = color.getGreen();
                int blue = color.getBlue();
                int red = color.getRed();
                double prewhite = Math.max(green, blue);
                double white = Math.max(red, prewhite);
                double black = 1.0 - white;
                if (white > black) picture.set

            }
        }
    }

    ;


    public static void main(String[] args) {

    }
}
