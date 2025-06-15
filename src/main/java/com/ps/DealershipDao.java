package com.ps;

import javax.sql.DataSource;

public class DealershipDao {
    private SalesDao salesDao;
    private LeaseDao leaseDao;

    public DealershipDao(DataSource dataSource) {
        this.salesDao = new SalesDao(dataSource);
        this.leaseDao = new LeaseDao(dataSource);
    }

    public void saveSalesContract(SalesContract contract) {
        salesDao.save(contract);
    }

    public void saveLeaseContract(LeaseContract contract) {
        leaseDao.save(contract);
    }
}
