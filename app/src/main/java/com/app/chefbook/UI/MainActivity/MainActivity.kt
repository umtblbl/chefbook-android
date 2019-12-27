package com.app.chefbook.UI.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.viewpager.widget.ViewPager

import com.app.chefbook.DI.DataManager.componentActivity
import com.app.chefbook.Data.IDataManager
import com.app.chefbook.R
import com.app.chefbook.UI.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemReselectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {


    private val backStack = Stack<Int>()

    private val fragments = listOf(
        BaseFragment.newInstance(R.layout.content_flow_base, R.id.toolbar_flow, R.id.nav_container_flow),
        BaseFragment.newInstance(R.layout.content_discovery_base, R.id.toolbar_discovery, R.id.nav_container_discovery),
        BaseFragment.newInstance(R.layout.content_profile_base, R.id.toolbar_profile, R.id.nav_container_profile))

    private val indexToPage = mapOf(0 to R.id.flow, 1 to R.id.discovery, 2 to R.id.profile)

    @Inject
    lateinit var dataManager: IDataManager
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        componentActivity.inject(this)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(dataManager)).get(MainViewModel::class.java)

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

    private fun setupBottomNavMenu(navController: NavController) {
        bottom_navigation?.let {
            NavigationUI.setupWithNavController(it, navController)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = Navigation.findNavController(this, R.id.bottom_navigation)
        val navigated = NavigationUI.onNavDestinationSelected(item!!, navController)
        return navigated || super.onOptionsItemSelected(item)
    }

    override fun onPageScrollStateChanged(state: Int) { }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) { }

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
