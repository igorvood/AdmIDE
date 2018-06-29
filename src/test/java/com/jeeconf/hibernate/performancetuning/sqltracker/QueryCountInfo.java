package com.jeeconf.hibernate.performancetuning.sqltracker;

/**
 * Created by Igor Dmitriev / Mikalai Alimenkou on 12/6/15
 */
public class QueryCountInfo {
    private int selectCount;
    private int insertCount;
    private int updateCount;
    private int deleteCount;
    private int callCount;

    public void incrementSelectCount() {
        selectCount++;
    }

    public void incrementInsertCount() {
        insertCount++;
    }

    public void incrementUpdateCount() {
        updateCount++;
    }

    public void incrementDeleteCount() {
        deleteCount++;
    }

    public void incrementCallCount() {
        callCount++;
    }

    public void clear() {
        selectCount = 0;
        insertCount = 0;
        updateCount = 0;
        deleteCount = 0;
        callCount = 0;
    }

    public int countAll() {
        return selectCount + insertCount + updateCount + deleteCount + callCount;
    }

    public int getSelectCount() {
        return selectCount;
    }

    public void setSelectCount(int selectCount) {
        this.selectCount = selectCount;
    }

    public int getInsertCount() {
        return insertCount;
    }

    public void setInsertCount(int insertCount) {
        this.insertCount = insertCount;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public int getDeleteCount() {
        return deleteCount;
    }

    public void setDeleteCount(int deleteCount) {
        this.deleteCount = deleteCount;
    }

    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }
}
