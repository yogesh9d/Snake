import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends Applet implements MouseListener, MouseMotionListener, KeyListener, Runnable {
    int frame_x_limit = 700;
    int frame_y_limit = 500;

    boolean firstFrame = true;

    volatile boolean foodEaten = false;

    Snake snake;
    Thread frameThread;
    Thread snakeThread;

    float fps = (float) 2;
    int fpsSleepTime = Math.round(1000 / fps);

    @Override
    public void run() {
        snakeThread.start();
        while (!snake.gameOver) {
            System.out.println("frame is running");
            if (!snake.moveMadeByPlayer){
                snake.moveSnake();
                snake.frameChance = true;
            }

            if (snake.frameChance){
                repaint();
//            if (snakeThread.getState() != Thread.State.TIMED_WAITING && snake.snakeChance)
                try {
                    Thread.sleep(fpsSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void paint(Graphics g) {

        System.out.println("painting frame");
        g.setColor(Color.black);
        Graphics2D G = (Graphics2D) g;

        g.drawRect(1, 1, frame_x_limit, frame_y_limit);

        if (snake.isAliveee()) {
            for (int i = 0; i < snake.x.size(); i++)
                G.fillOval(snake.x.get(i), snake.y.get(i), 10, 10);
        } else {
            snake.gameOver = true;
            Font temp = new Font(Font.MONOSPACED, Font.BOLD, 40);
            g.setFont(temp);
            g.setColor(Color.green);
            g.drawString("GAME OVER", Math.round(frame_x_limit / 2) - 120, Math.round(frame_y_limit / 2));
        }

        snake.frameChance = false;
        snake.snakeChance = true;
        snake.moveMadeByPlayer = false;
        snake.keyPressedInSnake = false;

        System.out.println("snake's chance");
    }

    @Override
    public void init() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        addKeyListener(snake);

        this.setSize(new Dimension(frame_x_limit, frame_y_limit));

        snake = new Snake(10, frame_x_limit, frame_y_limit);

        frameThread = new Thread(this);
        snake.theFrame = frameThread;

        snakeThread = new Thread(snake);

        setBackground(Color.gray);
        setForeground(Color.black);
    }

    @Override
    public void start() {
        frameThread.start();
    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("snake chance now inn frame");
        if (snake.snakeChance && !snake.keyPressedInSnake && !snake.moveMadeByPlayer) {
            snake.keyPressedInSnake = true;
            System.out.println("key pressed in frame" + e.getKeyChar());
            snake.setDirection(e.getKeyChar());
            if (snake.snakeChance && snake.keyPressedInSnake) {
                System.out.println("moving by player's will - " + snake.direction);
                snake.moveSnake();
                snake.snakeChance = false;
                snake.frameChance = true;
                snake.keyPressedInSnake = true;
                snake.moveMadeByPlayer = true;
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
