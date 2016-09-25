package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.zip.Inflater;

import fragment.example.com.fragment.R;

/**
 * Created by lenovo on 9/24/2016.
 */
public class FormFragment extends Fragment implements View.OnClickListener{

    public EditText etFirstName, etLastName;
    public RadioGroup rgGender;
    public Button btnSave;
    public Gender gender = Gender.Male;

    public static enum Gender{
        Male, Female
    }
    public interface IFormFragment{
        public void onSaveClicked(String fName, String lName, Gender gender);
        public void setFormTitle(String name);
    }

    public static FormFragment formFragment;
    public IFormFragment iFormFragment;
    public static FormFragment getInstance(){
        if(formFragment == null){
            formFragment = new FormFragment();
        }
        return formFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getInstance() != null){
            iFormFragment = (IFormFragment) getActivity();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getInstance() != null){
            iFormFragment = (IFormFragment) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_fragment, null);
        iFormFragment.setFormTitle(getString(R.string.form_frag_title));
        init(view);
        return view;
    }

    public void init(View view) {
        btnSave = (Button) view.findViewById(R.id.btnSave);
        etFirstName = (EditText) view.findViewById(R.id.etFirstName);
        etLastName = (EditText) view.findViewById(R.id.etlastName);
        btnSave.setOnClickListener(this);

        rgGender = (RadioGroup) view.findViewById(R.id.radioGroup);
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbMale:
                        gender = Gender.Male;
                        break;

                    case R.id.rbFemale:
                        gender = Gender.Female;
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                if(firstName.trim().length() > 0 && lastName.trim().length() > 0) {
                    iFormFragment.onSaveClicked(firstName,
                            lastName, gender);
                }
                break;
        }
    }


}
