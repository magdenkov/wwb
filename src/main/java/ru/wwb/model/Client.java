package ru.wwb.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "clients")
public class Client extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "birth_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime birthDate;

    @Column(name = "address")
    @NotEmpty
    private String address;

    @Transient
    private Integer age;

    @Transient
    private Integer totalMoney;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Account> accounts;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    protected void setAccountsInternal(Set<Account> accounts) {
        this.accounts = accounts;
    }

    protected Set<Account> getAccountsInternal() {
        if (this.accounts == null) {
            this.accounts = new HashSet<>();
        }
        return this.accounts;
    }

    public List<Account> getAccounts() {
        List<Account> sortedAccounts = new ArrayList<>(getAccountsInternal());
        PropertyComparator.sort(sortedAccounts, new MutableSortDefinition("id", true, true));
        return Collections.unmodifiableList(sortedAccounts);
    }

    public void addAccount(Account account) {
        getAccountsInternal().add(account);
        account.setClient(this);
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
       /* DateTime now = new DateTime();
        age = Years.yearsBetween(birthDate, now).getYears();*/
        return age;
    }

    public Integer getTotalMoney() {
        totalMoney = 0;
        for (Account acc: accounts) {
            totalMoney += acc.getMoneyAmount();
        }
        return totalMoney;
    }
}
