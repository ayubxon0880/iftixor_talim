package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Speaking;
import uz.iftixortalim.crmspring.model.Topic;

import java.util.Collection;
import java.util.List;

@Repository
public interface SpeakingRepository extends JpaRepository<Speaking,Long> {
}
