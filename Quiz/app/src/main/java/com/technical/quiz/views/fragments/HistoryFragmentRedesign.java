package com.technical.quiz.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technical.quiz.R;
import com.technical.quiz.model.HistoryData;
import com.technical.quiz.views.adapters.QuestionsHistoryAdapter;
import com.technical.quiz.views.fragments.interfaces.OnFragmentInteractionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistoryFragmentRedesign.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistoryFragmentRedesign#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragmentRedesign extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView_QuestionAnswer;
    private LinearLayoutManager linearLayoutManager;

    private QuestionsHistoryAdapter questionsHistoryAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HistoryFragmentRedesign() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragmentRedesign.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragmentRedesign newInstance(String param1, String param2) {
        HistoryFragmentRedesign fragment = new HistoryFragmentRedesign();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_history_fragment_redesign, container, false);

        recyclerView_QuestionAnswer = (RecyclerView) view.findViewById(R.id.recyclerView_QuestionAnswer);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView_QuestionAnswer.setLayoutManager(linearLayoutManager);

        readFromAssets();

        return view;
    }


    private void readFromAssets() {

        BufferedReader bufferedReader = null;
        StringBuilder returnString = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(getActivity().getAssets().open("History.json")));
            String mLine = "";

            while ((mLine = bufferedReader.readLine()) != null) {
                returnString.append(mLine);
            }

            parseLocalJson(returnString);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }

        }

    }


    // TODO: Rename method, update argument and hook method into UI event

    private void parseLocalJson(StringBuilder returnString) {

        try {
            JSONObject jsonObject = new JSONObject(returnString.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("history");
            ArrayList<HistoryData> historyDatas = new ArrayList<>();

            Log.d("LOCAL JSON", "" + jsonArray);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                HistoryData historyData = new HistoryData();
                historyData.setDate(object.getString("date"));
                historyData.setQuestion(object.getString("question"));
                historyData.setOptionOne(object.getString("optionOne"));
                historyData.setOptionTwo(object.getString("optionTwo"));
                historyData.setOptionThree(object.getString("optionThree"));
                historyData.setOptionFour(object.getString("optionFour"));
                historyData.setCorrectAns(object.getString("correctAns"));
                historyData.setIsAttempted(object.getString("answered"));
                historyData.setSubmittedAns(object.getString("answerRecvd"));

                historyDatas.add(historyData);
            }

            questionsHistoryAdapter = new QuestionsHistoryAdapter(getActivity(),historyDatas);
            recyclerView_QuestionAnswer.setAdapter(questionsHistoryAdapter);

            Log.d("HISTORY DATA", "" + historyDatas);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
