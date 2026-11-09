package id.ac.ub.filkom.contoh.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buku")
public class Buku {

    @Id
    @Column(name = "id_buku", length = 10)
    private String idBuku;

    @Column(name = "judul", nullable = false)
    private String judul;

    @Column(name = "stok")
    private Integer stok;

    protected Buku() { }

    public Buku(String idBuku, String judul, Integer stok) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.stok = stok;
    }

    public String getIdBuku() { return idBuku; }
    public String getJudul()  { return judul; }
    public Integer getStok()  { return stok; }

    // Ditambahkan untuk kebutuhan modul Peminjaman
    public void setStok(Integer stok) { this.stok = stok; }
}