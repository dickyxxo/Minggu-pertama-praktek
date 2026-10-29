package com.example.minggupertamapraktek

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.text.NumberFormat
import java.util.Locale

class RingkasanActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ringkasan)
        Log.d("PesanMakan", "HALAMAN RINGKASAN: onCreate — menyiapkan form")

        val tvNama = findViewById<TextView>(R.id.tvNama)
        val tvJumlah = findViewById<TextView>(R.id.tvJumlah)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val btnEdit = findViewById<Button>(R.id.btnEdit)

        val nama = intent.getStringExtra(KunciPesanan.NAMA) ?: ""
        // ?: "" berarti gunakan teks kosong jika hasilnya null.

        val jumlah = intent.getIntExtra(KunciPesanan.JUMLAH, 0)

        if (nama.isNullOrBlank() || jumlah !in 1..100) {
            finish()
            return
        }

        val total = jumlah * 15_000

        val rupiah = NumberFormat.getCurrencyInstance(
            Locale("id", "ID")
        ).apply {
            maximumFractionDigits = 0
        }

        tvNama.text = nama
        tvJumlah.text = "$jumlah paket"
        tvTotal.text = "Total: ${rupiah.format(total)}"

        btnEdit.setOnClickListener {
            finish()
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("PesanMakan", "FORM: onStart — form mulai terlihat")
    }

    override fun onResume() {
        super.onResume()
        Log.d("PesanMakan", "FORM: onResume — form siap digunakan")
    }

    override fun onPause() {
        super.onPause()
        Log.d("PesanMakan", "FORM: onPause — form kehilangan posisi aktif")
    }

    override fun onStop() {
        super.onStop()
        Log.d("PesanMakan", "FORM: onStop — form tidak terlihat")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("PesanMakan", "FORM: onRestart — form akan tampil kembali")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("PesanMakan", "FORM: onDestroy — instance form diakhiri")
    }
}