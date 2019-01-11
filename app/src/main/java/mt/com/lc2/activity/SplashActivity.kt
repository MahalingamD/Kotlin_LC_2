package mt.com.lc2.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import mt.com.lc2.R

class SplashActivity : AppCompatActivity() {

    private val DELAY_TIME_FOR_SPLASH_SCREEN: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val aHoldScreen = Handler()
        aHoldScreen.postDelayed(object : Runnable {
            override fun run() {
                try {
                    val aIntent = Intent(this@SplashActivity, HomeActivityTest::class.java)
                    aIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    aIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(aIntent)
                    this@SplashActivity.finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }, DELAY_TIME_FOR_SPLASH_SCREEN)
    }
}
