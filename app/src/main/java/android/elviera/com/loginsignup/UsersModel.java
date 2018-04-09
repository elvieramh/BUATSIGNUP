package android.elviera.com.loginsignup;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Date;

@IgnoreExtraProperties
public class UsersModel {
    private String nama,username,password,alamat,noidentitas,notelp,email,pertanyaanrahasia,jawabanrahasia;
    private Date tgllahir;
    private String gender;
    private String key;

    public UsersModel() {
    }

    public UsersModel(String nama, String username, String password, String alamat, String noidentitas, String notelp, String email, String pertanyaanrahasia, String jawabanrahasia, Date tgllahir, String genderGroup) {
        this.nama = nama;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.noidentitas = noidentitas;
        this.notelp = notelp;
        this.email = email;
        this.pertanyaanrahasia = pertanyaanrahasia;
        this.jawabanrahasia = jawabanrahasia;
        this.tgllahir = tgllahir;
        this.gender = genderGroup;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoidentitas() {
        return noidentitas;
    }

    public void setNoidentitas(String noidentitas) {
        this.noidentitas = noidentitas;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPertanyaanrahasia() {
        return pertanyaanrahasia;
    }

    public void setPertanyaanrahasia(String pertanyaanrahasia) {
        this.pertanyaanrahasia = pertanyaanrahasia;
    }

    public String getJawabanrahasia() {
        return jawabanrahasia;
    }

    public void setJawabanrahasia(String jawabanrahasia) {
        this.jawabanrahasia = jawabanrahasia;
    }

    public Date getTgllahir() {
        return tgllahir;
    }

    public void setTgllahir(Date tgllahir) {
        this.tgllahir = tgllahir;
    }

    public String getGenderGroup() {
        return gender;
    }

    public void setGenderGroup(String genderGroup) {
        this.gender = genderGroup;
    }
}
