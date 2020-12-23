package edu.ib.androidcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Calculator c=new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hiding title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null){
            c.setExpression(new StringBuilder(savedInstanceState.getString("expression")));
            c.setLastValue(savedInstanceState.getString("lastValue"));
            TextView textView = (TextView) findViewById(R.id.txtOut);
            textView.setText(c.toString());
        }
    }

    public void addExpression(View view) {
        Button button=(Button) findViewById(view.getId());
        TextView out=(TextView) findViewById(R.id.txtOut);
        String value = button.getText().toString();
        if (out.getLineCount() == 2) {
            c.getExpression().setLength(0);
            out.setText(c.getExpression().toString());
        }
        switch (value) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "(":
            case ")":
            case ".":
            case "+":
            case "-":
            case "/":
            case "*":
            case "E":
            case ",":
            case "ANS":
            case "^":
            case "!":
                c.addExpresion(value.toLowerCase());
                break;
            case "π":
                c.addExpresion("pi");
                break;
            case "sin":
                c.addExpresion("sin(");
                break;
            case "cos":
                c.addExpresion("cos(");
                break;
            case "tg":
                c.addExpresion("tg(");
                break;
            case "ctg":
                c.addExpresion("ctg(");
                break;
            case "ln":
                c.addExpresion("ln(");
                break;
            case "min":
                c.addExpresion("min(");
                break;
            case "max":
                c.addExpresion("max(");
                break;
            case "√":
                c.addExpresion("sqrt(");
                break;
            case "log":
                c.addExpresion("log(");
                break;
        }

        out.setText(c.toString());
    }

    public void calculate(View view) {
        TextView textView = (TextView) findViewById(R.id.txtOut);
        String out=c.calculate();
        out=c.toString()+"\n"+out;
        textView.setText(out);
    }

    public void removeExpression(View view) {
        TextView textView = (TextView) findViewById(R.id.txtOut);
        c.removeExpresion();
        textView.setText(c.toString());
    }

    public void clear(View view) {
        TextView textView = (TextView) findViewById(R.id.txtOut);
        c.setLastValue("");
        c.getExpression().setLength(0);
        textView.setText("");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("expression",c.getExpression().toString());
        outState.putString("lastValue",c.getLastValue());
    }
}