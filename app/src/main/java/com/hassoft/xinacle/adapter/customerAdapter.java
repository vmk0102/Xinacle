package com.hassoft.xinacle.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.hassoft.xinacle.R;
import com.hassoft.xinacle.model.Customers;
import com.hassoft.xinacle.model.Salesman;

import java.util.ArrayList;


public class customerAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Customers> customers=null;
    customerFilter customerFilter=new customerFilter();
    ArrayList<Customers> BackupCustomers=null;


    public customerAdapter(Context context, ArrayList<Customers> customers){
        this.context=context;
        this.customers=customers;
        BackupCustomers=customers;

    }
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return customers.get(i);
    }

    @Override
    public int getCount() {
        return customers.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.spinnercustomersngle,viewGroup,false);
        }

        final Customers customer = (Customers) getItem(i);
        TextView optionName = (TextView) view.findViewById(R.id.itemCustomerName);
        optionName.setText(customer.getCustomerName());
        /*optionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SalesSummaryActivity.class);
                i.putExtra("FromDate","2020-01-01");
                i.putExtra("ToDate","2020-10-22");
                i.putExtra("CompanyID","1");
                context.startActivity(i);
            }
        });*/
        return  view;
    }

    @Override
    public Filter getFilter() {
        return customerFilter;
    }

    public class customerFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = BackupCustomers.size();
                filterResults.values = BackupCustomers;
                return filterResults;
            }
            final ArrayList<Customers> filterPersons = new ArrayList<>();
            for (Customers customer: BackupCustomers) {
                if (customer.getCustomerName().toLowerCase().contains(constraint)) {
                    filterPersons.add(customer);
                }
            }
            filterResults.count = filterPersons.size();
            filterResults.values = filterPersons;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            customers = (ArrayList<Customers>) results.values;
            notifyDataSetChanged();
        }
    }

}
