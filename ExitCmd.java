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
package cmd;

import driver.JShell;
import err.JShellException;

/**
 * A shell command that instructs the associated <code>JShell</code> to exit
 * when it is executed via the <code>execute</code> method.
 * <p>
 * To execute this command when you run <code>JShell</code>, simply enter the
 * string "exit" into the shell's input without any additional arguments. You
 * may have trailing and leading whitespace in the input.
 * 
 * @author Adam Wu
 */
public class ExitCmd extends ShellCommand {

  /**
   * Creates an exit command with the arguments stored in <code>cmd</code> that
   * will instruct <code>sh</code> to exit.
   * 
   * @param cmd
   * @param sh
   */
  public ExitCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }


  /**
   * Returns true iff there are no arguments stored in this command.
   * 
   * @return whether the arguments are valid
   */
  public boolean hasValidArgs() {
    // exit cmd accepts no additional arguments
    boolean noArgs = this.getCmd().getArguments().size() == 0;
    return noArgs;
  }

  @Override
  /**
   * Tells this <code>JShell</code> to terminate its run() method
   * 
   * @throws JShellException if there are > 0 arguments in this command, or if
   *         the shell is not running
   */
  public String execute() throws JShellException {
    try {
      this.validate();
    } catch (JShellException e) {
      throw e;
    }
    // tell jshell to exit
    this.getShell().setExit(true);
    return null;
  }

  /** throws an error if there there are >0 args or shell is not running */
  private void validate() throws JShellException {
    // there shouldnt be > 0 arguments
    if (!this.hasValidArgs()) {
      throw new JShellException(
          "ERROR: exit: exit command does not accept extra arguments\n");
    }
    // jshell shouldnt be not running
    if (!this.getShell().isRunning()) {
      throw new JShellException("ERROR: exit: the shell is not being run\n");
    }
  }
}
