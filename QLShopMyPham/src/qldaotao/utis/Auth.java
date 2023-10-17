package qldaotao.utis;

import qlsmp.Model.Account;

public class Auth {
    public static Account user = null;
    public static void clear(){
        Auth.user = null;
    }
    public static boolean isLogin(){
        return Auth.user != null;
    }
    public static boolean isManager(){
        return Auth.isLogin()&&user.isVaiTro();
    }
}
