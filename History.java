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
package driver;

import java.util.ArrayList;

public class History {
  public ArrayList<String> input;
  
  /**
   * Creates an empty history that will store input entered into
   * <code>JShell</code>.
   */
  public History() {
    input = new ArrayList<>();
  }
  
  /**
   * This method adds input to the history list
   * 
   * @param command A string representation of a possible command
   */
  public void addToHistory(String command) {
    input.add(command);
  }
  
  /**
   * This method returns a list of all the possible commands entered
   * 
   * @return input A list of all the inputed possible commands
   */
  public ArrayList<String> getHist() {
    return input;
  }
  
  /**
   * This method returns a command previously entered specified by the number
   * 
   * @param num a number specifying which history to retrieve 
   * 
   * @return A string from the list of all previous inputs
   */
  public String getSpecificHist(int num) {
    ArrayList<String> list= this.getHist();
    return list.get(num);
  }
}
