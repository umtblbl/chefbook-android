package com.app.chefbook.ui.adapters.slidingMedia

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.app.chefbook.model.serviceModel.responseModel.UserFlowPost
import kotlinx.android.extensions.LayoutContainer

class SlidingMediaViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
    LayoutContainer