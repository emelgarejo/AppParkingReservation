package pe.edu.upc.appparkingreservation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pe.edu.upc.appparkingreservation.R;

/**
 * Created by Heavyarms on 30/03/2016.
 */

public class ItemParkingViewActivity extends AppCompatActivity {
    TextView nameTextView;
    TextView rateTextView;
    TextView statusTextView;
    ImageView logo;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_parking);
        Bundle bundle = getIntent().getExtras();
        nameTextView = (TextView) findViewById(R.id.nameTextView);
        nameTextView.setText(bundle.getString("nameParking"));
        rateTextView = (TextView) findViewById(R.id.rateTextView);
        rateTextView.setText(bundle.getString("rate"));
        statusTextView = (TextView) findViewById(R.id.statusTextView);
        statusTextView.setText(bundle.getString("status"));
        statusTextView = (TextView) findViewById(R.id.addressTextView);
        statusTextView.setText(bundle.getString("address"));
        statusTextView = (TextView) findViewById(R.id.phoneTextView);
        statusTextView.setText(bundle.getString("phone"));
        statusTextView = (TextView) findViewById(R.id.openTimeTextView);
        statusTextView.setText(bundle.getString("openTime"));
        statusTextView = (TextView) findViewById(R.id.closeTimeTextView);
        statusTextView.setText(bundle.getString("closeTime"));
        /*statusTextView = (TextView) findViewById(R.id.statusTextView);
        statusTextView.setText(bundle.getString("logoUrl"));*/
        logo = (ImageView)findViewById(R.id.imageViewReserv);
        Picasso.with(logo.getContext())
                .load(bundle.getString("logoUrl"))
                .into(logo);
        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ItemActivity.this, CatalogActivity.class));
//            }
//        });

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
