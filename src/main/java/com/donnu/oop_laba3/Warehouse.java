package com.donnu.oop_laba3;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component("WarehouseBean")
@Scope("singleton")
@EnableAspectJAutoProxy
public class Warehouse
{
    private ArrayList<Jewel> jewels = new ArrayList<Jewel>();

    public Jewel putInWarehouse(Jewel jewel){
        jewels.add(jewel);
        return jewel;
    }

    public ArrayList<Jewel> getAllJewelsFromWarehouse(){
        return this.jewels;
    }

    public Jewel getJewel(int id){

        return this.jewels.get(id);
    }

    public void getMaterialAllJewels(){
        for(int i = 0; i<this.jewels.size(); i++){
            System.out.printf(" Товар №%s %s - %s", i, this.jewels.get(i).getType(), this.jewels.get(i).getMaterial());
        }
    }

    public void deleteJewelById(int id){
        try {
            String material = this.jewels.get(id).getMaterial();
            this.jewels.remove(id);
            System.out.printf("Товар из материала %s изъят со склада!", material);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void clearWarehouse(){
        this.jewels.clear();
        System.out.println("Склад пуст!");
    }
}
