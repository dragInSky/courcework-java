package Graphic.AdditionalClasses;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SearchPlayersWindow extends JFrame {

  public SearchPlayersWindow() {
    super("Searching players...");
    JLabel label = new JLabel("Searching players...");
    add(label);
    setSize(200, 100);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void turnOFF() {
    this.dispose();
  }
}
