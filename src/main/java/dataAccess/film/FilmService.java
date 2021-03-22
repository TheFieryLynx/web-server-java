package dataAccess.film;

import java.util.List;

public class FilmService {
    private final FilmDAO filmDAO = new FilmDAOImpl();

    public boolean addFilm(Film film) {
        if (film.checkCorrectness()) { return this.filmDAO.save(film); }
        return false;
    }

    public boolean deleteFilmById(long id) { return this.filmDAO.deleteById(id); }

    public boolean updateFilm(Film film) {
        if (film.checkCorrectness()) { return this.filmDAO.update(film); }
        return false;
    }

    public Film findFilmById(long id) {return this.filmDAO.findById(id);}

    public List<Film> loadAll() {return this.filmDAO.loadAll();}
}
