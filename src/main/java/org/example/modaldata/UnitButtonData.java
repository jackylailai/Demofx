package org.example.modaldata;

public class UnitButtonData {
    private Long unitId;
    private String unitName;

    public UnitButtonData(Long unitId, String unitName) {
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