package project.fcl.app;

import project.fcl.model.Member;
import project.fcl.service.FileHandler;
import project.fcl.service.MembershipManagement;

import java.util.LinkedList;

public class ConsoleApp {
    public static void main(String[] args) throws Exception {
        String mem;

        MembershipManagement membershipManagement = new MembershipManagement();
        FileHandler fileHandler = new FileHandler();

        LinkedList<Member> members = fileHandler.readFile();
        int choice = membershipManagement.getChoice();

        while (choice != -1){
            switch (choice){
                case 1 -> {
                    mem = membershipManagement.addMembers(members);
                    fileHandler.appendFile(mem);
                }
                case 2 -> {
                    membershipManagement.removeMember(members);
                    fileHandler.overwriteFile(members);
                }
                case 3 -> membershipManagement.printMemberInfo(members, 1);
                default -> System.out.println("Invalid input");
            }
            choice = membershipManagement.getChoice();
        }
    }
}
