//package com.onionmonster.kalibareader.home
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.RecyclerView
//import com.onionmonster.kalibareader.R
//import java.io.File
//
//
//class HomeRecyclerAdapter(var homeList: List<HomeItem>): RecyclerView.Adapter<HomeRecyclerAdapter.HomeItemViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
//        val view: View =
//            LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
//        return HomeItemViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
//        val id: Int = homeList[position].id
//        val desc: String = homeList[position].desc
//        holder.desc.text = desc
//        holder.image.setImageResource(id)
//    }
//
//    override fun getItemCount(): Int {
//        return homeList.size
//    }
//
//    inner class HomeItemViewHolder(private val mView: View) : RecyclerView.ViewHolder(
//        mView
//    ) {
//        val desc: TextView = mView.findViewById(R.id.item_desc)
//        val image: ImageView = mView.findViewById(R.id.item_image)
//    }
//}
//
//class HomeItemDiffCallback: DiffUtil.ItemCallback<File>() {
//    override fun areItemsTheSame(oldItem: File, newItem: File): Boolean {
//        return oldItem.name == newItem.name
//    }
//
//    override fun areContentsTheSame(oldItem: File, newItem: File): Boolean {
//        return oldItem == newItem
//    }
//
//}