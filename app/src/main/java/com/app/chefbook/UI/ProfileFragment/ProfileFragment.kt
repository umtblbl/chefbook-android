package com.app.chefbook.UI.ProfileFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.app.chefbook.DI.DataManager.componentFragment
import com.app.chefbook.Data.DataManager
import com.app.chefbook.R
import com.app.chefbook.Utilities.getInflateLayout
import javax.inject.Inject

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    private var toolbarProfile: View? = null
    private var imgToolbarSettings: ImageView? = null
    private var txtUserNameToolbar: TextView? = null
    var toolbar: Toolbar? = null

    @Inject
    lateinit var dataManager: DataManager

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        componentFragment.inject(this)
        viewModel = ViewModelProviders.of(this, ProfileViewModelFactory(dataManager)).get(ProfileViewModel::class.java)

        initializeToolbar()

        val accessToken = dataManager.getAuth()
        if (accessToken != null)
            viewModel.getProfile(accessToken)

        viewModel.profile.observe(this, Observer {

            var profile = viewModel.profile

        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imgToolbarSettings?.setOnClickListener {
            Log.d("TAG", "Settings Tıklandı")
            val action = ProfileFragmentDirections.actionProfileFragmentToSettingsFragment()
            findNavController().navigate(action).let { true }
        }

    }

    private fun initializeToolbar() {

        toolbar = requireActivity().findViewById(R.id.toolbar_profile_content)
        toolbar?.menu?.clear()
        toolbar?.title = null

        toolbarProfile = context?.let { getInflateLayout(it, R.layout.toolbar_profile) }

        imgToolbarSettings = toolbarProfile?.findViewById(R.id.imgSettings)
        txtUserNameToolbar = toolbarProfile?.findViewById(R.id.txtUserId)

        toolbar?.addView(toolbarProfile)

    }

    override fun onDestroy() {
        super.onDestroy()
        toolbar?.removeView(toolbarProfile)
    }

    override fun onPause() {
        super.onPause()
        toolbar?.removeView(toolbarProfile)
    }
}
