import java.util.LinkedList;

public class Body {
    public LinkedList<Integer> x = new LinkedList<>();
    public LinkedList<Integer> y = new LinkedList<>();

    GameFrame gameFrame;

    int deadX = 0;
    int deadY = 0;

    public Body(GameFrame gameFrame){

        this.gameFrame = gameFrame;

        x.addLast(Math.round(gameFrame.frameXLimit / 2));
        y.addLast(Math.round(gameFrame.frameYLimit / 2));

        int len = gameFrame.initialLength-1;

        while (len-- != 0)
            addHead(gameFrame.initialDirection);

    }

    boolean isAliveee() {
//        System.out.println("checking if alive"); //DEBUG

/***********ENSURING NO BODY PART GOES OUT OF FRAME************/
        for (int i = 0; i < x.size(); i++)
            x.set(i, (x.get(i) + gameFrame.frameXLimit) % gameFrame.frameXLimit);

        for (int i = 0; i < y.size(); i++)
            y.set(i, (y.get(i) + gameFrame.frameYLimit) % gameFrame.frameYLimit);


/*************************CHECKING FOR OVERLAP*****************************/
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < y.size(); j++) {
                if (i != j && x.get(i).intValue() == x.get(j).intValue() && y.get(i).intValue() == y.get(j).intValue()) {
//                    System.out.println(i + " - " + j);
                    return false;
                }
            }
        }
        return true;

    }

    public void removeTail() {
//        System.out.println("removing tail"); //DEBUG
        deadX = x.getLast();
        deadY = y.getLast();
        if (x.size() != 0)
            x.removeLast();
        if (y.size() != 0)
            y.removeLast();
    }

    public void addTail() {
//        System.out.println("adding tail"); //DEBUG
        x.addLast(deadX);
        y.addLast(deadY);
    }


    public void addHead(Character direction) {
//        System.out.println("adding head"); //DEBUG
        int x = this.x.getFirst();
        int y = this.y.getFirst();

        if(direction == gameFrame.up)
            y-=10;
        else if(direction == gameFrame.down)
            y+=10;
        else if(direction == gameFrame.left)
            x-=10;
        else if(direction == gameFrame.right)
            x+=10;

        x = (x + gameFrame.frameXLimit) % gameFrame.frameXLimit;
        y = (y + gameFrame.frameYLimit) % gameFrame.frameYLimit;
//        System.out.println("putting module at ( " + x + ", " + y + ")"); //DEBUG
        this.x.addFirst(x);
        this.y.addFirst(y);
    }
}
