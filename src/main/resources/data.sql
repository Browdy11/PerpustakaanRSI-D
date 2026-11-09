-- Data awal buku
INSERT INTO buku (id_buku, judul, stok) VALUES ('B001', 'Pemrograman Java', 2);
INSERT INTO buku (id_buku, judul, stok) VALUES ('B002', 'Rekayasa Perangkat Lunak', 0);
INSERT INTO buku (id_buku, judul, stok) VALUES ('B003', 'Basis Data', 2);

-- Data awal anggota
INSERT INTO anggota (id_anggota, nama, alamat) VALUES ('A001', 'Budi Santoso', 'Malang');
INSERT INTO anggota (id_anggota, nama, alamat) VALUES ('A002', 'Siti Aminah', 'Surabaya');
INSERT INTO anggota (id_anggota, nama, alamat) VALUES ('A003', 'Agus Wijaya', 'Jakarta');

-- Data awal peminjaman
INSERT INTO peminjaman (id_peminjaman, id_buku, id_anggota, tanggal_pinjam, tanggal_kembali, status) VALUES ('PJ001', 'B001', 'A001', '2026-11-01', NULL, 'DIPINJAM');
