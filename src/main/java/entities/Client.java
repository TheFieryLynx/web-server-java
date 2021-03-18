package entities;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public class Client {
    private long client_id;
    private String client_name;
    private String phone;
    private boolean client_is_removed;

    public Client() {}

    public Client(long client_id, String client_name, String phone, boolean client_is_removed) {
        this.client_id = client_id;
        this.client_name = client_name;
        this.phone = phone;
        this.client_is_removed = client_is_removed;
    }

    @Column(name = "client_name")
    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "client_is_removed")
    public boolean getClient_is_removed() {
        return client_is_removed;
    }

    public void setClient_is_removed(boolean is_client_removed) {
        this.client_is_removed = is_client_removed;
    }

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }
}
