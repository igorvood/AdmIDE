package ru.vood.Plugin.admPlugin.tune;

import jfork.nproperty.Cfg;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Cfg
public class PluginTunes {
    @Value("${nameCurrentList}")
    private String nameCurrentList;

    @Value("${defaultConfiguration}")
    private Boolean defaultConfiguration;

    @Value("${packageIn}")
    private String packageIn;

    @Value("${password}")
    private String password;

    @Value("${host}")
    private String host;

    @Value("${port}")
    private String port;

    @Value("${sid}")
    private String sid;

    @Value("${tableSpaseSysTable}")
    private String tableSpaseSysTable;

    @Value("${tableSpaseSysIndex}")
    private String tableSpaseSysIndex;

    @Value("${tableSpaseUserTable}")
    private String tableSpaseUserTable;

    @Value("${tableSpaseUserIndex}")
    private String tableSpaseUserIndex;

    @Value("${encodding}")
    private String encodding;

    @Value("${prefixTable}")
    private String prefixTable;

    @Value("${prefixColomn}")
    private String prefixColomn;

    @Value("${dbmsType}")
    private String dbmsType;

    @Value("${user}")
    private String user;

    @Value("${owner}")
    private String owner;

    @Value("${storageTable}")
    private String storageTable;

    @Value("${storageIndex}")
    private String storageIndex;

    @Value("${defaultFolder}")
    private String defaultFolder;

    public String getNameCurrentList() {
        return nameCurrentList;
    }

    public void setNameCurrentList(String nameCurrentList) {
        this.nameCurrentList = nameCurrentList;
    }

    public Boolean getDefaultConfiguration() {
        return defaultConfiguration;
    }

    public void setDefaultConfiguration(Boolean defaultConfiguration) {
        this.defaultConfiguration = defaultConfiguration;
    }

    public String getPackageIn() {
        return packageIn;
    }

    public void setPackageIn(String packageIn) {
        this.packageIn = packageIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTableSpaseSysTable() {
        return tableSpaseSysTable;
    }

    public void setTableSpaseSysTable(String tableSpaseSysTable) {
        this.tableSpaseSysTable = tableSpaseSysTable;
    }

    public String getTableSpaseSysIndex() {
        return tableSpaseSysIndex;
    }

    public void setTableSpaseSysIndex(String tableSpaseSysIndex) {
        this.tableSpaseSysIndex = tableSpaseSysIndex;
    }

    public String getTableSpaseUserTable() {
        return tableSpaseUserTable;
    }

    public void setTableSpaseUserTable(String tableSpaseUserTable) {
        this.tableSpaseUserTable = tableSpaseUserTable;
    }

    public String getTableSpaseUserIndex() {
        return tableSpaseUserIndex;
    }

    public void setTableSpaseUserIndex(String tableSpaseUserIndex) {
        this.tableSpaseUserIndex = tableSpaseUserIndex;
    }

    public String getEncodding() {
        return encodding;
    }

    public void setEncodding(String encodding) {
        this.encodding = encodding;
    }

    public String getPrefixTable() {
        return prefixTable;
    }

    public void setPrefixTable(String prefixTable) {
        this.prefixTable = prefixTable;
    }

    public String getPrefixColomn() {
        return prefixColomn;
    }

    public void setPrefixColomn(String prefixColomn) {
        this.prefixColomn = prefixColomn;
    }

    public String getDbmsType() {
        return dbmsType;
    }

    public void setDbmsType(String dbmsType) {
        this.dbmsType = dbmsType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStorageTable() {
        return storageTable;
    }

    public void setStorageTable(String storageTable) {
        this.storageTable = storageTable;
    }

    public String getStorageIndex() {
        return storageIndex;
    }

    public void setStorageIndex(String storageIndex) {
        this.storageIndex = storageIndex;
    }

    public String getDefaultFolder() {
        return defaultFolder;
    }

    public void setDefaultFolder(String defaultFolder) {
        this.defaultFolder = defaultFolder;
    }
}

