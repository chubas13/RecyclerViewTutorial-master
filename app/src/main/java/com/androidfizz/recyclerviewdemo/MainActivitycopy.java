package com.androidfizz.recyclerviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.androidfizz.recyclerviewdemo.adapters.ListAdapter;
import com.androidfizz.recyclerviewdemo.helper.MyDividerItemDecoration;
import com.androidfizz.recyclerviewdemo.helper.RecyclerItemTouchListener;
import com.androidfizz.recyclerviewdemo.models.AndroidVersion;

import java.util.ArrayList;
import java.util.List;

public class MainActivitycopy extends AppCompatActivity{

    private Context context;
    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private List<AndroidVersion> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context=this;

        list=getVersionsList();
        adapter=new ListAdapter(context, list);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //if your layout has fixed size this will improve it performance
        recyclerView.setHasFixedSize(true);

        //using DividerItemDecoration from support library
        /*DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(context,
                LinearLayoutManager.VERTICAL);

        //setting custom drawable as a divider
        Drawable drawable= ContextCompat.getDrawable(context, R.drawable.divider);
        if(drawable!=null)
            dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);*/


        //default divider using custom ItemDecoration
        //recyclerView.addItemDecoration(new MyDividerItemDecoration(context));

        //setting custom drawable as a divider
        recyclerView.addItemDecoration(new MyDividerItemDecoration(context, R.drawable.divider));

        recyclerView.setAdapter(adapter);

        /*adapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showToast("Clicked "+position);
            }

            @Override
            public void onItemLongPress(int position) {
                showToast("long Clicked "+position);
            }
        });*/

        recyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(this,
                        new RecyclerItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                showToast("Clicked "+position);
            }

            @Override
            public void onItemLongPress(int position) {
                showToast("long Clicked "+position);
            }
        }));
    }

    private List<AndroidVersion> getVersionsList(){
        List<AndroidVersion> list = new ArrayList<>();
        list.add(new AndroidVersion(R.drawable.polloasado,"Pollo Asado", "$105.00", "1 Entero"));
        list.add(new AndroidVersion(R.drawable.polloasado,"Pollo Asado", "$85.00", "3/4 cuartos"));
        list.add(new AndroidVersion(R.drawable.polloasado,"Pollo Asado", "$63.00", "1/2 medio"));
        list.add(new AndroidVersion(R.drawable.polloasado,"Pollo Asado", "$38.00", "1/4 cuarto"));
        list.add(new AndroidVersion(R.drawable.plancha250,"Pollo a la plancha", "$70.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.plancha250,"Pollo a la plancha", "$47.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.pechuga250,"Pollo empanizado", "$73.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.pechuga250,"Pollo empanizado", "$49.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.marinadas250,"Alitas marinadas", "$61.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.marinadas250,"Alitas marinadas", "$39.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.alas250,"Alitas empanizadas", "$74.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.alas250,"Alitas empanizadas", "$46.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.parmesana250,"Pechugas rellenas", "$78.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.parmesana250,"Pechugas rellenas", "$48.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.tiras250,"Tiras de pollo crujiente", "$76.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.tiras250,"Tiras de pollo crujiente", "$47.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.carneasada250,"Carne asada", "$64.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.carneasada250,"Carne asada", "$40.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.costillas250,"Costillas asadas", "$79.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.costillas250,"Costillas asadas", "$48.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.chuletas250,"Chuletas", "$72.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.chuletas250,"Chuletas", "$45.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.puerco250,"Puerco empanizado", "$67.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.puerco250,"Puerco empanizado", "$44.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.adobaba250,"Carne adobada", "$64.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.adobaba250,"Carne adobada", "$40.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.guiso250,"Guiso del día", "$65.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.guiso250,"Guiso del día", "$40.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.papas,"Papas a la francesa", "$30.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.papas,"Papas a la francesa", "$15.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.papas,"Papas a la francesa", "$10.00", "1 bolsa"));
        list.add(new AndroidVersion(R.drawable.spaghetti250,"Spaghetti", "$30.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.spaghetti250,"Spaghetti", "$15.00", "1/2 Orden"));
        list.add(new AndroidVersion(R.drawable.spaghetti250,"Spaghetti", "$10.00", "1 bolsa"));
        list.add(new AndroidVersion(R.drawable.platanos250,"Platanos fritos", "$25.00", "1 Orden"));
        list.add(new AndroidVersion(R.drawable.tortillas,"Tortillas", "$10.00", "Agranel"));

        return list;
    }

    private void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
