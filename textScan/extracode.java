import java.awt.Color;

public class extracode {
    public static void main(String[] args) {
        if (white > black) picture.set(i, j, Color.WHITE);
        if (black > white) picture.set(i, j, Color.BLACK);
    }
}

    int widthEnd = 0;

for(int j=0;j<colinfo.length;j++){
        // first if statement returns characters that are black
        if(colinfo[j]){
        for(int k=j;k<colinfo.length;k++){
        if(colinfo[k])continue;
        if(!colinfo[j]){
        Picture character=new Picture(k-j,height);
        for(int m=0;m<k -j;m++){
        for(int n=0;n<height; n++){
        Color original=line.get(m+j,n);
        character.set(m,n,original);
        }
        }
        characters.enqueue(character);
        j+=(k-j);
        }
        break;
        }
        }
        }
        }
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


        if(!colinfo[j]){
        int count=0;
        for(int i=j;i<colinfo.length;j++){
        count++;
        if(colinfo[i]){
        if(count< 20){
        j+=i-j;
        break;
        }
        }
        if(!colinfo[i]){
        if(count< 20)continue;
        if(count>20){
        if(colinfo[i+1]){
        Picture a=new Picture(i-j,height);
        for(int m=0;m<a.width();m++){
        for(int k=0;k<a.height();k++){
        a.set(m,k,Color.WHITE);
        }
        }
        characters.enqueue(a);
        j+=i-j;
        break;
        }
        }
        }
        }
        }
        }
        }

        if(Black==true){
        int Height=character.height();
        int heightstart=0;
        int heightend=0;
        for(int i=0;i<Height; i++){
        if(ContainsBlackHorizontal(i,character)){
        heightstart=i;
        for(int j=character.height();j>0;j--){
        if(ContainsBlackHorizontal(j-1,character)){
        heightend=j;
        break;
        }
        }
        break;
        }
        }
        Picture processedCharacter=new Picture(character.width(),
        heightend-heightstart);
        for(int k=0;k<processedCharacter.width();k++){
        for(int m=0;m<processedCharacter.height();m++){
        Color original=character.get(k,m+heightstart);
        processedCharacter.set(k,m,original);
        }
        }
        characters.enqueue(processedCharacter);
        }
        else characters.enqueue(character);
        int startHeight=0;
        int endHeight=character.height();
        for(int i=0;i<High; i++){
        if(ContainsBlackHorizontal(i,character)){
        startHeight=i;
        for(int j=endHeight-1;j>=0;j--){
        if(ContainsBlackHorizontal(j,character)){
        endHeight=j;
        break;
        }
        else continue;
        }
        break;
        }
        else continue;
        }
        Picture finalCharacter=new Picture(width,endHeight-startHeight);
        for(int m=0;m<width; m++){
        for(int n=0;n<finalCharacter.height();n++){
        Color original=character.get(m,n+startHeight);
        finalCharacter.set(m,n,original);
        }
        }
        characters.enqueue(finalCharacter);


        Queue<Picture> characters=new Queue<Picture>();
        while(!precharacters.isEmpty()){
        Picture character=precharacters.dequeue();
        boolean Black=false;
        for(int p=0;p<character.height();p++){
        for(int q=0;q<character.width();q++){
        Color pixel=character.get(q,p);
        if(pixel==Color.BLACK)Black=true;
        }
        }
        if(Black){
        int Width=character.width();
        int High=character.height();
        for(int i=0;i<High; i++){
        if(ContainsBlackHorizontal(i,character)){
        int startHeight=i;
        int endHeight=0;
        for(int j=High-1;j>=0;j--){
        if(ContainsBlackHorizontal(j,character)){
        endHeight=j;
        break;
        }
        }
        Picture finalCharacter=new Picture(Width,endHeight-startHeight);
        for(int m=0;m<Width; m++){
        for(int n=0;n<finalCharacter.height();n++){
        Color original=character.get(m,n+startHeight);
        finalCharacter.set(m,n,original);
        }
        }
        characters.enqueue(finalCharacter);
        break;
        }
        }
        }
        else characters.enqueue(character);
        }
