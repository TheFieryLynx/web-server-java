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
    private final long nonExistentId = 100;
    private final long existentId = 1;
    private final Film filmUpdated = new Film("Monsters University", "Kori Rae",
            2013, 2, 3, 1,
            2, 150, 160, false);

    @BeforeClass
    public void setUp() throws IOException, SQLException {
        PrepareDatabase.initEmptyDB();
        this.film_s = new FilmService();
    }

    @Test
    public void testFilmService() {
        // fill the table
        for (Film film : TablesTestData.films) {
            Assert.assertTrue(film_s.addFilm(film));
        }

        // read the whole table
        List<Film> films = film_s.loadAll();
        Assert.assertEquals(films.size(), TablesTestData.films.length);
        for (int i = 0; i < films.size(); ++i) {
            Assert.assertEquals(films.get(i), TablesTestData.films[i]);
        }

        // find by id & update
        Assert.assertNull(film_s.findFilmById(nonExistentId));
        filmUpdated.setFilm_id(nonExistentId);
        Assert.assertFalse(film_s.updateFilm(filmUpdated));
        filmUpdated.setFilm_id(existentId);
        Assert.assertTrue(film_s.updateFilm(filmUpdated));
        Assert.assertEquals(filmUpdated, film_s.findFilmById(existentId));

        // save object with initialized filed "id"
        Assert.assertFalse(film_s.addFilm(filmUpdated));

        // remove all objects from the table
        for (Film film : films) {
            Assert.assertTrue(film_s.deleteFilmById(film.getFilm_id()));
        }
    }
}
