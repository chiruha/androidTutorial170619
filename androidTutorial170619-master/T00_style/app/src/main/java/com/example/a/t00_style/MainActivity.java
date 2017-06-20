package com.example.a.t00_style;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView selectedTextView, workingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedTextView = (TextView) findViewById(R.id.selectedTextView);
        workingTextView = (TextView) findViewById(R.id.workingTextView);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String str = btn.getText().toString();
                if(workingTextView.getText().toString().equals("0"))
                    workingTextView.setText(str);
                else
                    workingTextView.append(str);
            }
        };

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        int number = 1;
        for(int i=2; i<tableLayout.getChildCount()-1; i++){
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for(int k=0; k<row.getChildCount(); k++){
                Button button = (Button) row.getChildAt(k);
                button.setText(""+number);
                number += 1;
                button.setOnClickListener(numberListener);
            }
        }

        TableRow bottomRow = (TableRow) tableLayout.getChildAt( tableLayout.getChildCount()-1 );
        Button deleteButton = (Button) bottomRow.getChildAt(0);
        deleteButton.setText("delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingTextView.setText("0");
            }
        });
        Button zeroButton = (Button)bottomRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberListener);

        Button enterButton = (Button)bottomRow.getChildAt(2);
        enterButton.setText("enter");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTextView.setText( workingTextView.getText() );
                workingTextView.setText("0");
            }
        });
    }
}
