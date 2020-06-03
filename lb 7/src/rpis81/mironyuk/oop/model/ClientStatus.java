package rpis81.mironyuk.oop.model;

public enum ClientStatus {
    GOOD(0),
    GOLD(3),
    PLATINUM(-2),
    RISKY(-2),
    BAD(-4);

    ClientStatus(int i) {
    }
}