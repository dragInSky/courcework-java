package Graphic.AdditionalClasses;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;

public class DeleteActionWrapper extends AbstractAction {

  /*
   * Удаляет лишний звук при нажатии клавиши Backspace*/
  private JTextField textField;
  private Action action;

  public DeleteActionWrapper(JTextField textField, Action action) {

    this.textField = textField;
    this.action = action;

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (textField.getCaretPosition() > 0) {
      action.actionPerformed(e);
    }

  }

  public static void removeBEEP(final JTextField textField) {
    Action deleteAction = textField.getActionMap().get(DefaultEditorKit.deletePrevCharAction);
    textField.getActionMap().put(DefaultEditorKit.deletePrevCharAction,
        new DeleteActionWrapper(textField, deleteAction));
  }
}
