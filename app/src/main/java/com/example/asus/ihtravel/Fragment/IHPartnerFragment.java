package com.example.asus.ihtravel.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.ihtravel.MyRecycler.MyAdapter;
import com.example.asus.ihtravel.R;
import com.example.asus.ihtravel.SpaceCraft;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class IHPartnerFragment extends Fragment {

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    //private static final String URL_PRODUCTS = "http://ihtravel.com.kh/apps/category.php";
    String product_url = "http://ihtravel.com.kh/apps/partner.php?cat_id=";


    //a list to store all the products
    List<SpaceCraft> spaceCraftList;

    //Recycler view
    private RecyclerView recyclerView2;

    String id = "1";

    public static IHPartnerFragment newInstance()
    {
        IHPartnerFragment ihPartnerFragment = new IHPartnerFragment();

        return new IHPartnerFragment();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_ihpartner,null);

        // TODO test
        id = getArguments().getString("id");
        Log.d("test", id +"");
        product_url = product_url + id;

        //REFERENCE
        recyclerView2= (RecyclerView) rootView.findViewById(R.id.ihpartner_rv);

        //LAYOUT MANAGER
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));

        //ADAPTER
        recyclerView2.setAdapter(new MyAdapter(getActivity(),spaceCraftList));
        spaceCraftList = new ArrayList<SpaceCraft>();
        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();



        return rootView;
    }
    private void loadProducts() {
        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, product_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                spaceCraftList.add(new SpaceCraft(
                                        product.getInt("id"),
                                        product.getString("name"),
                                        product.getString("address"),
                                        product.getString("tel"),
                                        product.getString("goldcard"),
                                        product.getString("standardcard"),
                                        product.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            MyAdapter adapter = new MyAdapter(getActivity(), spaceCraftList);
                            recyclerView2.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our string request to queue
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

}
