package controllers;

import dataAccess.film.Film;
import dataAccess.film.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilmController {
    FilmService filmService = new FilmService();

    @GetMapping("/filmsList")
    public String filmsListPage(Model model) {
        List<Film> films = filmService.loadAllActualSorted();  // todo load not all films
        model.addAttribute("films", films);
        return "filmsList";
    }

    @GetMapping("/film")
    public String filmPage(@RequestParam(name = "id", required = true) String filmId, Model model) {
        model.addAttribute("filmId", filmId);
        Film film = filmService.findFilmById(Long.parseLong(filmId));
        model.addAttribute("filmName", film.getFilm_name());
        model.addAttribute("producer", film.getProducer());
        model.addAttribute("release_year", film.getRelease_year());
        model.addAttribute("cassette_total_number", film.getCassette_total_number());
        model.addAttribute("disc_total_number", film.getDisc_total_number());
        model.addAttribute("cassette_available_number", film.getCassette_available_number());
        model.addAttribute("disc_available_number", film.getDisc_available_number());
        model.addAttribute("cassette_price", film.getCassette_price());
        model.addAttribute("disk_price", film.getDisk_price());
        model.addAttribute("film_is_removed", film.isFilm_is_removed());
        return "film";
    }

    @PostMapping("/saveFilm")
    public String saveFilmPage(@RequestParam(name = "film_id", required = false) Integer film_id,
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
            return String.format("redirect:/film?id=%d", film.getFilm_id());
        }
        return "filmsList";  // todo show error
    }

    @PostMapping("/deleteFilm")
    public String deleteFilmPage(@RequestParam(name = "id", required = true) Integer filmId, Model model){
        boolean result = filmService.safetyDeleteFilmById(filmId);
        if (!result){ return String.format("redirect:/film?id=%d", filmId); }
        return "redirect:/filmsList";
    }

    @GetMapping("/addFilm")
    public String addFilmPage(@RequestParam(name = "id", required = false) Integer filmId, Model model) {
        if (filmId == null) {
            return "addFilm";
        }

        Film film = filmService.findFilmById(filmId);
        if (film == null) {
            return "addFilm";
        }

        model.addAttribute("film_id", filmId);
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
