// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: caojeff
// UT Student #: 1004259615
// Author: Jeff Cao
//
// Student2:
// UTORID user_name: wuadam
// UT Student #: 1004266741
// Author: Adam Wu
//
// Student3:
// UTORID user_name: muruga12
// UT Student #: 1004020999
// Author: Ramesh Murugananthan
//
// Student4:
// UTORID user_name: shahahm6
// UT Student #: 1004192287
// Author: Ahmad Shah
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
// Student1:
// UTORID user_name: wuadam
// UT Student #: 1004266741
// Author: Adam Wu
//
// Student2:
// UTORID user_name:
// UT Student #:
// Author:
//
// Student3:
// UTORID user_name: muruga12
// UT Student #: 1004020999
// Author: Ramesh Murugananthan
//
// Student4:
// UTORID user_name: shahahm6  
// UT Student #: 1004192287
// Author: Ahmad Shah
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package err;

/**
 * The general class of exceptions related to JShell and its components.
 * <p>
 * Only exceptions of this type are recognized by JShell, so inherit from this
 * class for new exceptions.
 * 
 * @author Adam Wu
 */
public class JShellException extends Exception {

  /**
   * Creates an exception that represents an exception that occurs in the
   * execution of <code>JShell</code> with an error message.
   * 
   * @param msg the string that explains why an exception has occurred
   */
  public JShellException(String msg) {
    super(msg);
  }
}
