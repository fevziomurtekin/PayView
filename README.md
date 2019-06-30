<p align="center"><img src="art/banner.png"></p>

💳 Payment View library for Credit and Debit Card. 

# Demo

<p align="center">
<img src="art/record.gif" width="270"  height="480" />
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
        app:cardBgColor="@color/colorAccent"
        app:cardNameHelperText="Enter to card name. Max 25 characters."
        app:cardCvTextSize="14"
        app:cardNoTextSize="14"
        app:cardNumberHelperText="You must enter your 16-digit card number."
        app:cardYearTextSize="13"
        app:cardNameTextSize="15"
        app:cardMonthTextSize="13"
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
| `cardBgColor` |The color in int of the title text color (R.color.textcolor => #ff33b5e5) | 
| `cardNameTextSize` | The size in sp of the search text size (by default 15sp) |
| `cardNoTextSize` | The size in sp of the search text size (by default 14sp)|
|`cardYearTextSize`|The size in sp of the search text size (by default 13sp) |
| `cardMonthTextSize` |The size in sp of the search text size (by default 13sp) |
| `cardCvTextSize`|The size in sp of the result text size (by default 14sp) |
|`cardNumberHelperText`| Default text, "You must enter your 16-digit card number. "|
|`cardNameHelperText`| Default text "Enter to card name. You'll enter max 25 characters"|

## License
The Apache License 2.0 - see [`LICENSE`](LICENSE) for more details


