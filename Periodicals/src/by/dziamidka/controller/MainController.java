package by.dziamidka.controller;

import by.dziamidka.entity.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private Library curLib = null;
    private Scanner scan;
    private ArrayList<Publisher> publishers;
    private ArrayList<Genre> genres;
    private ArrayList<Author> authors;
    private boolean isAuthorsInit = false;
    private boolean isGenresInit = false;
    private boolean isPublishersInit = false;

    public MainController() {
    }

    private void SaveChanges(){
        if (this.curLib != null) {
            try {
                FileOutputStream fos = new FileOutputStream("data/Libraries/" + this.curLib.getTitle() + ".lib");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(this.curLib);
                oos.flush();
                oos.close();
            } catch (Exception e) {
                System.out.println("Can't save current library");
            }
        }
        if (isAuthorsInit) {
            try {
                FileOutputStream fos = new FileOutputStream("data/authors.per");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(authors);
                oos.flush();
                oos.close();
            } catch (Exception e) {
                System.out.println("Can't save authors to file");
            }
        }
        if (isPublishersInit){
            try {
                FileOutputStream fos = new FileOutputStream("data/publishers.per");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(publishers);
                oos.flush();
                oos.close();
            } catch (Exception e) {
                System.out.println("Can't save publishers to file");
            }
        }
        if (isGenresInit){
            try {
                FileOutputStream fos = new FileOutputStream("data/genres.per");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(genres);
                oos.flush();
                oos.close();
            } catch (Exception e) {
                System.out.println("Can't save genres to file");
            }
        }
    }

    private void GenreInit(){
        if(!isGenresInit){
            try {
                FileInputStream fis = new FileInputStream("data/genres.per");
                ObjectInputStream oin = new ObjectInputStream(fis);
                genres = (ArrayList<Genre>) oin.readObject();
                oin.close();
                isGenresInit = true;
            }catch (Exception e){
                System.out.println("Can't read file with genres");
            }
        }
    }

    private Genre GetGenre(BookGenre bookGenre){
        for (Genre genre : genres){
            if(genre.getBookGenre().equals(bookGenre))
                return genre;
        }
        return null;
    }

    private Genre ChooseGenre(){
        boolean isChosen = false;
        GenreInit();
        while (!isChosen){
            System.out.println("Enter number of your genre:\n" +
                    "0. EXIT \n" +
                    "1. fantasy \n" +
                    "2. science fiction \n" +
                    "3. western \n" +
                    "4. romance \n" +
                    "5. thriller \n" +
                    "6. mystery \n" +
                    "7. detective \n" +
                    "8. dystopia");

            if (scan.hasNextInt()){
                int state = scan.nextInt();
                switch (state){
                    case 0:
                        isChosen = true;
                        break;
                    case 1:
                        return GetGenre(BookGenre.FANTASY);
                    case 2:
                        return GetGenre(BookGenre.SCIENCE_FICTION);
                    case 3:
                        return GetGenre(BookGenre.WESTERN);
                    case 4:
                        return GetGenre(BookGenre.ROMANCE);
                    case 5:
                        return GetGenre(BookGenre.THRILLER);
                    case 6:
                        return GetGenre(BookGenre.MYSTERY);
                    case 7:
                        return GetGenre(BookGenre.DETECTIVE);
                    case 8:
                        return GetGenre(BookGenre.DYSTOPIA);
                    default:
                        System.out.println("Wrong number, try again");
                        break;
                }
            }else {
                System.out.println("Not a number, try again");
            }
        }
        return null;
    }

    private LocalDate ChooseYear(){
        System.out.println("Enter year of the book publication(not empty) or 0 to go back to library: ");
        boolean isChosen = false;
        while (!isChosen) {
            if (scan.hasNextInt()) {
                int year = scan.nextInt();
                if (year == 0)
                    return null;
                try {
                    return LocalDate.of(year, 1, 1);
                } catch (Exception e) {
                    System.out.println("Can't create data, incorrect");
                }
            } else {
                System.out.println("Sorry, incorrect data, try again");
                break;
            }
        }
        return null;
    }

    private Author ChooseAuthor(){
        System.out.println("Enter Author's name of the book(not empty) or 0 to go back to library: ");
        //scan.nextLine();
        String name = scan.nextLine();
        if(name.equals("0") || name.equals("")){
            System.out.println("Book is not added");
            return null;
        }
        System.out.println("Enter Author's surname of the book(not empty) or 0 to go back to library: ");
        //scan.nextLine();
        String surname = scan.nextLine();
        if(surname.equals("0") || surname.equals("")){
            System.out.println("Book is not added");
            return null;
        }
        Author author;
        try {
            author = GetAuthor(name, surname);
        }
        catch (Exception e){
            System.out.println("Can't read authors file");
            return null;
        }
        return author;
    }

    private Publisher ChoosePublisher(){
        System.out.println("Enter publisher's name(not empty) or 0 to go back to library");
        scan.nextLine();
        String name = scan.nextLine();
        if(name.equals("0") || name.equals("")){
            System.out.println("Publisher is not added");
            return null;
        }
        Publisher publisher;
        try {
            publisher = GetPublisher(name);
        }
        catch (Exception e){
            System.out.println("Can't read publishers file");
            return null;
        }
        System.out.println("Publishers name :"+ publisher.getTitle());
        return publisher;
    }

    private String ChooseBookTitle(){
        boolean isChosen = false;
        String title = null;
        while (!isChosen){
            System.out.println("Enter title of the book(not empty) or 0 to go back to library:");
            scan.nextLine();
            title = scan.nextLine();
            isChosen = true;
            if(title.equals("0")){
                System.out.println("Book is not added");
                return null;
            }
            if(title.equals("")){
                System.out.println("Title is empty, try again");
                isChosen = false;
            }
        }
        return title;
    }

    private Author GetAuthor(String name, String surname) throws IOException, ClassNotFoundException {
        if (isAuthorsInit){
            for(Author author : authors){
                if(author.getName().equals(name)&&author.getSurname().equals(surname))
                    return author;
            }
            Author newAuthor = new Author(name, surname);
            authors.add(newAuthor);
            return newAuthor;
        }else {
            FileInputStream fis = new FileInputStream("data/authors.per");
            ObjectInputStream oin = new ObjectInputStream(fis);
            authors = (ArrayList<Author>) oin.readObject();
            oin.close();
            isAuthorsInit = true;
            return GetAuthor(name,surname);
        }
    }

    private Publisher GetPublisher(String title) throws IOException, ClassNotFoundException {
        if (isPublishersInit){
            for(Publisher publisher : publishers){
                if(publisher.getTitle().equals(title))
                    return publisher;
            }
            Publisher newPublisher = new Publisher(title);
            publishers.add(newPublisher);
            return newPublisher;
        }else {
            FileInputStream fis = new FileInputStream("data/publishers.per");
            ObjectInputStream oin = new ObjectInputStream(fis);
            publishers = (ArrayList<Publisher>) oin.readObject();
            oin.close();
            isPublishersInit = true;
            return GetPublisher(title);
        }
    }

    private void WorkingWithLib(){
        boolean isWork = true;
        while (isWork){
            System.out.printf("Creation date: %s \n", curLib.getCreationDate().format(DateTimeFormatter.ofPattern("dd LLLL yyyy")));
            System.out.println("Size: " + curLib.getBooks().size() +" books \n" +
                    "\\\\\\\\\\\\\\\\\\\\" + curLib.getTitle() + " library MENU//////////\n" +
                    + EXIT + ". Close Library\n" +
                    + SHOW_BOOKS + ". Show books \n" +
                    + ADD_BOOK + ". Add book \n" +
                    + CHANGE_BOOK + ". Change book \n" +
                    + DELETE_BOOK + ". Delete book");

            if (scan.hasNextInt()){
                int state = scan.nextInt();
                LibraryController libController = new LibraryController(this.curLib);
                switch (state){
                    case EXIT:
                        isWork = false;
                        break;
                    case SHOW_BOOKS :
                        libController.ShowBooks();
                        break;
                    case ADD_BOOK :
                        Book bookToAdd = GetBookInfo();
                        this.curLib = libController.AddBook(bookToAdd);

                        break;
                    case CHANGE_BOOK :
                        break;
                    case DELETE_BOOK :
                        break;
                }
            }else {
                System.out.println("Sorry it is not a number, try again. \n");
                isWork =false;
            }
        }
    }

    private Book GetBookInfo(){
        String title = ChooseBookTitle();
        if(title == null)
            return null;
        Author author = ChooseAuthor();
        if(author == null)
            return null;
        LocalDate date = ChooseYear();
        if(date == null)
            return null;
        Genre genre = ChooseGenre();
        if (genre == null){
            System.out.println("Can't choose genre");
            return null;
        }
        Publisher publisher = ChoosePublisher();
        if(publisher == null)
            return null;
        Book newBook = new Book(title, author, date, publisher, genre);
        ArrayList<Book> tmpAuthorsBooks = author.getBooks();
        tmpAuthorsBooks.add(newBook);
        author.setBooks(tmpAuthorsBooks);
        ArrayList<Book> tmpGenreBooks = genre.getBooks();
        tmpGenreBooks.add(newBook);
        genre.setBooks(tmpGenreBooks);
        ArrayList<Book> tmpPublishersBooks = publisher.getBooks();
        tmpPublishersBooks.add(newBook);
        publisher.setBooks(tmpPublishersBooks);
        return newBook;
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

                            }
                            catch (Exception e){
                                System.out.println(e.getMessage() + ". Can't read this path, try again");
                            }
                            WorkingWithLib();
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
        ///////////SAVE FILES/////////////////
        System.out.println("Don't forget to save changes");
        SaveChanges();
    }
}
