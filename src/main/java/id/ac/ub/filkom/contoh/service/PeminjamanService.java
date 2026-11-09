package id.ac.ub.filkom.contoh.service;

import id.ac.ub.filkom.contoh.dto.PeminjamanTampil;
import id.ac.ub.filkom.contoh.entity.Anggota;
import id.ac.ub.filkom.contoh.entity.Buku;
import id.ac.ub.filkom.contoh.entity.Peminjaman;
import id.ac.ub.filkom.contoh.repository.AnggotaRepository;
import id.ac.ub.filkom.contoh.repository.BukuRepository;
import id.ac.ub.filkom.contoh.repository.PeminjamanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeminjamanService {

    private final PeminjamanRepository peminjamanRepository;
    private final BukuRepository bukuRepository;
    private final AnggotaRepository anggotaRepository;

    public PeminjamanService(PeminjamanRepository peminjamanRepository,
                              BukuRepository bukuRepository,
                              AnggotaRepository anggotaRepository) {
        this.peminjamanRepository = peminjamanRepository;
        this.bukuRepository = bukuRepository;
        this.anggotaRepository = anggotaRepository;
    }

    /** Menampilkan semua data peminjaman, lengkap dengan judul buku & nama anggota. */
    public List<PeminjamanTampil> daftarPeminjaman() {
        return peminjamanRepository.findAll().stream()
                .map(p -> {
                    String judul = bukuRepository.findById(p.getIdBuku())
                            .map(Buku::getJudul).orElse("(buku tidak ditemukan)");
                    String nama = anggotaRepository.findById(p.getIdAnggota())
                            .map(Anggota::getNama).orElse("(anggota tidak ditemukan)");
                    return new PeminjamanTampil(
                            p.getIdPeminjaman(), judul, nama,
                            p.getTanggalPinjam(), p.getTanggalKembali(), p.getStatus());
                })
                .collect(Collectors.toList());
    }

    /** Buku yang stoknya masih tersedia, untuk ditampilkan di dropdown form pinjam. */
    public List<Buku> daftarBukuTersedia() {
        return bukuRepository.findAll().stream()
                .filter(b -> b.getStok() != null && b.getStok() > 0)
                .collect(Collectors.toList());
    }

    public List<Anggota> daftarSemuaAnggota() {
        return anggotaRepository.findAll();
    }

    /**
     * Proses peminjaman buku.
     * @return null jika berhasil, atau pesan error jika gagal (misal stok habis)
     */
    public String pinjamBuku(String idAnggota, String idBuku) {
        Buku buku = bukuRepository.findById(idBuku).orElse(null);
        if (buku == null) {
            return "Buku tidak ditemukan.";
        }
        if (buku.getStok() == null || buku.getStok() <= 0) {
            return "Stok buku \"" + buku.getJudul() + "\" sedang habis.";
        }
        if (anggotaRepository.findById(idAnggota).isEmpty()) {
            return "Anggota tidak ditemukan.";
        }

        // Kurangi stok buku
        buku.setStok(buku.getStok() - 1);
        bukuRepository.save(buku);

        // Simpan record peminjaman baru
        String idBaru = "PJ" + String.format("%03d", peminjamanRepository.count() + 1);
        Peminjaman peminjaman = new Peminjaman(
                idBaru, idBuku, idAnggota, LocalDate.now(), "DIPINJAM");
        peminjamanRepository.save(peminjaman);

        return null; // berhasil
    }

    /**
     * Proses pengembalian buku.
     * @return null jika berhasil, atau pesan error jika gagal
     */
    public String kembalikanBuku(String idPeminjaman) {
        Peminjaman peminjaman = peminjamanRepository.findById(idPeminjaman).orElse(null);
        if (peminjaman == null) {
            return "Data peminjaman tidak ditemukan.";
        }
        if ("KEMBALI".equals(peminjaman.getStatus())) {
            return "Buku ini sudah dikembalikan sebelumnya.";
        }

        // Update status peminjaman
        peminjaman.setTanggalKembali(LocalDate.now());
        peminjaman.setStatus("KEMBALI");
        peminjamanRepository.save(peminjaman);

        // Tambah kembali stok buku
        bukuRepository.findById(peminjaman.getIdBuku()).ifPresent(buku -> {
            buku.setStok(buku.getStok() + 1);
            bukuRepository.save(buku);
        });

        return null; // berhasil
    }
}