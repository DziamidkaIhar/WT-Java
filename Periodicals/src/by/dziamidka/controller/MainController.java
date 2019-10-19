package by.dziamidka.controller;

import by.dziamidka.entity.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Scanner;

public class MainController {

    private static final int EXIT = 0;
    private static final int OPEN_LIBRARY = 1;
    private static final int CREATE_LIBRARY = 2;
    private Library curLib;

    public MainController() {
    }

    public void Start()
    {
        boolean isWork = true;
        Scanner scan = new Scanner(System.in);
        while(isWork){
            System.out.println(EXIT + ". Exit\n" +
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
                            Scanner openScan = new Scanner(System.in);
                            String name = openScan.nextLine();
                            openScan.close();
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
                                isWork = false;
                            }
                            catch (Exception e){
                                System.out.println("Can't read this path, try again");
                            }
                        }
                        break;

                    case CREATE_LIBRARY:
                        boolean isCreate = false;
                        while (!isCreate) {
                            System.out.println("Enter name of your library, 0 - go back:");
                            Scanner createScan = new Scanner(System.in);
                            String name = createScan.nextLine();
                            createScan.close();
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
                                isWork = false;
                            } catch (Exception e) {
                                System.out.println("Something go wrong, try again");
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
