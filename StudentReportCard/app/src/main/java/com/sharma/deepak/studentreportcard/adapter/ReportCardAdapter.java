package com.sharma.deepak.studentreportcard.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharma.deepak.studentreportcard.R;
import com.sharma.deepak.studentreportcard.ReportCard;

import java.util.List;

/**
 * Created by deepak on 28-06-2017.
 */

public class ReportCardAdapter extends RecyclerView.Adapter<ReportCardAdapter.MyViewHolder> {

    private List<ReportCard> reportCardList;
    private ReportCardItemInterface mInterface;

    public interface ReportCardItemInterface {
        void OnReportCardItemClick(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name, standard, rollNo;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tv_student_name);
            standard = (TextView) view.findViewById(R.id.tv_class);
            rollNo = (TextView) view.findViewById(R.id.tv_student_roll_no);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mInterface.OnReportCardItemClick(getAdapterPosition());
        }
    }


    public ReportCardAdapter(ReportCardItemInterface mInterface, List<ReportCard> reportCardList) {
        this.reportCardList = reportCardList;
        this.mInterface = mInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.report_card_liat_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ReportCard reportCard = reportCardList.get(position);
        holder.name.setText(reportCard.getStudentName());
        holder.standard.setText(reportCard.getStudentClass()+"th");
        holder.rollNo.setText(reportCard.getStudentRollNo());
    }

    @Override
    public int getItemCount() {
        return reportCardList.size();
    }
}
