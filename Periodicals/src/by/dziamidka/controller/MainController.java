package by.dziamidka.controller;

import by.dziamidka.entity.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {

    private static final int EXIT = 0;
    private static final int OPEN_LIBRARY = 1;
    private static final int CREATE_LIBRARY = 2;
    private static final int SHOW_BOOKS = 1;
    private static final int ADD_BOOK = 2;
    private static final int CHANGE_BOOK = 3;
    private static final int DELETE_BOOK = 4;
    private Library curLib;
    private Scanner scan;
    private ArrayList<Publisher> publishers;
    private ArrayList<Genre> genres;
    private ArrayList<Author> authors;

    public MainController() {
    }

    public void WorkingWithLib(){
        boolean isWork = true;
        while (isWork){

            System.out.println("\\\\\\\\\\\\\\\\\\\\" +curLib.getTitle() + " library MENU//////////\n" +
                    + EXIT + ". Close Library\n" +
                    SHOW_BOOKS + ". Show books \n" +
                    + ADD_BOOK + ". Add book \n" +
                    + CHANGE_BOOK + ". Change book \n" +
                    + DELETE_BOOK + ". Delete book");

            if (scan.hasNextInt()){
                int state = scan.nextInt();
                System.out.println("State:"+ state +".");
                switch (state){
                    case EXIT:
                        isWork = false;
                        break;
                }
            }else {
                System.out.println("Sorry it is not a number, try again. \n");
                isWork =false;
            }
        }
    }

    public void Start()
    {
        boolean isWork = true;
        scan = new Scanner(System.in);
        while(isWork){
            System.out.println("\\\\\\\\\\\\\\\\\\\\MAIN MENU//////////\n" +
                    EXIT + ". Exit\n" +
                    OPEN_LIBRARY + ". Open library\n" +
                    CREATE_LIBRARY + ". Create library");

            if(scan.hasNextInt()){
                int state = scan.nextInt();
                switch (state){
                    case EXIT:
                        isWork = false;
                        break;

                    case OPEN_LIBRARY:
                        boolean isRead = false;
                        while (!isRead){
                            System.out.println("Enter name of library, 0 - to go back:");
                            scan.nextLine();
                            String name = scan.nextLine();
                            if(name.equals("0"))
                                break;
                            String path = "data/Libraries/" + name +".lib";
                            try {
                                FileInputStream fis = new FileInputStream(path);
                                ObjectInputStream oin = new ObjectInputStream(fis);
                                Library lib = (Library) oin.readObject();
                                this.curLib = lib;
                                oin.close();
                                isRead = true;
                                System.out.println(curLib.getTitle() + " MENU:");
                                WorkingWithLib();
                            }
                            catch (Exception e){
                                System.out.println(e.getMessage() + ". Can't read this path, try again");
                            }
                        }
                        break;

                    case CREATE_LIBRARY:
                        boolean isCreate = false;
                        while (!isCreate) {
                            System.out.println("Enter name of your library, 0 - go back:");
                            scan.nextLine();
                            String name = scan.nextLine();
                            if(name.equals("0"))
                                break;
                            try {
                                FileOutputStream fos = new FileOutputStream("data/Libraries/" + name + ".lib");
                                Library newLib = new Library(name);
                                ObjectOutputStream oos = new ObjectOutputStream(fos);
                                oos.writeObject(newLib);
                                oos.flush();
                                oos.close();
                                curLib = newLib;
                                isCreate = true;
                                System.out.println(name + " is created.");
                                WorkingWithLib();
                            } catch (Exception e) {
                                System.out.println(e.getMessage() + " Something go wrong, try again");
                            }
                        }
                        break;
                    default:
                        System.out.println("Wrong number, try again. \n");
                        break;
                }
            } else {
                System.out.println("Sorry it is not a number, try again. \n");
            }
        }
        scan.close();
    }
}
