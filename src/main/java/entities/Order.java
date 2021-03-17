package entities;

import javax.persistence.*;
import java.sql.Date;

// todo column names
@Entity
@Table(name = "Orders")
public class Order {
    private long order_id;
    private long client_id;
    private long film_id;
    private String medium;
    private int price;
    private java.sql.Date film_issue_date;
    private java.sql.Date film_return_date;

    public Order() {}

    public Order(long client_id, long film_id, String medium, int price, Date film_issue_date, Date film_return_date) {
        this.client_id = client_id;
        this.film_id = film_id;
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

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(long film_id) {
        this.film_id = film_id;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public java.sql.Date getFilm_issue_date() {
        return film_issue_date;
    }

    public void setFilm_issue_date(java.sql.Date film_issue_date) {
        this.film_issue_date = film_issue_date;
    }

    public java.sql.Date getFilm_return_date() {
        return film_return_date;
    }

    public void setFilm_return_date(java.sql.Date film_return_date) {
        this.film_return_date = film_return_date;
    }
}
