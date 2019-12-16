package by.dziamidka.exception;

public class DatabaseException extends Exception{
    public DatabaseException(String message) {
        super("Uvazhemyi, oshibka " + message);
    }
}
