package mt.com.lc2.fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import mt.com.lc2.R
import mt.com.lc2.activity.HomeActivityTest
import mt.com.lc2.adapter.MyAdapter
import mt.com.lc2.adapter.MyCheckAdapter
import mt.com.lc2.listener.INetworkAPI
import mt.com.lc2.model.Check
import mt.com.lc2.model.Movies
import mt.com.lc2.rest.APIClient
import mt.com.lc2.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 * Mahalingam
 *
 */
class ThirdFragment : Fragment() {

   private var views: View? = null

   private lateinit var myRecyclerView: RecyclerView

   private val API_KEY = "7e8f60e325cd06e164799af1e317d7a7"

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      // Inflate the layout for this fragment
      views = inflater.inflate(R.layout.fragment_third, container, false)

      init(views)

      return views
   }

   private fun init(views: View?) {

      myRecyclerView = views?.findViewById(R.id.recyclerView) as RecyclerView

      myRecyclerView.layoutManager = LinearLayoutManager(activity)

     // myRecyclerView.layoutManager = LinearLayoutManager(activity)
      myRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL ,false)
      // myRecyclerView.addItemDecoration(ListItemDecorations(20))
      myRecyclerView.setHasFixedSize(true)



      /* val apiService = APIClient.getClient()?.create(ApiInterface::class.java)

       val call = apiService?.getTopRatedMovies(API_KEY)
       call?.enqueue(object : Callback<Movies> {
          override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
             val statusCode = response.code()
             val movies = response.body()!!.results

             myRecyclerView.adapter = MyAdapter(movies, activity)
             //  myRecyclerView.setAdapter(MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()))
          }

          override fun onFailure(call: Call<Movies>, t: Throwable) {
             // Log error here since request failed
             Log.e(TAG, t.toString())
          }
       })*/

      val apiService = APIClient.getClient()?.create(ApiInterface::class.java)

      val call = apiService?.getAllPosts()
      call?.enqueue(object : Callback<List<Check>> {

         override fun onResponse(call: Call<List<Check>>, response: Response<List<Check>>) {
            val statusCode = response.code()
            val movies = response.body()

            myRecyclerView.adapter = MyCheckAdapter(movies!!, activity)
            //  myRecyclerView.setAdapter(MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()))
         }

         override fun onFailure(call: Call<List<Check>>, t: Throwable) {
            Log.e(TAG, t.toString())
         }

      })

   }

   override fun onResume() {
      super.onResume()
      val aActivity = activity as HomeActivityTest
      aActivity.myToolbar.title = getString(R.string.third_fragment)
   }

}
