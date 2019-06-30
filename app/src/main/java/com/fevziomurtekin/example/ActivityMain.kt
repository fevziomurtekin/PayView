package com.fevziomurtekin.example

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationSet
import androidx.appcompat.app.AppCompatActivity
import com.fevziomurtekin.payview.Payview
import com.fevziomurtekin.payview.data.PayModel
import kotlinx.android.synthetic.main.activity_main.*

class ActivityMain : AppCompatActivity(), Payview.OnChangelistener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        payview.setOnDataChangedListener(this)
        payview.setPayOnclickListener(View.OnClickListener {
            Log.d("parentView "," clicked.")

        })

    }

    override fun onChangelistener(payModel: PayModel?) {
        Log.d("parentView", "data : ${payModel?.cardOwnerName}")
    }

}