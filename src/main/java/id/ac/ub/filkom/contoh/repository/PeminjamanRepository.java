package id.ac.ub.filkom.contoh.repository;

import id.ac.ub.filkom.contoh.entity.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, String> {
}