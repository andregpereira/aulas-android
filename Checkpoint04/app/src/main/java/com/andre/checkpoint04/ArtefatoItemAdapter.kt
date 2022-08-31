package com.andre.checkpoint04

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andre.checkpoint04.database.ArtefatoInfo
import com.andre.checkpoint04.databinding.ViewArtefatoItemBinding

class ArtefatoItemAdapter(
    private val onDeleteListener: (ArtefatoInfo) -> Unit,
    private val onUpdateListener: (ArtefatoInfo) -> Unit
) : RecyclerView.Adapter<ArtefatoItemAdapter.CharacterItemViewHolder>() {

    private var artefatoList: MutableList<ArtefatoInfo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterItemViewHolder {
        val binding = ViewArtefatoItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.view_artefato_item, parent, false)
        )
        return CharacterItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterItemViewHolder, position: Int) {
        holder.bindView(artefatoList[position])
    }

    override fun getItemCount(): Int {
        return artefatoList.size
    }

    fun setData(list: List<ArtefatoInfo>) {
        with(artefatoList) {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class CharacterItemViewHolder(
        private val view: ViewArtefatoItemBinding
    ) : RecyclerView.ViewHolder(view.root) {

        fun bindView(artefatoInfo: ArtefatoInfo) {
            view.artefatoNomeValue.text = artefatoInfo.nome
            view.artefatoNomeSistemaEstelarValue.text = artefatoInfo.nomeSistemaEstelar
            view.artefatoNomePlanetaLuaValue.text = artefatoInfo.nomePlanetaLua
            view.artefatoCoordenadaXValue.text = artefatoInfo.coordenadaX.toString()
            view.artefatoCoordenadaYValue.text = artefatoInfo.coordenadaY.toString()

            view.iconDelete.setOnClickListener {
                onDeleteListener.invoke(artefatoInfo)
            }

            view.iconUpdate.setOnClickListener {
                onUpdateListener.invoke(artefatoInfo)
            }
        }
    }
}