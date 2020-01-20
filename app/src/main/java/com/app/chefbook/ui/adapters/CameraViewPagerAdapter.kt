package com.app.chefbook.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.app.chefbook.ui.cameraFragment.CameraFragment
import com.app.chefbook.ui.galleryFragment.GalleryFragment

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
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Camera"
            else -> {
                return "Gallery"
            }
        }
    }
}