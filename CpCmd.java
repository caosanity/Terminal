package cmd;

import java.util.List;
import err.ItemNotFoundException;
import err.JShellException;
import driver.*;
import java.util.ArrayList;

public class CpCmd extends ShellCommand implements Cloneable {

  public CpCmd(Command cmd, JShell sh) {
    this.setCmd(cmd);
    this.setShell(sh);
  }
  
  // TODO complete
  @Override
  public String execute() throws JShellException, ItemNotFoundException {
    // get paths
    List<String> arguments = this.getCmd().getArguments();
    Path oldPath = new Path(arguments.get(0));
    Path newPath = new Path(arguments.get(1));
    
    
    // check if path is absolute or relative and fix the oldPath if its relative
    // Fixing => turning relative path to absolute path
    if (oldPath.isRelative()) {
      if (this.getShell().getFs().getCWD().getPath().getParentPath() != null) {
      oldPath = this.getShell().getFs().getCWD().getPath().getParentPath().add(oldPath);
      } else {
        // if the parent of the relative directory is the root, we just add the
        // "/" before the relative path
        oldPath = this.getShell().getFs().getRoot().getPath().add(oldPath);
      }
    }
    
    // Do the same thing for the newpath
    if (newPath.isRelative()) {
      if (this.getShell().getFs().getCWD().getPath().getParentPath() != null) {
      newPath = this.getShell().getFs().getCWD().getPath().getParentPath().add(newPath);
      } else {
        newPath = this.getShell().getFs().getRoot().getPath().add(newPath);
      }
    }

    //System.out.println(oldPath + "     ");
    // grabbing item at oldPath and removing it from the old path
    Item oldItem = this.getShell().getFs().getItemAtPath(oldPath);
    
    // get Item at new path
    Item newParent = this.getShell().getFs().getItemAtPath(newPath);
    
    // Since new parent must be a directory we verify that its a directory
    if (newParent instanceof Directory) {
      // Set the oldItem as a child
      if (oldItem instanceof File) {
      ((Directory) newParent).setChildFile((File) oldItem);
      } else {
        // fix the parent/child relationship
        ((Directory) newParent).setChildDirectory((Directory) oldItem);
        ((Directory) oldItem).setParent((Directory) newParent);
        // change the paths of the old item so they are relative to the new 
        // directory
        this.getShell().getFs().fixPaths((Directory) oldItem, (Directory) newParent);
      }
    } else {
      throw new JShellException("NEWPATH must be a path to a directory");
    }
    return null;
  }
  
  
  @Override
  public Object clone(){  
    try{  
        return super.clone();  
    }catch(Exception e){ 
        return null; 
    }
}
}
