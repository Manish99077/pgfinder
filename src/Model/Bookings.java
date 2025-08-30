package Model;

public class Bookings {
    private int id;
    private int pgId;
    private int tenantId;
    private String status;

    public Bookings(int id, int pgId, int tenantId, String status) {
        this.id = id;
        this.pgId = pgId;
        this.tenantId = tenantId;
        this.status = status;
    }

    public Bookings(int pgId, int tenantId, String status) {
        this.pgId = pgId;
        this.tenantId = tenantId;
        this.status = status;
    }

    public int getId() { return id; }
    public int getPgId() { return pgId; }
    public int getTenantId() { return tenantId; }
    public String getStatus() { return status; }

    public void setId(int id) { this.id = id; }
    public void setPgId(int pgId) { this.pgId = pgId; }
    public void setTenantId(int tenantId) { this.tenantId = tenantId; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", pgId=" + pgId +
                ", tenantId=" + tenantId +
                ", status='" + status + '\'' +
                '}';
    }
}
