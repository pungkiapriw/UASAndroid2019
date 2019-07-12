<?php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		//Mendapatkan Nilai Variable
		$name = $_POST['nama'];
		$nik = $_POST['nik'];
        $alamat = $_POST['kelas'];
        $jenis_kelamin = $_POST['jam'];
		
		//Pembuatan Syntax SQL
		$sql = "INSERT INTO form (nama,nik,kelas,jam) VALUES ('$name','$nik','$alamat','$jenis_kelamin')";
		
		//Import File Koneksi database
		require_once('koneksi.php');
		
		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Mahasiswa';
		}else{
			echo 'Gagal Menambahkan Mahasiswa';
		}
		
		mysqli_close($con);
	}
?>