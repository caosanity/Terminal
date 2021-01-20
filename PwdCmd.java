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

import driver.FileSystem;
import driver.JShell;
import err.JShellException;
import io.Output;

public class PwdCmd extends ShellCommand {
	public PwdCmd(Command cmd, JShell sh) {
		this.setCmd(cmd);
		this.setShell(sh);
	}

	// a method to determine if proper arguments
	public boolean hasValidArgs() {
		// if it has no arguments, then it has valid args and options
		boolean noArgs = this.getCmd().getArguments().size() == 0;
		// return the boolean value
		return noArgs;
	}

	// a method that prints out the working directory
	public String execute() throws JShellException {
	    try {
	        this.validate();
	      } catch (JShellException e) {
	        throw e;
	      }
	    String ret = "";
	    // TODO: does the line below serve a purpose? if not, remove it
	    this.getShell().getFs();
        ret = FileSystem.getCWD().getPath() + "\n";
        return ret;
	}

	private void validate() throws JShellException {
		// there should no arguments
		if (!this.hasValidArgs()) {
			throw new JShellException("ERROR: pwd: pwd command does not accept extra arguments\n");
		}
		// jshell should not be running
		if (!this.getShell().isRunning()) {
			throw new JShellException("ERROR: pwd: the shell is not being run\n");
		}
	}

}
