package Graphic.AdditionalClasses;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class BeautifulButton extends JButton {

  private static BeautifulButton INSTANCE;

  private BeautifulButton() {
    super(
        "Show The Answers!");
    this.setBackground(new Color(59, 89, 182));
    this.setForeground(Color.WHITE);
    this.setFocusPainted(false);
    this.setFont(new Font("Agency FB", Font.BOLD, 30));
  }

  public static synchronized BeautifulButton getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new BeautifulButton();
    }
    return INSTANCE;
  }
}



