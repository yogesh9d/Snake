public class Food {

    int foodX;
    int foodY;

    Boolean isEaten;

    Snake snake;
    GameFrame theFrame;

    public Food(Snake snake, GameFrame theFrame) {
        this.snake = snake;
        this.theFrame = theFrame;
        createFood();
    }

    public void createFood() {
//        System.out.println("creating food"); //DEBUG
        do {
            foodX = Math.round((float) (Math.round(Math.random() * 100000) * 10) % (theFrame.frameXLimit - 10));
            foodY = Math.round((float) (Math.round(Math.random() * 100000) * 10) % (theFrame.frameYLimit - 10));
        } while (!isFoodValid());
        isEaten = false;
    }

    boolean isFoodValid() {
        for (int i = 0; i < snake.x.size(); i++)
            if ((snake.x.get(i) <= (foodX + 10) && snake.x.get(i) >= foodX) && (snake.y.get(i) <= (foodY + 10) && snake.y.get(i) >= foodY))
                return false;
        return true;
    }
}
