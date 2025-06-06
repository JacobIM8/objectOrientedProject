package finalProject;

public class premiumMember extends client {
private boolean isMember;
public premiumMember() {
	super();
	isMember = false;
}
public premiumMember(String n, int a, double  b, boolean i) {
	super(n,a,b);
	setMember(i);
}
public void setMember(boolean i) {
	this.isMember = i;
}
public boolean getMember() {
	return this.isMember;
}
public String toString() {
    String result = super.toString();
    if (isMember) {
        result += "\n" + super.getName() + " is a premium member.";
    } else {
        result += "\n" + super.getName() + " is NOT a premium member.";
    }
    return result;
}
}
