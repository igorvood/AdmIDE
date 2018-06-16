package ru.vood.Plugin.db;

import oracle.jdbc.OracleDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vood.Plugin.admPlugin.tune.ListTunes;
import ru.vood.Plugin.applicationConst.AppConst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Класс работы с коннектом к базе
 */
@Deprecated
public class DBConnect {
    private final static Logger lOG = LoggerFactory.getLogger(DBConnect.class);
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

            lOG.error("Не удалось создать соединение." + _thinConn, sqle);
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
