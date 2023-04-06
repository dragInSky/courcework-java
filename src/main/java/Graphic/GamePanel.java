package Graphic;

import static Graphic.AdditionalClasses.DeleteActionWrapper.removeBEEP;

import Graphic.AdditionalClasses.BeautifulButton;
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
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;


public class GamePanel extends JFrame {

  public void setSizeOfMainFrame(final JFrame frame) {
    Dimension currentScreen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((currentScreen.getWidth()) / 1.5);
    int y = (int) ((currentScreen.getHeight()) / 1.5);
    frame.setSize(x, y);
  }

  public void centerFrame(final JFrame frame) {
    Dimension currentScreen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((currentScreen.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((currentScreen.getHeight() - frame.getHeight()) / 2);
    frame.setLocation(x, y);
  }

  public GamePanel() throws InterruptedException, BadLocationException {
    super("Кроссворд");
    String[][] crossword = Main.createCrossword();
    JLabel testLabel = new JLabel(
        "1)эластичный продольный тяж, который является осевым скелетом предковых и некоторых современных форм животных организмов.");
    String[] descriptionsVertical = {
        testLabel.getText(),
        "2)инструмент для черчения окружностей и дуг, также может быть использован для измерения расстояний, в частности, на картах.",
        "3)отрезок, соединяющий центр окружности (или сферы) с любой точкой, лежащей на окружности (или сфере), а также длина этого отрезка. Радиус составляет половину диаметра.",
        "4)отрезок, соединяющий две точки на окружности и проходящий через центр окружности, а также длина этого отрезка"};
    final int M = Main.getSize().x();
    final int N = Main.getSize().y();

    setSizeOfMainFrame(this);
    centerFrame(this);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    // Вспомогательная панель
    JPanel mainGrid = new JPanel(new GridLayout(0, 2, 0, 0));

    JPanel crosswordPanel = new JPanel();

    crosswordPanel.setLayout(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    JTextField[][] cells = new JTextField[N][M];
    fillTheCells(crossword, N, M, cells, crosswordPanel, constraints);
    //на все нечерные кнопочки добавляем событие
    new EventKeyboardHadler().handleEventFromKeyboard(crossword, N, M, cells);

    JButton sendAnswers = BeautifulButton.getInstance();

    new EventButtonHandler().handleEventFromButtonClickToSendAnswers(crossword, N, M, cells,
        sendAnswers);

    //grid1.add(sendAnswers, constraints);
    JPanel rightPanel = new JPanel(new GridLayout(2, 0));
    JPanel topRightPanel = new JPanel(new GridLayout(0, 2));

    topRightPanel.add(this.anotherWay());
    JTextField rightDescription = new JTextField("Описание слов по горизонтали");
    topRightPanel.add(this.anotherWay());

    rightPanel.add(topRightPanel);
    // Размещаем нашу панель в панели содержимого
    rightPanel.add(sendAnswers);
    mainGrid.add(crosswordPanel);
    mainGrid.add(rightPanel);
    getContentPane().add(mainGrid);
    // Устанавливаем оптимальный размер окна
    //pack();
    // Открываем окно
    setVisible(true);
  }

  public void fillTheCells(final String[][] crossword, final int N, final int M,
      final JTextField[][] cells, final JPanel grid, final GridBagConstraints constraints) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        final JTextField inputCEll = new JTextField(3);
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


  public static void main(String[] args) throws InterruptedException, BadLocationException {
    new GamePanel();
  }

  public JScrollPane anotherWay() throws BadLocationException {
    JTextPane textPane = new JTextPane();
    Font font = new Font("Times New Roman", Font.ITALIC, 22);
    textPane.setFont(font);
    SimpleAttributeSet attributeSet = new SimpleAttributeSet();
    StyleConstants.setItalic(attributeSet, true);
    StyleConstants.setForeground(attributeSet, Color.blue);
    StyleConstants.setBackground(attributeSet, Color.white);
    textPane.setCharacterAttributes(attributeSet, true);
    textPane.setText("Описание слов по вертикали"
        + "\n1)This is demo text4.This is demo text5. This is demo text6. "
        + "\n2)This is demo text7. This is demo text8. This is demo text9. "
        + "\n3)This is demo text10. This is demo text11. This is demo text12."
        + "\n4)This is demo text13. This is demo text13. This is demo text14."
        + "\n5)This is demo text15. This is demo text13. This is demo text16."
        + "\n6)This is demo text17. This is demo text13. This is demo text18."
        + "\n7)This is demo text19.This is demo text13.This is demo text20.");
    JScrollPane scrollPane = new JScrollPane(textPane);
    scrollPane.setVerticalScrollBarPolicy(
        javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    return scrollPane;
  }
}

