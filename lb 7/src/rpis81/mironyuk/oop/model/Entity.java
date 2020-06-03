package rpis81.mironyuk.oop.model;

import java.time.LocalDate;
import java.util.*;

public class Entity implements Client{
    DebitAccount[] accounts;
    int size=0;
    String name;
    int points;
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
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Account> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
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
        return accounts.length==0;
    }

    @Override
    public boolean contains(Object o) {
        for (DebitAccount acc:accounts){
            if(acc.equals(o))return true;
        }
        return false;
    }

    public  DebitAccount[] getAccounts()
    {
        return (DebitAccount[]) toArray();
    }

    public DebitAccount[] sortedAccountsByBalance()
    {
        DebitAccount[] accounts=getAccounts().clone();
        java.util.Arrays.sort(accounts);
        ArrayList<Account > acc= new ArrayList<>();
        acc.addAll(Arrays.asList(accounts));
        return acc;
    }

    public double totalBalance()
    {
        double sum=0.0;
        for ( DebitAccount account: getAccounts())
            sum+=account.getBalance();
        return sum;
    }
    public Entity( DebitAccount[] accounts,String name)
    {
        this.accounts=new  DebitAccount[accounts.length];
        for ( DebitAccount account: accounts) {
            add(new  DebitAccount(account.getNumber(),account.getBalance(), LocalDate.now()));
        }
        this.name=name;
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
        return new Entity(accounts,name);
    }

    @Override
    public Iterator<Account> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return accounts;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    class AccountIterator implements Iterator<Account>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Account next() {
            return null;
        }
    }
}