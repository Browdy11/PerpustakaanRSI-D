package id.ac.ub.filkom.contoh.controller;

import id.ac.ub.filkom.contoh.service.PeminjamanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeminjamanController {

    private final PeminjamanService peminjamanService;

    public PeminjamanController(PeminjamanService peminjamanService) {
        this.peminjamanService = peminjamanService;
    }

    // Lihat daftar peminjaman
    @GetMapping("/peminjaman")
    public String daftarPeminjaman(Model model) {
        model.addAttribute("daftarPeminjaman", peminjamanService.daftarPeminjaman());
        return "peminjaman";
    }

    // Tampilkan form pinjam buku
    @GetMapping("/peminjaman/pinjam")
    public String formPinjam(Model model) {
        model.addAttribute("daftarBuku", peminjamanService.daftarBukuTersedia());
        model.addAttribute("daftarAnggota", peminjamanService.daftarSemuaAnggota());
        return "pinjam";
    }

    // Proses simpan peminjaman baru
    @PostMapping("/peminjaman")
    public String prosesPinjam(@RequestParam String idAnggota,
                                @RequestParam String idBuku,
                                Model model) {
        String error = peminjamanService.pinjamBuku(idAnggota, idBuku);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("daftarBuku", peminjamanService.daftarBukuTersedia());
            model.addAttribute("daftarAnggota", peminjamanService.daftarSemuaAnggota());
            return "pinjam";
        }
        return "redirect:/peminjaman";
    }

    // Proses pengembalian buku
    @PostMapping("/peminjaman/kembalikan/{id}")
    public String prosesKembalikan(@PathVariable("id") String idPeminjaman) {
        peminjamanService.kembalikanBuku(idPeminjaman);
        return "redirect:/peminjaman";
    }
}