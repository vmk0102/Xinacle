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


public class salesmanAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Salesman> salesmen=null;
    salesmanFilter salesmanFilter = new salesmanFilter();
    ArrayList<Salesman> backupSalesman=null;


    public salesmanAdapter(Context context, ArrayList<Salesman> salesmen){
        this.context=context;
        this.salesmen=salesmen;
        backupSalesman=salesmen;

    }
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return salesmen.get(i);
    }

    @Override
    public int getCount() {
        return salesmen.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.spinnersalemansngle,viewGroup,false);
        }

        final Salesman salesman = (Salesman) getItem(i);
        TextView optionName = (TextView) view.findViewById(R.id.itemCustomerName);
        optionName.setText(salesman.getSalesmanName());
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
        return salesmanFilter;
    }

    public class salesmanFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = backupSalesman.size();
                filterResults.values = backupSalesman;
                return filterResults;
            }
            final ArrayList<Salesman> filterPersons = new ArrayList<>();
            for (Salesman salesman : backupSalesman) {
                if (salesman.getSalesmanName().toLowerCase().contains(constraint)) {
                    filterPersons.add(salesman);
                }
            }
            filterResults.count = filterPersons.size();
            filterResults.values = filterPersons;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            salesmen = (ArrayList<Salesman>) results.values;
            notifyDataSetChanged();
        }
    }
}


