package food.eat.food;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final int VIEW = R.id.nav_view;
    final int NEW = R.id.nav_new;
    final int STATS = R.id.nav_stats;
    final int GOALS = R.id.nav_goals;
    final int HELP = R.id.nav_help;
    final int SETTINGS = R.id.nav_settings;
    public int state = VIEW;
    public final int NEW_CAPTURE = 1;
    public final int NEW_MEAL = 2;

    private List<Meal> meals = new ArrayList<Meal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeNew();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        
        populateMeals();
        populateList();
    }

    private void populateMeals() {
        meals.add(new Meal("Breakfast", 350, 12, 0, 0, 0, 2.2));
        meals.add(new Meal("Dinner", 1060, 17, 0, 0, 0, 4.5));
        meals.add(new Meal("Lunch", 750, 15, 0, 0, 0, 3.3));
        meals.add(new Meal("Breakfast", 330, 10, 0, 0, 0, 2));
    }

    private void populateList() {
        ArrayAdapter<Meal> adapter = new MealAdapter();
        ListView list = (ListView) findViewById(R.id.mealList);
        list.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case VIEW:
            case STATS:
            case GOALS:
            case HELP:
                if (state != id) changeTo(id);
                break;
            case NEW:
                makeNew();
                break;
            case SETTINGS:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeTo(int toState) {
        state = toState;
        findViewById(R.id.content_view).setVisibility(View.GONE);
        findViewById(R.id.content_stats).setVisibility(View.GONE);
        findViewById(R.id.content_goals).setVisibility(View.GONE);
        findViewById(R.id.content_help).setVisibility(View.GONE);
        int content;
        switch (toState) {
            case VIEW:
                content = R.id.content_view;
                break;
            case STATS:
                content = R.id.content_stats;
                break;
            case GOALS:
                content = R.id.content_goals;
                break;
            case HELP:
                content = R.id.content_help;
                break;
            default:
                content = R.id.content_view;
                break;
        }
        findViewById(content).setVisibility(View.VISIBLE);
    }

    public void makeNew(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, NEW_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        findViewById(R.id.nav_view).callOnClick();
        if (resultCode != RESULT_OK) return;
        Bundle extras = data.getExtras();
        switch (requestCode){
            case NEW_CAPTURE:
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                Intent intent = new Intent(getApplicationContext(), NewActivity.class);
                intent.putExtra("pic", imageBitmap);
                startActivityForResult(intent, NEW_MEAL);
                break;
            case NEW_MEAL:
                meals.add(0, new Meal((String) extras.get("Name"), (int) extras.get("Calories"), (int) extras.get("Fat"), (int) extras.get("Carbohydrates"), (int) extras.get("Protein"), (int) extras.get("Cholesterol"), (double) extras.get("Servings")));
                break;
        }
    }

    private class MealAdapter extends ArrayAdapter<Meal>{
        public MealAdapter(){
            super(StartActivity.this, R.layout.meal, meals);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.meal, parent, false);
            }

            Meal currentMeal = meals.get(position);

            TextView nameText = (TextView) itemView.findViewById(R.id.meal_name);
            nameText.setText(currentMeal.getName());

            TextView calText = (TextView) itemView.findViewById(R.id.meal_cals);
            calText.setText(currentMeal.getCalories() + " cals");

            TextView fatText = (TextView) itemView.findViewById(R.id.meal_fat);
            fatText.setText(currentMeal.getFat() + "g fat");

            TextView servingText = (TextView) itemView.findViewById(R.id.meal_servings);
            servingText.setText(currentMeal.getServings() + " servings");

            return itemView;
        }
    }

}
