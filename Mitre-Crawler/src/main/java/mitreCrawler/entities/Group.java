package mitreCrawler.entities;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group {
	private String name;
	private String id;
	private String version;
	private String description;
	private Collection<Technique> techniques;
	private Collection<String> groupDescriptions;
	private Collection<String> softwares;
}
