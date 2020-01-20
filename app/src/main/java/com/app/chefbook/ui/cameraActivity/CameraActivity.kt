package com.app.chefbook.ui.cameraActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.chefbook.R
import com.app.chefbook.ui.adapters.CameraViewPagerAdapter
import kotlinx.android.synthetic.main.activity_camera.*

class CameraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val fragmentAdapter = CameraViewPagerAdapter(supportFragmentManager)
        viewPagerCamera.adapter = fragmentAdapter
        tabLayoutCamera.setupWithViewPager(viewPagerCamera)

    }
}
