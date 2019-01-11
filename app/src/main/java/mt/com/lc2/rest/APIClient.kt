package mt.com.lc2.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

public class APIClient {

   companion object {

      /* val baseURL: String = "https://api.themoviedb.org/3/"
       var retofit: Retrofit? = null

       val client: Retrofit
          get() {

             if(retofit == null) {
                retofit = Retrofit.Builder()
                   .baseUrl(baseURL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build()
             }
             return retofit!!
          }*/

      //val BASE_URL = "http://api.themoviedb.org/3/"
      val BASE_URL = "https://api.androidhive.info/json/glide.json/"
      private var retrofit: Retrofit? = null

      fun getClient(): Retrofit? {
         if(retrofit == null) {
            retrofit = Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
         }
         return retrofit
      }
   }
}


