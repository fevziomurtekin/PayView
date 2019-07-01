package com.fevziomurtekin.payview

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.IntegerRes
import androidx.core.widget.NestedScrollView
import com.fevziomurtekin.payview.commons.AnimationType
import com.fevziomurtekin.payview.commons.CardType
import com.fevziomurtekin.payview.data.PayModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Payview : NestedScrollView, View.OnFocusChangeListener {

    /**
     * Data changed on listener
     * */
    interface OnChangelistener{
        fun onChangelistener(payModel: PayModel?)
    }


    /** attributes of layout */
    private var cardBackgroundColor:Int = Color.parseColor("#ff33b5e5")
    private var cardNameHelperText : String = resources.getString(R.string.cardname_helper_text)
    private var cardNoHelperText : String = resources.getString(R.string.cardno_helper_text)
    private var cardNameTextSize : Int = 14
    private var cardNoTextSize : Int = 14
    private var cardYearTextSize : Int = 13
    private var cardMonthTextSize : Int = 13
    private var cardCvTextSize : Int = 14


    /** Views on layout.*/
    private lateinit var rl_form: RelativeLayout
    private lateinit var rl_back: RelativeLayout
    private lateinit var rl_front: RelativeLayout
    private lateinit var tv_card_cv: TextView
    private lateinit var iv_card_type: ImageView
    private lateinit var tv_card_one: TextView
    private lateinit var tv_card_two: TextView
    private lateinit var tv_card_three: TextView
    private lateinit var tv_card_four: TextView
    private lateinit var tv_card_owner: TextView
    private lateinit var tv_card_month: TextView
    private lateinit var tv_card_year: TextView
    private lateinit var til_card_name:TextInputLayout
    private lateinit var til_card_no:TextInputLayout
    private lateinit var til_card_month:TextInputLayout
    private lateinit var til_card_year:TextInputLayout
    private lateinit var til_card_cv:TextInputLayout
    private lateinit var tev_card_name:TextInputEditText
    private lateinit var tev_card_no:TextInputEditText
    private lateinit var tev_card_month:TextInputEditText
    private lateinit var tev_card_year:TextInputEditText
    private lateinit var tev_card_cv:TextInputEditText
    private lateinit var btn_pay:Button

    private var lastMood:Char = 'i'

    private var payModel:PayModel?=null

    private var onChangeListener:OnChangelistener?=null

    private var cardType : CardType = CardType.MASTERCARD



    constructor(context:Context) : super(context) { init(context,null,0,0) }

    constructor(context: Context,attrs:AttributeSet?) : super(context,attrs){ init(context,attrs,0,0)}

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr:Int) :super(context,attrs,defStyleAttr){ init(context,attrs,defStyleAttr,0)}

  /*  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr, defStyleRes)
    }*/


    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        Log.d("PayView","View : ${v!!.id} hasFocus : $hasFocus" )
        if(hasFocus) {
            when (v!!.id) {
                R.id.tev_card_name -> showAnimation('i')
                R.id.tev_card_no -> showAnimation('i')
                R.id.tev_card_year -> showAnimation('i')
                R.id.tev_card_month -> showAnimation('i')
                R.id.tev_card_cv-> showAnimation('o')
            }
        }
    }


    private fun init(context: Context,attrs: AttributeSet?,defStyleAttr: Int,defStyleRes: Int){
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.payview_layout,null)

        this@Payview.addView(layout.findViewById(R.id.rl_parent))

        context.theme?.obtainStyledAttributes(attrs, R.styleable.Payview, defStyleAttr, defStyleRes).let {
            if(it!=null){
                cardBackgroundColor=it.getInt(R.styleable.Payview_cardBgColor, Color.parseColor("#ff33b5e5"))
                cardNameHelperText=it.getString(R.styleable.Payview_cardNameHelperText).let { s-> s?.toString()
                    ?: cardNameHelperText }
                cardNoHelperText=it.getString(R.styleable.Payview_cardNumberHelperText).let { s-> s?.toString()
                    ?: cardNoHelperText }
                cardNameTextSize=it.getInt(R.styleable.Payview_cardNameTextSize,14)
                cardNoTextSize=it.getInt(R.styleable.Payview_cardNoTextSize,14)
                cardYearTextSize=it.getInt(R.styleable.Payview_cardYearTextSize,13)
                cardMonthTextSize=it.getInt(R.styleable.Payview_cardMonthTextSize,13)
                cardCvTextSize=it.getInt(R.styleable.Payview_cardCvTextSize,14)
                animationType = it.getInteger(R.styleable.Payview_cardAnimationType,AnimationType.HORIZONTAL)
                initViews()
                initData()
            }
        }

    }

    fun setOnDataChangedListener(onChangelistener:OnChangelistener){
        this@Payview.onChangeListener = onChangelistener
    }

    fun setPayOnclickListener(onClickListener: OnClickListener){
        btn_pay.setOnClickListener(onClickListener)
    }

    /**
     * attributes init to components.
     * */
    private fun initViews(){
        rl_form = this@Payview.findViewById(R.id.rl_form)
        rl_front = this@Payview.findViewById(R.id.rl_front)
        rl_back = this@Payview.findViewById(R.id.rl_back)
        tv_card_cv = this@Payview.findViewById(R.id.tv_card_cv)
        iv_card_type = this@Payview.findViewById(R.id.iv_card_type)
        tv_card_one = this@Payview.findViewById(R.id.tv_card_one)
        tv_card_two = this@Payview.findViewById(R.id.tv_card_two)
        tv_card_three = this@Payview.findViewById(R.id.tv_card_three)
        tv_card_four = this@Payview.findViewById(R.id.tv_card_four)
        tv_card_owner = this@Payview.findViewById(R.id.tv_card_owner)
        tv_card_month = this@Payview.findViewById(R.id.tv_card_month)
        tv_card_year = this@Payview.findViewById(R.id.tv_card_year)
        til_card_name = this@Payview.findViewById(R.id.til_card_name)
        til_card_no = this@Payview.findViewById(R.id.til_card_no)
        til_card_month = this@Payview.findViewById(R.id.til_card_month)
        til_card_year = this@Payview.findViewById(R.id.til_card_year)
        til_card_cv = this@Payview.findViewById(R.id.til_card_cv)
        tev_card_name = this@Payview.findViewById(R.id.tev_card_name)
        tev_card_no = this@Payview.findViewById(R.id.tev_card_no)
        tev_card_month = this@Payview.findViewById(R.id.tev_card_month)
        tev_card_year = this@Payview.findViewById(R.id.tev_card_year)
        tev_card_cv = this@Payview.findViewById(R.id.tev_card_cv)
        btn_pay = this@Payview.findViewById(R.id.btn_pay)

        rl_form.setBackgroundColor(cardBackgroundColor)
        til_card_name.helperText=cardNameHelperText
        til_card_no.helperText=cardNoHelperText
        tev_card_name.textSize = cardNameTextSize.toFloat()
        tev_card_no.textSize = cardNoTextSize.toFloat()
        tev_card_year.textSize = cardYearTextSize.toFloat()
        tev_card_month.textSize = cardMonthTextSize.toFloat()
        tev_card_cv.textSize = cardCvTextSize.toFloat()


        tev_card_name.onFocusChangeListener = this
        tev_card_no.onFocusChangeListener = this
        tev_card_year.onFocusChangeListener = this
        tev_card_month.onFocusChangeListener = this
        tev_card_cv.onFocusChangeListener = this

        tev_card_name.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                tv_card_owner.text = s.toString()
                initData()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        tev_card_no.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                    Log.d("PayView"," length : ${s.toString().length}")
                    when (s.toString().length) {
                        in 0..4 -> {
                            tv_card_one.text = s.toString()
                            tv_card_two.text = ""
                            tv_card_three.text =""
                            tv_card_four.text = ""
                        }
                        in 5..8 -> {
                            Log.d("PayView","1")
                            tv_card_one.text = s.toString().substring(0, 4)
                            tv_card_two.text = s.toString().substring(4, s.toString().length)
                            tv_card_three.text =""
                            tv_card_four.text = ""

                        }
                        in 9..12 -> {
                            Log.d("PayView","2")
                            tv_card_one.text = s.toString().substring(0, 4)
                            tv_card_two.text = s.toString().substring(4, 8)
                            tv_card_three.text = s.toString().substring(8, s.toString().length)
                            tv_card_four.text = ""
                        }
                        else -> {
                            Log.d("PayView","3")
                            tv_card_one.text = s.toString().substring(0, 4)
                            tv_card_two.text = s.toString().substring(4, 8)
                            tv_card_three.text = s.toString().substring(8, 12)
                            tv_card_four.text = s.toString().substring(12, s.toString().length)
                        }
                    }
                    initData()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        tev_card_year.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                tv_card_year.text = s.toString()
                initData()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        tev_card_month.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                tv_card_month.text = s.toString()
                initData()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        tev_card_cv.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                tv_card_cv.text = s.toString()
                initData()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    /**
     * When first created and change edittext, change to the data model.
     * */
    private fun initData(){
        payModel = PayModel(
            tv_card_owner.text.toString(),
            tv_card_year.text.toString().toInt(),
            tv_card_month.text.toString().toInt(),
            "${tv_card_one.text}${tv_card_two.text}${tv_card_three.text}${tv_card_four.text}",
            tv_card_cv.text.toString()
        )
        if(!payModel?.cardNo.isNullOrEmpty())
            checkCardType(payModel?.cardNo)
        else
            checkCardType("123")

        this@Payview.onChangeListener?.onChangelistener(payModel)
    }

    /**
     * Check @param cardNo =>
     *                       first character 4 visa
     *                       first character 6 discover
     *                       first character 5 masterCard
     *                       first two character 35 JCB
     *                       first two character 30-38 dinner
     *                       first two character 37 american express*/
    private fun checkCardType(cardNo: String?){
        when(cardNo?.substring(0,1).toString().toInt()){
            3->{
                when(cardNo?.substring(0,2)?.toInt()){
                    in 30..38->{
                        cardType = CardType.DINNERSCLUB
                        iv_card_type.setImageResource(R.drawable.ic_dinners)
                    }
                    35->{
                        cardType = CardType.JCB
                        iv_card_type.setImageResource(R.drawable.ic_jcb)
                    }
                    37->{
                        cardType = CardType.AMERICANEXPRESS
                        iv_card_type.setImageResource(R.drawable.ic_american_express)
                    }
                    else->{
                        cardType = CardType.UNDEFINED
                        iv_card_type.setImageResource(R.drawable.ic_warning)
                    }
                }
            }
            4->{
                cardType = CardType.VISA
                iv_card_type.setImageResource(R.drawable.ic_visa)
            }
            5->{
                cardType = CardType.MASTERCARD
                iv_card_type.setImageResource(R.drawable.ic_mastercard)
            }
            6->{
                cardType = CardType.DISCOVER
                iv_card_type.setImageResource(R.drawable.ic_discover)
            }
            else->{
                cardType = CardType.UNDEFINED
                iv_card_type.setImageResource(R.drawable.ic_warning)
            }
        }

        payModel?.cardType = cardType

    }

    /**
     * @param mood => 'i' = in animation
     *             => 'o' = out animation
     * */
    private fun showAnimation(mood: Char){
        System.gc()
        if(mood == 'o' && mood != lastMood){
            val set = AnimatorInflater.loadAnimator(this@Payview.context, R.animator.front_horizontal_in) as AnimatorSet
            set.apply {
                setTarget(rl_front)
            }
            val set1 = AnimatorInflater.loadAnimator(this@Payview.context, R.animator.front_horizontal_out) as AnimatorSet
            set1.apply {
                setTarget(rl_back)
            }

            val animator = AnimatorSet()
            animator.playTogether(set, set1)
            animator.start()

        }else if (mood == 'i' && mood!=lastMood){
            val set = AnimatorInflater.loadAnimator(this@Payview.context, R.animator.back_horizontal_in) as AnimatorSet
            set.apply {
                setTarget(rl_front)
            }
            val set1 = AnimatorInflater.loadAnimator(this@Payview.context, R.animator.back_horizontal_out) as AnimatorSet
            set1.apply {
                setTarget(rl_back)
            }

            val animator = AnimatorSet()
            animator.playTogether(set, set1)
            animator.start()
        }

        lastMood = mood
    }

}