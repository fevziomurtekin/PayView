package com.fevziomurtekin.payview

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.annotation.IntegerRes
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import com.fevziomurtekin.payview.commons.AnimationType
import com.fevziomurtekin.payview.commons.CardType
import com.fevziomurtekin.payview.data.PayModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.payview_layout.view.*
import java.util.*

class Payview : NestedScrollView, View.OnFocusChangeListener {

    /**
     * Data changed on listener
     * */
    interface OnChangelistener{
        fun onChangelistener(payModel: PayModel?,isFillAllComponents:Boolean)
    }


    /** attributes of layout */
    private var cardBackgroundColor:Int = resources.getColor(android.R.color.holo_blue_light)
    private var cardForegoundColor:Int = resources.getColor(android.R.color.white)
    private var cardTextColor:Int = resources.getColor(android.R.color.black)
    private var cardNameHelperText : String = resources.getString(R.string.cardname_helper_text)
    private var cardNoHelperText : String = resources.getString(R.string.cardno_helper_text)
    private var cardYearErrorText:String = resources.getString(R.string.cardyear_error_text)
    private var cardMonthErrorText:String= resources.getString(R.string.cardmonth_error_text)
    private var cardCvErrorText:String= resources.getString(R.string.cardmonth_error_text)
    private var cardExpiredError:String= resources.getString(R.string.cardexpired_error_text)
    private var cardNameTextSize : Int = 14
    private var cardNoTextSize : Int = 14
    private var cardYearTextSize : Int = 13
    private var cardMonthTextSize : Int = 13
    private var cardCvTextSize : Int = 14
    private var cardBtnPayText : String = resources.getString(R.string.pay)
    private var cardNameHintText : String = resources.getString(R.string.cardname)
    private var cardNoHintText : String = resources.getString(R.string.cardno)
    private var cardMonthHintText : String = resources.getString(R.string.cardmonth)
    private var cardYearHintText : String = resources.getString(R.string.cardyear)
    private var cardCvHintText : String = resources.getString(R.string.cardcv)


    /** Views on layout.*/
    private lateinit var rl_form: RelativeLayout
    private lateinit var rl_back: RelativeLayout
    private lateinit var rl_front: RelativeLayout
    private lateinit var tv_card_cv: TextView
    private lateinit var iv_card_type: ImageView
    private lateinit var view_black:View
    private lateinit var iv_chip: ImageView
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

    var isFillAllComponents=false
    private var lastMood:Char = 'i'
    private var isRemoveText = false
    private var payModel:PayModel?=null
    private var onChangeListener:OnChangelistener?=null
    private var cardType : CardType = CardType.MASTERCARD
    private var cardAnimationType:Int = AnimationType.HORIZONTAL

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
                cardBackgroundColor=it.getInt(R.styleable.Payview_cardBgColor, resources.getColor(android.R.color.holo_blue_light))
                cardForegoundColor = it.getInt(R.styleable.Payview_cardFgColor,resources.getColor(android.R.color.white))
                cardTextColor = it.getInt(R.styleable.Payview_cardTextColor,resources.getColor(android.R.color.black))
                cardNameHelperText=it.getString(R.styleable.Payview_cardNameHelperText).let { s-> s?.toString()
                    ?: cardNameHelperText }
                cardNoHelperText=it.getString(R.styleable.Payview_cardNumberHelperText).let { s-> s?.toString()
                    ?: cardNoHelperText }
                cardMonthErrorText=it.getString(R.styleable.Payview_cardMonthErrorText).let { s-> s?.toString()
                        ?: cardMonthErrorText }
                cardYearErrorText=it.getString(R.styleable.Payview_cardYearErrorText).let { s-> s?.toString()
                        ?: cardYearErrorText }
                cardCvErrorText=it.getString(R.styleable.Payview_cardCvErrorText).let { s-> s?.toString()
                        ?: cardCvErrorText }
                cardExpiredError=it.getString(R.styleable.Payview_cardExpiredErrorText).let { s-> s?.toString()
                        ?: cardExpiredError }
                cardNameTextSize=it.getInt(R.styleable.Payview_cardNameTextSize,14)
                cardNoTextSize=it.getInt(R.styleable.Payview_cardNoTextSize,14)
                cardYearTextSize=it.getInt(R.styleable.Payview_cardYearTextSize,13)
                cardMonthTextSize=it.getInt(R.styleable.Payview_cardMonthTextSize,13)
                cardCvTextSize=it.getInt(R.styleable.Payview_cardCvTextSize,14)
                cardBtnPayText=it.getString(R.styleable.Payview_cardBtnPayText).let { s-> s?.toString()
                    ?: cardBtnPayText }
                cardNameHintText=it.getString(R.styleable.Payview_cardNameHintText).let { s-> s?.toString()
                    ?: cardNameHintText }
                cardNoHintText=it.getString(R.styleable.Payview_cardNoHintText).let { s-> s?.toString()
                    ?: cardNoHintText }
                cardMonthHintText=it.getString(R.styleable.Payview_cardMonthHintText).let { s-> s?.toString()
                    ?: cardMonthHintText }
                cardYearHintText=it.getString(R.styleable.Payview_cardYearHintText).let { s-> s?.toString()
                    ?: cardYearHintText }
                cardCvHintText=it.getString(R.styleable.Payview_cardCvHintText).let { s-> s?.toString()
                    ?: cardCvHintText }
                cardAnimationType=it.getInt(R.styleable.Payview_cardAnimationType,AnimationType.HORIZONTAL)
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
    
    fun setPayText(text: String) {
        btn_pay.text = text
    }

    /**
     * attributes init to components.
     * */
    @SuppressLint("ResourceType", "ClickableViewAccessibility")
    private fun initViews(){
        rl_form = this@Payview.findViewById(R.id.rl_form)
        rl_front = this@Payview.findViewById(R.id.rl_front)
        rl_back = this@Payview.findViewById(R.id.rl_back)
        tv_card_cv = this@Payview.findViewById(R.id.tv_card_cv)
        iv_card_type = this@Payview.findViewById(R.id.iv_card_type)
        iv_chip = this@Payview.findViewById(R.id.iv_chip)
        view_black = this@Payview.findViewById(R.id.view_black)
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
        rl_front.backgroundTintList = ColorStateList.valueOf(cardForegoundColor)
        rl_back.backgroundTintList = ColorStateList.valueOf(cardForegoundColor)
        iv_chip.backgroundTintList = ColorStateList.valueOf(cardForegoundColor)

        if(cardForegoundColor == Color.WHITE)
            tv_card_cv.setBackgroundColor(context.resources.getColor(R.color.alternative_white_light))

        view_black.visibility = View.VISIBLE
        if(cardForegoundColor == Color.BLACK)
            view_black.setBackgroundColor(context.resources.getColor(android.R.color.white))
        else
            view_black.setBackgroundColor(context.resources.getColor(android.R.color.black))


        tv_card_owner.setTextColor(cardTextColor)
        tv_card_one.setTextColor(cardTextColor)
        tv_card_two.setTextColor(cardTextColor)
        tv_card_three.setTextColor(cardTextColor)
        tv_card_four.setTextColor(cardTextColor)
        tv_card_month.setTextColor(cardTextColor)
        tv_card_year.setTextColor(cardTextColor)


        til_card_name.helperText=cardNameHelperText
        til_card_no.helperText=cardNoHelperText
        tev_card_name.textSize = cardNameTextSize.toFloat()
        tev_card_no.textSize = cardNoTextSize.toFloat()
        tev_card_year.textSize = cardYearTextSize.toFloat()
        tev_card_month.textSize = cardMonthTextSize.toFloat()
        tev_card_cv.textSize = cardCvTextSize.toFloat()
        btn_pay.text = cardBtnPayText

        // Custom Hint Text
        til_card_name.hint = cardNameHintText
        til_card_no.hint = cardNoHintText
        til_card_month.hint = cardMonthHintText
        til_card_year.hint = cardYearHintText
        til_card_cv.hint = cardCvHintText

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
                        in 0..3 -> {
                            tv_card_one.text = s.toString()
                            tv_card_two.text = ""
                            tv_card_three.text = ""
                            tv_card_four.text = ""
                        }
                        in 5..8 -> {
                            Log.d("PayView", "1")
                            tv_card_one.text = s.toString().substring(0, 4)
                            tv_card_two.text = s.toString().substring(5, s.toString().length)
                            tv_card_three.text = ""
                            tv_card_four.text = ""

                        }
                        in 10..13 -> {
                            Log.d("PayView", "2")
                            tv_card_one.text = s.toString().substring(0, 4)
                            tv_card_two.text = s.toString().substring(5, 9)
                            tv_card_three.text = s.toString().substring(10, s.toString().length)
                            tv_card_four.text = ""
                        }
                        in 15..19 -> {
                            Log.d("PayView", "3")
                            tv_card_one.text = s.toString().substring(0, 4)
                            tv_card_two.text = s.toString().substring(5, 9)
                            tv_card_three.text = s.toString().substring(10, 14)
                            tv_card_four.text = s.toString().substring(14, s.toString().length)
                        }
                        4,9,14-> {
                            if(!isRemoveText)
                                s?.insert(s.toString().length," ")
                            else
                                s?.delete(s.toString().length-1,s.toString().length)
                        }
                    }
                    initData()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //Log.d("PayView", "start: $start count: $count after: $after s:${s?.length}")
                isRemoveText = start < s!!.length
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


            }

        })

        tev_card_year.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                tv_card_year.text = s.toString()
                tev_card_month.error = null
                tev_card_year.error = null
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
                tev_card_month.error = null
                tev_card_year.error = null
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


        btn_pay.setOnTouchListener(object : OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                isFillAllComponents=checkAllFormFields()
                return false
            }
        })
    }

    /**
     * When first created and change edittext, change to the data model.
     * */
    private fun initData(){
        payModel = PayModel(
            tv_card_owner.text.toString(),
            tv_card_year.text.toString().let {
             if(it == "") 0 else it.toInt()
            },
            tv_card_month.text.toString().let {
                if(it == "") 0 else it.toInt()
            },
            "${tv_card_one.text}${tv_card_two.text}${tv_card_three.text}${tv_card_four.text}",
            tv_card_cv.text.toString()
        )
        if(!payModel?.cardNo.isNullOrEmpty()) {
            payModel?.cardNo?.replace(" ", "")
            checkCardType(payModel?.cardNo)
        }else
            checkCardType("123")

        this@Payview.onChangeListener?.onChangelistener(payModel,isFillAllComponents)
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
                if(cardNo?.length!! > 2) {
                    when (cardNo?.substring(0, 2)?.toInt()) {
                        in 30..38 -> {
                            cardType = CardType.DINNERSCLUB
                            iv_card_type.setImageResource(R.drawable.ic_dinners)
                        }
                        35 -> {
                            cardType = CardType.JCB
                            iv_card_type.setImageResource(R.drawable.ic_jcb)
                        }
                        37 -> {
                            cardType = CardType.AMERICANEXPRESS
                            iv_card_type.setImageResource(R.drawable.ic_american_express)
                        }
                        else -> {
                            cardType = CardType.UNDEFINED
                            iv_card_type.setImageResource(R.drawable.ic_warning)
                        }
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
            val set = AnimatorInflater.loadAnimator(this@Payview.context,
                if(cardAnimationType == AnimationType.HORIZONTAL)
                    R.animator.front_horizontal_in
                else
                    R.animator.front_vertical_in
            ) as AnimatorSet
            set.apply {
                setTarget(rl_front)
            }
            val set1 = AnimatorInflater.loadAnimator(this@Payview.context,
                if(cardAnimationType == AnimationType.HORIZONTAL)
                    R.animator.front_horizontal_out
                else
                    R.animator.front_vertical_out
            ) as AnimatorSet
            set1.apply {
                setTarget(rl_back)
            }

            val animator = AnimatorSet()
            animator.playTogether(set, set1)
            animator.start()

        }else if (mood == 'i' && mood!=lastMood){
            val set = AnimatorInflater.loadAnimator(this@Payview.context,
                if(cardAnimationType == AnimationType.HORIZONTAL)
                    R.animator.back_horizontal_in
                else
                    R.animator.back_vertical_in
            ) as AnimatorSet
            set.apply {
                setTarget(rl_front)
            }
            val set1 = AnimatorInflater.loadAnimator(this@Payview.context,
                if(cardAnimationType == AnimationType.HORIZONTAL)
                    R.animator.back_horizontal_out
                else
                    R.animator.back_vertical_out
            ) as AnimatorSet
            set1.apply {
                setTarget(rl_back)
            }

            val animator = AnimatorSet()
            animator.playTogether(set, set1)
            animator.start()
        }

        lastMood = mood
    }


    /**
     * Cheking all form components.
     * */
    private fun checkAllFormFields():Boolean{
        Log.d("PayView","card no: ${tev_card_no.text?.toString()?.replace(" ","")}")
        this@Payview.apply {
            if(tev_card_name.text.toString().isNotEmpty()
                    && tev_card_cv.text.toString().length==3
                    && tev_card_month.text.toString().length==2
                    && tev_card_year.text.toString().length==2
                    && tev_card_no.text?.toString()?.replace(" ","")?.length in 14..16){

                /** card month/card year compare to now date.*/
                val nowMonth = Calendar.getInstance().get(Calendar.MONTH)
                val nowYear = Calendar.getInstance().get(Calendar.YEAR).toString().substring(2,4).toInt()
                return if(nowYear>tev_card_year.text.toString().toInt()
                        || (nowYear==tev_card_year.text.toString().toInt()
                                && nowMonth>=tev_card_month.text.toString().toInt())){
                    tev_card_year.error = cardExpiredError
                    tev_card_month.error = cardExpiredError
                    false
                }else true
            }
            else{
                var valid = true
                if(tev_card_name.text.isNullOrEmpty()) {
                    tev_card_name.error = cardNameHelperText
                    valid = false
                }
                if(tev_card_no.text?.toString()?.replace(" ","")?.length!=16) {
                    tev_card_no.error = cardNoHelperText
                    valid = false
                }
                if(tev_card_cv.text.isNullOrEmpty()
                        || tev_card_cv.text.toString().length!=3) {
                    tev_card_cv.error = cardCvErrorText
                    valid = false
                }


                if(tev_card_year.text.isNullOrEmpty()
                        || tev_card_year.text.toString().length!=2
                        || tev_card_year.text.toString().toInt()>99) {
                    tev_card_year.error = cardYearErrorText
                    valid = false
                }
                if(tev_card_month.text.isNullOrEmpty()
                        || tev_card_month.text.toString().length!=2
                        || tev_card_month.text.toString().toInt()<12) {
                    tev_card_month.error = cardMonthErrorText
                    valid = false
                }

                if (valid) {
                    /** card month/card year compare to now date.*/
                    val nowMonth = Calendar.getInstance().get(Calendar.MONTH)
                    val nowYear =
                        Calendar.getInstance().get(Calendar.YEAR).toString().substring(2, 4).toInt()
                    if (nowYear > tev_card_year.text.toString().toInt()
                        || (nowYear == tev_card_year.text.toString().toInt()
                                && nowMonth >= tev_card_month.text.toString().toInt())
                    ) {
                        tev_card_year.error = cardExpiredError
                        tev_card_month.error = cardExpiredError
                        valid = false
                    }
                }
                return valid
            }
        }
    }

}
