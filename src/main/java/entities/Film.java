package entities;

import javax.persistence.*;

@Entity
@Table(name = "Films")
public class Film implements EntityWithId {
    private long film_id = -1;
    private String film_name;
    private String producer;
    private int release_year;
    private int cassette_total_number;
    private int disc_total_number;
    private int cassette_available_number;
    private int disc_available_number;
    private int cassette_price;
    private int disk_price;
    private boolean film_is_removed;


    public Film() {}

    public Film(String film_name,
                String producer,
                int release_year,
                int cassette_total_number,
                int disc_total_number,
                int cassette_available_number,
                int disc_available_number,
                int cassette_price,
                int disk_price,
                boolean film_is_removed) {
        this.film_name = film_name;
        this.producer = producer;
        this.release_year = release_year;
        this.cassette_total_number = cassette_total_number;
        this.disc_total_number = disc_total_number;
        this.cassette_available_number = cassette_available_number;
        this.disc_available_number = disc_available_number;
        this.cassette_price = cassette_price;
        this.disk_price = disk_price;
        this.film_is_removed = film_is_removed;
    }

    @Id
    @Column(name = "film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(long film_id) {
        this.film_id = film_id;
    }

    @Column(name = "film_name")
    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    @Column(name = "producer")
    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Column(name = "release_year")
    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    @Column(name = "cassette_total_number")
    public int getCassette_total_number() {
        return cassette_total_number;
    }

    public void setCassette_total_number(int cassette_total_number) {
        this.cassette_total_number = cassette_total_number;
    }

    @Column(name = "disc_total_number")
    public int getDisc_total_number() {
        return disc_total_number;
    }

    public void setDisc_total_number(int disc_total_number) {
        this.disc_total_number = disc_total_number;
    }

    @Column(name = "cassette_available_number")
    public int getCassette_available_number() {
        return cassette_available_number;
    }

    public void setCassette_available_number(int cassette_available_number) {
        this.cassette_available_number = cassette_available_number;
    }

    @Column(name = "disc_available_number")
    public int getDisc_available_number() {
        return disc_available_number;
    }

    public void setDisc_available_number(int disc_available_number) {
        this.disc_available_number = disc_available_number;
    }

    @Column(name = "cassette_price")
    public int getCassette_price() {
        return cassette_price;
    }

    public void setCassette_price(int cassette_price) {
        this.cassette_price = cassette_price;
    }

    @Column(name = "disk_price")
    public int getDisk_price() {
        return disk_price;
    }

    public void setDisk_price(int disk_price) {
        this.disk_price = disk_price;
    }

    @Column(name = "film_is_removed")
    public boolean isFilm_is_removed() {
        return film_is_removed;
    }

    public void setFilm_is_removed(boolean film_is_removed) {
        this.film_is_removed = film_is_removed;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj.getClass() != this.getClass()) { return false; }
        final Film other = (Film) obj;
        return (this.film_id == other.film_id) &&
                (this.film_name.equals(other.film_name)) &&
                (this.producer.equals(other.producer)) &&
                (this.cassette_total_number == other.cassette_total_number) &&
                (this.disc_total_number == other.disc_total_number) &&
                (this.cassette_available_number == other.cassette_available_number) &&
                (this.disc_available_number == other.disc_available_number) &&
                (this.cassette_price == other.cassette_price) &&
                (this.disk_price == other.disk_price) &&
                (this.film_is_removed == other.film_is_removed);
    }

    @Override
    public long receiveId() {
        return getFilm_id();
    }
}
