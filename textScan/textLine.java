public class textLine {
    private Picture image;
    private int width;
    private int height;
    private String[][] words;

    public textLine(String image) {
        this.image = new Picture(image);
        parse(this.image);


    }

    private static void parse(Picture image) {
        int widthOrig = image.width();
        int heightOrig = image.width();
        int widthFinal = 0;
        int heigthFinal = 0;

        for (int h = 0; h < heightOrig; h++) {
            for (int w = 0; w < widthOrig; w++) {

            }
        }

    }
    public static void main(String[] args) {

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
