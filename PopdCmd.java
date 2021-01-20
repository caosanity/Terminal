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
// UTORID user_name: muruga12
// UT Student #: 1004020999
// Author: Ramesh Murugananthan
//
// Student3:
// UTORID user_name: shahahm6
// UT Student #: 1004192287
// Author: Ahmad Shah
//
// Student4:
// UTORID user_name: caojeff
// UT Student #: 1004259615
// Author: Jeff Cao
//
//
// Honor Code: I pledge that this program represents my own
// program code and that I have coded on my own. I received
// help from no one in designing and debugging my program.
// I have also read the plagiarism section in the course info
// sheet of CSC B07 and understand the consequences.
// *********************************************************
package cmd;

import driver.DirectoryStack;
import driver.JShell;
import driver.Path;
import err.ItemNotFoundException;
import err.JShellException;

/**
 * A shell command that pops the absolute path to a directory in the
 * <code>DirectoryStack</code> stored in <code>JShell</code> and changes the
 * current working directory in <code>FileSystem</code> to the popped path.
 * <p>
 * To execute this command when running <code>JShell</code>, simply enter "popd"
 * with no additional arguments (leading and trailing whitespace is acceptable).
 * 
 * @author Adam Wu
 */
public class PopdCmd extends ShellCommand {

  /**
   * Creates a shell command that has the arguments in <code>cmd</code> and
   * interacts with <code>sh</code>.
   * 
   * @param cmd the object storing the arguments
   * @param sh the shell containing the <code>DirectoryStack</code> and
   *        <code>FileSystem</code> objects
   */
  public PopdCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }

  /**
   * Changes the current working directory of the <code>FileSystem</code> in
   * this <code>JShell</code> to the directory popped from the directory stack.
   * 
   * @throws JShellException if the directory stack is empty 
   */
  @Override
  public String execute() throws JShellException {
    // validate before executing
    try {
      this.validate();
    } catch (JShellException e) {
      throw e;
    }
    // pop the path and cd to it
    DirectoryStack dStack = this.getShell().getDirStack();
    Path newCwd = dStack.pop();
    this.getShell().getFs().changeDirectory(newCwd);
    return null;
  }

  // checks that the args is correct and the state of the directory stack is
  // is correct; if its not then it will throw an exception
  private void validate() throws JShellException {
    // cmd shud have no args
    if (!this.hasValidArgs()) {
      String msg = "ERROR: popd: does not accept any arguments\n";
      throw new JShellException(msg);
    }
    // directory stack shudnt be empty
    if (this.getShell().getDirStack().size() < 1) {
      String msg = "ERROR: popd: cannot popd on empty stack\n";
      throw new JShellException(msg);
    }
  }

  /**
   * Returns true iff this command has 0 arguments.
   * 
   * @return whether the arguments are valid
   */
  public boolean hasValidArgs() {
    // if it has has zero argument, it is valid
    boolean zeroArgs = this.getCmd().getArguments().size() == 0;
    // return the boolean value
    return zeroArgs;
  }

}
