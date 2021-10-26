package com.donnu.oop_laba3;

public class Jewel implements  PutWarehouseAble
{
    private String material;
    private String type;

    @Override
    public void setMaterial(String material)
    {
        this.material = material;
    }

    @Override
    public String getMaterial()
    {
        return this.material;
    }

    @Override
    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String getType()
    {
        return this.type;
    }

    @Override
    public void putInWarehouse(Warehouse warehouse) {

        warehouse.putInWarehouse(this);
    }

}
