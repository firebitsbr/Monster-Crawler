package mitreCrawler.bl.crawlers.articles;

import static utils.CrawelersUtils.encodeUrl;
import static utils.CrawelersUtils.getFirstElementByClass;

import javax.inject.Named;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;

import mitreCrawler.entities.Group;

@Named
public class ZdnetArticleCrawler extends AbstractArticlesCrawler<Group> {
	private static final String SEARCH = "search/";
	private static final String SEARCH_QUERY = "?o=1&q=";

	@Value("${ZDNET_URL}")
	private String zdnetUrl;

	@Override
	public String buildUrl(Group entity) {
		return zdnetUrl + SEARCH + SEARCH_QUERY + '"' + encodeUrl(entity.getName()) + '"';
	}

	@Override
	public String buildSearchUrl(Group entity, int currentPage) {
		return zdnetUrl + SEARCH + "/" + (currentPage++) + "/" + SEARCH_QUERY + '"' + encodeUrl(entity.getName()) + '"';
	}

	@Override
	public String extractTitle(Element article) {
		return article.selectFirst("h3").text();
	}

	@Override
	public Elements extractArticlesElements(Document doc) {
		return doc.select("article");
	}

	@Override
	public String extractArticleDate(Element article) {
		return getFirstElementByClass(article, "meta").selectFirst("span").text();
	}

	@Override
	public String getDateFormatPattern() {
		return "MMMM d, yyyy";
	}

	@Override
	public int getFirstSearchPageIndex() {
		return 2;
	}
}
