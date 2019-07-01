<p align="center"><img src="art/banner.png"/></p>

💳 Payment View library for Credit and Debit Card. 

<p align="left"><img src="art/visa.svg" width="55" height="55"/>&nbsp;&nbsp;<img src="art/mastercard.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/americanexpress.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/jcb.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/discover.svg"  width="55" height="55"/>&nbsp;&nbsp;<img src="art/dinnersclub.svg" width="55" height="55"/>&nbsp;&nbsp;<img src="art/undefined.svg" width="55" height="55"/></p> 

<br>

[![](https://jitpack.io/v/fevziomurtekin/PayView.svg)](https://jitpack.io/#fevziomurtekin/PayView) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-PayView-green.svg?style=flat )]( https://android-arsenal.com/details/1/7724 )

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
| `cardBgColor` |The color in int of the title text color (#ff33b5e5) | 
| `cardNameTextSize` | The size in sp of the search text size (by default 15sp) |
| `cardNoTextSize` | The size in sp of the search text size (by default 14sp)|
|`cardYearTextSize`|The size in sp of the search text size (by default 13sp) |
| `cardMonthTextSize` |The size in sp of the search text size (by default 13sp) |
| `cardCvTextSize`|The size in sp of the result text size (by default 14sp) |
|`cardNumberHelperText`| Default text, "You must enter your 16-digit card number. "|
|`cardNameHelperText`| Default text "Enter to card name. You'll enter max 25 characters"|

## Features

- [ ] Horizontal/vertical Animation.
- [ ] Card design programmatically will changed. 

## License
The Apache License 2.0 - see [`LICENSE`](LICENSE) for more details


