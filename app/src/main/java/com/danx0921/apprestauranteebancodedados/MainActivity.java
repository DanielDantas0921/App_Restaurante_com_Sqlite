package com.danx0921.apprestauranteebancodedados;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewHolder mViewHolder = new ViewHolder();
    DBHelper db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewHolder.db = new DBHelper(this);


        mViewHolder.lv_pedidos = findViewById(R.id.lv_pedidos);
        mViewHolder.fab_novopedido = findViewById(R.id.Fab_novopedido);
        mViewHolder.lista_pedidos = new ArrayList<>();

        mViewHolder.i=getIntent();

        //voce não tem chamar tipo a função para criar aqui ? no outro código do instrutor eu vi que ele não chamou e inseriu ai eu fiquei
        //confuso kkkk



        mViewHolder.fab_novopedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NovoPedidoActivity.class);
                startActivityForResult(i, 1);

            }
        });



        mViewHolder.lv_pedidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mViewHolder.i = new Intent(MainActivity.this,DadosPedidosActivity.class);
                mViewHolder.i.putExtra("nome",mViewHolder.lista_pedidos.get(position).getNome());
                startActivityForResult(mViewHolder.i,2);
            }
        });


        listarPedidos();

    }

    private void listarPedidos() {

        mViewHolder.lista_pedidos.clear();
        Cursor c = mViewHolder.db.SelectAll_Pedidos();
        c.moveToFirst();
        if (c.getCount() > 0) {

            do {
                String nome = c.getString(c.getColumnIndex("nome"));
                String descricao = c.getString(c.getColumnIndex("descricao"));
                Double preco = c.getDouble(c.getColumnIndex("preco"));
                mViewHolder.lista_pedidos.add(new Pedidos(nome, descricao, preco));


            } while (c.moveToNext());


        }
        //é mano ele esta vindo apenas com 1 item na lita

        ArrayAdapter<Pedidos> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mViewHolder.lista_pedidos);
        mViewHolder.lv_pedidos.setAdapter(adapter);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == 1) {
            listarPedidos();

        } else if (requestCode == 2 && (resultCode == 1 || resultCode == 2)) {
            listarPedidos();
        }

    }


    private static class ViewHolder {
        FloatingActionButton fab_novopedido;
        ListView lv_pedidos;
        List<Pedidos> lista_pedidos;
        DBHelper db;
        Intent i;

    }
}