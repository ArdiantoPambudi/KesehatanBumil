-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 08, 2020 at 02:23 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moms`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_bidan`
--

CREATE TABLE `tb_bidan` (
  `id_bidan` int(11) NOT NULL,
  `nama_bidan` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_bidan`
--

INSERT INTO `tb_bidan` (`id_bidan`, `nama_bidan`, `password`) VALUES
(1, 'any', 'rusmiyati'),
(2, 'Minggu ke 2', 'Kemampuan motorik ka'),
(3, 'anyy', 'rusmiyati'),
(4, 'anyy', 'rusmiyati'),
(5, 'ais', 'ais');

-- --------------------------------------------------------

--
-- Table structure for table `tb_cek_kesehatan`
--

CREATE TABLE `tb_cek_kesehatan` (
  `kode_pemeriksaan` int(11) NOT NULL,
  `id_moms` int(11) NOT NULL,
  `tgl_pemeriksaan` date NOT NULL,
  `keluhan` varchar(255) NOT NULL,
  `berat_badan` varchar(30) NOT NULL,
  `tekanan_darah` varchar(30) NOT NULL,
  `tinggi_fundus` int(11) NOT NULL,
  `denyut_jantung_janin` int(11) NOT NULL,
  `lingkar_lengan_atas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_cek_kesehatan`
--

INSERT INTO `tb_cek_kesehatan` (`kode_pemeriksaan`, `id_moms`, `tgl_pemeriksaan`, `keluhan`, `berat_badan`, `tekanan_darah`, `tinggi_fundus`, `denyut_jantung_janin`, `lingkar_lengan_atas`) VALUES
(1, 1, '2020-04-08', '', '50', '110', 18, 120, 23),
(4, 2, '2020-04-29', 'Demam', '46', '100', 11, 124, 23),
(10, 1, '2020-04-08', '', '43', '110', 18, 120, 23),
(11, 1, '2020-04-15', 'Simpant', '52', '120', 19, 121, 24),
(18, 1, '2020-03-03', 'Sakit Gigi', '20', '20', 20, 102, 12),
(20, 1, '2019-11-19', 'Sakit Punggung dan mual', '60', '110', 14, 123, 25),
(21, 1, '2020-06-02', 'Bengkak Kaki', '57', '110', 23, 121, 23),
(22, 1, '2020-06-02', 'Pusing mual', '66', '100', 35, 121, 23),
(23, 2, '2020-06-02', 'Bengkak Kaki', '65', '110', 23, 110, 21),
(24, 1, '2020-06-04', 'Mual', '66', '120', 24, 120, 23),
(25, 0, '2020-06-05', 'Sakit gigi', '50', '120', 110, 120, 130),
(26, 0, '2020-06-05', 'Sakit gigi', '50', '120', 110, 120, 130),
(27, 0, '2020-06-05', 'Sakit gigi', '50', '120', 110, 120, 130),
(28, 0, '2020-06-05', 'sakit', '55', '120/80', 15, 121, 23),
(29, 0, '2020-06-05', 'sakit', '50', '120', 20, 23, 23),
(30, 0, '2020-06-05', 'sakit', '50', '120', 20, 23, 23),
(31, 0, '2020-06-05', 'sakit', '50', '120', 20, 23, 23),
(32, 0, '2020-06-05', '1', '1', '1', 1, 1, 1),
(33, 0, '2020-06-05', '1', '1', '1', 1, 1, 1),
(34, 0, '2020-06-05', '30', '50', '6', 6, 6, 12),
(35, 0, '2020-06-05', 'sakit', '5', '5', 5, 5, 15),
(36, 3, '2020-06-05', 'sakit', '30', '120', 13, 12, 23),
(37, 7, '2020-06-05', 'Mual', '20', '110/20', 18, 120, 12);

-- --------------------------------------------------------

--
-- Table structure for table `tb_janin`
--

CREATE TABLE `tb_janin` (
  `id_janin` int(11) NOT NULL,
  `minggu` varchar(50) NOT NULL,
  `penjelasan` text NOT NULL,
  `gambar` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_janin`
--

INSERT INTO `tb_janin` (`id_janin`, `minggu`, `penjelasan`, `gambar`) VALUES
(7, 'Minggu KeTiga', 'Pada minggu ketiga, seiring telur yang telah dibuahi berkembang, sebuah kantung embrio dan plasenta pun ikut terbentuk. Sel darah terbentuk dan ratusan sel lainnya ikut berkembang, lalu sirkulasi darah pun dimulai.', '85606fa6866f14c9d3383f6f9d786616.jpg'),
(10, 'Minggu KeEmpat', 'Pada Akhir minggu keempat,tabung jantung janin sudah ada dan dapat berdenyut 65 kali dalam satu menit', 'IMG-20200703-WA0012.jpg'),
(11, 'Minggu KeLima', 'Di akhir bulan pertama, janin sudah berukuran sepanjang 0,6 cm, lebih kecil dari butiran nasi. Anda juga akan mulai mengalami gejala kehamilan, contohnya mudah kelelahan dan payudara yang membesar. ', '2520ea9a676008f097cc73ce6e4e57a1.jpg'),
(12, 'Minggu KeEnam', 'Pada minggu keenam, wajah dengan lingkaran besar untuk mata, hidung, mulut, telinga serta rahang bawah dan tenggorokan sudah mulai terbentuk. Dan janin sudah mulai terlihat melengkung seperti huruf C.', '3154a45372b3f92c1237da3a337fe5ff.jpg'),
(13, 'Minggu KeTujuh', 'Pada minggu ketujuh, janin mulai membentuk tangan dan kaki, dan rahim kini telah berukuran dua kali lipat.', '97cff7b1d26e7f0e15699e0e4aeab749.jpg'),
(14, 'Minggu KeDelapan - KeSepuluuh', 'Pada minggu kedelapan hingga sepuluh, janin telah berhasil melalui  masa kritis dari perkembangan organ dan struktur tubuhnya, telah berukuran hampir 3 cm panjangnya, semakin banyak bergerak dan semakin terlihat seperti manusia. Pada minggu ini, bayi dalam kandungan telah siap untuk berkembang.', '9e6f89b8a206d381d013b47d1f718e5a.jpg'),
(15, 'Minggu KeSebelas - KeTigabelas', 'Pada minggu kesebelas hingga ketigabelas otak bayi akan berkembang dengan pesat, ginjalnya mulai mengeluarkan urine dan jari-jarinya telah bisa mengepal seperti tinju. Panjang bayi akan mencapai 8 cm. Memasuki minggu keduabelas alat kelamin bayi sudah mulai dibentuk.', '40e5a037471acff47defb9807edb0253.jpg'),
(16, 'Minggu KeEmpatbelas - KeLimabelas', 'Pada minggu keempatbelas dan kelimabelas, indera perasanya terbentuk dan ia sudah mulai bisa mendeteksi cahaya. Bayi akan mengalami lonjakan pertumbuhan pada minggu keenambelas dan alat kelaminnya telah berkembang dengan baik, sehingga bisa terlihat saat pemeriksaan USG.', 'c1803531cfe7bf0345e5c809a1becee7.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tb_jeniskeluhan`
--

CREATE TABLE `tb_jeniskeluhan` (
  `id_jeniskeluhan` int(11) NOT NULL,
  `nama_keluhan` varchar(30) NOT NULL,
  `penyebab` text NOT NULL,
  `pengobatan` text NOT NULL,
  `image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_jeniskeluhan`
--

INSERT INTO `tb_jeniskeluhan` (`id_jeniskeluhan`, `nama_keluhan`, `penyebab`, `pengobatan`, `image`) VALUES
(1, 'Sakit punggung', 'Selama kehamilan, ligamen yang menghubungkan antar tulang menjadi lebih lunak dan meregang untuk mempersiapkan persalinan.\r\n\r\nNamun, beban tubuh yang meningkat karena bayi makin besar dalam justru akan memberatkan punggung dan panggul sehingga menyebabkan sakit punggung.', 'Untuk mencegah sakit punggung saat hamil, ibu hamil sebaiknya:\r\n\r\nMenghindari mengangkat benda berat\r\nTekuk lutut Anda dan jaga agar tubuh Anda tetap tegak ketika mengambil barang dari bawah atau lantai\r\nGerakan kaki Anda ketika berbalik untuk menghindari memutarnya tulang belakang\r\nGunakan alas kaki yang datar seperti flat shoes agar berat badan Anda dibagi secara merata pada kedua kaki\r\nBekerja pada meja yang cukup tinggi untuk mencegah Anda membungkuk\r\nSeimbangkan berat tas Anda ketika membawa tas atau membawa belanja\r\nDuduklah dengan tegak\r\nPastikan Anda mempunyai waktu cukup untuk istirahat', 'c3e7e3b9e0a08720c0507f2e5d239380.jpg'),
(2, 'Sembelit atau Masalah Pencerna', 'Hal yang sering dirasakan oleh ibu hamil di akhir trimester yang sering kali mengganggu ialah sembelit atau susah buang air besar. hal ini terjadi lagi – lagi karena perubahan hormon di dalam tubuh, ang berpengaruh pada fungsi usus yang menjadi lebih lambat saat mencerna makanan.', 'Hal ini bisa diatasi dengan perbanyak minum air putih dan juga mengkonsumsi makanan yang kaya akan serat dan juga vitamin. Bukan cuma suplemen vitamin saja tetapi juga sayur – sayuran organik yang sehat bagi tubuh. Dan jika masih terasa sulit buang air besar, juga bisa menggunakan obat pencahar yang khusus untuk ibu hamil yang bisa didapat di apotek sesuai dengan resep dokter.', 'b094aafbcb7170c151ae71505731a61c.jpg'),
(54, 'Heartburn', 'Kebanyakan wanita hamil akan merasakan heartburn, atau rasa panas yang terasa di bagian dada seperti perasaan terbakar yang diakibatkan oleh asam lambung yang naik hingga ke bagian kerongkongan.', 'Hal ini terjadi akibat pengaruh dari hormon kehamilan, dan juga pertumbuhan janin yang menekan rongga perut hingga membuat cairan asam naik.', '2d9a5dc921b819b738140a555f97fc73.jpg'),
(55, 'Sesak napas', 'Dalam trimester ketiga ini janin di dalam rahim akan tumbuh dengan pesat, setiap bagian dair organnya akan tumbuh dan matang agar ketika tiba waktunya untuk lahir maka bayi akan siap dilahirkan. Dan karena perkembangannya ini, satu keluhan yang mungkin akan sering dirasakan ialah mudah sesak napas.', 'Hal ini terjadi karena janin yang tumbuh akan menyita seluruh ruang di perut sang ibu dan hal ini akan membuat ibu akan merasa mudah sesak napas. ', '1278e497aff857363c0110e5ee65121a.jpg'),
(56, 'Sering Terjadi Kontraksi Palsu', 'Seiring pertumbuhan janin dalam perut ibu yang mulai mendapatkan asupan nutrisi, selain tubuhnya yang kini mulai tumbuh sempurna, gerakannya pun juga akan terasa lebih jelas. Sensasi ini sering disertai dengan membuat tubuh terasa tidak nyaman, salah satunya yang mungkin akan sering dirasakan ialah  kontraksi Braxton Hicks atau lebih sering disebut dengan kontraksi palsu.', 'Kontraksi ini berbeda dengan kontraksi yang dirasakan sebelum melahirkan, biasanya kontraksi dirasakan lebih ringan dan akan hilang dengan sendirinya. Jika calon ibu sering merasakan kontraksi ringan ini mungkin memang akan meninggalkan rasa was – was dan sedikit sesak di perut. Namun tidak perlu terlalu dikhawatirkan secara berlebihan karena kontraksi ini biasanya akan hilang sendirinya. Dan kondisi ini akan lebih sering terjadi jika calon ibu melakukan aktifittas yang cukup melelahkan baik di siang atau pun malam hari.', 'a6e66e5621c42e9e0ebb637bdd63426b.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `tb_moms`
--

CREATE TABLE `tb_moms` (
  `id_moms` int(11) NOT NULL,
  `nama_lengkap` varchar(50) NOT NULL,
  `nama_suami` varchar(50) NOT NULL,
  `tinggi_badan` int(4) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `telepon` varchar(13) NOT NULL,
  `hpht` date NOT NULL,
  `hpl` date NOT NULL,
  `keguguran` varchar(20) NOT NULL,
  `kelahiran_anak` varchar(20) NOT NULL,
  `golongan_darah` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_moms`
--

INSERT INTO `tb_moms` (`id_moms`, `nama_lengkap`, `nama_suami`, `tinggi_badan`, `tgl_lahir`, `telepon`, `hpht`, `hpl`, `keguguran`, `kelahiran_anak`, `golongan_darah`) VALUES
(1, 'Setianingsih', 'Hermansyah', 164, '2019-11-13', '085729216950', '2020-06-03', '2020-11-28', 'Tidak', 'kedua', 'O'),
(2, 'Aisyah', 'Ardian', 0, '2019-11-13', '08572921695', '2020-05-21', '2020-05-21', 'Pernah', 'KeSatu', 'AB'),
(3, 'Putri', 'Pambudi', 170, '2019-11-13', '085729216950', '2019-11-13', '2019-11-13', 'Tidak', 'Kedua', 'O'),
(7, 'Larmi', 'Juki', 165, '2020-05-13', '085729216950', '2020-05-13', '2020-05-20', 'Tidak', 'KeLima', 'O');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tandabahaya`
--

CREATE TABLE `tb_tandabahaya` (
  `id_tandabahaya` int(11) NOT NULL,
  `nama_bahaya` varchar(255) NOT NULL,
  `penyebab` text NOT NULL,
  `tindakan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_tandabahaya`
--

INSERT INTO `tb_tandabahaya` (`id_tandabahaya`, `nama_bahaya`, `penyebab`, `tindakan`) VALUES
(1, 'Ibu hamil mengalami perdarahan', 'Pada masa hamil muda, keadaan ini dapat menimbulkan bahaya keguguran pada janin dalam kandungan. Pada usia kehamilan yang lanjut mendekati cukup bulan, bila tiba tiba mengalami keluar  darah merah segar maupun gumpal kehitaman dari jalan lahir kemungkinan besar berasal dari ari-ari atau plasenta yang terlepas sebagian sebelum bayi lahir. Pada kondisi ini  sebaiknya  ibu hamil segera di bawa ke tempat pelayanan kesehatan.\r\n\r\n', 'Saran: Pada saat mengalami perdarahan seorang ibu hamil harus segera tirah baring agar perdarahan tidak semakin banyak. Bila perdarahan cukup banyak hingga menembus kain atau pakaian dan tempat pelayanan kesehatan jauh sebaiknya selama perjalanan menuju ke rumah sakit posisikan kedua kaki lebih tinggi dengan di ganjal bantal. Kepala dibaringkan datar sejajar tubuh. Beri minum manis pada ibu hamil yang mengalami perdarahan. Jangan memaksakan diri menuju rumah sakit yang jauh, segera menuju di tempat pelayanan kesehatan yang ditemukan dalam  perjalanan agar mendapat tindakan pertolongan penambahan cairan dan upaya penghentian perdarahan. Keterlambatan penanganan sering terjadi karena keluarga berusaha menuju rumah sakit yang jauh tanpa adanya tindakan darurat dari tenaga kesehatan terdekat.\r\n'),
(2, 'Bengkak di tangan, kaki dan wajah', 'Memasuki masa kehamilan beberapa perubahan tubuh pada ibu hamil antara lain adalah kenaikan berat badan  dan sedikit pembengkakan pada bagian tubuh seperti tangan, kaki dan wajah. Namun waspada bila terjadi pembengkakan pada bagian tubuh tersebut dan diikuti dengan nyeri tengkuk, nyeri ulu hati dan pusing kepala bahkan kejang - kejang mendadak  dan disertai pertambahan berat badan  berlebihan selama hamil  juga perlu diwaspadai.Semua tanda tersebut mengarah pada keadaan keracunan kehamilan atau disebut dengan preeklampsia dan eklampsia bila kejang.\r\n', 'Saran: Untuk mengetahui pembengkakan yang tidak normal antara lain dengan menekan pada daerah tungkai kaki yang bengkak, bila bagian yang ditekan tampak cekung dan tidak segera kembali seperti semula berarti terdapat penumpukan cairan. Untuk ibu hamil yang mengalami kenaikan tekanan darah selama kehamlan seringkali diikuti dengan pembengkakan pada anggota tubuh kaki , bila sudah mendapat perawatan dan pengobatan sebaiknya ibu hamil sering sering mengganjal kedua kaki lebih tinggi dari tubuh agar memperlancar aliran darah dan mencegah penumpukan cairan berlebihan di area kaki. Hindari penggunaan pakaian yang ketat selama hamil. Ibu hamil dengan kondisi bengkak, pusing kepala, nyeri tengkuk dan ulu hati , mata berkunang kunang wajib segera memeriksakan diri ke bidan dan tenaga kesehatan terdekat.\r\n\r\n'),
(3, 'Demam tinggi', 'Ibu hamil dalam usia kehamilan berapapun bila mengalami panas  atau demam tinggi perlu segera dibawa kepada tenaga kesehatan atau pelayanan kesehatan untuk mendapatkan pertolongan. Keterlambatan penanganan dapat menimbulkan bahaya bagi ibu akibat infeksi. Selain itu, bayi berpotensi mengalami keguguran dan terlahir prematur bahkan kematian bayi dalam kandungan.\r\n', 'Saran  : Ibu hamil dengan panas tinggi tidak dianjurkan untuk minum obat penurun panas tanpa ada pemeriksaan dari tenaga kesehatan. Selama mengalami demam ibu hamil minum air putih yang cukup agar tidak terjadi kekurangan cairan tubuh. Bila sudah mendapat pengobatan, sebaiknya ibu hamil istirahat  tirah baring di atas tempat tidur hingga suhu tubuh kembali normal.'),
(4, 'Keluar air ketuban.', 'Bagi ibu hamil dalam usia kehamilan berapapun bila mengalami ada cairan keluar dari jalan lahir, baik itu merembes maupun mengalir, segera menuju ke tempat pelayanan kesehatan untuk memastikan apakah ibu mengalami pecah ketuban. Jangan lupa perhatikan warna air ketuban atau perembesan air ketuban. Beritahukan pada bidan saat memeriksa misalnya banyaknya air ketuban hingga membasahi sprei atau berapa kali ganti pembalut, warna dan baunya.', 'Saran: Jangan menunda untuk memeriksakan diri karena air ketuban semakin berkurang dan bisa kering.  Berisiko bayi mengalami infeksi dalam kandungan. Terutama bila air ketuban yang keluar berwarna kuning kental atau kehijauan bau, ibu hamil wajib segera datang kepada tenaga kesehatan terdekat. Bila terasa ada perembesan air ketuban atau ada cairan ketuban mengalir dari jalan lahir  sebaiknya ibu hamil segera di bawa ke bidan atau dokter dan posisikan duduk atau bila air ketuban mengalir deras upayakan  berbaring selama perjalanan agar tidak semakin banyak cairan yang keluar.\r\n\r\n'),
(5, 'Gerakan bayi berkurang atau tidak bergerak sama-sekali', 'Bagi ibu hamil penting memantau gerak bayi dalam kandungan. Pada kehamilan yang masih muda memang belum dapat dirasakan. Pada umumnya, memasuki kehamilan lima bulan, ibu hamil semakin sering meraskan gerakan gerakan janin dalam kandungan. Bila dalam keadaan terjaga, diharapkan seorang ibu hamil bisa merasakan gerakan janin  kurang lebih sepuluh kali dalam 12 jam. Bila ibu tidak merasakan gerakan janin sebaiknya segera menuju ke tempat pelayanan kesehatan agar tidak terlambat dan terjadi kematian janin dalam kandungan.', 'Saran: Ibu hamil bisa mempraktikkan menghitung gerakan janin ini misalnya dengan menulis di kertas saat sambil bekerja, misalnya dengan membuat tulisan dengan sepuluh huruf : “ S A Y A N G    B A Y I “  bila setiap merasakan bayi bergerak, segera ibu menulis satu huruf saja maka selama bekerja di kantor atau saat di rumah. bila sudah terangkai kalimat sayang bayi berarti sudah aman. Atau bila ibu rumah tangga bisa dengan menggunakan koin uang logam, saat bayi bergerak tandai dengan koin uang logam yang dikumpulkan dalam wadah kecil, bila sudah terkumpul sepuluh koin berarti bayi aman. Namun demikian perlu tetap diwaspadai bila bayi tiba tiba berhenti bergerak sama sekali setelah gesit bergerak terus menerus tanpa henti. Pada beberapa kasus bayi dengan lilitan tali pusat seringkali janin dalam kandungan setelah bergerak lincah, tiba-tiba bayi tidak bergerak sama sekali . Untuk merangsang gerak bayi  sehari hari bisa dilakukan antara lain  ibu coba berbaring miring ke satu sisi tubuh ke arah kiri dan usap perlahan perut ibu, ajak bayi berkomunikasi sambil ibu relaksasi dan menarik nafas panjang.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_bidan`
--
ALTER TABLE `tb_bidan`
  ADD PRIMARY KEY (`id_bidan`);

--
-- Indexes for table `tb_cek_kesehatan`
--
ALTER TABLE `tb_cek_kesehatan`
  ADD PRIMARY KEY (`kode_pemeriksaan`);

--
-- Indexes for table `tb_janin`
--
ALTER TABLE `tb_janin`
  ADD PRIMARY KEY (`id_janin`);

--
-- Indexes for table `tb_jeniskeluhan`
--
ALTER TABLE `tb_jeniskeluhan`
  ADD PRIMARY KEY (`id_jeniskeluhan`),
  ADD UNIQUE KEY `nama_keluhan` (`nama_keluhan`);

--
-- Indexes for table `tb_moms`
--
ALTER TABLE `tb_moms`
  ADD PRIMARY KEY (`id_moms`);

--
-- Indexes for table `tb_tandabahaya`
--
ALTER TABLE `tb_tandabahaya`
  ADD PRIMARY KEY (`id_tandabahaya`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_bidan`
--
ALTER TABLE `tb_bidan`
  MODIFY `id_bidan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tb_cek_kesehatan`
--
ALTER TABLE `tb_cek_kesehatan`
  MODIFY `kode_pemeriksaan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `tb_janin`
--
ALTER TABLE `tb_janin`
  MODIFY `id_janin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `tb_jeniskeluhan`
--
ALTER TABLE `tb_jeniskeluhan`
  MODIFY `id_jeniskeluhan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `tb_moms`
--
ALTER TABLE `tb_moms`
  MODIFY `id_moms` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tb_tandabahaya`
--
ALTER TABLE `tb_tandabahaya`
  MODIFY `id_tandabahaya` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
