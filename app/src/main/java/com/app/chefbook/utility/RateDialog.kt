package com.app.chefbook.utility

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.app.chefbook.R
import com.app.chefbook.data.remote.ServiceCallBack
import com.app.chefbook.data.remote.service.postService.PostService
import com.app.chefbook.di.componentFragment
import com.app.chefbook.model.serviceModel.requestModel.UserPostRate
import javax.inject.Inject

class RateDialog(private val postId: String) : DialogFragment() {

    @Inject
    lateinit var postService: PostService

    var imgStarOne: ImageView? = null
    var imgStarSecond: ImageView? = null
    var imgStarThird: ImageView? = null
    var imgStarFourth: ImageView? = null
    var imgStarFifth: ImageView? = null
    private var btnSendRate: Button? = null
    var postRate: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_rate_select, container)
        componentFragment.inject(this)

        imgStarOne = view.findViewById(R.id.starOne)
        imgStarSecond = view?.findViewById(R.id.starSecond)
        imgStarThird = view?.findViewById(R.id.starThird)
        imgStarFourth = view?.findViewById(R.id.starFourth)
        imgStarFifth = view?.findViewById(R.id.starFifth)
        btnSendRate = view.findViewById(R.id.btnSendRate)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgStarOne?.setOnClickListener {
            imgStarOne!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarSecond!!.setImageResource(R.drawable.ic_star_24dp)
            imgStarThird!!.setImageResource(R.drawable.ic_star_24dp)
            imgStarFourth!!.setImageResource(R.drawable.ic_star_24dp)
            imgStarFifth!!.setImageResource(R.drawable.ic_star_24dp)
            postRate = 1
        }
        imgStarSecond?.setOnClickListener {
            imgStarOne!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarSecond!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarThird!!.setImageResource(R.drawable.ic_star_24dp)
            imgStarFourth!!.setImageResource(R.drawable.ic_star_24dp)
            imgStarFifth!!.setImageResource(R.drawable.ic_star_24dp)
            postRate = 2
        }
        imgStarThird?.setOnClickListener {
            imgStarOne!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarSecond!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarThird!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarFourth!!.setImageResource(R.drawable.ic_star_24dp)
            imgStarFifth!!.setImageResource(R.drawable.ic_star_24dp)
            postRate = 3
        }
        imgStarFourth?.setOnClickListener {
            imgStarOne!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarSecond!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarThird!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarFourth!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarFifth!!.setImageResource(R.drawable.ic_star_24dp)
            postRate = 4
        }
        imgStarFifth?.setOnClickListener {
            imgStarOne!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarSecond!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarThird!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarFourth!!.setImageResource(R.drawable.ic_star_gold_24dp)
            imgStarFifth!!.setImageResource(R.drawable.ic_star_gold_24dp)
            postRate = 5
        }

        btnSendRate?.setOnClickListener {
            if(postRate == 0) {
                Toast.makeText(context, "Lütfen önce puan seç.", Toast.LENGTH_SHORT).show()
            }
            else {
                postService.sendPostRate(UserPostRate(postId, postRate), object: ServiceCallBack<String> {
                    override fun onResponse(response: String) {
                        Toast.makeText(context, "Start Ok.", Toast.LENGTH_SHORT).show()

                    }

                    override fun onError(message: String) {
                        Toast.makeText(context, "Star Error.", Toast.LENGTH_SHORT).show()

                    }
                })
            }
        }
    }
}