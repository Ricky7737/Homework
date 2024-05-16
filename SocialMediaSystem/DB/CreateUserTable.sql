CREATE TABLE User (
                      UserID INT AUTO_INCREMENT PRIMARY KEY,
                      UserName VARCHAR(255) NOT NULL,
                      Email VARCHAR(255) NOT NULL,
                      Password VARCHAR(255) NOT NULL,
                      CoverImage VARCHAR(255),
                      Phone VARCHAR(20) UNIQUE
);
