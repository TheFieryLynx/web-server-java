package controllers;

import dataAccess.client.Client;
import dataAccess.client.ClientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ClientController {
    ClientService clientService = new ClientService();

    @GetMapping("/client")
    public String clientPage(@RequestParam(name = "client_id", required = true) int clientId, Model model) {
        model.addAttribute("clientId", clientId);
        Client client = clientService.findClientById(clientId);
        model.addAttribute("clientName", client.getClient_name());
        return "client";
    }
}