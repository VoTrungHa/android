package vn.edu.ntu.votrungha.Controller;

import java.util.List;

import vn.edu.ntu.votrungha.Model.SinhVien;

public interface ISinhVien {

    public List<SinhVien> getDanhsach();
    public Boolean ThemSinhVien(SinhVien sinhVien);
    public SinhVien getSinhVienByNam(String name);
    public Boolean UpdateSinhVien(SinhVien sinhVien,String name);
    public Boolean DeleteSinhVien (SinhVien sinhVien);
}
