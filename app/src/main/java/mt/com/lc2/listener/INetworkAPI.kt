package mt.com.lc2.listener

import io.reactivex.Observable
import mt.com.lc2.model.Check
import retrofit2.http.GET

interface INetworkAPI {

   @GET("name")
   fun getAllPosts(): Observable<List<Check>>
}

