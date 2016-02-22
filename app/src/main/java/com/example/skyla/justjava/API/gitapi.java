//package com.example.skyla.justjava.API;

//import com.example.skyla.justjava.model.gitmodel;

//import retrofit2.Call;
//import retrofit2.http.GET;
//import retrofit2.http.Path;

/*
public interface GitHubService {
    @GET("/users/{username}")
    //Call<User> getUser(@Path("username") String username);
}*/


//package com.makeinfo.flowerpi.API;
package com.example.skyla.justjava.API;

  //import com.makeinfo.flowerpi.bean.User;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Path;


public interface GitHubService {
    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username);
}
