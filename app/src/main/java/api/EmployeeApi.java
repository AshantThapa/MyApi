package api;

import java.util.List;

import model.EmployeeCUD;
import model.Employees;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeApi {

    @GET("employees")
    Call<List<Employees>> getEmployee();

    @GET("employee/{empID}")
    Call<Employees>getEmployeeById(@Path("empID") int empId);

    @POST("create")
    Call<Void>registerEmployee(@Body EmployeeCUD emp);

    @PUT("update/{empID}")
    Call<Void>updateEmployee(@Path("empID") int empId,  @Body EmployeeCUD employee);

    @DELETE("delete/{empID}")
    Call<Void>deleteEmployee(@Path("empID") int empId);

}
