import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

abstract class AnimatedThing {

    protected double x;
    protected double y;
    protected ImageView imageView;
    protected int attitude=0; //valeur de offset Y
    protected int index=0;
    protected int duration;
    protected int indexMax;
    protected int sizeX;
    protected int sizeY;
    protected int offset;
    protected double gravity;
    protected int limitY;
    protected Pane pane;

    public ImageView getImageView() {
        return imageView;
    }

    public AnimatedThing(String fileName, int indexMax, int duration, int sizeX, int sizeY, int offset, Pane pane) {
        this.imageView=new ImageView(fileName);
        this.indexMax=indexMax;
        this.duration=duration;
        this.offset=offset;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.pane=pane;
        pane.getChildren().add(this.imageView);


    }

    public void update(long time){
            imageView.setViewport(new Rectangle2D(index*(offset+sizeX),attitude,sizeX,sizeY));
            setY(y+gravity);
            if(y>=limitY){
                setY(limitY);
            }
            imageView.setY(y);
            index++;
            if (index>indexMax){
                index=0;
                if (attitude!=0){
                    attitude=0;
                }
            }


    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getAttitude() {
        return attitude;
    }

    public int getIndex() {
        return index;
    }

    public int getDuration() {
        return duration;
    }

    public int getIndexMax() {
        return indexMax;
    }

    public void setAttitude(int attitude, int indexMax) {
        this.attitude = attitude;
        this.indexMax=indexMax;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Pane getPane() {
        return pane;
    }

    public int getOffset() {
        return offset;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setSizeX(int sizeX){
        this.sizeX=sizeX;
    }
}
