USE master
GO
IF NOT EXISTS (
   SELECT name
   FROM sys.databases
   WHERE name = N'Periodicals'
)
CREATE DATABASE Periodicals;
GO
USE [Periodicals]


CREATE TABLE Libraries
(
	LibraryId INT PRIMARY KEY NOT NULL,
	Title VARCHAR(255),
	CreationDate VARCHAR(255)
);


CREATE TABLE Genres
(
	GenreId INT NOT NULL PRIMARY KEY,
	title VARCHAR(255)
);

CREATE TABLE Authors
(
	AuthorId INT NOT NULL PRIMARY KEY,
	name VARCHAR(255),
	surname VARCHAR(255)
);

CREATE TABLE Publishers
(
	PublisherId INT NOT NULL PRIMARY KEY,
	title VARCHAR(255)
);


CREATE TABLE Books
(
	BookId INT NOT NULL PRIMARY KEY,
	AuthorId INT NOT NULL,
	YearOfPublication VARCHAR(255),
	PublisherId INT NOT NULL,
	GenreId INT NOT NULL,
	CONSTRAINT FK_Book_Author
		FOREIGN KEY (AuthorId)
		REFERENCES Authors(AuthorId)
		ON DELETE CASCADE,
	CONSTRAINT FK_Book_Publisher_Id
		FOREIGN KEY (PublisherID)
		REFERENCES Publishers(PublisherId)
		ON DELETE CASCADE,
	CONSTRAINT FK_Book_Genre_Id
		FOREIGN KEY (GenreId)
		REFERENCES Genres(GenreId)
		ON DELETE CASCADE
);

CREATE TABLE BooksInLibrary
(
	BookId INT NOT NULL UNIQUE,
	LibraryId INT NOT NULL,
	CONSTRAINT FK_Book_Library_Id
		FOREIGN KEY (BookId)
		REFERENCES Books(BookId)
		ON DELETE CASCADE,
	CONSTRAINT FK_Library_Book_Id
		FOREIGN KEY (LibraryId)
		REFERENCES Libraries(LibraryId)
		ON DELETE CASCADE
);