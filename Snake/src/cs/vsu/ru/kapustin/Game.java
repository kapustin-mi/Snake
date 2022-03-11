package cs.vsu.ru.kapustin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    private int numberOfApples;
    private int cellsCount;
    private boolean needWalls;

    private MoveState moveState = MoveState.RIGHT;
    private GameState gameState = GameState.PLAYING;
    private final Random rnd = new Random();
    private Cell[][] cells;

    private int lifeTime = 3;
    private int x;
    private int y;

    public Game(GameParams params) {
        this.numberOfApples = params.getNumberOfApples();
        this.needWalls = params.isNeedWall();
        this.cellsCount = needWalls? params.getCellsCount() + 2 : params.getCellsCount();
        this.x = cellsCount / 2;
        this.y = cellsCount / 2;
    }

    private Cell[][] createEmptyCells() {
        Cell[][] field = new Cell[cellsCount][cellsCount];
        for (int r = 0; r < field.length; r++) {
            for (int c = 0; c < field[r].length; c++) {
                field[r][c] = new Cell(CellState.EMPTINESS);
            }
        }
        return field;
    }

    public void generateStartingField() {
        cells = createEmptyCells();
        int lifeTime = 3;

        for (int i = x; i >= x - 2; i--) {
            cells[y][i] = new Cell(CellState.SNAKE, lifeTime);
            lifeTime--;
        }

        if (needWalls) {
            for (int r = 0; r < cells.length; r++) {
                for (int c = 0; c < cells[r].length; c++) {
                    if (r == 0 || r == cells.length - 1 || c == 0 || c == cells.length - 1) {
                        cells[r][c] = new Cell(CellState.WALL);
                    }
                }
            }
        }

        int createdApples = numberOfApples;
        while (createdApples != 0) {
            createApple();
            createdApples--;
        }

    }

    public void createApple() {
        int freeTiles = 0;
        List<Integer> freeTilesIndexes = new ArrayList<>();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j].getCellState() == CellState.EMPTINESS) {
                    freeTiles++;
                    freeTilesIndexes.add(i);
                    freeTilesIndexes.add(j);
                }
            }
        }

        if (freeTiles == 0) {
            gameState = GameState.WIN;
        }

        int newTile = rnd.nextInt(1, freeTiles);
        cells[freeTilesIndexes.get((newTile - 1) * 2)][freeTilesIndexes.get((newTile - 1) * 2 + 1)].setCellState(CellState.APPLE);
    }

    public void makeMove() {
        int startFieldIndex = needWalls ? 1 : 0;
        int lastFieldIndex = needWalls? cellsCount - 2 : cellsCount - 1;

        if (moveState == MoveState.RIGHT) {
            if (x == lastFieldIndex) {
                x = needWalls ? x + 1 : startFieldIndex;
            } else {
                x += 1;
            }
        }

        if (moveState == MoveState.UP) {
            if (y == startFieldIndex) {
                y = needWalls ? y - 1 : lastFieldIndex;
            } else {
                y -= 1;
            }
        }

        if (moveState == MoveState.LEFT) {
            if (x == startFieldIndex) {
                x = needWalls ? x - 1 : lastFieldIndex;
            } else {
                x -= 1;
            }
        }

        if (moveState == MoveState.DOWN) {
            if (y == lastFieldIndex) {
                y = needWalls ? y + 1 : startFieldIndex;
            } else {
                y += 1;
            }
        }

        if (cells[y][x].getCellState() == CellState.SNAKE || cells[x][y].getCellState() == CellState.WALL) {
            gameState = GameState.LOSS;
        } else if (cells[y][x].getCellState() == CellState.APPLE) {
            lifeTime++;
            createApple();
        } else {
            updateField();
        }
        cells[y][x] = new Cell(CellState.SNAKE, lifeTime);
    }

    private void updateField() {
        for (Cell[] rowOfCells : cells) {
            for (Cell cell : rowOfCells) {
                if (cell.getCellState() == CellState.SNAKE) {
                    int lifeTime = cell.getLifeTime() - 1;
                    if (lifeTime == 0) {
                        cell.setCellState(CellState.EMPTINESS);
                    } else {
                        cell.setLifeTime(lifeTime);
                    }
                }
            }
        }
    }

    public void setMoveState(MoveState moveState) {
        this.moveState = moveState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public MoveState getMoveState() {
        return moveState;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
