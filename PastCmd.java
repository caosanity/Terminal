// **********************************************************
// Student1:
// UTORID user_name: muruga12
// UT Student #: 1004020999
// Author: Ramesh Murugananthan
//
// Student2:
// UTORID user_name: wuadam
// UT Student #: 1004266741
// Author: Adam Wu
//
// Student3:
// UTORID user_name: caojeff
// UT Student #: 1004259615
// Author: jeff cao
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


package cmd;

import java.util.List;
import java.util.regex.Pattern;
import driver.History;
import driver.JShell;
import err.JShellException;

public class PastCmd extends ShellCommand {

  /**
   * Constructs the History command with inputs of a Command and JShell.
   * 
   * @param cmd The given command
   * @param sh The given shell
   */
  public PastCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }

  // TODO fix javadoc:
  /**
   * The purpose of this method is to execute the past command.
   *
   * @return 
   * @throws JShellException The general class for exceptions
   */
  @Override
  public String execute() throws JShellException {
    String message = "";
    History hist = this.getShell().getHist();
    List<String> args = this.getCmd().getArguments();
    // check for errors
    try {
      this.validate();
    } 
    catch (JShellException e) {
      throw e;
    }
    // get the command at the given index
    message = hist.getSpecificHist(Integer.parseInt(args.get(0)) - 1);
    this.getShell().executeCmd(message);
    return null;
  }

  /**
   * Checks that the number of arguments are correct and if an argument is
   * given, it is not greater than the list size and non negative
   * 
   * @throws JShellException The general class for exceptions
   */
  // 
  private void validate() throws JShellException {
    History hist = this.getShell().getHist();
    List<String> args = this.getCmd().getArguments();
    String msg = "";
    // command should have one or no arguments
    if (!this.hasValidArgs()) {
      msg = "ERROR: !: The amount of arguments given are incorrect\n";
      throw new JShellException(msg);
    }
    if(!this.isANumber()) {
      msg = "ERROR: !: the input is not an integer";
      throw new JShellException(msg);
    }
    // The number inputed should not be greater than the length of the list
    else if (Integer.parseInt(args.get(0)) > hist.getHist().size()) {
      msg = "ERROR: !: the value inputted does not exist in history\n";
      throw new JShellException(msg);
    }
    // Or less than zero
    else if (Integer.parseInt(args.get(0)) < 0) {
      msg = "ERROR: !: cannot get a negative number of history\n";
      throw new JShellException(msg);
    }
  }

  private boolean isANumber() {
    // check that every character is a digit
    boolean number = false;
    Pattern p = Pattern.compile("[0-9]+");
    number = p.matcher(this.getCmd().getArguments().get(0)).matches();
    return number;
  }

  /**
   * The purpose of this method is to check if the history method has the
   * correct number of arguments
   * 
   * @return whether the arguments are valid
   */

  public boolean hasValidArgs() {
    // if it has no arguments then it has valid args and options
    boolean oneArg = this.getCmd().getArguments().size() == 1;
    // return boolean value if no args or one arg
    if (oneArg == true) {
      return true;
    }
    // Otherwise return false for any more args
    else {
      return false;
    }
  }
}
