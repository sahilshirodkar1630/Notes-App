package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class NotesAdapter(private val context:Context, private val listener: INotes) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.text;
        val deletButton = itemView.btnDelete
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(
           R.layout.item_note,
           parent,
           false
       ))
        viewHolder.deletButton.setOnClickListener {
            listener.OnItemClicked(allNotes[viewHolder.adapterPosition])
        }

        return viewHolder;
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position];
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int  = allNotes.size

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()

    }


}
interface INotes{
    fun OnItemClicked(note:Note)
}