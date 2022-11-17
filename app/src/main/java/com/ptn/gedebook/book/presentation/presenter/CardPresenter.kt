package com.ptn.gedebook.book.presentation.presenter

import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import com.ptn.gedebook.R
import com.ptn.gedebook.book.domain.entity.Book
import com.ptn.gedebook.core.GlideApp

class CardPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        return ViewHolder(ImageCardView(parent?.context))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val view = viewHolder?.view
        if (view is ImageCardView && item is Book) {
            view.titleText = item.title
            view.contentText = item.author
            view.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT)
            GlideApp.with(view.context)
                .load(item.imageUrl)
                .timeout(DEFAULT_GLIDE_TIMEOUT)
                .error(R.mipmap.ic_launcher)
                .into(view.mainImageView)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
        when (val view = viewHolder?.view) {
            is ImageCardView -> view.mainImage = null
        }
    }

    companion object {

        private const val CARD_WIDTH = 200

        private const val CARD_HEIGHT = 250

        private const val DEFAULT_GLIDE_TIMEOUT = 30_000
    }
}
