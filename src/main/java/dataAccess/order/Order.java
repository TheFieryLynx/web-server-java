package dataAccess.order;

import dataAccess.EntityWithId;
import dataAccess.client.Client;
import dataAccess.film.Film;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Orders")
public class Order implements EntityWithId {
    private long order_id = -1;
    private Client client;
    private Film film;
    private String medium;
    private int price;
    private java.sql.Date film_issue_date;
    private java.sql.Date film_return_date;

    public Order() {}

    public Order(Client client_id,
                 Film film_id,
                 String medium,
                 int price,
                 Date film_issue_date,
                 Date film_return_date) {
        this.client = client_id;
        this.film = film_id;
        this.medium = medium;
        this.price = price;
        this.film_issue_date = film_issue_date;
        this.film_return_date = film_return_date;
    }

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client_id) {
        this.client = client_id;
    }

    @ManyToOne(targetEntity = Film.class)
    @JoinColumn(name = "film_id")
    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film_id) {
        this.film = film_id;
    }

    @Column(name = "medium")
    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Column(name = "film_issue_date")
    public java.sql.Date getFilm_issue_date() {
        return film_issue_date;
    }

    public void setFilm_issue_date(java.sql.Date film_issue_date) {
        this.film_issue_date = film_issue_date;
    }

    @Column(name = "film_return_date")
    public java.sql.Date getFilm_return_date() {
        return film_return_date;
    }

    public void setFilm_return_date(java.sql.Date film_return_date) {
        this.film_return_date = film_return_date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Order other = (Order) obj;

        // film_return_date may be null if the film is not returned
        if ((this.film_return_date == null) && (other.film_return_date != null)) { return false; }
        if ((this.film_return_date != null) && !this.film_return_date.equals(other.film_return_date)) {
            return false;
        }
        return (this.order_id == other.order_id) &&
                this.client.equals(other.client) &&
                this.film.equals(other.film) &&
                this.medium.equals(other.medium) &&
                (this.price == other.price) &&
                this.film_issue_date.equals(other.film_issue_date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", client_id=" + client +
                ", film_id=" + film +
                ", medium='" + medium + '\'' +
                ", price=" + price +
                ", film_issue_date=" + film_issue_date +
                ", film_return_date=" + film_return_date +
                '}';
    }

    @Override
    public long receivePrimaryKey() {
        return getOrder_id();
    }

    public boolean checkCorrectness(){
        return (medium.equals("disk") || medium.equals("cassette") &&
                (this.film_issue_date != null));
    }
}
