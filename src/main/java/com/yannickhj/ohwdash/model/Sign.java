package com.yannickhj.ohwdash.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

//Aanmaken entiteit en dmv. Hibernate annotaties aanmaken in de database
@Entity
@Table(name = "signs")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class Sign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank
    private String locatienummer;
    private String soort;
    private String grootte;
    private String biestekst;
    private String plaats;
    private String gemeente;
    private String straatnaam;
    private String route;
    private int xcord;
    private int ycord;
    private Boolean onderhoud=false;
    private String controleur;
    private String acties;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocatienummer() {
		return locatienummer;
	}

	public void setLocatienummer(String locatienummer) {
		this.locatienummer = locatienummer;
	}

	public String getSoort() {
		return soort;
	}

	public void setSoort(String soort) {
		this.soort = soort;
	}

	public String getGrootte() {
		return grootte;
	}

	public void setGrootte(String grootte) {
		this.grootte = grootte;
	}

	public String getBiestekst() {
		return biestekst;
	}

	public void setBiestekst(String biestekst) {
		this.biestekst = biestekst;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	public String getGemeente() {
		return gemeente;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	public String getStraatnaam() {
		return straatnaam;
	}

	public void setStraatnaam(String straatnaam) {
		this.straatnaam = straatnaam;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public int getXcord() {
		return xcord;
	}

	public void setXcord(int xcord) {
		this.xcord = xcord;
	}

	public int getYcord() {
		return ycord;
	}

	public void setYcord(int ycord) {
		this.ycord = ycord;
	}

	public Boolean getOnderhoud() {
		return onderhoud;
	}

	public void setOnderhoud(Boolean onderhoud) {
		this.onderhoud = onderhoud;
	}

	public String getControleur() {
		return controleur;
	}

	public void setControleur(String controleur) {
		this.controleur = controleur;
	}

	public String getActies() {
		return acties;
	}

	public void setActies(String acties) {
		this.acties = acties;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
