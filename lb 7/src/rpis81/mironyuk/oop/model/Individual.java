package rpis81.mironyuk.oop.model;

import java.util.Collection;
import java.util.Iterator;

public class Individual implements Client{
    DebitAccount[] accounts;
    ClientStatus status;
    int size=0;
    int points;
    String name;
    public boolean add( DebitAccount account)
    {

        for (int i=0;i<accounts.length;i++)
            if(accounts[i]==null)
            {
                accounts[i]=account;
                size=size<=i?i+1:size;
                return true;
            }
        DebitAccount[] accounts1=accounts;
        accounts=new  DebitAccount[accounts1.length*2];
        for (int i=0;i<size;i++)
            add(i,accounts1[i]);
        return add(account);

    }

    public boolean add(int index,  DebitAccount account){
        if(index>=accounts.length){
            DebitAccount[] accounts1=accounts;
            accounts=new  DebitAccount[accounts1.length*2];
            for (int i=0;i<size;i++)
                add(i,accounts1[i]);
            add(index,account);}
        else set(index,account);

        if (index>=size)
            size=index+1;
        return true;
    }

    @Override
    public boolean add(Account account) {
        return add((DebitAccount)account );
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Account> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean add(int index, Account account) {
        return add(index,(DebitAccount)account );
    }

    public  DebitAccount get(int index)
    {
        return accounts[index];
    }

    public  DebitAccount get(String accountNumber)
    {
        for ( DebitAccount account: accounts) {
            if(accountNumber.equals(account.getNumber()))
                return account;

        }
        return null;
    }

    public boolean hasAccount(String accountNumber)
    {
        for ( DebitAccount account: accounts) {
            if(accountNumber.equals(account.getNumber()))
                return true;

        }
        return false;
    }

    @Override
    public Account set(int index, Account account) {
        return set(index,(DebitAccount)account );
    }

    public  DebitAccount set(int index,  DebitAccount account)
    {
        DebitAccount deletedAccount=get(index);
        accounts[index]=account;
        return deletedAccount;
    }

    public  DebitAccount remove(int index)
    {
        DebitAccount deletedAccount=get(index);
        for (int i=index;i<size-1;i++)
            accounts[i]=accounts[i+1];
        accounts[size-1]=null;
        size--;
        return deletedAccount;
    }

    public  DebitAccount remove(String accountNumber)
    {
        for (int i=0;i<size;i++) {
            if(accountNumber.equals(accounts[i].getNumber()))
            {
                return remove(i);
            }
        }
        return null;
    }

    public int size()
    {
        int size=0;
        for( DebitAccount account:accounts)
            if(account!=null)size++;
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    public  DebitAccount[] getAccounts()
    {
        DebitAccount[] accounts=new  DebitAccount[size()];
        int i=0;
        for ( DebitAccount account:this.accounts)
            if(account!=null)
                accounts[i++]=account;
        return accounts;
    }

    public DebitAccount[] sortedAccountsByBalance()
    {
        DebitAccount[] accounts=getAccounts().clone();
        DebitAccount tmp;
        for (int i=0,k,f;i<accounts.length-1;i++){//Selection sort
            for( k=i+1, f=i;k<accounts.length;k++)
                if(accounts[f].getBalance()<accounts[k].getBalance())
                    f=k;
            tmp=accounts[f];
            accounts[f]=accounts[i];
            accounts[i]=tmp;
        }
        return accounts;
    }

    public double totalBalance()
    {
        double sum=0.0;
        for ( DebitAccount account: getAccounts())
            sum+=account.getBalance();
        return sum;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public int getClientPoints() {

        return points ;
    }

    @Override
    public void increase(int points) {
        this.points+=points;
    }

    @Override
    public Account[] getCredits() {
        return  accounts;
    }

    public Individual(DebitAccount[] name)
    {
        accounts=new  DebitAccount[16];
    }

    public Individual(int length,String name)
    {
        accounts=new  DebitAccount[length];
        this.name=name;
    }

    public Individual( DebitAccount[] accounts,String name)
    {
        this.accounts=new  DebitAccount[accounts.length];
        for ( DebitAccount account: accounts) {
            add(new  DebitAccount(account.getNumber(),account.getBalance()));
        }
        this.name=name;
    }

    @Override
    public String toString() {
        String s= String.format("Client\nname:%s\ncreditScore: %s\n");

        for (DebitAccount account:accounts) {
            s+=account.toString()+'\n';
        }
        return String.format("%stotal: %d",s,totalBalance());
    }

    @Override
    public int hashCode() {
        int hash=0;
        for (DebitAccount account:accounts) {
            hash^=account.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        for (int i=0;i<accounts.length;i++) {
            if(!accounts[i].equals(((DebitAccount[]) obj)[i]))return false;
        }
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Individual(accounts,name);
    }

    @Override
    public Iterator<Account> iterator() {
        return null;
    }
}

