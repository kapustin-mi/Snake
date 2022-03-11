package cs.vsu.ru.kapustin;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GameParamsDialog extends JDialog {
    private JPanel contentPane;
    private JSpinner spinnerCellsCount;
    private JSpinner spinnerNeedWalls;
    private JSpinner spinnerNumberOfApples;
    private JSpinner spinnerColorOfSnake;
    private JButton saveButton;
    private JButton cancelButton;

    private GameParams params;

    public GameParamsDialog(GameParams params) {
        this.setTitle("Settings");
        this.setContentPane(contentPane);
        this.setModal(true);
        this.setBounds(660, 430, 400, 350);
        this.setResizable(false);
        this.pack();

        this.params = params;

        spinnerCellsCount.setModel(new SpinnerListModel(Utils.readListFromFile("spinners/cellsCount.txt")));
        spinnerNeedWalls.setModel(new SpinnerListModel(Utils.readListFromFile("spinners/presenceOfWalls.txt")));
        spinnerNumberOfApples.setModel(new SpinnerListModel(Utils.readListFromFile("spinners/numbersOfApples.txt")));
        spinnerColorOfSnake.setModel(new SpinnerListModel(Utils.readListFromFile("spinners/snakeColors.txt")));

        initSpinners(new JSpinner[] {spinnerCellsCount, spinnerColorOfSnake, spinnerNeedWalls, spinnerNumberOfApples});
        initButtons(new JButton[] {saveButton, cancelButton});

        saveButton.addActionListener(e -> {
            params.setNumberOfApples(Integer.parseInt(String.valueOf(spinnerNumberOfApples.getValue())));
            params.setCellsCount(Integer.parseInt(String.valueOf(spinnerCellsCount.getValue())));
            String walls = String.valueOf(spinnerNeedWalls.getValue());
            params.setNeedWall(Objects.equals(walls, "yes"));
            Color snakeColor;
            String color = String.valueOf(spinnerColorOfSnake.getValue());
            if (Objects.equals(color, "blue")) {
                snakeColor = Color.BLUE;
            } else if (Objects.equals(color, "black")) {
                snakeColor = Color.BLACK;
            } else if (Objects.equals(color, "yellow")) {
                snakeColor = Color.YELLOW;
            } else if (Objects.equals(color, "purple")) {
                snakeColor = Color.MAGENTA;
            } else {
                snakeColor = Color.GRAY;
            }
            params.setSnakeColor(snakeColor);
            dispose();
        });
    }

    private void initButtons(JButton[] buttons) {
        for (JButton button : buttons) {
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
        }
    }

    private void initSpinners(JSpinner[] spinners) {
        for (JSpinner spinner : spinners) {
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEnabled(false);
            ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setDisabledTextColor(Color.BLACK);
        }
    }
}
