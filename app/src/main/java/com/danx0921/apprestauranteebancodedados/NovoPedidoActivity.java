package com.danx0921.apprestauranteebancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NovoPedidoActivity extends AppCompatActivity {

    ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);
        mViewHolder.et_nome = findViewById(R.id.et_NovoPedido_nome);
        mViewHolder.et_descricao = findViewById(R.id.et_NovoPedido_descricao);
        mViewHolder.et_preco = findViewById(R.id.et_NovoPedido_preco);
        mViewHolder.bt_gravar = findViewById(R.id.bt_NovoPedido_gravar);
        mViewHolder.bt_cancelar = findViewById(R.id.bt_NovoPedido_cancelar);
        mViewHolder.i = getIntent();
        mViewHolder.db = new DBHelper(this);


        mViewHolder.bt_gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = mViewHolder.et_nome.getText().toString();
                String descricao = mViewHolder.et_descricao.getText().toString();
                String preco = mViewHolder.et_preco.getText().toString();
                double precodouble = Double.parseDouble(mViewHolder.et_preco.getText().toString());







                  if (!nome.trim().isEmpty() && !descricao.trim().isEmpty() && !preco.trim().isEmpty()) {


                    long res = mViewHolder.db.Insert_Pedidos(nome, descricao, precodouble);

                    if(res>0){
                        Toast.makeText(NovoPedidoActivity.this, "Pedido criado com sucesso", Toast.LENGTH_SHORT).show();
                        setResult(1,mViewHolder.i);
                        finish();


                    } else {
                        Toast.makeText(NovoPedidoActivity.this, "Erro ao criar o pedido", Toast.LENGTH_SHORT).show();

                    }


                }





            }
        });


        mViewHolder.bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1, mViewHolder.i);
                finish();

            }
        });


    }


    public static class ViewHolder {
        EditText et_nome, et_descricao, et_preco;
        Button bt_gravar, bt_cancelar;
        Intent i;
        DBHelper db;


    }
}