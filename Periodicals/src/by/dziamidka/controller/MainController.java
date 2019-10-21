package by.dziamidka.controller;

import by.dziamidka.entity.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
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
    private static TitleComparator titleComparator = new TitleComparator();
    private static YearComparator yearComparator = new YearComparator();

    private static class TitleComparator implements Comparator<Book> {
        public int compare(Book book1, Book book2){
            return (book1.getTitle().compareTo(book2.getTitle()));
        }
    }

    private static class YearComparator implements Comparator<Book>{
        @Override
        public int compare(Book book, Book t1) {
            return (book.getYearOfPublication().getYear() - t1.getYearOfPublication().getYear());
        }
    }

    private static Comparator getYearComparator() {
        return yearComparator;
    }

    private static Comparator getTitleComparator(){
        return titleComparator;
    }

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
                genres = new ArrayList<Genre>();
            }
        }
    }

    private void AuthorsInit(){
        if(!isAuthorsInit){
            try {
                FileInputStream fis = new FileInputStream("data/authors.per");
                ObjectInputStream oin = new ObjectInputStream(fis);
                authors = (ArrayList<Author>) oin.readObject();
                oin.close();
                isAuthorsInit = true;
            }catch (Exception e){
                System.out.println("Can't read file with authors");
                authors = new ArrayList<Author>();
            }
        }
    }

    private void PublishersInit(){
        if(!isPublishersInit){
            try {
                FileInputStream fis = new FileInputStream("data/publishers.per");
                ObjectInputStream oin = new ObjectInputStream(fis);
                publishers = (ArrayList<Publisher>) oin.readObject();
                oin.close();
                isPublishersInit = true;
            }catch (Exception e){
                System.out.println("Can't read file with publishers");
                publishers = new ArrayList<Publisher>();
            }
        }
    }

    private void DeleteFromFiles(Book book){
        GenreInit();
        AuthorsInit();
        PublishersInit();
        for (Genre genre: genres){
            if(genre.getBookGenre().equals(book.getGenre().getBookGenre())){
                ArrayList<Book> tmpBooks = genre.getBooks();
                tmpBooks.remove(book);
                genre.setBooks(tmpBooks);
                break;
            }
        }
        for (Author author: authors){
            if(book.getAuthor().equals(author)){
                ArrayList<Book> tmpBooks = author.getBooks();
                tmpBooks.remove(book);
                author.setBooks(tmpBooks);
                break;
            }
        }
        for (Publisher publisher: publishers){
            if(book.getPublisher().equals(publisher)){
                ArrayList<Book> tmpBooks = publisher.getBooks();
                tmpBooks.remove(book);
                publisher.setBooks(tmpBooks);
                break;
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

    private Author GetAuthor(String name, String surname) {
        AuthorsInit();
        for(Author author : authors){
            if(author.getName().equals(name)&&author.getSurname().equals(surname))
                return author;
        }
        Author newAuthor = new Author(name, surname);
        authors.add(newAuthor);
        return newAuthor;
    }

    private Publisher GetPublisher(String title){
        PublishersInit();
        for(Publisher publisher : publishers){
            if(publisher.getTitle().equals(title))
                return publisher;
        }
        Publisher newPublisher = new Publisher(title);
        publishers.add(newPublisher);
        return newPublisher;
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
                scan.nextLine();
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
                scan.nextLine();
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
            //System.out.println("Book is not added");
            return null;
        }
        Author author = GetAuthor(name, surname);
        return author;
    }

    private Publisher ChoosePublisher(){
        System.out.println("Enter publisher's name(not empty) or 0 to go back to library");
        //scan.nextLine();
        String name = scan.nextLine();
        if(name.equals("0") || name.equals("")){
            System.out.println("Publisher is not added");
            return null;
        }
        Publisher publisher = GetPublisher(name);
        System.out.println("Publishers name :"+ publisher.getTitle());
        return publisher;
    }

    private String ChooseBookTitle(){
        boolean isChosen = false;
        String title = null;
        while (!isChosen){
            System.out.println("Enter title of the book(not empty) or 0 to go back to library:");
            //scan.nextLine();
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

    private void ChangeBook(){
        boolean isWork = true;
        ArrayList<Book> books = curLib.getBooks();
        while (isWork){
            for(int i = 0; i < books.size(); i ++){
                System.out.println(i+1 + ". - Title '"+books.get(i).getTitle()+"'.  Author : '"+books.get(i).getAuthor().getSurname()+"'");
            }
            System.out.println("Choose number of book to change , 0 - to go back");
            if(scan.hasNextInt()){
                int state = scan.nextInt();
                scan.nextLine();
                if (state != 0){
                    if(state <= books.size() && state > 0) {
                        GetFieldsToChange(books.get(state - 1));
                        isWork = false;
                    }
                }else{
                    isWork = false;
                }
            }else {
                System.out.println("It's not a number, try again");
            }
        }
    }

    private void GetFieldsToChange(Book book){
        boolean isWork = true;
        while (isWork){
            System.out.println("0. Go back\n" +
                    "1. Change title\n" +
                    "2. Change author\n" +
                    "3. Change year\n" +
                    "4. Change publisher\n" +
                    "5. Change genre");
            if(scan.hasNextInt()){
                int state = scan.nextInt();
                scan.nextLine();
                BookController bc = new BookController(book);
                switch (state){
                    case 0:
                        isWork = false;
                        break;
                    case 1:
                        //bc = new BookController(book);
                        System.out.println("Write new title(not empty)");
                        //scan.nextLine();
                        String newTitle = scan.nextLine();
                        if(newTitle.equals("")){
                            System.out.println("String is empty, try again");
                        }else {
                            book = bc.ChangeTitle(newTitle);
                            System.out.println("New title : " + newTitle);
                        }
                        break;
                    case 2:
                        //bc = new BookController(book);
                        Author newAuthor = ChooseAuthor();
                        if(newAuthor != null){
                            book = bc.ChangeAuthor(newAuthor);
                            System.out.println("New author : " + newAuthor.getName());
                            ArrayList<Book> tmpAuthorsBook = newAuthor.getBooks();
                            tmpAuthorsBook.add(book);
                            newAuthor.setBooks(tmpAuthorsBook);
                        }
                        break;
                    case 3:
                        //bc = new BookController(book);
                        LocalDate newDate = ChooseYear();
                        if(newDate != null){
                            book = bc.ChangeYear(newDate);
                            System.out.println("New year of publication : " + newDate.getYear());
                        }
                        break;
                    case 4:
                        //bc = new BookController(book);
                        Publisher newPublisher = ChoosePublisher();
                        if(newPublisher != null){
                            book = bc.ChangePublisher(newPublisher);
                            System.out.println("New publisher : " + newPublisher.getTitle());
                            ArrayList<Book> tmpPublishersBook = newPublisher.getBooks();
                            tmpPublishersBook.add(book);
                            newPublisher.setBooks(tmpPublishersBook);
                        }
                        break;
                    case 5:
                        Genre newGenre = ChooseGenre();
                        if(newGenre != null){
                            book = bc.ChangeGenre(newGenre);
                            System.out.println("New Genre : "+ newGenre.getBookGenre().getValue());
                            ArrayList<Book> tmpGenreBook = newGenre.getBooks();
                            tmpGenreBook.add(book);
                            newGenre.setBooks(tmpGenreBook);
                        }
                        break;
                    default:
                        break;
                }
            }else {
                System.out.println("Not a number, try again");
            }
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
                    + DELETE_BOOK + ". Delete book\n" +
                    "5. Sort books by name\n" +
                    "6. Sort books by year" );

            if (scan.hasNextInt()){
                int state = scan.nextInt();
                scan.nextLine();
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
                        if(bookToAdd != null)
                            this.curLib = libController.AddBook(bookToAdd);
                        break;
                    case CHANGE_BOOK :
                        ChangeBook();
                        break;
                    case DELETE_BOOK :
                        Book bookToDelete = GetDeleteBook();
                        if (bookToDelete != null)
                            this.curLib = libController.DeleteBook(bookToDelete);
                        break;
                    case 5:
                        ArrayList<Book> tmpBooksByTitle = curLib.getBooks();
                        tmpBooksByTitle.sort(getTitleComparator());
                        curLib.setBooks(tmpBooksByTitle);
                        break;
                    case 6:
                        ArrayList<Book> tmpBooksByYear = curLib.getBooks();
                        tmpBooksByYear.sort(getYearComparator());
                        curLib.setBooks(tmpBooksByYear);
                        break;
                    default:
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
        FillListFields(newBook);
        return newBook;
    }

    private void FillListFields(Book book){
        ArrayList<Book> tmpAuthorsBooks = book.getAuthor().getBooks();
        tmpAuthorsBooks.add(book);
        book.getAuthor().setBooks(tmpAuthorsBooks);
        ArrayList<Book> tmpGenreBooks = book.getGenre().getBooks();
        tmpGenreBooks.add(book);
        book.getGenre().setBooks(tmpGenreBooks);
        ArrayList<Book> tmpPublishersBooks = book.getPublisher().getBooks();
        tmpPublishersBooks.add(book);
        book.getPublisher().setBooks(tmpPublishersBooks);
    }

    private Book GetDeleteBook(){
        System.out.println("Enter title of the book to delete(not empty) or 0 to go back to library: ");
        //scan.nextLine();
        String title = scan.nextLine();
        if(title.equals("0") || title.equals("")){
            System.out.println("Book is not deleted");
            return null;
        }
        System.out.println("Enter Author's surname of the book to delete(not empty) or 0 to go back to library: ");
        //scan.nextLine();
        String surname = scan.nextLine();
        if(surname.equals("0") || surname.equals("")){
            System.out.println("Book is not deleted");
            return null;
        }
        ArrayList<Book> books = curLib.getBooks();
        for(Book book : books){
            if(book.getAuthor().getSurname().equals(surname)&&book.getTitle().equals(title)){
                DeleteFromFiles(book);
                return book;
            }
        }
        System.out.println("Such book wasn't found");
        return null;
    }

    public void Start(){
        boolean isWork = true;
        scan = new Scanner(System.in);
        while(isWork){
            System.out.println("\\\\\\\\\\\\\\\\\\\\MAIN MENU//////////\n" +
                    EXIT + ". Exit\n" +
                    OPEN_LIBRARY + ". Open library\n" +
                    CREATE_LIBRARY + ". Create library");

            if(scan.hasNextInt()){
                int state = scan.nextInt();
                scan.nextLine();
                switch (state){
                    case EXIT:
                        isWork = false;
                        break;

                    case OPEN_LIBRARY:
                        boolean isRead = false;
                        while (!isRead){
                            System.out.println("Enter name of library, 0 - to go back:");
                            //scan.nextLine();
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
                            //scan.nextLine();
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
        SaveChanges();
        System.out.println("Files were saved");
}
}
