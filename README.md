[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6af0386ba9e24da7ad94a12e38cd3268)](https://app.codacy.com/app/fevziomurtekin/PayView?utm_source=github.com&utm_medium=referral&utm_content=fevziomurtekin/PayView&utm_campaign=Badge_Grade_Dashboard)
[![](https://jitpack.io/v/fevziomurtekin/PayView.svg)](https://jitpack.io/#fevziomurtekin/PayView) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-PayView-green.svg?style=flat )]( https://android-arsenal.com/details/1/7724 )![Twitter Follow](https://img.shields.io/twitter/follow/fevziomurtekin.svg?label=fevziomurtekin&style=social)

<p align="center">
<img src="art/banner.png"/>
<br>
💳 Payment View library for Credit and Debit Card. 
</p>


<p align="center"><img src="art/visa.svg" width="55" height="55"/>&nbsp;&nbsp;<img src="art/mastercard.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/americanexpress.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/jcb.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/discover.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/dinnersclub.svg" width="55" height="55"/>&nbsp;&nbsp;<img src="art/undefined.svg" width="55" height="55"/></p> 

<br>


# Demo

<p align="center">
<img src="art/newrecord.gif" width="360"  height="640" />
</p>

# Setup
## Gradle
```Gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
  
  .....

dependencies {
      implementation 'com.github.fevziomurtekin:PayView:1.0.3'
  }
}
```

## Layout

```xml
 <com.fevziomurtekin.payview.Payview
        android:id="@+id/payview"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:cardBgColor="@android:color/holo_blue_light"
        app:cardFgColor="@android:color/white"
        app:cardTextColor="@color/black"
        app:cardNameHelperText="Enter to card name. Max 25 characters."
        app:cardCvTextSize="14"
        app:cardNoTextSize="14"
        app:cardNumberHelperText="You must enter your 16-digit card number."
        app:cardYearTextSize="13"
        app:cardNameTextSize="15"
        app:cardMonthTextSize="13"
        app:cardAnimationType="vertical"
        app:cardCvErrorText="You must enter 3-digit characters"
        app:cardMonthErrorText="You must enter 2-digit characters and you'll enter to number the most digit-value is '12'"
        app:cardYearErrorText="You must enter 2-digit characters and you'll enter to number the most digit-value is '99'"
        app:cardExpiredErrorText="Your card has expired. Please enter the usage date correctly."
    />
```

## Listeners

```kotlin
   payview.setOnDataChangedListener(object : Payview.OnChangelistener{
            override fun onChangelistener(payModel: PayModel?) {
                Log.d("PayView", "data : ${payModel?.cardOwnerName} \n " +
                        "is Fill all form component : $isFillAllComponents")

            }

        })
        
    payview.setPayOnclickListener(View.OnClickListener {
        Log.d("PayView "," clicked. iss Fill all form Component : ${payview.isFillAllComponents}")

    })
      
```


 # Attributes

|Attribute|Description|
|---|---|
|`cardBgColor`|The color in int of the card background color (by default android.R.color.holo_blue_light)| 
|`cardFgColor`|The color in int of the card foreground color (by default android.R.color.white)|
|`cardTextColor`|The color in int of the card text color (by default android.R.color.black)|
|`cardAnimationType`|Animation in AnimationType of the PayView (by default horizontal)|
|`cardNameTextSize`|The size in sp of the search text size (by default 15sp) |
|`cardNoTextSize`|The size in sp of the search text size (by default 14sp)|
|`cardYearTextSize`|The size in sp of the search text size (by default 13sp) |
|`cardMonthTextSize`|The size in sp of the search text size (by default 13sp) |
|`cardCvTextSize`|The size in sp of the result text size (by default 14sp) |
|`cardNumberHelperText`|Default text, "You must enter your 16-digit card number. "|
|`cardNameHelperText`|Default text "Enter to card name. You'll enter max 25 characters"|
|`cardCvErrorText`|Default text "You must enter 3-digit characters"|
|`cardMonthErrorText`|Default text ""You must enter 2-digit characters and you'll enter to number the most digit-value is '12'"|
|`cardYearErrorText`| Default text "You must enter 2-digit characters and you'll enter to number the most digit-value is '99'"|
|`cardExpiredErrorText`| Default text "Your card has expired. Please enter the usage date correctly."|


## License
The Apache License 2.0 - see [`LICENSE`](LICENSE) for more details


