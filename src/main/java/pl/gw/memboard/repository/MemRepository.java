package pl.gw.memboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.gw.memboard.model.Mem;

@Repository
public interface MemRepository extends JpaRepository<Mem, Long> {
}
