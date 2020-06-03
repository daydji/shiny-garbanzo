package rpis81.mironyuk.oop.model;

import java.util.*;

public class AccountManager implements Iterable<Client> {
    Client[] individuals;
    int size;

    public boolean add(Individual individual) {

        for (int i = 0; i < individuals.length; i++)
            if (individuals[i] == null) {
                individuals[i] = individual;
                size = size <= i ? i + 1 : size;
                return true;
            }
        Client[] individuals1 = individuals;
        individuals = new Client[individuals1.length * 2];
        for (int i = 0; i < size; i++)
            add(i, individuals1[i]);
        return add(individual);

    }

    public boolean add(int index, Client individual) {
        if (index < 0) throw new IndexOutOfBoundsException();
        if (index >= individuals.length) {
            Client[] individuals1 = individuals;
            individuals = new Individual[individuals.length * 2];
            for (int i = 0; i < size; i++)
                add(i, individuals1[i]);
            add(index, individual);
        } else set(index, individual);

        if (index >= size)
            size = index + 1;
        return true;
    }

    public Client get(int index) {
        return individuals[index];
    }

    public Client set(int index, Client individual) {
        if (index < 0 || individuals.length < index) throw new IndexOutOfBoundsException();
        Client individ = individuals[index];
        individuals[index] = individual;
        return individ;
    }

    public Client remove(int index) {
        if (index < 0 || individuals.length < index) throw new IndexOutOfBoundsException();
        Client individ = individuals[index];
        for (; index < size - 1; index++)
            individuals[index] = individuals[index + 1];
        individuals[index] = null;
        return individ;
    }

    public int size() {
        int size = 0;
        for (Client individual : individuals)
            if (individual != null) size++;
        return size;
    }

    public Client[] getClients() {
        Client[] clients = new Client[size()];
        int i = 0;
        for (Client individual : this.individuals)
            if (individual != null)
                clients[i++] = individual;
        return clients;
    }

    public Collection<Credit> sortedByBalanceClientss() {
        Client[] individuals = getClients().clone();
        Arrays.sort(individuals);
        LinkedList<Credit> list = new LinkedList<>();
        for (Client client : individuals) {
            list.add((Credit) client);
        }
        return list;
    }

    public DebitAccount getAccount(String accountNumber) {
        for (Client individual : individuals)
            if (individual.hasAccount(accountNumber))
                return (DebitAccount) individual.get(accountNumber);
        return null;
    }

    public DebitAccount removeAccount(String accountNumber) {
        for (Client individual : individuals)
            if (individual.hasAccount(accountNumber))
                return (DebitAccount) individual.remove(accountNumber);
        return null;
    }

    public Account setAccount(String accountNumber, DebitAccount account) {
        for (Client individual : individuals)
            if (individual.hasAccount(accountNumber)) {
                int i = 0;
                for (Account account1 : individual.getAccounts())
                    if (account1.getNumber().equals(accountNumber)) {
                        return individual.set(i, account);
                    } else i++;
            }

        return null;
    }

    public AccountManager(int quantity) {
        individuals = new Client[quantity];
    }

    public AccountManager(Client[] individuals) {
        this.individuals = individuals.clone();
    }

    public Set<Client> getCreditedClients() {
        List<Client> clients = Arrays.asList(individuals);
        for (Client client : clients) {
            if (client.getCredits().length == 0) clients.remove(client);
        }
        HashSet<Client> hashSet = new HashSet<>(clients);
        return hashSet;
    }

    public Client[] getBadClients() {
        HashSet<Client> clients = (HashSet<Client>) getCreditedClients();
        for (Client client : clients) {
            if (!client.getClientStatus().equals(ClientStatus.BAD)) clients.remove(client);
        }
        return clients.toArray(new Client[0]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Client account : getClients()) {
            builder.append(account.toString());
            builder.append('\n');
        }
        return builder.toString();
    }

    @Override
    public Iterator<Client> iterator() {
        return null;
    }
}