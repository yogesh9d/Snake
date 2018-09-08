import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Snake extends Body implements Runnable, KeyListener {
    volatile Character direction = new Character('d');

    Thread theFrame;

    volatile int length = 5; // DEFAULT LENGTH
    volatile int frame_x_limit = 700; // DEFAULT FRAME SIZE
    volatile int frame_y_limit = 700; // DEFAULT FRAME SIZE

    volatile boolean keyPressedInSnake = false;
    volatile boolean frameChance = true;
    volatile boolean snakeChance = false;
    volatile boolean moveMadeByPlayer = true;

    public Snake(int length, int frame_x_limit, int frame_y_limit) {

        super(length, frame_x_limit, frame_y_limit);
        this.length = length;
        this.frame_x_limit = frame_x_limit;
        this.frame_y_limit = frame_y_limit;

    }

    public void setDirection(Character p) {
        System.out.println("setting direction");
        if (direction == 'w' && p != 's')
            direction = p;
        else if (direction == 's' && p != 'w')
            direction = p;
        else if (direction == 'a' && p != 'd')
            direction = p;
        else if (direction == 'd' && p != 'a')
            direction = p;
    }

    public void moveSnake() {
        System.out.println("moving snake");
        addHead(direction);
        RemoveTail();
    }

    public void increase(int length) {
        System.out.println("increasing length");
        addHead(direction);
    }

    @Override
    public void run() {
        while (!gameOver) {
//            System.out.println("snake is running");
            if (theFrame.getState() != Thread.State.TIMED_WAITING)
                Thread.yield();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("snake chance now inn snake");
        if (snakeChance && !keyPressedInSnake && !moveMadeByPlayer) {
            keyPressedInSnake = true;
            System.out.println("key pressed in snake" + e.getKeyChar());
            if (e.getKeyChar() != direction)
                setDirection(e.getKeyChar());
            if (snakeChance && keyPressedInSnake) {
                System.out.println("moving by player's will - " + direction);
                moveSnake();
                snakeChance = false;
                frameChance = true;
                keyPressedInSnake = true;
                moveMadeByPlayer = true;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
