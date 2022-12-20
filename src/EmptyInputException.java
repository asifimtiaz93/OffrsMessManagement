import javax.swing.JOptionPane;

public class EmptyInputException extends Exception {
  public EmptyInputException(String message) {
    super(message);
    JOptionPane.showMessageDialog(null, message);
  }
}
