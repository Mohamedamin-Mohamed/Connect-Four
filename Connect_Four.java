import java.util.*;
import java.lang.*;
import java.util.Scanner;

/**********************
 * Files: Connect_Four.java
 * Author: Mohamedamin Mohamed
 * Contact mohamedamin204080@gmail.com
 * Created 11/29/2022
 * Modified: 03/28/2023
 * Description:This program builds a connect four" simulator!
 *************************/

class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    // array to store the disk colors
    String[][] array = new String[6][7];
    int col;
    String color = "red";

    // loop and initialize the array
    for (int i = 0; i < array.length; ++i) {
      for (int j = 0; j < array[i].length; ++j) {
        array[i][j] = " ";
      }
    }
    // print the array
    printBoard(array);

    // the returned col from the method will be stored
    col = validateInput(scan, color);

    // initialize the last [row][specified column] to red because first 
call is red
    array[5][col - 1] = "R";
    // validation happens in this method, and a check to see if column is 
empty or
    // not
    validateArray(array, col, scan);
  }

  public static void validateArray(String[][] array, int choice, Scanner 
scan) {
    String color = "";
    boolean winner = false;
    int x = 1;

    // run loop until there is a winner and the board is full
    while (winner == false && x <= 41) {
      // loop to start from bottom and assign the abbreviated colors to 
the column the
      // user defined
      for (int i = 5; i >= 0; i--) {
        if (array[i][choice - 1].equals(" ")) {
          if (color.equals("yellow")) {
            /*
             * choice-1 to assign to the right column example if user 
chooses column
             * 7(lastcolumn),and in our array we have only 6 column
             */
            array[i][choice - 1] = "Y";
            break;
          } else if (color.equals("red")) {
            array[i][choice - 1] = "R";
            break;
          }
        }
      }

      // print the board
      printBoard(array);

      // interchange the colors
      if (color.equals("yellow")) {
        // pass abbreviated color if color is yellow to method checkWinner
        winner = checkWinner(array, "Y");
        color = "red";
      } else {
        winner = checkWinner(array, "R");
        color = "yellow";
      }
      // return variable in the method called will be user specified 
column
      choice = validateInput(scan, color);

      // increment the turns played
      ++x;
    }

    // we get to here if there is a winner
    if (winner) {
      if (color.equals("yellow"))
        System.out.println("Red player won");
      else
        System.out.println("Yellow player won");
    }
    // no winner
    else {
      System.out.println("Game is draw");
    }
  }

  // method to print the board
  public static void printBoard(String[][] array) {
    System.out.println(" 1 2 3 4 5 6 7");
    for (int i = 0; i < array.length; ++i) {
      System.out.print("|");

      for (int j = 0; j < array[i].length; ++j) {
        System.out.print(array[i][j]);
        System.out.print("|");
      }
      System.out.println();
    }
    for (int k = 0; k < 15; ++k) {
      System.out.print("-");
    }
    System.out.println();
  }

  // check to see whether the column specified is in the range(1-7)
  public static int validateInput(Scanner scan, String color) {
    int choice = 0;
    boolean mismatch = true;

    // run the loop until there is no inputMismatch exception
    while (mismatch) {

      // try block to see if an exception might occur
      try {
        do {
          System.out.println("Drop a " + color + " disk at which column 
(1-7):");
          choice = scan.nextInt();
        } while (choice < 1 || choice > 7);
        // if we reach here no exception occured
        mismatch = false;
      }
      // catch block to catch the throwed exception
      catch (InputMismatchException e) {
        System.out.println("InputMismatch try again!!");

        // use nextLine() method to remove the trailing line left by 
nextInt()method
        scan.nextLine();

        // will set mismatch to true because an exception occured
        mismatch = true;
      }
    }
    return choice;
  }

  // method to return a boolean if there is a winner
  public static boolean checkWinner(String[][] array, String color) {

    // check to see if there is a winner across
    for (int i = 0; i < 6; ++i) {
      for (int j = 0; j < 4; ++j) {
        if (array[i][j].equals(color) && array[i][j + 1].equals(color) && 
array[i][j +
            2].equals(color)
            && array[i][j + 3].equals(color)) {

          // return true if four color in a row match
          return true;
        }
      }
    }

    // outer loop is running thrice because we can check up and down 
thrice only
    for (int i = 0; i < 3; ++i) {
      for (int j = 0; j < 7; ++j) {
        if (array[i][j].equals(color) && array[i + 1][j].equals(color) && 
array[i + 2][j].equals(color)
            && array[i + 3][j].equals(color)) {

          // match found
          return true;
        }
      }
    }

    // to check diagonal going down
    for (int i = 0; i < 3; ++i) {

      // 2nd loop will run thrice because we can check diagonal going down 
three times
      // by using the columns
      for (int j = 0; j < 4; ++j) {
        if (array[i][j].equals(color) && array[i + 1][j + 1].equals(color) 
&& array[i + 2][j + 2].equals(color)
            && array[i + 3][j + 3].equals(color)) {
          return true;
        }
      }
    }
    // to check diagonal going up and return true if there is a match
    for (int i = 3; i < 6; ++i) {
      for (int j = 0; j < 4; ++j) {
        if (array[i][j].equals(color) && array[i - 1][j + 1].equals(color) 
&& array[i - 2][j + 2].equals(color)
            && array[i - 3][j + 3].equals(color)) {
          return true;
        }
      }
    }

    // no match is found
    return false;
  }
}


