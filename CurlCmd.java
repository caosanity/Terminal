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
package cmd;

import driver.File;
import driver.JShell;
import err.ItemNotFoundException;
import err.JShellException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class CurlCmd extends ShellCommand {
  // constructor
  public CurlCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }

  @Override
  public String execute()
      throws JShellException, ItemNotFoundException {
    try {
    // grab the URL string and create a URL object from it
    String urlString = this.getCmd().getArguments().get(0);
    URL currUrl = new URL(urlString);
    // BufferedReader object to read the file at the URL
    BufferedReader in =
        new BufferedReader(new InputStreamReader(currUrl.openStream()));
    String inputLine;
    // To get the file name we get the name after the last slash
    String fileName =
        urlString.substring(urlString.lastIndexOf("/") + 1, urlString.length());
    
    // create this new file and place it in CWD
    File newFile = new File(fileName, this.getShell().getFs().getCWD());
    this.getShell().getFs().getCWD().setChildFile(newFile);
    
    // Go throw the file and append the file contents
    while ((inputLine = in.readLine()) != null) {
      newFile.appendContents(inputLine + "\n");
    }
    in.close();
    } catch (MalformedURLException e) {
      throw new JShellException("URL not found");
    } catch (IOException e) {
      throw new JShellException("URL not found");
    } catch (IndexOutOfBoundsException e) {
      throw new JShellException("URL not found");
    }
    return null;
  }



  private void validate() throws JShellException {}

  public boolean hasValidArgs() {
    // TODO Auto-generated method stub
    return false;
  }

}
