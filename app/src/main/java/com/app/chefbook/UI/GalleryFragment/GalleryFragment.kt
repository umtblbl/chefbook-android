package com.app.chefbook.UI.GalleryFragment


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.Model.AppModel.GalleryPicture
import com.app.chefbook.R
import com.app.chefbook.UI.Adapters.GalleryPicturesAdapter
import com.app.chefbook.Utilities.SpaceItemDecoration
import com.bumptech.glide.Glide.init
import kotlinx.android.synthetic.main.fragment_gallery.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * A simple [Fragment] subclass.
 */
class GalleryFragment : Fragment() {

    private lateinit var viewModel: GalleryViewModel

    private lateinit var adapter: GalleryPicturesAdapter
    private lateinit var pictures: ArrayList<GalleryPicture>

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
        val layoutManager = GridLayoutManager(context, 3)
        rv.layoutManager = layoutManager
        rv.addItemDecoration(SpaceItemDecoration(8))
        pictures = ArrayList(viewModel.getGallerySize(context!!))
        adapter = GalleryPicturesAdapter(pictures, 10)
        rv.adapter = adapter


        adapter.setOnClickListener { galleryPicture ->
            showToast(galleryPicture.path)
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
            //super.onBackPressed()

        }

        ivBack.setOnClickListener {
            //onBackPressed()
        }
        loadPictures(25)

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
            showToast("Permission Required to Fetch Gallery.")
            //super.onBackPressed()
        }
    }


}
