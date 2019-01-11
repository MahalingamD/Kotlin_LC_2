package mt.com.lc2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import mt.com.lc2.R
import mt.com.lc2.activity.HomeActivityTest

/**
 * A simple [Fragment] subclass.
 *
 */
class SecondFragment : Fragment() {

   private var views: View? = null

   lateinit var mywebview: WebView

   private val url = "https://www.google.com"

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      // Inflate the layout for this fragment
      views = inflater.inflate(R.layout.fragment_second, container, false)



      init(views)

      return views
   }

   private fun init(views: View?) {

      mywebview = views?.findViewById(R.id.webview) as WebView

      mywebview.webViewClient = object : WebViewClient() {
         override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
         }
      }
      mywebview.loadUrl(url)
   }

   override fun onResume() {
      super.onResume()
      val activity = activity as HomeActivityTest
      activity.myToolbar.title = getString(R.string.second_fragment)
   }

}
