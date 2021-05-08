package controllers;

import dataAccess.film.Film;
import dataAccess.film.FilmService;
import dataAccess.order.Order;
import dataAccess.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;


import java.util.List;

@Controller
public class FilmController {
    FilmService filmService = new FilmService();
    OrderService orderService = new OrderService();

    @GetMapping("/filmsList")
    public String filmsListPage(Model model) {
        List<Film> films = filmService.loadAllActualSorted();  // todo load not all films
        model.addAttribute("films", films);
        return "filmsList";
    }

    @GetMapping("/film")
    public String filmPage(@RequestParam(name = "film_id", required = true) int film_id,
                           @RequestParam(name = "issue_date_from", required = false) String issue_date_from,
                           @RequestParam(name = "issue_date_to", required = false) String issue_date_to,
                           Model model) {
        model.addAttribute("film_id", film_id);
        Film film = filmService.findFilmById(film_id);
        model.addAttribute("filmName", film.getFilm_name());
        model.addAttribute("producer", film.getProducer());
        model.addAttribute("release_year", film.getRelease_year());
        model.addAttribute("cassette_total_number", film.getCassette_total_number());
        model.addAttribute("disc_total_number", film.getDisc_total_number());
        model.addAttribute("cassette_available_number", film.getCassette_available_number());
        model.addAttribute("disc_available_number", film.getDisc_available_number());
        model.addAttribute("cassette_price", film.getCassette_price());
        model.addAttribute("disk_price", film.getDisk_price());
        model.addAttribute("film_is_removed", film.getFilm_is_removed());

        Date startDate;
        try {
            startDate = java.sql.Date.valueOf(issue_date_from);
            model.addAttribute("issue_date_from", startDate);
        } catch (IllegalArgumentException e) {
            startDate = null;
            model.addAttribute("issue_date_from", "the beginning of the history");
        }
        Date endDate;
        try {
            endDate = java.sql.Date.valueOf(issue_date_to);
            model.addAttribute("issue_date_to", endDate);
        } catch (IllegalArgumentException e) {
            endDate = null;
            model.addAttribute("issue_date_to", "the end of the history");
        }

        List<Order> orders = orderService.getOrdersOfFilmForSpecifiedPeriod(film_id, startDate, endDate);
        model.addAttribute("orders", orders);
        return "film";
    }

    @PostMapping("/filmSave")
    public String filmSavePage(@RequestParam(name = "film_id", required = false) Integer film_id,
                               @RequestParam(name = "film_name") String film_name,
                               @RequestParam(name = "producer") String producer,
                               @RequestParam(name = "release_year") int release_year,
                               @RequestParam(name = "cassette_total_number") int cassette_total_number,
                               @RequestParam(name = "disc_total_number") int disc_total_number,
                               @RequestParam(name = "cassette_price") int cassette_price,
                               @RequestParam(name = "disk_price") int disk_price,
                               Model model) {
        Film film = null;
        boolean changeIsSuccessful = false;

        if (film_id != null) {
            film = filmService.findFilmById(film_id);
            if (film != null) {
                film.setFilm_name(film_name);
                film.setProducer(producer);
                film.setRelease_year(release_year);
                film.setCassette_total_number(cassette_total_number);
                film.setDisc_total_number(disc_total_number);
                film.setCassette_price(cassette_price);
                film.setDisk_price(disk_price);
                changeIsSuccessful = filmService.updateFilm(film);
            }
        }
        if (film == null) {
            film = new Film(film_name, producer, release_year,
                    cassette_total_number, disc_total_number,
                    cassette_total_number,  // is cassette_available_number,
                    disc_total_number,  // is  disc_available_number,
                    cassette_price, disk_price, false);
            changeIsSuccessful = filmService.addFilm(film);
        }

        if (changeIsSuccessful) {
            // todo return page with params somehow pretty
            return String.format("redirect:/film?film_id=%d", film.getFilm_id());
        }
        return "filmsList";  // todo show error
    }

    @PostMapping("/filmDelete")
    public String filmDeletePage(@RequestParam(name = "film_id", required = true) Integer film_id, Model model){
        boolean result = filmService.safetyDeleteFilmById(film_id);
        if (!result){ return String.format("redirect:/film?id=%d", film_id); }
        return "redirect:/filmsList";
    }

    @PostMapping("/filmAdd")
    public String filmAddPage(@RequestParam(name = "film_id", required = false) Integer film_id, Model model) {
        if (film_id == null) {
            return "filmAdd";
        }

        Film film = filmService.findFilmById(film_id);
        if (film == null) {
            return "addFilm";
        }

        model.addAttribute("film_id", film_id);
        model.addAttribute("film_name", film.getFilm_name());
        model.addAttribute("producer", film.getProducer());
        model.addAttribute("release_year", film.getRelease_year());
        model.addAttribute("cassette_total_number", film.getCassette_total_number());
        model.addAttribute("disc_total_number", film.getDisc_total_number());
        model.addAttribute("cassette_price", film.getCassette_price());
        model.addAttribute("disk_price", film.getDisk_price());
        return "addFilm";
    }
}
