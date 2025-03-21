package Model;

import Util.MyFileHandler;
import java.sql.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;

/**
 * The BoardGameManager class is a class that manages the boardgames, members, and events
 */
public class BoardGameManager implements Serializable
{
  private String boardgames;
  private String members;
  private String events;

  private MemberList memberList;
  private BoardGameList boardGameList;
  private EventList eventList;

  /**
   * A constructor that takes in three strings and sets them to the variables in the class.
   * @param boardgames String
   * @param events String
   * @param members String
   */
  public BoardGameManager(String boardgames,String members,String events)
  {
    //this.events=events;
    //this.members=members;
    //this.boardgames=boardgames;
    try
    {
      DriverManager.registerDriver(new org.postgresql.Driver());
    }
    catch (SQLException e)
    {
      System.err.println(e.getMessage());
    }
    getAllMembers();
    getAllBoardGames();
  }



  /**
   * It reads the memberList from the file and returns it
   * @return A list of all members.
   */
  public MemberList getAllMembers(){
    memberList=new MemberList();
    try(Connection connection=getConnection())
    {
      PreparedStatement ps=connection.prepareStatement("select * from member");

      ResultSet rs=ps.executeQuery();
      while (rs.next()){
        String firstname=rs.getString("firstname");
        String lastname=rs.getString("lastname");
        String phoneNo=rs.getString("phoneno");
        String email=rs.getString("email");
        memberList.addMember(new Member(firstname,lastname,phoneNo,email));
      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    /*try
    {
      memberList=(MemberList) MyFileHandler.readFromBinaryFile(members);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
    catch (ClassNotFoundException e){
      System.out.println("Wrong class in file");
    }*/
    return memberList;
  }

  /**
   * It gets all the boardGames from the boardGameList.
   * @return The method returns a BoardGameList object.
   */
  public BoardGameList getAllBoardGames(){
    boardGameList=new BoardGameList();
    getAllMembers();
    try(Connection connection=getConnection())
    {
      PreparedStatement ps=connection.prepareStatement("select name,type,minPlayers,maxPlayers,availability,ownerId,number,b.pickupDate as BorrowPick,b.borrower as borrow,b.returnDate as BorrowReturn,r.borrower as reserver,r.returnDate as ReserveReturn,r.pickupDate as reservePick,r2.review,r2.rankno from boardgame\n"
          + "    left join Vote V on boardGame.gameID = V.gameID\n"
          + "    left join borrow b on boardGame.gameID = b.gameID\n"
          + "    left join reservation r on boardGame.gameID = r.gameID\n"
          + "    left join Rank R2 on boardGame.gameID = R2.gameID;");

      ResultSet rs=ps.executeQuery();

      while (rs.next()){
        String name=rs.getString("name");
        String type=rs.getString("type");
        int minPlayers=rs.getInt("minplayers");
        int maxPlayers=rs.getInt("maxplayers");
        boolean availabilty=rs.getBoolean("availability");
        String phone=rs.getString("ownerId");
        Member temp=memberList.getMemberByOwnerID(phone);
        BoardGame tempgame=new BoardGame(name,type,minPlayers,maxPlayers,temp,availabilty);
        int voteNumber=rs.getInt("number");
        String borrower=rs.getString("borrow");
        if (borrower!=null){
          Date borrowPick=rs.getDate("borrowpick");
          MyDate borrowPickDate= new MyDate(borrowPick.toLocalDate().getDayOfMonth(), borrowPick.toLocalDate().getMonthValue(),borrowPick.toLocalDate().getYear());
          Date borrowReturn=rs.getDate("borrowReturn");
          MyDate borrowReturnDate= new MyDate(borrowReturn.toLocalDate().getDayOfMonth(), borrowReturn.toLocalDate().getMonthValue(),borrowReturn.toLocalDate().getYear());
          Member borrowUser=memberList.getMemberByOwnerID(borrower);
          tempgame.setBorrow(new Borrow(borrowUser,borrowReturnDate,borrowPickDate));
        }
        String reserver=rs.getString("reserver");
        if (reserver!=null){
          Date reservePick=rs.getDate("reservepick");
          MyDate reservePickDate= new MyDate(reservePick.toLocalDate().getDayOfMonth(), reservePick.toLocalDate().getMonthValue(),reservePick.toLocalDate().getYear());
          Date reserveReturn=rs.getDate("reservereturn");
          MyDate reserveReturnDate= new MyDate(reserveReturn.toLocalDate().getDayOfMonth(), reserveReturn.toLocalDate().getMonthValue(),reserveReturn.toLocalDate().getYear());
          Member reserveUser=memberList.getMemberByOwnerID(reserver);
          if (tempgame.getReservationList()==null){
            tempgame.setReservationList(new ReservationList());
          }
          tempgame.getReservationList().addReservation(new Reservation(reserveUser,reservePickDate,reserveReturnDate));
        }
        String review=rs.getString("review");
        if (review!=null){
          int rankNo=rs.getInt("rankno");
          if (tempgame.getRankList()==null)
            tempgame.setRankList(new RankList());
          tempgame.getRankList().addRank(new Rank(review,rankNo));
        }

        tempgame.getVoteList().setVote(voteNumber);
        boardGameList.addBoardGame(tempgame);
      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    /*try
    {
      boardGameList=(BoardGameList) MyFileHandler.readFromBinaryFile(boardgames);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
    catch (ClassNotFoundException e){
      System.out.println("Wrong class in file");
    }*/
    return boardGameList;
  }



  /**
   * It gets all the events from the EventList.
   * @return The method returns the eventList.
   */

  public EventList getAllEvents(){
    eventList=new EventList();
    try(Connection connection=getConnection()) {
      PreparedStatement ps=connection.prepareStatement("select location,event.name,capacity,\"date\",g.name as guests,bG.name as BgName,m2.phoneNo as memberID from event\n"
          + "    join guestAttendingEvent gAE on event.name = gAE.eventName and event.date = gAE.eventDate\n"
          + "    join sep1.guest g on gAE.guestID = g.guestid\n"
          + "    join boardGameAtEvent bGAE on event.name = bGAE.eventName and event.date = bGAE.eventDate\n"
          + "    join boardGame bG on bG.gameID = bGAE.gameID\n"
          + "    join memberAttendingEvent mAE on event.name = mAE.eventName and event.date = mAE.eventDate\n"
          + "    join member m2 on mAE.memberID = m2.phoneNo;");

      ResultSet rs=ps.executeQuery();

      while (rs.next()){
        String name=rs.getString("name");
        String location=rs.getString("location");
        int capacity=rs.getInt("capacity");
        Date date=rs.getDate("date");
        String guests=rs.getString("guests");
        String tempgamename=rs.getString("BgName");
        String memberID=rs.getString("memberid");
        MemberList membertemp= memberList.getMembersByPhoneNumber(memberID);
        BoardGameList tempGame=boardGameList.getBoardGamesByName(tempgamename);
        MyDate tempdate=new MyDate(date.toLocalDate().getDayOfMonth(), date.toLocalDate().getMonthValue(),date.toLocalDate().getYear());
        //date.toLocalDate().
        eventList.addEvent(new Event(tempdate,location,name,guests,capacity,tempGame,membertemp));
      }
    }
    catch (SQLException e)
    {
      throw new RuntimeException(e);
    }
    /*try
    {
      eventList=(EventList) MyFileHandler.readFromBinaryFile(events);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
    catch (ClassNotFoundException e){
      System.out.println("Wrong class in file");
    }*/
    return eventList;
  }

  /**
   * It saves all the members from the MemberList.
   * It tries to write the memberList to a binary file called "members.bin"
   * @param memberList the variable to MemberList.
   */
  public void saveAllMembers(MemberList memberList){
    try
    {
      MyFileHandler.writeToBinaryFile("members.bin",memberList);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
  }

  /**
   * It saves all the board games from the BoardGameList.
   * The function takes a BoardGameList object which is boardGameList as a parameter and saves it to a binary file called "Boardgames.bin"
   * @param boardGameList the variable to BoardGameList.
   */
  public void saveAllBoardGames(BoardGameList boardGameList){
    try
    {
      MyFileHandler.writeToBinaryFile("Boardgames.bin",boardGameList);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
  }

  /**
   * It saves all the events from the EventList.
   * This function takes an EventList object which is eventList as a parameter and saves it to a binary file called events.bin
   * @param eventList the variable to EventList.
   */
  public void saveAllEvents(EventList eventList){
    try
    {
      MyFileHandler.writeToBinaryFile("events.bin",eventList);
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
    catch (IOException e){
      System.out.println("IO error when opening the file");
    }
  }

  private Connection getConnection() throws SQLException
  {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep1","postgres","password");
  }
}