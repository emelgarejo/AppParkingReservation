package pe.edu.upc.appparkingreservation.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pe.edu.upc.appparkingreservation.backend.BackEndRequest;
import pe.edu.upc.appparkingreservation.model.Person;

/**
 * Created by Edgar Melgarejo on 25/03/2016.
 */
public class AccountService {

    private Context context;

    private static final String URl_USER = "http://rnld1503-001-site1.btempurl.com/Users.svc/";

    public AccountService(Context context) {
        this.context = context;
    }

    public Person validateAccount(String mEmail, String mPassword) {
        Person person = null;
        try {
            mEmail = mEmail.substring(0,mEmail.indexOf('@'));
            String methot = URl_USER + "ValidateUser/%s/%s";
            methot = String.format(methot, mEmail, mPassword);
            Log.d("URL USER: ",methot);
            BackEndRequest jsonObjReq = new BackEndRequest(this.context, methot);
            JSONObject result = jsonObjReq.getSingleResult();

            if (result != null) {
                person = new Person();
                person.setName(result.getString("name"));
                person.setLastName(result.getString("lastName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return person;
    }

    public boolean registerPerson(Person person) {

        try {
            String methot = URl_USER + "Users";
            BackEndRequest jsonObjReq = new BackEndRequest(this.context, Request.Method.POST, methot);

            Map<String, String> params = new HashMap<String, String>();
            params.put("name", person.getName());
            params.put("lastName", person.getLastName());
            params.put("email", person.getUserName());
            params.put("password", person.getPassword());
            params.put("registerDate", "0");
            params.put("status", "1");
            params.put("userID", "0");

            Log.d("ENTIDAD: ", params.toString());
            jsonObjReq.sendRequest(params);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
