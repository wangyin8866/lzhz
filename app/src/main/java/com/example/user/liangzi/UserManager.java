package com.example.user.liangzi;

/**
 * Created by User on 2017/4/13.
 */

public class UserManager {
    private static UserManager instance;

    private boolean isLogin = false;
    private String juid;
    private String join_no;

    private String mobile = "";
    private String head_pic = "";
    private String name = "";
    private int insure_num  ;
    private String top_insure = "";
    private int count_invite ;
    private String count_help = "";
    private int rank ;
    private double amount ;
    private String[] family_join;
    private String invite_code;

     private UserManager(){

     }

     public static UserManager getInstance(){
         if(instance == null) {
             synchronized (UserManager.class) {
                 if(instance == null) {
                     instance = new UserManager();
                 }
             }
         }

         return instance;
     }

    public static void clear(){
        instance = null;
    }

    public String getJuid() {
        return juid;
    }

    public void setJuid(String juid) {
        this.juid = juid;
    }

    public String getJoin_no() {
        return join_no;
    }

    public void setJoin_no(String join_no) {
        this.join_no = join_no;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHead_pic() {
        return head_pic;
    }

    public void setHead_pic(String head_pic) {
        this.head_pic = head_pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInsure_num() {
        return insure_num;
    }

    public void setInsure_num(int insure_num) {
        this.insure_num = insure_num;
    }

    public String getTop_insure() {
        return top_insure;
    }

    public void setTop_insure(String top_insure) {
        this.top_insure = top_insure;
    }

    public int getCount_invite() {
        return count_invite;
    }

    public void setCount_invite(int count_invite) {
        this.count_invite = count_invite;
    }

    public String getCount_help() {
        return count_help;
    }

    public void setCount_help(String count_help) {
        this.count_help = count_help;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String[] getFamily_join() {
        return family_join;
    }

    public void setFamily_join(String[] family_join) {
        this.family_join = family_join;
    }

    public String getInvite_code() {
        return invite_code;
    }

    public void setInvite_code(String invite_code) {
        this.invite_code = invite_code;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
