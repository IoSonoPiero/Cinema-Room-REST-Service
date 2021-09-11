package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class Controller {
    Cinema cinema = new Cinema();

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinema;
    }

    @PostMapping("/return")
    public ResponseEntity<RefundTicket> refundTicket(@RequestBody Ticket soldTicket) {
        Map<String, String> error = new HashMap<>();

        for (Seat seat : cinema.availableSeats) {
            UUID theToken = seat.getToken();
            if (theToken == null) {
                continue;
            } else if (theToken.equals(soldTicket.getToken())) {
                // Token matches, refund
                seat.setPurchased(false);
                seat.setToken(null);
                RefundTicket refundTicket = new RefundTicket(seat);
                return new ResponseEntity<RefundTicket>(refundTicket, HttpStatus.OK);
            }
        }

        // no token found
        error.put("error", "Wrong token!");
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseSeat(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        Map<String, String> error = new HashMap<>();

        // check if seat range is ok
        if (row > 9 || row <= 0 || column > 9 || column <= 0) {
            error.put("error", "The number of a row or a column is out of bounds!");
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        // check if seat is already purchased
        Seat aSeat = cinema.getSeat(row, column);
        if (aSeat.isPurchased()) {
            error.put("error", "The ticket has been already purchased!");
            return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
        }

        // everything is ok, buy a ticket
        Ticket ticket = new Ticket(cinema.purchaseSeat(row, column));
        cinema.getSeat(row, column).setToken(ticket.getToken());
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @PostMapping("/stats")

    public ResponseEntity<Stats> getStats(@RequestParam(value = "password", required = false) String password) {
        Map<String, String> error = new HashMap<>();

        if(password != null && password.equals("super_secret")) {
            Stats stats = new Stats(cinema);
            return new ResponseEntity<Stats>(stats, HttpStatus.OK);
        }

        error.put("error", "The password is wrong!");
        return new ResponseEntity(error, HttpStatus.UNAUTHORIZED);
    }
}