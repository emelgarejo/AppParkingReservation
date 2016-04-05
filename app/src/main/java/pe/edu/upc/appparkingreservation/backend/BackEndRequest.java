package pe.edu.upc.appparkingreservation.backend;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

    public void sendRequest(Map<String, String> params) {

        BackEndResponse<String> response = new BackEndResponse<>();
        this.params = params;
        StringRequest jsonObjReq = new StringRequest(this.method, this.url, response, response) {
            @Override
            protected Map<String, String> getParams() {

                return BackEndRequest.this.params;
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
