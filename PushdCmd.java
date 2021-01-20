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

import driver.JShell;
import driver.Path;
import err.ItemNotFoundException;
import err.JShellException;

/**
 * A shell command that pushes the path to current working directory to the
 * <code>DirectoryStack</code> and changes the current working directory to the
 * one given when executed. This allows for the user to change back into the
 * most recently stored directory using <code>PopdCmd</code> at a later time.
 * <p>
 * To use this command while the shell is running, the input should match the
 * following syntax: "pushd pathToDirectory" where pathToDirectory is the path
 * to the directory the user wants to change to. The input may have any number
 * of whitespace before, between, and after arguments.
 * 
 * @author Adam Wu
 */
public class PushdCmd extends ShellCommand {

  /**
   * Creates a shell command that uses <code>cmd</code> for the arguments and
   * interacts with <code>sh</code>.
   * 
   * @param cmd object containing the arguments
   * @param sh the shell this interacts with
   */
  public PushdCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }

  /**
   * Pushes the absolute path of the current working directory to the the
   * <code>DirectoryStack</code> of this <code>JShell</code> and changes the
   * current working directory to the one given.
   * 
   * @throws JShellException if it doesn't have valid arguments or if the path
   *         doesn't exist in the <code>FileSystem</code>
   */
  @Override
  public String execute() throws JShellException {
    try {
      this.validate();
    } catch (JShellException e){
      throw e;
    }
    // push curr working director onto stack and cd to given path
    Path cwd = this.getShell().getFs().getCWD().getPath();
    this.getShell().getDirStack().push(cwd);
    String newCwd = this.getCmd().getArguments().get(0);
    this.getShell().getFs().changeDirectory(new Path(newCwd));
    return null;
  }

  private void validate() throws JShellException {
    String msg;
    if(!this.hasOneArg()) {
      msg = "ERROR: pushd: pushd only accepts 1 argument\n";
      throw new JShellException(msg);
    }
    if (!this.hasPathArg()) {
      msg = "ERROR: pushd: the given argument is not a path\n";
      throw new JShellException(msg);
    }
    // TODO: check if the path exists and is a directory
  }

  /**
   * Returns true iff this shell command only has 1 argument and this argument
   * can be made into a <code>Path</code>.
   * 
   * @return whether the arguments are valid
   */
  public boolean hasValidArgs() {
    // must only have 1 argument that is a path
    return this.hasOneArg() && this.hasPathArg();
  }

  private boolean hasOneArg() {
    return this.getCmd().getArguments().size() == 1;
  }

  private boolean hasPathArg() {
    return Path.isAPath(this.getCmd().getArguments().get(0));
  }
}
