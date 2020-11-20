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
import com.hassoft.xinacle.model.Accounts;
import com.hassoft.xinacle.model.Supplier;

import java.util.ArrayList;


public class accountsAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<Accounts> accounts=null;
    ArrayList<Accounts> BackupAccounts=null;
    accountFilter accountFilter=new accountFilter();

    public accountsAdapter(Context context, ArrayList<Accounts> accounts){
        this.context=context;
        this.accounts=accounts;
         BackupAccounts=accounts;

    }
   // int pos=0;


    @Override
    public Object getItem(int i) {
        return accounts.get(i);
    }

    @Override
    public int getCount() {
        return accounts.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.spinneraccount,viewGroup,false);
        }

        final Accounts accounts = (Accounts) getItem(i);
        TextView optionName = (TextView) view.findViewById(R.id.itemCustomerName);
        optionName.setText(accounts.getAccountTitle());
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
        return accountFilter;
    }
    public class accountFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults filterResults = new FilterResults();
            if (TextUtils.isEmpty(constraint)) {
                filterResults.count = BackupAccounts.size();
                filterResults.values = BackupAccounts;
                return filterResults;
            }
            final ArrayList<Accounts> filterPersons = new ArrayList<>();
            for (Accounts accounts : BackupAccounts) {
                if (accounts.getAccountTitle().toLowerCase().contains(constraint)) {
                    filterPersons.add(accounts);
                }
            }
            filterResults.count = filterPersons.size();
            filterResults.values = filterPersons;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            accounts = (ArrayList<Accounts>) results.values;
            notifyDataSetChanged();
        }
    }
}
