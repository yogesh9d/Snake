import java.util.LinkedList;

public class Body {
    public LinkedList<Integer> x = new LinkedList<>();
    public LinkedList<Integer> y = new LinkedList<>();

    boolean gameOver = false;

    int x_limit = 100;
    int y_limit = 100;

    int clearX = 0;
    int clearY = 0;

    boolean alive = true;

    public Body(int length, int x_limit, int y_limit) {
        this.x_limit = x_limit;
        this.y_limit = y_limit;

        int baseX = Math.round(x_limit / 2);
        int baseY = Math.round(y_limit / 2);

        x.addLast(baseX);
        y.addLast(baseY);
        length--;

        while (length-- != 0) {
            addHead('d');
        }
    }

    boolean isAliveee() {
        System.out.println("checking if allive");
        for (int i = 0; i < x.size(); i++)
            x.set(i, (x.get(i) + x_limit) % x_limit);

        for (int i = 0; i < y.size(); i++)
            y.set(i, (y.get(i) + y_limit) % y_limit);

        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < y.size(); j++) {
                if (i != j && x.get(i).intValue() == x.get(j).intValue() && y.get(i).intValue() == y.get(j).intValue()) {
                    alive = false;
                    break;
                }
            }
        }
        return alive;
    }

    public void RemoveTail() {
        System.out.println("removing tail");
        clearX = x.getLast();
        clearY = y.getLast();
        if (x.size() != 0)
            x.removeLast();
        if (y.size() != 0)
            y.removeLast();
    }

    public void addHead(Character direction) {
        System.out.println("adding head");
        Integer x = this.x.getFirst();
        Integer y = this.y.getFirst();
        switch (direction) {
            case 'w': {
                y -= 10;
                break;
            }
            case 'a': {
                x -= 10;
                break;
            }
            case 's': {
                y += 10;
                break;
            }
            case 'd': {
                x += 10;
                break;
            }
        }
        System.out.println("putting module at ( " + x + ", " + y + ")");
        this.x.addFirst(x);
        this.y.addFirst(y);
    }

//    public void addTail(Character direction) {
//        Integer x = this.x.getLast();
//        Integer y = this.y.getLast();
//        switch (direction) {
//            case 'w': {
//                y-=10;
//                break;
//            }
//            case 'a': {
//                x-=10;
//                break;
//            }
//            case 's': {
//                y+=10;
//                break;
//            }
//            case 'd': {
//                x+=10;
//                break;
//            }
//        }
//        this.x.addLast(x);
//        this.y.addLast(y);
//    }

}
