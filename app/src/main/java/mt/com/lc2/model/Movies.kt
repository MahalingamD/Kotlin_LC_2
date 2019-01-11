package mt.com.lc2.model

data class Movies(
   val page: Int,
   val totalResults: Int,
   val totalPages: Int,
   val results: List<Result>
)