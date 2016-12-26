package com.technical.quiz.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technical.quiz.R;
import com.technical.quiz.http.ApiService;
import com.technical.quiz.utils.Utils;
import com.technical.quiz.views.fragments.interfaces.OnFragmentInteractionListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText editName;

    EditText editEmail;

    EditText editMob;

    EditText editCompany;

    EditText editExp;

    EditText editCollege;

    EditText editSubject;

    EditText editDomain;

    EditText editDepart;

    EditText editDesg;

    Spinner occuSpinner;

    TextView textComp;

    TextView textDesg;

    TextView textDept;

    TextView textDomain;

    TextView textExp;

    TextView textCollege;

    TextView textSubject;

    Button btnUpdate;

    LinearLayout collegeLayout,companyLayout;

    private boolean isCompany;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initUI(view);

        return view;
    }

    private void initUI(View view) {

//        textComp = (TextView) view.findViewById(R.id.text_comp);
//        textExp = (TextView) view.findViewById(R.id.text_exp);
//        textCollege = (TextView) view.findViewById(R.id.text_college);
//        textSubject = (TextView) view.findViewById(R.id.text_sub);
//        textDesg = (TextView) view.findViewById(R.id.text_designation);
//        textDept = (TextView) view.findViewById(R.id.text_department);
//        textDomain = (TextView) view.findViewById(R.id.text_domain);

        occuSpinner = (Spinner) view.findViewById(R.id.spinner_occ);
        occuSpinner.setOnItemSelectedListener(onItemSelectedListener);

        editName = (EditText) view.findViewById(R.id.edit_firstname);
        editEmail = (EditText) view.findViewById(R.id.edit_email);
        editMob = (EditText) view.findViewById(R.id.edit_mobile);

        editCompany = (EditText) view.findViewById(R.id.edit_company);
        editExp = (EditText) view.findViewById(R.id.edit_experience);
        editDomain = (EditText) view.findViewById(R.id.edit_domain);
        editDepart = (EditText) view.findViewById(R.id.edit_department);
        editDesg = (EditText) view.findViewById(R.id.edit_designation);

        collegeLayout = (LinearLayout) view.findViewById(R.id.college_layout);
        companyLayout = (LinearLayout) view.findViewById(R.id.company_layout);

        editCollege = (EditText) view.findViewById(R.id.edit_college);
        editSubject = (EditText) view.findViewById(R.id.edit_subject);

        btnUpdate = (Button) view.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            doUpdateUserProfile();

        }
    };

    private void doUpdateUserProfile() {

        String userName = editName.getText().toString();
        String userEmail = editEmail.getText().toString();
        String userMob = editMob.getText().toString();

        String userDomain = editDomain.getText().toString();
        String userDept = editDepart.getText().toString();
        String userDesg = editDesg.getText().toString();
        String userComp = editCompany.getText().toString();
        String userExp = editExp.getText().toString();
//        String userCollege = editCollege.getText().toString();
//        String userSubject = editSubject.getText().toString();

        if (!userName.isEmpty() && !userEmail.isEmpty() && !userMob.isEmpty()) {

            if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                Toast.makeText(getActivity(), "Please provide valid email adress.", Toast.LENGTH_LONG).show();
                return;
            }

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", userName);
                jsonObject.put("email", userEmail);
                jsonObject.put("mobile", userMob);
                jsonObject.put("password", "");
                jsonObject.put("gcmId", Utils.getFromPrefrencesBoolean(getActivity(), "gcmId"));


                JSONObject companyOrCollege = new JSONObject();
                String occupation;
                if (isCompany) {
                    companyOrCollege.put("name", userComp);
                    companyOrCollege.put("domain", userDomain);
                    companyOrCollege.put("department", userDept);
                    companyOrCollege.put("designation", userDesg);
                    companyOrCollege.put("experience", userExp);
                    occupation = "company";
                } else {
                    companyOrCollege.put("name", "test");
                    occupation = "college";
                }

                jsonObject.put(occupation, companyOrCollege);

//                ApiService.getApiService().doSignUp(getApplicationContext(), jsonObject);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
    }


    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position == 0) {

                collegeLayout.setVisibility(View.GONE);
                companyLayout.setVisibility(View.VISIBLE);
//                editCompany.setVisibility(View.VISIBLE);
//                editExp.setVisibility(View.VISIBLE);
//                editDesg.setVisibility(View.VISIBLE);
//                editDomain.setVisibility(View.VISIBLE);
//                editDepart.setVisibility(View.VISIBLE);
//                editCollege.setVisibility(View.GONE);
//                editSubject.setVisibility(View.GONE);

//                textComp.setVisibility(View.VISIBLE);
//                textExp.setVisibility(View.VISIBLE);
//                textDesg.setVisibility(View.VISIBLE);
//                textDept.setVisibility(View.VISIBLE);
//                textDomain.setVisibility(View.VISIBLE);
//                textCollege.setVisibility(View.GONE);
//                textSubject.setVisibility(View.GONE);

                isCompany = true;

            } else {

                collegeLayout.setVisibility(View.VISIBLE);
                companyLayout.setVisibility(View.GONE);

//                editCompany.setVisibility(View.GONE);
//                editExp.setVisibility(View.GONE);
//                editDesg.setVisibility(View.GONE);
//                editDomain.setVisibility(View.GONE);
//                editDepart.setVisibility(View.GONE);
//                editCollege.setVisibility(View.VISIBLE);
//                editSubject.setVisibility(View.VISIBLE);

//                textComp.setVisibility(View.GONE);
//                textExp.setVisibility(View.GONE);
//                textDesg.setVisibility(View.GONE);
//                textDept.setVisibility(View.GONE);
//                textDomain.setVisibility(View.GONE);
//                textCollege.setVisibility(View.VISIBLE);
//                textSubject.setVisibility(View.VISIBLE);

                isCompany = false;

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    // TODO: Rename method, update argument and hook method into UI event
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
