package com.example.asus.ihtravel;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import com.example.asus.ihtravel.Model.Category;
import com.example.asus.ihtravel.MyRecycler.MyAdapter;
import com.example.asus.ihtravel.Navigationdrawer.NavMenuAdapter;
import com.example.asus.ihtravel.Navigationdrawer.NavMenuModel;
import com.example.asus.ihtravel.Navigationdrawer.SubTitle;
import com.example.asus.ihtravel.Navigationdrawer.TitleMenu;
import com.example.asus.ihtravel.data.Constant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements NavMenuAdapter.MenuItemClickListener{

    MyAdapter adapter ;
    Toolbar toolbar;
    DrawerLayout drawer;
    ArrayList<NavMenuModel> menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

        drawer = (DrawerLayout) findViewById(R.id.main_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        // TODO test get all categories
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://ihtravel.com.kh/apps/category.php", new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                Log.d("test", response.toString());

                ArrayList<Category> categoryArrayList = new ArrayList<>();
                for(int i=0; i<response.length(); i++){
                    try{
                        JSONObject jsonObject = response.getJSONObject(i);
                        // get id and name from server db
                        String id = jsonObject.getString("pc_id");
                        String name = jsonObject.getString("pc_name");
                        Category cat= new Category(id, name);
                        categoryArrayList.add(cat);
                    }catch (JSONException ex){

                    }
                }

                setNavigationDrawerMenu(categoryArrayList);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);

                Log.d("test","error");
            }
        });
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toobar);
        setSupportActionBar(toolbar);
        setTitle("Categories");
    }

    private void setNavigationDrawerMenu(ArrayList<Category> categories) {
        NavMenuAdapter adapter = new NavMenuAdapter(this, getMenuList(categories), this);
        RecyclerView navMenuDrawer = (RecyclerView) findViewById(R.id.main_nav_menu_recyclerview);
        navMenuDrawer.setAdapter(adapter);
        navMenuDrawer.setLayoutManager(new LinearLayoutManager(this));
        navMenuDrawer.setAdapter(adapter);

//        INITIATE SELECT MENU
        adapter.selectedItemParent = menu.get(0).menuTitle;
        onMenuItemClick(adapter.selectedItemParent);
        adapter.notifyDataSetChanged();
    }


    private List<TitleMenu> getMenuList(ArrayList<Category> categories) {
        List<TitleMenu> list = new ArrayList<>();

        menu = Constant.getMenuNavigasi(categories);
        for (int i = 0; i < menu.size(); i++) {
            ArrayList<SubTitle> subMenu = new ArrayList<>();
            if (menu.get(i).subMenu.size() > 0){
                for (int j = 0; j < menu.get(i).subMenu.size(); j++) {
                    subMenu.add(new SubTitle(menu.get(i).subMenu.get(j).subMenuTitle));
                }
            }

            list.add(new TitleMenu(menu.get(i).menuTitle, subMenu, menu.get(i).menuIconDrawable));
        }

        return list;
    }

    @Override
    public void onMenuItemClick(String itemString) {
        for (int i = 0; i < menu.size(); i++) {
            if (itemString.equals(menu.get(i).menuTitle)){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_content, menu.get(i).fragment)
                        .commit();
                break;
            }else{
                for (int j = 0; j < menu.get(i).subMenu.size(); j++) {
                    if (itemString.equals(menu.get(i).subMenu.get(j).subMenuTitle)){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_content, menu.get(i).subMenu.get(j).fragment)
                                .commit();
                        break;
                    }
                }
            }
        }

        if (drawer != null){
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
