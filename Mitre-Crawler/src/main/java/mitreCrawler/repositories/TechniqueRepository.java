package mitreCrawler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mitreCrawler.entities.Technique;

@Repository
public interface TechniqueRepository extends JpaRepository<Technique, String> {
}
