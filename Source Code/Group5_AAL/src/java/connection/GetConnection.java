/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HieuDQ_B00385
 */
public class GetConnection {

    Connection con = null;
    private static ResourceBundle bundle = ResourceBundle.getBundle("Connection");
    private String host;
    private String pcName;
    private String port;
    private String databaseName;
    private String user;
    private String pass;

    public Connection getConnec() {
        try {
            host = bundle.getString("host");
            pcName = bundle.getString("pcName");
            port = bundle.getString("port");
            databaseName = bundle.getString("databaseName");
            user = bundle.getString("user");
            pass = bundle.getString("pass");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://" + host + "\\" + pcName + ":" + port + ";databaseName=" + databaseName + "", user, pass);
            if (con != null) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(GetConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
