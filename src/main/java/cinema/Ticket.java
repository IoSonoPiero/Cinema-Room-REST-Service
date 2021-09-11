package cinema;

import java.util.UUID;

public class Ticket {
    private UUID token;
    public Seat ticket;

    public Ticket(Seat ticket) {
        this.token = UUID.randomUUID();
        this.ticket = ticket;
    }

    public Ticket() {}

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Seat getTicket() {
        return ticket;
    }

    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
