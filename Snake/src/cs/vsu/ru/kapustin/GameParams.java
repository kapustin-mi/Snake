package cs.vsu.ru.kapustin;

import java.awt.*;

public class GameParams {
    private int cellsCount;
    private int numberOfApples;
    private boolean needWall;
    private Color snakeColor;

    private static final int DEFAULT_CELLS_COUNT = 20;
    private static final int DEFAULT_NUMBER_OF_APPLES = 1;
    private static final boolean DEFAULT_NEED_WALLS = true;
    private static final Color DEFAULT_SNAKE_COLOR = Color.BLUE;

    public GameParams() {
        this.cellsCount = DEFAULT_CELLS_COUNT;
        this.numberOfApples = DEFAULT_NUMBER_OF_APPLES;
        this.needWall = DEFAULT_NEED_WALLS;
        this.snakeColor = DEFAULT_SNAKE_COLOR;
    }

    public GameParams(int cellsCount, int numberOfApples, boolean needWall, Color snakeColor) {
        this.cellsCount = cellsCount;
        this.numberOfApples = numberOfApples;
        this.needWall = needWall;
        this.snakeColor = snakeColor;
    }

    public int getCellsCount() {
        return cellsCount;
    }

    public void setCellsCount(int cellsCount) {
        this.cellsCount = cellsCount;
    }

    public int getNumberOfApples() {
        return numberOfApples;
    }

    public void setNumberOfApples(int numberOfApples) {
        this.numberOfApples = numberOfApples;
    }

    public Color getSnakeColor() {
        return snakeColor;
    }

    public void setSnakeColor(Color snakeColor) {
        this.snakeColor = snakeColor;
    }

    public boolean isNeedWall() {
        return needWall;
    }

    public void setNeedWall(boolean needWall) {
        this.needWall = needWall;
    }
}
