package mt.com.lc2.fragment

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.os.ConfigurationCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mt.com.lc2.R
import mt.com.lc2.activity.HomeActivityTest

/**
 * A simple [Fragment] subclass.
 *
 */
class FirstFragment : Fragment() {

   private var views: View? = null
   private lateinit var aTextView: TextView

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
      // Inflate the layout for this fragment
      views = inflater.inflate(R.layout.fragment_first, container, false)

      init(views)

      return views
   }

   private fun init(views: View?) {

      aTextView = views?.findViewById(R.id.aTextview1) as TextView

      aTextView.text = getString(R.string.app_name)

      val current = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         resources.configuration.locales.get(0)
      } else {
         resources.configuration.locale
      }

      val currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0]

      val activity = activity as HomeActivityTest
      activity.myToolbar.title = getString(R.string.first_fragment)

   }

   override fun onResume() {
      super.onResume()
      val activity = activity as HomeActivityTest
      activity.myToolbar.title = getString(R.string.first_fragment)
   }


}
