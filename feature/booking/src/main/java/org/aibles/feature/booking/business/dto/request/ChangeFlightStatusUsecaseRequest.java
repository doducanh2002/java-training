package org.aibles.feature.booking.business.dto.request;

public class ChangeFlightStatusUsecaseRequest {

    private String flightId;
    private String status;

    public ChangeFlightStatusUsecaseRequest() {
    }

    public ChangeFlightStatusUsecaseRequest(String flightId, String status) {
        this.flightId = flightId;
        this.status = status;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
