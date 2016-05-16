package reemhesham.countrycodepicker.example;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;

import reemhesham.countrycodepicker.*;


public class MainActivity extends AppCompatActivity {

    private TextView mCountryCode, mCountryName;
    private ImageView mFlagImage;
    private CountryCodeDialogFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maint);


        mCountryCode = (TextView)findViewById(R.id.country_code_number);
        mCountryName = (TextView)findViewById(R.id.country_code_name);
        mFlagImage = (ImageView)findViewById(R.id.flag_Image);
        Button btnCountryCodeDialog = (Button)findViewById(R.id.btnCountryCodeDialog);
        Button btnCountryCodeList = (Button)findViewById(R.id.btnCountryCodeList);
        mFragment = new CountryCodeDialogFragment();
        btnCountryCodeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFragment.show(getFragmentManager(), "countryCodesDialog");
            }
        });

        mFragment.setOnCountryDialogListener(new CountryCodeDialogFragment.OnCountryDialogListener() {

            @Override
            public void onCountrySelected(String codeNumber, String codeName, String countryName) {

                mCountryName.setText(countryName);
                mCountryCode.setText(codeNumber);

                try {
                    mFlagImage.setImageDrawable(Drawable.createFromStream(getAssets().open("Flags/" + codeName
                            + ".png"), null));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onCancel() {
                //Do something...
            }
        });


        btnCountryCodeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent codeIntent = new Intent(MainActivity.this, CountryCodeListActivity.class);
                startActivityForResult(codeIntent, 500);
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK && requestCode == 500 && data != null ){


            mCountryName.setText(data.getStringExtra(Utils.SELECTED_COUNTRY_NAME));
            mCountryCode.setText(data.getStringExtra(Utils.SELECTED_COUNTRY_CODE));

            try {
           mFlagImage.setImageDrawable(Drawable.createFromStream(getAssets().open("Flags/" + data.getStringExtra(Utils.SELECTED_COUNTRY_ISO_CODE)
                   + ".png"), null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

    }

}
