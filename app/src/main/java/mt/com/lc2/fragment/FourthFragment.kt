package mt.com.lc2.fragment

import android.content.ContentValues
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import mt.com.lc2.R
import mt.com.lc2.adapter.AlphabetPagerAdapter
import mt.com.lc2.model.Check
import mt.com.lc2.rest.APIClient
import mt.com.lc2.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class FourthFragment : Fragment() {

   private var views: View? = null
   lateinit var viewPager: ViewPager
   // private var dots: Array<ImageView>? = null
   private var dots: Array<ImageView?>? = null

   private var pager_indicator: LinearLayout? = null

   //  val stringsOrNulls = arrayOfNulls<String>(10)

   private var dotsCount: Int = 0

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      // Inflate the layout for this fragment
      views = inflater.inflate(R.layout.fragment_fourth, container, false)

      init(views)

      return views
   }

   private fun init(views: View?) {

      viewPager = views?.findViewById<View>(R.id.view_pager) as ViewPager

      pager_indicator = views.findViewById(R.id.viewPagerCountDots)

      val apiService = APIClient.getClient()?.create(ApiInterface::class.java)

      val call = apiService?.getAllPosts()
      call?.enqueue(object : Callback<List<Check>> {

         override fun onResponse(call: Call<List<Check>>, response: Response<List<Check>>) {
            val statusCode = response.code()
            val movies = response.body()

            setViewpager(movies)

            setPageViewIndicator()
         }

         override fun onFailure(call: Call<List<Check>>, t: Throwable) {
            Log.e(ContentValues.TAG, t.toString())
         }

      })
   }

   private fun setViewpager(movies: List<Check>?) {
      val adapter = AlphabetPagerAdapter(movies)
      // Finally, data bind the view pager widget with pager adapter
      viewPager.adapter = adapter

      viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
         override fun onPageScrollStateChanged(p0: Int) {


         }

         override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

         }

         override fun onPageSelected(p0: Int) {

            Log.d("###onPageSelected, pos ", p0.toString())
            for(i in 0 until dotsCount) {
               dots?.get(i)?.setImageDrawable(resources.getDrawable(R.drawable.nonselecteditem_dot))
            }

            dots?.get(p0)?.setImageDrawable(resources.getDrawable(R.drawable.selecteditem_dot))

            if(p0 + 1 == dotsCount) {

            } else {

            }
         }

      })
   }

   private fun setPageViewIndicator() {

      Log.d("###setPageViewIndicator", " : called")
      dotsCount = viewPager.adapter?.getCount()!!
      dots = arrayOfNulls<ImageView?>(dotsCount)

      arrayOfNulls<String>(10)

      for(i in 0 until dotsCount) {
         dots?.set(i, ImageView(activity))
         dots?.get(i)?.setImageDrawable(resources.getDrawable(R.drawable.nonselecteditem_dot))

         val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
         )

         params.setMargins(4, 0, 4, 0)

         dots?.get(i)?.setOnTouchListener { v, event ->
            viewPager.setCurrentItem(i)
            true
         }


         pager_indicator?.addView(dots?.get(i), params)
      }

      dots?.get(0)?.setImageDrawable(resources.getDrawable(R.drawable.selecteditem_dot))
   }


}
