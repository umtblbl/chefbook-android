package com.app.chefbook.ui.GalleryFragment


import android.Manifest
import android.app.Dialog
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.AdapterModel.PostInitiatorMedia
import com.app.chefbook.model.AppModel.GalleryPicture
import com.app.chefbook.R
import com.app.chefbook.ui.adapters.galleryPicture.GalleryPicturesAdapter
import com.app.chefbook.utility.PostList
import com.app.chefbook.utility.SpaceItemDecoration
import com.app.chefbook.utility.Utility
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.toolbar_gallery.*
import java.io.File

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    private lateinit var viewModel: GalleryViewModel

    private lateinit var adapter: GalleryPicturesAdapter
    private lateinit var pictures: ArrayList<GalleryPicture>
    var imgGalleryPicture: SubsamplingScaleImageView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        viewModel = ViewModelProviders.of(this, GalleryViewModelFactory()).get(GalleryViewModel::class.java)


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        requestReadStoragePermission()
    }

    private fun requestReadStoragePermission() {
        val readStorage = Manifest.permission.READ_EXTERNAL_STORAGE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(
                context!!,
                readStorage
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(readStorage), 3)
        } else init()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this)[GalleryViewModel::class.java]
        updateToolbar(0)
        val spanCount = Utility.calculateNoOfColumns(context!!, 133f)
        val layoutManager = GridLayoutManager(context, spanCount)
        rv.layoutManager = layoutManager
        rv.addItemDecoration(SpaceItemDecoration(8))
        pictures = ArrayList(viewModel.getGallerySize(context!!))
        adapter = GalleryPicturesAdapter(pictures, 7-PostList.instance!!.size)
        rv.adapter = adapter

        loadPictures(25)

        adapter.setOnClickListener {
            val pictureFullScreenDialog = Dialog(context!!)
            pictureFullScreenDialog.setContentView(R.layout.dialog_fullscreen_item_viewer)
            pictureFullScreenDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            pictureFullScreenDialog.setCanceledOnTouchOutside(true)
            pictureFullScreenDialog.setTitle("Tam Ekran")
            pictureFullScreenDialog.show()

            imgGalleryPicture = pictureFullScreenDialog.findViewById(R.id.imgGalleryPicture)
            imgGalleryPicture?.setImage(ImageSource.uri(Uri.fromFile(File(it.path))))
        }

        adapter.setAfterSelectionListener {
            updateToolbar(getSelectedItemsCount())
        }

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == pictures.lastIndex) {
                    loadPictures(25)
                }
            }
        })

        tvDone.setOnClickListener {
            val selectedList = adapter.getSelectedItems()
            val postList = PostList.instance!!
            selectedList.forEach {
                postList.add(PostInitiatorMedia(Uri.parse(it.path), isImage = true, isAddPost = false))
            }
            activity!!.finish()
        }
    }

    private fun getSelectedItemsCount() = adapter.getSelectedItems().size


    private fun loadPictures(pageSize: Int) {
        viewModel.getImagesFromGallery(context!!, pageSize) {
            if (it.isNotEmpty()) {
                pictures.addAll(it)
                adapter.notifyItemRangeInserted(pictures.size, it.size)
            }
            Log.i("GalleryListSize", "${pictures.size}")

        }

    }

    private fun updateToolbar(selectedItems: Int) {
        val data = if (selectedItems == 0) {
            tvDone.visibility = View.GONE
            getString(R.string.txt_gallery)
        } else {
            tvDone.visibility = View.VISIBLE
            "$selectedItems/${adapter.getSelectionLimit()}"
        }
        tvTitle.text = data
    }

    /*override fun onBackPressed() {
        if (adapter.removedSelection()) {
            updateToolbar(0)
        } else {
            //super.onBackPressed()
        }
    }*/

    private fun showToast(s: String) = Toast.makeText(context, s, Toast.LENGTH_SHORT).show()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            init()
        else {
            showToast("Galeri erişimi verilmedi!")
            //super.onBackPressed()
        }
    }


}
