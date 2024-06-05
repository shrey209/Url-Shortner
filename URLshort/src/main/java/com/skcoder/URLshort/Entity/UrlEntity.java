package com.skcoder.URLshort.Entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="short_URL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlEntity  implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	long id;
	
	String urlString;
	
}
