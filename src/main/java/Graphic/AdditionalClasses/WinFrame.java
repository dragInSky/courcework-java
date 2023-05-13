package Graphic.AdditionalClasses;
import javax.swing.*;
public class WinFrame extends JFrame {
    public WinFrame() {
        super("You Win!");
        setSize(200, 100);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("You Win!", SwingConstants.CENTER);
        add(label);

        setVisible(true);

        // Закрытие окна через 5 секунды
        Timer timer = new Timer(5000, e -> dispose());
        timer.setRepeats(false);
        timer.start();
    }
}
