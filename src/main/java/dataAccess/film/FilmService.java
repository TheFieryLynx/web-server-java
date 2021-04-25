package dataAccess.film;

import dataAccess.order.Order;
import dataAccess.order.OrderDAO;
import dataAccess.order.OrderDAOImpl;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class FilmService {
    private final FilmDAO filmDAO = new FilmDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();

    public boolean addFilm(Film film) {
        if (film.checkCorrectness()) { return this.filmDAO.save(film); }
        return false;
    }

    public boolean deleteFilmById(long id) { return this.filmDAO.deleteById(id); }

    public boolean safetyDeleteFilmById(long id) {
        // delete the film only if there is no orders with them
        Film film = this.filmDAO.findById(id);
        if (film == null) { return true; }
        List<Order> orders = this.orderDAO.getOrdersOfFilmForSpecifiedPeriod(id, null, null);
        if (orders.size() == 0) {
            filmDAO.delete(film);
            return true;
        }
        film.setFilm_is_removed(true);
        return this.filmDAO.update(film);
    }

    public boolean updateFilm(Film film) {
        if (film.checkCorrectness()) { return this.filmDAO.update(film); }
        return false;
    }

    public Film findFilmById(long id) {return this.filmDAO.findById(id);}

    public List<Film> loadAll() {return this.filmDAO.loadAll();}

    public List<Film> loadAllActualSorted() {
        List<Film> films = loadAll();
        films.removeIf(Film::isFilm_is_removed);
        films.sort(Comparator.comparing(Film::getFilm_name));
        return films;
    }
}
