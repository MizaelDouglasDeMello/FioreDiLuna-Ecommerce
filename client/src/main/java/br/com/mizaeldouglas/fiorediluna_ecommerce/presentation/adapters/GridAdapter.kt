package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.mizaeldouglas.fiorediluna_ecommerce.R

data class Item(val imageRes: Int, val name: String, val type: String, val price: String)

class GridAdapter(private val items: List<Item>) :
    RecyclerView.Adapter<GridAdapter.GridViewHolder>() {

    // Defina um conjunto de itens visíveis para aplicar os efeitos de fade
    private val visibleItems = mutableSetOf<Int>()

    class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        val itemName: TextView = itemView.findViewById(R.id.itemName)
        val itemType: TextView = itemView.findViewById(R.id.itemType)
        val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val item = items[position]
        holder.itemImage.setImageResource(item.imageRes)
        holder.itemName.text = item.name
        holder.itemType.text = item.type
        holder.itemPrice.text = item.price

        // Aplicar o efeito de fade nos itens
        if (visibleItems.contains(position)) {
            holder.itemView.alpha = 1f // Fade-in
        } else {
            holder.itemView.alpha = 1f // Certifique-se de que o alpha está 1 por padrão
        }
    }

    override fun getItemCount(): Int = items.size

    // Método para atualizar a visibilidade dos itens com base na rolagem
    fun setVisibleItems(visibleItems: Set<Int>) {
        this.visibleItems.clear()
        this.visibleItems.addAll(visibleItems)
        notifyDataSetChanged()  // Notificar o adapter para atualizar a view
    }
}
