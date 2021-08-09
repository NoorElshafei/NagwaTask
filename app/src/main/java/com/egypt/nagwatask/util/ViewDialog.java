package com.egypt.nagwatask.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.egypt.nagwatask.R;

public class ViewDialog extends Dialog {
    private Context context;
    private Button dialogButton;
    private TextView progressText, statusText;
    private ProgressBar progressBar;

    public ViewDialog(@NonNull Context context) {
        super(context);
        this.context = context;

    }

    public void showDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);

        dialogButton = (Button) dialog.findViewById(R.id.cancel_action);
        statusText = (TextView) dialog.findViewById(R.id.status_text);
        progressText = (TextView) dialog.findViewById(R.id.progress_text);
        progressBar = (ProgressBar) dialog.findViewById(R.id.progressBar);
        dialogButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    private void setProgressAnimate(ProgressBar pb, int progressTo) {
        ObjectAnimator animation = ObjectAnimator.ofInt(pb, "progress", pb.getProgress(), progressTo);
        animation.setDuration(10000);
        animation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animation.start();
    }

    private void setProgressAnimateText(TextView textView) {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(0, 100);// here you set the range, from 0 to "count" value
        animator.addUpdateListener(animation -> textView.setText(String.valueOf(animation.getAnimatedValue())));
        animator.setDuration(10000);
        animator.start();
    }
}
