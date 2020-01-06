package com.app.chefbook.UI.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.chefbook.UI.CameraActivity.CameraFragment
import com.app.chefbook.UI.CameraActivity.GalleryFragment

class CameraViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                CameraFragment()
            }
            else -> {
                return GalleryFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Camera"
            1 -> "Video"
            else -> {
                return "Gallery"
            }
        }
    }
}