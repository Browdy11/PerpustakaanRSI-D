package id.ac.ub.filkom.contoh.controller;

import id.ac.ub.filkom.contoh.service.AnggotaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Controller: terima request, panggil service, pilih template. */
@Controller
public class AnggotaController {

    private final AnggotaService anggotaService;

    public AnggotaController(AnggotaService anggotaService) {
        this.anggotaService = anggotaService;
    }

    // GET http://localhost:8080/anggota?kata=budi
    @GetMapping("/anggota")
    public String daftarAnggota(@RequestParam(required = false) String kata,
                                 Model model) {
        model.addAttribute("daftarAnggota", anggotaService.cariAnggota(kata));
        model.addAttribute("kata", kata);
        return "anggota";               // -> templates/anggota.html
    }

    // GET untuk menampilkan form tambah anggota
    @GetMapping("/anggota/tambah")
    public String formTambahAnggota() {
        return "Tambah_anggota";        // -> templates/Tambah_anggota.html
    }

    // POST dari form tambah anggota
    @PostMapping("/anggota")
    public String tambahAnggota(@RequestParam String idAnggota,
                                 @RequestParam String nama,
                                 @RequestParam(required = false) String alamat) {
        anggotaService.tambahAnggota(idAnggota, nama, alamat);
        return "redirect:/anggota";     // pola Post-Redirect-Get
    }
}