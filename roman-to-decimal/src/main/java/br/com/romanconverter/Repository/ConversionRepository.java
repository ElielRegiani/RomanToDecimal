package br.com.romanconverter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.romanconverter.models.entities.Conversion;

@Repository
@Transactional
public interface ConversionRepository extends JpaRepository<Conversion, Long> {

}
