package com.donnu.oop_laba3;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Earrings earrings = context.getBean("EarringsBean", Earrings.class);
        Ring ring = context.getBean("RingBean", Ring.class);
        Watch watch = context.getBean("WatchBean", Watch.class);
        Warehouse warehouse = context.getBean("WarehouseBean", Warehouse.class);
        earrings.setType("Сапфировые серьги");
        earrings.setMaterial("Золото");
        earrings.putInWarehouse(warehouse);
        ring.setType("Кольцо");
        ring.setMaterial("Серебро");
        ring.putInWarehouse(warehouse);
        watch.setType("Часы");
        watch.setMaterial("Платина");
        watch.putInWarehouse(warehouse);
        try {
            warehouse.getJewel(2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        context.close();
    }
}
