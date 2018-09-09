import java.util.LinkedList;

public class Body {
    public LinkedList<Integer> x = new LinkedList<>();
    public LinkedList<Integer> y = new LinkedList<>();

    boolean gameOver = false;

    int frameXLimit;
    int frameYLimit;

    int clearX = 0;
    int clearY = 0;

    public Body(int length, int frameXLimit, int frameYLimit) {
        this.frameXLimit = frameXLimit;
        this.frameYLimit = frameYLimit;

        int baseX = Math.round(frameXLimit / 2);
        int baseY = Math.round(frameYLimit / 2);

        x.addLast(baseX);
        y.addLast(baseY);
        length--;

        while (length-- != 0) {
            addHead('d');
        }
    }

    boolean isAliveee() {
//        System.out.println("checking if alive"); //DEBUG
        for (int i = 0; i < x.size(); i++)
            x.set(i, (x.get(i) + frameXLimit) % frameXLimit);

        for (int i = 0; i < y.size(); i++)
            y.set(i, (y.get(i) + frameYLimit) % frameYLimit);

        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < y.size(); j++) {
                if (i != j && x.get(i).intValue() == x.get(j).intValue() && y.get(i).intValue() == y.get(j).intValue()) {
//                    System.out.println(i + " - " +j);
                    return false;
                }
            }
        }
        return true;
    }

    public void removeTail() {
//        System.out.println("removing tail"); //DEBUG
        clearX = x.getLast();
        clearY = y.getLast();
        if (x.size() != 0)
            x.removeLast();
        if (y.size() != 0)
            y.removeLast();
    }

    public void addTail() {
//        System.out.println("adding tail"); //DEBUG
        x.addLast(clearX);
        y.addLast(clearY);
    }


    public void addHead(Character direction) {
//        System.out.println("adding head"); //DEBUG
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
//        System.out.println("putting module at ( " + x + ", " + y + ")"); //DEBUG
        this.x.addFirst(x);
        this.y.addFirst(y);
    }
}
