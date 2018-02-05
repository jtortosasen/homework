package com.example.wasdf.examenandroid;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Option1Activity extends MainActivity {

    EditText editTextNumber;
    TextView textViewBinary;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option1);

        editTextNumber = (EditText) findViewById(R.id.editTextNumber);
        textViewBinary = (TextView) findViewById(R.id.textViewBinary);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

    }

    public void onClickConvertDecToBin(View v){
        new MyAsyncTask().execute(Integer.parseInt(editTextNumber.getText().toString()));
    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, int[]> {

        int myIndex;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
        }

        @Override
        protected int[] doInBackground(Integer... number) {
            int num = number[0];
            int binary[] = new int[40];
            int index = 0;
            while(num > 0){
                binary[index++] = num%2;
                num = num/2;
                publishProgress(5);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            myIndex = index;
            return binary;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.incrementProgressBy(values[0]);
        }
        @Override
        protected void onPostExecute(int[] array) {
            String decimalNumber = "[";
            for(int i = myIndex -1; i >= 0; i--){
                decimalNumber = decimalNumber.concat(Integer.toString(array[i])).concat(",");
            }
            decimalNumber = decimalNumber.concat("]");

            textViewBinary.setText(decimalNumber);
            progressBar.setProgress(100);
        }
    }
}
