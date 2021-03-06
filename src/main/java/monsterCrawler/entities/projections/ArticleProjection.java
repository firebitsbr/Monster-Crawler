package monsterCrawler.entities.projections;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.rest.core.config.Projection;

import monsterCrawler.entities.Article;
import monsterCrawler.entities.Group;

@Projection(name = "article", types = { Article.class })
public interface ArticleProjection {
	public String getId();

	public String getTitle();

	public String getUrl();

	public LocalDate getDate();

	public Collection<Group> getGroups();
}
