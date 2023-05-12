package Graphic.AdditionalClasses;

import javax.swing.*;

public class LoseFrame extends JFrame {

    public LoseFrame() {
        super("You Win!");
        setSize(200, 100);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("You Win!", SwingConstants.CENTER);
        add(label);

        setVisible(true);

        // Закрытие окна через 3 секунды
        Timer timer = new Timer(3000, e -> dispose());
        timer.setRepeats(false);
        timer.start();
    }
}
