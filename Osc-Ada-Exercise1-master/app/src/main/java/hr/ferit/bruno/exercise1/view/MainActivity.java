package hr.ferit.bruno.exercise1.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.ferit.bruno.exercise1.R;
import hr.ferit.bruno.exercise1.TasksRepository;
import hr.ferit.bruno.exercise1.model.Task;

public class MainActivity extends AppCompatActivity {

	TasksRepository mRepository;

	@BindView(R.id.edittext_newtask_title) EditText mTitle;
	@BindView(R.id.edittext_newtask_summary) EditText mSummary;
	@BindView(R.id.edittext_newtask_importance) EditText mImportance;
	@BindView(R.id.textview_newtask_display) TextView mDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		mRepository = TasksRepository.getInstance();
	}

	@OnClick(R.id.button_newtask_save)
	public void storeTask(View view){
		if(mImportance.getText().toString().isEmpty() ||
				mSummary.getText().toString().isEmpty() ||
				mTitle.getText().toString().isEmpty()) {
			Toast.makeText(getApplicationContext(), "Invalid values.", Toast.LENGTH_SHORT).show();
			return;
		}
		mRepository.save(new Task(Integer.parseInt(mImportance.getText().toString()),
				mTitle.getText().toString(), mSummary.getText().toString()));
		clearGuiElements();
		displayTasks();
	}

	private void displayTasks() {
		if(mDisplay.getText().toString().startsWith(getResources().getString(R.string.newtask_tasksdisplay)))
			mDisplay.setText("");
		mDisplay.setText(mDisplay.getText()
		+ "\n" + mRepository.getTasks().get(mRepository.getTasks().size() - 1).getTitle()
		+ " " + mRepository.getTasks().get(mRepository.getTasks().size() - 1).getSummary()
		+ " " + mRepository.getTasks().get(mRepository.getTasks().size() - 1).getImportance());
	}

	private void clearGuiElements() {
		mImportance.getText().clear();
		mTitle.getText().clear();
		mSummary.getText().clear();
	}
}
