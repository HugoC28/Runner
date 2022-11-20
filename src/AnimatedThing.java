import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

abstract class AnimatedThing {

    protected double x;
    protected double y;
    private ImageView imageView;
    private int attitude=0; //valeur de offset Y
    private int index=0;
    private int duration;
    private int indexMax;
    private int sizeX;
    private int sizeY;
    private int offset;
    protected double gravity;
    protected int limitY;

    public ImageView getImageView() {
        return imageView;
    }

    public AnimatedThing(String fileName, int indexMax, int duration, int sizeX, int sizeY, int offset) {
        this.imageView=new ImageView(fileName);
        this.indexMax=indexMax;
        this.duration=duration;
        this.offset=offset;
        this.sizeX=sizeX;
        this.sizeY=sizeY;


    }

    public void update(long time){
            imageView.setViewport(new Rectangle2D(index*(offset+sizeX),attitude,sizeX,sizeY));
            setY(y+gravity);
            System.out.println(y);
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
        this.index=0;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getOffset() {
        return offset;
    }

    public void setY(double y) {
        this.y = y;
    }
}
