package com.app.chefbook.ui.MainActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager

import com.app.chefbook.di.DataManager.componentActivity
import com.app.chefbook.data.IDataManager
import com.app.chefbook.R
import com.app.chefbook.ui.CameraActivity.CameraActivity
import com.app.chefbook.ui.utility.BaseFragment
import com.app.chefbook.utility.PostList
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemReselectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {


    private val backStack = Stack<Int>()

    private val fragments = listOf(
        BaseFragment.newInstance(
            R.layout.content_flow_base,
            R.id.toolbar_flow_content,
            R.id.nav_container_flow
        ),
        BaseFragment.newInstance(
            R.layout.content_discovery_base,
            R.id.toolbar_discovery_content,
            R.id.nav_container_discovery
        ),
        BaseFragment.newInstance(
            R.layout.content_addpost_base,
            R.id.toolbar_addPost_content,
            R.id.nav_container_addPost
        ),
        BaseFragment.newInstance(
            R.layout.content_profile_base,
            R.id.toolbar_profile_content,
            R.id.nav_container_profile
        )
    )

    private val indexToPage = mapOf(0 to R.id.flow, 1 to R.id.discovery, 2 to R.id.addPost, 3 to R.id.profile)

    @Inject
    lateinit var dataManager: IDataManager
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        componentActivity.inject(this)

        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(dataManager))
            .get(MainViewModel::class.java)

        //switchFragment(UserSearchFragment())
        //bottom_navigation?.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        //supportFragmentManager.beginTransaction().replace(R.id.bottom_navigation_container, UserSearchFragment(), UserSearchFragment.javaClass.getSimpleName()).commit()

        //------------ Üsttekiler kullanılmıyor

        //val navController = Navigation.findNavController(this, R.id.bottom_navigation_container)

        //setupBottomNavMenu(navController)


        main_viewPager.addOnPageChangeListener(this)
        main_viewPager.adapter = ViewPagerAdapter()
        main_viewPager.post(this::checkDeepLink)
        main_viewPager.offscreenPageLimit = fragments.size

        // set bottom nav
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        bottom_navigation.setOnNavigationItemReselectedListener(this)

        // initialize backStack with elements
        if (backStack.empty()) backStack.push(0)
    }

    /*private fun setupBottomNavMenu(navController: NavController) {
        bottom_navigation?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }*/

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = Navigation.findNavController(this, R.id.bottom_navigation)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }

    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        val itemId = indexToPage[position] ?: R.id.home
        if (bottom_navigation.selectedItemId != itemId) bottom_navigation.selectedItemId = itemId
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = indexToPage.values.indexOf(item.itemId)
        if (main_viewPager.currentItem != position) setItem(position)
        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        val position = indexToPage.values.indexOf(item.itemId)
        val fragment = fragments[position]
        fragment.popToRoot()
    }

    override fun onBackPressed() {
        val fragment = fragments[main_viewPager.currentItem]
        val hadNestedFragments = fragment.onBackPressed()
        // Eğer hiç fragment kaldırılmadıysa
        if (!hadNestedFragments) {
            if (backStack.size > 1) {
                // Mevcut konumu yığından kaldır
                backStack.pop()
                // Sonraki öğeyi yığında geçici olarak ayarla
                main_viewPager.currentItem = backStack.peek()

            } else super.onBackPressed()
        }
    }

    private fun checkDeepLink() {
        fragments.forEachIndexed { index, fragment ->
            val hasDeepLink = fragment.handleDeepLink(intent)
            if (hasDeepLink) setItem(index)
        }
    }

    private fun setItem(position: Int) {
        main_viewPager.currentItem = position
        backStack.push(position)
        if (position == 2 && PostList.instance!!.size == 1) {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }

    inner class ViewPagerAdapter : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

    }

}

/*
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

        when (menuItem.itemId) {
            R.id.bottom_navigation_search -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.bottom_navigation_profile -> {
                switchFragment(UserProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun switchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.bottom_navigation_container, fragment, fragment.javaClass.getSimpleName()).commit()

    }
*/
