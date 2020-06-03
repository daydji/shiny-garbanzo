package rpis81.mironyuk.oop.model;

import java.time.LocalDate;

public class CreditAccount extends AbstractAccount implements Credit {
    double AnnualPercentageRate=0;

    public double getAnnualPercentageRate() {
        return AnnualPercentageRate;
    }

    public void setAnnualPercentageRate(double AnnualPercentageRate) {
        this.AnnualPercentageRate=AnnualPercentageRate;
    }

    @Override
    public double nextPayment() {
        return balance*(1+AnnualPercentageRate*
                (monthesQuantityBeforeExpiration()/12.0))/monthesQuantityBeforeExpiration();
    }

    @Override
    public LocalDate nextPaymentDate() {
        LocalDate now=LocalDate.now();
        if(now.getDayOfMonth()<25)
            now.plusDays(25-now.getDayOfMonth());
        else now.minusDays(now.getDayOfMonth()-25).plusMonths(1);
        return now;
    }

    public CreditAccount()
    {
        AnnualPercentageRate=30;
    }

    public CreditAccount(String number, double balance, double AnnualPercentageRate, LocalDate expirationDate)
    {       super(number, balance,expirationDate);
        if(balance>0)throw new IllegalArgumentException();
        if(number.length()!=20||number.substring(0,1)!="44"||number.substring(0,1)!="45") throw new InvalidAccountNumberException();
        if(number.substring(5,7)!="810")throw new IllegalArgumentException();
        this.AnnualPercentageRate=AnnualPercentageRate;
    }

    @Override
    public String toString() {
        return String.format("Credit account - number: %s balance: %d APR: %d",number,balance, AnnualPercentageRate);
    }

    @Override
    public int hashCode() {
        return super.hashCode()*71;
    }

    protected Object clone()throws CloneNotSupportedException{
        throw new CloneNotSupportedException();
    }

    @Override
    public boolean equals(Object obj) {
        return number.equals(((CreditAccount)obj).number)&&balance==((CreditAccount)obj).balance&&AnnualPercentageRate==((CreditAccount)obj).AnnualPercentageRate;
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