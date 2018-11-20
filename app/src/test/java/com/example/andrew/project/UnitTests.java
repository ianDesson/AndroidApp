package com.example.andrew.project;

import com.example.andrew.project.Model.Availability;
import com.example.andrew.project.Model.Service;
import com.example.andrew.project.Model.ServiceProvider;
import com.example.andrew.project.Model.User;

import junit.framework.TestCase;
import org.junit.Test;

public class UnitTests extends TestCase {
    @Test
    public void testUserEmail(){
        User user = new User("bill","123","123@test.com");
        assertEquals("getEmail Test","123@test.com", user.getEmail());
    }
    @Test
    public void testUserUsername(){
        User user = new User("bill","123","123@test.com");
        assertEquals("getUsername Test","bill", user.getUsername());
    }
    @Test
    public void testUserPassword(){
        User user = new User("bill","123","123@test.com");
        assertEquals("getPassword Test", "123", user.getPassword());
    }
    @Test
    public void testServiceType(){
        Service service = new Service("painter","bill paints",20.0);
        assertEquals("getType Test", "painter",service.getType());
    }
    @Test
    public void testServiceName(){
        Service service = new Service("painter","bill paints",20.0);
        assertEquals("getName Test", "bill paints",service.getName());
    }

    public void testAvailability(){
        Availability available = new Availability();
        available.setSundayStart(9);
        available.setSundayEnd(5);
        assertEquals("getTime Test", 9,available.getTime(0));
        assertEquals("getTime Test", 5,available.getTime(1));
    }

    public void testServiceInstance(){
        ServiceProvider service = new ServiceProvider();
        Service service1 = new Service("painter","bill paints",20.0);

        service.addService(service1);
        assertEquals("getServices Test", "bill paints",service.getServices().get(0).getName());
    }

}
