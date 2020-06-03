package rpis81.mironyuk.oop.model;

import java.time.LocalDate;

public class DebitAccount extends AbstractAccount {
    String number;
    double balance;



    public DebitAccount()
    {
        super();
    }

    public DebitAccount(String number, double balance,LocalDate expirationDate)
    {
        super(number, balance,expirationDate);
        if(balance<0)throw new IllegalArgumentException();
        if(balance>0)throw new IllegalArgumentException();
        if(number.length()!=20||number.substring(0,1)!="40") throw new InvalidAccountNumberException();
        if(number.substring(5,7)!="810")throw new IllegalArgumentException();

    }

    public DebitAccount(String number, double balance) {

    }

    @Override
    public String toString() {
        return String.format("Debit account - number: %s balance: %d",number,balance);
    }

    @Override
    public int hashCode() {
        return super.hashCode()*53;
    }

    protected Object clone()throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }

    LocalDate creationDate;
    LocalDate expirationDate;

    public LocalDate getCreationDate(){
        return creationDate;
    }
    public LocalDate getExpirationDate(){
        return expirationDate;
    }
    public void setExpirationDate(LocalDate expirationDate){
        if(expirationDate==null) throw new NullPointerException();
        if(expirationDate.isBefore(expirationDate))throw new java.lang.IllegalArgumentException();
        this.expirationDate=expirationDate;
    }
    public int monthesQuantityBeforeExpiration(){
        return expirationDate.minusYears(LocalDate.now().getYear()).minusMonths(LocalDate.now().getMonthValue()).getMonthValue();
    }
}