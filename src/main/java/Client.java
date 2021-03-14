import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    private long client_id;
    private String client_name;
    private String phone;
    private boolean client_is_removed;

    public Client() {
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isClient_is_removed() {
        return client_is_removed;
    }

    public void setClient_is_removed(boolean client_is_removed) {
        this.client_is_removed = client_is_removed;
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
