package Graphic;

import static Graphic.AdditionalClasses.DeleteActionWrapper.removeBEEP;

import Graphic.AdditionalClasses.BeautifulButton;
import Graphic.AdditionalClasses.JTextFieldLimit;
import Graphic.AdditionalClasses.SearchPlayersWindow;
import Graphic.Handlers.CheckerAnswers;
import Graphic.Handlers.EventButtonHandler;
import Graphic.Handlers.EventKeyboardHadler;
import Graphic.Handlers.ScreenSettingsManager;
import Graphic.Handlers.ScrollManager;
import Network.Client;
import crossword.Tuple;
import crossword.Word;
import generation.Generator;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.text.BadLocationException;


public class GamePanel extends JFrame {

  private static final StringBuffer verticalDescription = new StringBuffer();
  private static final StringBuffer gorizontalDescription = new StringBuffer();

  public GamePanel() throws InterruptedException, BadLocationException {
    super("Кроссворд");
    SearchPlayersWindow playersWindow = new SearchPlayersWindow();
    Client client = new Client();
    client.connectToServer();
    Generator generator = Generator.getInstance();
    String[][] crossword = generator.getCrossword();
    Tuple size = generator.getSize();
    List<Word> wordsInformation = generator.getWordsInformation();

    final int M = size.x();//но как бы y
    final int N = size.y();//но как бы x
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(crossword[i][j] + " ");
      }
      System.out.println();
    }
    playersWindow.turnOFF();

    ScreenSettingsManager screenSettingsManager = new ScreenSettingsManager();
    screenSettingsManager.setSizeOfMainFrame(this);
    screenSettingsManager.centerFrame(this);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    JPanel mainGrid = new JPanel(new GridLayout(0, 2, 0, 0));

    JPanel crosswordPanel = new JPanel();

    crosswordPanel.setLayout(new GridLayout(N, M));
    //GridBagConstraints constraints = new GridBagConstraints();
    JTextField[][] cells = new JTextField[N][M];
    fillTheCells(crossword, N, M, cells, crosswordPanel);
    fillDescriptions(wordsInformation, cells);

    System.out.println();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        System.out.print(cells[i][j].getText() + " ");
      }
      System.out.println();
    }

    new EventKeyboardHadler().handleEventFromKeyboard(crossword, N, M, cells);

    JButton sendAnswers = BeautifulButton.getInstance();

    new EventButtonHandler().handleEventFromButtonClickToSendAnswers(crossword, N, M, cells,
        sendAnswers);

    JPanel rightPanel = new JPanel(new GridLayout(2, 0));
    JPanel topRightPanel = new JPanel(new GridLayout(0, 2));
    JPanel bottomRightPanel = new JPanel(new GridLayout(0, 2));

    ScrollManager scrollManager = new ScrollManager();
    topRightPanel.add(
        scrollManager.getScrollerOnDescriptionPanels(GamePanel.verticalDescription.toString()));
    topRightPanel.add(
        scrollManager.getScrollerOnDescriptionPanels(GamePanel.gorizontalDescription.toString()));

    bottomRightPanel.add(sendAnswers);
    bottomRightPanel.add(getTimePanel(N, M, cells, wordsInformation));

    rightPanel.add(topRightPanel);
    rightPanel.add(bottomRightPanel);
    //crosswordPanel.pack();
    mainGrid.add(crosswordPanel);
    mainGrid.add(rightPanel);
    getContentPane().add(mainGrid);
    //pack();
    setVisible(true);
  }

  public void fillTheCells(final String[][] crossword, final int N, final int M,
      final JTextField[][] cells, final JPanel grid) {
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
        inputCEll.setText("_");

        cells[i][j] = inputCEll;

        grid.add(inputCEll);
      }
    }
  }

  public JLabel getTimePanel(int N, int M, JTextField[][] cells, List<Word> words) {
    final JLabel timeDisplayer = new JLabel("", SwingConstants.CENTER);
    timeDisplayer.setFont(new Font("Arial", Font.BOLD, 23));

    Timer timer = new Timer(1000, e -> {
      if (count > 0) {
        timeDisplayer.setText("Осталось " + count-- + " сек");
      } else {
        int countRightAnswers = new CheckerAnswers().countOfRightAnswers(N, M, cells, words);
        timeDisplayer.setFont(new Font("Arial", Font.BOLD, 9));
        timeDisplayer.setText("Количество правильных ответов " + countRightAnswers);
      }
    });
    timer.start();
    return timeDisplayer;

  }

  public void fillDescriptions(List<Word> wordsInformation, JTextField[][] cells) {
    int localCount = 0;
    verticalDescription.append("Описание слов по вертикали:\n");
    gorizontalDescription.append("Описание слов по горизонтали:\n");
    for (Word word : wordsInformation) {
      boolean orient = word.orientation();

      if (orient) {
        if (cells[word.tuple().y()][word.tuple().x()].getText().equals("_")) {
          gorizontalDescription.append(++localCount).append(") ").append(word.repr()).append("\n");
        } else {
          gorizontalDescription.append(cells[word.tuple().y()][word.tuple().x()].getText())
              .append(") ").append(word.repr()).append("\n");
        }

      } else {
        if (cells[word.tuple().y()][word.tuple().x()].getText().equals("_")) {
          verticalDescription.append(++localCount).append(") ").append(word.repr()).append("\n");
        } else {
          verticalDescription.append(cells[word.tuple().y()][word.tuple().x()].getText())
              .append(") ").append(word.repr()).append("\n");
        }

      }
      if (cells[word.tuple().y()][word.tuple().x()].getText().equals("_")) {
        cells[word.tuple().y()][word.tuple().x()].setText(String.valueOf(localCount));
      }
      System.out.println(localCount + " " + word.word());
    }
  }

  private volatile int count = 40;

  public static void main(String[] args) throws InterruptedException, BadLocationException {
    new GamePanel();
  }


}

