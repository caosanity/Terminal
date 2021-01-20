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
// UTORID user_name:
// UT Student #:
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package io;

import err.JShellException;

/**
 * This class prints error messages to <code>System.err</code> during the
 * execution of <code>JShell</code>.
 * 
 * @author Adam Wu
 */
public class StdError implements ErrorOut {

  /**
   * Prints the specified error message to <code>System.err</code> without
   * moving to the next line.
   * 
   * @param msg the error message explaining why an error has occurred
   */
  @Override
  public void printErrorMsg(String msg) {
    System.err.print(msg);
  }

  /**
   * Prints the error message stored in the given exception to
   * <code>System.err</code> without moving to the next line. Equivalent to
   * <code>System.err.println()</code>.
   * 
   * @param e the exception that has been thrown during shell execution
   */
  @Override
  public void printErrorMsg(JShellException e) {
    System.err.print(e.getMessage());
  }

  /**
   * Prints the specified error message to <code>System.err</code>, moving to
   * the next line.
   * 
   * @param msg the error message explaining why an error has occurred
   */
  public void printlnErrorMsg(String msg) {
    System.err.println(msg);
  }

  /**
   * Prints the error message stored in the given exception to
   * <code>System.err</code>, moving to the next line. Equivalent to
   * <code>System.err.println()</code>.
   * 
   * @param e the exception that has been thrown during shell execution
   */
  public void printlnErrorMsg(JShellException e) {
    System.err.println(e.getMessage());
  }

  @Override
  public String toString() {
    return "System.err";
  }

}
