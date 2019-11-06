package mitreCrawler.bl.scheduled;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;
import mitreCrawler.bl.crawlers.articles.ThreatPostArticleCrawler;
import mitreCrawler.bl.crawlers.groups.AttackGroupsCrawler;
import mitreCrawler.entities.Group;
import mitreCrawler.repositories.GroupRepository;

@Named
@Slf4j
public class CrawlerSchedulator {
	@Inject
	private AttackGroupsCrawler groupsCrawler;

	@Inject
	private ThreatPostArticleCrawler articlesCrawler;

	// for debug
	@Inject
	private GroupRepository groupRepository;

	// @Scheduled(cron = "${cron.expression}")
	public void executeGroupsCrawler() {
		log.info("started executing groups crawler");
		groupsCrawler.crawl();
		log.info("finished executing groups crawler");
	}

	@Scheduled(cron = "${cron.expression}")
	public void executeArticlesCrawler() {
		log.info("started executing groups crawler");
		for (Group group : groupRepository.findAll()) {
			log.info("[GROUP] " + group.getName());
			articlesCrawler.crawl(group);
		}

		log.info("finished executing groups crawler");
	}
}
