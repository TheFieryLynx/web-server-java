package controllers;

import dataAccess.film.Film;
import dataAccess.film.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/film")
    public String filmPage(@RequestParam(name="id", required=true) String filmId, Model model) {
        model.addAttribute("filmId", filmId);
        System.out.println("id: " + Long.parseLong(filmId));

        FilmService filmService = new FilmService();
        Film film = filmService.findFilmById(Long.parseLong(filmId));
        System.out.println("film: " + film);

        model.addAttribute("filmName", film.getFilm_name());
        return "film";
    }

}