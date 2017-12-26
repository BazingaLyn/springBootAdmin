package com.geekcattle.config.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.sql.*;

/**
 * @Author lgl
 * @time 2017/11/14 10:06
 * @desc
 */
public class SSHConnection {

    public SSHConnection(){
        String user = "aijia";
        String password = "QX9CA&1cYB9*eWwS";
        String host = "121.196.205.228";
        int port=2525;
        try
        {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            int lport = 3307;
            String rhost = "10.11.0.1";
            int rport = 3306;
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            int assinged_port=session.setPortForwardingL("127.0.0.1",3310, "10.11.0.1", 3306);
            System.out.println("localhost:"+assinged_port+" -> "+rhost+":"+rport);
        }
        catch(Exception e){System.err.print(e);}
    }

    public static void main(String[] args) {
        try{
            SSHConnection sshConnection = new SSHConnection();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.println("An example for updating a Row from Mysql Database!");
        Connection con = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3310/account";//" + rhost +"
        String dbUser = "read_only";
        String dbPasswd = "@roAijia!123";
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url, dbUser, dbPasswd);
            try{
                Statement st = con.createStatement();
                String sql = "select * from account.account_amount limit 10" ;

                ResultSet result = st.executeQuery(sql);
                while(result.next()){
                    System.out.println(result.getObject(0).toString() + result.getObject(1).toString());
                }
            }
            catch (SQLException s){
                System.out.println("SQL statement is not executed!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
