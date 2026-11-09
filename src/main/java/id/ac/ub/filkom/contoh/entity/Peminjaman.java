package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "peminjaman")
public class Peminjaman {

    @Id
    @Column(name = "id_peminjaman", length = 10)
    private String idPeminjaman;

    @Column(name = "id_buku", length = 10, nullable = false)
    private String idBuku;

    @Column(name = "id_anggota", length = 10, nullable = false)
    private String idAnggota;

    @Column(name = "tanggal_pinjam", nullable = false)
    private LocalDate tanggalPinjam;

    @Column(name = "tanggal_kembali")
    private LocalDate tanggalKembali;

    @Column(name = "status", length = 20, nullable = false)
    private String status; // "DIPINJAM" atau "KEMBALI"

    protected Peminjaman() { }

    public Peminjaman(String idPeminjaman, String idBuku, String idAnggota,
                       LocalDate tanggalPinjam, String status) {
        this.idPeminjaman = idPeminjaman;
        this.idBuku = idBuku;
        this.idAnggota = idAnggota;
        this.tanggalPinjam = tanggalPinjam;
        this.status = status;
    }

    public String getIdPeminjaman()    { return idPeminjaman; }
    public String getIdBuku()          { return idBuku; }
    public String getIdAnggota()       { return idAnggota; }
    public LocalDate getTanggalPinjam()  { return tanggalPinjam; }
    public LocalDate getTanggalKembali() { return tanggalKembali; }
    public String getStatus()          { return status; }

    public void setTanggalKembali(LocalDate tanggalKembali) { this.tanggalKembali = tanggalKembali; }
    public void setStatus(String status) { this.status = status; }
}