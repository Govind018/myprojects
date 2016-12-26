package com.technical.quiz.views.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.technical.quiz.R;
import com.technical.quiz.model.HistoryData;

import java.util.List;

/**
 * Created by Govind on 10/24/2016.
 */

public class QuestionsHistoryAdapter extends RecyclerView.Adapter<QuestionsHistoryAdapter.MyViewHolder> {

    private final Activity activity;
    List<HistoryData> questionsLstArray;
    private RadioButton lastCheckedRB = null;


    AdapterView.OnItemClickListener mItemClickListener;

    public QuestionsHistoryAdapter(Activity activty, List<HistoryData> questionsLstAray) {
        this.activity = activty;
        this.questionsLstArray = questionsLstAray;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.que_answer_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        try {


            if(position == 0){

            }else{

            }

            holder.textViewQuestion.setText(questionsLstArray.get(position).getQuestion());
            holder.radioAns1.setText(Html.fromHtml("<b> A. </b>  ") + questionsLstArray.get(position).getOptionOne());
            holder.radioAns2.setText("B.  " + questionsLstArray.get(position).getOptionTwo());
            holder.radioAns3.setText("C.  " + questionsLstArray.get(position).getOptionThree());
            holder.radioAns4.setText("D.  " + questionsLstArray.get(position).getOptionFour());




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return questionsLstArray.size();
    }

    public void setOnItemClickListener(final AdapterView.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewQuestion;
        RadioGroup answerRadioGroup;
        TextView textSeperator;
        TextView radioAns1;
        TextView radioAns2;
        TextView radioAns3;
        TextView radioAns4;

        LinearLayout itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewQuestion = (TextView) itemView.findViewById(R.id.textViewQuestion);
//            answerRadioGroup = (RadioGroup) itemView.findViewById(R.id.answerRadioGroup);
            radioAns1 = (TextView) itemView.findViewById(R.id.radio_answer1);
            radioAns2 = (TextView) itemView.findViewById(R.id.radio_answer2);
            radioAns3 = (TextView) itemView.findViewById(R.id.radio_answer3);
            radioAns4 = (TextView) itemView.findViewById(R.id.radio_answer4);
            textSeperator = (TextView) itemView.findViewById(R.id.separator);
            //itemLayout = (LinearLayout) itemView.findViewById(item_layout);
            // itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
//                mItemClickListener.onItemClick(view, getPosition());
            }
        }
    }
}

