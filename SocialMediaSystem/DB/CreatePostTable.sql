CREATE TABLE Post (
                      PostID INT PRIMARY KEY,
                      UserID INT,
                      FOREIGN KEY (UserID) REFERENCES User(UserID),
                      Content TEXT,
                      Image VARCHAR(255),
                      PublishTime DATETIME
);



