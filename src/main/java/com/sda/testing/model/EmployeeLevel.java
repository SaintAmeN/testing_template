package com.sda.testing.model;

public enum EmployeeLevel {
    EXECUTIVE(null), MANAGER(EXECUTIVE), LEAD(MANAGER), WORKER(LEAD),   // levels of emplyment
    SALES(MANAGER),                              //
    INDEPENDENT(null),                        // can't get promotion
    ACCOUNTING(MANAGER);

    private final EmployeeLevel nextLevel;

    EmployeeLevel(EmployeeLevel nextLevel) {
        this.nextLevel = nextLevel;
    }

    public EmployeeLevel getNextLevel() {
        return nextLevel;
    }
}
