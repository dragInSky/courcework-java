package Graphic.Handlers;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class EventButtonHandler {

  public void handleEventFromButtonClickToSendAnswers(final String[][] crossword,
      final int N, final int M,
      final JTextField[][] cells, final JButton sendAnswers) {
    sendAnswers.addActionListener(e ->
    {
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
          if (!crossword[i][j].equals("_")) {
            if (crossword[i][j].equals(cells[i][j].getText())) {
              cells[i][j].setBackground(Color.GREEN);
            } else {
              cells[i][j].setBackground(Color.RED);
            }
          }
        }
      }
    });
  }
}
