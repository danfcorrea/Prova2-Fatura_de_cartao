package br.com.cotemig.daniel.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.daniel.R
import br.com.cotemig.daniel.models.ItensFatura
import coil.load

class ItensFaturaAdapter(var context: Context, var despesas: List<ItensFatura>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item_fatura, parent, false)
        return DespesasHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DespesasHolder).bind(context, despesas[position])
    }

    override fun getItemCount(): Int {
        return despesas.size
    }

    class DespesasHolder(var view: View):RecyclerView.ViewHolder(view){
        fun bind(context: Context, itensFatura: ItensFatura){

            var tipo = view.findViewById<TextView>(R.id.tipo)
            tipo.text = itensFatura.tipo

            var data = view.findViewById<TextView>(R.id.data)
            data.text = itensFatura.data

            var image = view.findViewById<ImageView>(R.id.image)
            image.load(itensFatura.imagem)

            var valor = view.findViewById<TextView>(R.id.valor)
            valor.text = context.getString(R.string.real).plus(String.format("%.2f", itensFatura.valor))

            var descricao = view.findViewById<TextView>(R.id.descricao)
            descricao.text = itensFatura.descricao
        }
    }
}