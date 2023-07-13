package br.com.romanconverter.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.romanconverter.models.entities.ConversionHistory;

@Repository
@Transactional
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {

    public List<ConversionHistory> findByDate(Date date);
}
