package dataAccess.film;

import java.util.List;

public class FilmService {
    private final FilmDAO filmDAO = new FilmDAO();

    public boolean addFilm(Film film) { return this.filmDAO.save(film); }

    public boolean deleteFilmById(long id) { return this.filmDAO.deleteById(id); }

    public boolean updateFilm(Film film) { return this.filmDAO.update(film); }

    public Film findFilmById(long id) {return this.filmDAO.findById(id);}

    public List<Film> loadAll() {return this.filmDAO.loadAll();}
}
