package pe.edu.upc.appparkingreservation.backend;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Edgar Melgarejo on 04/04/2016.
 */
public class BackEndRequest {

    private int method;
    private String url;
    private Context context;
    private Map<String, String> params;

    public BackEndRequest(Context context, int method, String url) {
        this.method = method;
        this.url = url;
        this.context = context;
    }

    public BackEndRequest(Context context, String url) {
        this.method = Request.Method.GET;
        this.url = url;
        this.context = context;
    }

    public JSONObject getSingleResult() {

        BackEndResponse<JSONObject> response = new BackEndResponse<>();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(this.method, this.url, null, response, response);

        Volley.newRequestQueue(this.context).add(jsonObjReq);

        while (!response.isComplete()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response.getResult();
    }

    public JSONArray getListResult() {

        BackEndResponse<JSONArray> response = new BackEndResponse<>();

        JsonArrayRequest jsonArray = new JsonArrayRequest(this.method, this.url, null, response, response);

        Volley.newRequestQueue(this.context).add(jsonArray);

        while (!response.isComplete()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return response.getResult();
    }

    public void sendRequest(Map<String, String> params) {

        BackEndResponse<JSONObject> response = new BackEndResponse<>();
        this.params = params;
        JSONObject json = new JSONObject(
                params);

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(this.method, this.url, json, response, response) {
            @Override
            protected Map<String, String> getParams() {
                Log.d("REQUEST", BackEndRequest.this.params.toString());
                return BackEndRequest.this.params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }
        };

        Volley.newRequestQueue(this.context).add(jsonObjReq);

        while (!response.isComplete()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
