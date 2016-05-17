# CountryCodePicker

![](http://s32.postimg.org/tg27055p1/list_View.png)
![](http://s32.postimg.org/osa34exat/list_View_Search.png)
![](http://s32.postimg.org/e9g2f3ytx/dialog.png)
![](http://s32.postimg.org/mlgvdh2j9/dialog_Search.png)  

CountryCodePicker will help users to search and select country to get the selected country name, dial code and ISO code.

# How to use
### **Step 1** Add it in your root build.gradle at the end of repositories:

```sh
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

### **Step 2** Add the dependency:

```sh
dependencies {
    compile 'com.github.ReemHesham:CountryCodePicker:1.0'
}
```

### **Step 3** Use CountryCodeList in your activity:  

#### A. As List activity  

```sh
Intent codeIntent = new Intent(MainActivity.this, CountryCodeListActivity.class);
startActivityForResult(codeIntent, 500);
```

#### And get selected country's details in onActivityResult callback

```sh
 if (resultCode == RESULT_OK && requestCode == 500 && data != null) {
         String countryName = data.getStringExtra(Utils.SELECTED_COUNTRY_NAME);
         String countryCode = data.getStringExtra(Utils.SELECTED_COUNTRY_CODE);
         try {
              yourImageView.setImageDrawable(Drawable.
              createFromStream(getAssets().open("Flags/" + data.getStringExtra
              (Utils.SELECTED_COUNTRY_ISO_CODE) + ".png"), null));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
```

#### B. As Dialog 

```sh
CountryCodeDialogFragment countryDialog = new CountryCodeDialogFragment();
countryDialog.show(getFragmentManager(), "countryCodesDialog");
```

#### Implement CountryCodeDialogListener to get selected country's details 

```sh
  mFragment.setOnCountryDialogListener(new CountryCodeDialogFragment.OnCountryDialogListener() {
            @Override
            public void onCountrySelected(String codeNumber, String codeName, String countryName) {
            }

            @Override
            public void onCancel() {
                //Do something...
            }
        });
```
