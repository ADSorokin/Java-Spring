package ru.sorokinad;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public ClientController(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/private-data")
    public String getPrivateData(Model model, @RegisteredOAuth2AuthorizedClient("client-app") OAuth2AuthorizedClient client) {
        // Делать запрос к resource-server с использованием токена
        String data = WebClient.create()
                .get()
                .uri("http://localhost:8081/api/private-data")
                .headers(headers -> headers.setBearerAuth(client.getAccessToken().getTokenValue()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        model.addAttribute("data", data);
        return "private-data";
    }
}
