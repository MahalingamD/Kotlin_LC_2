package mt.com.lc2.adapter

import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import mt.com.lc2.R
import mt.com.lc2.model.Check

class AlphabetPagerAdapter(private val list: List<Check>?) : PagerAdapter() {

   override fun isViewFromObject(v: View, `object`: Any): Boolean {
      // Return the current view
      return v === `object` as View
   }

   override fun getCount(): Int {
      // Count the items and return it
      return list?.size!!
   }

   override fun instantiateItem(parent: ViewGroup, position: Int): Any {

      val view = LayoutInflater.from(parent.context).inflate(R.layout.inflate_list, parent, false)
      // Get the widgets reference from layout
      val imageView: ImageView = view.findViewById(R.id.imageview)

      Picasso.get().load(list?.get(position)?.url?.medium).into(imageView);

      // Add the view to the parent
      parent.addView(view)

      return view
   }

   override fun destroyItem(parent: ViewGroup, position: Int, `object`: Any) {
      // Remove the view from view group specified position
      parent.removeView(`object` as View)
   }


}