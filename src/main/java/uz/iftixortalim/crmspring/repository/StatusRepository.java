package uz.iftixortalim.crmspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.iftixortalim.crmspring.model.Status;
import uz.iftixortalim.crmspring.model.User;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
    Optional<Status> findByName(String status);
}
