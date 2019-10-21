package by.dziamidka;

import by.dziamidka.controller.MainController;
import by.dziamidka.entity.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class Test {
    public static void main(String[] args) throws Exception {
        MainController mc = new MainController();
        mc.Start();
        /*FileInputStream fis = new FileInputStream("data/Libraries/Ihar.lib");
        ObjectInputStream oin = new ObjectInputStream(fis);
        Library lib = (Library) oin.readObject();
        System.out.println(lib.toString());
        oin.close();*/
    }
}
