package com.danx0921.apprestauranteebancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DadosPedidosActivity extends AppCompatActivity {
    ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_pedidos);
        mViewHolder.et_nome =  findViewById(R.id.et_DadosPedidos_nome);
        mViewHolder.et_descricao = findViewById(R.id.et_DadosPedidos_descricao);
        mViewHolder.et_preco = findViewById(R.id.et_DadosPedidos_preco);
        mViewHolder.bt_cancelar = findViewById(R.id.bt_DadosPedidos_cancelar);
        mViewHolder.bt_eliminar = findViewById(R.id.bt_DadosPedidos_eliminar);
        mViewHolder.bt_modificar = findViewById(R.id.bt_DadosPedidos_modificar);
        mViewHolder.i = getIntent();
        mViewHolder.db = new DBHelper(this);
        String nomeget = mViewHolder.i.getExtras().getString("nome");
        carregarDadosPedidos();

        mViewHolder.bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(0,mViewHolder.i);
                finish();

            }
        });




        mViewHolder.bt_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                
                
                long res = mViewHolder.db.Delete_Pedidos(nomeget);

                if(res>0){
                    Toast.makeText(DadosPedidosActivity.this, "Pedido eliminado com sucesso", Toast.LENGTH_SHORT).show();
                    setResult(2,mViewHolder.i);
                    finish();

                } else{
                    Toast.makeText(DadosPedidosActivity.this, "Erro ao eliminar pedido", Toast.LENGTH_SHORT).show();
                    

                }




            }
        });






        mViewHolder.bt_modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                double preco = Double.parseDouble(mViewHolder.et_preco.getText().toString());
                
                if(!mViewHolder.et_nome.getText().toString().trim().isEmpty()
                        && !mViewHolder.et_descricao.getText().toString().trim().isEmpty() &&
                        !mViewHolder.et_preco.getText().toString().isEmpty()){
                    String descricao= mViewHolder.et_descricao.getText().toString();
                    String nome = mViewHolder.et_nome.getText().toString();
                    long res = mViewHolder.db.Update_Pedidos(nome,descricao,preco);
                    
                    
                    if(res>0) {
                        Toast.makeText(DadosPedidosActivity.this, "Pedido alterado com sucesso", Toast.LENGTH_SHORT).show();
                        setResult(1,mViewHolder.i);
                        finish();
                        
                    } else {
                        Toast.makeText(DadosPedidosActivity.this, "Erro ao alterar pedido", Toast.LENGTH_SHORT).show();
                        
                    }







                } else {
                    Toast.makeText(DadosPedidosActivity.this, "Deve inserir dados nos campos vazios", Toast.LENGTH_SHORT).show();
                }



            }
        });








    }

    private void carregarDadosPedidos() {
        String nome = mViewHolder.i.getExtras().getString("nome");
        Cursor c = mViewHolder.db.SelectByNome_Pedidos(nome);
        c.moveToFirst();




        if(c.getCount() == 1) {
            String descricao = c.getString(c.getColumnIndex("descricao"));
            String preco = c.getString(c.getColumnIndex("preco"));

            mViewHolder.et_nome.setText(nome);
            mViewHolder.et_descricao.setText(descricao);
            mViewHolder.et_preco.setText(preco);
        } else if (c.getCount() == 0){
            Toast.makeText(this, "Pedido inexistente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erro ao carregar utilizador", Toast.LENGTH_SHORT).show();
        }



    }


    private static class ViewHolder{
        EditText et_nome,et_descricao,et_preco;
        Button bt_modificar,bt_eliminar , bt_cancelar;
        Intent i;
        DBHelper db;



    }
}