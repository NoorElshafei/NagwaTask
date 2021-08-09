package com.egypt.nagwatask.util

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.egypt.nagwatask.R
import com.egypt.nagwatask.model.MovieModel
import com.egypt.nagwatask.ui.main.MainActivity

class DialogCustom : DialogFragment() {
    private lateinit var progressText: TextView
    private lateinit var buttonCancel: Button
    private lateinit var statusText: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var movieModel: MovieModel.MovieModelItem



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.MyTheme)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_layout, container, false)

        buttonCancel = view.findViewById(R.id.cancel_action) as Button
        statusText = view.findViewById(R.id.status_text) as TextView
        progressText = view.findViewById(R.id.progress_text) as TextView
        progressBar = view.findViewById(R.id.progressBar) as ProgressBar
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieModel = arguments?.getParcelable("movieModel")!!

        setProgressAnimate(progressBar)
        setProgressAnimateText(progressText)

    }

    private fun setProgressAnimate(
        pb: ProgressBar
    ) {
        val animation = ObjectAnimator.ofInt(pb, "progress", 0, 100)
        animation.duration = 10000

        animation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                movieModel.status = "Downloading"
                statusText.text = movieModel.status
            }

            override fun onAnimationEnd(animation: Animator) {
                (activity as MainActivity?)!!.updateMovies()
                movieModel.status = "Downloaded"
                statusText.text = movieModel.status
                Toast.makeText(
                    context,
                    "${movieModel.name} downloaded successfully",
                    Toast.LENGTH_SHORT
                ).show()
                dismiss()
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })

        animation.start()
    }

    private fun setProgressAnimateText(textView: TextView) {
        val animator = ValueAnimator()
        animator.setObjectValues(0, 100)
        animator.addUpdateListener { animation: ValueAnimator ->
            textView.text = animation.animatedValue.toString()
        }
        animator.duration = 10000
        animator.start()
    }
}