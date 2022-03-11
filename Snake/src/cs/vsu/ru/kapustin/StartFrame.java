package cs.vsu.ru.kapustin;

import javax.swing.*;

public class StartFrame extends JFrame {
    private JButton startGameButton;
    private JButton settingsButton;
    private JButton exitButton;
    private JPanel startPanel;

    private GameParams params = new GameParams();

    public StartFrame() {
        this.setTitle("Game");
        this.setContentPane(startPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(700, 250, 400, 350);
        this.setResizable(false);
        this.pack();

        initButtons(new JButton[] {startGameButton, settingsButton, exitButton});

        startGameButton.addActionListener(e -> {
            dispose();
            new GameFrame(params).setVisible(true);
        });

        settingsButton.addActionListener(e -> new GameParamsDialog(params).setVisible(true));

        exitButton.addActionListener(e -> dispose());
    }

    private void initButtons(JButton[] buttons) {
        for (JButton button : buttons) {
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
        }
    }
}
