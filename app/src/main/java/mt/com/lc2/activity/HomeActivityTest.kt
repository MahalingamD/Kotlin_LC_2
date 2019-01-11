package mt.com.lc2.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_home_test.*
import kotlinx.android.synthetic.main.app_bar_home_activity_test.*
import mt.com.lc2.R
import mt.com.lc2.fragment.FirstFragment
import mt.com.lc2.fragment.FourthFragment
import mt.com.lc2.fragment.SecondFragment
import mt.com.lc2.fragment.ThirdFragment

class HomeActivityTest : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

   var navItemIndex = 0
   lateinit var mHandler: Handler

   lateinit var myToolbar: Toolbar

   private var myBackPressed: Long = 0

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_home_test)
      setSupportActionBar(toolbar)

      myToolbar = findViewById<Toolbar>(R.id.toolbar)

      mHandler = Handler()

      fab.setOnClickListener { view ->
         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
      }

      val toggle = ActionBarDrawerToggle(
         this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
      )
      drawer_layout.addDrawerListener(toggle)
      toggle.syncState()

      loadHomeFragment()

      nav_view.setNavigationItemSelectedListener(this)
   }

   override fun onBackPressed() {
      if(drawer_layout.isDrawerOpen(GravityCompat.START)) {
         drawer_layout.closeDrawer(GravityCompat.START)
      } else {
         exitApp()
      }
   }

   override fun onCreateOptionsMenu(menu: Menu): Boolean {
      // Inflate the menu; this adds items to the action bar if it is present.
      menuInflater.inflate(R.menu.home_activity_test, menu)
      return true
   }

   override fun onNavigationItemSelected(item: MenuItem): Boolean {
      // Handle navigation view item clicks here.
      when(item.itemId) {
         R.id.nav_camera -> {
            // Handle the camera action
            navItemIndex = 0
         }
         R.id.nav_gallery -> {
            navItemIndex = 1
         }
         R.id.nav_slideshow -> {
            navItemIndex = 2
         }
         R.id.nav_manage -> {
            navItemIndex = 3
         }
         else -> {
            //Default
         }
      }

      loadHomeFragment()
      drawer_layout.closeDrawer(GravityCompat.START)
      return true
   }

   private fun loadHomeFragment() {
      val mPendingRunnable = object : Runnable {
         override fun run() {
            // update the main content by replacing fragments
            val fragment = getHomeFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            // fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            fragmentTransaction.replace(R.id.frame, fragment, "HOME")
            fragmentTransaction.commitAllowingStateLoss()
         }
      }
      if(mPendingRunnable != null) {
         mHandler.post(mPendingRunnable)
      }
   }

   private fun getHomeFragment(): Fragment {
      when(navItemIndex) {
         0 -> {
            // FirstFragment
            val aFirstFragment = FirstFragment()
            return aFirstFragment
         }
         1 -> {
            // SecondFragment
            val aSecondFragment = SecondFragment()
            return aSecondFragment
         }
         2 -> {
            // movies fragment
            val aThirdFragment = ThirdFragment()
            return aThirdFragment
         }
         3 -> {
            // notifications fragment
            val aFourthFragment = FourthFragment()
            return aFourthFragment
         }
         else -> return FirstFragment()
      }
   }

   fun exitApp() {
      try {
         if(myBackPressed + 2000 > System.currentTimeMillis()) {
            val aIntent = Intent(Intent.ACTION_MAIN)
            aIntent.addCategory(Intent.CATEGORY_HOME)
            aIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(aIntent)
            finish()
         } else {
            Toast.makeText(baseContext, getString(R.string.label_exit_app), Toast.LENGTH_SHORT).show()
            myBackPressed = System.currentTimeMillis()
         }
      } catch(e: Exception) {
         e.printStackTrace()
      }

   }
}
