package cinema;

public class RefundTicket {
    public Seat returnedTicket;

    public RefundTicket(Seat ticket) {
        this.returnedTicket = ticket;
    }

    public RefundTicket() {
    }

    public void setTicket(Seat ticket) {
        this.returnedTicket = ticket;
    }
}
