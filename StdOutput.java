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

/**
 * This class outputs messages during the execution of <code>JShell</code> to
 * <code>System.out</code>.
 * 
 * @author Adam Wu
 */
public class StdOutput implements Output {

  /**
   * Prints <code>msg</code> to <code>System.out</code> (without moving to the
   * next line). Equivalent to <code>System.out.print(msg)</code>.
   * 
   * @param msg the message being printed
   */
  @Override
  public void printMessage(String msg) {
    System.out.print(msg);
  }

  /**
   * Prints <code>msg</code> to <code>System.out</code>, moving to the next
   * line. Equivalent to <code>System.out.println(msg)</code>.
   * 
   * @param msg the message being printed
   */
  @Override
  public void printlnMessage(String msg) {
    System.out.println(msg);
  }

  @Override
  public String toString() {
    return "System.out";
  }
}
