package food.eat.food;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

public class NewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Lunch");

        Intent intent = getIntent();
        Bitmap imageBitmap = (Bitmap) intent.getExtras().get("pic");
        ((ImageView) findViewById(R.id.imageView2)).setImageBitmap(imageBitmap);
    }

    @Override
    public void onBackPressed() {
        onNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            ArrayList<Integer> arrayList = new ArrayList<Integer>(6);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("Name", "Lunch");
            returnIntent.putExtra("Calories", 593);
            returnIntent.putExtra("Fat", 13);
            returnIntent.putExtra("Carbohydrates", 29);
            returnIntent.putExtra("Protein", 5);
            returnIntent.putExtra("Cholesterol", 0);
            returnIntent.putExtra("Servings", 3.4);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
            return true;
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
