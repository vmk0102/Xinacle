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
import com.hassoft.xinacle.model.Warehouse;

import java.util.ArrayList;


public class warehouseAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Warehouse> warehouses=null;
    ArrayList<Warehouse> BackupWarehouse=null;
    warehouseFilter warehouseFilter=new warehouseFilter();

    public warehouseAdapter(Context context, ArrayList<Warehouse> warehouses){
        this.context=context;
        this.warehouses=warehouses;
        BackupWarehouse=warehouses;

    }
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return warehouses.get(i);
    }

    @Override
    public int getCount() {
        return warehouses.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.spinnerwarehouse,viewGroup,false);
        }

        final Warehouse warehouse = (Warehouse) getItem(i);
        TextView optionName = (TextView) view.findViewById(R.id.itemCustomerName);
        optionName.setText(warehouse.getWarehouseName());
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
        return warehouseFilter;
    }
    public class warehouseFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = BackupWarehouse.size();
                filterResults.values = BackupWarehouse;
                return filterResults;
            }
            final ArrayList<Warehouse> filterPersons = new ArrayList<>();
            for (Warehouse warehouse : warehouses) {
                if (warehouse.getWarehouseName().toLowerCase().contains(constraint)) {
                    filterPersons.add(warehouse);
                }
            }
            filterResults.count = filterPersons.size();
            filterResults.values = filterPersons;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            warehouses = (ArrayList<Warehouse>) results.values;
            notifyDataSetChanged();
        }
    }
}
