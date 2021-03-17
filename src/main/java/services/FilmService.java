package services;

import DAO.FilmDAO;
import entities.Film;

public class FilmService {
    private FilmDAO filmDAO = new FilmDAO();

    public void addFilm(Film film) {
        this.filmDAO.save(film);
    }
}
