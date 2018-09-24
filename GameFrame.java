import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/*<applet code="GameFrame.class" width="710" height="510"> </applet>*/

@SuppressWarnings({"deprecation", "serial"})

public class GameFrame extends Applet implements MouseListener, MouseMotionListener, KeyListener, Runnable {

    boolean chance = true;
    boolean isFirstFrame = true;
    boolean gameOver = false;

    int frameXLimit = 700;
    int frameYLimit = 500;

    /**************SAFE MODIFY****************/
    float fps = (float) 10;  // DEFAULT GAME SPEED
    int initialLength = 3;  // DEFAULT INITIAL SNAKE LENGTH

//    DEFAULT COLORS
    Color headColor = Color.blue;
    Color bodyColor = Color.black;
    Color scoreColor = Color.white;
    Color backGroundColor = Color.gray;
    Color borderColor = Color.black;
    Color textColor = Color.green;
    Color foodColor = Color.green;

//    DEFAULT CONTROLS
    char up = 'w';
    char down = 's';
    char left = 'a';
    char right = 'd';

//    DEFAULT DIRECTION
    char initialDirection = right;

    /*****************************************/
    Snake snake;
    Food food;
    Thread theFrameThread;

    int fpsSleepTime = Math.round(1000 / fps);

    @Override
    public void run() {
//            System.out.println("frame is running"); //DEBUG

        while (!gameOver) {
//            System.out.println("game running"); //DEBUG

//            DEFAULT MOVE
            if (!snake.moveMadeByPlayer)
                snake.moveSnake();

//            PAINTING FRAME IF SNAKE CHANCE
            if (chance) {
                repaint();

                try {
                    Thread.sleep(fpsSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void update(Graphics g){
        paint(g);
    }

    public void paint(Graphics g) {

//        System.out.println("painting frame"); //DEBUG
        Graphics2D G = (Graphics2D) g;

/********************************COUNTDOWN***************************************/
        if (isFirstFrame) {
//        BORDER PRINTING
            g.setColor(borderColor);
            g.drawRect(1, 1, frameXLimit-1, frameYLimit-1);

            g.setColor(textColor);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));

//            GAME BEGINS COUNTDOWN
            for (int i = 0; i < 7; i++) {
                g.clearRect(2, 2, frameXLimit-2, frameYLimit-2);

                if (i == 0)
                    g.drawString("GAME BEGINS IN", Math.round(frameXLimit / 2) - 170, Math.round(frameYLimit / 2));
                else
                    g.drawString(Integer.toString(6 - i), Math.round(frameXLimit / 2), Math.round(frameYLimit / 2));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            g.clearRect(10, 10, frameXLimit - 10, frameYLimit - 10);
            isFirstFrame = false;
        }
/***************************************************************************************/


/***********************************GAMEPLAY********************************************/
        else{
            g.clearRect(10, 10, 300, 30);

//            FOOD PRINTING
            g.setColor(foodColor);
            G.fillRect(food.foodX, food.foodY, 10, 10);

//            BODY PRINTING
            for (int i = snake.x.size()-1 ; i >= 0; i--) {
                if (i == 0)
                    g.setColor(headColor);
                else
                    g.setColor(bodyColor);
                G.fillRect(snake.x.get(i), snake.y.get(i), 10, 10);
            }
            g.clearRect(snake.deadX, snake.deadY, 10, 10);

//            SCORE PRINTING
            g.setColor(scoreColor);
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
            g.drawString(Integer.toString((snake.x.size() - initialLength) * 7), 30, 30);

//            GAME OVER PRINTING
            if(!snake.isAliveee()) {
                gameOver = true;

                g.setColor(textColor);
                g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));

                g.drawString("GAME OVER", Math.round(frameXLimit / 2) - 120, Math.round(frameYLimit / 2));

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

//            PAINTING BORDER
            g.drawRect(1,1, frameXLimit+1, frameYLimit+1);

//            RESETS AFTER EACH GAME PLAY FRAME
            chance = false;
            snake.chance = true;
            snake.moveMadeByPlayer = false;
        }

    }

    @Override
    public void init() {
//        System.out.println("initializing game applet"); //DEBUG
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);

        this.setSize(new Dimension(frameXLimit, frameYLimit));

        snake = new Snake(this);
        theFrameThread = new Thread(this);
        food = new Food(snake, this);

        setBackground(backGroundColor);
    }

    @Override
    public void start() {
        theFrameThread.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (snake.chance && !snake.moveMadeByPlayer) {
//            System.out.println("key pressed in frame - " + e.getKeyChar()); //DEBUG

            snake.setDirection(e.getKeyChar());

            snake.moveSnake();
        }
    }

/**********************************UNNECESSARY******************************************/
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
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
