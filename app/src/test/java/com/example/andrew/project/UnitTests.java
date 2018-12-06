package com.example.andrew.project;

import com.example.andrew.project.Model.Availability;
import com.example.andrew.project.Model.Booking;
import com.example.andrew.project.Model.HomeOwner;
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

    @Test
    public void testAvailability(){
        Availability available = new Availability();
        available.setSundayStart(9);
        available.setSundayEnd(5);
        assertEquals("getTime Test", 9,available.getTime(0));
        assertEquals("getTime Test", 5,available.getTime(1));
    }

    @Test
    public void testServiceInstance(){
        ServiceProvider service = new ServiceProvider();
        Service service1 = new Service("painter","bill paints",20.0);

        service.addService(service1);
        assertEquals("getServices Test", "bill paints",service.getServices().get(0).getName());
    }

    // Deliverable 4 Tests

    @Test
    public void testBookingDefaultConstructor() {
        Booking book1 = new Booking();
        book1.setTime(1);
        assertEquals("Correct Default Construction Test", 1, book1.getTime());
    }

    @Test
    public void testBookingPolyConstructor() {
        ServiceProvider testSrvc = new ServiceProvider();
        testSrvc.setLicensed(true);
        HomeOwner testHme = new HomeOwner();
        testHme.setEmail("testing@test.com");

        Booking book2 = new Booking(testSrvc, testHme);
        assertEquals("Correct Polymorphic Construction Test", "testing@test.com", book2.getBooker().getEmail());
    }

    @Test
    public void testBookingGetProvider() {
        ServiceProvider testSrvc = new ServiceProvider();
        testSrvc.setLicensed(true);

        Booking book = new Booking();
        book.setProvider(testSrvc);

        assertEquals("getProvider Test", testSrvc, book.getProvider());
    }

    @Test
    public void testBookingGetBooker() {
        HomeOwner testHme = new HomeOwner();

        Booking book = new Booking();
        book.setBooker(testHme);

        assertEquals("getBooker Test", testHme, book.getBooker());
    }

    @Test
    public void testBookingGetTime() {
        Booking book = new Booking();

        book.setTime(2);
        assertEquals("getTime Test", 2, book.getTime());
    }

    @Test
    public void testBookingGetRating() {
        Booking book = new Booking();

        book.setRating(2);
        assertEquals("getRating Test", 2, book.getRating());
    }

    @Test
    public void testHomeOwnerManipulation() {
        HomeOwner testHme = new HomeOwner();
        Booking book = new Booking();

        book.setBooker(testHme);
        testHme.setEmail("testest@test.com");

        assertEquals("HomeOwner Changes Test", "testest@test.com", book.getBooker().getEmail());
    }

    @Test
    public void testServiceProviderManipulation() {
        ServiceProvider testSrvc = new ServiceProvider();
        Booking book = new Booking();

        book.setProvider(testSrvc);
        testSrvc.setUsername("Meek");

        assertEquals("ServiceProvider Changes Test", "Meek", book.getProvider().getUsername());
    }

    @Test
    public void testChangeTime() {
        Booking book = new Booking();
        book.setTime(5);

        book.setTime(9);
        assertEquals("Change Time Test", 9, book.getTime());
    }

    @Test
    public void testChangeRating() {
        Booking book = new Booking();
        book.setRating(1);

        book.setRating(5);
        assertEquals("Change Rating Test", 5, book.getRating());
    }

}
