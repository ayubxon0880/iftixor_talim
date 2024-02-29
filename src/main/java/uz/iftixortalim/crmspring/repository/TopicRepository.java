package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {
}
