package services;

import entities.Film;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PrepareDatabase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FilmServiceTest {
    private FilmService film_s;

    @BeforeClass
    public void setUp() throws IOException, SQLException {
        PrepareDatabase.initDB();
        this.film_s = new FilmService();
    }

    @Test
    public void testAddFilm() {
        for (Film film: TablesTestData.films){
            film_s.addFilm(film);
        }
    }
    
}