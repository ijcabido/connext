package com.juco.connextfinal;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://connext.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String fullname, String studentid, int age, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("fullname", fullname);
        params.put("age", age + "");
        params.put("studentid", studentid);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}