package manager.hbm;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactsRecord {

    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "firstname")
    public String firstname;
    @Column(name = "middlename")
    public String middlename;
    @Column(name = "lastname")
    public String lastname;

    public String nickname = new String();
    public String company = new String();
    public String title = new String();
    public String address = new String();
    public String home = new String();
    public String mobile = new String();
    public String work = new String();
    public String fax = new String();
    public String email = new String();
    public String email2 = new String();
    public String email3 = new String();
    public String homepage = new String();
    public String im = new String();
    public String im2 = new String();
    public String im3 = new String();
    public Integer bday = 0;
    public String bmonth = "-";
    public String byear = new String();
    public Integer aday = 0;
    public String amonth = "-";
    public String ayear = new String();
    public String address2 = new String();
    public String phone2 = new String();
    public String photo = new String();
    public String notes = new String();
    public Date created = new Date();
    public Date modified = new Date();
    public String deprecated = "0000-00-00 00:00:00";

    public ContactsRecord() {
    }

    public ContactsRecord(int id, String firstname, String middlename, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
    }

}
