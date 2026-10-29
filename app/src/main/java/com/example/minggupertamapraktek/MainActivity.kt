package com.example.minggupertamapraktek

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.minggupertamapraktek.ui.theme.MingguPertamaPraktekTheme
import android.content.Intent
import android.util.Log


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("PesanMakan", "FORM: onCreate — menyiapkan form")
//  deklarasi variable
    val etnama = findViewById<EditText>(R.id.editTextNama)
    val etjumlah = findViewById<EditText>(R.id.editTextJumlah)

    val btnhitung = findViewById<Button>(R.id.buttonPesan)
    val btnreset = findViewById<Button>(R.id.buttonReset)


    val tvhasil = findViewById<TextView>(R.id.textViewHasil)

    btnhitung.setOnClickListener {
        val nama = etnama.text.toString().trim()
        val jumlahteks = etjumlah.text.toString().trim()

        etnama.error = null
        etjumlah.error = null

        if (nama.isBlank()){
            etnama.error="nama wajib diisi"
            etnama.requestFocus()
            return@setOnClickListener
        }

        val jumlah = jumlahteks.toIntOrNull()

        if(jumlah == null){
            etjumlah.error="masukkan jumlah pesanan anda"
            etjumlah.requestFocus()
            return@setOnClickListener
        }
        if (jumlah !in 1..100 ){
            etjumlah.error="jumlah kakehan"
            etjumlah.requestFocus()
            return@setOnClickListener
        }

        val hargaPorsi = 12.000
        val total = jumlah*hargaPorsi

        val formatRupiah = java.text.NumberFormat
            .getCurrencyInstance(java.util.Locale("id","id"))
            .apply {
                maximumFractionDigits
            }
        val tujuan = Intent(this, RingkasanActivity::class.java)

        tujuan.putExtra(KunciPesanan.NAMA, nama)
        tujuan.putExtra(KunciPesanan.JUMLAH, jumlah)

        startActivity(tujuan)

        tvhasil.text="""
            Berikut Adalah Pesanan Anda 
            Nama= $nama 
            Jumlah Pesanan= $jumlah
            Total : ${formatRupiah.format(total)}
        """.trimIndent()

    }
        btnreset.setOnClickListener {
            etnama.text.clear()
            etjumlah.text.clear()

            etnama.error = null
            etjumlah.error = null

            tvhasil.text="hasil masukan anda akan tampil disini"
            etnama.requestFocus()
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MingguPertamaPraktekTheme {
        Greeting("Android")
    }
}