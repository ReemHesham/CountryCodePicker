package reemhesham.countrycodepicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reem on 8/4/2015.
 */
public class CountryAdapter extends ArrayAdapter<CountryCodes> implements Filterable {

    private Context mContext;
    private ArrayList<CountryCodes> mCountryCodes;
    private List codesList;
    private CountryFilter mCountryFilter;

    public CountryAdapter(Context context, int resource, ArrayList<CountryCodes> countryCodes) {
        super(context, resource);
        mContext = context;
        mCountryCodes = countryCodes ;
    }

    private class ViewHolder
    {
        ImageView flag;
        TextView countryName;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder ;
        CountryCodes countryCodes = getItem(position);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.flags_country_codes, null);
            holder = new ViewHolder();
            holder.flag = (ImageView) convertView.findViewById(R.id.flag);
            holder.countryName = (TextView) convertView.findViewById(R.id.country_name);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.countryName.setText(countryCodes.getCountryName());
        try
        {
            holder.flag.setImageDrawable(Drawable.createFromStream(mContext.getAssets()
                    .open("Flags/" + countryCodes.getCountryISOCode() + ".png"), null));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }


    @Override
    public Filter getFilter() {

        if (mCountryFilter == null) {

            mCountryFilter = new CountryFilter();
        }

        return mCountryFilter;
    }


    private class CountryFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {

                ArrayList<CountryCodes> filterList = new ArrayList<CountryCodes>();

                for (int i = 0; i < mCountryCodes.size(); i++) {

                    if (mCountryCodes.get(i).getCountryName().regionMatches(true, 0, constraint.toString()
                            , 0, constraint.length())) {

                        filterList.add(mCountryCodes.get(i));
                    }
                }

                results.count = filterList.size();
                results.values = filterList;

            } else {

                results.count = mCountryCodes.size();
                results.values = mCountryCodes;

            }

            return results;
        }

        //        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            codesList = (ArrayList<String>) results.values;
            notifyDataSetChanged();

            clear();
            addAll(codesList);
            notifyDataSetChanged();


        }
    }


    public int countFilteredData(String arg) {

        int filteredArraySize = 0;

        if (arg != null && arg.length() > 0) {

            ArrayList<CountryCodes> filteredList = new ArrayList<CountryCodes>();

            for (int i = 0; i < mCountryCodes.size(); i++) {

                if (mCountryCodes.get(i).getCountryName().regionMatches(true, 0, arg.toString(), 0, arg.length())) {

                    filteredList.add(mCountryCodes.get(i));

                }
            }
            filteredArraySize = filteredList.size();
        }

        return filteredArraySize;
    }

}
