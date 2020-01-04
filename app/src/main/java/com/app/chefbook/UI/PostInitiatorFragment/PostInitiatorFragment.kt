package com.app.chefbook.UI.PostInitiatorFragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.chefbook.DI.DataManager.componentFragment
import com.app.chefbook.Data.DataManager
import com.app.chefbook.Model.AdapterModel.PostInitiator
import com.app.chefbook.R
import com.app.chefbook.UI.Adapters.PostInitiatorAdapter
import com.app.chefbook.UI.Adapters.RecyclerViewOnClickListener
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.fragment_post_initiator.*

import javax.inject.Inject

class PostInitiatorFragment : Fragment(), RecyclerViewOnClickListener {

    @Inject
    lateinit var dataManager: DataManager
    private lateinit var viewModel: PostInitiatorViewModel
    lateinit var postInitiatorAdapter: PostInitiatorAdapter
    var postList = mutableListOf<PostInitiator>()
    private val uri: Uri = Uri.parse("android.resource://com.example.peeple/drawable/ic_add_a_photo_white_24dp")


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_post_initiator, container, false)
        componentFragment.inject(this)
        viewModel = ViewModelProviders.of(this, ProfileViewModelFactory(dataManager)).get(PostInitiatorViewModel::class.java)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        postList.add(PostInitiator(uri, isPost = true, isAddImage = true))
        postInitiatorAdapter = PostInitiatorAdapter(postList, this)
        recViewPostInitiator.layoutManager = GridLayoutManager(context, 3)
        recViewPostInitiator.adapter = postInitiatorAdapter
        postInitiatorAdapter.notifyDataSetChanged()

    }

    override fun onClick(item: String) {
        if(postList[item.toInt()].isAddImage) {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(context!!, this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            val result = CropImage.getActivityResult(data)

            if (resultCode == RESULT_OK) {
                val resultUri = result.uri

                postList.forEach {
                    if(it.isAddImage) {
                        postList.remove(it)
                    }
                }
                postList.add(PostInitiator(resultUri, isPost = true, isAddImage = false))
                if(postList.size < 6)
                    postList.add(PostInitiator(uri, isPost = true, isAddImage = true))
                postInitiatorAdapter.notifyDataSetChanged()

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Log.d("Camera", result.error.toString())
                Toast.makeText(context, "Kamere hatası!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
