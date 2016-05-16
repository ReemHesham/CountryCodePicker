package reemhesham.countrycodepicker;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Reem on 8/4/2015.
 */
public class CountryCodeDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener {


    private ListView mCountryList;
    private ArrayList<CountryCodes> mCountryCodesList;
    private ArrayList<String> mCountries;
    public static EditText mInputSearch;
    private OnCountryDialogListener onDialogResultListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


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

        View view = inflater.inflate(R.layout.country_code_fragment, null, false);

        mCountryList = (ListView) view.findViewById(R.id.country_list);
        mInputSearch = (EditText) view.findViewById(R.id.inputSearch);

        final CountryAdapter adapter = new CountryAdapter(getActivity(),
                R.layout.flags_country_codes, mCountryCodesList);

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

                String str = null;
                if (adapter.countFilteredData(arg0.toString()) > 0)
                {
                    adapter.getFilter().filter(arg0.toString());
                }
                else
                {
                    if (arg0.length() > 0)
                    {
                        mInputSearch.setText(arg0.toString().substring(0, arg0.toString().length() - 1));
                        mInputSearch.setSelection(mInputSearch.getText().length());
                        str = arg0.toString().substring(0, arg0.toString().length() - 1);
                    }
                    else
                    {
                        str = "";
                    }
                    adapter.getFilter().filter(str);

                }

            }
        });
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();

        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        CountryCodes countryCode = (CountryCodes) parent.getAdapter().getItem(position);
        onDialogResultListener.onCountrySelected(countryCode.getCountryCode(), countryCode.getCountryISOCode(),
                countryCode.getCountryName());

        dismiss();
        mInputSearch.setText("");


    }


    public interface OnCountryDialogListener {
        public abstract void onCountrySelected(String codeNumber, String codeName, String countryName);

        public abstract void onCancel();
    }

    public void setOnCountryDialogListener(OnCountryDialogListener listener) {
        this.onDialogResultListener = listener;
    }


}
