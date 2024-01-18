package dao;

import entity.StoreUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStoreUserMappingRepository extends JpaRepository<StoreUserMapping, Long> {
}
