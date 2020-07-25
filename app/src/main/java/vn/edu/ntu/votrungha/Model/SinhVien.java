package vn.edu.ntu.votrungha.Model;

public class SinhVien {
    private String name,date,dienthoai,gioiTinh,noichon;
    private float diem;
    private Boolean game;
    private Boolean hoctap;
    private Boolean dulich;

    public SinhVien(){};

    public SinhVien(String name, String date, String dienthoai, String gioiTinh,
                    String noichon, float diem, Boolean game, Boolean hoctap, Boolean dulich) {
        this.name = name;
        this.date = date;
        this.dienthoai = dienthoai;
        this.gioiTinh = gioiTinh;
        this.noichon = noichon;
        this.diem = diem;
        this.game = game;
        this.hoctap = hoctap;
        this.dulich = dulich;
    }

    public void setGame(Boolean game) {
        this.game = game;
    }

    public void setHoctap(Boolean hoctap) {
        this.hoctap = hoctap;
    }

    public void setDulich(Boolean dulich) {
        this.dulich = dulich;
    }

    public Boolean getGame() {
        return game;
    }

    public Boolean getHoctap() {
        return hoctap;
    }

    public Boolean getDulich() {
        return dulich;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setNoichon(String noichon) {
        this.noichon = noichon;
    }



    public void setDiem(float diem) {
        this.diem = diem;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public String getNoichon() {
        return noichon;
    }



    public float getDiem() {
        return diem;
    }
}
