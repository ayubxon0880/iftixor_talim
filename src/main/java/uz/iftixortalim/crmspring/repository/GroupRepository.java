package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Authority;
import uz.iftixortalim.crmspring.model.Group;

import java.util.List;
import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
