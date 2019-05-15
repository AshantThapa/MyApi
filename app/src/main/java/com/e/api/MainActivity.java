package com.e.api;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import api.EmployeeApi;
import model.Employees;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvData;
    private final static String BASE_URL="http://dummy.restapiexample.com/api/v1/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData=findViewById(R.id.tvData);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);
        Call<List<Employees>> LisCall = employeeApi.getEmployee();
        LisCall.enqueue(new Callback<List<Employees>>() {
            @Override
            public void onResponse(Call<List<Employees>> call, Response<List<Employees>> response) {
                if (!response.isSuccessful()) {

                    tvData.setText("code:"+response.code());
                    return;
            }
                List<Employees>empolyeesList=response.body();
                for (Employees empolyees:empolyeesList){
                    String content="";
                    content+="id:"+empolyees.getId()+"\n";
                    content+="employee_name:"+empolyees.getEmployee_name()+"\n";
                    content+="salary:"+empolyees.getEmployee_salary()+"\n";
                    content+="age:"+empolyees.getEmployee_age()+"\n";
                    content+="image:"+empolyees.getProfile_image()+"\n";

                    tvData.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Employees>> call, Throwable t) {
                tvData.setText("Error"+ t.getMessage());
            }
        });
    }
}
