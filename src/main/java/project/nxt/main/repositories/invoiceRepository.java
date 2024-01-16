package project.nxt.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.nxt.main.models.invoice;

import java.util.List;

public interface invoiceRepository extends JpaRepository<invoice, String> {
    @Query(value = "SELECT * FROM invoices WHERE MONTH(time) = :month AND YEAR(time) = :year", nativeQuery = true)
    List<invoice> findByDate(int month,int year);

    @Query(value = "SELECT * FROM invoices WHERE contract_id = :contract_id", nativeQuery = true)
    List<invoice> findByContractId(String contract_id);
}
