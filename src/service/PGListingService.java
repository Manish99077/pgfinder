package service;

import Model.PGListings;
import dao.PGListingDAO;

import java.util.List;

public class PGListingService {
    private PGListingDAO dao = new PGListingDAO();

    public boolean addPGListing(PGListings pg) {
        return dao.addPGListing(pg);
    }

    public PGListings getPGListingById(int id) {
        return dao.getPGListingById(id);
    }

    public boolean updatePGListing(PGListings pg) {
        return dao.updatePGListing(pg);
    }

    public boolean deletePGListing(int id) {
        return dao.deletePGListing(id);
    }

    public List<PGListings> getAllPGListings() {
        return dao.getAllPGListings();
    }

    public List<PGListings> getPGListingsByOwnerId(int ownerId) {
        return dao.getPGListingsByOwnerId(ownerId);
    }
}