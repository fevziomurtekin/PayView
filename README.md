[![](https://jitpack.io/v/fevziomurtekin/PayView.svg)](https://jitpack.io/#fevziomurtekin/PayView) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-PayView-green.svg?style=flat )]( https://android-arsenal.com/details/1/7724 )

<p align="center">
<img src="art/banner.png"/>
<br>
💳 Payment View library for Credit and Debit Card. 
</p>


<p align="center"><img src="art/visa.svg" width="55" height="55"/>&nbsp;&nbsp;<img src="art/mastercard.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/americanexpress.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/jcb.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/discover.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/dinnersclub.svg" width="55" height="55"/>&nbsp;&nbsp;<img src="art/undefined.svg" width="55" height="55"/></p> 

<br>


# Demo

<p align="center">
<img src="art/record.gif" width="360"  height="640" />
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
      implementation 'com.github.fevziomurtekin:PayView:1.0.0'
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
    />
```

## Listeners

```kotlin
   payview.setOnDataChangedListener(object : Payview.OnChangelistener{
            override fun onChangelistener(payModel: PayModel?) {
                Log.d("payView", "data : ${payModel?.cardOwnerName}")

            }

        })
        
    payview.setPayOnclickListener(View.OnClickListener {
        Log.d("payView "," clicked.")

    })
      
```


 # Attributes

  | Attribute | Description |
| --- | --- |
| `cardBgColor` | The color in int of the card background color (by default android.R.color.holo_blue_light) | 
| `cardFgColor` | The color in int of the card foreground color (by default android.R.color.white)|
| `cardTextColor` | The color in int of the card text color (by default android.R.color.black)|
| `cardAnimationType` | Animation in AnimationType of the PayView (by default horizontal)|
| `cardNameTextSize` | The size in sp of the search text size (by default 15sp) |
| `cardNoTextSize` | The size in sp of the search text size (by default 14sp)|
|`cardYearTextSize`|The size in sp of the search text size (by default 13sp) |
| `cardMonthTextSize` |The size in sp of the search text size (by default 13sp) |
| `cardCvTextSize`|The size in sp of the result text size (by default 14sp) |
|`cardNumberHelperText`| Default text, "You must enter your 16-digit card number. "|
|`cardNameHelperText`| Default text "Enter to card name. You'll enter max 25 characters"|

## TODO for new Version

- [x] Horizontal/vertical Animation.
- [x] Card design programmatically will changed. 

## License
The Apache License 2.0 - see [`LICENSE`](LICENSE) for more details


