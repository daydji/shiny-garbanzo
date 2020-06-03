package rpis81.mironyuk.oop.model;

import rpis81.mironyuk.oop.model.*;

import java.time.LocalDate;

public class Test {
    public static void main (String[] args) {
        System.out.println("Я сделяль!");

    }

    public static void lab1tests(){
        DebitAccount[] accounts=new DebitAccount[3];
        accounts[0]=new DebitAccount();
        accounts[1]=new DebitAccount("464568",5266.01);
        accounts[2]=new DebitAccount("554589",165489.68);
        System.out.println("Вывод до изменений");
        for (DebitAccount account:  accounts) {
            System.out.format("Номер счета: %s \nТекущий баланс: %s\n\n",account.getNumber(),account.getBalance());
        }

        accounts[0].setNumber("558956");
        accounts[1].setBalance(55635.12);
        accounts[2].setBalance(accounts[2].getBalance()-100000);

        System.out.println("Вывод после изменений");
        for (DebitAccount account:  accounts) {
            System.out.format("Номер счета: %s \nТекущий баланс: %s\n\n",account.getNumber(),account.getBalance());

        }
        Individual[] individuals=new Individual[3];
        //   individuals[0]=new Individual();
        //  individuals[1]=new Individual(3);
        //  individuals[2]=new Individual(accounts);

        for (DebitAccount account:  individuals[2].getAccounts()) {
            System.out.format("Номер счета: %s \nТекущий баланс: %s\n\n",account.getNumber(),account.getBalance());

        }
        individuals[2].add(new DebitAccount("565659",56.0));
        individuals[2].add(9,new DebitAccount("565656",56.0));
        individuals[2].add(10,new DebitAccount("565656",56.0));
        individuals[2].add(new DebitAccount("565658",56.0));
        individuals[2].remove(10);
        individuals[2].remove("565658");
        for (DebitAccount account:  individuals[2].sortedAccountsByBalance()) {
            System.out.format("Номер счета: %s \nТекущий баланс: %s\n\n",account.getNumber(),account.getBalance());

        }
        System.out.format("Суммарный счет: %s\n",individuals[2].totalBalance());

        AccountManager[] accountManagers=new AccountManager[2];
        accountManagers[0]=new AccountManager(13);
        accountManagers[1]=new AccountManager(individuals);




        for (Account account:  accountManagers[1].get(2).getAccounts()) {
            System.out.format("Номер счета: %s \nТекущий баланс: %s\n\n",account.getNumber(),account.getBalance());

        }
    }

    public static void lab2tests()
    {

    }

    public static void lab3tests(){
        AccountManager manager=new AccountManager(3);
        manager.set(0, new Individual(1,"who") );
        manager.set(1, new Individual(1,"who") );
        manager.get(1).increase(-10);
        manager.set(2, new Individual(1,"who") );
        manager.get(2).increase(10);
        System.out.println(manager.getCreditedClients());
        System.out.println(manager.getBadClients());
    }

    public static void lab4tests()
    {
        AccountManager manager=new AccountManager(3);
        manager.set(0, new Individual(1,"who") );
        manager.set(1, new Individual(1,"who") );
        manager.get(1).increase(-10);
        manager.set(2, new Individual(1,"who") );
        manager.get(2).increase(10);
        System.out.println(manager.toString());
    }

    public static void lab5tests()
    {
        DebitAccount acc=new DebitAccount("123124234235325345",0,  LocalDate.now());
        CreditAccount acc1=new CreditAccount("123124234235325345", -234,2, LocalDate.now().plusMonths(42));
        System.out.format("Pay %d at %d", acc1.nextPayment(),acc1.nextPaymentDate());
    }

    public static void lab6tests()
    {
        lab1tests();//потому что переработаны методы созданные в 1 лабораторной
    }

    public static void lab7tests()
    {
        lab4tests();//потому что переработаны методы созданные в 4 лабораторной
    }
}

