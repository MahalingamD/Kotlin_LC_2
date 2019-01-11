package mt.com.lc2.rest

import io.reactivex.Observable
import mt.com.lc2.model.Check
import mt.com.lc2.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

   @GET("movie/top_rated?")
   fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<Movies>

   @GET("movie/{id}")
   fun getMovieDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<Movies>

   @GET("https://api.androidhive.info/json/glide.json")
   fun getAllPosts(): Call<List<Check>>
}