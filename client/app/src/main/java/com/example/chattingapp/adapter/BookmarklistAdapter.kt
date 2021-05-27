package com.example.chattingapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.R
import com.example.chattingapp.dto.Bookmark

class BookmarklistAdapter(val context: Context, var bookmarkList: ArrayList<Bookmark>) :
    RecyclerView.Adapter<BookmarklistAdapter.Holder>(), Filterable {
    private var bookmarks: ArrayList<Bookmark>? = bookmarkList

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val nameText = itemView?.findViewById<TextView>(R.id.bookmark_name)
        val contentText = itemView?.findViewById<TextView>(R.id.bookmark_contents)

        fun bind(bookmark: Bookmark, context: Context) {
            nameText?.text = bookmark.bookmarkName
            contentText?.text = bookmark.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.widget_bookmark_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(bookmarks?.get(position)!!, context)
        holder?.itemView.setOnClickListener {
            // 북마크 클릭시 이벤트
        }
    }

    override fun getItemCount(): Int {
        return bookmarks?.size!!
    }

    fun addItem(bookmark: Bookmark) {
        bookmarks?.add(bookmark)
        notifyItemInserted(bookmarks?.size!! - 1);
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                bookmarks = if (charString.isEmpty()) {
                    bookmarkList
                } else {
                    val filteredList = ArrayList<Bookmark>()
                    if (bookmarkList != null) {
                        for (bookmark in bookmarkList) {
                            if (bookmark.bookmarkName.toLowerCase()
                                    .contains(charString.toLowerCase())
                            ) {
                                filteredList.add(bookmark);
                            }
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = bookmarks
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                bookmarks = results.values as ArrayList<Bookmark>
                notifyDataSetChanged()
            }
        }
    }
}