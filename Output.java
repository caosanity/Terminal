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
// *********************************************************
package io;

/**
 * This interface is for outputting messages while running <code>JShell</code>.
 * Classes that wish to output a message should implement this interface.
 * 
 * @author Adam Wu
 */
public interface Output {

  /**
   * Prints msg to the output.
   * 
   * @param msg the message that is being printed
   */
  public void printMessage(String msg);

  /**
   * Prints <code>msg</code> to the output, moving to a new section such as a
   * new line.
   * 
   * @param msg the message being printed
   */
  public void printlnMessage(String msg);

  /**
   * Returns the name of the output.
   */
  @Override
  public String toString();
}
