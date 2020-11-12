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
import com.hassoft.xinacle.model.Products;
import com.hassoft.xinacle.model.Supplier;

import java.util.ArrayList;


public class productsAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Products> products=null;
    ArrayList<Products> BackupProducts=null;
    productFilter productFilter=new productFilter();

    public productsAdapter(Context context, ArrayList<Products> products){
        this.context=context;
        this.products=products;
        BackupProducts=products;

    }
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public int getCount() {
        return products.size();
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

        final Products product = (Products) getItem(i);
        TextView optionName = (TextView) view.findViewById(R.id.itemCustomerName);
        optionName.setText(product.getProductName());
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
        return  productFilter;
    }
    public class productFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = BackupProducts.size();
                filterResults.values = BackupProducts;
                return filterResults;
            }
            final ArrayList<Products> filterPersons = new ArrayList<>();
            for (Products product : products) {
                if (product.getProductName().toLowerCase().contains(constraint)) {
                    filterPersons.add(product);
                }
            }
            filterResults.count = filterPersons.size();
            filterResults.values = filterPersons;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products = (ArrayList<Products>) results.values;
            notifyDataSetChanged();
        }
    }
}
