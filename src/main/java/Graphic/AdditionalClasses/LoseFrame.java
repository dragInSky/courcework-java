package Graphic.AdditionalClasses;

import javax.swing.*;

public class LoseFrame extends JFrame {

    public LoseFrame() {
        super("You Lose!");
        setSize(200, 100);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("You Lose!", SwingConstants.CENTER);
        add(label);

        setVisible(true);

        // Закрытие окна через 5 секунды
        Timer timer = new Timer(5000, e -> dispose());
        timer.setRepeats(false);
        timer.start();
    }
}

