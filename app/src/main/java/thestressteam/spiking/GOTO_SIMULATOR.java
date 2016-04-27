package thestressteam.spiking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class GOTO_SIMULATOR extends AppCompatActivity {

    LinearLayout from, to, clear;
    TextView transitionFrom,transitionTo, transitionClear, console;
    int transitionCount = 0, lineNum;
    ViewGroup instruction;
    View printBlock, remBlock, gotoBlock,ifBlock, letBlock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto__simulator);

        //initialising by clearing display screen and console
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        if (instruction.getChildCount() > 0) {
            instruction.removeAllViews();
        }
            console = (TextView) findViewById(R.id.console);
            console.setText("");
    }

    //when the PRINT button is clicked
    public void onClickPrint(View v) {
        //add PRINT block to display screen
        instruction = (ViewGroup) findViewById(R.id.displayInstructions);
        printBlock = getLayoutInflater().inflate(R.layout.printblock,instruction,false);
        instruction.addView(printBlock);

        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
        console.append("PRINT block added.\n");
    }

    public void onClickRem(View v) {
        //add REM block to display screen
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        remBlock = getLayoutInflater().inflate(R.layout.remblock,instruction,false);
        instruction.addView(remBlock);

        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
        console.append("REM block added.\n");
    }

    public void onClickGoto(View v) {
        //add GOTO block to display screen
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        gotoBlock = getLayoutInflater().inflate(R.layout.gotoblock,instruction,false);
        instruction.addView(gotoBlock);

        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
        console.append("GOTO block added.\n");
    }

    public void onClickIf(View v) {
        //add IF block to display screen
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        ifBlock = getLayoutInflater().inflate(R.layout.ifblock,instruction,false);
        instruction.addView(ifBlock);

        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
        console.append("IF block added.\n");
    }

    public void onClickLet(View v) {
        //add LET block to display screen
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        letBlock = getLayoutInflater().inflate(R.layout.letblock,instruction,false);
        instruction.addView(letBlock);

        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
        console.append("LET block added.\n");
    }


    public void onClickTransition(View v){
        //get display-screen object
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);

        //reset all transition lines to default
        for (int x = 0; x<instruction.getChildCount();x++){
            clear = (LinearLayout) instruction.getChildAt(x);

            transitionClear = (TextView) clear.getChildAt(0);
            transitionClear.setText("||");
        }

        lineNum = transitionCount;

        //if its not the first transition, get the previous block/line of the transition
        if (transitionCount != 0){
            from = (LinearLayout) instruction.getChildAt(lineNum-1);
            //clear = from;
            transitionFrom = (TextView) from.getChildAt(0);
            transitionFrom.setText("FROM>");

            //if previous block/line is a GOTO, set the current block/line to the value of GOTO input
            if (from.getId() == R.id.gotoBlock) {
                EditText input = (EditText) instruction.findViewById(R.id.gotoEditText);
                String inputText = input.getText().toString();
                transitionCount = Integer.parseInt(inputText)-1;

            }
        }
        //get current block/line of transition
        to = (LinearLayout) instruction.getChildAt(transitionCount);
        transitionTo = (TextView) to.getChildAt(0);
        transitionTo.setText("TO>");
        transitionCount += 1;   //increase counter for next transition

        //print text to console indicating current block/line
        TextView currentBlockType = (TextView) to.getChildAt(1);
        TextView input = (TextView) to.getChildAt(2);
        console = (TextView) findViewById(R.id.console);
        console.append(String.format("%s: %s\n",currentBlockType.getText(),input.getText()));
    }

    //clear display screen if there are blocks
    public void onClickClearDisplay(View v){
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        if (instruction.getChildCount() > 0){
            instruction.removeAllViews();
        }
    }

    //clear console if there are texts
    public void onClickClearConsole(View v){
        console = (TextView) findViewById(R.id.console);
        if (!console.getText().equals("")){
            console.setText("");
        }
    }
}