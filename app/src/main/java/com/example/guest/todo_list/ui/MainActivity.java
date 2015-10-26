package com.example.guest.todo_list.ui;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.todo_list.R;
import com.example.guest.todo_list.models.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private ArrayList<String> mTasks;
    private Button mNewTaskButton;
    private EditText mNewTaskText;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Task newTask = new Task();
        newTask.save();
        newTask.delete();//these will run migrations.

        mNewTaskButton = (Button) findViewById(R.id.newTaskButton);
        mNewTaskText = (EditText) findViewById(R.id.newTask);
        mTasks = new ArrayList<>();

        for (Task task : Task.all()) {
            mTasks.add(task.getDescription());
        }

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mTasks);
            setListAdapter(mAdapter);

        mNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });
    }

    private void addTask() {
        String description = mNewTaskText.getText().toString();
        Task newTask = new Task(description);
        newTask.save();
        mTasks.add(description);
        mAdapter.notifyDataSetChanged();
    }

}
