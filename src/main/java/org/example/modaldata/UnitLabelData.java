package org.example.modaldata;

public class UnitLabelData {
    private Long unitId;
    private String unitName;

    public UnitLabelData(Long unitId, String unitName) {
        this.unitId = unitId;
        this.unitName = unitName;
    }

    public Long getUnitId() {
        return unitId;
    }

    public String getUnitName() {
        return unitName;
    }
}