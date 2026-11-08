package id.ac.ub.filkom.contoh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/** Controller untuk halaman beranda. */
@Controller
public class HomeController {

    @GetMapping("/")
    public String beranda() {
        return "index";   // -> templates/index.html
    }
}