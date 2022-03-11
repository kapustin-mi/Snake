package cs.vsu.ru.kapustin;

import javax.swing.*;
import java.awt.*;

public class Messages {

    private static final Font DEFAULT_FONT_FOR_MESSAGE = new Font("Segoe Print", Font.BOLD, 16);
    private static final Font DEFAULT_FONT_FOR_BUTTON = new Font("Segoe Print", Font.BOLD, 14);
    private static final Color DEFAULT_BACKGROUND_COLOR = new Color(235, 226, 198);

    public static void showMessage(int messageCode) {
        JTextArea textArea = new JTextArea();

        JButton buttonOK = new JButton("OK");
        buttonOK.addActionListener(e -> JOptionPane.getRootFrame().dispose());

        initMessage(textArea, new JButton[] {buttonOK});

        Object[] buttons = {buttonOK};

        if (messageCode == 0) {
            showMessageWithRules(textArea, buttons);
        }

        if (messageCode == 1) {
            showLossMessage(textArea, buttons);
        }

        if (messageCode == 2) {
            showMessageAboutEndOfGame(textArea, buttons);
        }
    }

    private static void showMessageWithRules(JTextArea textArea, Object[] buttons) {
        String message = """
                     You will control a long thin creature or a "Snake". All you
                have to do is collect food and try not to eat your tail or wall)\040\040\040\040\040""";
        textArea.setText(message);

        JOptionPane.showOptionDialog(null, textArea, "Rules", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);
    }

    private static void showLossMessage(JTextArea textArea, Object[] buttons) {
        String message = "Unfortunately, no more moves can be made. You lost :( ";
        textArea.setText(message);

        JOptionPane.showOptionDialog(null, textArea, "Game over!", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
    }

    private static void showMessageAboutEndOfGame(JTextArea textArea, Object[] buttons) {
        String message = """
                  Hey, stop, please. We need to talk seriously.
                Congratulations, You're crazy. You shouldn't
                have gone that far. But now I think it's time
                to rest, genius :)""";
        textArea.setText(message);

        JOptionPane.showOptionDialog(null, textArea, "Victory!", JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE, null, buttons, buttons[0]);
    }

    public static boolean shouldContinueGame() {
        String message = """
                  Wow, You collected a tile 2048. You won, however...
                You can continue to play and try to reach Your maximum.\040\040
                But also You can leave the winner. The choice is Yours!""";
        JTextArea textArea = new JTextArea(message);

        final boolean[] shouldContinueGame = new boolean[] {false};

        JButton buttonContinueGame = new JButton("Continue the game");
        buttonContinueGame.addActionListener(e -> {
            JOptionPane.getRootFrame().dispose();
            shouldContinueGame[0] = true;
        });

        JButton buttonExit = new JButton("Exit");
        buttonExit.addActionListener(e -> JOptionPane.getRootFrame().dispose());

        initMessage(textArea, new JButton[] {buttonContinueGame, buttonExit});

        Object[] buttons = {buttonContinueGame, buttonExit};

        JOptionPane.showOptionDialog(null, textArea, "Victory!", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, buttons, buttons[1]);


        return shouldContinueGame[0];
    }

    private static void initMessage(JTextArea textArea, JButton[] buttons) {
        textArea.setEditable(false);
        textArea.setBackground(DEFAULT_BACKGROUND_COLOR);
        textArea.setFont(DEFAULT_FONT_FOR_MESSAGE);

        UIManager.put("OptionPane.background", DEFAULT_BACKGROUND_COLOR);
        UIManager.put("Panel.background", DEFAULT_BACKGROUND_COLOR);

        for (JButton button : buttons) {
            button.setBackground(DEFAULT_BACKGROUND_COLOR);
            button.setFont(DEFAULT_FONT_FOR_BUTTON);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
        }
    }
}