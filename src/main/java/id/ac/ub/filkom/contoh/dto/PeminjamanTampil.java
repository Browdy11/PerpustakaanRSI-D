package id.ac.ub.filkom.contoh.dto;

import java.time.LocalDate;

/** DTO sederhana untuk menampilkan data peminjaman di halaman daftar. */
public class PeminjamanTampil {

    private final String idPeminjaman;
    private final String judulBuku;
    private final String namaAnggota;
    private final LocalDate tanggalPinjam;
    private final LocalDate tanggalKembali;
    private final String status;

    public PeminjamanTampil(String idPeminjaman, String judulBuku, String namaAnggota,
                             LocalDate tanggalPinjam, LocalDate tanggalKembali, String status) {
        this.idPeminjaman = idPeminjaman;
        this.judulBuku = judulBuku;
        this.namaAnggota = namaAnggota;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
        this.status = status;
    }

    public String getIdPeminjaman()      { return idPeminjaman; }
    public String getJudulBuku()         { return judulBuku; }
    public String getNamaAnggota()       { return namaAnggota; }
    public LocalDate getTanggalPinjam()  { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public String getStatus()            { return status; }
}