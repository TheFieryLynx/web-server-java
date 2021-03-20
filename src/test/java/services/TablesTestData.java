package services;

import entities.Client;
import entities.Film;
import entities.Order;

public class TablesTestData {
    public static final Client[] clients = {
            new Client("Лупа", "99-99", false),
            new Client("Пупа", "66-66", false)
    };

    public static final Film[] films = {
            new Film("The Lord of the Rings 1", "Barrie M. Osborne", 2001,
                    22, 33, 11,
                    12, 101, 102, false),
            new Film("The Lord of the Rings 2", "Barrie M. Osborne", 2002,
                    22, 33, 11,
                    12, 101, 102, false),
            new Film("The Lord of the Rings 3", "Barrie M. Osborne", 2003,
                    22, 33, 11,
                    12, 101, 102, false)
    };

    public static final Order[] orders = {
            new Order(2, 1, "cassette", 333,
                    java.sql.Date.valueOf("2011-01-01"), java.sql.Date.valueOf("2011-02-01")),
            new Order(2, 2, "cassette", 333,
                    java.sql.Date.valueOf("2012-01-01"), java.sql.Date.valueOf("2012-02-01")),
            new Order(2, 3, "disk", 222,
                    java.sql.Date.valueOf("2013-01-01"), java.sql.Date.valueOf("2013-02-01")),
    };
}
