package cs.vsu.ru.kapustin;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameFrame extends JFrame {
    private JPanel gamePanel;
    private JTable gameTable;
    private JScrollPane fieldPane;

    private GameParams params;
    private Game game;
    private static final int DEFAULT_CELL_SIZE = 40;

    private Color snakeColor;
    private int cellCount;
    private boolean needWall;

    private int frameY;
    private int frameX;

    Timer timer = new Timer(100, e -> {
        final boolean[] isMovementChanged = {false};
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (game.getMoveState() != MoveState.DOWN && !isMovementChanged[0]) {
                        game.setMoveState(MoveState.UP);
                        isMovementChanged[0] = true;
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (game.getMoveState() != MoveState.UP && !isMovementChanged[0]) {
                        game.setMoveState(MoveState.DOWN);
                        isMovementChanged[0] = true;
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (game.getMoveState() != MoveState.LEFT && !isMovementChanged[0]) {
                        game.setMoveState(MoveState.RIGHT);
                        isMovementChanged[0] = true;
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (game.getMoveState() != MoveState.RIGHT && !isMovementChanged[0]) {
                        game.setMoveState(MoveState.LEFT);
                        isMovementChanged[0] = true;
                    }
                }
            }
        });
        game.makeMove();
        updateGame();
    });

    public GameFrame(GameParams params) {
        this.params = params;
        this.snakeColor = params.getSnakeColor();
        this.needWall = params.isNeedWall();
        this.cellCount = needWall? params.getCellsCount() + 2 : params.getCellsCount();
        this.frameY = (1000 - (DEFAULT_CELL_SIZE * params.getCellsCount())) / 2;
        this.frameX = 500 + ((25 - params.getCellsCount()) / 5) * 100;

        this.setTitle("Snake");
        this.setContentPane(gamePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.fieldPane.setPreferredSize(new Dimension(DEFAULT_CELL_SIZE * cellCount, DEFAULT_CELL_SIZE * cellCount));
        this.setBounds(frameX, frameY, DEFAULT_CELL_SIZE * cellCount, DEFAULT_CELL_SIZE * cellCount);
        this.pack();

        this.game = new Game(params);

        DefaultTableModel tableModel = new DefaultTableModel(cellCount, cellCount);
        gameTable.setModel(tableModel);
        gameTable.setRowHeight(DEFAULT_CELL_SIZE);
        gameTable.setShowGrid(false);
        gameTable.getTableHeader().setPreferredSize(new Dimension(0, 0));
        gameTable.setIntercellSpacing(new Dimension(0, 0));

        game.generateStartingField();

        gameTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            final class DrawComponent extends Component {
                private int row = 0, column = 0;

                @Override
                public void paint(Graphics gr) {
                    Graphics2D g2d = (Graphics2D) gr;
                    paintCell(row, column, g2d);
                }
            }

            final DrawComponent comp = new DrawComponent();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                comp.row = row;
                comp.column = column;
                return comp;
            }
        });
        timer.start();
    }

    private void paintCell(int row, int column, Graphics2D g2d) {
        Color color;
        Cell[][] cells = game.getCells();

        if (cells[row][column].getCellState() == CellState.WALL) {
            color = new Color(17, 133, 12);
        } else {
            if ((row % 2 == 0 && column % 2 == 0) || (row % 2 == 1 && column % 2 == 1)) {
                color = new Color(14, 188, 0);
            } else {
                color = new Color(59, 229, 0);
            }
        }
        g2d.setColor(color);
        g2d.fillRect(0, 0, DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE);

        if (cells[row][column].getCellState() == CellState.SNAKE) {
            color = snakeColor;
            g2d.setColor(color);
            g2d.fillRect(0, 0, DEFAULT_CELL_SIZE, DEFAULT_CELL_SIZE);
        } else if (cells[row][column].getCellState() == CellState.APPLE) {
            color = Color.RED;
            g2d.setColor(color);
            g2d.fillOval(0, 0, DEFAULT_CELL_SIZE - 3, DEFAULT_CELL_SIZE - 3);
        }
    }

    private void updateGame() {
        if (game.getGameState() == GameState.LOSS) {
            JOptionPane.showMessageDialog(null, "ГГ", "LOOSER", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            timer.stop();
        } else if (game.getGameState() == GameState.WIN) {
            JOptionPane.showMessageDialog(null, "ГГ", "WINNER", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            timer.stop();
        } else {
            repaint();
        }
    }
}
