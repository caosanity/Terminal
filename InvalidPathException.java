package err;

/**
 * An exception that occurs when an invalid path is given. For example, the
 * given 'path' may be an incorrect path or it is a path to the wrong item.
 * 
 * @author Adam Wu
 */
public class InvalidPathException extends JShellException {

  /**
   * Creates an exception for an invalid path.
   * 
   * @param msg a message explaining the reason for the exception
   */
  public InvalidPathException(String msg) {
    super(msg);
  }

}
