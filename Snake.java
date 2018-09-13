public class Snake extends Body{
    GameFrame gameFrame;

    boolean chance = false;
    boolean moveMadeByPlayer = false;
    char direction;

    public Snake(GameFrame gameFrame) {
        super(gameFrame);
        this.gameFrame = gameFrame;
        direction = gameFrame.initialDirection;
    }

    public void setDirection(Character newDirection) {
//        System.out.println("setting direction"); //DEBUG

        if(newDirection == gameFrame.up && direction != gameFrame.down)
            direction = newDirection;
        else if(newDirection == gameFrame.down && direction != gameFrame.up)
            direction = newDirection;
        else if(newDirection == gameFrame.right && direction != gameFrame.left)
            direction = newDirection;
        else if(newDirection == gameFrame.left && direction != gameFrame.right)
            direction = newDirection;

    }

    public void moveSnake() {
        moveMadeByPlayer = true;
//        System.out.println("moving snake");
        addHead(direction);
        removeTail();

//        CHECKING IF FOOD IS EATEN
        if (!gameFrame.food.isFoodValid()) {
            addTail();
            gameFrame.food.createFood();
        }

        chance = false;
        gameFrame.chance = true;
    }
}
