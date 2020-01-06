package com.app.chefbook.UI.CameraActivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.app.chefbook.R
import com.app.chefbook.UI.Adapters.CameraViewPagerAdapter
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
