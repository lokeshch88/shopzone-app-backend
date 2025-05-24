package com.shopzone.app.dto;

import java.time.LocalDateTime;

public class EmailResponse {

    private String status;     // e.g. "SUCCESS", "FAILED"
    private String message;    // e.g. "Email sent successfully"
    private LocalDateTime timestamp;  // Optional: When the email was sent

    // Getters and Setters

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
