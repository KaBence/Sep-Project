package Util;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * class for writing and reading binary and text files
 */
public class MyFileHandler
{
  /**
   * Writes the given string to a file with the given file name
   * @param fileName name of the text file
   * @param str The string that is saved to the file
   * @throws FileNotFoundException If the program finds a problem with the file
   */

  public static void writeToTextFile(String fileName, String str) throws FileNotFoundException
  {
    writeText(fileName, str, false);
  }

  /**
   *  Appends the given string to a file with the given file name
   * @param fileName name of the text file
   * @param str The string that is saved to the file
   * @throws FileNotFoundException If the program finds a problem with the file
   */
  public static void appendToTextFile(String fileName, String str) throws FileNotFoundException
  {
    writeText(fileName, str, true);
  }

  /**
   *  writeToTextFile and appendToTextFile are almost identical - only the boolean in the constructor
   *  of the FileOutputStream differs. So I made this private method that both methods call
   * @param fileName name of the text file
   * @param str The string that is saved to the file
   * @param append If it is false it overwrites the file
   * @throws FileNotFoundException If the program finds a problem with the file
   */
  private static void writeText(String fileName, String str, boolean append) throws FileNotFoundException
  {
    PrintWriter writeToFile = null;

    try
    {
      FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
      writeToFile = new PrintWriter(fileOutStream);
      writeToFile.println(str);
    }
    finally
    {
      if (writeToFile != null)
      {
        writeToFile.close();
      }
    }
  }

  /**
   * Writes the strings in the given array to a file with the given file name
   * @param fileName name of the text file
   * @param strs The string array that is saved to the file
   * @throws FileNotFoundException If the program finds a problem with the file
   */
  public static void writeArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException
  {
    writeText(fileName, strs, false);
  }

  /**
   *  Appends the strings in the given array to a file with the given file name
   * @param fileName name of the text file
   * @param strs The string array that is saved to the file
   * @throws FileNotFoundException If the program finds a problem with the file
   */
  public static void appendArrayToTextFile(String fileName, String[] strs) throws FileNotFoundException
  {
    writeText(fileName, strs, true);
  }

  /**
   * Again, the writeArrayToTextFile and appendArrayToTextFile methods are almost identical.
   * So I made this private method that both methods call
   * @param fileName name of the text file
   * @param strs The string array that is saved to the file
   * @param append If it is false it overwrites the file
   * @throws FileNotFoundException If the program finds a problem with the file
   */
  private static void writeText(String fileName, String[] strs, boolean append) throws FileNotFoundException
  {
    PrintWriter writeToFile = null;

    try
    {
      FileOutputStream fileOutStream = new FileOutputStream(fileName, append);
      writeToFile = new PrintWriter(fileOutStream);

      for (int i = 0; i < strs.length; i++)
      {
        writeToFile.println(strs[i]);
      }
    }
    finally
    {
      if (writeToFile != null)
      {
        writeToFile.close();
      }
    }
  }

  /**
   * Reads the first line from the file with the given file name and returns it as a String
   * @param fileName name of the text file
   * @return The content of the text file as a string
   * @throws FileNotFoundException If the program finds a problem with the file
   */
  public String readFromTextFile(String fileName) throws FileNotFoundException
  {
    Scanner readFromFile = null;
    String str = "";

    try
    {
      FileInputStream fileInStream = new FileInputStream(fileName);
      readFromFile = new Scanner(fileInStream);
      str = readFromFile.nextLine();
    }
    finally
    {
      if (readFromFile != null)
      {
        readFromFile.close();
      }
    }
    return str;
  }

  /**
   * Reads all lines from the file with the given file name and returns it as a String[]
   * @param fileName name of the text file
   * @return the content of the file as a string array
   * @throws FileNotFoundException If the program finds a problem with the file
   */
  public static String[] readArrayFromTextFile(String fileName) throws FileNotFoundException
  {
    Scanner readFromFile = null;
    ArrayList<String> strs = new ArrayList<String>();

    try
    {
      FileInputStream fileInStream = new FileInputStream(fileName);
      readFromFile = new Scanner(fileInStream);

      while (readFromFile.hasNext())
      {
        strs.add(readFromFile.nextLine());
      }
    }
    finally
    {
      if (readFromFile != null)
      {
        readFromFile.close();
      }
    }

    String[] strsArray = new String[strs.size()];
    return strs.toArray(strsArray);
  }

  /**
   * Writes the given object to a file with the given file name
   * @param fileName name of the text file
   * @param obj This object is getting saved to the file
   * @throws FileNotFoundException If the program finds a problem with the file
   * @throws IOException If the program finds and IO problem with the file
   */
  public static void writeToBinaryFile(String fileName, Object obj) throws FileNotFoundException, IOException
  {
    ObjectOutputStream writeToFile = null;

    try
    {
      FileOutputStream fileOutStream = new FileOutputStream(fileName);
      writeToFile = new ObjectOutputStream(fileOutStream);

      writeToFile.writeObject(obj);
    }
    finally
    {
      if (writeToFile != null)
      {
        try
        {
          writeToFile.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + fileName);
        }
      }
    }
  }

  /**
   * Writes the objects in the given array to a file with the given file name
   * @param fileName name of the text file
   * @param objs These objects are getting saved to the file
   * @throws FileNotFoundException If the program finds a problem with the file
   * @throws IOException If the program finds and IO problem with the file
   */
  public static void writeArrayToBinaryFile(String fileName, Object[] objs) throws FileNotFoundException, IOException
  {
    ObjectOutputStream writeToFile = null;

    try
    {
      FileOutputStream fileOutStream = new FileOutputStream(fileName);
      writeToFile = new ObjectOutputStream(fileOutStream);

      for (int i = 0; i < objs.length; i++)
      {
        writeToFile.writeObject(objs[i]);
      }
    }
    finally
    {
      if (writeToFile != null)
      {
        try
        {
          writeToFile.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + fileName);
        }
      }
    }
  }

  /**
   *  Reads the first object from the file with the given file name and returns it.
   *  Whoever calls the method will need to cast it from type Object to its actual type
   * @param fileName name of the text file
   * @return The object that is in the file
   * @throws FileNotFoundException If the program finds a problem with the file
   * @throws IOException If the program finds and IO problem with the file
   * @throws ClassNotFoundException f cast the return object to a class, and it is not the correct one
   */
  public static Object readFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
  {
    Object obj = null;
    ObjectInputStream readFromFile = null;
    try
    {
      FileInputStream fileInStream = new FileInputStream(fileName);
      readFromFile = new ObjectInputStream(fileInStream);
      try
      {
        obj = readFromFile.readObject();
      }
      catch (EOFException eof)
      {
        //Done reading
      }
    }
    finally
    {
      if (readFromFile != null)
      {
        try
        {
          readFromFile.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + fileName);
        }
      }
    }

    return obj;
  }

  /**
   * Reads all objects from the file with the given file name and returns it as an Object[].
   * Whoever calls the method will need to cast the Objects to their actual type
   * @param fileName name of the text file
   * @return The object that is in the file as an Object array
   * @throws FileNotFoundException If the program finds a problem with the file
   * @throws IOException If the program finds and IO problem with the file
   * @throws ClassNotFoundException If cast the return object to a class, and it is not the correct one
   */
  public static Object[] readArrayFromBinaryFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException
  {
    ArrayList<Object> objs = new ArrayList<Object>();

    ObjectInputStream readFromFile = null;
    try
    {
      FileInputStream fileInStream = new FileInputStream(fileName);
      readFromFile = new ObjectInputStream(fileInStream);
      while (true)
      {
        try
        {
          objs.add(readFromFile.readObject());
        }
        catch (EOFException eof)
        {
          //Done reading
          break;
        }
      }
    }
    finally
    {
      if (readFromFile != null)
      {
        try
        {
          readFromFile.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + fileName);
        }
      }
    }

    return objs.toArray();
  }
}
