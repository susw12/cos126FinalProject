public class TextLine {
    private Picture image;
    private int width;
    private int height;
    private boolean[] colInfo;

    public TextLine(Picture image) {
        this.image = image;
        setWidth(this.image.width());
        setHeight(this.image.height());
        colInfo = new boolean[getWidth()];
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

    public boolean[] colFill() {
        for (int i = 0; i < this.width; i++) {
            colInfo[i] = LineParse.ContainsBlack(i, image);
        }
        return colInfo;
    }

    public Picture getLetter(int start, int end) {
        Picture letter = image.
    }
}
