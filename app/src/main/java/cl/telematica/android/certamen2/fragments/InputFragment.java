package cl.telematica.android.certamen2.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cl.telematica.android.certamen2.R;
import cl.telematica.android.certamen2.connection.HttpServerConnection;

public class InputFragment extends Fragment {
    String consulta;
    String respuesta;

    EditText mNombre;
    EditText mApellido;
    Button mBoton;

    TextView mRespuesta;

    /**
     * New instance of InputFragment
     *
     * @return new instance of InputFragment
     */
    public static InputFragment newInstance() {
        InputFragment fragment = new InputFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.fragment_input, null);

        mNombre = (EditText) mainView.findViewById(R.id.nombre);
        mApellido = (EditText) mainView.findViewById(R.id.apellido);
        mRespuesta = (TextView) mainView.findViewById(R.id.respuesta);

        mBoton = (Button) mainView.findViewById(R.id.boton);
        mBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consulta = "?firstName=" + mNombre.getText().toString() + "&lastName=" + mApellido.getText().toString();
                AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

                    @Override
                    protected void onPreExecute(){

                    }

                    @Override
                    protected String doInBackground(Void... params) {
                        String resultado = new HttpServerConnection().connectToServer("http://api.icndb.com/jokes/random"+consulta, 10000);
                        return resultado;
                    }

                    @Override
                    protected void onPostExecute(String result) {
                        if(result != null){
                            try {
                                JSONObject obj = new JSONObject(result);
                                //System.out.println(obj.getJSONObject("value").getString("joke"));
                                respuesta = obj.getJSONObject("value").getString("joke");
                                mRespuesta.setText(respuesta);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                };

                task.execute();
            }
        });

        /*
         * Aquí es donde deben hacer el link a los elementos del layout fragment_input,
         * y donde prácticamente se debe hacer el desarrollo
        */

        return mainView;
    }

}
