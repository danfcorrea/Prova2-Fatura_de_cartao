package br.com.cotemig.daniel.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.daniel.R
import br.com.cotemig.daniel.models.Fatura
import br.com.cotemig.daniel.models.ItensFatura
import br.com.cotemig.daniel.services.RetrofitInitializer
import br.com.cotemig.daniel.ui.adapters.ItensFaturaAdapter
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFatura()

    }

    fun showFatura(fatura:Fatura){
        var valorfatura = findViewById<TextView>(R.id.valorFatura)
        valorfatura.text = resources.getString(R.string.real).plus(String.format(" %.2f", fatura.valor))
        var valordisponivel = findViewById<TextView>(R.id.valorDisponivel)
        valordisponivel.text = resources.getString(R.string.real).plus(String.format(" %.2f", fatura.limiteDisponivel))
    }

    fun showItensFatura(itens: List<ItensFatura>){
        var recycleViewFatura = findViewById<RecyclerView>(R.id.recyclerViewFatura)
        recycleViewFatura.adapter = ItensFaturaAdapter(this, itens)
        recycleViewFatura.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    fun getFatura(){
        var s =RetrofitInitializer().serviceFatura()
        var call = s.getFatura()
        call.enqueue(object : retrofit2.Callback<Fatura>{
            override fun onResponse(call: Call<Fatura>, response: Response<Fatura>) {
                if(response.code()==200){
                    response.body()?.let {
                        showFatura(it)
                        showItensFatura(it.despesas)
                    }
                }
            }

            override fun onFailure(call: Call<Fatura>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ops", Toast.LENGTH_SHORT).show()
            }
        })
    }
}