package pe.edu.upc.appparkingreservation.backend;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Edgar Melgarejo on 04/04/2016.
 */
public class BackEndResponse<T> implements Response.Listener<T>, Response.ErrorListener {

    private T result;

    private boolean isComplete;

    public BackEndResponse() {
        this.result = null;
        this.isComplete = false;
    }

    public T getResult() {
        return result;
    }

    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public void onResponse(T response) {
        this.result = response;
        this.isComplete = true;
        Log.d("VOLLEY - RESPONSE","SUCCESS");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        this.result = null;
        this.isComplete = true;
        Log.d("VOLLEY - RESPONSE","FAIL");
    }
}
