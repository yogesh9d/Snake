public class BodyModule {
    public int x;
    public int y;

    public BodyModule(){

    }

    public BodyModule(int x, int y){
        this.x = x;
        this.y = y;
    }
    public  BodyModule getModule(){
        return this;
    }

    public int getX(){
        return  x;
    }

    public int getY(){
        return y;
    }
}
