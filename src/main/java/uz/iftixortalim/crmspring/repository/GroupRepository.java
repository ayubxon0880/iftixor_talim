package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Authority;
import uz.iftixortalim.crmspring.model.Group;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    List<Group> findByTeacherId(Long id);

    List<Group> findByDirectionLike(String direction);

    Optional<Group> findByDirectionAndTeacherId(String direction, Long id);
}
