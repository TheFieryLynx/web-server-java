package dataAccess.film;

import dataAccess.film.Film;

import java.util.List;

public interface FilmDAO {

    boolean save(Film film);
    void delete(Film film);
    boolean deleteById(long id);
    boolean update(Film film);
    Film findById(long id);
    List<Film> loadAll();    
}
