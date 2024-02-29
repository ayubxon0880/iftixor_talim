package uz.iftixortalim.crmspring.repository;

import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.iftixortalim.crmspring.model.Like;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    @Query(nativeQuery = true,value = "SELECT count(*) FROM likes where speaking_id=?1")
    Long findLikesCount(Long id);
    Boolean existsBySpeakingIdAndUserId(Long speaking_id, Long user_id);
    Optional<Like> findBySpeakingIdAndUserId(Long id, Long id1);
}
