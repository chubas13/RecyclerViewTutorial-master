package com.androidfizz.recyclerviewdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidfizz.recyclerviewdemo.helper.RecyclerItemTouchListener;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity{

    private ArrayList<ExampleItem> mExampleList;

    private Context context;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    //Variables globales

    //color del background del RecyclerView originalmente
    //android:background="@android:color/darker_gray"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;

        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.INTERNET,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }*/

        /**Validar el ws si trae algo pintar el RecyclerView
         * De lo contrario mostrar un mensaje al usuario */

        //buildRecyclerView();

        //showShoppingCart();

        EditText editText = findViewById(R.id.edittext);

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        client.get(MainActivity.this,"http://199.89.53.147:9000/api/general", null, new AsyncHttpResponseHandler() { //Menú de comidas
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String idProduct="";
                String priceProduct="";
                String descriptionProduct="";
                String sizeProduct="";
                String sizeProductObject="";
                String str = new String(responseBody);
                try {
                    JSONObject result = new JSONObject(str);
                    JSONArray dataRaw = result.getJSONArray("menucomidas");
                    JSONArray data = new JSONArray();
                    long peticiones=dataRaw.length();
                    Log.e("TAGproductos","Total de productos disponibles "+peticiones);
                    mExampleList = new ArrayList<>();


                    for (int i = 0; i < dataRaw.length(); i++) {
                        JSONObject object = dataRaw.getJSONObject(i);
                        //Log.e("TAG_ID"," "+object.getString("id"));
                        //Log.e("TAG_Name"," "+object.getString("producto"));
                        //Log.e("TAG_Precio"," "+object.getString("precio"));
                        //Log.e("TAG_Tipo"," "+object.getString("tipo"));
                        idProduct = object.getString("id");
                        NumberFormat nf = NumberFormat.getCurrencyInstance();
                        priceProduct=object.getString("precio");
                        descriptionProduct = object.getString("descripcion");
                        sizeProductObject = object.getString("medida");
                        sizeProduct = sizeOfProducts(sizeProductObject);

                        switch (descriptionProduct){
                            case "POLLO ASADO":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.polloasado,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "POLLO A LA PLANCHA":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.plancha250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "POLLO EMPANIZADO":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.pechuga250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "ALITAS MARINADAS":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.marinadas250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "ALITAS EMPANIZADAS":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.alas250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "PECHUGAS RELLENAS":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.parmesana250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "TIRAS DE POLLO CRUJIENTE":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.tiras250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "CARNE ASADA":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.carneasada250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "COSTILLAS ASADAS":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.costillas250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "CHULETAS":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.chuletas250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "PUERCO EMPANIZADO":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.puerco250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "CARNE ADOBADA":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.adobaba250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "GUISO DEL DIA":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.guiso250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "PAPAS A LA FRANCESA":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.papas,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "SPAGHETTI":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.spaghetti250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "PLATANOS FRITOS":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                mExampleList.add(new ExampleItem(R.drawable.platanos250,descriptionProduct, nf.format(new BigDecimal(priceProduct)), sizeProduct, idProduct));
                                break;
                            case "BOLSA C/SOPA, FRIJOL, TOMATE O ARROZ":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                break;
                            case "BOLSA DE BBQ":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                break;
                            case "BOLSA DE TOMATE":
                                //sizeProduct = sizeOfProducts(sizeProductObject);
                                break;
                        }
                        buildRecyclerView();
                    }
                    /*if(listfolioconavi.size()>0){
                        checktask(listfolioconavi.size());
                    }else{
                        //Toast.makeText(tdActivity.this,"No hay tareas asignadas al usuario actual",Toast.LENGTH_LONG).show();
                        TableTasks();
                    }*/
                } catch (JSONException i) {
                    i.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //Toast.makeText(tdActivity.this, "No hay respuesta del servidor", Toast.LENGTH_SHORT).show();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }
    /** Función que devuelve en un string las maneras en las cuales
     * se puede vender un producto (1 o 1/2 orden, 1 entero, 3/4, 1/2, 3/4
     * o 1 bolsa)
     * best practice */
    private String sizeOfProducts(String sizeParameter){
        String resultString="";
        switch(sizeParameter){
            case "1":
                resultString = "1 Entero";
                break;
            case "3/4":
                resultString = "3/4 cuartos";
                break;
            case "1/2":
                resultString = "1/2 medio";
                break;
            case "1/4":
                resultString = "1/4 cuarto";
                break;
            case "1 Orden":
                resultString = "1 Orden";
                break;
            case "1/2 Orden":
                resultString = "1/2 Orden";
                break;
            case "1 Bolsa":
                resultString = "1 Bolsa";
        }
        return resultString;
    }
    private void filter(String text) {
        ArrayList<ExampleItem> filteredList = new ArrayList<>();

        for (ExampleItem item : mExampleList) {
            if (item.getText1().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //Agregar la funcionalidad del click a cada elemento del
        //RecyclerView
        mRecyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(this,
                new RecyclerItemTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                //showToast("Clicked "+position);
                //Al hacer búsqueda reinicia las posiciones
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater = getLayoutInflater();

                View view = inflater.inflate(R.layout.dialog_personalizado,null);

                builder.setView(view);

                final AlertDialog dialog = builder.create();
                dialog.show();

                final String productName = ((TextView) mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.textView)).getText().toString();
                final String productPrice = ((TextView) mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.textView2)).getText().toString();
                String orderProduct = ((TextView) mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.textView3)).getText().toString();
                final String productID = ((TextView) mRecyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.textid)).getText().toString();

                TextView txt = view.findViewById(R.id.text_dialog);
                //txt.setText("¿Desea comprar?\n"+"Pechuga a la plancha");
                txt.setText("¿Desea agregar al carrito de compras?\n"+orderProduct+" de\n"+productName);
                builder.setCancelable(true);

                Button btnComprar = view.findViewById(R.id.btnConfirm);
                btnComprar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //add product to basket AddProductToBasket()
                        //Validar que nada vaya vacío y jalar el id del usuario logeado
                        AddProductToBasket("12",productID,productName,productPrice);
                        //Toast.makeText(getApplicationContext(),"Producto agregado al carrito de compras "+productID,Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                Button btnCancelar = view.findViewById(R.id.btnCancel);
                btnCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //no agregamos el producto al carrito de compras
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void onItemLongPress(int position) {
                //showToast("long Clicked "+position);
                //Al hacer búsqueda reinicia las posiciones de los items
            }
        }));
    }
    private void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /** Guardamos el producto seleccionado al carrito de compras */
    public void AddProductToBasket(String idUsuario, String idProducto,
                                   String nombreProducto, String precioProducto){
        HandlerBD adminSHOP = new HandlerBD(MainActivity.this, "carrito", null, 1);
        SQLiteDatabase ISQLshop = adminSHOP.getWritableDatabase();
        try {
            if (ISQLshop != null) {
                ContentValues itemToCart = new ContentValues();
                itemToCart.put("UserID", idUsuario);
                itemToCart.put("ProductID", idProducto);
                itemToCart.put("ProductName", nombreProducto);
                itemToCart.put("ProductPrice", precioProducto);
                itemToCart.put("ProductType", "Sin especificar");
                long inSQL = ISQLshop.insert("shoppingcart", null, itemToCart);
                Toast.makeText(MainActivity.this, "Producto agregado al carrito de compras...", Toast.LENGTH_SHORT).show();
            }
            //Cerramos la apertura a la base de datos y checar si no marca leak en la consola
            ISQLshop.close();
        } catch (Exception e) {
            Log.e("ErrOne", "exception: " + e.getMessage());
            Log.e("ErrTwo", "exception: " + e.toString());
        }
    }

    /** Muestra los productos que se han agreado al carrito de compras */
    public void showShoppingCart(){
        HandlerBD adminLIST = new HandlerBD(this, "carrito", null, 1);
        SQLiteDatabase BDShowShop = adminLIST.getWritableDatabase();
        Cursor rowSHOP = BDShowShop.rawQuery("SELECT * from shoppingcart", null);
        if(rowSHOP.moveToFirst()){
            do{
                Log.e("TAGtipo", "\n " + "ID:  " + rowSHOP.getString(0) + "   " +
                        "  UserID:  " + rowSHOP.getString(1) +
                        "  ProductID:  " + rowSHOP.getString(2) +
                        "  ProductName:  " + rowSHOP.getString(3) +
                        "  ProductPrice:  " + rowSHOP.getString(4) +
                        "  ProductType:  " + rowSHOP.getString(5));
            }while (rowSHOP.moveToNext());
        }
        rowSHOP.close();
        BDShowShop.close();
    }
}
