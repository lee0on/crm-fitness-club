package project.fcl.service;

import project.fcl.model.Member;
import project.fcl.model.MultiClubMember;
import project.fcl.model.SingleClubMember;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

  private int getIntInput() {
    int choice = 0;

    while (choice == 0) {
        try {
            choice = reader.nextInt();
            reader.nextLine(); // чистим \n
        } catch (InputMismatchException e) {
            System.out.println("ERROR: INVALID INPUT. Please try again:");
            reader.nextLine();
            choice = 0;         
        }
    }

    return choice;
}

    private void printClubOptions(){
        System.out.println("1) Club Mercury" + "\n" + "2) Club Neptune" + "\n" + "3) Club Jupiter" + "\n" + "4) Multi Clubs");
    }

    public int getChoice(){
        int choice;
        System.out.println("WELCOME TO OZONE FITNESS CENTER" + "\n" + "===============================" + "\n" + "1) Add Member" + "\n" + "2) Remove Member" + "\n" + "3) Display Member Information" + "\n" + "Please select an option (or Enter -1 to quit)");
        choice = getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> m){
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> calculator;

        System.out.println("Enter visitor's name");
        name = reader.nextLine();
        printClubOptions();
        System.out.println("Enter club ID");
        club = getIntInput();
        while (club < 1 || club > 4){
            System.out.println("Invalid ID, enter new ID");
            club = getIntInput();
        }
        if (!m.isEmpty()){
            memberID = m.getLast().getMemberId() + 1;
        } else memberID = 1;
        if (club != 4){
            calculator = (n) -> switch (n) {
                case 1 -> 900;
                case 2 -> 950;
                case 3 -> 1000;
                default -> -1;
            };
            fees = calculator.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            m.add(mbr);
            mem = mbr.toCSV();
            System.out.println("STATUS: Single Club Member added");
        } else {
            calculator = (n) -> switch (n) {
                case 4 -> 1200;
                default -> -1;
            };
            fees = calculator.calculateFees(club);
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            m.add(mbr);
            mem = mbr.toCSV();
            System.out.println("STATUS: Multi Club Member added");
        }
        return mem;
    }

    public void removeMember(LinkedList<Member> m){
        System.out.println("Enter visitor's ID");
        int memberID = getIntInput();
        for (int i = 0; i < m.size(); i++){
            if (m.get(i).getMemberId() == memberID){
                m.remove(i);
                System.out.println("Visitor was deleted");
                return;
            }
        }
        System.out.println("Visitor was not found");
    }

    public String printMemberInfo(LinkedList<Member> m, int input){
        int memberID = input;
        for (int i = 0; i < m.size(); i++){
            if (m.get(i).getMemberId() == memberID){
                String[] memberInfo = m.get(i).toString().split(",");
                return (String.join("\n", memberInfo) + "\n");
            }
        }
        return ("Visitor was not found" + "\n");
    }
}
