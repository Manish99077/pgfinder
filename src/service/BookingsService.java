package service;

import Model.Bookings;
import dao.BookingsDAO;

import java.util.List;

public class BookingsService {
    private BookingsDAO dao = new BookingsDAO();

    public boolean addBooking(Bookings booking) {
        return dao.addBooking(booking);
    }

    public List<Bookings> getBookingsByTenantId(int tenantId) {
        return dao.getBookingsByTenantId(tenantId);
    }

    public List<Bookings> getBookingsForOwner(int ownerId) {
        return dao.getBookingsForOwner(ownerId);
    }

    public List<Bookings> getAllBookings() {
        return dao.getAllBookings();
    }

    public Bookings getBookingById(int id) {
        return dao.getBookingById(id);
    }

    public boolean updateBooking(Bookings booking) {
        return dao.updateBooking(booking);
    }

    public boolean deleteBooking(int id) {
        return dao.deleteBooking(id);
    }
}