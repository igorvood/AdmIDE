package ru.vood.Plugin.db;

import oracle.jdbc.OracleDriver;
import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;
import ru.vood.Plugin.logging.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Класс работы с коннектом к базе
 */
@Deprecated
public class DBConnect {
    private static Log log = Log.getLogger(DBConnect.class);
    private static Connection _conn = null;
    private static String _username = AppConst.getTune(ListTunes.USER);
    private static String _password = AppConst.getTune(ListTunes.PASSWORD);
    //пример jdbc:oracle:thin:@//alonso.ftc.ru:1548/b0t1529
    private static String _thinConn = "jdbc:oracle:thin:@//" + AppConst.getTune(ListTunes.HOST)
            + ":" + AppConst.getTune(ListTunes.PORT) + "/"
            + AppConst.getTune(ListTunes.SID);

    public static Connection getConnection() {
        try {
            if (_conn == null || _conn.isClosed()) {
                OracleDriver od = new OracleDriver();

                DriverManager.registerDriver(od);
                _conn = DriverManager.getConnection(_thinConn, _username, _password);
                _conn.setAutoCommit(true);
            }
        } catch (Exception sqle) {

            log.putToLog("Не удалось создать соединение." + _thinConn, Level.SEVERE, sqle);
        }
        return _conn;
    }

    public static Connection getConnection(String thinConn, String user, String password) {
        _thinConn = thinConn;
        _username = user;
        _password = password;
        return getConnection();
    }

    public static void closeConnection() throws SQLException {
        if (_conn != null) {
            _conn.close();
        }
    }

}
