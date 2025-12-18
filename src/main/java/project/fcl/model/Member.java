package project.fcl.model;

public class Member {

    private char memberType;
    private int memberId;
    private String name;
    private double fee;
    private int club;

    public Member(char memberType, int memberId, String name, double fee) {
        this.memberType = memberType;
        this.memberId = memberId;
        this.name = name;
        this.fee = fee;
    }

    public char getMemberType() {
        return memberType;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public double getFee() {
        return fee;
    }

    public void setMemberType(char memberType){
        this.memberType = memberType;
    }

    public void setMemberId(int memberId){
        this.memberId = memberId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString(){
        String memType = Character.toString(memberType);
        String memId = Integer.toString(memberId);
        String memFee = Double.toString(fee);

        return "Type: " + memType + "\n" + "ID: " + memId + "\n" + "Name: " + name + "\n" + "Fee: " + memFee + "\n";
    }

    public String toCSV(){
        return memberType + "," + memberId + "," + name + "," + fee + "," + club;
    }
}
