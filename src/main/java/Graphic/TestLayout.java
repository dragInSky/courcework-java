package Graphic;

import static Graphic.AdditionalClasses.DeleteActionWrapper.removeBEEP;

import Graphic.AdditionalClasses.JTextFieldLimit;
import Graphic.Handlers.EventButtonHandler;
import Graphic.Handlers.EventKeyboardHadler;
import crossword.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TestLayout extends JFrame {


  public TestLayout() throws InterruptedException {
    super("GridLayoutTest");
    String[][] crossword = Main.createCrossword();

    setSize(520, 520);
    setLocation(500, 100);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    // Вспомогательная панель
    JPanel grid = new JPanel();
    final int N = 6;
    final int M = 5;
    /*
     * Первые два параметра конструктора GridLayout определяют количество
     * строк и столбцов в таблице. Вторые 2 параметра - расстояние между
     * ячейками по горизонтали и вертикали
     */
    GridLayout layout = new GridLayout(3, 0, 5, 12);
    grid.setLayout(layout);
    JPanel grid1 = new JPanel();
    JPanel grid2 = new JPanel();
    JPanel grid3 = new JPanel();
    GridLayout layout1 = new GridLayout(N, M, 0, 0);
    GridLayout layout2 = new GridLayout(0, 2, 5, 12);
    grid1.setLayout(layout1);
    grid2.setLayout(layout2);
    grid3.setLayout(new GridLayout(1, 0, 0, 0));
    JTextField[][] cells = new JTextField[N][M];
    //заполняем массив клеток
    fillTheCells(crossword, N, M, cells, grid1);
    //на все нечерные кнопочки добавляем событие
    new EventKeyboardHadler().handleEventFromKeyboard(crossword, N, M, cells);

    JButton sendAnswers = new JButton("Проверить ответы!");
    new EventButtonHandler().handleEventFromButtonClickToSendAnswers(crossword, N, M, cells,
        sendAnswers);
    grid3.add(sendAnswers);
    grid.add(grid1);
    grid.add(grid3);
    grid2.add(new JLabel("Описания слов по вертикали"));
    grid2.add(new JLabel("Описания слов по горизонтали"));
    grid.add(grid2);
    // Размещаем нашу панель в панели содержимого
    getContentPane().add(grid);
    // Устанавливаем оптимальный размер окна
    pack();
    // Открываем окно
    setVisible(true);
    Field[] fields = java.awt.event.KeyEvent.class.getDeclaredFields();
    for (Field f : fields) {
      if (Modifier.isStatic(f.getModifiers())) {
        System.out.println(f.getName());
      }
    }
  }

  public void fillTheCells(final String[][] crossword,
      final int N, final int M, final JTextField[][] cells,
      final JPanel grid1) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        final JTextField inputCEll = new JTextField();
        inputCEll.setDocument(new JTextFieldLimit(1));
        removeBEEP(inputCEll);
        if (!crossword[i][j].equals("_")) {
          inputCEll.setFont(new Font("Serif", Font.BOLD, 25));
        } else {
          inputCEll.setBackground(Color.BLACK);
          //туда нельзя ходить
        }
        cells[i][j] = inputCEll;
        grid1.add(inputCEll);
      }

    }
  }


  public static void main(String[] args) throws InterruptedException {
    new TestLayout();
  }
}

