package com.sharma.deepak.inventory.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sharma.deepak.inventory.R;
import com.sharma.deepak.inventory.data.InventoryDbHelper;
import com.sharma.deepak.inventory.model.Product;

import java.util.List;

/**
 * Created by deepak on 16/7/20172017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private final List<Product> productList;
    private final ReportCardItemInterface mInterface;
    private final InventoryDbHelper mDbHelper;
    private final Context mContext;

    public interface ReportCardItemInterface {
        void OnReportCardItemClick(int position);

        void OnSaleButtonClicked();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name, price, quantity;
        public Button saleBtn;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_product_name);
            price = (TextView) view.findViewById(R.id.tv_price);
            quantity = (TextView) view.findViewById(R.id.tv_quantity);
            saleBtn = (Button) view.findViewById(R.id.btn_sale);
            saleBtn.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_sale:
                    int currentCount = Integer.parseInt(quantity.getText().toString());
                    if (currentCount > 0) {
                        --currentCount;
                        mDbHelper.updateProductQuantity(productList.get(getAdapterPosition()).getProductId(), currentCount);
                        mInterface.OnSaleButtonClicked();
                    } else {
                        Toast.makeText(mContext, mContext.getString(R.string.not_less_than_zero), Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    mInterface.OnReportCardItemClick(getAdapterPosition());
            }
        }
    }


    public ProductAdapter(Context context, ReportCardItemInterface mInterface, List<Product> productList) {
        this.mContext = context;
        this.productList = productList;
        this.mInterface = mInterface;
        mDbHelper = new InventoryDbHelper(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.name.setText(product.getProductName());
        holder.price.setText("$"+product.getProductPrice());
        holder.quantity.setText(product.getProductQuantity() + "");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void swap(List<Product> data) {
        productList.clear();
        productList.addAll(data);
        notifyDataSetChanged();
    }
}
