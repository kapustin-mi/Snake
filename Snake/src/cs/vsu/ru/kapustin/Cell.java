package cs.vsu.ru.kapustin;

public class Cell {
    private CellState cellState;
    private int lifeTime;

    public Cell(CellState cellState, int lifeTime) {
        this.cellState = cellState;
        this.lifeTime = lifeTime;
    }

    public Cell(CellState cellState) {
        this.cellState = cellState;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }

    public CellState getCellState() {
        return cellState;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }
}
