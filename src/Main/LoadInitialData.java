package Main;

import Model.*;
import Util.MyFileHandler;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;

import Model.MyDate;

import Model.BoardGame;
import Model.BoardGameList;
import Model.MemberList;
import Util.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadInitialData {

    public static void main(String[] args) {
        BoardGameList boardgames = new BoardGameList();
        MemberList members = new MemberList();
        String[] boardgamesarray = null;
        String[] memberArray = null;
        EventList events = new EventList();
        String[] eventsArray = null;

        // reading the dummydata for members
        try {
            memberArray = MyFileHandler.readArrayFromTextFile("dummyDataMembers.txt");
            for (int i = 0; i < memberArray.length; i++) {
                String temp = memberArray[i];
                String[] tempArr = temp.split(",");
                String firstName = tempArr[0];
                String lastName = tempArr[1];
                String email = tempArr[2];
                String phoneNumber = tempArr[3];
                members.addMember(new Member(firstName, lastName, phoneNumber, email));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found, or could not be opened");
        }
        //Reading the events from the dummydata
        try {
            eventsArray = MyFileHandler.readArrayFromTextFile("dummyDataEvents.txt");

            for (int i = 0; i < eventsArray.length; i++) {
                BoardGameList boardgamesEvents = new BoardGameList();
                String temp = eventsArray[i];
                String[] tempArr = temp.split("-");
                String date = tempArr[0];
                String timeTemp = tempArr[1];
                String location = tempArr[2];
                int capacity = Integer.parseInt(tempArr[3]);
                String name = tempArr[4];
                String guestsString = tempArr[5];
                String tempGames = tempArr[6];
                String[] tempGamesArr = tempGames.split("/");
                for (String y:tempGamesArr) {
                    String[] tempItems = y.split(",");
                    String nameGame = tempItems[0];
                    String type = tempItems[1];
                    int minNoP = Integer.parseInt(tempItems[2]);
                    int maxNop = Integer.parseInt(tempItems[3]);
                    String owner = tempItems[4];
                    boolean avl = Boolean.parseBoolean(tempItems[5]);
                    boardgamesEvents.addBoardGame(new BoardGame(nameGame, type, minNoP, maxNop,
                            members.getMemberByName(owner), avl));
                }
                events.addEvent(new Event(MyDate.stringToDate(date, timeTemp), location, name, tempArr[5], capacity, boardgamesEvents, new MemberList()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found, or could not be opened");
        }

        //reading the dummydata for the boardgames

        try {
            boardgamesarray = MyFileHandler.readArrayFromTextFile("dummydataboardgames.txt");

            for (String item : boardgamesarray) {
                String[] temp = item.split(",");
                String name = temp[0];
                String type = temp[1];
                int minNoP = Integer.parseInt(temp[2]);
                int maxNop = Integer.parseInt(temp[3]);
                String owner = temp[4];
                boolean avl = Boolean.parseBoolean(temp[5]);
                boardgames.addBoardGame(new BoardGame(name, type, minNoP, maxNop,
                        members.getMemberByName(owner), avl));
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        //Writing the bin file for members
        try {
            MyFileHandler.writeToBinaryFile("members.bin", members);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file ");
        } catch (IOException e) {
            System.out.println("IO Error writing to file ");
        }

        System.out.println("Members Done");
//    writing bin file for events
        try {
            MyFileHandler.writeToBinaryFile("events.bin", events);
            System.out.println("Events Done");
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file ");
        } catch (IOException e) {
            System.out.println("IO Error writing to file ");
        }


        //Writing the bin file for boardgames
        try {
            MyFileHandler.writeToBinaryFile("Boardgames.bin", boardgames);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO error writing to the file");
        }

        System.out.println("Board Games Done");
    }
}
