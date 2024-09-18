package app.ecommerce.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
@Inheritance(strategy = jakarta.persistence.InheritanceType.JOINED)
//@DiscriminatorColumn(name="type_compte",columnDefinition = "ENUM("Admin","Client")")
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "className")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Client.class, name = "Client"),
        @JsonSubTypes.Type(value = Admin.class, name = "Admin")
})

public abstract class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(views.ViewBase.class)

    private  int idCompte;

    @JsonView(views.ViewBase.class)
    private  String username;
    @JsonView(views.ViewBase.class)
    @Column(name = "password", columnDefinition = "TEXT")
    private  String password;
    @JsonView(views.ViewBase.class)
    private  String token;





    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Compte(int idCompte, String username, String password) {
        super();
        this.idCompte = idCompte;
        this.username = username;
        this.password = password;
    }

    public Compte(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public Compte() {
        super();

    }
    public int getIdCompte() {
        return idCompte;
    }
    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }







}