package com.app.chefbook.ui.PostInitiatorFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.app.chefbook.DI.DataManager.componentFragment
import com.app.chefbook.Data.DataManager
import com.app.chefbook.model.AdapterModel.PostInitiator
import com.app.chefbook.R
import com.app.chefbook.ui.adapters.PostInitiatorAdapter
import com.app.chefbook.ui.adapters.RecyclerViewOnClickListener
import com.app.chefbook.ui.CameraActivity.CameraActivity
import com.app.chefbook.utility.PostList
import com.app.chefbook.utility.Utility
import kotlinx.android.synthetic.main.fragment_post_initiator.*
import java.io.File

import javax.inject.Inject

class PostInitiatorFragment : Fragment(), RecyclerViewOnClickListener {

    @Inject
    lateinit var dataManager: DataManager
    private lateinit var viewModel: PostInitiatorViewModel
    lateinit var postInitiatorAdapter: PostInitiatorAdapter
    private val addImageUri: Uri =
        Uri.parse("android.resource://com.example.peeple/drawable/ic_add_a_photo_white_24dp")
    lateinit var file: File

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_post_initiator, container, false)
        componentFragment.inject(this)
        viewModel = ViewModelProviders.of(this, ProfileViewModelFactory(dataManager))
            .get(PostInitiatorViewModel::class.java)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spanCount = Utility.calculateNoOfColumns(context!!, 125f)
        postInitiatorAdapter = PostInitiatorAdapter(PostList.instance, this)
        recViewPostInitiator.layoutManager = GridLayoutManager(context, spanCount)
        recViewPostInitiator.adapter = postInitiatorAdapter
        postInitiatorAdapter.notifyDataSetChanged()

    }

    override fun onClick(item: String) {
        if (PostList.instance!![item.toInt()].isAddPost) {
            startActivity(Intent(context!!, CameraActivity::class.java))
        }
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
                Toast.makeText(context, "Kamera hatası!", Toast.LENGTH_SHORT).show()
            }
        }
    }*/

    override fun onStart() {
        super.onStart()


    }

    override fun onResume() {
        super.onResume()

        //val result = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "/image.jpeg")

        val uri = PostList.instance
        val postList = PostList.instance!!
        val iterator = postList.iterator()

        while (iterator.hasNext()) {
            if (iterator.next().isAddPost) {
                iterator.remove()
            }
        }

        if (postList.size < 6) {
            postList.add(PostInitiator(addImageUri, isImage = true, isAddPost = true))
        }
        postInitiatorAdapter.notifyDataSetChanged()


    }
}
