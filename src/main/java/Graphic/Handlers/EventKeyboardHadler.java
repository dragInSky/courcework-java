package Graphic.Handlers;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

public class EventKeyboardHadler {

  public void handleEventFromKeyboard(final String[][] crossword,
      final int N, final int M, final JTextField[][] cells) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (!crossword[i][j].equals("-")) {
          int finalJ = j;
          int finalI = i;
          cells[i][j].addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
              switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                  if (finalJ + 1 < M) {
                    if (!crossword[finalI][finalJ + 1].equals("_")) {
                      cells[finalI][finalJ].setBackground(Color.white);
                      cells[finalI][finalJ + 1].setBackground(Color.yellow);
                      cells[finalI][finalJ + 1].requestFocus();
                    }
                  }
                  break;
                case KeyEvent.VK_UP:
                  if (finalI - 1 >= 0) {
                    if (!crossword[finalI - 1][finalJ].equals("_")) {
                      cells[finalI][finalJ].setBackground(Color.white);
                      cells[finalI - 1][finalJ].setBackground(Color.yellow);
                      cells[finalI - 1][finalJ].requestFocus();
                    }
                  }
                  break;
                case KeyEvent.VK_DOWN:
                  if (finalI + 1 < N) {

                    if (!crossword[finalI + 1][finalJ].equals("_")) {
                      cells[finalI][finalJ].setBackground(Color.white);
                      cells[finalI + 1][finalJ].setBackground(Color.yellow);
                      cells[finalI + 1][finalJ].requestFocus();
                    }
                  }
                  break;
                case KeyEvent.VK_LEFT:
                  if (finalJ - 1 >= 0) {

                    if (!crossword[finalI][finalJ - 1].equals("_")) {
                      cells[finalI][finalJ].setBackground(Color.white);

                      cells[finalI][finalJ - 1].setBackground(Color.yellow);
                      cells[finalI][finalJ - 1].requestFocus();
                    }
                  }
                  break;
                case KeyEvent.VK_BACK_SPACE:
                  cells[finalI][finalJ].setText("");
                  break;
                default:
                  if (e.getKeyCode() >= KeyEvent.VK_A && e.getKeyCode() <= KeyEvent.VK_Z) {
                    if (finalJ + 1 < M && !crossword[finalI][finalJ + 1].equals("_")) {
                      {
                        cells[finalI][finalJ].setBackground(Color.white);
                        cells[finalI][finalJ + 1].setBackground(Color.YELLOW);
                        java.awt.EventQueue.invokeLater(new Runnable() {
                          public void run() {
                            cells[finalI][finalJ + 1].requestFocus();
                          }
                        });
                      }
                    } else if (finalI + 1 < N && !crossword[finalI + 1][finalJ].equals("_")) {
                      {
                        {
                          cells[finalI][finalJ].setBackground(Color.white);
                          cells[finalI + 1][finalJ].setBackground(Color.YELLOW);
                          java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                              cells[finalI + 1][finalJ].requestFocus();
                            }
                          });
                        }
                      }
                    }
                  }

                  break;
                //переходим к следующей ячкйке
              }
            }
          });
        }
      }
    }
  }

}
