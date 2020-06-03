package rpis81.mironyuk.oop.model;

import java.time.LocalDate;

public abstract class AbstractAccount implements Account, Cloneable{
    protected String number;
    protected double balance;

    public String getNumber()
    {
        return number;
    }

    public void setNumber(String number)
    {
        if(balance>0)throw new IllegalArgumentException();
        if(number.length()!=20||number.substring(0,1)!="44"||number.substring(0,1)!="45"||number.substring(0,1)!="40") throw new InvalidAccountNumberException();
        if(number.substring(5,7)!="810")throw new IllegalArgumentException();

        this.number=number;
    }

    public double getBalance()
    {
        return balance;
    }
    public void setBalance(double balance)
    {
        this.balance=balance;
    }

    public AbstractAccount()
    {
        number="";
        balance=0;
        creationDate=LocalDate.now();
    }

    public AbstractAccount(String number, double balance,LocalDate expirationDate)
    {
        this.number=number;
        this.balance=balance;
        if(expirationDate==null) throw new NullPointerException();
        if(expirationDate.isBefore(expirationDate))throw new java.lang.IllegalArgumentException();
        this.expirationDate=expirationDate;
        creationDate=LocalDate.now();
        if(balance>0)throw new IllegalArgumentException();
        if(number.length()!=20||number.substring(0,1)!="44"||number.substring(0,1)!="45"||number.substring(0,1)!="40") throw new InvalidAccountNumberException();
        if(number.substring(5,7)!="810")throw new IllegalArgumentException();

    }

    @Override
    public String toString()
    {
        return String.format("number: %s balance %d",number, balance);
    }

    @Override
    public int hashCode() {
        return (int) (number.hashCode()*balance);
    }

    @Override
    public boolean equals(Object obj) {
        return number.equals(((AbstractAccount)obj).number)&&balance==((AbstractAccount)obj).balance
                &&expirationDate.equals(((AbstractAccount)obj).expirationDate)&&creationDate.equals(((AbstractAccount)obj).creationDate);
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

    @Override
    public int compareTo(Account o) {
        return (int) (o.getBalance()-this.getBalance());
    }
}