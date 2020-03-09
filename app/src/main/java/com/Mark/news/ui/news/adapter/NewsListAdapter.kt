package com.Mark.news.vacation.vacationlists.adapter


import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.Mark.news.R
import com.Mark.news.ui.news.model.Pojo.Article
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.list_item.view.*


class NewsListAdapter :
    ListAdapter<Article, NewsListAdapter.ListViewHolder>(PostWithUserDC()) {

    var interaction: Interaction? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ListViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false), interaction
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) = holder.bind(getItem(position))

    fun swapData(data: List<Article>) {
        submitList(data.toMutableList())
    }

    inner class ListViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val clicked = getItem(adapterPosition)
            interaction?.itemsClicked(clicked)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Article) = with(itemView) {
            title_tv_value.text = item.title.toString()
            date_tv_value.text = item.publishedAt.toString().substring(0, 10);
            dis_tv_value.text = item.description.toString()
            source_tv_value.text = item.source!!.name.toString()
            when {
                (item.urlToImage != null) ->
                    Picasso.get().load(item.urlToImage!!).into(
                        newsicon
                    )
            }

        }
    }

    private class PostWithUserDC : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.publishedAt == newItem.publishedAt

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
    }

}