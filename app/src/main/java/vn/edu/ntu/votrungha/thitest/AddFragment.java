package vn.edu.ntu.votrungha.thitest;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.ntu.votrungha.Controller.ISinhVien;
import vn.edu.ntu.votrungha.Model.SinhVien;

public class AddFragment extends Fragment {
    NavController controller;
    ISinhVien isinhvien;
    private EditText mName;
    private EditText mDienthoai;
    private EditText mDiem;
    private EditText mDate;
    private ImageView mBtndate;
    private RadioButton mNam;
    private RadioButton mNu;
    private RadioGroup mRg;
    private CheckBox mCbgame;
    private CheckBox mHoc;
    private CheckBox mDulich;
    private Spinner mSpinner;
    private Button mBtnadd;

    List<String> st;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }
    public void addView(View view)
    {
        mName = view.findViewById(R.id.name);
        mDienthoai = view.findViewById(R.id.dienthoai);
        mDiem = view.findViewById(R.id.diem);
        mDate = view.findViewById(R.id.date);
        mBtndate = view.findViewById(R.id.btndate);
        mNam = view.findViewById(R.id.nam);
        mNu = view.findViewById(R.id.nu);
        mRg = view.findViewById(R.id.rg);
        mCbgame = view.findViewById(R.id.cbgame);
        mHoc = view.findViewById(R.id.hoc);
        mDulich = view.findViewById(R.id.dulich);
        mSpinner = view.findViewById(R.id.spinner);
        mBtnadd = view.findViewById(R.id.btnadd);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(this);
        ((MainActivity) getActivity()).controller = controller;
        isinhvien = (ISinhVien) getActivity().getApplication();
        addView(view);
        Spinner();
        addEven1();
        ConnectaddEvent();



    }
    public void addEven1()
    {
        mBtndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chonngay();
            }
        });

    }

    public void connect(SinhVien sv)
    {
        mName.setText(sv.getName());
        if(sv.getGioiTinh().equalsIgnoreCase("nam"))
        {
            mNam.setChecked(true);
        }
        else
        {
            mNu.setChecked(true);
        }

        mDate.setText(sv.getDate());
        mDiem.setText(sv.getDiem()+"");
        mDienthoai.setText(sv.getDienthoai());
        mSpinner.getSelectedItem();
        mDulich.setChecked(sv.getDulich());
        mCbgame.setChecked(sv.getGame());
        mHoc.setChecked(sv.getHoctap());

    }

    public void Spinner()
    {
       List<String> list=new ArrayList<>();
        list.add("Ninh Hòa");
        list.add("Phú Yên");
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,list);
        mSpinner.setAdapter(spinnerAdapter);

    }
    public void chonngay()
    {
        Calendar calendar= Calendar.getInstance();
        DatePickerDialog.OnDateSetListener listener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                StringBuilder buffer=new StringBuilder();
                buffer.append(dayOfMonth)
                        .append("-")
                        .append(++month)
                        .append("-")
                        .append(year);

                mDate.setText(buffer.toString());
            }


        };
        DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),
                listener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private List<String> CheckBoxAction()
    {
       st=new ArrayList<>();

        CheckBox [] arrCb=new CheckBox[]{mCbgame,mDulich,mHoc};
        for (int i=0;i<arrCb.length;i++)
        {
            if(arrCb[i].isChecked())
            {
                st.add(arrCb[i].getText().toString());
            }
        }

        return st;
    }

    public String GioiTinh()
    {
        switch (mRg.getCheckedRadioButtonId())
        {
            case R.id.nam: return "Nam";
            case R.id.nu: return "Nữ";

            default: return "nam";
        }
    }

    public SinhVien adevent(SinhVien sinhVien)//set lại sinh viên
    {
        String gt="";
        if(mNam.isChecked())
        {
            gt="Nam";
        }
        else
        {
            gt="Nữ";
        }
        sinhVien.setName(mName.getText().toString());
        sinhVien.setNoichon(mSpinner.getSelectedItem().toString());
        sinhVien.setHoctap(mHoc.isChecked());
        sinhVien.setDulich(mDulich.isChecked());
        sinhVien.setGame(mCbgame.isChecked());
        sinhVien.setGioiTinh(gt);
        sinhVien.setDiem( Float.parseFloat(mDiem.getText().toString()));
        sinhVien.setDate(mDate.getText().toString());
        return sinhVien;
    }

    public void ConnectaddEvent()
    {
        Bundle bundle=getArguments();
        Boolean check= bundle.getBoolean("check");
        if(check)
        {
            mBtnadd.setText("Update");
            final String name=bundle.getString("name");
            final SinhVien sinhVien =isinhvien.getSinhVienByNam(name);// lấy sinh viên theo tên
            connect(sinhVien);// hiển thị sinh viê ra màn hình
            mBtnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if( isinhvien.UpdateSinhVien(adevent(sinhVien),name))
                    {
                        Toast.makeText(getActivity(),"update thành công",Toast.LENGTH_SHORT).show();
                        controller.navigate(R.id.action_addFragment_to_danhSachFragment);
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"update không thành công",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            mBtnadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     them();

                }
            });
        }
    }

    public void them()
    {
        CheckBoxAction();
         SinhVien sinhVien=new SinhVien(mName.getText().toString(),mDate.getText().toString(),mDienthoai.getText().toString(),
                GioiTinh(),mSpinner.getSelectedItem().toString(),Float.parseFloat(mDiem.getText().toString()),
                 mCbgame.isChecked(),st.contains("học tập")?true:false,
                st.contains("Du Lịch")?true:false);
        if(!isinhvien.ThemSinhVien(sinhVien))
        {
            Toast.makeText(getActivity(),"Thất Bại",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(),"Thành Công",Toast.LENGTH_SHORT).show();
            controller.navigate(R.id.action_addFragment_to_danhSachFragment);
        }
    }


}