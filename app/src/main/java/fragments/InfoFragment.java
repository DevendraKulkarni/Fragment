package fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fragment.example.com.fragment.R;

/**
 * Created by lenovo on 9/24/2016.
 */
public class InfoFragment extends Fragment {

    public interface IInfoFragment{
        public void setInfoTitle(String title);
    }

    public IInfoFragment iInfoFragment;

    public static InfoFragment infoFragment;

    public static InfoFragment getInstance(String fName, String lName, FormFragment.Gender gender) {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.firstName, fName);
        bundle.putString(Constant.lastName, lName);
        bundle.putSerializable(Constant.gender, gender);

        infoFragment = new InfoFragment();
        infoFragment.setArguments(bundle);
        return infoFragment;
    }

    private InfoFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity() != null){
            iInfoFragment = (IInfoFragment)getActivity();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getActivity() != null){
            iInfoFragment = (IInfoFragment)getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_layout, null);
        iInfoFragment.setInfoTitle(getString(R.string.info_fragment));
        Bundle bundle = getArguments();
        TextView dfName = (TextView) view.findViewById(R.id.dfName);
        TextView lfName = (TextView) view.findViewById(R.id.dlName);
        TextView gender = (TextView) view.findViewById(R.id.dgender);

        dfName.setText(bundle.getString(Constant.firstName));
        lfName.setText(bundle.getString(Constant.lastName));

        FormFragment.Gender gender1  = (FormFragment.Gender) bundle.getSerializable(Constant.gender);
        if(gender1 == FormFragment.Gender.Male){
            gender.setText(getActivity().getString(R.string.male));
        } else {
            gender.setText(getActivity().getString(R.string.female));
        }

        return view;
    }
}
