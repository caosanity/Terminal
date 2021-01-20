// **********************************************************
// Assignment2:
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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import driver.History;
import driver.JShell;
import err.JShellException;

public class HistoryCmd extends ShellCommand {

  /**
   * Constructs the History command with inputs of a Command and JShell.
   * 
   * @param cmd The given command
   * @param sh The given shell
   */
  public HistoryCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }

  /**
   * Takes all of the previously entered commands from a list and returns a
   * string representation of that list in a ordered number format. As input
   * parameters it takes in an array list.
   * 
   * @param his A list of all of the previously entered command history
   * @return history A string representation of all the inputed commands in a
   *         list format
   */
  private String basicHistory(ArrayList<String> his) {
    String history = "";
    // Loop through list of commands
    for (int i = 0; i < his.size() - 1; i++) {
      // Add each command to the string
      history += i + 1 + ". " + his.get(i) + "\n";
    }
    history += his.size() + ". " + his.get(his.size() - 1);
    return history;
  }

  /**
   * As input parameters it takes in an array list and a number. Takes the
   * number and retrieves the last amount of that number of commands from the
   * list. Returns those commands in a string representation of the list in an
   * ordered number format.
   * 
   * @param his A list of all of the previously entered command history
   * @param numberOfHistory A number for the amount of previously entered
   *        commands
   * @return history A string representation of all the inputed commands in a
   *         list format
   */
  private String complexHistory(ArrayList<String> his, int numberOfHistory) {
    String result = "";
    if (numberOfHistory > 0) {
      // Loop through the commands in the list starting from the number given
      for (int i = (his.size() - numberOfHistory); i < his.size() - 1; i++) {
        // Add commands to the string
        result += i + 1 + ". " + his.get(i) + "\n";
      }
      // Add the last command to the string
      result += his.size() + ". " + his.get(his.size() - 1);
    }
    // Return empty string for a zero size
    else if (numberOfHistory == 0) {
      result += "";
    }
    return result;
  }

  /**
   * The purpose of this method is to execute the history command.
   * 
   * @throws JShellException The general class for exceptions
   */
  @Override
  public String execute() throws JShellException {
    String message = "";
    History hist = this.getShell().getHist();
    List<String> args = this.getCmd().getArguments();
    // If there is no arguments call the basic history method
    if (args.isEmpty()) {
      message = this.basicHistory(hist.getHist());
    }
    // Otherwise call the complex history method
    else {
      // Check for errors
      try {
        this.validate();
      } catch (JShellException e) {
        throw e;
      }
      message = this.complexHistory(hist.getHist(), Integer.parseInt(args.get(0)));
      }
  // Return the history
  return message + "\n";
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
      msg = "ERROR: history: There are too many arguments\n";
      throw new JShellException(msg);
    }
    // The input should only be made of integer characters
    if(!this.isANumber()) {
      msg = "ERROR: history: the input is not an integer";
      throw new JShellException(msg);
    }
    // The number inputed should not be greater than the length of the list
    else if (Integer.parseInt(args.get(0)) > hist.getHist().size()) {
      msg = "ERROR: history: cannot get a greater number of history\n";
      throw new JShellException(msg);
    }
    // Or less than zero
    else if (Integer.parseInt(args.get(0)) < 0) {
      msg = "ERROR: history: cannot get a negative number of history\n";
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
    boolean noArgs = this.getCmd().getArguments().size() == 0;
    boolean oneArg = this.getCmd().getArguments().size() == 1;
    // return boolean value if no args or one arg
    if (noArgs == true || oneArg == true) {
      return true;
    }
    // Otherwise return false for any more args
    else {
      return false;
    }
  }
}
