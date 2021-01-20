// **********************************************************
// Assignment2:
// Student1:
// UTORID user_name: wuadam
// UT Student #: 1004266741
// Author: Adam Wu
//
// Student2:
// UTORID user_name: muruga12
// UT Student #: 1004020999
// Author: Ramesh Murugananthan
//
// Student3:
// UTORID user_name: shahahm6
// UT Student #: 1004192287
// Author: Ahmad Shah
//
// Student4: caojeff
// UTORID user_name: caojeff
// UT Student #: 1004259615
// Author:
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package cmd;

import java.io.IOException;
import driver.JShell;
import err.ItemNotFoundException;
import err.JShellException;

/**
 * <code>ShellCommand</code> is the abstract base class for all commands that
 * can be entered into <code>JShell</code> and can be executed. A
 * <code>ShellCommand</code> stores the necessary information to perform the
 * execution. This includes the parsed arguments for this shell command, stored
 * in the <code>Command</code> object, and the shell to interact with which is
 * the <code>JShell</code> object.
 * <p>
 * Subclasses should set the <code>Command</code> and <code>JShell</code>
 * objects before the shell command is executed by <code>JShell</code> via the
 * <code>execute</code> method.
 * 
 * @author Adam Wu
 */
public abstract class ShellCommand {
  private Command cmd;
  private JShell shell;

  /**
   * Returns the object that stores the name and the arguments of a parsed line
   * of input.
   * 
   * @return the object that contains the name and arguments for this
   *         <code>ShellCommand</code>
   */
  public Command getCmd() {
    return cmd;
  }

  /**
   * Specifies the object to extract the arguments from.
   * 
   * @param cmd the object storing name and arguments for a command
   */
  public void setCmd(Command cmd) {
    this.cmd = cmd;
  }

  /**
   * Returns the object which gives access to many useful objects
   * <code>JShell</code> uses.
   * 
   * @return the main driver of the shell program
   */
  public JShell getShell() {
    return shell;
  }

  /**
   * Specifies the shell object this <code>ShellCommand</code> should get data
   * from and/or affect.
   * 
   * @param shell the main driver of a shell program
   */
  public void setShell(JShell shell) {
    this.shell = shell;
  }

  /**
   * Executes the shell command and returns the output of this command or
   * <code>null</code> if there is no output. <code>Command</code> and
   * <code>JShell</code> of this <code>ShellCommand</code> must be set before
   * executing.
   * <p>
   * This method is used by <code>JShell</code> to execute this while
   * <code>JShell</code> is running. Exceptions thrown will be caught by
   * <code>JShell</code> which will print the attached message.
   * 
   * @return the string that should be displayed by <code>Output</code>, or
   *         <code>null</code> if there is no output
   * @throws JShellException if something is invalid or goes wrong related to
   *         this command
   */
  public abstract String execute() throws JShellException;

}
