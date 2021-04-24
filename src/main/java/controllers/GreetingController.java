package controllers;

import dataAccess.client.Client;
import dataAccess.client.ClientService;
import dataAccess.film.Film;
import dataAccess.film.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GreetingController {
    FilmService filmService = new FilmService();
    ClientService clientService = new ClientService();

//    @GetMapping("/")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }

    @GetMapping("/filmsList")
    public String filmsListPage(Model model) {
        List<Film> films = filmService.loadAll();  // todo sort & load not all films
        model.addAttribute("films", films);
        return "filmsList";
    }

    @GetMapping("/film")
    public String filmPage(@RequestParam(name="id", required=true) String filmId, Model model) {
        model.addAttribute("filmId", filmId);
        Film film = filmService.findFilmById(Long.parseLong(filmId));
        model.addAttribute("filmName", film.getFilm_name());
        return "film";
    }

    @PostMapping("/saveFilm")
    public String saveFilmPage(Model model) {
        return "index";  // todo
    }

    @GetMapping("/addFilm")
    public String addFilmPage(Model model) {
        return "addFilm";
    }

    @GetMapping("/client")
    public String clientPage(@RequestParam(name="id", required=true) String clientId, Model model) {
        model.addAttribute("clientId", clientId);
        Client client = clientService.findClientById(Long.parseLong(clientId));
        model.addAttribute("clientName", client.getClient_name());
        return "client";
    }

}