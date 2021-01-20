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
 * This interface displays error messages for caught exceptions during the
 * execution of <code>JShell</code>. Classes should implement this interface to
 * output errors.
 * 
 * @author Adam Wu
 */
public interface ErrorOut {

  /**
   * Prints the specified error message.
   * 
   * @param msg the error message explaining why an error has occurred
   */
  public void printErrorMsg(String msg);

  /**
   * Prints the error message stored in the exception.
   * 
   * @param e the exception that has been thrown during shell execution
   */
  public void printErrorMsg(JShellException e);

  /**
   * Prints the specified error message, starting a new section such as a new
   * line.
   * 
   * @param msg the error message explaining why an error has occurred
   */
  public void printlnErrorMsg(String msg);

  /**
   * Prints the error message stored in the exception, starting a new section
   * such as a new line.
   * 
   * @param e the exception that has been thrown during shell execution
   */
  public void printlnErrorMsg(JShellException e);


  /**
   * Returns the name of the error object.
   * 
   * @return the name of the object that prints error messages
   */
  @Override
  public String toString();
}
