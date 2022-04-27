import java.awt.Color;

public class extracode {
    public static void main(String[] args) {
        if (white > black) picture.set(i, j, Color.WHITE);
        if (black > white) picture.set(i, j, Color.BLACK);
    }
}
    int widthEnd = 0;
                    for(int j=line.width()-1;j>=0;j--){
                            if(ContainsBlack(j,line)){
                            widthEnd=j;
                            break;
                            }
                            }
                            Picture processedLine=new Picture(widthEnd-widthStart,line.height());
                            for(int k=0;k<processedLine.width();k++){
        for(int m=0;m<processedLine.height();m++){
        Color original=line.get(i,m);
        processedLine.set(k,m,original);
        }
        }
        lines.enqueue(processedLine);
