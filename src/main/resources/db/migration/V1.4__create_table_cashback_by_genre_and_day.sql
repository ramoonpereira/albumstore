CREATE TABLE albumstore.cashback_by_genre_and_day (
  id INT NOT NULL AUTO_INCREMENT,
  genre VARCHAR(15) NOT NULL,
  day VARCHAR(15) NOT NULL,
  percent_cash_back DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (id)
);
