package reemhesham.countrycodepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class CountryCodeListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView mCountryList;
    private ArrayList<CountryCodes> mCountryCodesList;
    private ArrayList<String> mCountries;
    private EditText mInputSearch ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_code_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCountryList = (ListView) findViewById(R.id.country_list);
        mInputSearch = (EditText) findViewById(R.id.inputSearch);

        mCountries = new ArrayList<>();
        mCountryCodesList = new ArrayList<>();

        if(mCountries.size() == 0)
        {
            mCountries = Utils.getCountriesNameArrayList();
        }

        if(mCountryCodesList.size() == 0)
        {
            mCountryCodesList = Utils.getCountriesCodeArrayList(mCountries);
        }

        final CountryAdapter adapter = new CountryAdapter(this, R.layout.flags_country_codes, mCountryCodesList);
        mCountryList.setAdapter(adapter);
        mCountryList.setTextFilterEnabled(true);
        adapter.addAll(mCountryCodesList);
        mCountryList.setOnItemClickListener(this);

        mInputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stu
                String str ;
                if (adapter.countFilteredData(arg0.toString()) > 0) {
                    adapter.getFilter().filter(arg0.toString());
                } else {
                    if (arg0.length() > 0) {
                        mInputSearch.setText(arg0.toString().substring(0, arg0.toString().length() - 1));
                        mInputSearch.setSelection(mInputSearch.getText().length());
                        str = arg0.toString().substring(0, arg0.toString().length() - 1);
                    } else {
                        str = "";
                    }
                    adapter.getFilter().filter(str);

                }

            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CountryCodes countryCode = (CountryCodes) parent.getAdapter().getItem(position);
        Intent output = new Intent();
        output.putExtra(Utils.SELECTED_COUNTRY_CODE,countryCode.getCountryCode());
        output.putExtra(Utils.SELECTED_COUNTRY_ISO_CODE, countryCode.getCountryISOCode());
        output.putExtra(Utils.SELECTED_COUNTRY_NAME, countryCode.getCountryName());

        this.setResult(this.RESULT_OK, output);
        mInputSearch.setText("");

        finish();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home)
        {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
