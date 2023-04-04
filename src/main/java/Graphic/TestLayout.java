package Graphic;

import static Graphic.AdditionalClasses.DeleteActionWrapper.removeBEEP;

import Graphic.AdditionalClasses.JTextFieldLimit;
import Graphic.Handlers.EventButtonHandler;
import Graphic.Handlers.EventKeyboardHadler;
import crossword.Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TestLayout extends JFrame {

  public void setSizeOfMainFrame(final JFrame frame) {
    Dimension currentScreen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((currentScreen.getWidth()) / 1.2);
    int y = (int) ((currentScreen.getHeight()) / 1.2);
    frame.setSize(x, y);
  }

  public void centerFrame(final JFrame frame) {
    Dimension currentScreen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((currentScreen.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((currentScreen.getHeight() - frame.getHeight()) / 2);
    frame.setLocation(x, y);
  }

  public TestLayout() throws InterruptedException {
    super("GridLayoutTest");
    String[][] crossword = Main.createCrossword();
    final int M = Main.getSize().x();
    final int N = Main.getSize().y();

    setSizeOfMainFrame(this);
    centerFrame(this);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    // Вспомогательная панель
    JPanel grid = new JPanel();

    grid.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    JTextField[][] cells = new JTextField[N][M];
    //заполняем массив клеток
    //final JPanel cellsField = new JPanel();
    //cellsField.setLayout(new GridBagLayout());
    fillTheCells(crossword, N, M, cells, grid, constraints);
    //на все нечерные кнопочки добавляем событие
    new EventKeyboardHadler().handleEventFromKeyboard(crossword, N, M, cells);

    JButton sendAnswers = new JButton("Проверить ответы!");
    //new EventButtonHandler().handleEventFromButtonClickToSendAnswers(crossword, N, M, cells,
    //    sendAnswers);
    //grid.add(cellsField, constraints);
    constraints.ipady = 45;   // кнопка высокая
    constraints.weightx = 0.0;
    constraints.gridwidth = 2;
    constraints.gridx = M / 2;
    constraints.gridy = N;
    //constraints.insets = new Insets(0, 0, 0, 0);

    grid.add(sendAnswers, constraints);
    constraints.gridy = N + 2;
    constraints.gridx = 0;
    grid.add(new JLabel("Описания слов по вертикали"), constraints);
    constraints.gridy = N + 2;
    constraints.gridx = M - 1;
    grid.add(new JLabel("Описания слов по горизонтали"), constraints);
    // Размещаем нашу панель в панели содержимого
    getContentPane().add(grid);
    // Устанавливаем оптимальный размер окна
    pack();
    // Открываем окно
    setVisible(true);

  }

  public void fillTheCells(final String[][] crossword,
      final int N, final int M, final JTextField[][] cells,
      final JPanel grid, final GridBagConstraints constraints) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        final JTextField inputCEll = new JTextField(5);
        inputCEll.setDocument(new JTextFieldLimit(1));
        removeBEEP(inputCEll);
        if (!crossword[i][j].equals("_")) {
          inputCEll.setFont(new Font("Serif", Font.BOLD, 15));
        } else {
          inputCEll.setFont(new Font("Serif", Font.BOLD, 15));
          inputCEll.setBackground(Color.BLACK);
          //туда нельзя ходить
        }
        cells[i][j] = inputCEll;
        constraints.gridx = j;
        constraints.gridy = i;

        grid.add(inputCEll, constraints);
      }

    }
  }


  public static void main(String[] args) throws InterruptedException {
    new TestLayout();
  }
}

