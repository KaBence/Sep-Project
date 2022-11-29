package Model;

import Util.MyFileHandler;

import java.io.IOException;

public class BoardGameClub
{
  private String filename;

  public BoardGameClub(String filename){
    this.filename=filename;
  }

  public void addMemberToClub(Member member) {
    MemberList list  = new MemberList();
    list.addMember(member);

    try {
      MyFileHandler.writeToBinaryFile(filename, list);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
