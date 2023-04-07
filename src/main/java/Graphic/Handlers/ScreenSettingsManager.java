package Graphic.Handlers;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class ScreenSettingsManager {

  public void setSizeOfMainFrame(final JFrame frame) {
    Dimension currentScreen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((currentScreen.getWidth()) / 1.1);
    int y = (int) ((currentScreen.getHeight()) / 1.1);
    frame.setSize(x, y);
  }

  public void centerFrame(final JFrame frame) {
    Dimension currentScreen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((currentScreen.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((currentScreen.getHeight() - frame.getHeight()) / 2);
    frame.setLocation(x, y);
  }
}
