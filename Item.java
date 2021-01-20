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
package driver;

import err.JShellException;

public class Item {
  private String name;

  /**
   * Constructs an item with the given name.
   * 
   * @param name the name of the item
   */
  public Item(String name) {
    this.name = name;
  }
  
  public Item() {};

  /** Returns the name of this item */
  public String getName() {
    return name;
  }

  /**
   * Assigns a new name to this item.
   * 
   * @param name the new name
   */
  public void rename(String newName) {
    name = newName;
  }

  /**
   * Method that checks if a file/directory has a valid name. Only letters a-z
   * (upper and lower case) and numbers from 0-9 are allowed for a name to be
   * valid.
   * 
   * @param name name of file/directory
   */
  public static void validateName(String name) throws JShellException {
    // false by default. If any of the conditions are met, its set to true.
    boolean isValid = false;
    for (char c : name.toCharArray()) {
      // we are converting to ASCII and using that to check validity
      int ascii = (int) c;
      if ((ascii >= 0) && (ascii <= 9)) {
        isValid = true;
      } else if ((ascii >= 65) && (ascii <= 90)) {
        isValid = true;
      } else if ((ascii >= 97) && (ascii <= 122)) {
        isValid = true;
      }

      if (!isValid) {
        throw new JShellException("Name has illegal characters. "
            + "Names may only have letters A-Z, a-z and numbers 0-9.\n");
      }
    }
  }
  
  public String toString() {
    return name;
  }

  
  public Path getPath() {
    // TODO Auto-generated method stub
    return null;
  }
}
