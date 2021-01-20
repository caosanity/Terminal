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
package driver;

import cmd.*;
import err.*;
import io.*;
import io.ErrorOut;

// TODO: class javadoc
public class JShell {

  private static final String DEFAULT_PROMPT = "/# ";
  private FileSystem fs;
  private Input in;
  private Output out;
  private ErrorOut errorOut;
  private History hist;
  private DirectoryStack dStack;
  // flag indicating if the exit command has been used
  private boolean exit;
  // flag indicating if shell is running
  private boolean running;

  @Override
  public String toString() {
    // show the name of input and output
    String inName = this.in.toString();
    String outName = this.out.toString();
    return "Input: " + inName + "\nOutput: " + outName;
  }

  /**
   * Creates a new <code>JShell</code> that accepts input from
   * <code>System.in</code>, outputs to <code>System.out</code> and prints
   * errors to <code>System.err</code>.
   */
  public JShell() {
    // create a new JShell with System.in and System.out IO
    this(new StdInput(), new StdOutput(), new StdError());
  }

  /**
   * Creates a new <code>JShell</code> that accepts input from <code>in</code>,
   * outputs to <code>out</code>, and prints errors to <code>errorOut</code>.
   * 
   * @param in the source of input
   * @param out the object to output to
   * @param errorOut the object that prints errors
   */
  public JShell(Input in, Output out, ErrorOut errorOut) {
    // initialize the file system
    fs = FileSystem.getFileSystem();
    // set the IO
    this.in = in;
    this.out = out;
    this.errorOut = errorOut;
    // create a new empty history
    this.hist = new History();
    // create empty dir stack
    this.dStack = new DirectoryStack();
    // set exit so that JShell will be run
    exit = false;
    running = false;
  }

  /**
   * Returns true iff the exit command has been executed in JShell
   * 
   * @return whether the exit command has been executed or not
   */
  public boolean isExit() {
    return exit;
  }

  /**
   * Manually sets the JShell to exit if <code>exit</code> is true, or to
   * continue executing if false.
   * 
   * @param exit the boolean that determines the exit of the shell
   */
  public void setExit(boolean exit) {
    this.exit = exit;
  }

  /**
   * Returns true iff this shell is still executing the run() method.
   * 
   * @return a boolean indicating if the shell is running
   */
  public boolean isRunning() {
    return running;
  }

  /**
   * Print a prompt for user input in the output
   */
  public void prompt() {
    // print the default prompt
    String msg = DEFAULT_PROMPT;
    out.printMessage(msg);
  }

  /**
   * Runs the JShell program that accepts input from the set <code>Input</code>
   * and outputs to the set <code>Output</code>. It terminates when the exit
   * command has been executed. When it terminates, the history is cleared.
   * 
   * @throws JShellException If JShell is already running
   */
  public void run() throws JShellException {
    // the shell shud not be running before executing this command
    if (this.isRunning()) {
      throw new JShellException("JShell is already running.");
    } else {
      // JShell is now running
      this.running = true;
    }

    // shell keeps running until the exit command is executed
    while (!this.isExit()) {
      // prompt the user for input
      this.prompt();
      // get the line of text entered by the user
      String line = this.in.readLine();

      // TODO: ADD IN REDIRECTION

      // try to execute the command, printing error messages for any caught
      // exceptions
      this.executeCmd(line);
    }
    // JShell terminated via exit command, so reset the state of the shell
    this.finishRun();
  }

  // TODO: fix java doc for untrackable
  /**
   * Executes the shell command corresponding to the given line of input and
   * prints their output. Any errors that arise will have their error messages
   * printed. Every line of input is recorded in the <code>History</code> object
   * in this shell, unless the shell command corresponding to the input is
   * listed in <code>Untrackable</code>.
   * 
   * @param line the input that specifies what to execute
   */
  public void executeCmd(String line) {
    // process the text into a command and add to history if its not untrackable
    Command cmd = new Command(line);
    CommandCentre cmdCentre = new CommandCentre();
    if (!cmdCentre.isUntrackable(cmd.getName())) {
      this.getHist().addToHistory(line);
    }
    // before trying to execute, we validate as much as we can and printing
    // helpful error msgs if invalid; then when we try to execute, we also
    // print an error msg if smth goes wrong
    String cmdName = cmd.getName();
    if (cmdCentre.commandCheck(cmdName)) {
      ShellCommand shCmd = cmdCentre.createShellCommand(cmd, this);
      try {
        // TODO: revise printing of string for no outputs
        String out = shCmd.execute();
        // if there is output, print it
        if (out != null) {
          this.out.printMessage(out);
        }
      } catch (JShellException e) {
        this.errorOut.printErrorMsg(e);
      }
    } else {
      this.errorOut.printErrorMsg("Invalid command. Please try again.\n");
    }
  }

  private void finishRun() {
    // clear the data thats only for an instance
    this.clearData();
    this.resetFlags();
  }

  /**
   * Removes every input stored in history and the directory stack.
   */
  private void clearData() {
    // TODO: might change if history implements something to clear itself
    this.hist = new History();
    this.dStack.clear();
  }

  private void resetFlags() {
    // shell initially: isnt running, doesnt have exit cmd executed
    this.exit = false;
    this.running = false;
  }

  /**
   * Returns the <code>FileSystem</code> where <code>JShell</code> stores it's
   * items.
   * 
   * @return the file system storing the items
   */
  public FileSystem getFs() {
    return fs;
  }

  /**
   * Returns the object this <code>JShell</code> receives it's input from.
   * 
   * @return the input of the shell
   */
  public Input getIn() {
    return in;
  }

  /**
   * Specifies where <code>JShell</code> should get it's input from.
   * 
   * @param in the input object
   */
  public void setIn(Input in) {
    this.in = in;
  }

  /**
   * Returns the object this <code>JShell</code> outputs to.
   * 
   * @return the object where the output is sent to
   */
  public Output getOut() {
    return out;
  }

  /**
   * Specifies where <code>JShell</code> should output to.
   * 
   * @param out the output object
   */
  public void setOut(Output out) {
    this.out = out;
  }


  /**
   * Returns the object that prints error messages.
   * 
   * @return the object this shell prints errors to
   */
  public ErrorOut getErrorOut() {
    return errorOut;
  }

  /**
   * Specifies where to print error messages.
   * 
   * @param errorOut the object that will handle printing errors
   */
  public void setErrorOut(ErrorOut errorOut) {
    this.errorOut = errorOut;
  }

  /**
   * Returns an object containing every line of text entered into this
   * <code>JShell</code> from execution to the time of this method call.
   * 
   * @return the object containing the history of input
   */
  public History getHist() {
    return hist;
  }


  /**
   * Returns the stack of absolute paths to directories.
   * 
   * @return the object that stores directories in this shell
   */
  public DirectoryStack getDirStack() {
    return dStack;
  }

  public static void main(String[] args) {
    // create a new JShell that takes input from System.in and output from
    // System.out
    JShell sh = new JShell();

    // run the shell
    try {
      sh.run();
    } catch (JShellException e) {
      // the shell was already running which shudnt be the case
      e.printStackTrace();
    }
  }
}
