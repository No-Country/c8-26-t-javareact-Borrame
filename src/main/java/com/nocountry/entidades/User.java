package com.nocountry.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUser;
	
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "nombre_usuario", nullable = false, length = 60)
	private String userName;
	
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "apellido_usuario", nullable = false, length = 60)
	private String userSurname;
	
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "email", nullable = false, length = 60)
	private String email;
	
	@NotNull(message = "No puede ser nulo")
	@NotEmpty(message = "No puede ser vacío")
	@Column(name = "telefono", nullable = false, length = 60)	
	private String phoneNumber;
	
	@Column(name = "fecha_creacion", nullable = false)
	private Date creationDate;
	
	@Column(name = "fecha_modificacion")
	private Date updateDate;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"usuario"})
	private Set<Service> services;
	
	
	
	
	public User(long idUser) {
		this.idUser = idUser;
	}

	public User( String userName, String userSurname, String email, String phoneNumber, Date creationDate) {
		this.userName = userName;
		this.userSurname = userSurname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.creationDate = creationDate;
	}
	
	public User(long idUser, String userName, String userSurname, String email, String phoneNumber, Date creationDate) {
		this.idUser = idUser;
		this.userName = userName;
		this.userSurname = userSurname;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.creationDate = creationDate;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(email, idUser, phoneNumber, userName, userSurname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && idUser == other.idUser
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(userName, other.userName)
				&& Objects.equals(userSurname, other.userSurname);
	}
	
	
	@PrePersist
	private void antesPersistir() {
		this.creationDate = new Date();
	}

	@PreUpdate
	private void antesActualizar() {
		this.updateDate = new Date();
	}

	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [idUser=");
		builder.append(idUser);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userSurname=");
		builder.append(userSurname);
		builder.append(", email=");
		builder.append(email);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", services=");
		builder.append(services);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6782471098653806527L;
	
	

}
