package tickets.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "concertName")
    private String concertName;

    @Column(name = "concertText")
    private String concertText;

    @Column(name = "price")
    private String price;

    @Column(name = "concertDate")
    private String concertDate;

    @Column(name = "seat")
    private String seat;

    public Order() {
    }

    public Order(Integer id,String username, String concertName,
                 String concertText, String price,
                 String concertDate, String seat) {
        this.id = id;
        this.username = username;
        this.concertName = concertName;
        this.concertText = concertText;
        this.price = price;
        this.concertDate = concertDate;
        this.seat = seat;

    }
    public Order(String username, String concertName,
                 String concertText, String price,
                 String concertDate, String seat) {
        this.username = username;
        this.concertName = concertName;
        this.concertText = concertText;
        this.price = price;
        this.concertDate = concertDate;
        this.seat = seat;
    }

    public Order(String concertText, String price, String seat) {
        this.concertText = concertText;
        this.price = price;
        this.seat = seat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer Id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public String getConcertText() {
        return concertText;
    }

    public void setConcertText(String concertText) {
        this.concertText = concertText;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return concertDate;
    }

    public void setConcertDate(String concertDate) {
        this.concertDate = concertDate;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }


}