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
import com.hassoft.xinacle.model.Salesman;
import com.hassoft.xinacle.model.Supplier;

import java.util.ArrayList;


public class suppliersAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Supplier> suppliers=null;
    ArrayList<Supplier> BackupSupplier=null;
    supplierFilter supplierFilter=new supplierFilter();

    public suppliersAdapter(Context context, ArrayList<Supplier> suppliers){
        this.context=context;
        this.suppliers=suppliers;
        BackupSupplier=suppliers;

    }
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return suppliers.get(i);
    }

    @Override
    public int getCount() {
        return suppliers.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.spinnersupplier,viewGroup,false);
        }

        final Supplier supplier = (Supplier) getItem(i);
        TextView optionName = (TextView) view.findViewById(R.id.itemCustomerName);
        optionName.setText(supplier.getSupplierName());
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
        return supplierFilter;
    }
    public class supplierFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = BackupSupplier.size();
                filterResults.values = BackupSupplier;
                return filterResults;
            }
            final ArrayList<Supplier> filterPersons = new ArrayList<>();
            for (Supplier supplier : BackupSupplier) {
                if (supplier.getSupplierName().toLowerCase().contains(constraint)) {
                    filterPersons.add(supplier);
                }
            }
            filterResults.count = filterPersons.size();
            filterResults.values = filterPersons;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            suppliers = (ArrayList<Supplier>) results.values;
            notifyDataSetChanged();
        }
    }
}
