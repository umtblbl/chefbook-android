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
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chefbook.DI.DataManager.componentFragment
import com.app.chefbook.Data.DataManager
import com.app.chefbook.R
import com.app.chefbook.UI.Adapters.ProfilePostAdapter
import com.app.chefbook.UI.Adapters.RecyclerViewOnClickListener
import com.app.chefbook.Utilities.getInflateLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : Fragment(), RecyclerViewOnClickListener {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel
    private var toolbarProfile: View? = null
    private var imgToolbarSettings: ImageView? = null
    private var txtUserNameToolbar: TextView? = null
    var toolbar: Toolbar? = null
    lateinit var profilePostAdapter: ProfilePostAdapter
    var userName: String = ""

    @Inject
    lateinit var dataManager: DataManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        componentFragment.inject(this)
        viewModel = ViewModelProviders.of(this, ProfileViewModelFactory(dataManager))
            .get(ProfileViewModel::class.java)

        initializeToolbar()
        viewModel.getProfile()

        viewModel.profile.observe(this, Observer {

            userName = it.userName
            txtSharingCount.text = it.postCount.toString()
            txtFollowersCount.text = it.followerCount.toString()
            txtFullName.text = it.fullName
            txtBiography.text = it.description
            txtUserNameToolbar?.text = it.userName.toString()

            Picasso.with(context).load(it.profilePicture).into(imgProfilePicture)
            Picasso.with(context).load(it.cover).into(imgProfileCover)

            profilePostAdapter = ProfilePostAdapter(it, this)
            recViewPost.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recViewPost.adapter = profilePostAdapter
            profilePostAdapter.notifyDataSetChanged()
        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        imgToolbarSettings?.setOnClickListener {
            Log.d("TAG", "Settings Tıklandı")
            val action = ProfileFragmentDirections.actionProfileToSettings()
            action.userName = userName
            //val bundle = action.arguments
            //bundle.putString("userName", userName)
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

    override fun onClick(item: String) {
        Toast.makeText(context, "Id = $item", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        toolbar?.removeView(toolbarProfile)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getProfile()
    }

}
