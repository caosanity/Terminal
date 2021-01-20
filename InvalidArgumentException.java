package err;

/**
 * An exception that occurs when an invalid argument is given to a shell
 * command. For example, the number of arguments is invalid or an argument
 * type is invalid.
 * 
 * @author Adam Wu
 */
public class InvalidArgumentException extends JShellException {

  /**
   * Creates an exception for invalid arguments.
   * 
   * @param msg a message explaining the reason for the exception
   */
  public InvalidArgumentException(String msg) {
    super(msg);
  }

}
