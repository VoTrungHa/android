package vn.edu.ntu.votrungha.Controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.votrungha.Model.SinhVien;

public class SinhVienController extends Application implements ISinhVien {

    List<SinhVien> ds=new ArrayList<>();

    public SinhVienController()
    {
        ds.add(new SinhVien("vo trung hai","10/10/2020","15613213","nam",
                "ninh hòa",7.5f,true,true,false));

        ds.add(new SinhVien("vo trung ha","10/10/2020","15613213","nam",
                            "ninh hòa",7.5f,true,true,false));
    }


    @Override
    public List<SinhVien> getDanhsach() {
        return ds;
    }
    @Override
    public Boolean ThemSinhVien(SinhVien sinhVien) {

        for(SinhVien item: ds)
        {
            if(item.getName().equalsIgnoreCase(sinhVien.getName()))
            {
                return false;
            }
        }
        ds.add(sinhVien);
        return true;
    }

    @Override
    public SinhVien getSinhVienByNam(String name) {

        SinhVien sv=new SinhVien();
        for(SinhVien item:ds)
        {
            if(item.getName().equalsIgnoreCase(name))
            {
                sv=item;
            }
        }
        return sv;
    }

    @Override
    public Boolean UpdateSinhVien(SinhVien sinhVien,String name) {

        SinhVien sv= getSinhVienByNam(name);

        sv.setName(sinhVien.getName());
        sv.setDate(sinhVien.getDate());
        sv.setDiem(sinhVien.getDiem());
        sv.setDienthoai(sinhVien.getDienthoai());
        sv.setGioiTinh(sinhVien.getGioiTinh());
        sv.setNoichon(sinhVien.getNoichon());
        sv.setDulich(sinhVien.getDulich());
        sv.setGame(sinhVien.getGame());
        sv.setHoctap(sinhVien.getHoctap());
        return true;
    }

    @Override
    public Boolean DeleteSinhVien(SinhVien sinhVien) {
        ds.remove(sinhVien);
        return true;
    }
}
