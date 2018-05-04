package asquero.com.myapplication.sqdatabase;

public class database {
    private int _id;
    private String _noti;

    public database(){
    }

    public database(String noti){
        this._noti = noti;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_noti(String _noti) {
        this._noti = _noti;
    }

    public int get_id() {
        return _id;
    }

    public String get_noti() {
        return _noti;
    }
}
