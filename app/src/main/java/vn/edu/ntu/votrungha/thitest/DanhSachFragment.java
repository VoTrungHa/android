package vn.edu.ntu.votrungha.thitest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.edu.ntu.votrungha.Controller.ISinhVien;
import vn.edu.ntu.votrungha.Model.SinhVien;


public class DanhSachFragment extends Fragment {

    NavController controller;

    List<SinhVien> ds;
    ISinhVien isinhvien;
    RecyclerView rvlist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onPause() {
        super.onPause();
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_danh_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(this);
        ((MainActivity) getActivity()).controller = controller;
        ((MainActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        isinhvien = (ISinhVien) getActivity().getApplication();
        rvlist = view.findViewById(R.id.rvlist);
        connect();
    }

    public void connect() {
        rvlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        ds = isinhvien.getDanhsach();
        rvlist.setAdapter(new ApdapterSinhVien(ds));
    }

    private class ViewHolderSinhVien extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView mName;
        private TextView mGioitinh;
        private TextView mDate;
        private TextView mDiem;
        private TextView mDienthoai;
        private TextView mNoichon;
        private TextView mSothich;
        private ImageView mEdit;
        private ImageView mDelete;
        SinhVien sv;
        String name;

        public ViewHolderSinhVien(@NonNull View view) {
            super(view);
            addView(view);
        }
        public void addView(View view)
        {
            mImageView = view.findViewById(R.id.imageView);
            mName = view.findViewById(R.id.name);
            mGioitinh = view.findViewById(R.id.gioitinh);
            mDate = view.findViewById(R.id.date);
            mDiem = view.findViewById(R.id.diem);
            mDienthoai = view.findViewById(R.id.dienthoai);
            mNoichon = view.findViewById(R.id.noichon);
            mSothich = view.findViewById(R.id.sothich);
            mEdit = view.findViewById(R.id.edit);
            mDelete = view.findViewById(R.id.delete);
        }

        public void bind(SinhVien sv) {
            this.sv = sv;
            this.name=sv.getName();
            String st="";
            if(sv.getGame())
            {
                st+="Chơi Game ";
            }
            if(sv.getDulich())
            {
                st+="Du lịch ";
            }
            if(sv.getHoctap())
            {
                st+="học Tâp";
            }
            mName.setText(sv.getName());
            mGioitinh.setText(sv.getGioiTinh());
            mDate.setText(sv.getDate());
            mDiem.setText(sv.getDiem()+"");
            mDienthoai.setText(sv.getDienthoai());
            mNoichon.setText(sv.getNoichon());
            mSothich.setText(st);
            mEdit.setOnClickListener(this);
            mDelete.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int id=v.getId();
            switch (id)
            {
                case R.id.edit: UpdateSinhVien(this.name); ;break;
                case R.id.delete: DeleteSinhVien(sv);break;

            }
            connect();

        }

        public void UpdateSinhVien(String name)
        {
            boolean check=true;
            Bundle bundle =new Bundle();
            bundle.putString("name",name);
            bundle.putBoolean("check",check);
            controller.navigate(R.id.action_danhSachFragment_to_addFragment,bundle);

        }
        public void DeleteSinhVien(SinhVien sinhVien)
        {

            if(isinhvien.DeleteSinhVien(sinhVien))
            {
                Toast.makeText(getActivity(),"Xóa thành Công",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getActivity(),"Xóa không thành Công",Toast.LENGTH_SHORT).show();
            }

        }

    }

    private class ApdapterSinhVien extends RecyclerView.Adapter<ViewHolderSinhVien> {
        List<SinhVien> sv;


        public ApdapterSinhVien(List<SinhVien> sv) {
            this.sv = sv;
        }

        @NonNull
        @Override
        public ViewHolderSinhVien onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.content_main, parent, false);
            return new ViewHolderSinhVien(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderSinhVien holder, int position) {
            holder.bind(sv.get(position));
        }

        @Override
        public int getItemCount() {
            return sv.size();
        }
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main,menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.cartmn) {
            CallCartShopping();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void CallCartShopping()
    {
        Bundle bundle=new Bundle();
        Boolean check=false;
        bundle.putBoolean("check",check);
        controller.navigate(R.id.action_danhSachFragment_to_addFragment,bundle);
    }


}