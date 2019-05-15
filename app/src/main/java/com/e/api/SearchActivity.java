package com.e.api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import api.EmployeeApi;
import model.Employees;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    private final static String BASE_URL = "http://dummy.restapiexample.com/api/v1/";
    private EditText etEmpID;
    private Button btnSearch;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etEmpID = findViewById(R.id.etEmpID);
        tvData = findViewById(R.id.tvData);
        btnSearch = findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);
        Call<Employees> ListCall = employeeApi.getEmployeeById(Integer.parseInt(etEmpID.getText().toString()));

        ListCall.enqueue(new Callback<Employees>() {
            @Override
            public void onResponse(Call<Employees> call, Response<Employees> response) {
                Toast.makeText(SearchActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
                String content = "";
                content += "Id:" + response.body().getId() + "\n";
                content += "Name:" + response.body().getEmployee_name() + "\n";
                content += "Age:" + response.body().getEmployee_age() + "\n";
                content += "Salary:" + response.body().getEmployee_salary() + "\n";
                tvData.setText(content);
            }

            @Override
            public void onFailure(Call<Employees> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
