public class Food {
    int foodX;
    int foodY;
    boolean isEaten = false;

    Snake snake;
    GameFrame gameFrame;

    public Food(Snake snake, GameFrame gameFrame) {
        this.snake = snake;
        this.gameFrame = gameFrame;
        createFood();
    }

    public void createFood() {
//        System.out.println("creating food"); //DEBUG
        do {
            foodX = Math.round((float) (Math.round(Math.random() * 100000) * 10) % (gameFrame.frameXLimit - 10));
            foodY = Math.round((float) (Math.round(Math.random() * 100000) * 10) % (gameFrame.frameYLimit - 10));
        } while (!isFoodValid());

        isEaten = false;
    }

    boolean isFoodValid() {
        for (int i = 0; i < snake.x.size(); i++)
            if (snake.x.get(i) == foodX && snake.y.get(i) == foodY )
                return false;
        return true;
    }
}
