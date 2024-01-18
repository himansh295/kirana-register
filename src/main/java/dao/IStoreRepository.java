package dao;

import entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStoreRepository extends JpaRepository<Store, Long> {
}
