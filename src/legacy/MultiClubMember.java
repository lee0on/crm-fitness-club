package javaproject;

public class MultiClubMember extends Member {

    private int membershipPoints;

    public MultiClubMember(char memberType, int memberId, String name, double fee, int membershipPoints) {
        super(memberType, memberId, name, fee);
        this.membershipPoints = membershipPoints;
    }

    public int getMembershipPoints() {
        return membershipPoints;
    }

    public void setMembershipPoints(int membershipPoints){
        this.membershipPoints = membershipPoints;
    }

    @Override
    public String toString(){
        return super.toString() + "Membership points: " + membershipPoints;
    }
}
