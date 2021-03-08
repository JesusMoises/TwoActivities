package com.example.twoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //para mostrar mensaje en consola
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //mensaje mediante clave-valor definida public para poder usarla tambien en la SecondActivity
    public static final String EXTRA_MESSAGE = "com.example.twoactivities.extra.MESSAGE";
    //Creamos variable privada para hacer referencia a la edición
    private EditText mMessageEditText;

    //variable para saber cuando se reciba una respuesta
    public static final int TEXT_REQUEST = 1;
    //
    private TextView mReplyHeaderTextView;
    private TextView mReplyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtenemos la referencia que fue escrito en nuestra primera activity EditText y lo guardamos en mMessageEditText
        mMessageEditText = findViewById(R.id.editText_main);

        //
        mReplyHeaderTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);

    }//fin  del método onCreate

    public void launchSecondActivity(View view) {
        //mandamos por consola el msj siguiente
        Log.d(LOG_TAG, "Button clicked");

        //obtenemos la cadena que tiene nuestra variable mMessageEditText
        String message = mMessageEditText.getText().toString();

        /*creamos objeto Intent para segunda activity, en el primer parámetro hacemos referencia a la clase actual y el segundo a
        la clase a la que vamos en este caso nuestra SecondActivity*/
        Intent intent = new Intent(this, SecondActivity.class);
        /*con nuestro objeto intent creado ahora tomamos el valor del ID que es el valor clave, el segundo parámetro es el texto
        real obtenido del mMessageEditText*/
        intent.putExtra(EXTRA_MESSAGE, message);
        //lanzamos nuestra SecondActivity
        startActivityForResult(intent, TEXT_REQUEST);
    }//fin del método launchSecondActivity

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);

                mReplyHeaderTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }

}//find e la clase MainActivity