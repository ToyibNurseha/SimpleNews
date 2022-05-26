package com.toyibnurseha.news_mvvm.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.toyibnurseha.news_mvvm.R
import com.toyibnurseha.news_mvvm.models.Article
import com.toyibnurseha.news_mvvm.ui.NewsActivity
import com.toyibnurseha.news_mvvm.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*

class ArticleFragment : Fragment(
    R.layout.fragment_article
) {
    lateinit var viewModel: NewsViewModel
    private val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as NewsActivity).viewModel
        val article = args.article
        viewModel.getIsNewsSaved(article.url).observe(viewLifecycleOwner) { id ->
            if (id?.url != null) {
                fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_favorited
                    )
                )
                fab.setOnClickListener {
                    deleteArticle(article, view)
                }
            } else {
                fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_favorite
                    )
                )
                fab.setOnClickListener {
                    saveArticle(article, view)
                }
            }
        }

        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    private fun deleteArticle(article: Article, view: View) {
        viewModel.deleteArticle(article)
        fab.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_favorited
            )
        )
        fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
        Snackbar.make(view, "Article Deleted", Snackbar.LENGTH_SHORT).show()
    }

    private fun saveArticle(article: Article, view: View) {
        viewModel.saveArticle(article)
        fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorited))
        Snackbar.make(view, "Article Saved!", Snackbar.LENGTH_SHORT).show()
    }

}