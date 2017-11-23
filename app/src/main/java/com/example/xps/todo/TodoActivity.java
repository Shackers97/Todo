package com.example.xps.todo;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TodoActivity extends AppCompatActivity
{
    private String[] mTodos;
    private int mTodoIndex = 0;
    private static final String TODO_INDEX = "todoIndex";

    public static final String TAG = "TodoActivity";

    // override to write the value of mTodoIndex into the Bundle with TODO_INDEX as its key
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, mTodoIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        Log.d(TAG, " *** Just to say the PC is in onCreate!");

        // set the user interface layout for this Activity
        // the layout file is defined in the project res/layout/activity_todo.xml file
        setContentView(R.layout.activity_todo);

        // check for saved state due to changes such as rotation or back button
        // and restore any saved state such as the todo index
        if (savedInstanceState != null)
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0);

        // initialize member TextView so we can manipulate it later
        final TextView TodoTextView;
        TodoTextView = (TextView) findViewById(R.id.textViewTodo);

        // read the todo array from res/values/strings.xml
        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todo);

        // display the first task from mTodo array in the TodoTextView
        TodoTextView.setText(mTodos[mTodoIndex]);

        Button buttonNext,buttonPrev;
        buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonPrev = (Button) findViewById(R.id.buttonPrev);

        // OnClick listener for the  Next button
        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mTodoIndex += 1;
                if(mTodoIndex >= mTodos.length)
                    mTodoIndex = 0;
                TodoTextView.setText(mTodos[mTodoIndex]);
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mTodoIndex -= 1;
                if(mTodoIndex <= 0)
                    mTodoIndex = mTodos.length-1;
                TodoTextView.setText(mTodos[mTodoIndex]);
            }
        });
    }
}
