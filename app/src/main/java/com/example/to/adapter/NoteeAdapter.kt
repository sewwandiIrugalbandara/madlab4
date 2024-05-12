package com.example.to.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.to.databinding.NoteLayoutBinding
import com.example.to.fragment.HomeFragmentDirections
import com.example.to.model.Notee

class NoteeAdapter : RecyclerView.Adapter<NoteeAdapter.NoteViewHolder>() {

    class NoteViewHolder(val itemBinding: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Notee>(){
        override fun areItemsTheSame(oldItem: Notee, newItem: Notee): Boolean {
            return oldItem.id == newItem.id
                    oldItem.noteDesc == newItem.noteDesc &&
                            oldItem.noteTitle == newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Notee, newItem: Notee): Boolean {
            return oldItem== newItem
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNotee = differ.currentList[position]

        holder.itemBinding.noteTitle.text = currentNotee.noteTitle
        holder.itemBinding.noteDesc.text = currentNotee.noteDesc

        holder.itemView.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditnoteFragment2(currentNotee)
            it.findNavController().navigate(direction)
        }
    }
}