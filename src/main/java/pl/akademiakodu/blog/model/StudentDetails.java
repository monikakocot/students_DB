package pl.akademiakodu.blog.model;

import javax.persistence.*;

@Entity
public class StudentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="lastname")
    private String lastname;
    @Column(name="phone_number")
    private String phoneNumber;

/* //Version L - TODO add additional fields
    @Column(name = "register_date")
    private Date registerDate;
    @Column(name = "update_date")
    private Date updateDate;

        public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
*/

    //CONSTRUCTORS, GETTERS,SETTERS

    public StudentDetails() {
    }

    public StudentDetails(Long id, String lastname, String phoneNumber) {
        this.id = id;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
