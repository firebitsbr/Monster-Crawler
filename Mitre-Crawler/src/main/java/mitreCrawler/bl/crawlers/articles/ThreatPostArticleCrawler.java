package mitreCrawler.bl.crawlers.articles;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Named;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;
import mitreCrawler.entities.Group;
import mitreCrawler.repositories.ArticleRepository;

//@Named
@Slf4j
public class ThreatPostArticleCrawler implements ArticlesCrawler<Group> {
	private static final String SEARCH = "?s=";
	private static final String API_URL = "wp-admin/admin-ajax.php";

	@Value("${THREAT_POST_URL}")
	private String ThreatPostUrl;

	@Inject
	private ArticleRepository articlesRepository;

	@Override
	public Elements extractArticlesElements(Document doc) {
		return doc.select("article");
	}

	@Override
	public Elements loadAndExtractNextArticles(Group entity) throws IOException {
		int currentPage = 1;
		Elements articlesElements = new Elements();
		Document doc;
		do {
			doc = Jsoup.connect(ThreatPostUrl + API_URL).data("action", "loadmore")
					.data("query", URLEncoder.encode("{\"s\":\"" + entity.getName() + "\",\"order\":\"DESC\"}"))
					.data("page", Integer.toString(currentPage++)).post();

			articlesElements.addAll(extractArticlesElements(doc));
		} while (doc.hasText());

		return articlesElements;
	}

	@Override
	public String extractTitle(Element article) {
		return article.getElementsByClass("c-card__title").first().text();
	}

	@Override
	public LocalDate extractArticleDate(Element article) {
		return LocalDate.parse(article.select("time").first().attr("datetime").split("T")[0],
				DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US));

	}

	@Override
	public String buildUrl(Group entity) {
		return ThreatPostUrl + SEARCH + '"' + entity.getName().replace(" ", "%20") + '"';
	}

	@Override
	public String getEntityName(Group entity) {
		return entity.getName();
	}

	@Override
	public ArticleRepository getRepository() {
		return articlesRepository;
	}

	@Override
	public Logger getLogger() {
		return log;
	}
}
