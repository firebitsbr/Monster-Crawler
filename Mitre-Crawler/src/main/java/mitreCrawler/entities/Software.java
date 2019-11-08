package mitreCrawler.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "atk_softwares")

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Software implements NamedEntity{
	@Id
	@JoinColumn(name = "software_id")
	private String id;
	@Column(nullable = false, unique = false)
	private String name;
	@Column(length = 50000, nullable = true, unique = false)
	private String description;
}
