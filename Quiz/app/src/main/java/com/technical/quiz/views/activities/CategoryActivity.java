package com.technical.quiz.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.technical.quiz.R;
import com.technical.quiz.http.ApiService;
import com.technical.quiz.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class CategoryActivity extends AppCompatActivity {

    ListView listCat;

    ArrayAdapter<String> adapter;

    String[] categories = {"C", "C++", "C#", "Java", "HTML", "Javascript", "PHP", "Python"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.cat_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listCat = (ListView) findViewById(R.id.list_categories);
        adapter = new ArrayAdapter<String>(CategoryActivity.this, android.R.layout.simple_list_item_multiple_choice, categories);
        listCat.setAdapter(adapter);


    }

    public void doSubmit(View view) {

        SparseBooleanArray checked = listCat.getCheckedItemPositions();

        for (int i = 0; i < listCat.getAdapter().getCount(); i++) {
            if (checked.get(i)) {
                // Do something
            }
        }

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "java");
            jsonObject.put("gcmid", Utils.getFromPrefrencesBoolean(getApplicationContext(), "gcmId"));
            ApiService.getApiService().doSubscribeCategory(getApplicationContext(), jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
