package finalProject;

import java.text.DecimalFormat;

public class client {
private String name;
private int acct;
private double balance;
public client() {
	name = "null";
	acct = 0000;
	balance = 0.00;
}
public client(String n, int a, double b) {
	this.setName(n);
	this.setAcct(a);
	this.setBalance(b);
}
public void setName(String n) {
	if(n != null) {
		this.name = n;
	} else {
		this.name = "null";
	}
	
}
public String getName() {
	return this.name;
}
public void setAcct(int a) {
	if(a <= 9999 && a >= 1000) {
		this.acct = a;
	}else {
		this.acct = 0000;
	}
}
public int getAcct() {
	return this.acct;
}
public void setBalance(double b) {
	if(b > 0) {
		this.balance = b;
	}
}
public double getBalance() {
	return this.balance;
}
DecimalFormat money = new DecimalFormat("#,##0.00");
public String toString() {
	return "client's name: " + this.name +"\n"+ "acct#: " + this.acct + "\n" + "balance: " + "$" +money.format(this.balance);
}
}
