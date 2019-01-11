package mt.com.lc2.adapter

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.inflate_list.view.*
import mt.com.lc2.R
import mt.com.lc2.model.Result

class MyAdapter(var it: List<Result>, var context: FragmentActivity?) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val v = LayoutInflater.from(context).inflate(R.layout.list_item_movie, parent, false)
      return ViewHolder(v)
   }

   override fun getItemCount(): Int {
      return it.size
   }

   override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      /*Picasso.with(this) // give it the context
         .load("https://i.imgur.com/H981AN7.jpg") // load the image
         .into(holder.imageView) // select the ImageView to load it into*/

      // holder?.updateWithUrl(it.get(position).url.medium)

      // Picasso.get().load(it.get(position).url.medium).into(holder.imageView);

      holder.movieTitle.setText(it.get(position).title)
      holder.data.setText(it.get(position).overview)
      holder.movieDescription.setText(it.get(position).originalTitle)
      holder.rating.setText(it.get(position).posterPath)


   }

   class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//      val imageView: ImageView = itemView.imageview

      val moviesLayout = itemView.findViewById(R.id.movies_layout) as LinearLayout
      val movieTitle = itemView.findViewById(R.id.title) as TextView
      val data = itemView.findViewById(R.id.subtitle) as TextView
      val movieDescription = itemView.findViewById(R.id.description) as TextView
      val rating = itemView.findViewById(R.id.rating) as TextView
   }
}